package com.simple.lambda.inter;

/**
 * Created by zhubo on 2018/4/14.
 * 接口里的静态方法
 * 也是函数式接口
 */
@FunctionalInterface
public interface TestStaticMethod {
    // 这是一个抽象方法
    void test();
    // 静态方法，不是抽象方法
    static void test1() {
        System.out.println("接口里的静态方法！");
    }
}
