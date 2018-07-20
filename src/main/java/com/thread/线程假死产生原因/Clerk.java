package com.thread.线程假死产生原因;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: zhubo
 * @description: 销售员
 * @time: 2018年05月04日
 * @modifytime:
 */
public class Clerk {

    protected List<Product> store = new ArrayList<>();

    private Object con = new Object();
    private Object pro = new Object();

    private static int MAX = 5;

    public synchronized void receiveProduct(Product product){
        while(store.size()>=MAX){
            try {
                // 目前没有空间收产品，请稍候！
                pro.wait(); // 必须在同步块或方法中才能使用 wait
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        store.add(product);
        System.out.printf("-->库存状态(%d) 新产品(%s)%n", store.size(), product.getName());
        //通知等待区中的一个线程可以工作了
        con.notify();
    }

    public synchronized Product buyProduct(){
        while(store.size()==0){
            try {
                //目前货架上没有商品，需要等待
                con.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Product p = store.remove(0);
        System.out.printf("<--库存状态(%d) 取走产品(%s)%n", store.size(), p.getName());
        // 通知等待区中的一个生产者可以继续工作了
        pro.notify(); // notify 和 interrupt 会使等待区域的线程回到锁定池的 Blocked 状态
        return p;
    }
}
