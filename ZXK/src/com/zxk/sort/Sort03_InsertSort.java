package com.zxk.sort;

import java.util.Arrays;

//插入排序
public class Sort03_InsertSort {
    public static void main(String[] args) {
        int[] arr = {101, 34, 119, 1, -1, 89};
        sort2(arr);

    }

    public static void print(int[] arr) {
        System.out.println(Arrays.toString(arr));
    }


    private static void sort(int[] arr) {
        //变量声明在循环外更好
        int insertVal =0;
        int insertIndex= 0;
        for (int i = 1; i < arr.length; i++) {
            insertVal = arr[i];//取出待插入数,保证该位置可被后推覆盖
            insertIndex= i-1;//有序数组下界
            //索引不越界下,逆序遍历有序数组,只要待插入值比遍历值小(小到大排序)就将遍历值后推
            while (insertIndex >= 0 && insertVal < arr[insertIndex]) {
                arr[insertIndex + 1] = arr[insertIndex];
                insertIndex--;
            }
            //位置找到,则先补上遍历附带的索引自减1,再将待插入值放入有序数组
            //赋值优化:只有待插入数和有序数组之间已经有序则无需赋值,既然有序则没有上面的后推和遍历,
            //          即索引自减没执行过,因此
            if (insertIndex + 1 != i) {
                arr[insertIndex+1] = insertVal;
                System.out.print("该轮赋值了");
            }
            System.out.print("第"+i+"轮: ");
            print(arr);
        }

    }


    //等价于sort方法,只是for代替while循环,交换代替了插入的后推及赋值
    //后续的希尔排序就是基于这两种方法的优化
    private static void sort2(int[] arr) {
        int temp = 0;
        for (int i = 0; i < arr.length-1; i++) {
            for (int j = i; j >= 0; j--) {
                if (arr[j] > arr[j + 1]) {
                    temp = arr[j + 1];
                    arr[j + 1] = arr[j];
                    arr[j] = temp;
                }
            }
        }
        print(arr);

    }


}
