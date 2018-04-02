package com.design.statepattern.fin;

/**
 * Created with IntelliJ IDEA.
 * Description : 电梯门关闭以后，电梯可以做哪些事情
 * User: zhubo
 * Date: 2018-04-02
 * Time: 19:17
 */
public class ClosingState extends LiftState{
    //电梯门关了再打开，逗你玩呢，那这个允许呀
    @Override
    public void open() {
        super.context.setLiftState(Context.openningState); //置为门敞状态
        super.context.getLiftState().open();
    }
    //电梯门关闭，这是关闭状态要实现的动作
    @Override
    public void close() {
        System.out.println("电梯门关闭...");
    }

    @Override
    public void run() {
        super.context.setLiftState(Context.runningState); //设置为运行状态；
        super.context.getLiftState().run();
    }

    @Override
    public void stop() {
        super.context.setLiftState(Context.stoppingState); //设置为停止状态；
        super.context.getLiftState().stop();
    }
}
