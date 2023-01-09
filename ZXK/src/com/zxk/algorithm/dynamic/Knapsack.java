package com.zxk.algorithm.dynamic;

//动态规划:背包问题
public class Knapsack {
    public static void main(String[] args) {
        int[] w = {1, 4, 3};
        int[] val = {1500, 3000, 2000};
        int m = 3+1;
        int n = 4+1;
        int[][] v = new int[m][n];
        int[][] path = new int[m][n];

        //填表
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                //考虑到v数组的0容量和无物品可放的情况,所以使用w和val数组时需要索引-1
                if (w[i - 1] > j) {
                    v[i][j] = v[i - 1][j];
                } else {
                    //v[i-1][j-w[i-1]]  含义:j-w[i-1]表示可用容量,而第一个i-1表示第i个物品已被使用,所以i-1
                    //v[i][j] = Math.max(v[i - 1][j],val[i-1]+v[i-1][j-w[i-1]]);
                    if (v[i - 1][j] < val[i - 1] + v[i - 1][j - w[i - 1]]) {
                        v[i][j] = val[i-1]+v[i-1][j-w[i-1]];
                        path[i][j] = 1;
                    } else {
                        v[i][j] = v[i - 1][j];
                    }
                }
            }
        }

        //表示从表的最后一列从下往上查看是否放入
        int p = m-1;
        int q = n-1;
        while (p > 0 && q > 0) {
            if (path[p][q] == 1) {
                System.out.println("放入第"+p+"个物品");
                //放入一件则扣除容量
                q -= w[p - 1];
            }
            //寻找可放入的物品
            p--;
        }

        for (int i = 0; i <v.length; i++) {
            for (int j = 0; j < v[i].length; j++) {
                System.out.print(v[i][j]+"\t");
            }
            System.out.println();
        }
    }
}
