package com.zxk.algorithm.floyd;

import java.util.Arrays;

//弗洛伊德算法:最短路径问题
public class FloydAlgorithm {
    public static void main(String[] args) {
        //测试看看图是否创建成功
        char[] vertex = { 'A', 'B', 'C', 'D', 'E', 'F', 'G' };
        //创建邻接矩阵
        int[][] matrix = new int[vertex.length][vertex.length];
        final int N = 65535;
        matrix[0] = new int[] { 0, 5, 7, N, N, N, 2 };
        matrix[1] = new int[] { 5, 0, N, 9, N, N, 3 };
        matrix[2] = new int[] { 7, N, 0, N, 8, N, N };
        matrix[3] = new int[] { N, 9, N, 0, N, 4, N };
        matrix[4] = new int[] { N, N, 8, N, 0, 5, 4 };
        matrix[5] = new int[] { N, N, N, 4, 5, 0, 6 };
        matrix[6] = new int[] { 2, 3, N, N, 4, 6, 0 };
        Graph graph = new Graph(vertex, matrix);
        graph.floyd();
        graph.show();
    }
}

class Graph {
    private char[] vertex;
    private int[][] dis;
    private int[][] pre;

    public Graph(char[] vertex, int[][] matrix) {
        this.vertex = vertex;
        //初始化最短路径数组为邻接矩阵,直连就是最短路径,非直连则需要找
        this.dis = matrix;
        pre = new int[vertex.length][vertex.length];
        for (int i = 0; i < pre.length; i++) {
            //这里为什么如此初始化前驱节点?
            //答:因为默认开始只有两种路线:直连/不直连,两种情况下默认前驱节点都是出发点,即出发点到任何顶点的前驱节点都是自己
            //  至于后续的改动,不管最短路线改为非直连的两段还是三段,也只是针对不直连的路(即65535),
            //  而直连的两个顶点相关数据无需改动,前驱自然就是终点的前驱节点,即出发点
            Arrays.fill(pre[i], i);
        }
    }

    public void show() {
        for (int i = 0; i < vertex.length; i++) {
            for (int p : pre[i]) {
                System.out.print(vertex[p]+" ");
            }
            System.out.println();
            for (int j = 0; j < vertex.length; j++) {
                System.out.print("["+vertex[i]+"->"+vertex[j]+"="+dis[i][j]+"] ");
            }
            System.out.println("\n");
        }
    }

    public void floyd() {
        int len = 0;
        //问题:两顶点之间非直连,最短路径为3段的,最短路径是怎么更新的,如CD的最短路径为CEFD?
        //答:
        //  观察:虽然F作为中间点,但是测试时中间点第一个就用F,CD的距离和前驱都未能更新,仅更新了DE之间和DG之间数据,
        //      而当F之后的E作为中间点,CD之间数据才能更新,即CD的前驱为F,为什么会如此?
        //  注意:这是因为F作为中间点时,CEFD中存在65535,即CF间距此时还是65535所以CD无法更新,而E作为中间点时之前更新了ED=EF+FD
        //      所以CD=CE+ED,CD相关数据才得以更新
        //  结论:当MN两点之间最短路径需要经过n个顶点,那么只有当这n个中间点的相关数据全部更完,MN间的数据才能更新
        //      也可以说,想更新MN的数据,必须保证路径上没有65535
        int[] range = {5,4,3,2,1,0,6};
        for (int k : range) {
            //BC CG
            System.out.println(vertex[k] + "作为中间节点");
            for (int i = 0; i < vertex.length; i++) {
                for (int j = 0; j < vertex.length; j++) {
                    len = dis[i][k] + dis[k][j];
                    if (len < dis[i][j]) {
                        System.out.println("修改了:[" + vertex[i] + "-" + vertex[j] + "]");
                        dis[i][j] = len;
                        //直连的前驱(出发点)改为非直连后半段(最后一段)的前驱(出发点)
                        //举例:AE>AG+GE,所以A(pre[AE])=G(pre[GE])
                        pre[i][j] = pre[k][j];
                    }
                }
            }
        }
    }
}