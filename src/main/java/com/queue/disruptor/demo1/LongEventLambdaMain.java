package com.queue.disruptor.demo1;

import com.lmax.disruptor.RingBuffer;
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
        ExecutorService executor = Executors.newCachedThreadPool();
        LongEventFactory factory = new LongEventFactory();
        LongEventHandler handler = new LongEventHandler();

        Disruptor<LongEvent> disruptor = new Disruptor<LongEvent>(factory , 1024, executor , ProducerType.SINGLE, new YieldingWaitStrategy());
        disruptor.handleEventsWith(handler);
        disruptor.start();
        RingBuffer<LongEvent> ringBuffer = disruptor.getRingBuffer();

        //ringBuffer.publishEvent();
        ByteBuffer bb = ByteBuffer.allocate(8);
        ringBuffer.publishEvent(((event, sequence, buffer) -> event.setValue(buffer.getLong())),bb);

        //ringBuffer.publishEvent(((event, sequence) -> event.setValue(bb.getLong())));


    }
}
