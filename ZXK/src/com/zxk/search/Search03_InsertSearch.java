package com.zxk.search;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//插值查找
public class Search03_InsertSearch {

    public static void main(String[] args) {
        int[] arr = { 1,8, 10, 89, 1000,1000, 1000,1234 };
        System.out.println(search01(arr, 0,arr.length-1,1000));

    }

    public static void print(int[] arr) {
        System.out.println(Arrays.toString(arr));
    }

    //基于二分查找的改良,将权重由1/2改为自适应,在数据量较大且分布均匀时明显效率更优
    public static int search01(int[] arr,int lo,int hi, int value) {
        int index = -1;

        //优化:查找值超过极值则直接返回-1
        if (lo > hi||value<arr[0]||value>arr[arr.length-1]) {
            return index;
        }

        //(value-arr[lo])/(arr[hi]-arr[lo])就是自适应的权重,二分的权重是1/2
        int mid = lo+(hi-lo)*( (value-arr[lo])/(arr[hi]-arr[lo]) );
        int midVal = arr[mid];

        if (value < midVal) {
            index=search01(arr, lo, mid - 1, value);
        } else if (value > midVal) {
            index=search01(arr, mid + 1, hi, value);
        } else {
            index=mid;
        }

        return index;
    }

}
