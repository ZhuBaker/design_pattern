package com.design.proxy.cglib_proxy.springproxyfactory;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: zhubo
 * Date: 2018-03-13
 * Time: 9:59
 */
public class LoginServiceImpl implements LoginService {
    @Override
    public boolean login(User user) {
        System.out.println(user);
        if(user == null){
            return false;
        }else if (user.getUsername() == "xinchun.wang" && user.getPassword() == "123456") {
            return true;
        }
        return false;
    }
}
