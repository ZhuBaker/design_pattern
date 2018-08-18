package com.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: zhubo
 * @description:
 * @time: 2018年08月17日
 * @modifytime:
 */
public class LeetCode006 {

    public static void main(String[] args) {
        LeetCode006 leetCode006 =  new LeetCode006();
        System.out.println(leetCode006.romanToInt("III"));
    }

    public int romanToInt(String s) {
        int result = 0;
        Map<String,Integer> init = init();
        Map<String,Integer> special = special();
        char[] chars = s.toCharArray();
        int length = chars.length;
        for (int i = 0; i < length; i++) {
            if(i < length-1) {
                String combine = String.valueOf(chars,i,2);
                if(special.containsKey(combine)){
                    result += special.get(combine);
                    i++;
                    continue;
                }
            }
            if(init.containsKey(String.valueOf(chars[i])))  {
                result += init.get(String.valueOf(chars[i]));
            }
        }
        return result;
    }

    public Map<String,Integer> special(){
        Map<String,Integer> pattern = new HashMap<>();
        pattern.put("IV",4);
        pattern.put("IX",9);
        pattern.put("XL",40);
        pattern.put("XC",90);
        pattern.put("CD",400);
        pattern.put("CM",900);
        return pattern;
    }
    public Map<String,Integer> init() {
        Map<String,Integer> pattern = new HashMap<>();
        pattern.put("I",1);
        pattern.put("V",5);
        pattern.put("X",10);
        pattern.put("L",50);
        pattern.put("C",100);
        pattern.put("D",500);
        pattern.put("M",1000);
        return pattern;
    }
}
