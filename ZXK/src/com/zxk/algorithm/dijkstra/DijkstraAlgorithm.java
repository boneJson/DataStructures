package com.zxk.algorithm.dijkstra;

import java.util.*;

//迪杰斯特拉(Dijkstra)算法最佳应用-最短路径
public class DijkstraAlgorithm {
    public static void main(String[] args) {
        char[] vertex = { 'A', 'B', 'C', 'D', 'E', 'F', 'G' };
        //邻接矩阵
        int[][] matrix = new int[vertex.length][vertex.length];
        final int N = 65535;// 表示不可以连接
        matrix[0]=new int[]{N,5,7,N,N,N,2};
        matrix[1]=new int[]{5,N,N,9,N,N,3};
        matrix[2]=new int[]{7,N,N,N,8,N,N};
        matrix[3]=new int[]{N,9,N,N,N,4,N};
        matrix[4]=new int[]{N,N,8,N,N,5,4};
        matrix[5]=new int[]{N,N,N,4,5,N,6};
        matrix[6]=new int[]{2,3,N,N,4,6,N};
        Graph graph = new Graph(vertex, matrix);
        graph.showGraph();
        graph.djs(2);
    }
}

class Graph {
    private char[] vertex;
    private int[][] matrix;

    public Graph(char[] vertex, int[][] matrix) {
        this.vertex = vertex;
        this.matrix = matrix;
    }

    public void showGraph() {
        for (int[] ints : matrix) {
            System.out.println(Arrays.toString(ints));
        }
    }

    //dijkstra算法
    public void djs(int index) {
        //出发点为index
        VisitedVertex vv = new VisitedVertex(vertex.length, index);
        //更新出发点周围顶点的前驱,以及到周围顶点的距离,ABEF可联通
        update(index,vv);
        //分别走访可联通顶点,谁近先访问,ABEF
        //第一轮因为ABEF距离更新了且未访问所以访问ABEF,update方法导致出发点到CD的距离更新,所以第二轮访问CD
        for (int i = 1; i < vertex.length; i++) {
            //访问未读且可联通(dis<65535)的顶点
            int temp = vv.updateArr();
            update(temp,vv);
        }

        //输出结果
        vv.show();
    }

    //更新index顶点到周围顶点的距离以及更新周围顶点的前驱节点
    //注意:更新了距离的下一步才是访问,这里先更新距离才表示哪些顶点可联通,为访问做好准备
    public void update(int index, VisitedVertex vv) {
        int len = 0;
        for (int i = 0; i < matrix[index].length; i++) {
            len = vv.getDis(index) + matrix[index][i];
            if (!vv.isVisited(i) && len < vv.getDis(i)) {
                vv.update(i, len);
                vv.updatePre(i,index);
            }
        }
    }
}

//已访问顶点集合
class VisitedVertex {
    public int[] already_arr;//是否访问过
    public int[] pre_visited;//前驱节点
    public int[] dis;//出发点到其他顶点的距离

    //构造器:初始化数组+出发点在各数组中的值
    public VisitedVertex(int length, int index) {
        already_arr = new int[length];
        pre_visited = new int[length];
        dis = new int[length];
        Arrays.fill(dis, 65535);
        Arrays.fill(pre_visited, -1);
        dis[index] = 0;
        already_arr[index] = 1;
    }

    //是否访问过
    public boolean isVisited(int index) {
        return already_arr[index] == 1;
    }

    //更新出发点到index的距离
    public void update(int index, int length) {
        dis[index] = length;
    }
    //返回距离
    public int getDis(int index) {
        return dis[index];
    }

    //更新前驱节点
    public void updatePre(int index, int pre) {
        pre_visited[index] = pre;
    }

    //访问未读但可联通(dis<65535)的顶点
    public int updateArr() {
        int min = 65535;
        int index = -1;
        for (int i = 0; i < already_arr.length; i++) {
            if (already_arr[i] == 0 && dis[i] < min) {
                min = dis[i];
                index = i;
            }
        }
        already_arr[index] = 1;
        return index;
    }


    //输出最后结果
    //说明:这里出发点的前驱应做特殊处理,通过前驱数组可展示出发点到各顶点的最短路线
    public void show() {
        System.out.println();
        //最短距离
        char[] vertex = { 'A', 'B', 'C', 'D', 'E', 'F', 'G' };
        int count = 0;
        for (int i : dis) {
            if (i != 65535) {
                System.out.print(vertex[count] + "("+i+") ");
            } else {
                System.out.println("N ");
            }
            count++;
        }

        //最短路线
        System.out.println();
        LinkedList<String> res = new LinkedList<>();
        for (int i = 0; i < pre_visited.length; i++) {
            res.addFirst("] ");
            int j = i;
            while (true) {
                res.addFirst("->"+vertex[j] );
                j = pre_visited[j];
                if (j == -1) {
                    break;
                }
            }
            res.addFirst("[");
        }
        for (String str : res) {
            System.out.print(str);
        }
    }
}