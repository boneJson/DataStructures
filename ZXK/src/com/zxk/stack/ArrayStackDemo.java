package com.zxk.stack;

import java.util.Scanner;
//数组模拟栈
public class ArrayStackDemo {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println();
        String key = "";
        boolean loop = true;
        ArrayStack stack = new ArrayStack(4);
        while (loop) {
            System.out.println("show:显示栈");
            System.out.println("push:压栈");
            System.out.println("pop:出栈");
            System.out.println("exit:退出程序");
            System.out.println("请输入你的选择:");
            key = sc.next();
            switch (key) {
                case "show":
                    stack.list();
                    break;
                case "push":
                    System.out.println("请输入压栈的数字:");
                    int value = sc.nextInt();
                    stack.push(value);
                    break;
                case "pop":
                    try {
                        System.out.println("出栈数字为:"+stack.pop());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case "exit":
                    loop = false;
                    break;
                default:
                    System.out.println("你会不会啊~");
                    break;
            }
        }
    }

}

class ArrayStack {
    private int maxSize;
    private int top=-1;
    private int[] array;

    public ArrayStack(int maxSize) {
        this.maxSize = maxSize;
        array = new int[maxSize];
    }

    public void push(int num) {
        if (isFull()) {
            System.out.println("栈满");
            return;
        }
        top++;
        array[top] = num;
    }

    public int pop() {
        if (isEmpty()) {
            throw new RuntimeException("栈空");
        }
        int num = array[top];
        top--;
        return num;
    }

    public void list() {
        if (isEmpty()) {
            System.out.println("栈空");
            return;
        }
        for (int i = top; i >=0 ; i--) {
            System.out.println(array[i]);
        }
    }

    public boolean isEmpty() {
        return top == -1;
    }

    public boolean isFull() {
        return top == maxSize - 1;
    }

}