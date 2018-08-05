package com.simple.java8features;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: zhubo
 * @description:
 * @time: 2018年08月05日
 * @modifytime:
 */
@FunctionalInterface
public interface AnnoInterface {
    // 抽象方法
    public void method();
    // java.lang.Object中的方法不是抽象方法
    public boolean equals(Object obj);
    // default不是抽象方法
    public default void defaultMethod() {

    }
    // static 不是抽象方法
    public static void staticMethod() {

    }
}
