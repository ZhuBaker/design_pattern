package com.simple.lambda.stream;

import java.util.Arrays;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: zhubo
 * @description:
 * @time: 2018年08月06日
 * @modifytime:
 */
public class MinMaxTest {
	public static void main(String[] args) {
		List<Integer> list = Arrays.asList(2,4,6,3,9,4);

		Integer maxInt = list.stream().max(Integer::compareTo).get();
		Integer minInt = list.stream().min(Integer::compareTo).get();

		System.out.println(maxInt);
		System.out.println(minInt);
	}
}
