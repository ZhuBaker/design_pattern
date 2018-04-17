package com.queue.disruptor.demo1.exec;

/**
 * @Author: BakerZhu
 * @Date: 2018/4/16 22:35
 * @Description:
 */
public class ExeUtils {
    public static long getCount(long count){
        long exe = 0L;
        for(long j = 0 ; j < count ; j ++){
            exe = exe + j;
        }
        return exe;
    }
}
