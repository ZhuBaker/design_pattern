package com.design.proxy.cglib_proxy.lazy_load;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: zhubo
 * Date: 2018-03-12
 * Time: 16:50
 */
public class ExecLazyLoaderMain {

    public static void main(String[] args) throws Exception{
        LoaderBean loader = new LoaderBean();
        System.out.println(loader.getLoaderName());
        System.out.println(loader.getLoaderValue());


        PropertyBean propertyBean=loader.getPropertyBean();//访问延迟加载对象
        System.out.println("after get...");
        Thread.sleep(2000L);
        System.out.println(propertyBean.getPropertyName());
        System.out.println(propertyBean.getPropertyValue());
        System.out.println("after...");
        //当再次访问延迟加载对象时,就不会再执行回调了
        System.out.println(propertyBean.getPropertyName());




    }
}
