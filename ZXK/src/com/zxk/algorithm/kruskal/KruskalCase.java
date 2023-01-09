package com.zxk.algorithm.kruskal;

import java.util.Arrays;

//克鲁斯卡尔算法:公交站问题,最小生成树
public class KruskalCase {
    private int edgeNum; //边的个数
    private char[] vertexs; //顶点数组
    private int[][] matrix; //邻接矩阵
    //使用 INF 表示两个顶点不能连通
    private static final int INF = Integer.MAX_VALUE;

    public static void main(String[] args) {
        char[] vertexs = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        //克鲁斯卡尔算法的邻接矩阵
        int matrix[][] = {
                /*A*//*B*//*C*//*D*//*E*//*F*//*G*/
                /*A*/ {0, 12, INF, INF, INF, 16, 14},
                /*B*/ {12, 0, 10, INF, INF, 7, INF},
                /*C*/ {INF, 10, 0, 3, 5, 6, INF},
                /*D*/ {INF, INF, 3, 0, 4, INF, INF},
                /*E*/ {INF, INF, 5, 4, 0, 2, 8},
                /*F*/ {16, 7, 6, INF, 2, 0, 9},
                /*G*/ {14, INF, INF, INF, 8, 9, 0}};
        KruskalCase tree = new KruskalCase(vertexs, matrix);
        tree.print();
        tree.kruskal();

    }

    //构造器
    public KruskalCase(char[] vertexs, int[][] matrix) {
        this.vertexs = vertexs;
        this.matrix = matrix;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = i + 1; j < matrix[i].length; j++) {
                if (matrix[i][j] != INF) {
                    edgeNum++;
                }
            }
        }
    }

    //重点
    public void kruskal() {
        //获得所有边并排序
        EData[] edges = getEdges();
        sortEdges(edges);

        //结果集
        int index = 0;
        EData[] res = new EData[vertexs.length - 1];

        //存放的并不一定是终点,很有可能是后继节点的索引,而数组本身的索引就对应顶点
        int[] ends = new int[vertexs.length];
        Arrays.fill(ends, -1);

        for (int i = 0; i < edges.length; i++) {
            //边的两端顶点
            int start = getPosition(edges[i].start);//4
            int end = getPosition(edges[i].end);//5

            //两个顶点的终点
            int m = getEnd(ends, start);//4
            int n = getEnd(ends, end);//5

            //为什么这里用m和n而不用start和end?m替换成start也不行吗?
            //1.因为后者的方式会导致ends的元素值被覆盖,导致判断错误,结果错误,结果集索引越界
            //  比如修eg时,end[4]的值由5改为g的下标6,导致之前的cde的终点f改为g,导致错修fg
            //  其实这里的终点也并不是路的尽头,而是路上所有顶点中(下标)最大的
            //2.本操作说简单点,就是让新的终点放在旧的终点的后面,
            //  用start代替m,同样会导致1中的问题,
            //  end数组中的轨迹并不是路的轨迹,而是从曾经的终点跳到另一个新的终点,比如end[3]=5
            if (m != n) {
                res[index++] = edges[i];
                ends[m] = n;
            }
        }
        System.out.println(Arrays.toString(res));
    }

    //打印邻接矩阵
    public void print() {
        for (int[] ints : matrix) {
            for (int i : ints) {
                System.out.printf("%12d", i);
            }
            System.out.println();
        }
    }

    //对边数组冒泡排序
    public void sortEdges(EData[] edges) {
        EData temp = null;
        for (int i = 0; i < edges.length - 1; i++) {
            for (int j = 0; j < edges.length - i - 1; j++) {
                if (edges[j].weight > edges[j + 1].weight) {
                    temp = edges[j];
                    edges[j] = edges[j + 1];
                    edges[j + 1] = temp;
                }
            }
        }
    }

    //返回顶点下标
    public int getPosition(char ch) {
        for (int i = 0; i < vertexs.length; i++) {
            if (ch == vertexs[i]) {
                return i;
            }
        }
        return -1;
    }

    //收集图中所有边
    public EData[] getEdges() {
        EData[] edges = new EData[edgeNum];
        int index = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = i + 1; j < matrix[i].length; j++) {
                if (matrix[i][j] != INF) {
                    edges[index++] = new EData(vertexs[i], vertexs[j], matrix[i][j]);
                }
            }
        }
        return edges;
    }

    //获取顶点的终点下标,这里初始化ends为元素-1的数组而不是0避免与顶点a同义
    //注意:ends数组虽然存放的可能只是后继节点/终点,但genEnd方法得到的一定是终点(自己也算)
    public int getEnd(int[] ends, int i) {
        //我理解这里ends存放的并不一定是终点,很有可能是后继节点的索引,而数组本身的索引就对应顶点
        while (ends[i] != -1) {
            i = ends[i];
        }
        return i;
    }
}
//边对象
class EData {
    char start;
    char end;
    int weight;

    public EData(char start, char end, int weight) {
        this.start = start;
        this.end = end;
        this.weight = weight;
    }

    //重写 toString, 便于输出边信息
    @Override
    public String toString() {
        return "EData [<" + start + ", " + end + ">= " + weight + "]";
    }
}