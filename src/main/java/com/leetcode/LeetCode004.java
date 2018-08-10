package com.leetcode;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: zhubo
 * @description: 给定一个 32 位有符号整数，将整数中的数字进行反转。
输入: 123
输出: 321
输入: -123
输出: -321
输入: 120
输出: 21

假设我们的环境只能存储 32 位有符号整数，其数值范围是 [−2 31,  2 31 − 1]。根据这个假设，如果反转后的整数溢出，则返回 0。


 * @time: 2018年08月10日
 * @modifytime:
 */
public class LeetCode004 {

	public static void main(String[] args) {
		System.out.println(reverse(-289482100));
	}
	public static int reverse(int x) {
		if (x == Integer.MIN_VALUE || x == 0) {
			return 0;
		}
		boolean negative = x < 0;
		long result = 0;
		int[] metaData = initMetaData(x);
		int length = numLength(metaData);
		for(int i = 0 ; i < length ; i ++ ) {
			result = result + (long)(metaData[i] * Math.pow(10,i));
		}
		if(result > Integer.MAX_VALUE || result < Integer.MIN_VALUE) {
			return 0;
		}
		if(negative) result = -1 * result;
		return (int)result;
	}
	private static int[] initMetaData(int x) {
		int index = 9;
		x = Math.abs(x);
		int[] by = new int[index + 1];
		//数组反转
		while (x / 10 != 0 || x % 10 != 0) {
			by[index--] = x / 10 != 0 ? x % 10 : x;
			x = x / 10;
		}
		// 删除开头0
		while(by[0] == 0){
			for(int i = 0 ; i < by.length -1 ; i ++) {
				by[i] = by[i + 1];
			}
			by[9] = 0 ;
		}
		return by;
	}
	private static int numLength(int[] array) {
		int length = array.length;
		int index = 0;
		while (array[index++] == 0) {
			length --;
		}
		return length;
	}
}
