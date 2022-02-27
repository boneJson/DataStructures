package com.zxk.queue;

import java.util.Scanner;

/**
 * @ClassName
 * @Description
 * @Author zxk
 * @DateTime 2022-02-18-1:21
 * @Version
 *///数组模拟环形队列
@SuppressWarnings({"all"})
public class CircleQueueDemo {
    public static void main(String[] args) {
        CircleQueue circleQueue = new CircleQueue(3);
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
                    circleQueue.show();
                    break;
                case 'e':
                    loop = false;
                    break;
                case 'a':
                    System.out.println("输入一个数");
                    int value = sc.nextInt();
                    circleQueue.add(value);
                    break;
                case 'g':
                    try {
                        int num = circleQueue.get();
                        System.out.println("取出的数为:"+num);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case 'h':
                    try {
                        int head = circleQueue.head();
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
class CircleQueue {
    private int maxSize;//容量
    private int rear;//尾
    private int front;//头
    private int[] arr;//

    public CircleQueue(int num) {
        maxSize = num;
        arr = new int[maxSize];

    }
    //判断数组已满
    public boolean isFull() {
        return (rear+1)%maxSize==front;
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
        arr[rear] = num;
        rear = (rear + 1) % maxSize;
    }
    //取出方法
    public int get() throws Exception {
        if (isEmpty()) {
            System.out.println("空的,没法取出");
            throw new Exception("数组为空,无法取出");
        }
        int value= arr[front];
        front = (front + 1) % maxSize;
        return value;
    }
    //遍历方法
    public void show() {
        if (isEmpty()) {
            System.out.println("数组为空");
            return;
        }
        for (int i = front; i < front+(rear+maxSize-front)%maxSize; i++) {
            System.out.println("arr[" + i%maxSize+"]=" + arr[i%maxSize]);
        }
    }
    //显示头部数据方法
    public int head() throws Exception {
        if (isEmpty()) {
            throw new Exception("数组为空");
        }
        return arr[front];
    }
}