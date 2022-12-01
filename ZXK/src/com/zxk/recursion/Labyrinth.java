package com.zxk.recursion;

//迷宫回溯
public class Labyrinth {
    private static int count = 0;//路径距离
    public static void main(String[] args) {
        int[][] map = new int[8][7];
        //横墙
        for (int i = 0; i < 7; i++) {
            map[0][i] = 1;
            map[7][i] = 1;
        }
        //竖墙
        for (int i = 0; i < 8; i++) {
            map[i][0] = 1;
            map[i][6] = 1;
        }
        //挡板
        map[3][1] = 1;
        map[3][2] = 1;

        setWay(map, 1, 1);


        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 7; j++) {
                System.out.print(" "+map[i][j]);
            }
            System.out.println();
        }

        System.out.println(count);
    }

    //递归方法:寻找出口
    public static boolean setWay(int[][] map, int i, int j) {
        if (map[6][5] == 2) {//找到出口,递归结束
            return true;
        } else {//未找到出口,递归探路
            if (map[i][j] == 0) {//该点未走过
                map[i][j] = 2;//标记走过
                count++;
                if (setWay(map, i + 1, j)) {//四个方向探路
                    return true;
                }else if (setWay(map, i , j+1)) {
                    return true;
                }else if (setWay(map, i - 1, j)) {
                    return true;
                }else if (setWay(map, i, j - 1)) {
                    return true;
                } else {//怎么探都是死路
                    map[i][j] = 3;//标记死路
                    return false;
                }
            } else {//该点走过,被标记为1墙/2回溯/3死路
                return false;
            }
        }
    }
}
