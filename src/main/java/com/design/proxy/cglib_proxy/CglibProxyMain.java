package com.design.proxy.cglib_proxy;

import net.sf.cglib.proxy.Enhancer;

/**
 * Created by zhubo on 2018/3/11.
 */
public class CglibProxyMain {

    public static void main(String[] args) {

        UserDao dao = new UserDao();
        ProxyFactory proxy = new ProxyFactory();
        //利用Enhancer类生成代理类
        Enhancer enhancer = new Enhancer();
        //设置代理类的父类
        enhancer.setSuperclass(dao.getClass());
        //设置回调
        enhancer.setCallback(proxy);
        //穿件代理类
        Object o = enhancer.create();
        System.out.println(o.getClass());
        UserDao proxyObj = (UserDao)o;
        proxyObj.save();

    }
}
