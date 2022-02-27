package com.zxk.queue;

import java.util.Scanner;

/**
 * @ClassName
 * @Description
 * @Author zxk
 * @DateTime 2022-02-17-0:36
 * @Version 数组模拟队列
 */
public class ArrayQueueDemo {
    public static void main(String[] args) {
        ArrayQueue arrayQueue = new ArrayQueue(3);
        char key = ' ';
        Scanner sc = new Scanner(System.in);
        boolean loop = true;
        while (loop) {//菜单
            System.out.println("s(show):显示队列");
            System.out.println("e(exit):退出程序");
            System.out.println("a(add):添加数据到队列");
            System.out.println("g(get):取出数据");
            System.out.println("h(head):查看队列头部数据");
            System.out.println("请输入指令:");
            key = sc.next().charAt(0);
            switch (key) {
                case 's':
                    arrayQueue.show();
                    break;
                case 'e':
                    loop = false;
                    break;
                case 'a':
                    System.out.println("输入一个数");
                    int value = sc.nextInt();
                    arrayQueue.add(value);
                    break;
                case 'g':
                    try {
                        int num = arrayQueue.get();
                        System.out.println("取出的数为:"+num);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case 'h':
                    try {
                        int head = arrayQueue.head();
                        System.out.println("头部为:"+head);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                default:
                    break;
            }
        }
        System.out.println("程序退出~");
    }
}

//提示:指针只有在添加数据或取出数据时才后移
class ArrayQueue {
    private int maxSize;//容量
    private int rear;//尾
    private int front;//头
    private int[] arr;//

    public ArrayQueue(int num) {
        rear = -1;
        front = -1;
        maxSize = num;
        arr = new int[maxSize];

    }
    //判断数组已满
    public boolean isFull() {
        return rear==maxSize-1;
    }
    //判断数组为空
    public boolean isEmpty() {
        return front==rear;
    }
    //添加方法
    public void add(int num) {
        if (isFull()) {
            System.out.println("满了,添加失败");
            return;
        }
        rear++;
        arr[rear]=num;
    }
    //取出方法
    public int get() throws Exception {
        if (isEmpty()) {
            System.out.println("空的,没法取出");
            throw new Exception("数组为空,无法取出");
        }
        front++;
        return arr[front];
    }
    //遍历方法
    public void show() {
        if (isEmpty()) {
            System.out.println("数组为空");
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.println("arr[" + i+"]=" + arr[i]);
        }
    }
    //显示头部数据方法
    public int head() throws Exception {
        if (isEmpty()) {
            throw new Exception("数组为空");
        }
        return arr[front+1];
    }
}