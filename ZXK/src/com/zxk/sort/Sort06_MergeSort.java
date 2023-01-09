package com.zxk.sort;

import java.util.Arrays;

//归并排序
public class Sort06_MergeSort {
    public static void main(String[] args) {
        int[] arr = {78,0,-9,  0  ,-23,-566,-567,70     };
        sort(arr,0, arr.length-1);
        print(arr);

    }

    public static void print(int[] arr) {
        System.out.println(Arrays.toString(arr));
    }


    //分治方法,分为递归分组,治为该层的归并排序
    private static void sort(int[] arr,int low ,int high) {
        //辅助数组,用于复制归并后的有序数组
        int[] assist = new int[arr.length];

        //安全性校验,表示该组只有一个元素
        if (high <= low) {
            return;
        }

        //分治法中的分,即分组,也是递归调用,上面的健壮判断可以看成递归终止条件
        int mid = low + ((high - low) >> 1);
        sort(arr, low, mid);
        sort(arr,mid+1,high);

        //当分组完毕(递归终止)后,最终的两组内只有一个元素(即有序),才会执行下面代码,即层层排序返回上层,这里为界,上面为分组,下面为(归并)排序
        //个人理解为:在上面的分组操作没有终止前,一直在做分的操作,直到分组完毕才开始治,治完再治上层
        //举例:本层的上下界为0和1,则上面的分组防止直接不做操作返回,而对两个单元素的子数组做如下的归并排序,返回上层

        //使用三个指针,表示两个子数组的起点索引和归并数组的起点索引
        int i = low;
        int p1 = low;
        int p2 = mid + 1;

        //比较子有序数组的起点值,不断复制最小值到辅助数组并后移指针,直到某一子数组复制完毕
        while (p1 <= mid && p2 <= high) {
            if (arr[p1] < arr[p2]) {
                assist[i++] = arr[p1++];
            } else {
                assist[i++] = arr[p2++];
            }
        }

        //将未复制完的子数组弄完
        while (p1 <= mid) {
            assist[i++] = arr[p1++];
        }
        while (p2 <= high) {
            assist[i++] = arr[p2++];
        }

        //将归并后的顺序同步给请求者
        for (int j = low; j <= high; j++) {
            arr[j] = assist[j];
        }
    }
}
