package com.thread.线程假死产生原因;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: zhubo
 * @description:
 * @time: 2018年05月04日
 * @modifytime:
 */
public class Consumer implements Runnable {

    private Clerk clerk;

    public Consumer(Clerk clerk) {
        this.clerk = clerk;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            try {
                //暂停若干时间
                Thread.sleep((int) Math.random() * 5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //消费者取出商品
            clerk.buyProduct();
        }
    }
}
