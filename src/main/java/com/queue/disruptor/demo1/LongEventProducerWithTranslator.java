package com.queue.disruptor.demo1;

import com.lmax.disruptor.EventTranslatorOneArg;
import com.lmax.disruptor.RingBuffer;

import java.nio.ByteBuffer;

/**
 * Created with IntelliJ IDEA.
 * Description:  Translator可以分离出来并且更加容易单元测试。Disruptor提供了不同的接口
 * (EventTranslator, EventTranslatorOneArg, EventTranslatorTwoArg, 等等)去产生一个Translator对象。
 * 很明显，Translator中方法的参数是通过RingBuffer来传递的。
 * User: zhubo
 * Date: 2018-04-12
 * Time: 13:26
 */
public class LongEventProducerWithTranslator {

    private static final EventTranslatorOneArg<LongEvent,ByteBuffer> TRANSLATOR = new EventTranslatorOneArg<LongEvent, ByteBuffer>() {
        @Override
        public void translateTo(LongEvent longEvent, long l, ByteBuffer buffer) {
            longEvent.setValue(buffer.getLong());
        }
    };

    private final RingBuffer<LongEvent> ringBuffer;

    public LongEventProducerWithTranslator(RingBuffer<LongEvent> ringBuffer) {
        this.ringBuffer = ringBuffer;
    }

    public void onData(ByteBuffer buf){
        ringBuffer.publishEvent(TRANSLATOR,buf);
    }
}
