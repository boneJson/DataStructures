package com.zxk.sort;

import java.util.Arrays;

//基数排序
public class Sort07_RadixSort {
    public static void main(String[] args) {
        int[] arr = {53, 3, 542, 748, 14, 214};
        sort(arr);
        print(arr);

    }

    public static void print(int[] arr) {
        System.out.println(Arrays.toString(arr));
    }



    private static void sort(int[] arr) {

        //准备工作:
        // 1.开辟一个二维数组(10个桶),每个桶大小和arr保持一致
        // 2.计算数组中最大位数
        int[][] buckets = new int[10][arr.length];
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }
        int rounds = (max + "").length();

        //元素最高n位,就需要进行n轮排序,依次对个位,十位,百位操作
        for (int round = 0,n=1; round < rounds; round++,n*=10) {

            //每一轮需先重置桶偏移量
            int[] bucketOffset = new int[10];

            //遍历数组元素,计算桶编号,放入对应桶中并更新桶偏移量
            for (int i = 0; i < arr.length; i++) {
                int bucketNo = arr[i]/n % 10;
                buckets[bucketNo][bucketOffset[bucketNo]] = arr[i];
                bucketOffset[bucketNo]++;
            }

            //顺序遍历每个桶中元素,并同步到原数组
            int index = 0;
            for (int i = 0; i < 10; i++) {
                if (bucketOffset[i] != 0) {
                    for (int j = 0; j < bucketOffset[i]; j++) {
                        arr[index++] = buckets[i][j];
                    }
                }
            }
            System.out.print("第" + (round+1) + "轮:");
            print(arr);
        }

    }
}
