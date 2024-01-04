package com.zxk.leecode;

import java.util.Arrays;

//给你两个整数数组 nums1 和 nums2 ，请你实现一个支持下述两类查询的数据结构：
//
//累加 ，将一个正整数加到 nums2 中指定下标对应元素上。
//计数 ，统计满足 nums1[i] + nums2[j] 等于指定值的下标对 (i, j) 数目（0 <= i < nums1.length 且 0 <= j < nums2.length）。
//实现 FindSumPairs 类：
//
//FindSumPairs(int[] nums1, int[] nums2) 使用整数数组 nums1 和 nums2 初始化 FindSumPairs 对象。
//void add(int index, int val) 将 val 加到 nums2[index] 上，即，执行 nums2[index] += val 。
//int count(int tot) 返回满足 nums1[i] + nums2[j] == tot 的下标对 (i, j) 数目。
public class Test07 {
    int[] nums1;
    int[] nums2;

    public static void main(String[] args) {
        int[] nums1 = {1, 1, 2, 2, 2, 3};
        int[] nums2 = {1, 4, 5, 2, 5, 4};
        System.out.println(new Test07(nums1, nums2).count(7));//8

    }
    public Test07(int[] nums1, int[] nums2) {
        this.nums1 = nums1;
        this.nums2 = nums2;
    }

    public void add(int index, int val) {
        nums2[index] += val;
    }

    public int count(int tot) {
        int[] arr01 = Arrays.copyOf(nums1, nums1.length);
        int[] arr02 = Arrays.copyOf(nums2, nums2.length);
        Arrays.sort(arr01);
        Arrays.sort(arr02);
        int i = 0;
        int j = arr02.length - 1;
        int res = 0;
        while (i < arr01.length && j >= 0) {
            int sum = arr01[i] + arr02[j];
            if (sum == tot) {//存在多值重复问题

                while (i<arr01.length-1) {
                    if (arr01[i] == arr01[i + 1]) {
                        res++;
                        i++;
                    } else {
                        i++;
                        res++;
                        break;
                    }
                }
                while (j>0) {
                    if (arr02[j] == arr02[j - 1]) {
                        res++;
                        j--;
                    } else {
                        j--;
                        res++;
                        break;
                    }
                }
            } else if (sum > tot) {
                j--;
            } else {
                i++;
            }
        }
        return res;
    }
}
