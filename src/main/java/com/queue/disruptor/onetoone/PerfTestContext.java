package com.queue.disruptor.onetoone;

/**
 * @Author: BakerZhu
 * @Date: 2018/4/15 9:01
 * @Description: 性能测试的上下文
 */
public class PerfTestContext {

    // 操作个数
    private long disruptorOps;

    private long batchesProcessedCount;

    private long iterations;

    public PerfTestContext() {
    }

    public long getDisruptorOps()
    {
        return disruptorOps;
    }

    public void setDisruptorOps(long disruptorOps)
    {
        this.disruptorOps = disruptorOps;
    }

    public long getBatchesProcessedCount()
    {
        return batchesProcessedCount;
    }

    public double getBatchPercent()
    {
        if (batchesProcessedCount == 0) return 0;
        return 1 - (double)batchesProcessedCount / iterations;
    }

    public double getAverageBatchSize()
    {
        if (batchesProcessedCount == 0) return -1;
        return (double)iterations / batchesProcessedCount;
    }

    public void setBatchData(long batchesProcessedCount, long iterations)
    {
        this.batchesProcessedCount = batchesProcessedCount;
        this.iterations = iterations;
    }
}
