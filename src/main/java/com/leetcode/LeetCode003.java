package com.leetcode;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: zhubo
 * @description:
 *
给定两个非空链表来表示两个非负整数。位数按照逆序方式存储，它们的每个节点只存储单个数字。将两数相加返回一个新的链表。
你可以假设除了数字 0 之外，这两个数字都不会以零开头。
输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
输出：7 -> 0 -> 8
原因：342 + 465 = 807
 * @time: 2018年08月09日
 * @modifytime:
 */
public class LeetCode003 {
	public static void main(String[] args) {
		LeetCode003 code003 =  new LeetCode003();
		ListNode node1 = new ListNode(new ListNode(new ListNode(null,3),4),2);
		ListNode node2 = new ListNode(new ListNode(new ListNode(null,4),6),5);
		ListNode listNode = code003.addTwoNumbers(node1, node2);
		System.out.println(listNode);
	}
	private  ListNode addTwoNumbers(ListNode l1 , ListNode l2) {
		return this.addNode(l1,l2,0);
	}
	private  ListNode addNode(ListNode node1 , ListNode node2 , int num) {
		int v = 0;
		ListNode node = new ListNode();
		int n = (node1 != null ? node1.getVal() : 0)  + (node2 != null ? node2.getVal() : 0) + num;
		node.setVal(n%10);
		if(node1 != null && node2 != null) {
			if(n >= 10) {
				node.setNext(addNode(node1.getNext() , node2.getNext() , 1));
			}else {
				node.setNext(addNode(node1.getNext(),node2.getNext() , 0));
			}
		}else if(node1!= null && node2 == null) {
			if(n >= 10) {
				node.setNext(addNode(node1.getNext() , null , 1));
			}
		}else if (node1 == null && node2 != null) {
			if(n >= 10) {
				node.setNext(addNode(null , node2.getNext() , 1));
			}
		}else if (num == 0) {
			return null;
		}
		return node;
	}
	private static class ListNode {
		int val;
		ListNode next;
		public ListNode() {
		}
		public ListNode(ListNode node , int val) {
			this.next = node;
			this.val = val;
		}
		public int getVal() {
			return val;
		}
		public void setVal(int val) {
			this.val = val;
		}
		public ListNode getNext() {
			return next;
		}
		public void setNext(ListNode next) {
			this.next = next;
		}
		@Override
		public String toString() {
			return "ListNode{" +
					"val=" + val +
					", next=" + next +
					'}';
		}
	}
}
