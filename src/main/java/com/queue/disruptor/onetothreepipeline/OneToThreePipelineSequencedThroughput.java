package com.queue.disruptor.onetothreepipeline;

import com.lmax.disruptor.BatchEventProcessor;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.SequenceBarrier;
import com.lmax.disruptor.YieldingWaitStrategy;
import com.queue.disruptor.AbstractPerfTestDisruptor;
import com.queue.disruptor.DaemonThreadFactory;
import com.queue.disruptor.PerfTestUtil;
import com.queue.disruptor.onetoone.PerfTestContext;
import com.queue.disruptor.support.FunctionEvent;
import com.queue.disruptor.support.FunctionEventHandler;
import com.queue.disruptor.support.FunctionStep;

import java.util.concurrent.CountDownLatch;
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
    private final SequenceBarrier stepOneSequenceBarrier = ringBuffer.newBarrier();
    private final FunctionEventHandler stepOneFunctionHandler = new FunctionEventHandler(FunctionStep.ONE);
    BatchEventProcessor<FunctionEvent> stepOneBatchProcessor = new BatchEventProcessor<>(ringBuffer,stepOneSequenceBarrier,stepOneFunctionHandler);

    private final SequenceBarrier stepTwoSequenceBarrier = ringBuffer.newBarrier(stepOneBatchProcessor.getSequence());
    private final FunctionEventHandler stepTwoFunctionHandler = new FunctionEventHandler(FunctionStep.TWO);
    private final BatchEventProcessor<FunctionEvent> stepTwoBatchProcessor =
            new BatchEventProcessor<FunctionEvent>(ringBuffer, stepTwoSequenceBarrier, stepTwoFunctionHandler);

    private final SequenceBarrier stepThreeSequenceBarrier = ringBuffer.newBarrier(stepTwoBatchProcessor.getSequence());
    private final FunctionEventHandler stepThreeFunctionHandler = new FunctionEventHandler(FunctionStep.THREE);
    private final BatchEventProcessor<FunctionEvent> stepThreeBatchProcessor =
            new BatchEventProcessor<FunctionEvent>(ringBuffer, stepThreeSequenceBarrier, stepThreeFunctionHandler);
    // 构造反向依赖，stepThreeBatchProcessor的Sequence最小
    {
        ringBuffer.addGatingSequences(stepThreeBatchProcessor.getSequence());
    }


    @Override
    protected int getRequiredProcessorCount() {
        return 4;
    }

    @Override
    protected PerfTestContext runDisruptorPass() throws Exception {
        PerfTestContext perfTestContext = new PerfTestContext();

        CountDownLatch latch = new CountDownLatch(1);
        stepThreeFunctionHandler.reset(latch, stepThreeBatchProcessor.getSequence().get() + ITERATIONS);
        executor.submit(stepOneBatchProcessor);
        executor.submit(stepTwoBatchProcessor);
        executor.submit(stepThreeBatchProcessor);

        long start = System.currentTimeMillis();

        long operandTwo = OPERAND_TWO_INITIAL_VALUE;
        for (long i = 0; i < ITERATIONS; i++)
        {
            long sequence = ringBuffer.next();
            FunctionEvent event = ringBuffer.get(sequence);
            event.setOperandOne(i);
            event.setOperandTwo(operandTwo--);
            ringBuffer.publish(sequence);
        }

        latch.await();
        perfTestContext.setDisruptorOps((ITERATIONS * 1000L) / (System.currentTimeMillis() - start));

        stepOneBatchProcessor.halt();
        stepTwoBatchProcessor.halt();
        stepThreeBatchProcessor.halt();

        PerfTestUtil.failIfNot(expectedResult, stepThreeFunctionHandler.getStepThreeCounter());

        return perfTestContext;
    }

    public static void main(String[] args) throws Exception {
        new OneToThreePipelineSequencedThroughput().testImplementations();
    }
}
