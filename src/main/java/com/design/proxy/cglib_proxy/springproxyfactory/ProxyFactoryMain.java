package com.design.proxy.cglib_proxy.springproxyfactory;

import org.springframework.aop.framework.ProxyFactory;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: zhubo
 * Date: 2018-03-13
 * Time: 9:55
 */
public class ProxyFactoryMain {

    public static void main(String[] args) {
        ProxyFactory factory = new ProxyFactory();
        //注意前置处理器和后置处理器的处理顺序
        factory.addAdvice(new BeforeAdvice1());
        factory.addAdvice(new BeforeAdvice2());
        factory.addAdvice(new AfterAdvice1());
        factory.addAdvice(new AfterAdvice2());



        LoginService target = new LoginServiceImpl();
        factory.setTarget(target);
        factory.setProxyTargetClass(false);
        factory.setInterfaces(new Class[]{LoginService.class});
        LoginService service = (LoginService)factory.getProxy();

        System.out.println(service.login(new User("xinchun.wang","123456")));


    }

}
