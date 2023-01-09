package com.zxk.sort;

import java.util.Arrays;

//快速排序,以左边界作为基准值
public class Sort05_QuickSort {
    public static void main(String[] args) {
        int[] arr = {0,78,0,  9 ,-23,-566,-567,70     };
        sort(arr,0, arr.length-1);
        print(arr);

    }

    public static void print(int[] arr) {
        System.out.println(Arrays.toString(arr));
    }

    //问题1:理想条件下,基准值两边交换完后,刚好剩一个小的与基准值交换,如果不剩呢
    //  退出while前r指针做了自减,所以指向的一定是左子组的数据
    //问题2:如果只有一侧数据呢,怎么交换
    //  若只有右组数据,则r最终指向上界即自己换自己,若只有左组数据,l和r在下界相交,只需交换一次基准值即可
    //问题3:分组结束的状况是怎么样的,也就是最底层和上两层的情况举例
    //  最底层区间只有一个元素,上层则2/3个
    //函数功能:对数组的某一区间进行排序
    private static void sort(int[] arr,int lo ,int hi) {

        //安全性校验:保证区间内不止一个元素
        if (lo >= hi) {
            return;
        }

        //分组:将区间元素(按序)分为左右子组,并拿到基准值最终的角标
        //左右指针最初为区间上界和下界+1,即待分组元素的边界外,开始相向移动
        int l = lo;
        int r = hi+1;
        int temp = 0;
        int pivot = arr[lo];//基准值

        while (true) {
            //右指针向左移,遇到左元素(基准值)停下,或遇到区间上界停下
            //说明:如果指针遇到等基准值,则交换后等基准值分到哪一组都不影响正确性
            while (arr[--r] > pivot) {
                if (r == lo) {
                    break;
                }
            }
            while (arr[++l] < pivot) {
                if (l == hi) {
                    break;
                }
            }
            //左右指针相交,说明交换完毕/只有一侧有数据,需退出,否则持续交换
            //至于最终l==r还是l>r,看谁先找,以及是否能找到,结果复杂不好判断
            if (l >= r) {
                break;
            } else {
                temp = arr[l];
                arr[l] = arr[r];
                arr[r] = temp;
            }
        }
        //使用r作为基准值角标是,因为r最终指向左子组元素
        //当然r也可能是lo上界(本身有序没有交换操作),因此可做优化if(lo===r)
        temp = arr[lo];
        arr[lo] = arr[r];
        arr[r] = temp;

        //左右子组排序
        sort(arr, lo, r-1);
        sort(arr, r+1, hi);






    }
}