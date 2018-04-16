package com.design.decorator;

/**
 * Created by zhubo on 2018/4/9.
 *
 *  接着 我们定义一个Decorator类继承 我们的Coffice基类
 */
public abstract class Decorator extends Coffee {

    protected Coffee mCoffee;

    /**
     * 通过组合方式把Coffee对象传递进来
     * @param coffee
     */
    public Decorator(Coffee coffee) {
        this.mCoffee = coffee;
    }

}
