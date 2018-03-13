package com.design.proxy.cglib_proxy.springproxyfactory.advisor;

import org.aopalliance.aop.Advice;
import org.springframework.aop.Advisor;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.framework.ProxyFactoryBean;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.NameMatchMethodPointcut;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: zhubo
 * Date: 2018-03-13
 * Time: 11:36
 */
public class App {

    public static void main(String[] args) {
        CustomerService customerService = new CustomerService();
        customerService.setName("LeiOOLei");
        customerService.setUrl("http://www.cnblogs.com/leiOOlei/");

        ProxyFactory proxy = new ProxyFactory(customerService);
        NameMatchMethodPointcut pointcut = new NameMatchMethodPointcut();
        pointcut.setMappedName("printName");

        Advice advice1 = new BeforeAdvice1();
        Advice advice2 = new BeforeAdvice2();
        Advice advice3 = new AfterAdvice1();
        Advice advice4 = new AfterAdvice2();

        Advisor advisor1 = new DefaultPointcutAdvisor(pointcut,advice1);
        Advisor advisor2 = new DefaultPointcutAdvisor(pointcut,advice2);
        Advisor advisor3 = new DefaultPointcutAdvisor(pointcut,advice3);
        Advisor advisor4 = new DefaultPointcutAdvisor(pointcut,advice4);

        proxy.addAdvisors(new Advisor[]{advisor1});

        CustomerService service = (CustomerService)proxy.getProxy();
        service.printName();


    }
}
