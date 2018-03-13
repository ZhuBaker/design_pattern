package com.design.proxy.cglib_proxy.lazy_load;

import net.sf.cglib.proxy.LazyLoader;

/**
 * Created with IntelliJ IDEA.
 * Description:
 *
 * 在LoaderBean的构造方法中，对属性Bean进行了代理类生成，使用了CGLib中的LazyLoader回调接口。
 *
 * User: zhubo
 * Date: 2018-03-12
 * Time: 16:47
 */
public class ConcreateClassLazyLoader implements LazyLoader {

    @Override
    public Object loadObject() throws Exception {
        System.out.println("LazyLoader loadObject() ...");
        PropertyBean bean=new PropertyBean();
        bean.setPropertyName("lazy-load object propertyName!");
        bean.setPropertyValue(11);
        return bean;
    }
}
