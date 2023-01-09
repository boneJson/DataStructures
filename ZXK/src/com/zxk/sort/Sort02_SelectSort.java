package com.zxk.sort;

import java.util.Arrays;

//选择排序
public class Sort02_SelectSort {
    public static void main(String[] args) {
        int[] arr = {101, 34, 119, 1, -1, 90, 123};
        sort(arr);

    }

    public static void print(int[] arr) {
        System.out.println(Arrays.toString(arr));
    }

    private static void sort(int[] arr) {

        int min = 0;
        int cor=0;

        for (int j = 0; j < arr.length - 1; j++) {
            //每一轮都默认乱序数组第一个数为该轮最小值
            min = arr[j];
            cor=j;
            for (int i = j; i < arr.length; i++) {
                if (arr[i] < min) {
                    //找到更小的则更新最小值和角标
                    cor = i;
                    min = arr[i];
                }
            }
            //最小值已保存,将第一个数与最小处赋值
            if (cor!=j)//优化:第一个数为最小则无需赋值
                arr[cor] = arr[j];
            arr[j] = min;
            print(arr);
        }

    }
}
