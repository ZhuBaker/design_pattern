package com.design.decorator;

/**
 * Created by zhubo on 2018/4/9.
 */
public class SugarDecorator extends Decorator {

    public SugarDecorator(Coffee coffee) {
        super(coffee);
    }

    @Override
    public int getPrice() {
        return mCoffee.getPrice() + 20;
    }

    @Override
    public String getName() {
        return "addSugar";
    }
}
