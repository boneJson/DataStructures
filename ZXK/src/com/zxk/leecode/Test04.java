package com.zxk.leecode;

import java.util.*;

//给你一个整数 n ，表示一个国家里的城市数目。城市编号为 0 到 n - 1 。
//
//给你一个二维整数数组 roads ，其中 roads[i] = [ai, bi] 表示城市 ai 和 bi 之间有一条 双向 道路。
//
//你需要给每个城市安排一个从 1 到 n 之间的整数值，且每个值只能被使用 一次 。道路的 重要性 定义为这条道路连接的两座城市数值 之和 。
//
//请你返回在最优安排下，所有道路重要性 之和 最大 为多少。
public class Test04 {
    public static void main(String[] args) {
        int n = 5;
        int[][] roads = {{0, 3}, {2, 4}, {1, 3}};
        System.out.println(new Test04().maximumImportance(n, roads));
    }

    public long maximumImportance(int n, int[][] roads) {
        long[] values = new long[n];
        for (int[] road : roads) {
            values[road[0]]++;
            values[road[1]]++;
        }

        Arrays.sort(values);
        long result=0L;
        for (int i = 0; i < n; i++) {
            result += values[i] * (i+1);
        }

        return result;
    }
}
