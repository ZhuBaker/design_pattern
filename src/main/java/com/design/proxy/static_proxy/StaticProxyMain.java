package com.design.proxy.static_proxy;

/**
 * Created by zhubo on 2018/3/11.
 */
public class StaticProxyMain {

    public static void main(String[] args) {
        IUserDao dao = new IUserDaoImpl();
        new UserDaoProxy(dao).save();
    }
}
