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
        Runnable runnable = () -> System.out.println("Hello World");

        ActionListener oneArgument = event -> System.out.println("button clicked");

        Runnable multiStatement = () -> {
            System.out.print("Hello");
            System.out.println(" World");
        };

        BinaryOperator<Long> add = (x , y) -> x + y;

        new BinaryOperator<Long>() {
            @Override
            public Long apply(Long aLong, Long aLong2) {
                return null;
            }
        };
    }
}
