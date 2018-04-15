package com.queue.disruptor;

import com.queue.disruptor.onetoone.PerfTestContext;

/**
 * @Author: BakerZhu
 * @Date: 2018/4/15 9:03
 * @Description: 性能测试的基类
 */
public abstract class AbstractPerfTestDisruptor {

    private static final int RUNS = 7;

    protected void testImplementations()
            throws Exception
    {
        final int availableProcessors = Runtime.getRuntime().availableProcessors();
        if (getRequiredProcessorCount() > availableProcessors)
        {
            System.out.print("*** Warning ***: your system has insufficient processors to execute the test efficiently. ");
            System.out.println("Processors required = " + getRequiredProcessorCount() + " available = " + availableProcessors);
        }

        PerfTestContext[] contexts = new PerfTestContext[RUNS];

        System.out.println("Starting Disruptor tests");
        for (int i = 0; i < RUNS; i++)
        {
            System.gc();
            PerfTestContext context = runDisruptorPass();
            contexts[i] = context;
            System.out.format("Run %d, Disruptor=%,d ops/sec BatchPercent=%.2f%% AverageBatchSize=%,d\n",
                    i, context.getDisruptorOps(), context.getBatchPercent() * 100, (long)context.getAverageBatchSize());
        }
    }

    protected abstract int getRequiredProcessorCount();

    protected abstract PerfTestContext runDisruptorPass() throws Exception;
}
