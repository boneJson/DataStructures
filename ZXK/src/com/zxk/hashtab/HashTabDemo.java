package com.zxk.hashtab;

import java.util.Scanner;

//使用数组+链表实现哈希表
public class HashTabDemo {
    public static void main(String[] args) {

        //创建哈希表
        HashTab hashTab = new HashTab(7);

        //菜单
        String key = "";
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("add: 添加雇员");
            System.out.println("list: 显示雇员");
            System.out.println("find: 查找雇员");
            System.out.println("exit: 退出系统");
            key = scanner.next();
            switch (key) {
                case "add":
                    System.out.println("输入id");
                    int id = scanner.nextInt();
                    System.out.println("输入名字");
                    String name = scanner.next();
                    hashTab.add(new Emp(id, name));
                    break;
                case "list":
                    hashTab.list();
                    break;
                case "find":
                    System.out.println("输入目标id");
                    int target = scanner.nextInt();
                    hashTab.findEmp(target);
                    break;
                case "exit":
                    scanner.close();
                    System.exit(0);
                default:
                    break;
            }
        }
    }
}

//定义哈希表类型
class HashTab {
    //由元素为链表的数组构成
    public EmpLinkedList[] empLinkedListArray;
    //数组大小
    public int size;

    //构造器初始化
    public HashTab(int size) {
        this.size = size;
        this.empLinkedListArray = new EmpLinkedList[size];

        //初始化数组:必须先创建链表!!!!!!!!!
        //否则空指针
        for (int i = 0; i < size; i++) {
            empLinkedListArray[i] = new EmpLinkedList();
        }
    }

    //返回雇员所在链表的下标
    public int hashFun(int id) {
        return id % size;
    }

    public void add(Emp emp) {
        int empLinkedListNo = hashFun(emp.id);
        empLinkedListArray[empLinkedListNo].add(emp);
    }

    public void list() {
        for (int i = 0; i < size; i++) {
            empLinkedListArray[i].list(i);
        }
    }

    public void findEmp(int id) {
        int empLinkedListNo = hashFun(id);
        Emp emp = empLinkedListArray[empLinkedListNo].findEmp(id);
        if (emp == null) {
            System.out.println("没找到!!!");
        } else {
            System.out.printf("在第%d条链表中找到雇员 id = %d\n", (empLinkedListNo), id);
        }
    }
}

//定义链表类型
class EmpLinkedList {

    //这里默认head可以存放数据
    public Emp head;

    //添加节点到链表尾
    public void add(Emp emp) {
        if (head== null) {
            head= emp;
        } else {
            Emp temp = head;
            while (true) {
                if (temp.next == null) {
                    break;
                }
                temp = temp.next;
            }
            temp.next = emp;
        }
    }

    //顺序遍历链表
    public void list(int no) {
        if (head == null) {
            System.out.println("第"+no+"条链表为空");
        } else {
            System.out.print("第"+no+"条链表为");
            Emp temp = head;
            while (true) {
                System.out.print("=>" + temp.id + "=" + temp.name);
                if (temp.next == null) {
                    break;
                }
                temp = temp.next;
            }
            System.out.println();
        }
    }

    //输入员工id,返回员工
    public Emp findEmp(int id) {

        //链表为空不可能找到
        if (head == null) {
            System.out.println("链表为空");
            return null;
        } else {
            Emp temp = head;
            while (true) {

                //找到目标员工
                if (temp.id == id) {
                    return temp;
                }

                //遍历结束也没找到
                if (temp.next == null ) {
                    return null;
                }

                temp = temp.next;
            }

        }
    }
}

//定义员工节点类型
class Emp {
    public int id;
    public String name;
    public Emp next;
    public Emp(int id,String name) {
        this.id = id;
        this.name = name;
    }
}