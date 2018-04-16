package com.queue.disruptor.onetoone;

import com.lmax.disruptor.BusySpinWaitStrategy;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.YieldingWaitStrategy;

/**
 * Created by zhubo on 2018/4/14.
 * Disruptor中同样没有定义生产者，而是由RingBuffer提供添加消息的接口。针对单生产者和多生产者两种应用场景，RingBuffer提供了不同的初始化方法：
 */
public class ProducerTest {

    //单生产者应用场景
    private static  final RingBuffer<ValueEvent> singleRingBuffer =
            RingBuffer.createSingleProducer(ValueEvent.EVENT_FACTORY,1024*1024, new YieldingWaitStrategy());

    //多生产者应用场景
    private static  final RingBuffer<ValueEvent> multiRingBuffer =
            RingBuffer.createMultiProducer(ValueEvent.EVENT_FACTORY,1024*1024, new BusySpinWaitStrategy());

    /**
     * 初始化的时候需要提供消息工厂，RingBuffer大小，以及一个选定的waitStrategy。向RingBuffer中添加消息的过程分成两阶段：
     * 1，申请可用节点，并将消息放入节点中；2，提交节点。
     * @param args
     */
    public static void main(String[] args) {
        long next = singleRingBuffer.next();
        singleRingBuffer.get(next).setValue(0);
        singleRingBuffer.publish(next);


    }

}
