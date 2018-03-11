package com.design.proxy.java_proxy;

import com.design.proxy.static_proxy.IUserDao;
import com.design.proxy.static_proxy.IUserDaoImpl;

import java.lang.reflect.Proxy;

/**
 * Created by zhubo on 2018/3/11.
 */
public class JavaProxyMain {
    public static void main(String[] args) {
        IUserDao dao = new IUserDaoImpl();
        ProxyFactory factory = new ProxyFactory(dao);
        IUserDao proxyInstance = (IUserDao) factory.getProxyInstance();
        System.out.println(proxyInstance.getClass());
        proxyInstance.save();
    }
}
