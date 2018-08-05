package com.simple.lambda.expr;

import java.awt.event.ActionListener;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.Function;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: zhubo
 * @description:
 * @time: 2018年08月05日
 * @modifytime:
 */
public class LambdaExpression {

    public static void express() {

        // 1----------------------------------------------
        Runnable runnable = () -> System.out.println("Hello World");
        // 2----------------------------------------------
        ActionListener oneArgument = event -> System.out.println("button clicked");
        // 3----------------------------------------------
        Runnable multiStatement = () -> {
            System.out.print("Hello");
            System.out.println(" World");
        };
        // 4----------------------------------------------
        BinaryOperator<Long> add = (x , y) -> x + y;

        new BinaryOperator<Long>() {
            @Override
            public Long apply(Long aLong, Long aLong2) {
                return null;
            }
        };
        // 5----------------------------------------------
        BinaryOperator<Long> addOper = (Long x , Long y) -> x + y;

        final int a = 1;
        new Runnable(){
            @Override
            public void run() {
                System.out.println(a);
            }
        };
    }
}
