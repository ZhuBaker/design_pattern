package com.simple;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: zhubo
 * @description:
 * @time: 2018年08月06日
 * @modifytime:
 */
public class GcCase {

	public static void main(String[] args) {
		for (int i = 0; i < 1000; i++) {
			allocate_1M();
		}
	}

	public static void allocate_1M() {
		byte[] _1M = new byte[1024 * 1000];
	}
}
