package com.zxk.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

//图
public class Graph {
    public static void main(String[] args) {

        //测试
        int n = 5;
        Graph graph = new Graph(5);
        String[] vertexValue = {"A", "B", "C", "D", "E"};
        for (String s : vertexValue) {
            graph.insertVertex(s);
        }
        graph.insertEdge(1,0,1);
        graph.insertEdge(1,2,1);
        graph.insertEdge(1,3,1);
        graph.insertEdge(1,4,1);
        graph.insertEdge(2,0,1);
        graph.show();
        graph.dfs(0,new boolean[5]);
//        graph.bfs();
    }

    List<String> vertexes;
    int[][] edges;
    int edgesNum;

    public Graph(int n) {
        vertexes = new ArrayList<>(n);
        edges = new int[n][n];
        edgesNum = 0;
    }

    //封装调用bfs
    //虽是广度优先遍历,但考虑广度不够,因此起点有序调用dfs
    public void bfs() {
        boolean[] isVisited = new boolean[vertexes.size()];
        for (int i = 0; i < vertexes.size(); i++) {
            if (!isVisited[i]) {
                bfs(i,isVisited);
            }
        }
    }
    //广度优先遍历
    public void bfs(int index, boolean[] isVisited) {
        int u = 0;
        int w = 0;
        System.out.print(getValueByIndex(index) + "->");
        isVisited[index] = true;
        LinkedList<Integer> queue = new LinkedList<>();
        queue.addLast(index);

        while (!queue.isEmpty()) {
            //下一个节点的广度遍历
            u = queue.removeFirst();
            w = getFirstNeighbor(u);
            //遍历队列头的所有邻接节点
            while (w != -1) {
                if (!isVisited[w]) {
                    System.out.print(getValueByIndex(w) + "->");
                    isVisited[w] = true;
                    //填充队列
                    queue.addLast(w);
                }
                w = getNextNeighbor(u, w);
            }
        }
    }

    //封装调用dfs
    //虽是深度优先遍历,但考虑邻接中断(深度不够),因此起点有序调用dfs
    public void dfs() {
        boolean[] isVisited = new boolean[vertexes.size()];
        for (int i = 0; i < vertexes.size(); i++) {
            if (!isVisited[i]) {
                dfs(i,isVisited);
            }
        }
    }
    //深度优先遍历
    //对当前节点的处理(打印+已读)+向邻接结点递归
    public void dfs(int index, boolean[] isVisited) {
        System.out.print(getValueByIndex(index) + "->");
        isVisited[index] = true;
        int w = getFirstNeighbor(index);
        while (w != -1) {
            if (isVisited[w]) {
                w = getNextNeighbor(index, w);
            } else {
                dfs(w,isVisited);
            }
        }
    }
    //获取第一个邻接结点
    public int getFirstNeighbor(int index) {
        for (int i = 0; i < vertexes.size(); i++) {
            if (edges[index][i] > 0) {
                return i;
            }
        }
        return -1;
    }
    //获取下一个邻接节点
    public int getNextNeighbor(int v1, int v2) {
        for (int i = v2+1; i < vertexes.size(); i++) {
            if (edges[v1][i] > 0) {
                return i;
            }
        }
        return -1;
    }

    //插入节点
    public void insertVertex(String vertex) {
        vertexes.add(vertex);
    }

    //插入边
    public void insertEdge(int v1, int v2, int weight) {
        edges[v1][v2] = weight;
        edges[v2][v1] = weight;
        edgesNum++;
    }

    //返回节点数
    public int getVertexNum() {
        return vertexes.size();
    }

    //返回边个数
    public int getEdgesNum() {
        return edgesNum;
    }

    //返回节点
    public String getValueByIndex(int index) {
        return vertexes.get(index);
    }

    //返回边的权值
    public int getWeight(int v1, int v2) {
        return edges[v1][v2];
    }

    //显示图对应的矩阵
    public void show() {
        for (int[] edge : edges) {
            System.out.println(Arrays.toString(edge));
        }
    }
}
