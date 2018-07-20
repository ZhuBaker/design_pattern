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
public class ConsumerQueue implements Runnable {

    BlockingQueue<String> queue;

    public ConsumerQueue(BlockingQueue<String> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            try {
                // 停顿任意时间
                Thread.sleep((int) Math.random() * 5000);
                // 消费者消费产品
                System.out.println(Thread.currentThread().getName() + ": 消费"
                        + queue.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
