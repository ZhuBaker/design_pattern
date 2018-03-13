package com.design.proxy.cglib_proxy.lazy_load;

import net.sf.cglib.proxy.Dispatcher;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: zhubo
 * Date: 2018-03-12
 * Time: 18:21
 */
public class ConcreateClassDispatcher implements Dispatcher {

    @Override
    public Object loadObject() throws Exception {
        System.out.println("Dispatcher loadObject ...");
        PropertyBean object = new PropertyBean();
        object.setPropertyName("PropertyBeanName!");
        object.setPropertyValue(1);
        return object;
    }
}
