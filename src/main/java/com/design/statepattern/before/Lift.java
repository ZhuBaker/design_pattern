package com.design.statepattern.before;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: zhubo
 * Date: 2018-04-02
 * Time: 18:13
 */
public class Lift implements ILift {

    @Override
    public void open() {
        System.out.println("电梯门开启...");
    }

    @Override
    public void close() {
        System.out.println("电梯门关闭...");
    }

    @Override
    public void run() {
        System.out.println("电梯上下跑起来...");
    }

    @Override
    public void stop() {
        System.out.println("电梯停止了...");
    }

}
