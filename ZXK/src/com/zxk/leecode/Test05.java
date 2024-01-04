package com.zxk.leecode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//给你一个整数数组 nums 和一个整数 k 。
//
//每一步操作中，你需要从数组中选出和为 k 的两个整数，并将它们移出数组。
//
//返回你可以对数组执行的最大操作数。
public class Test05 {
    public static void main(String[] args) {
        int k = 6;
        int[] nums = {3,1,3,4,3};//13 334
        System.out.println(new Test05().maxOperations(nums, k));
    }


    public int maxOperations(int[] nums, int k) {
        Arrays.sort(nums);
        int res = 0;

        int i = 0;
        int j = nums.length - 1;
        int sum = -1;
        while (i < j) {
            sum = nums[i] + nums[j];
            if (sum == k) {
                res++;
                i++;
                j--;
            } else if (sum < k) {
                i++;
            } else {
                j--;
            }
        }
        return res;

    }
}
