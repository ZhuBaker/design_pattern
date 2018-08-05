package com.simple.java8features;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: zhubo
 * @description:由于java支持一个实现类可以实现多个接口，如果多个接口中存在同样的static和default方法会怎么样呢？
 * 如果有两个接口中的静态方法一模一样，并且一个实现类同时实现了这两个接口，此时并不会产生错误，因为jdk8只能通过接口类调用接口中的静态方法，
 * 所以对编译器来说是可以区分的。但是如果两个接口中定义了一模一样的默认方法，并且一个实现类同时实现了这两个接口，那么必须在实现类中重写默认方法，
 * 否则编译失败。
 * @time: 2018年08月05日
 * @modifytime:
 */
public class JDK8InterfaceImpl implements JDK8Interface ,JDK8Interface1 {
    //实现接口后，因为默认方法不是抽象方法，所以可以不重写，但是如果开发需要，也可以重写
    @Override
    public void defaultMethod() {
        System.out.println("接口实现类覆盖了接口中的default");
    }
}
