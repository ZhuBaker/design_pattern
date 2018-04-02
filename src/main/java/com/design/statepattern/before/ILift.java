package com.design.statepattern.before;

/**
 * Created with IntelliJ IDEA.
 * Description:定义一个电梯的接口
 * User: zhubo
 * Date: 2018-04-02
 * Time: 18:05
 */
public interface ILift {

    //首先电梯门开启动作
    public void open();
    //电梯门有开启，那当然也就有关闭了
    public void close();
    //电梯要能上能下，跑起来
    public void run();
    //电梯还要能停下来，停不下来那就扯淡了
    public void stop();

}
