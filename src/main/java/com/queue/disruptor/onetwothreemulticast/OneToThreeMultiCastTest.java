package com.queue.disruptor.onetwothreemulticast;

import com.lmax.disruptor.*;
import com.queue.disruptor.DaemonThreadFactory;
import com.queue.disruptor.support.FunctionEvent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: zhubo
 * Date: 2018-04-18
 * Time: 13:22
 */
public class OneToThreeMultiCastTest {

    private static final int NUM_EVENT_PROCESSORS = 3;
    private static final int BUFFER_SIZE = 1024 * 8;
    private static final long ITERATIONS = 1000L * 1000L * 100L;
    private final ExecutorService executor = Executors.newFixedThreadPool(NUM_EVENT_PROCESSORS , DaemonThreadFactory.INSTANCE);

    // 构造RingBuffer
    private final RingBuffer<FunctionEvent> ringBuffer =
            RingBuffer.createSingleProducer(FunctionEvent.EVENT_FACTORY, BUFFER_SIZE, new YieldingWaitStrategy());
    // 构造BatchEventProcessor 及依赖关系
    private final SequenceBarrier sequenceBarrier = ringBuffer.newBarrier();



    private final EventHandler<FunctionEvent> handler1 = new EventHandler<FunctionEvent>() {
        int num = 1;
        @Override
        public void onEvent(FunctionEvent event, long sequence, boolean endOfBatch) throws Exception {
            System.out.println(num + " : " + event.getOperandOne());
        }
        public void setNum(int num){
            this.num = num;
        }
    };
    private final EventHandler<FunctionEvent> handler2 = new EventHandler<FunctionEvent>() {
        int num = 2;
        @Override
        public void onEvent(FunctionEvent event, long sequence, boolean endOfBatch) throws Exception {
            System.out.println(num + " : " + event.getOperandOne());
        }
        public void setNum(int num){
            this.num = num;
        }
    };
    private final EventHandler<FunctionEvent> handler3 = new EventHandler<FunctionEvent>() {
        int num = 3;
        @Override
        public void onEvent(FunctionEvent event, long sequence, boolean endOfBatch) throws Exception {
            System.out.println(num + " : " + event.getOperandOne());
        }
        public void setNum(int num){
            this.num = num;
        }
    };
    private final BatchEventProcessor<FunctionEvent>[] batchEventProcessors = new BatchEventProcessor[3];

    {
        batchEventProcessors[0] = new BatchEventProcessor<>(ringBuffer,sequenceBarrier,handler1);
        batchEventProcessors[1] = new BatchEventProcessor<>(ringBuffer,sequenceBarrier,handler2);
        batchEventProcessors[2] = new BatchEventProcessor<>(ringBuffer,sequenceBarrier,handler3);

        ringBuffer.addGatingSequences(batchEventProcessors[0].getSequence(),batchEventProcessors[1].getSequence(),batchEventProcessors[2].getSequence());
    }


    public void exec(){
        executor.submit(batchEventProcessors[0]);
        executor.submit(batchEventProcessors[1]);
        executor.submit(batchEventProcessors[2]);
        for(int i = 0 ; i < 100; i++){
            long next = ringBuffer.next();
            ringBuffer.get(next).setOperandOne(i);
            ringBuffer.publish(next);
        }
        try{
            Thread.sleep(1000);
        }catch (Exception e){

        }


    }


    public static void main(String[] args) {
        new OneToThreeMultiCastTest().exec();
    }











}
