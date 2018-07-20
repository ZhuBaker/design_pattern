package com.thread.线程假死产生原因.blockqueue;

import java.util.concurrent.*;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: zhubo
 * @description:
 * @time: 2018年05月04日
 * @modifytime:
 */
public class ProductConsumerOptimize {

    public static void main(String[] args) {
        BlockingQueue<String> store = new ArrayBlockingQueue<String>(5);
        ProducerQueue producer = new ProducerQueue(store);
        ConsumerQueue consumer = new ConsumerQueue(store);
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        for (int i = 1; i <= 10; i++) {
            executorService.submit(producer);
        }
        for (int i = 1; i <= 10; i++) {
            executorService.submit(consumer);
        }
    }
}
