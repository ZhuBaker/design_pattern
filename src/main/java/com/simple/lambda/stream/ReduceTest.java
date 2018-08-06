package com.simple.lambda.stream;

import java.util.function.BinaryOperator;
import java.util.stream.Stream;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: zhubo
 * @description:
 * @time: 2018年08月06日
 * @modifytime:
 */
public class ReduceTest {
	public static void main(String[] args) {
		long l = Stream.of(1, 2, 3, 4 ).reduce(0, (acc, element) -> acc + element).longValue();
		System.out.println(l);


		BinaryOperator<Integer> operator = new BinaryOperator<Integer>() {
			@Override
			public Integer apply(Integer integer, Integer integer2) {
				return integer * integer2;
			}
		};
		long l1 = Stream.of(1, 2, 3, 4).reduce(1, operator).longValue();
		System.out.println(l1);


	}
}
