package com.leetcode;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: bakerZhu
 * @description: 回文判断
 * @time: 2018年08月16日
 * @modifytime:
 */
public class LeetCode005 {

	public static void main(String[] args) {
		int x = 123321;
		boolean palindrome = isPalindrome2(x);
		System.out.println(palindrome);
	}

	/**
	 * 1. 如果 x < 0 则返回false
	 * 2. 如果 x 与 回文 相等
	 *
	 * @param x
	 * @return
	 */
	public static boolean isPalindrome(int x) {
		if (x < 0) return false;
		String s = String.valueOf(x);
		String s1 = new StringBuilder(s).reverse().toString();
		return s.equals(s1);
	}

	public static boolean isPalindrome2(int x) {
		if (x < 0 || (x % 10 == 0 && x != 0)) return false;
		int revertedNumber = 0;
		while (x > revertedNumber) {
			revertedNumber = revertedNumber * 10 + x % 10;
			x /= 10;
		}
		return x == revertedNumber || x == revertedNumber / 10;
	}

}
