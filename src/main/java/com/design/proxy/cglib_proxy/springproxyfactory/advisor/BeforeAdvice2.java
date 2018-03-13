package com.design.proxy.cglib_proxy.springproxyfactory.advisor;

import org.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: zhubo
 * Date: 2018-03-13
 * Time: 10:08
 */
public class BeforeAdvice2 implements MethodBeforeAdvice {

    @Override
    public void before(Method method, Object[] args, Object target) throws Throwable {
        System.out.println("BeforeAdvice2.before() execute ");
    }
}
