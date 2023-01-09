package com.zxk.algorithm.horse;

import java.awt.*;
import java.util.ArrayList;

//骑士周游
public class HorseChessboard {
    private static int X;//列数
    private static int Y;//行数
    private static boolean visited[];
    private static boolean finished;

    public static void main(String[] args) {
        X =6;
        Y =6;
        int row = 0; //马儿初始位置的行，从 1 开始编号
        int column = 1; //马儿初始位置的列，从 1 开始编号
        //创建棋盘
        int[][] chessboard = new int[X][Y];
        visited = new boolean[X * Y];//初始值都是 false

        travel(chessboard,row,column,1);
        //输出棋盘的最后情况
        for(int[] rows : chessboard) {
            for(int step: rows) {
                System.out.print(step + "\t");
            }
            System.out.println();
        }
    }

    //周游
    public static void travel(int[][] chessboard, int row, int column, int step) {
        if (step == X * Y) {
            chessboard[row][column] = step;
            visited[row * X + column] = true;
            for(int[] rows : chessboard) {
                for(int r: rows) {
                    System.out.print(r + "\t");
                }
                System.out.println();
            }
            return;
        }

        //1行2列,chessboard[1][2],visited[10],point(2,1)
        chessboard[row][column] = step;
        visited[row * X + column] = true;
        ArrayList<Point> ps = next(new Point(column,row));
        for (Point p : ps) {
            if (!visited[p.y * X + p.x]) {
//                chessboard[p.y][p.x] = step;
//                visited[p.y * X + p.x] = true;
                travel(chessboard,p.y,p.x,step+1);
                chessboard[p.y][p.x] = 0;
                visited[p.y * X + p.x] = false;
            }
        }

//        chessboard[row][column] = step;
//        visited[row * X + column] = true; //标记该位置已经访问
//        ArrayList<Point> ps = next(new Point(column, row));
//        while(!ps.isEmpty()) {
//            Point p = ps.remove(0);//取出下一个可以走的位置
//            if(!visited[p.y * X + p.x]) {//说明还没有访问过
//                travel(chessboard, p.y, p.x, step + 1);
//            }
//        }
//        if(step < X * Y && !finished ) {
//            chessboard[row][column] = 0;
//            visited[row * X + column] = false;
//        } else {
//            finished = true;
//        }
    }

    //由当前位置,得到可以走的下一步集合
    public static ArrayList<Point> next(Point curPoint) {
        ArrayList<Point> ps = new ArrayList<>();
        Point p1 = new Point();

        //表示马儿可以走 5 这个位置
        if((p1.x = curPoint.x - 2) >= 0 && (p1.y = curPoint.y -1) >= 0) {
            ps.add(new Point(p1));
        }
        //判断马儿可以走 6 这个位置
        if((p1.x = curPoint.x - 1) >=0 && (p1.y=curPoint.y-2)>=0) {
            ps.add(new Point(p1));
        }
        //判断马儿可以走 7 这个位置
        if ((p1.x = curPoint.x + 1) < X && (p1.y = curPoint.y - 2) >= 0) {
            ps.add(new Point(p1));
        }
        //判断马儿可以走 0 这个位置
        if ((p1.x = curPoint.x + 2) < X && (p1.y = curPoint.y - 1) >= 0) {
            ps.add(new Point(p1));
        }
        //判断马儿可以走 1 这个位置
        if ((p1.x = curPoint.x + 2) < X && (p1.y = curPoint.y + 1) < Y) {
            ps.add(new Point(p1));
        }
        //判断马儿可以走 2 这个位置
        if ((p1.x = curPoint.x + 1) < X && (p1.y = curPoint.y + 2) < Y) {
            ps.add(new Point(p1));
        }
        //判断马儿可以走 3 这个位置
        if ((p1.x = curPoint.x - 1) >= 0 && (p1.y = curPoint.y + 2) < Y) {
            ps.add(new Point(p1));
        }
        //判断马儿可以走 4 这个位置
        if ((p1.x = curPoint.x - 2) >= 0 && (p1.y = curPoint.y + 1) < Y) {
            ps.add(new Point(p1));
        }
        return ps;
    }
}
