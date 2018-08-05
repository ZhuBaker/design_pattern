package com.simple.java8features;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: zhubo
 * @description: JDK8及以后，允许我们在接口中定义static方法和default方法。
 * @time: 2018年08月05日
 * @modifytime:
 */
public interface  JDK8Interface {

    // static修饰符定义静态方法
    static void staticMethod() {
        System.out.println("接口中定义静态方法");
    }
    // default修饰符定义默认方法
    default void defaultMethod() {
        System.out.println("接口中的默认方法");
    }
}
