package com.leetcode;

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
public class LeetCode001 {


	public static void main(String[] args) {
		int[] arr = new int[]{12,3,5,2,6,7,23,45,123,34,60,22};
		fastSort(arr,0,arr.length-1);

	}

	private static void fastSort(int[] array , int low , int high) {
		int l = low;
		int h = high;
		int index = l;
		while(l < h) {
			while(l < h && array[h] > array[index]) {
				h -- ;
			}
			if(array[index] != array[h]) {
				array[index] = array[index] ^ array[h];
				array[h]     = array[index] ^ array[h];
				array[index] = array[index] ^ array[h];
			}
			index = h;
			while(l < h && array[l] < array[index]) {
				l ++;
			}
			if(array[index] != array[l]){  //解决
				array[index] = array[index] ^ array[l];
				array[l]     = array[index] ^ array[l];
				array[index] = array[index] ^ array[l];
			}
			index = l;
		}
		printArr(array);
		try {
			Thread.sleep(200L);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		if(low < index) {
			fastSort(array,low,index);
		}
		if(index+1 < high) {
			fastSort(array,index+1,high);
		}
	}

	private static void printArr(int[] array) {
		for (int i = 0; i < array.length; i++) {
			System.out.print(array[i] + ",");
		}
		System.out.println();
	}
}
