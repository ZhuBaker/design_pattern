package com.simple.java8features;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: zhubo
 * @description: 在jdk8之前，interface之中可以定义变量和方法，变量必须是public、static、final的，方法必须是public、abstract的。由于这些修饰符都是默认的，所以在JDK8之前，下面的写法都是等价的。
 * @time: 2018年08月05日
 * @modifytime:
 */
public interface   JDK8BeforeInterface {

    public static final int field1 = 0;

    int field2 = 0;

    public abstract void m1(int a) throws Exception ;

    void m2(int a) throws Exception;

}
