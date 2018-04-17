package com.queue.disruptor.demo1.exec;

import com.lmax.disruptor.EventHandler;

import java.util.concurrent.CountDownLatch;

/**
 * @Author: BakerZhu
 * @Date: 2018/4/16 22:23
 * @Description:
 */
public class ExeEventHandler implements EventHandler<ExeEvent> {

    private long expectedValue;
    private CountDownLatch countDownLatch;
    private long count ;

    public ExeEventHandler(){

    }

    public void reset(CountDownLatch countDownLatch , long expectedValue){
        this.expectedValue = expectedValue;
        this.countDownLatch = countDownLatch;
        this.count = 0;
    }

    @Override
    public void onEvent(ExeEvent event, long sequence, boolean endOfBatch) throws Exception {
        count = count + event.getValue();
        if(expectedValue == sequence){
            countDownLatch.countDown();
        }
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public long getExpectedValue() {
        return expectedValue;
    }

    public void setExpectedValue(long expectedValue) {
        this.expectedValue = expectedValue;
    }
}
