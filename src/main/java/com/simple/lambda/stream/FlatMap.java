package com.simple.lambda.stream;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: zhubo
 * @description: 可用于Stream替换 将多个Stream转换为1个Stream
 * @time: 2018年08月06日
 * @modifytime:
 */
public class FlatMap {

	public static void main(String[] args) {

		List<Integer> collect = Stream.of(Arrays.asList(1, 2), Arrays.asList(3, 4))
				.flatMap(item -> item.stream()).collect(Collectors.toList());
		System.out.println(collect);



	}
}
