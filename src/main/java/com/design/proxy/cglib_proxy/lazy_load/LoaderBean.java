package com.design.proxy.cglib_proxy.lazy_load;

import net.sf.cglib.proxy.Enhancer;

/**
 * Created with IntelliJ IDEA.
 * Description:
 *
 * 首先定义一个实体类LoaderBean，该Bean内有一个需要延迟加载的属性PropertyBean。
 *
 *
 *
 * User: zhubo
 * Date: 2018-03-12
 * Time: 16:44
 */
public class LoaderBean {

    private String loaderName;
    private int loaderValue;
    private PropertyBean propertyBean;

    public LoaderBean() {
        this.loaderName = "loaderNameA";
        this.loaderValue = 123;
        this.propertyBean = createPropertyBean();
    }

    protected PropertyBean createPropertyBean(){
        Enhancer enhancer = new Enhancer();
        //设置父类
        enhancer.setSuperclass(PropertyBean.class);
        //设置回调
        enhancer.setCallback(new ConcreateClassLazyLoader());
        PropertyBean propertyBean = (PropertyBean) enhancer.create();
        return propertyBean;
        /*PropertyBean propertyBean = (PropertyBean) Enhancer.create(PropertyBean.class, new ConcreateClassLazyLoader());
        return propertyBean;*/
    }

    public String getLoaderName() {
        return loaderName;
    }

    public void setLoaderName(String loaderName) {
        this.loaderName = loaderName;
    }

    public int getLoaderValue() {
        return loaderValue;
    }

    public void setLoaderValue(int loaderValue) {
        this.loaderValue = loaderValue;
    }

    public PropertyBean getPropertyBean() {
        return propertyBean;
    }

    public void setPropertyBean(PropertyBean propertyBean) {
        this.propertyBean = propertyBean;
    }


}
