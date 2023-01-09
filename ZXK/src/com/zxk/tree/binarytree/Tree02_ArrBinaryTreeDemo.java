package com.zxk.tree.binarytree;


//顺序存储二叉树(数组和树可相互转换,这里用数组存放二叉树节点)
//数组存储元素顺序是按树层序遍历顺序......
public class Tree02_ArrBinaryTreeDemo {
    public static void main(String[] args) {
        int[] arr = { 1, 2, 3, 4, 5, 6, 7 };
        ArrBinaryTree arrBinaryTree = new ArrBinaryTree(arr);
        arrBinaryTree.preOrder();

    }
}

//树
class ArrBinaryTree {
    private int[] arr;

    public ArrBinaryTree(int[] arr) {
        this.arr = arr;
    }

    //重载
    public void preOrder() {
        preOrder(0);
    }

    //前序遍历
    //核心:父子节点间的索引计算等式,左子节点2n+1,右子节点2n+2,父节点(n-1)/2
    //递归终止条件为索引越界arr.length,当然给递归调用加if判断也可以终止调用
    public void preOrder(int index) {
        if (arr == null || arr.length == 0) {
            System.out.println("数为空,无法遍历!");
            return;
        }
        if (index >= 0 && index < arr.length) {
            System.out.println(arr[index]);
        } else {
            return;
        }

        preOrder(2 * index + 1);
        preOrder(2 * index + 2);

    }
}