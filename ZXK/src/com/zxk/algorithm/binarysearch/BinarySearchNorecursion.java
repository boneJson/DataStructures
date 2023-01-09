package com.zxk.algorithm.binarysearch;

//非递归二分查找
public class BinarySearchNorecursion {
    public static void main(String[] args) {
        int[] arr = {1, 3, 8, 10, 11, 67, 100};
        System.out.println(search(arr,100));
    }

    public static int search(int[] arr, int value) {
        int lo = 0;
        int hi = arr.length - 1;
        while (lo <= hi) {
            int mid = (lo + hi) / 2;
            if (value < arr[mid]) {
                hi = mid - 1;
            } else if (value > arr[mid]) {
                lo = mid + 1;
            } else {
                return mid;
            }
        }
        return -1;
    }
}
