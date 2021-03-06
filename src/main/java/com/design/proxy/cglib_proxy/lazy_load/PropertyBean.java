package com.design.proxy.cglib_proxy.lazy_load;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: zhubo
 * Date: 2018-03-12
 * Time: 16:45
 */
public class PropertyBean {

    private String propertyName;

    private int propertyValue;

    public String getPropertyName() {
        return propertyName;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

    public int getPropertyValue() {
        return propertyValue;
    }

    public void setPropertyValue(int propertyValue) {
        this.propertyValue = propertyValue;
    }

    @Override
    public String toString() {
        return "PropertyBean{" +
                "propertyName='" + propertyName + '\'' +
                ", propertyValue=" + propertyValue +
                '}';
    }
}
