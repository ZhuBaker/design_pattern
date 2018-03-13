package com.design.proxy.cglib_proxy.lazy_load;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: zhubo
 * Date: 2018-03-12
 * Time: 18:35
 */
public class ExecDispatcherMain {

    public static void main(String[] args) {
        DispatcherBean dispatcherBean = new DispatcherBean();
        System.out.println(dispatcherBean.getName());
        System.out.println(dispatcherBean.getValue());

        PropertyBean propertyBean = dispatcherBean.getPropertyBean();
        System.out.println(propertyBean.getPropertyName());
        System.out.println(propertyBean.getPropertyValue());


    }
}
