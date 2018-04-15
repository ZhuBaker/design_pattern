package com.queue.disruptor.onetothreepipeline;

import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.YieldingWaitStrategy;
import com.queue.disruptor.AbstractPerfTestDisruptor;
import com.queue.disruptor.DaemonThreadFactory;
import com.queue.disruptor.onetoone.PerfTestContext;
import com.queue.disruptor.support.FunctionEvent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static com.lmax.disruptor.RingBuffer.createSingleProducer;

/**
 * @Author: BakerZhu
 * @Date: 2018/4/15 21:26
 * @Description:
 */
public class OneToThreePipelineSequencedThroughput extends AbstractPerfTestDisruptor {

    private static final int NUM_EVENT_PROCESSORS = 3;
    private static final int BUFFER_SIZE = 1024 * 8;
    private static final long ITERATIONS = 1000L * 1000L * 100L;
    private final ExecutorService executor = Executors.newFixedThreadPool(NUM_EVENT_PROCESSORS , DaemonThreadFactory.INSTANCE);

    private static final long OPERAND_TWO_INITIAL_VALUE = 777;
    private final long expectedResult ;

    {
        long temp = 0L;
        long operandTwo = OPERAND_TWO_INITIAL_VALUE;

        for (long i = 0 ; i < ITERATIONS ; i++ ) {
            long stepOneResult = i + operandTwo -- ;
            long stepTwoResult = stepOneResult + 3;
            if ((stepTwoResult & 4L) == 4L) {
                ++temp;
            }
        }
        expectedResult = temp;
    }

    private final RingBuffer<FunctionEvent> ringBuffer =
            createSingleProducer(FunctionEvent.EVENT_FACTORY, BUFFER_SIZE, new YieldingWaitStrategy());


    @Override
    protected int getRequiredProcessorCount() {
        return 0;
    }

    @Override
    protected PerfTestContext runDisruptorPass() throws Exception {
        return null;
    }
}
