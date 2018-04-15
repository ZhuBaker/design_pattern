package com.queue.disruptor.onetoone;

import com.lmax.disruptor.EventFactory;

/**
 * Created by zhubo on 2018/4/14.
 * Disruptor中消息对象可以自由定义，但是必须定义实现EventFactory接口的消息对象工厂来告诉RingBuffer如何初始化消息对象。
 */
public class ValueEvent {

    private long value;

    public long getValue() {
        return value;
    }

    public void setValue(long value) {
        this.value = value;
    }

    public static final EventFactory<ValueEvent> EVENT_FACTORY = new EventFactory<ValueEvent>() {
        @Override
        public ValueEvent newInstance() {
            return new ValueEvent();
        }
    };
}
