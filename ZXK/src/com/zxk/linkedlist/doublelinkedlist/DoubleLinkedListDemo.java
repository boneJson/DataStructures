package com.zxk.linkedlist.doublelinkedlist;

//双向链表的CRUD
public class DoubleLinkedListDemo {
    public static void main(String[] args) {
        HeroNode hero1 = new HeroNode(1, "宋江", "及时雨");
        HeroNode hero2 = new HeroNode(2, "卢俊义", "玉麒麟");
        HeroNode hero3 = new HeroNode(3, "吴用", "智多星");
        HeroNode hero4 = new HeroNode(4, "林冲", "豹子头");

        DoubleLinkedList doubleLinkedList = new DoubleLinkedList();
        /*doubleLinkedList.add(hero1);
        doubleLinkedList.add(hero4);
        doubleLinkedList.add(hero3);
        doubleLinkedList.add(hero2);*/
        doubleLinkedList.addByOrder(hero1);
        doubleLinkedList.addByOrder(hero4);
        doubleLinkedList.addByOrder(hero3);
        doubleLinkedList.addByOrder(hero2);
        doubleLinkedList.list();
        /*System.out.println("删除后:");
        doubleLinkedList.delete(3);*/
        /*System.out.println("修改后:");
        doubleLinkedList.update(new HeroNode(3,"入云龙","公孙胜"));
        doubleLinkedList.list();*/
    }
}

class DoubleLinkedList {
    HeroNode first = new HeroNode(0, "", "");

    //添加结点方式一:不考虑添加顺序(较单链表小改)
    public void add(HeroNode hn) {
        HeroNode temp = first;//创建辅助指针
        while (true) {
            if (temp.next == null) {//此时temp为代表尾结点
                temp.next = hn;//尾部接上结点
                hn.pre = temp;//处理待添加结点的pre指向
                break;
            }
            temp = temp.next;//每次使用后赋值更迭
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
            } else if (temp.next.no == hn.no) {//该hn的标号已存在
                flag = true;
                break;
            }
            temp = temp.next;//迭代
        }
        if (flag) {
            System.out.println("排名存在,添加失败~");
        }
        //将新结点放在temp和temp.next的中间
        //先将temp的下一个结点挂在hn后面-->再将hn按排序结果插入到temp后面
        hn.next = temp.next;
        if (temp.next != null) {
            temp.next.pre = hn;
        }
        hn.pre = temp;
        temp.next = hn;

    }

    //修改结点:同单链表
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
            temp.nickName = newhn.nickName;
            temp.name = newhn.name;
        } else {
            System.out.println("没找到该编号,修改失败...");
        }
    }


    //删除结点:只需操作待删除结点上的4个指针即可(尾结点2个)
    public void delete(int no) {
        boolean flag = false;//该变量表示:默认待删除英雄的排名在表中不存在
        HeroNode temp = first.next;
        if (temp == null) {
            System.out.println("链表为空~");
        }

        while (true) {
            if (temp == null) {//没找到对应编号
                break;
            }
            if (temp.no == no) {//找到对应编号
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag) {
            System.out.println("删除成功~");
            //改变指向待删除结点的next指针即可-->指向待删除的下一个结点
            temp.pre.next = temp.next;
            if (temp.next != null) {//删除最后的结点不用操作temp.next的pre指针,否则报空
                //改变指向待删除结点的pre指针即可-->指向待删除的上一个结点
                temp.next.pre = temp.pre;
            }
        } else {
            System.out.println("没找到该编号,删除失败...");
        }
    }


    //遍历方法:同单链表
    public void list() {
        HeroNode temp = first;
        if (temp.next == null) {
            System.out.println("链表为空~");
        }
        while (true) {
            if (temp.next == null) {
                break;
            }
            temp = temp.next;
            System.out.println(temp);
        }
    }

}

class HeroNode {
    public int no;
    public String name;
    public String nickName;
    public HeroNode next;
    public HeroNode pre;

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

