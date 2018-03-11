package com.design.proxy.static_proxy;

/**
 * Created by zhubo on 2018/3/11.
 */
public class IUserDaoImpl implements IUserDao {
    @Override
    public void save() {
        System.out.println("----已经保存数据!----");
    }
}
