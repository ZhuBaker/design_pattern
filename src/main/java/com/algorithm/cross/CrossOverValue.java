package com.algorithm.cross;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: zhubo
 * @description:
 * @time: 2018年07月31日
 * @modifytime:
 */
public class CrossOverValue {

    public static void main(String[] args) {
        useTemporaryVariable();
        change2();
        binaryOperation();;
    }

    //使用临时变量
    public static void useTemporaryVariable() {
        int x = 5 , y = 10;
        int tmp = x;
        x = y ;
        y = tmp;
        System.out.println("x="+x+"y="+y);
    }

    /**
     * 可以用两个数求和然后相减的方式进行数据交换,弊端在于如果 x 和 y 的数值过大的话，超出 int 的值会损失精度。
     */
    public static void change2() {
        int x = 5 , y = 10;
        x = x + y ;
        y = x - y;
        x = x - y;
        System.out.println("x="+x+"y="+y);
    }


    public static void binaryOperation() {
        int x = 5 , y = 10;
        x = x ^ y;
        y = x ^ y;
        x = x ^ y ;
        System.out.println("x="+x+"y="+y);
    }


}
