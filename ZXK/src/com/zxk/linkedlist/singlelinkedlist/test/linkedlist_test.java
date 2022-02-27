package com.zxk.linkedlist.singlelinkedlist.test;

import java.util.Stack;

/**
 * @ClassName
 * @Description
 * @Author zxk
 * @DateTime 2022-02-19-22:13
 * @Version
 *///面试题
@SuppressWarnings({"all"})
public class linkedlist_test {
        public static void main(String[] args) {
            HeroNode hero1 = new HeroNode(1, "宋江", "及时雨");
            HeroNode hero2 = new HeroNode(2, "卢俊义", "玉麒麟");
            HeroNode hero3 = new HeroNode(3, "吴用", "智多星");
            HeroNode hero4 = new HeroNode(4, "林冲", "豹子头");
            HeroNode hero5 = new HeroNode(5, "霹雳火", "秦明");
            HeroNode hero6 = new HeroNode(6, "行者", "武松");
            HeroNode hero7 = new HeroNode(7, "花和尚", "鲁智深");
            HeroNode hero8 = new HeroNode(8, "神行太保", "戴宗");

            SingleLinkedList singleLinkedList = new SingleLinkedList();
        /*singlelinkedlist.add(hero1);
        singlelinkedlist.add(hero2);
        singlelinkedlist.add(hero3);
        singlelinkedlist.add(hero4);*/

            singleLinkedList.addByOrder(hero1);
            singleLinkedList.addByOrder(hero4);
            singleLinkedList.addByOrder(hero3);
            singleLinkedList.addByOrder(hero2);

            SingleLinkedList singleLinkedList2 = new SingleLinkedList();
            singleLinkedList2.addByOrder(hero7);
            singleLinkedList2.addByOrder(hero5);
            singleLinkedList2.addByOrder(hero6);
            singleLinkedList2.addByOrder(hero8);

            /*System.out.println("修改前:");
            singlelinkedlist.list();
            singlelinkedlist.update(new HeroNode(2,"小卢","玉麒麟~~~"));
            System.out.println("修改后:");
            singlelinkedlist.list();
            singlelinkedlist.delete(2);
            System.out.println("删除后:");*/

            /*System.out.println("顺序打印链表:");
            singlelinkedlist.list();*/

            /*System.out.println("有效结点个数:"+singlelinkedlist.getLength(singlelinkedlist.first));

            System.out.println("查找的结点为:"+singlelinkedlist.findLastIndexNode(singlelinkedlist.first, 1));

            singlelinkedlist.reverseList(singlelinkedlist.first);
            System.out.println("反转后的链表为:");
            singlelinkedlist.list();*/

            /*System.out.println("逆序打印链表:");
            singlelinkedlist.reversePrint(singlelinkedlist.first);*/

            System.out.println("链表1:");
            singleLinkedList.list();

            System.out.println("链表2:");
            singleLinkedList2.list();

            System.out.println("有序合并:");
            SingleLinkedList.combine01(singleLinkedList.first,singleLinkedList2.first).list();
        }
    }

    class SingleLinkedList {
        HeroNode first = new HeroNode(0, "", "");

        //合并两个有序的单链表，合并之后的链表依然有序
        //收集两个链表的所有结点,按序添加到新链表
        public static SingleLinkedList combine01(HeroNode first, HeroNode head) {
            if (first.next == null && head.next == null) {
                System.out.println("两个空链表");
            }
            SingleLinkedList combineList01 = new SingleLinkedList();
            Stack<HeroNode> stack = new Stack<>();
            HeroNode temp01=first.next;
            while (temp01 != null) {
            //addByOrder()方法操作结点后会改变next的指向
            //-->由原来改为指向待加入链表的插入位置后面的一个结点
            //-->因此遍历链表时,因该方法会导致迭代语句失效(temp=temp.next)
            //-->禁止迭代时使用该方法
            //combineList01.addByOrder(temp01);
                stack.add(temp01);//入栈
                temp01 = temp01.next;
            }
            HeroNode temp02=head.next;
            while (temp02 != null) {
                stack.add(temp02);
                temp02 = temp02.next;
            }
            while (stack.size() > 0) {
                combineList01.addByOrder(stack.pop());//出栈-->有序添加到新链表
            }
            return combineList01;
        }

        //逆序打印
        public void reversePrint(HeroNode head) {
            if (head.next == null) {
                System.out.println("链表为空!");
            }
            Stack<HeroNode> stack = new Stack<HeroNode>();
            HeroNode temp = head.next;
            while (temp != null) {
                stack.add(temp);//遍历并入栈
                temp = temp.next;
            }
            while (stack.size() > 0) {
                System.out.println(stack.pop());//取出栈顶结点
            }
        }

        //反转链表
        public void reverseList(HeroNode head) {
            HeroNode reverseHead = new HeroNode(0, "", "");
            HeroNode temp = head.next;
            HeroNode next;
            while (temp!= null) {
                next = temp.next;//这里提取辅助变量后面的链表是为了使用完辅助变量后该可以保持迭代
                temp.next=reverseHead.next;//将新链表(头结点排除)先挂在待添加结点后面
                reverseHead.next = temp;//再将加入结点后的链表挂在头结点后,此时完成一次按序加入
                temp =next;//辅助结点后移
            }
            head.next = reverseHead.next;//新链表(头结点排除)挂在head后面
        }

        //查找单链表中的倒数第 k 个结点
        public HeroNode findLastIndexNode(HeroNode head, int lastIndex) {
            int index = this.getLength(first) - lastIndex;//正序索引为index
            if (head.next == null) {//链表为空
                return null;
            }
            if (lastIndex < 1 || lastIndex > this.getLength(first)) {//校验
                return null;
            }
            HeroNode temp = head.next;//第一个有效结点
            for (int i = 0; i < index; i++) {//当index==1,表示定位到第二个有效结点
                temp = temp.next;//迭代一次,正确
            }
            return temp;
        }

        //获得有效结点的个数
        public int getLength(HeroNode head) {
            if (head.next == null) {
                return 0;
            }
            int len = 0;
            HeroNode temp=head.next;
            while (temp != null) {
                len++;
                temp=temp.next;
            }
            return len;
        }

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
