package com.zxk.stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
//使用老师的方法实现中缀转后缀再计算
public class HSPCalculator {
    public static void main(String[] args) {
        String infixExpression = "1+((2+3)*4)-5";//中缀表达式
        List<String> infixExpressionList = toInfixExpressionList(infixExpression);//中缀拆开放入集合
        List<String> suffixExpressionList = parseSuffixExpressionList(infixExpressionList);//后缀表达式1 2 3 + 4 * + 5 -
        System.out.println(calc(suffixExpressionList));

    }

    //逆波兰计算器
    public static int calc(List<String> ls) {
        Stack<Integer> stack = new Stack<>();
        int num1;
        int num2;
        String opera;

        for (String string : ls) {//遍历表达式字符串
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
        return stack.pop();//表达式计算结果
    }

    //将中缀List转成后缀List
    public static List<String> parseSuffixExpressionList(List<String> ls) {
        Stack<String> s1 = new Stack<>();//符号栈
        ArrayList<String> s2 = new ArrayList<>();//存放中间结果的集合

        for (String item : ls) {
            if (item.matches("\\d+")) {
                s2.add(item);
            } else if (item.equals("(")) {
                s1.push(item);
            } else if (item.equals(")")) {
                while (!s1.peek().equals("(")) {
                    s2.add(s1.pop());
                }
                s1.pop();
            } else {
                while (s1.size() != 0 && priority(s1.peek()) >= priority(item)) {

                    s2.add(s1.pop());
                }
                s1.push(item);
            }
        }
        while (s1.size() != 0) {
            s2.add(s1.pop());
        }

        return s2;
    }


    //方法:将中缀表达式转成List:多位数和运算符分别以字符串类型存入集合
    public static List<String> toInfixExpressionList(String s) {
        ArrayList<String> ls = new ArrayList<>();
        int index = 0;

        String num;
        char ch;
        do {

            if ((ch = s.charAt(index)) < 48 || (ch = s.charAt(index)) > 57) {
                ls.add("" + ch);
                index++;
            } else {
                num = "";
                while (index < s.length() && ((ch = s.charAt(index)) >= 48 && (ch = s.charAt(index)) <= 57)) {
                    num += ch;
                    index++;
                }
                ls.add(num);
            }

        } while (index < s.length());
        return ls;
    }


    //返回操作符的优先级
    public static int priority(String opera) {
        if (opera .equals("*") || opera .equals("/") ) {
            return 2;
        }else if (opera .equals("+")  || opera .equals("-")) {
            return 1;
        }
        return 0;
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
