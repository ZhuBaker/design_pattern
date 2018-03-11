package com.design.proxy.static_proxy;

/**
 * Created by zhubo on 2018/3/11.
 */
public class UserDaoProxy implements IUserDao{
    private IUserDao userDao;

    public UserDaoProxy(IUserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public void save() {
        userDao.save();
    }
}
