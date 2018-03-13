package com.design.proxy.cglib_proxy.callback_filter;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: zhubo
 * Date: 2018-03-12
 * Time: 16:14
 */
public class ConcreateClassInterceptor implements MethodInterceptor{

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {

        System.out.println("Before: " + method);
        Object object = methodProxy.invokeSuper(o,objects);
        System.out.println("After: "+method);
        return object;
    }
}
