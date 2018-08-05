package com.simple.java8features;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: zhubo
 * @description: 静态方法，只能通过接口名调用，不可以通过实现类的类名或者实现类的对象调用。default方法，只能通过接口实现类的对象来调用。
 * @time: 2018年08月05日
 * @modifytime:
 */
public class Main {

    public static void main(String[] args) {
        // static方法必须通过接口类调用
        JDK8Interface.staticMethod();
        //default方法必须通过实现类的对象调用
        new JDK8InterfaceImpl().defaultMethod();
    }
}
