package com.zxk.stack;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Stack;

//计算中缀表达式(带括号)将一个中缀表达式转成后缀表达式并计算
public class RPNCalculator {
    public static void main(String[] args) {

        Stack<Character> s1 = new Stack<>();
        Stack<String> s2 = new Stack<>();

        String expression = "";
        expression = "1+((2+3)*4)-5";//1 2 3 + 4 * + 5 -
        expression = "3+(4*(1+1)+1)*2";//出栈+*2+1*+1143 后缀:3 4 1 1 + * 1 + 2 * +
        int index = 0;
        String num = "";
        ArrayList<Character> characters;
        while (true) {
            if (index>=expression.length()) {//扫描完毕退出循环
                break;
            }
            char ch = expression.charAt(index);
            index ++;
            if (isOpera(ch)) {//字符为运算符
                //以下条件满足任一直接入s1栈
                //1.栈为空
                //2.栈顶为'('
                //3.该字符为'('
                if (s1.empty() || peek(s1) == '('||ch=='(') {
                    s1.push(ch);
                //判断该字符为')'执行:
                //循环取出s1栈顶入s2直到取出'('为止
                } else if (ch == ')') {
                    while (true) {
                        if (peek(s1) == '(') {
                            s1.pop();
                            break;
                        }
                        s2.push("" + s1.pop());
                    }
                }else {//剩余情况:

                    //该字符优先级小于栈顶:取出s1栈顶入s2栈并重新将索引--指回该字符
                    if (priority(ch) <= priority(s1.peek())) {
                        s2.push("" + s1.pop());
                        index--;
                        //否则即为优先级大于或等于栈顶:直接入s1栈
                    } else {
                        s1.push(ch);
                    }

                }
            } else {//字符为数字
                //将该字符追加到字符串末尾
                num += ch;
                //判断是否为最后一个字符,或下一个字符为运算符:直接入s2栈,然后数字字符串置空
                if (index==expression.length()||isOpera(expression.charAt(index))) {
                    s2.push(num);
                    num = "";
                }
            }
        }
        //将s1剩余运算符出栈同时入s2栈
        while (true) {
            if (s1.empty()) {
                break;
            }
            s2.push("" + s1.pop());
        }

        //到此行,中缀转后缀的完整的后缀表达式入s2栈完毕
        /*while (true) {
            if (s2.empty()) {
                break;
            }
            System.out.print(s2.pop());
        }*/

        //s2元素出栈
        ArrayList<String> elements = new ArrayList<>();
        while (true) {
            if (s2.empty()) {
                break;
            }

            elements.add(s2.pop());
        }
        //数字栈,用于存放临时计算结果
        Stack<Integer> stack = new Stack<>();
        int num1;
        int num2;
        String opera;
        //倒序遍历集合中的元素,使用逆波兰计算器,借助一个数字栈计算,将计算结果存入该栈
        for (int i = elements.size()-1; i >=0 ; i--) {
            //数组元素为运算符:出栈两个,运算结果入栈
            if (isOpera(elements.get(i))) {
                num1 = stack.pop();
                num2 = stack.pop();
                opera = elements.get(i);
                stack.push(calcu(num1, num2, opera));
                //数组元素为数字,直接入栈
            } else {
                stack.push(Integer.parseInt(elements.get(i)));
            }
        }
        System.out.println(stack.pop());//表达式计算结果
    }

    //判断字符是否运算符
    public static boolean isOpera(char ch) {
        return ch == '+' || ch == '-' || ch == '*' || ch == '/'||ch=='('||ch==')';
    }

    //判断数组元素(字符串)是否运算符
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

    //返回操作符的优先级
    public static int priority(char opera) {
        if (opera == '*' || opera == '/') {
            return 1;
        }else if (opera == '+' || opera == '-') {
            return 0;
        }
        return -1;
    }

    //返回栈顶元素
    public static Character peek(Stack<Character> stack) {
        Character result =stack.pop();

                stack.push(result);
            return result;

    }
}

