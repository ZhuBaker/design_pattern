package com.queue.disruptor.onetothreepipeline.exec;

import com.lmax.disruptor.*;
import com.queue.disruptor.support.FunctionEvent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: zhubo
 * Date: 2018-04-18
 * Time: 9:38
 */
public class ExecMain {

    ExecutorService executor =  Executors.newFixedThreadPool(3);

    private final Integer BUFFER_SIZE = 1024 * 8;

    private final RingBuffer<FunctionEvent> ringBuffer =
            RingBuffer.createSingleProducer(FunctionEvent.EVENT_FACTORY, BUFFER_SIZE, new YieldingWaitStrategy());
    // 构造BatchEventProcessor 及依赖关系
    // stepOneBatchProcessor依赖RingBuffer
    private final SequenceBarrier stepOneSequenceBarrier = ringBuffer.newBarrier();
    private final EventHandler stepOneHandler = new EventHandler<FunctionEvent>(){
        @Override
        public void onEvent(FunctionEvent event, long sequence, boolean endOfBatch) throws Exception {
            System.out.println("one : "+event.getOperandOne());
        }
    };
    private final BatchEventProcessor<FunctionEvent> stepOneBatchProcessor =
            new BatchEventProcessor<FunctionEvent>(ringBuffer, stepOneSequenceBarrier, stepOneHandler);

    // stepTwoBatchProcessor依赖RingBuffer和stepOneBatchProcessor
    private final SequenceBarrier stepTwoSequenceBarrier = ringBuffer.newBarrier(stepOneBatchProcessor.getSequence());
    private final EventHandler stepTwoHandler = new EventHandler<FunctionEvent>(){
        @Override
        public void onEvent(FunctionEvent event, long sequence, boolean endOfBatch) throws Exception {
            System.out.println("two : "+event.getOperandOne());
        }
    };
    private final BatchEventProcessor<FunctionEvent> stepTwoBatchProcessor = new BatchEventProcessor<>(ringBuffer,stepTwoSequenceBarrier,stepTwoHandler);

    // stepThreeBatchProcessor依赖RingBuffer和stepTwoBatchProcessor
    private final SequenceBarrier stepThreeSequenceBarrier = ringBuffer.newBarrier(stepTwoBatchProcessor.getSequence());
    private final EventHandler stepThreeHandler = new EventHandler<FunctionEvent>(){
        @Override
        public void onEvent(FunctionEvent event, long sequence, boolean endOfBatch) throws Exception {
            System.out.println("three : "+event.getOperandOne());
        }
    };
    private final BatchEventProcessor<FunctionEvent> stepThreeBatchProcessor =
            new BatchEventProcessor<FunctionEvent>(ringBuffer, stepThreeSequenceBarrier, stepThreeHandler);

    {
        // 构造反向依赖，stepThreeBatchProcessor的Sequence最小
        ringBuffer.addGatingSequences(stepThreeBatchProcessor.getSequence());
    }

    public static void main(String[] args) throws Exception {
        ExecMain main = new ExecMain();
        main.exec();
    }

    public void exec() throws Exception{

        executor.submit(stepOneBatchProcessor);
        executor.submit(stepTwoBatchProcessor);
        executor.submit(stepThreeBatchProcessor);

        for(int i = 0 ; i < 100; i++){
            long next = ringBuffer.next();
            ringBuffer.get(next).setOperandOne(i);
            ringBuffer.publish(next);
        }

        /*stepOneBatchProcessor.halt();
        stepTwoBatchProcessor.halt();
        stepThreeBatchProcessor.halt();*/

        /*Thread.sleep(100L);
        stepOneBatchProcessor.run();
        stepTwoBatchProcessor.run();*/




    }
}
