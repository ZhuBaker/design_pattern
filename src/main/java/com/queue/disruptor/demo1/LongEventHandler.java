package com.queue.disruptor.demo1;


import com.lmax.disruptor.EventHandler;

/**
 * 3.定义事件处理的具体实现
 * Created with IntelliJ IDEA.
 * Description: 我们还需要一个事件消费者，也就是一个事件处理器。这个事件处理器简单地把事件中存储的数据打印到终端：
 * User: zhubo
 * Date: 2018-04-12
 * Time: 12:17
 */
public class LongEventHandler implements EventHandler<LongEvent> {

    @Override
    public void onEvent(LongEvent longEvent, long l, boolean b) throws Exception {

        System.out.println(longEvent.getValue());
        Thread.sleep(100);
    }
}
