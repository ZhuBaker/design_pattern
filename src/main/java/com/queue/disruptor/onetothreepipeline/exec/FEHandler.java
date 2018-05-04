package com.queue.disruptor.onetothreepipeline.exec;

import com.lmax.disruptor.EventHandler;
import com.queue.disruptor.support.FunctionEvent;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: zhubo
 * Date: 2018-04-18
 * Time: 9:41
 */
public class FEHandler implements EventHandler<FunctionEvent> {
    /**
     * Called when a publisher has published an event to the {@link RingBuffer}.  The {@link BatchEventProcessor} will
     * read messages from the {@link RingBuffer} in batches, where a batch is all of the events available to be
     * processed without having to wait for any new event to arrive.  This can be useful for event handlers that need
     * to do slower operations like I/O as they can group together the data from multiple events into a single
     * operation.  Implementations should ensure that the operation is always performed when endOfBatch is true as
     * the time between that message an the next one is inderminate.
     *
     * @param event      published to the {@link RingBuffer}
     * @param sequence   of the event being processed
     * @param endOfBatch flag to indicate if this is the last event in a batch from the {@link RingBuffer}
     * @throws Exception if the EventHandler would like the exception handled further up the chain.
     */
    @Override
    public void onEvent(FunctionEvent event, long sequence, boolean endOfBatch) throws Exception {
        System.out.println(event.getOperandOne());
    }
}
