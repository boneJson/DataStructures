package com.zxk.search;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//斐波那契查找
public class Search04_FibonacciSearch {

    public static void main(String[] args) {
        int[] arr = {1, 8, 10, 89, 1000, 1234};
        System.out.println(search(arr, 1234));
    }

    public static void print(int[] arr) {
        System.out.println(Arrays.toString(arr));
    }

    //获取斐波那契数组方法
    public static int[] fib() {
        int[] f = new int[20];
        f[0] = 1;
        f[1] = 1;
        for (int i = 2; i < 20; i++) {
            f[i] = f[i - 1] + f[i - 2];
        }
        return f;
    }

    //查找方法,不使用递归实现
    //斐波那契查找说到底只是让查找的区间大小一直在斐波那契数列中
    //,说白了就是切割比接近0.618,既然如此为什么不将二分查找的逻辑改为权重0.618
    //,纯粹就是装杯吧,说是感受数学之美还比不上插值查找的自适应权重有用
    public static int search(int[] arr, int value) {
        int lo = 0;
        int hi = arr.length - 1;
        int key = 0;//指针指向斐波那契数列元素,用以控制区间大小
        int mid = 0;//切割点
        int[] f = fib();//斐波那契数列

        //临时数组扩容到原数组大小的下一级,比如6扩容到5的下一级8
        while (hi > f[++key]-1) {
        }
        int[] temp = Arrays.copyOf(arr, f[key]);
        for (int i = hi+1; i < temp.length; i++) {
            temp[i] = temp[hi];
        }

        //此时key==5,f[key]==8,f[key-1]==5,f[key-2]==3,lo==0,hi==5,temp.length==8,mid==4
        //主要就是保证lo和hi的差值就是斐波那契数列的某个值-1,即查找的区间大小就是斐波那契数列值
        //缩小查找区间只需使key自减就行,但是
        //上下界的变迁需要注意,hi变迁只需赋为mid,lo却赋为mid+1,这是因为mid和hi间距是f[key-2]需要-1
        while (lo < hi) {
            mid = lo+ f[key - 1] - 1;
            if (value < temp[mid]) {
                hi = mid;
                key--;
            } else if (value > temp[mid]) {
                lo = mid + 1;
                key -= 2;
            } else {
                return mid;
            }
        }

        //hi==lo+f[k]-1,f[k]>=1
        if (lo == hi) {
            return lo;
        }


        return -1;
    }
}
