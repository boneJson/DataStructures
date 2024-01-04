package com.zxk.leecode;

import java.util.*;

//给你一个混合了数字和字母的字符串 s，其中的字母均为小写英文字母。
//
//请你将该字符串重新格式化，使得任意两个相邻字符的类型都不同。也就是说，字母后面应该跟着数字，而数字后面应该跟着字母。
//
//请你返回 重新格式化后 的字符串；如果无法按要求重新格式化，则返回一个 空字符串 。
public class Test03 {
    public static void main(String[] args) {

        System.out.println(new Test03().reformat("covid2019"));
    }

    public String reformat(String s) {
        char[] chars = s.toCharArray();
        LinkedList<Character> list = new LinkedList<Character>();
        for (char aChar : chars) {
            list.add(aChar);
        }

        LinkedList<Character> nums = new LinkedList<Character>();


        Iterator<Character> iterator = list.iterator();
        while (iterator.hasNext()) {
            Character character = iterator.next();
            if (character >= '0' && character <= '9') {
                nums.add(character);
                iterator.remove();
            }
        }


        char[] resChars = new char[nums.size() + list.size()];
        if (Math.abs(nums.size()-list.size()) > 1) {
            return "";
        } else if (nums.size() > list.size()) {
            for (int i = 0; i < resChars.length; i++) {
                if (i % 2 == 0) {
                    resChars[i] = nums.removeFirst();
                } else {
                    resChars[i] = list.removeFirst();
                }
            }
        } else {
            for (int i = 0; i < resChars.length; i++) {
                if (i % 2 == 0) {
                    resChars[i] = list.removeFirst();
                } else {
                    resChars[i] = nums.removeFirst();
                }
            }
        }
        return new String(resChars);
    }
}
