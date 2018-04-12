package com.design.decorator;

/**
 * Created by zhubo on 2018/4/9.
 * 首先定义一个Coffce基类 这里Coffee相当于我们的Component， 是要装饰的类
 */
public abstract class Coffee {


    /**
     * @return 返回价格
     */
    public abstract int getPrice();

    /**
     * @return 返回名字
     */
    public abstract String getName();


}
