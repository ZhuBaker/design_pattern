package com.design.proxy.cglib_proxy.callback_filter;

import net.sf.cglib.proxy.Callback;
import net.sf.cglib.proxy.CallbackFilter;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.NoOp;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: zhubo
 * Date: 2018-03-12
 * Time: 16:02
 */
public class ExecMain {

    public static void main(String[] args) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(ConcreateClassNoInterface.class);
        CallbackFilter filter = new ConcreateClassCallbackFilter();
        enhancer.setCallbackFilter(filter);

        Callback interceptor = new ConcreateClassInterceptor();

        Callback noOp = NoOp.INSTANCE;

        Callback fixedValue = new ConcreateClassFixedValue();

        Callback[] callbacks = new Callback[]{interceptor,noOp,fixedValue};

        enhancer.setCallbacks(callbacks);

        ConcreateClassNoInterface proxyObject = (ConcreateClassNoInterface) enhancer.create();

        System.out.println("*** NoOp Callback ***");
        String abcde = proxyObject.getConcreteMethodA("abcde");
        System.out.println();

        System.out.println("*** MethodInterceptor Callback ***");
        proxyObject.getConcreteMethodB(1);
        System.out.println();



        System.out.println("*** FixedValue Callback ***");
        int fixed1=proxyObject.getConcreteMethodFixedValue(128);
        System.out.println("fixedValue1:"+fixed1);
        int fixed2=proxyObject.getConcreteMethodFixedValue(256);
        System.out.println("fixedValue2:"+fixed2);

    }
}
