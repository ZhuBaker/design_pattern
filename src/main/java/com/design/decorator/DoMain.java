package com.design.decorator;

/**
 * Created by zhubo on 2018/4/9.
 */
public class DoMain {
    public static void main(String[] args) {
        SimpleCoffee simpleCoffee = new SimpleCoffee();
        MilkDecorator milkDecorator = new MilkDecorator(simpleCoffee);
        System.out.println(milkDecorator.getPrice());
    }
}