package com.zxk.tree.apply;

import java.util.ArrayList;
import java.util.Collections;

//赫夫曼树的创建
public class App02_HuffmanTree {
    public static void main(String[] args) {
        int[] arr = {13, 7, 8, 3, 29, 6, 1};

        Node01 root = createHuffmanTree(arr);

        if (root != null) {
            root.preOrder();
        } else {
            System.out.println("树为空!!!");
        }
    }

    public static Node01 createHuffmanTree(int[] arr) {
        //将数组元素作为权值创建节点放入集合
        ArrayList<Node01> list = new ArrayList<>();
        for (int value : arr) {
            list.add(new Node01(value));
        }

        //取集合中权值最小和第二小的节点,权相加作为父节点,更新集合(删2个添1个)
        //思考:为什么取最小的2个?
        //      1.取2个第一次是2个叶子,第二次开始每次加一片叶子,
        //      2.取最小是因为需满足权值大的靠近根节点
        while (list.size() > 1) {
            //这里排序存在问题: 如果多个节点的权值相同,则取2个的取法不同,导致最终的赫夫曼树可能不同(虽然wpl一样
            //,导致最终压缩长度一样),参考赫夫曼编码即该问题导致字符最终的编码不同
            Collections.sort(list);
            Node01 left = list.get(0);
            Node01 right = list.get(1);
            Node01 parent = new Node01(left.getValue() + right.getValue());
            parent.setLeft(left);
            parent.setRight(right);
            list.remove(left);
            list.remove(right);
            list.add(parent);
        }

        //当集合大小为1,则将节点作为根节点返回
        return list.get(0);
    }
}

class Node01 implements Comparable<Node01>{
    private int value;
    private Node01 left;
    private Node01 right;

    public Node01(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public Node01 getLeft() {
        return left;
    }

    public void setLeft(Node01 left) {
        this.left = left;
    }

    public Node01 getRight() {
        return right;
    }

    public void setRight(Node01 right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return "Node01 [value=" + value + "]";
    }

    //升序
    @Override
    public int compareTo(Node01 Node01) {
        return this.value-Node01.value;
    }

    public void preOrder() {
        System.out.println(this);
        if (this.left != null) {
            left.preOrder();
        }
        if (this.right != null) {
            right.preOrder();
        }
    }
}