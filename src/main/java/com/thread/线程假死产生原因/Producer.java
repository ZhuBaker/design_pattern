package com.thread.线程假死产生原因;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: zhubo
 * @description: 生产者
 * @time: 2018年05月04日
 * @modifytime:
 */
public class Producer implements Runnable{
    Clerk clerk = null;
    public Producer(Clerk clerk) {
        this.clerk = clerk;
    }
    @Override
    public void run() {
        for (int i = 1; i <= 10; i++) {
            try {
                // 暂停随机时间
                Thread.sleep((int) Math.random() * 3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Product p = new Product(Thread.currentThread().getName()+"产品" + i);
            //产品放入到货架中
            clerk.receiveProduct(p);
        }

    }
}
