package com.design.proxy.cglib_proxy.callback_filter;

import net.sf.cglib.proxy.FixedValue;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: zhubo
 * Date: 2018-03-12
 * Time: 16:07
 */
public class ConcreateClassFixedValue implements FixedValue {

    @Override
    public Object loadObject() throws Exception {
        System.out.println("ConcreteClassFixedValue loadObject ...");
        Object object=999;
        return object;
    }
}
