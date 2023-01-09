package com.zxk.algorithm.prim;

import java.util.Arrays;

//普利姆算法:修路问题,最小生成树
public class PrimAlgorithm {
    public static void main(String[] args) {
        char[] data = new char[]{'A','B','C','D','E','F','G'};
        int verxs = data.length;
        //邻接矩阵的关系使用二维数组表示,10000 这个大数，表示两个点不联通
        int[][] weight = new int[][]{
                {10000, 5, 7, 10000, 10000, 10000, 2}, {5, 10000, 10000, 9, 10000, 10000, 3}, {7, 10000, 10000, 10000, 8, 10000, 10000}, {10000, 9, 10000, 10000, 10000, 4, 10000}, {10000, 10000, 8, 10000, 10000, 5, 4}, {10000, 10000, 10000, 4, 5, 10000, 6}, {2, 3, 10000, 10000, 4, 6, 10000},};
        MinTree tree = new MinTree();
        MGraph mGraph = new MGraph(verxs);
        tree.createGraph(mGraph, verxs, data, weight);
        tree.showGraph(mGraph);
        tree.prim(mGraph,0);
    }
}

//最小生成树
class MinTree {
    //构建邻接矩阵
    public void createGraph(MGraph mGraph, int verxs, char[] data, int[][] weight) {
        for (int i = 0; i < verxs; i++) {
            mGraph.data[i] = data[i];
            for (int j = 0; j < verxs; j++) {
                mGraph.weight[i][j] = weight[i][j];
            }
        }
    }
    //展示邻接矩阵
    public void showGraph(MGraph mGraph) {
        for (int i = 0; i < mGraph.verxs; i++) {
            System.out.println(Arrays.toString(mGraph.weight[i]));
        }
    }

    //得到最小生成树
    public void prim(MGraph graph, int v) {
        //数组标记经过的顶点
        int[] visited = new int[graph.verxs];
        visited[v] = 1;

        //两个指针存放经过的顶点,和周围未经过的顶点
        int h1 = -1;
        int h2 = -1;
        //n和顶点需要n-1条路连接,因此需要修n-1轮路
        for (int k = 1; k < graph.verxs; k++) {

            //存放本轮的最短修路长
            int minWeight = 10000;
            for (int i = 0; i < graph.verxs; i++) {
                for (int j = 0; j < graph.verxs; j++) {
                    //围绕经过的顶点和周围未经过的顶点找最短路径
                    if (visited[i] == 1 && visited[j] == 0 && graph.weight[i][j] < minWeight) {
                        minWeight = graph.weight[i][j];
                        h1 = i;
                        h2 = j;
                    }
                }
            }
            //标记通路的新顶点为已读
            visited[h2] = 1;
            System.out.println("第"+k+"轮修路"+graph.data[h1]+"-"+graph.data[h2]+"路长"+minWeight);
        }
    }
}

//图
class MGraph {
    int verxs;//顶点数
    char[] data;//顶点集
    int[][] weight;//邻接矩阵

    public MGraph(int verxs) {
        this.verxs = verxs;
        data = new char[verxs];
        weight = new int[verxs][verxs];
    }
}