package com.thread.线程假死产生原因.blockqueue;

import java.util.concurrent.BlockingQueue;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: zhubo
 * @description:
 * @time: 2018年05月04日
 * @modifytime:
 */
public class ProducerQueue implements Runnable {

    BlockingQueue<String> queue;

    public ProducerQueue(BlockingQueue<String> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            try {
                // 停顿任意时间
                Thread.sleep((int) Math.random() * 3000);
                // 放入产品到队列中
                System.out.println(Thread.currentThread().getName() + ": 产出了产品" + i);
                queue.put(Thread.currentThread().getName() + ": 产品" + i);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
