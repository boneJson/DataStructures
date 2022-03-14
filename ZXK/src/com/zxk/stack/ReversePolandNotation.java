package com.zxk.stack;

import java.util.Arrays;
import java.util.List;
import java.util.Stack;

//逆波兰计算器:使用Stack计算后缀表达式
public class ReversePolandNotation {
    public static void main(String[] args) {
        //中缀:(30+4)*5-6
        //后缀:30 4 + 5 * 6 -
        String expression = "30 4 + 5 * 6 -";

        //该栈用于存放数字和临时计算结果
        Stack<Integer> stack = new Stack<>();
        int num1;
        int num2;
        String opera;
        String[] s = expression.split(" ");
        for (String string : s) {//遍历表达式字符串
            //数组元素为运算符:出栈两个数字并运算,运算结果入栈
            if (isOpera(string)) {
                num1 = stack.pop();
                num2 = stack.pop();
                opera = string;
                stack.push(calcu(num1, num2, opera));
            //数组元素为数字,直接入栈
            } else {
                stack.push(Integer.parseInt(string));
            }
        }
        System.out.println(stack.pop());//表达式计算结果
    }

    //判断数组元素是否运算符
    public static boolean isOpera(String s) {
        return s.equals("+")||s.equals("-")||s.equals("*")||s.equals("/");
    }

    //计算三个元素
    public static int calcu(int num1,int num2,String opera) {
        int result = 0;
        switch (opera) {
            case "+":
                result = num2 + num1;
                break;
            case "-":
                result = num2 - num1;
                break;
            case "*":
                result = num2 * num1;
                break;
            case "/":
                result = num2 / num1;
                break;
        }
        return result;
    }
}
