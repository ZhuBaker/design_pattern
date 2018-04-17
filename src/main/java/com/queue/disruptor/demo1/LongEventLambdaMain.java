package com.queue.disruptor.demo1;

import com.lmax.disruptor.BatchEventProcessor;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.SequenceBarrier;
import com.lmax.disruptor.YieldingWaitStrategy;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;

import java.nio.ByteBuffer;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by zhubo on 2018/4/14.
 */
public class LongEventLambdaMain {

    public static void main(String[] args) {
        /*ExecutorService executor = Executors.newCachedThreadPool();
        LongEventFactory factory = new LongEventFactory();
        LongEventHandler handler = new LongEventHandler();

        Disruptor<LongEvent> disruptor = new Disruptor<LongEvent>(factory , 1024, executor , ProducerType.SINGLE, new YieldingWaitStrategy());
        disruptor.handleEventsWith(handler);
        disruptor.start();
        RingBuffer<LongEvent> ringBuffer = disruptor.getRingBuffer();

        //ringBuffer.publishEvent();
        ByteBuffer bb = ByteBuffer.allocate(8);
        ringBuffer.publishEvent(((event, sequence, buffer) -> event.setValue(buffer.getLong())),bb);*/

        //ringBuffer.publishEvent(((event, sequence) -> event.setValue(bb.getLong())));

        doTest2();

    }

    public static void doTest2(){

        ExecutorService executor = Executors.newSingleThreadExecutor();
        Long expectedValue = 1000L * 1000L * 100L;
        RingBuffer<LongEvent> ringBuffer = RingBuffer.createSingleProducer(new LongEventFactory(),1024 * 64 , new YieldingWaitStrategy());
        SequenceBarrier sequenceBarrier = ringBuffer.newBarrier();
        BatchEventProcessor<LongEvent> batchEventProcessor = new BatchEventProcessor<>(ringBuffer,sequenceBarrier,new LongEventHandler());
        ringBuffer.addGatingSequences(batchEventProcessor.getSequence());
        executor.submit(batchEventProcessor);
        long currentTime = System.currentTimeMillis();
        for (long i = 0 ; i < expectedValue ; i++) {
            long next  = ringBuffer.next();
            ringBuffer.get(next).setValue(i);
            ringBuffer.publish(next);
        }






    }
}
