package com.zxk.sort;

import java.util.Arrays;

//冒泡排序
public class Sort01_BubbleSort {
    public static void main(String[] args) {
        int[] arr = {3, 9, -1, 10 , -2};
        sort(arr);

    }

    public static void print(int[] arr) {
        System.out.println(Arrays.toString(arr));
    }

    private static void sort(int[] arr) {
        int temp;
        boolean flag = true;
        for (int i = 0; i < arr.length - 1; i++) {//每完成一轮排序则排除一个最大值,剩余的是无序数组,注意区别插入排序中的交换法
            for (int j = 0; j < arr.length-i-1; j++) {
                if (arr[j] > arr[j + 1]) {
                    flag = false;
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
            System.out.println("完成第"+(i+1)+"轮排序");
            print(arr);
            //优化:如果本轮没有换位则无需再排
            if (flag) {
                break;
            } else {
                flag = true;
            }

        }
    }
}
