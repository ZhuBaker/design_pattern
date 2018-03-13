package com.design.proxy.cglib_proxy;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * Created by zhubo on 2018/3/11.
 */
public class ProxyFactory implements MethodInterceptor {

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("开始事务...");
        //执行目标对象的方法
        //Object returnValue = method.invoke(target, objects);
        System.out.println("method: "+method);
        Object returnValue = methodProxy.invokeSuper(o, objects);

        System.out.println("提交事务...");

        return returnValue;
    }
}
