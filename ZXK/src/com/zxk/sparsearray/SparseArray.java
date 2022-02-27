package com.zxk.sparsearray;

import java.io.*;

/**
 * @ClassName
 * @Description
 * @Author zxk
 * @DateTime 2022-02-15-23:58
 * @Version 稀疏数组
 */
public class SparseArray {
    public static void main(String[] args) {
        int[][] chessArr1=new int[11][11];
        //此处添加棋子的坐标即数组元素
        chessArr1[1][2]=1;
        chessArr1[2][3]=2;
        chessArr1[5][6]=1;

        //遍历原始二维数组
        for (int[] row : chessArr1) {
            for (int i : row) {//打印行
                System.out.print("\t"+i);
            }
            System.out.println();//换行
        }



        //声明稀疏数组
        int count=0;
        for (int i = 0; i < chessArr1.length; i++) {
            for (int j = 0; j < chessArr1[i].length; j++) {
                if (chessArr1[i][j] != 0) {
                    count++;
                }
            }
        }
        System.out.println();
        int[][] sparseArr=new int[count+1][3];
        //初始化稀疏数组
        sparseArr[0][0] = 11;//row
        sparseArr[0][1] = 11;//column
        sparseArr[0][2]=count;//count
        int num=0;
        for (int i = 0; i < chessArr1.length; i++) {
            for (int j = 0; j < chessArr1[i].length; j++) {
                if (chessArr1[i][j] != 0) {
                    num++;
                    sparseArr[num][0]=i;
                    sparseArr[num][1]=j;
                    sparseArr[num][2]=chessArr1[i][j];
                }
            }
        }
        //遍历稀疏数组
        System.out.println("得到稀疏数组:");
        for (int[] row : sparseArr) {
            for (int i : row) {//打印行
                System.out.print("\t"+i);
            }
            System.out.println();//换行
        }

        //调用存盘
        keep(sparseArr);
        //调用读取
        int[][] backSparseArr=get();

        //恢复后的稀疏数组
        System.out.println("恢复后的稀疏数组:");
        for (int[] row : backSparseArr) {
            for (int i : row) {//打印行
                System.out.print("\t"+i);
            }
            System.out.println();//换行
        }

        ///将稀疏数组 --》 恢复成 原始的二维数组
        int[][] chessArr02=new int[backSparseArr[0][0]][backSparseArr[0][1]];
        for (int i = 1; i < backSparseArr.length; i++) {
            chessArr02[backSparseArr[i][0]][backSparseArr[i][1]]=backSparseArr[i][2];
        }
        //遍历恢复后的二维数组
        System.out.println("由稀疏数组恢复后的二维数组:");
        for (int[] row : chessArr02) {
            for (int i : row) {//打印行
                System.out.print("\t"+i);
            }
            System.out.println();//换行
        }
    }

    //写入方法
    public static void keep(int[][] arr) {
        BufferedWriter bw=null;
        try {
            bw = new BufferedWriter(new FileWriter("f:\\io\\sparseArr.txt"));
            bw.write(""+arr[0][2]);//存入原始二维数组非零个数,待恢复稀疏数组时声明行数时使用
            bw.newLine();
            for (int i = 0; i < arr.length; i++) {
                bw.write(arr[i][0] + " " + arr[i][1] + " " + arr[i][2]);//写入每行元素
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bw != null) {
                    bw.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //读取方法
    public static int[][] get() {
        String data;
        BufferedReader br=null;

        int row=0;//用于表示数组行数但并非行数
        int count=0;//表示读到第几行
        int[][] arr=null;
        try {
            br = new BufferedReader(new FileReader("f:\\io\\sparseArr.txt"));
            row=Integer.parseInt(br.readLine());//数组非零个数用于表示行数
            arr=new int[row+1][3];
            while ((data = br.readLine()) != null) {
                String[] s = data.split(" ");
                arr[count][0]=Integer.parseInt(s[0]);
                arr[count][1]=Integer.parseInt(s[1]);
                arr[count][2]=Integer.parseInt(s[2]);
                count++;//刷新数组的行数

            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return arr;
    }
}
