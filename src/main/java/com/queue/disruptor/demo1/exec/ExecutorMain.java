package com.queue.disruptor.demo1.exec;

import com.lmax.disruptor.BatchEventProcessor;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.SequenceBarrier;
import com.lmax.disruptor.YieldingWaitStrategy;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

/**
 * @Author: BakerZhu
 * @Date: 2018/4/16 22:30
 * @Description:
 */
public class ExecutorMain {
    public static void main(String[] args) throws Exception {
        ExecutorService executor = Executors.newSingleThreadExecutor(new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                return new Thread(r);
            }
        });
        final long EXEC_COUNT = 1000L * 1000L * 100L;
        long expectedResult = ExeUtils.getCount(EXEC_COUNT);
        System.out.println(expectedResult);
        CountDownLatch countDownLatch = new CountDownLatch(1);

        RingBuffer<ExeEvent> ringBuffer = RingBuffer.createSingleProducer(ExeEvent.EVENT_FACTORY,1024 * 64 , new YieldingWaitStrategy());
        SequenceBarrier sequenceBarrier = ringBuffer.newBarrier();

        ExeEventHandler handler = new ExeEventHandler();
        BatchEventProcessor<ExeEvent> batchEventProcessor
                = new BatchEventProcessor<ExeEvent>(ringBuffer,sequenceBarrier,handler);
        long expectedCount = batchEventProcessor.getSequence().get() + EXEC_COUNT;
        handler.reset(countDownLatch,expectedCount);
        ringBuffer.addGatingSequences(batchEventProcessor.getSequence());

        executor.submit(batchEventProcessor);
        for(int i = 0 ; i < EXEC_COUNT ; i++){
            long next = ringBuffer.next();
            ringBuffer.get(next).setValue(i);
            ringBuffer.publish(next);
        }
        countDownLatch.await();
        System.out.println(handler.getCount());
        System.out.println(expectedCount);
        batchEventProcessor.halt();



    }
}
