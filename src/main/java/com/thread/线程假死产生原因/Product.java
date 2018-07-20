package com.thread.线程假死产生原因;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: zhubo
 * @description: 产品类
 * @time: 2018年05月04日
 * @modifytime:
 */
public class Product {

    private String name;

    public Product(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
