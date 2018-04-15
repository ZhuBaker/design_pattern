package com.queue.disruptor;

import java.util.concurrent.ThreadFactory;

/**
 * @Author: BakerZhu
 * @Date: 2018/4/15 9:10
 * @Description:   Access to a ThreadFactory instance. All threads are created with setDaemon(true).
 */
public enum  DaemonThreadFactory implements ThreadFactory {

    INSTANCE;

    DaemonThreadFactory() {
    }

    @Override
    public Thread newThread(Runnable r) {
        Thread t = new Thread(r);
        t.setDaemon(true);
        return t;
    }
}
