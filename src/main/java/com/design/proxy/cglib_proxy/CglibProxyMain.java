package com.design.proxy.cglib_proxy;

/**
 * Created by zhubo on 2018/3/11.
 */
public class CglibProxyMain {

    public static void main(String[] args) {
        UserDao dao = new UserDao();
        ProxyFactory proxy = new ProxyFactory(dao);
        UserDao proxyInstance = (UserDao) proxy.getProxyInstance();
        System.out.println(proxyInstance.getClass());
        proxyInstance.save();
    }
}
