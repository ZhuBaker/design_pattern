package com.design.结构型模式.适配器模式.对象适配器模式;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: zhubo
 * Date: 2018-04-12
 * Time: 9:12
 */
public interface ScoreOperation {

    public int[] sort(int array[]); //成绩排序
    public int search(int array[],int key); //成绩查找

}
