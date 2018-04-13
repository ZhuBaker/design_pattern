package com.design.结构型模式.桥接模式;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: zhubo
 * Date: 2018-04-13
 * Time: 12:50
 */
public abstract class Image {

    protected ImageImp imp;

    public void setImp(ImageImp imp) {
        this.imp = imp;
    }

    public abstract void parseFile(String fileName);

}
