package com.design.proxy.cglib_proxy.springproxyfactory.advisor;


import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: zhubo
 * Date: 2018-03-13
 * Time: 11:33
 */
public class HijackAroundMethod implements MethodInterceptor {

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("Method name : "
                + method.getName());
        System.out.println("Method arguments : "
                + Arrays.toString(objects));

        // 相当于  MethodBeforeAdvice
        System.out.println("HijackAroundMethod : Before method hijacked!");
        try {
            // 调用原方法，即调用CustomerService中的方法
            Object result = methodProxy.invoke(o,objects);

            // 相当于 AfterReturningAdvice
            System.out.println("HijackAroundMethod : After method hijacked!");

            return result;

        } catch (IllegalArgumentException e) {
            // 相当于 ThrowsAdvice
            System.out.println("HijackAroundMethod : Throw exception hijacked!");
            throw e;
        }
    }
}
