package com.queue.disruptor.onetoone;

import com.lmax.disruptor.BatchEventProcessor;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.SequenceBarrier;
import com.lmax.disruptor.YieldingWaitStrategy;
import com.queue.disruptor.AbstractPerfTestDisruptor;
import com.queue.disruptor.DaemonThreadFactory;
import com.queue.disruptor.PerfTestUtil;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author: BakerZhu
 * @Date: 2018/4/14 19:09
 * @Description: 这是最简单的场景，一个BatchEventProcessor
 */
public class OneProcuderToOneBatchEventProcessor extends AbstractPerfTestDisruptor {

    private static final int BUFFER_SIZE = 1024 * 64;
    private static final long ITERATIONS = 1000L * 1000L * 100L;
    private final ExecutorService executor = Executors.newSingleThreadExecutor(DaemonThreadFactory.INSTANCE);
    private final long expectedResult = PerfTestUtil.accumulatedAddition(ITERATIONS);

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    // 构造RingBuffer
     private final RingBuffer<ValueEvent> ringBuffer =
            RingBuffer.createSingleProducer(ValueEvent.EVENT_FACTORY,BUFFER_SIZE,new YieldingWaitStrategy());

    /**
     * 构造 BatchEventProcessor 及 依赖关系
     * 在Disruptor中，消费者是以EventProcessor的形式存在的。其中一类消费者是BatchEvenProcessor。每个BatchEvenProcessor有一个Sequence，
     * 来记录自己消费RingBuffer中消息的情况。所以，一个消息必然会被每一个BatchEvenProcessor消费。
     */
    private final SequenceBarrier sequenceBarrier = ringBuffer.newBarrier();
    private final ValueEventHandler handler = new ValueEventHandler();
    private final BatchEventProcessor<ValueEvent> batchEventProcessor =
            new BatchEventProcessor<ValueEvent>(ringBuffer,sequenceBarrier,handler);
    {
        // 构造反向依赖
        // RingBuffer会追踪有依赖关系的EventProcessor中最小的Sequence（如果不能根据依赖关系判断Sequence大小，则全部追踪）。需要追踪的Sequence会加入到RingBuffer的gatingSequences数组中。
        ringBuffer.addGatingSequences(batchEventProcessor.getSequence());
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    protected int getRequiredProcessorCount() {
        return 2;
    }

    @Override
    protected PerfTestContext runDisruptorPass() throws Exception {
        PerfTestContext perfTestContext = new PerfTestContext();
        final CountDownLatch latch = new CountDownLatch(1);
        long expectedCount = batchEventProcessor.getSequence().get() + ITERATIONS;
        handler.reset(latch, expectedCount);
        executor.submit(batchEventProcessor);
        long start = System.currentTimeMillis();
        final RingBuffer<ValueEvent> rb = ringBuffer;
        for (long i = 0; i < ITERATIONS; i++)
        {
            long next = rb.next();
            rb.get(next).setValue(i);
            rb.publish(next);
            //System.out.println(i);
        }
        latch.await();
        perfTestContext.setDisruptorOps((ITERATIONS * 1000L) / (System.currentTimeMillis() - start));
        perfTestContext.setBatchData(handler.getBatchesProcessed(),ITERATIONS);
        waitForEventProcessorSequence(expectedCount);
        batchEventProcessor.halt();
        PerfTestUtil.failIfNot(expectedResult, handler.getValue());

        return perfTestContext;
    }


    private void waitForEventProcessorSequence(long expectedCount) throws InterruptedException
    {
        while (batchEventProcessor.getSequence().get() != expectedCount)
        {
            Thread.sleep(1);
        }
    }

    public static void main(String[] args) throws Exception{
        OneProcuderToOneBatchEventProcessor test = new OneProcuderToOneBatchEventProcessor();
        test.testImplementations();
    }



}
