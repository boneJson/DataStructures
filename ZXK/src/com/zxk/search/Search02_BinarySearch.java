package com.zxk.search;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//二分查找
public class Search02_BinarySearch {

    public static void main(String[] args) {
        int[] arr = { 1,8, 10, 89, 1000,1000, 1000,1234 };
        System.out.println(search02(arr, 0,arr.length-1,1000));
//        search03(arr, 0, arr.length - 1, 1000);
//        System.out.println(list);

    }

    public static void print(int[] arr) {
        System.out.println(Arrays.toString(arr));
    }

    //返回下标,没找到返回-1,前提是数组本身有序
    public static int search01(int[] arr,int lo,int hi, int value) {
        int index = -1;

        //这里为什么不能是大于或等于,因为最后区间大小就是1,即lo==mid==hi
        //而任一树枝上(本题只有一只树枝)一定先满足lo==hi(如果能找到此层就该返回正确值),而后才是lo>hi,
        if (lo > hi) {
            return index;
        }

        int mid = (lo + hi) / 2;
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

    //返回多个相同值的索引集合,前提是数组本身有序
    //原理:找到一个正确值后没有立即返回,而是向两侧搜索相同值
    public static List<Integer> search02(int[] arr,int lo,int hi, int value) {
        ArrayList<Integer> result = new ArrayList<>();

        //这里为什么不能是大于或等于,因为最后区间大小就是1,即lo==mid==hi
        //而任一树枝上(本题只有一只树枝)一定先满足lo==hi(如果能找到此层就该返回正确值),而后才是lo>hi,
        if (lo > hi) {
            return result;
        }

        int mid = (lo + hi) / 2;
        int midVal = arr[mid];

        if (value < midVal) {
            return search02(arr, lo, mid - 1, value);
        } else if (value > midVal) {
            return search02(arr, mid + 1, hi, value);
        } else {
            result.add(mid);
            int l = mid;
            int r = mid;
            //注意这里的或不能用短路或,否则后面的r自增不会执行,导致mid重复收集
            while (arr[--l] == midVal | arr[++r] == midVal) {
                if (arr[l] == midVal) {
                    result.add(l);
                }
                if (arr[r] == midVal) {
                    result.add(r);
                }
            }
        }

        return result;
    }


    public static List<Integer> list = new ArrayList<>();

    //基于search01优化:返回若干个相同值
    //不同于search01,方法1的原理是树只有一个树枝,遇到正确结果立即层层返回
    //search03原理,没有返回值,暴力搜索,遍历多个树枝的所有节点,收集所有满足的结果放入全局变量list集合
    //当然本方法不要求数组本身有序
    public static void search03(int[] arr,int lo,int hi, int value) {

        //这里为什么不能是大于或等于,因为最后区间大小就是1,即lo==mid==hi
        //而任一树枝上(本题只有一只树枝)一定先满足lo==hi(如果能找到此层就该返回正确值),而后才是lo>hi,
        if (lo > hi) {
            return;
        }

        int mid = (lo + hi) / 2;
        int midVal = arr[mid];

        if (value == midVal) {
            list.add(mid);
        }

        search03(arr, lo, mid - 1, value);
        search03(arr, mid + 1, hi, value);
    }
}
