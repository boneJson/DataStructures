package com.zxk.leecode;

import java.util.*;

//有一个 单线程 CPU 正在运行一个含有 n 道函数的程序。每道函数都有一个位于  0 和 n-1 之间的唯一标识符。
//
//函数调用 存储在一个 调用栈 上 ：当一个函数调用开始时，它的标识符将会推入栈中。而当一个函数调用结束时，它的标识符将会从栈中弹出。标识符位于栈顶的函数是 当前正在执行的函数 。每当一个函数开始或者结束时，将会记录一条日志，包括函数标识符、是开始还是结束、以及相应的时间戳。
//
//给你一个由日志组成的列表 logs ，其中 logs[i] 表示第 i 条日志消息，该消息是一个按 "{function_id}:{"start" | "end"}:{timestamp}" 进行格式化的字符串。例如，"0:start:3" 意味着标识符为 0 的函数调用在时间戳 3 的 起始开始执行 ；而 "1:end:2" 意味着标识符为 1 的函数调用在时间戳 2 的 末尾结束执行。注意，函数可以 调用多次，可能存在递归调用 。
//
//函数的 独占时间 定义是在这个函数在程序所有函数调用中执行时间的总和，调用其他函数花费的时间不算该函数的独占时间。例如，如果一个函数被调用两次，一次调用执行 2 单位时间，另一次调用执行 1 单位时间，那么该函数的 独占时间 为 2 + 1 = 3 。
//
//以数组形式返回每个函数的 独占时间 ，其中第 i 个下标对应的值表示标识符 i 的函数的独占时间。
public class Test06 {
    public static void main(String[] args) {
        //输入：n = 2, logs = ["0:start:0","0:start:2","0:end:5","1:start:6","1:end:6","0:end:7"]
        //输出：[7,1]
        String[] strings = {"0:start:0","1:start:5","2:start:6","3:start:9","4:start:11","5:start:12","6:start:14","7:start:15","1:start:24","1:end:29","7:end:34","6:end:37","5:end:39","4:end:40","3:end:45","0:start:49","0:end:54","5:start:55","5:end:59","4:start:63","4:end:66","2:start:69","2:end:70","2:start:74","6:start:78","0:start:79","0:end:80","6:end:85","1:start:89","1:end:93","2:end:96","2:end:100","1:end:102","2:start:105","2:end:109","0:end:114"};
        ArrayList<String> list = new ArrayList<String>();
        Collections.addAll(list, strings);
        int[] ints = new Test06().exclusiveTime(8, list);
        for (int anInt : ints) {
            System.out.println(anInt);
        }//[20,14,35,7,6,9,10,14]

    }
    //要么起始,要么结束
    //起始:前面可能没有/可能start/可能end
    //结束:前面可能start/可能end

    //输入：n = 2, logs = ["0:start:0","0:start:2","0:end:5","1:start:6","1:end:6","0:end:7"]
    //输出：[7,1]
    //cur=start/end+1
    //注意:只有end才会弹栈,start标志的仅仅是入栈和peek用来时间累加
    public int[] exclusiveTime(int n, List<String> logs) {
        int[] res = new int[n];//结果集
        LinkedList<Integer> tmp = new LinkedList<>();//未结束
        int cur = 0;
        for (String log : logs) {
            String[] fields = log.split(":");
            Integer no = Integer.parseInt(fields[0]);
            String type = fields[1];
            Integer ts = Integer.parseInt(fields[2]);
            int gap = -1;
            if ("start".equals(type)) {
                gap = ts - cur;
                //首先,获取上个未结束函数编号
                if (!tmp.isEmpty()) {
                    Integer last = tmp.peekLast();
                    res[last] += gap;
                }
                //最后,开始编号放入未结束
                tmp.addLast(no);

                cur = ts;
            } else {
                //
                gap = ts + 1 - cur;
                res[no] += gap;
                if (!tmp.isEmpty()) {
                    tmp.removeLast();
                }
                cur = ts + 1;
            }
        }
        return res;
    }
}
