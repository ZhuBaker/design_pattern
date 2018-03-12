package com.design.proxy.cglib_proxy;

import net.sf.cglib.proxy.Enhancer;

/**
 * Created by zhubo on 2018/3/11.
 */
public class CglibProxyMain {

    public static void main(String[] args) {

        UserDao dao = new UserDao();
        ProxyFactory proxy = new ProxyFactory();

        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(dao.getClass());
        enhancer.setCallback(proxy);
        Object o = enhancer.create();
        System.out.println(o.getClass());
        UserDao proxyObj = (UserDao)o;
        proxyObj.save();

    }
}
