package com.simple.lambda.stream;

import org.springframework.util.Assert;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: zhubo
 * @description:
 * @time: 2018年08月06日
 * @modifytime:
 */
public class BeanTest {

	public static void main(String[] args) {
		List<String> collect = Stream.of("a", "b", "c").collect(Collectors.toList());

		Function f = new Function<String,String>() {
			@Override
			public String apply(String o) {
				return o.toUpperCase();
			}
		};

		List<String> upperCollect = Stream.of("a", "b", "hello").map(item -> item.toUpperCase()).collect(Collectors.toList());


		Function<String,String> function = new Function<String, String>() {
			@Override
			public String apply(String o) {
				return o.toUpperCase();
			}
		};
		List<String> funcCollect = Stream.of("a", "b", "hello").map(function).collect(Collectors.toList());


	}
}
