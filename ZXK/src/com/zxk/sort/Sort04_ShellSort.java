package com.zxk.sort;

import java.util.Arrays;

//希尔排序(基于插入排序的优化,又叫缩小增量排序)
//交换式对比移位式,后者是更新角标然后一次插入,前者是每发现逆序就三个变量间赋值,后者更优
public class Sort04_ShellSort {
    public static void main(String[] args) {
        int[] arr = {8, 9, 1, 7, 2,3,5,4,6,0};
        sort2(arr);

    }

    public static void print(int[] arr) {
        System.out.println(Arrays.toString(arr));
    }

    //参考插入排序的交换式,加入分组和步长的概念,不断缩小组内增量
    private static void sort1(int[] arr) {
        int temp = 0;
        int count = 0;
        for (int gap = arr.length/2; gap >0 ; gap/=2) {
            for (int i = 0; i < arr.length - gap; i++) {//这里使用i++而不是加步长,才能保证每个组内排序
                for (int j = i; j >=0; j-=gap) {
                    if (arr[j] > arr[j + gap]) {
                        temp = arr[j];
                        arr[j] = arr[j + gap];
                        arr[j + gap] = temp;
                    }
                }
            }
            System.out.print("第"+(++count)+"轮");
            print(arr);
        }

    }

    //参考插入排序的移位法,加入分组和步长的概念,不断缩小组内增量
    private static void sort2(int[] arr) {
        int value = 0;
        int key = 0;
        int count = 0;

        for (int gap = arr.length/2; gap >0 ; gap/=2) {

            for (int i = gap; i < arr.length ; i++) {
                value = arr[i];
                key = i - gap;
                while (key >= 0 && value < arr[key]) {
                    arr[key + gap] = arr[key];
                    key-=gap;
                }
                if (key != i - gap) {
                    arr[key + gap] = value;
                }
            }

            System.out.print("第"+(++count)+"轮");
            print(arr);
        }

    }
}