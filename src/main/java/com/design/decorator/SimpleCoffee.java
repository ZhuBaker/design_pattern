package com.design.decorator;

/**
 * Created by zhubo on 2018/4/9.
 */
public class SimpleCoffee extends Coffee {

    @Override
    public int getPrice() {
        return 60;
    }

    @Override
    public String getName() {
        return "coffee";
    }
}
