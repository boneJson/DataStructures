package com.zxk.algorithm.dac;

//分治思想:汉诺塔
public class HanoiTower {
    public static void main(String[] args) {
        hanoi(5,'A','B','C');
    }

    /**
     *
     * @param num 移动目标数
     * @param a 起始柱
     * @param b 辅助柱
     * @param c 目标柱
     */
    //抽离目标的最大盘就是在往终止条件逼近,只是起始柱和目标柱在不断变化
    public static void hanoi(int num, char a, char b, char c) {
        if (num == 1) {
            System.out.println("第" + num + "个盘从"+a+"->"+c);
        } else {
            hanoi(num - 1, a, c, b);
            System.out.println("第" + num + "个盘从"+a+"->"+c);
            hanoi(num - 1, b, a, c);
        }
    }
}
