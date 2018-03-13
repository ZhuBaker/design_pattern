package com.design.proxy.cglib_proxy.callback_filter;

import net.sf.cglib.proxy.CallbackFilter;

import java.lang.reflect.Method;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: zhubo
 * Date: 2018-03-12
 * Time: 16:00
 */
public class ConcreateClassCallbackFilter implements CallbackFilter{

    @Override
    public int accept(Method method) {

        if("getConcreteMethodB".equals(method.getName())){
            return 0;//Callback callbacks[0]
        }else if("getConcreteMethodA".equals(method.getName())){
            return 1;//Callback callbacks[1]
        }else if("getConcreteMethodFixedValue".equals(method.getName())){
            return 2;//Callback callbacks[2]
        }
        return 1;
    }
}
