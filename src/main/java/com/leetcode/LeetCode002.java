package com.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: zhubo
 * @description:
	Given an array of integers, return indices of the two numbers such that they add up to a specific target.
	You may assume that each input would have exactly one solution, and you may not use the same element twice.

	Example:
	Given nums = [2, 7, 11, 15], target = 9,
	Because nums[0] + nums[1] = 2 + 7 = 9,
	return [0, 1].
 * @time: 2018年08月08日
 * @modifytime:
 */
public class LeetCode002 {
	public static void main(String[] args) {
		int[] arr = new int[]{3,2,4};
		printArr(findTarget(arr, 6));
	}
	/**
	 * 主要逻辑实现
	 * @param array
	 * @param target
	 * @return
	 */
	private static int[]  findTarget(int[] array,int target) {
		int[] returnVal = new int[2];
		// 排除array中两值相同的情况

		Map<Integer,Integer> map = new HashMap<>(array.length,1);
		for (int i = 0; i < array.length; i++) {
			map.put(array[i],i);
		}
		for (int i = 0; i < array.length; i++) {
			Integer index = map.get((target - array[i]));
			if(index != null && index != i) {
				returnVal[0] = i;
				returnVal[1] = index;
				return returnVal;
			}
		}
		throw new IllegalArgumentException("cannot find tow element in array sum to target!");
	}

	private static void printArr(int[] array) {
		for (int i = 0; i < array.length; i++) {
			System.out.print(array[i] + ",");
		}
	}

}
