package com.queue.disruptor.demo1.exec;

import com.lmax.disruptor.EventFactory;
import com.lmax.disruptor.EventHandler;

/**
 * @Author: BakerZhu
 * @Date: 2018/4/16 22:20
 * @Description:
 */
public class ExeEvent {
    private long value;

    public long getValue() {
        return value;
    }

    public void setValue(long value) {
        this.value = value;
    }

    public static final EventFactory<ExeEvent> EVENT_FACTORY = new EventFactory<ExeEvent>() {
        @Override
        public ExeEvent newInstance() {
            return new ExeEvent();
        }
    };
}
