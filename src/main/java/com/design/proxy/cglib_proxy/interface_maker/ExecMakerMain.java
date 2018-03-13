package com.design.proxy.cglib_proxy.interface_maker;

import com.design.proxy.cglib_proxy.callback_filter.ConcreateClassNoInterface;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.InterfaceMaker;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: zhubo
 * Date: 2018-03-12
 * Time: 19:17
 */
public class ExecMakerMain {

    public static void main(String[] args) throws Exception{

        InterfaceMaker im = new InterfaceMaker();
        im.add(ConcreteClassNoInterface.class);
        Class interfaceObj = im.create();
        System.out.println(interfaceObj.isInterface()); // true
        System.out.println(interfaceObj.getName()); // net.sf.cglib.empty.Object$$InterfaceMakerByCGLIB$$23fc625e

        Method[] methods = interfaceObj.getMethods();
        for (Method method : methods){
            System.out.println(method.getName());
        }

        Object obj = Enhancer.create(Object.class, new Class[]{interfaceObj}, new MethodInterceptor() {
            @Override
            public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                return "intercept!";
            }
        });

        Method method = obj.getClass().getMethod("getConcreteMethodA",new Class[]{String.class});

        System.out.println(method.invoke(obj,new Object[]{"12345"}));

    }
}
