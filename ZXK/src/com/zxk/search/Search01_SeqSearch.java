package com.zxk.search;

import java.util.Arrays;

//线性查找
public class Search01_SeqSearch {
    public static void main(String[] args) {
        int[] arr = { 1, 9, 11, -1, 34, 89 };
        System.out.println(search(arr, -1));

    }

    public static void print(int[] arr) {
        System.out.println(Arrays.toString(arr));
    }

    /**
     *
     * @param arr  给定数组
     * @param value 目标元素值
     * @return 返回目标元素的下标,没找到返回-1
     */
    public static int search(int[] arr, int value) {
        int index = -1;

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == value) {
                index = i;
            }
        }

        return index;
    }
}
