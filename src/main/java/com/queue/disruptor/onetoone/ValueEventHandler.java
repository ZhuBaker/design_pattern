package com.queue.disruptor.onetoone;

import com.lmax.disruptor.BatchStartAware;
import com.lmax.disruptor.EventHandler;
import com.queue.disruptor.support.PaddedLong;

import java.util.concurrent.CountDownLatch;

/**
 * @Author: BakerZhu
 * @Date: 2018/4/14 19:14
 * @Description: 事件处理器
 */
public class ValueEventHandler implements EventHandler<ValueEvent> , BatchStartAware {

    private final PaddedLong value = new PaddedLong();
    private final PaddedLong batchesProcessed = new PaddedLong();
    private long count;
    private CountDownLatch latch;

    public long getValue()
    {
        return value.get();
    }

    public long getBatchesProcessed()
    {
        return batchesProcessed.get();
    }

    /**
     *
     * @param latch
     * @param expectedCount  设置预完毕值
     */
    public void reset(final CountDownLatch latch, final long expectedCount)
    {
        value.set(0L);
        this.latch = latch;
        count = expectedCount;
        batchesProcessed.set(0);
    }



    @Override
    public void onEvent(ValueEvent event, long sequence, boolean endOfBatch) throws Exception {

        value.set(value.get() + event.getValue());

        if (count == sequence)
        {
            latch.countDown();
        }
    }

    @Override
    public void onBatchStart(long batchSize) {
        batchesProcessed.increment();
    }
}
