package com.zxk.recursion;

//八皇后问题
public class Queen {

    //索引代表行,值代表列
    private int[] array = new int[8];
    //统计判断了多少次
    private static int sum=0;
    //统计有多少种正确的摆放方法
    private static int count = 0;

    public static void main(String[] args) {
        Queen queen = new Queen();
        queen.check(0);
        System.out.println("一共判断了"+sum+"次冲突次数");
        System.out.println("最终得到八皇后问题的"+count+"种正确摆法");
    }

    //摆放第n个皇后
    public void check(int n) {
        //第八个皇后摆完,递归终止
        if (n == 8) {
            print();
            count++;
            return;
        }

        //尝试一行的八个位置
        for (int i = 0; i < 8; i++) {
            array[n] = i;
            if (judge(n)) {
                check(n+1);
            }
        }

    }



    //判断该摆放方式是否符合规则
    public boolean judge(int n) {
        sum++;
        for (int i = 0; i < n; i++) {
            //判断该方式不可行:同行||同一斜线
            if (array[i] == array[n] || Math.abs(n - i) == Math.abs(array[n] - array[i])) {
                return false;
            }
        }
        return true;
    }

    public void print() {
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i]+" ");
        }
        System.out.println();
    }
}
