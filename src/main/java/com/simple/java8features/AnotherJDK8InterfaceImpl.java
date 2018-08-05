package com.simple.java8features;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: zhubo
 * @description: 当然如果接口中的默认方法不能满足某个实现类需要，那么实现类可以覆盖默认方法。
 * @time: 2018年08月05日
 * @modifytime:
 */
public class AnotherJDK8InterfaceImpl implements JDK8Interface {
    @Override
    public void defaultMethod() {
        System.out.println("接口实现类覆盖了接口中的default");
    }
}
