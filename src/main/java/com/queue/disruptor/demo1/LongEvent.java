package com.queue.disruptor.demo1;

/**
 * 1.定义事件
 * Created with IntelliJ IDEA.
 * Description:首先声明一个Event来包含需要传递的数据
 * User: zhubo
 * Date: 2018-04-12
 * Time: 12:13
 */
public class LongEvent{
    private long value;

    public long getValue() {
        return value;
    }

    public void setValue(long value) {
        this.value = value;
    }
}
