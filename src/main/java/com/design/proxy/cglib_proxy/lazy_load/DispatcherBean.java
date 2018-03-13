package com.design.proxy.cglib_proxy.lazy_load;

import net.sf.cglib.proxy.Enhancer;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: zhubo
 * Date: 2018-03-12
 * Time: 18:17
 */
public class DispatcherBean {

    private String name;

    private String value;

    private PropertyBean propertyBean;

    public DispatcherBean() {
        this.name = "DispatcherBean";
        this.value = "abc";
        this.propertyBean = this.createDispatcherBean();
    }

    protected PropertyBean createDispatcherBean(){
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(PropertyBean.class);
        enhancer.setCallback(new ConcreateClassDispatcher());
        PropertyBean propertyBean = (PropertyBean) enhancer.create();
        return propertyBean;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public PropertyBean getPropertyBean() {
        return propertyBean;
    }

    public void setPropertyBean(PropertyBean propertyBean) {
        this.propertyBean = propertyBean;
    }
}
