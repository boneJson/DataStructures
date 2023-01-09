package com.zxk.recursion;

import java.util.ArrayList;

//迷宫回溯复习
public class ShortestPath {
    static int[][] map = new int[8][7];
    ArrayList<Character> path = new ArrayList<Character>();
    public static void main(String[] args) {
        ShortestPath sp = new ShortestPath();
        sp.dec();


        //回溯
        sp.setWay(map,5,5);



    }

    //布置宫墙
    public void dec() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 7; j++) {
                if (i == 0 || i == 7 || j == 0 || j == 6||(i == 3 && (j == 1 || j == 2))) {
                    map[i][j]=1;
                }
            }
        }
        map[2][2] = 1;
    }

    //查看轨迹
    public void print() {
        int count=0;
        //查看路径
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 7; j++) {
                System.out.print(map[i][j]+" ");
                if (map[i][j] == 2) {
                    count++;
                }
            }
            System.out.println();
        }
        System.out.println(count);
    }

    //探路方法:先检验后赋值
    public void setWay(int[][] map, int i, int j) {

        //终止
        if (map[6][5] == 2) {
            print();
            return ;
        //踏中宫,探路
        } else {
            if (map[i][j] == 0) {
                map[i][j] = 2;
            }
            setWay(map,i+1,j);
            setWay(map,i,j+1);
            setWay(map,i-1,j);
            setWay(map,i,j-1);

        }

    }
}
