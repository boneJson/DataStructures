package com.zxk.linkedlist.singlelinkedlist.josephu;
/*
Josephu 问题为：设编号为 1，2，… n 的 n 个人围坐一圈，约定编号为 k（1<=k<=n）的人从 1 开始报数，数到
m 的那个人出列，它的下一位又从 1 开始报数，数到 m 的那个人又出列，依次类推，直到所有人出列为止，由此
产生一个出队编号的序列。
 */
public class Josephu {
    public static void main(String[] args) {
        CircleSingleLinkedList circleSingleLinkedList = new CircleSingleLinkedList();
        circleSingleLinkedList.add(25);
//        circleSingleLinkedList.out(5,1,2);//24153
        circleSingleLinkedList.out(25,1,2);//

    }
}

//创建单向环形链表
class CircleSingleLinkedList {
    //第一个有效结点的引用
    private Boy first = null;

    /**
     * n个小孩围一圈,从第k个小孩开始,数到第m个小孩出圈
     * @param n 表示最初有多少小孩在圈中
     * @param k 表示从第几个小孩开始数数
     * @param m 表示数几下
     */
    public void out(int n,int k,int m) {

        if (k > n || k < 1 ||first==null) {//数据有误
            System.out.println("游戏没法玩...");
            return;
        }
        //报数前对照k,先向后移动变量first k-1次,
        for (int i = 0; i < k - 1; i++) {
            first = first.getNext();
        }
        while (true) {
            for (int i = 0; i <m-2 ; i++) {
                first = first.getNext();//first后移m-2次,表示待删除的第m个结点的前一个
            }
            System.out.println("出圈的小孩为"+first.getNext().getNo());
            first.setNext(first.getNext().getNext());//first.next跳过第m个结点指向其下一个
            first = first.getNext();//后移first
            if (first.getNext() == first) {//第一个结点自身成环-->场上仅剩一个小孩
                System.out.println("最后出圈的小孩为"+first.getNo());
                break;
            }

        }
    }

    //创造n个小孩结点的链表
    public void add(int num) {
        //校验
        if (num < 1) {
            System.out.println("游戏没法玩...");
        }
        Boy cur=null;
        for (int i = 1; i <= num; i++) {
            Boy boy = new Boy(i);
            if (i == 1) {//第一个小孩为first,且自身成环
                first = boy;
                first.setNext(first);
                cur = first;
            }
            cur.setNext(boy);//cur的next指向下一个结点
            cur = boy;//迭代:cur后移
            cur.setNext(first);//新的next指向first形成环
        }
    }

    //遍历
    public void list() {
        if (first == null) {
            System.out.println("链表为空~");
            return;
        }
        Boy cur=first;
        while (true) {

            System.out.println("场上小孩编号为:"+cur.getNo());
            cur = cur.getNext();//cur后移
            if (cur ==first) {//发现到first了,遍历结束
                break;
            }
        }
    }
}
class Boy {
    private int no;
    private Boy next;

    public Boy(int no) {
        this.no = no;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public Boy getNext() {
        return next;
    }

    public void setNext(Boy next) {
        this.next = next;
    }
}