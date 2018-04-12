package com.queue.disruptor.demo1;

import com.lmax.disruptor.EventFactory;

/**
 * 2.定义事件工厂
 * Created with IntelliJ IDEA.
 * Description: 由于需要让Disruptor为我们创建事件，我们同时还需要声明一个EventFactory来实例化Event对象，
 * 事件工厂(Event Factory)定义了如何实例化前面第1步中定义的事件(Event)，需要实现接口com.lmax.disruptor.EventFactory<T>。Disruptor
 * 通过 EventFactory 在 RingBuffer 中预创建 Event 的实例。一个 Event 实例实际上被用作一个“数据槽”，
 * 发布者发布前，先从 RingBuffer 获得一个 Event 的实例，然后往 Event 实例中填充数据，之后再发布到 RingBuffer 中，
 * 之后由 Consumer 获得该 Event 实例并从中读取数据。
 * User: zhubo
 * Date: 2018-04-12
 * Time: 12:16
 */
public class LongEventFactory implements EventFactory {

    @Override
    public Object newInstance() {
        return new LongEvent();
    }
}
