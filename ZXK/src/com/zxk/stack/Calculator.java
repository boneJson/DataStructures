package com.zxk.stack;

import java.util.Scanner;
//使用模拟栈计算中缀表达式(不带括号)
public class Calculator {
    public static void main(String[] args) {
        Stack operas = new Stack(10);//数字栈:存放数字的解析后的值
        Stack nums = new Stack(10);//运算符栈:存放运算符对应 ascll码值

        String cal = "";//接收输入的表达式字符串
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入表达式:");
        cal = scanner.next();

        int index = 0;//用于遍历表达式字符串的索引
        char ch;//指向扫描到的字符
        String keepNum="";//指向多位的数字
        int num1;//用于计算准备的变量
        int num2;
        int opera;
        while (true) {
            if (index>=cal.length()) {//扫描完毕退出循环
                break;
            }
            ch = cal.charAt(index);//扫描到的字符
            index++;//迭代
            if (isOpera(ch)) {//字符为运算符
                if (operas.isEmpty()) {//运算符栈空
                    operas.push(ch);//第一个运算符压入空栈
                }else {
                    //判断待入栈的运算符优先级是否小于或等于栈顶的运算符
                    //满足<=,数字栈取出两个数字,运算符栈取出一个运算符,将计算结果存入数字栈
                    //新的运算符入栈
                    if (priority(ch) <= priority(operas.peek())) {
                        num1 = nums.pop();
                        num2 = nums.pop();
                        opera = operas.pop();
                        nums.push(calcu(num1, num2, opera));
                        index--;
                    }else {
                        operas.push(ch);//优先级高于栈顶的元素
                    }
                }
            }else {//字符为数字

                keepNum+=ch;//追加字符
                //判断索引越界或者下一个字符是运算符
                //-->将字符串解析成数字压栈
                //-->重置字符串
                if (index==cal.length()||isOpera(cal.charAt(index))) {
                    nums.push(Integer.parseInt(keepNum));
                    keepNum = "";
                }
            }
        }
        //以上程序结束表示遍历并入栈完毕
        //循环取出一组元素运算并将结果放入数字栈
        //当运算符栈为空表示运算完毕
        while (true) {
            if (operas.isEmpty()) {
                System.out.println("运算结果为:"+nums.pop());
                break;
            }
            num1 = nums.pop();
            num2 = nums.pop();
            opera = operas.pop();
            nums.push(calcu(num1, num2, opera));
        }

    }

    //判断是否运算符
    public static boolean isOpera(char ch) {
        return ch == '+' || ch == '-' || ch == '*' || ch == '/';
    }

    //计算两个数间的运算结果
    public static int calcu(int num1,int num2,int opera) {
        int result = 0;
        switch (opera) {
            case '+':
                result = num2 + num1;
                break;
            case '-':
                result = num2 - num1;
                break;
            case '*':
                result = num2 * num1;
                break;
            case '/':
                result = num2 / num1;
                break;
        }
        return result;
    }

    //返回操作符的优先级
    public static int priority(int opera) {
        if (opera == '*' || opera == '/') {
            return 1;
        }else if (opera == '+' || opera == '-') {
            return 0;
        }
        return -1;
    }
}
class Stack {
    private int maxSize;
    private int top=-1;
    private int[] array;

    public Stack(int maxSize) {
        this.maxSize = maxSize;
        array = new int[maxSize];
    }

    //入栈
    public void push(int num) {
        if (isFull()) {
            System.out.println("栈满");
            return;
        }
        top++;
        array[top] = num;
    }

    //检查栈顶元素
    public int peek() {
        return array[top];
    }

    //出栈
    public int pop() {
        if (isEmpty()) {
            throw new RuntimeException("栈空");
        }
        int num = array[top];
        top--;
        return num;
    }

    //从栈顶往栈底遍历
    public void list() {
        if (isEmpty()) {
            System.out.println("栈空");
            return;
        }
        for (int i = top; i >=0 ; i--) {
            System.out.println(array[i]);
        }
    }

    //栈空
    public boolean isEmpty() {
        return top == -1;
    }

    //栈满
    public boolean isFull() {
        return top == maxSize - 1;
    }

}