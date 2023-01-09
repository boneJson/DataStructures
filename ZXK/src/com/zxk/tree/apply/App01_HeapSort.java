package com.zxk.tree.apply;

import java.util.Arrays;

//堆排序
public class App01_HeapSort {
    public static void main(String[] args) {
        int[] arr = {10, 20, 15, 25, 50, 30, 40};
        heapSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    //堆排序方法
    public static void heapSort(int[] arr) {
        int temp = 0;

        //从最后一个非叶子节点开始,从右往左,从下往上,调整堆,最终得到一个大顶堆
        //最后一个非叶子节点下标:arr.length / 2 - 1
        for (int i = arr.length / 2 - 1; i >= 0; i--) {
            adjust(arr, i, arr.length);
        }

        //将大顶堆的堆顶(最大值)与末尾交换,并缩减调整区间
        //调整剩余次小值的堆成为大顶堆,直到最终下标0和1交换,完成堆排序
        //问:这里j范围表示堆就一个值,为什么还要执行操作?  因为这里是先交换再调整,因为0和1需要交换,所以j需要取1,只是j=1调整方法没有生效
        for (int j = arr.length-1; j >=1; j--) {
            temp = arr[0];
            arr[0] = arr[j];
            arr[j] = temp;
            adjust(arr,0,j);
        }

    }

    //调整 堆顶为节点i的堆 为大顶堆,前提是节点i的左右子树都是大顶堆
    //问:该方法对传入的i有什么要求?
    //答:目的不同,传入i不同
    //  1.当初次构建大顶堆时,i应该从最后一个非叶子节点开始自减直到为0 (类似归并排序的先分后治)
    //  2.非初次调整时,除了堆顶,下层所有都符合上大下小,就是说目的成了将堆顶元素从上往下找位置插入构建大顶堆,所以i就是堆顶0
    public static void adjust(int[] arr, int i, int length) {
        //取出堆顶值
        int temp = arr[i];

        //从上往下寻找插入位置同时,将较大值往上挪
        for (int k = 2 * i + 1; k < length; k = 2 * k + 1) {
            //使k指向左右子节点中较小的
            if (k + 1 < length && arr[k] < arr[k + 1]) {
                k++;
            }

            //类似插值排序的移位法,就是将较大值往上挪,直到找到合适的位置插入堆顶值,从而完成构建大顶堆
            if (temp < arr[k]) {//这里为什么得用temp而不是arr[i]
                arr[i] = arr[k];
                i = k;
            //为什么这里可以break?
            //答:因为除了堆顶整体都符合上大下小,else表示堆顶比该层的节点大,即堆依然满足大顶堆,则堆无需调整
            } else {
                break;
            }
        }
        arr[i] = temp;
    }
}