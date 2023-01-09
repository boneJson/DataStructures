package com.zxk.algorithm.kmp;

//暴力匹配
public class ViolentMatch {
    public static void main(String[] args) {
        String str1 = "abcde";
        String str2 = "cde";
        System.out.println(match(str1,str2));
    }

    public static int match(String str1, String str2) {
        char[] cs1 = str1.toCharArray();
        char[] cs2 = str2.toCharArray();

        for (int i = 0,j = 0; i < cs1.length; i++) {
            if (cs1[i] == cs2[j]) {
                j++;
            }else {
                i=i-j;
                j = 0;
            }


            if (j == cs2.length) {
                return i - j + 1;
            }
        }
        return -1;
    }
}
