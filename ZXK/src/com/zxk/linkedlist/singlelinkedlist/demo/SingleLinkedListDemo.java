package com.zxk.linkedlist.singlelinkedlist.demo;

/**
 * @ClassName
 * @Description
 * @Author zxk
 * @DateTime 2022-02-18-22:24
 * @Version
 *///单向链表CRUD:添加顺序不考虑结点的编号no
//并提供优化方法-->按结点的编号no作为添加顺序
public class SingleLinkedListDemo {
    public static void main(String[] args) {
        HeroNode hero1 = new HeroNode(1, "宋江", "及时雨");
        HeroNode hero2 = new HeroNode(2, "卢俊义", "玉麒麟");
        HeroNode hero3 = new HeroNode(3, "吴用", "智多星");
        HeroNode hero4 = new HeroNode(4, "林冲", "豹子头");

        SingleLinkedList singleLinkedList = new SingleLinkedList();
        singleLinkedList.add(hero1);
        singleLinkedList.add(hero2);
        singleLinkedList.add(hero3);
        singleLinkedList.add(hero4);
        singleLinkedList.list();

        /*singleLinkedList.addByOrder(hero1);
        singleLinkedList.addByOrder(hero4);
        singleLinkedList.addByOrder(hero3);
        singleLinkedList.addByOrder(hero2);

        System.out.println("修改前:");
        singleLinkedList.list();

        singleLinkedList.update(new HeroNode(2,"小卢","玉麒麟~~~"));

        System.out.println("修改后:");
        singleLinkedList.list();

        singleLinkedList.delete(2);
        System.out.println("删除后:");
        singleLinkedList.list();*/
    }
}

class SingleLinkedList {
    HeroNode first = new HeroNode(0, "", "");

    //添加结点方式一:不考虑添加顺序-->添加顺序即为打印顺序
    public void add(HeroNode hn) {
        HeroNode temp=first;//创建辅助指针
        while (true) {
            if (temp.next == null) {//此时temp为代表尾结点
                temp.next=hn;//尾部接上结点
                break;
            }
            temp= temp.next;//每次使用后赋值更迭
        }
    }
    //添加结点方式二:按结点的编号no作为添加顺序
    //因为添加的位置是在结点的后面,所以比较编号也是同下一个结点比较
    public void addByOrder(HeroNode hn) {
        HeroNode temp = first;
        boolean flag = false;//该变量表示:默认待加入英雄的排名在表中不存在
        while (true) {
            if (temp.next == null) {//遍历到尾部,hn的编号为最大
                break;
            }
            if (temp.next.no > hn.no) {//该结点的下一个编号比hn大
                break;
            } else if (temp.next.no == hn.no) {//还hn的标号已存在
                flag = true;
                break;
            }
            temp = temp.next;//迭代
        }
        if (flag) {
            System.out.println("排名存在,添加失败~");
        }
        //先将temp的下一个结点挂在hn后面-->再将hn按排序结果插入
        // -->否则每次在链表中间插入结点后面的链表会断开丢弃
        hn.next = temp.next;
        temp.next = hn;
    }

    //修改结点
    public void update(HeroNode newhn) {
        boolean flag = false;//该变量表示:默认待修改英雄的排名在表中不存在
        HeroNode temp = first;
        if (temp.next == null) {
            System.out.println("链表为空~");
        }

        while (true) {
            if (temp == null) {//没找到对应编号
                break;
            }
            if (temp.no == newhn.no) {//找到对应编号
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag) {
            System.out.println("修改成功~");
            //提示:此处不能直接将结点直接赋值,否则链表断裂(next属性未处理)
            temp.nickName= newhn.nickName;
            temp.name = newhn.name;
        } else {
            System.out.println("没找到该编号,修改失败...");
        }
    }


    //删除结点
    public void delete(int no) {
        boolean flag = false;//该变量表示:默认待删除英雄的排名在表中不存在
        HeroNode temp = first;
        if (temp.next == null) {
            System.out.println("链表为空~");
        }

        while (true) {
            if (temp.next == null) {//没找到对应编号
                break;
            }
            if (temp.next.no == no) {//找到对应编号
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag) {
            System.out.println("删除成功~");
            //改变指向待删除结点的next指针即可-->指向待删除的下一个结点
            temp.next = temp.next.next;
        } else {
            System.out.println("没找到该编号,删除失败...");
        }
    }



    //遍历方法:
    public void list() {
        HeroNode temp = first;
        if (temp.next == null) {
            System.out.println("链表为空~");
        }
        while (true) {
            if (temp.next == null) {
                break;
            }
            temp= temp.next;
            System.out.println(temp);
        }
    }

}

class HeroNode {
    public int no;
    public String name;
    public String nickName;
    public HeroNode next;

    public HeroNode(int no, String name, String nickName) {
        this.no = no;
        this.name = name;
        this.nickName = nickName;
    }

    //提示:toString方法不可以返回next属性,否则每个结点打印都会打印由其开始的一条链表
    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickName='" + nickName + '\'' +
                '}';
    }
}