package com.zxk.tree.apply;

//平衡二叉树
public class App05_AVLTree {
    public static void main(String[] args) {
        AVLTree tree = new AVLTree();
//        int[] arr = {4,3,6,5,7,8};
//        int[] arr = {10,12, 8, 9, 7, 6};
//        int[] arr = {10, 11, 7, 6, 8, 9};
        int[] arr = {2,1,6,5,7,3};
        for (int i : arr) {
            tree.add(new BNode(i));
        }
        tree.infixOrder();
        System.out.println("根高度:"+tree.getRoot().getHeight());
        System.out.println("左子树高度:"+tree.getRoot().leftTreeHeight());
        System.out.println("右子树高度:"+tree.getRoot().rightTreeHeight());
    }
}

class AVLTree {
    private BNode root;

    public BNode getRoot() {
        return root;
    }

    //中序遍历
    public void infixOrder() {
        if (root != null) {
            root.infixOrder();
        } else {
            System.out.println("树为空!!!");
        }
    }

    //添加节点
    public void add(BNode nd) {
        if (root == null) {
            root = nd;
        } else {
            root.add(nd);
        }
    }

    //找到删除节点及其父节点
    public BNode search(int value) {
        if (root == null) {
            return null;
        } else {
            return root.search(value);
        }
    }
    public BNode searchParent(int value) {
        if (root == null) {
            return null;
        } else {
            if (root.getLeft() == null && root.getRight() == null) {
                return null;
            } else {
                return root.searchParent(value);
            }
        }
    }

    //删除节点
    public void delNode(int value) {
        if (root == null) {
            return;
        } else {
            BNode target = search(value);
            BNode parent = searchParent(value);//为空则只能是删根节点
            //有的删才行
            if (target != null) {
                if (parent != null) {
                    //删叶子节点
                    if (target.getLeft() == null && target.getRight() == null) {
                        if (parent.getLeft()!=null&&parent.getLeft().getValue() == value) {
                            parent.setLeft(null);
                        } else {
                            parent.setRight(null);
                        }

                        //删有双子树的节点
                    } else if (target.getLeft() != null && target.getRight() != null) {
                        //target为左子树
                        if (parent.getLeft().getValue() == value) {
                            int max = delLeftTreeMax(target);
                            target.setValue(max);
                            //target为右子树
                        } else {
                            int min = delRightTreeMin(target);
                            target.setValue(min);
                        }

                        //删有单子树的节点
                    } else {
                        //4种可能,左左,左右,右左,右右
                        if (parent.getLeft()!=null&& parent.getLeft().getValue() == value && target.getLeft() != null) {
                            parent.setLeft(target.getLeft());
                        } else if (parent.getLeft() != null && parent.getLeft().getValue() == value && target.getRight() != null) {
                            parent.setLeft(target.getRight());
                        } else if (parent.getRight().getValue() == value && target.getLeft() != null) {
                            parent.setRight(target.getLeft());
                        } else {
                            parent.setRight(target.getRight());
                        }
                    }
                } else {//没有父节点,说明就是删root,因为删除的节点存在
                    //叶子
                    if (root.getLeft() == null && root.getRight() == null) {
                        root = null;
                        //有双子树
                    } else if (root.getLeft() != null && root.getRight() != null) {
                        //用左子树最大或右子树最小都可以
                        root.setValue(delLeftTreeMax(root.getLeft()));

                        //有单子树
                    } else {
                        if (root.getRight() != null) {
                            root = root.getRight();
                        } else {
                            root = root.getLeft();
                        }
                    }
                }
            } else {
                System.out.println("删除的节点不存在!!!");
            }
        }
    }

    //删左子树,最大值
    public int delLeftTreeMax(BNode nd) {
        BNode temp = nd;
        while (true) {
            if (temp.getRight() == null) {
                break;
            }
            temp = temp.getRight();
        }
        delNode(temp.getValue());
        return temp.getValue();
    }
    //删右子树,最小值
    public int delRightTreeMin(BNode nd) {
        BNode temp = nd;
        while (true) {
            if (temp.getLeft() == null) {
                break;
            }
            temp = temp.getLeft();
        }
        delNode(temp.getValue());
        return temp.getValue();
    }
}

class BNode {
    private int value;
    private BNode left;
    private BNode right;

    public BNode(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public BNode getLeft() {
        return left;
    }

    public void setLeft(BNode left) {
        this.left = left;
    }

    public BNode getRight() {
        return right;
    }

    public void setRight(BNode right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return "BNode [value=" + value + "]";
    }

    //中序遍历
    public void infixOrder() {
        if (this.left != null) {
            this.left.infixOrder();
        }
        System.out.println(this);
        if (this.right != null) {
            this.right.infixOrder();
        }
    }

    //添加节点
    public void add(BNode bnode) {
        if (bnode == null) {
            return;
        }
        if (bnode.value < this.value) {
            if (this.left == null) {
                this.left = bnode;
            } else {
                this.left.add(bnode);
            }
        } else {
            if (this.right == null) {
                this.right = bnode;
            } else {
                this.right.add(bnode);
            }
        }

        //考虑左旋
//        if (rightTreeHeight() - leftTreeHeight() > 1) {
//            leftRotate();
//        }
        //考虑右旋
//        if (leftTreeHeight()-rightTreeHeight() > 1) {
//            rightRotate();
//        }
        //考虑双旋(单独的左旋/右旋可能不能解决问题)
        if (rightTreeHeight() - leftTreeHeight() > 1) {
            if (right != null && right.leftTreeHeight() > right.rightTreeHeight()) {
                right.rightRotate();
            }
            leftRotate();
            return;//省的走下面代码,影响效率(因为涉及递归调用)
        }
        if (leftTreeHeight()-rightTreeHeight() > 1) {
            if (left != null && left.leftTreeHeight() < left.rightTreeHeight()) {
                left.leftRotate();
            }
            rightRotate();
        }
    }

    //查找要删除节点
    public BNode search(int value) {
        if (this.value == value) {
            return this;
        } else if (value < this.value) {//这里对于==的处理与add方法保持一致
            if (this.left != null) {
                return this.left.search(value);
            }
        } else {
            if (this.right != null) {
                return this.right.search(value);
            }
        }
        return null;
    }

    //查找要删除的节点的父节点
    public BNode searchParent(int value) {
        if ((this.left != null && this.left.value == value) ||
                this.right != null && this.right.value == value) {
            return this;
        } else {
            if (value < this.value && this.left != null) {
                return this.left.searchParent(value);
            } else if (value >= this.value && this.right != null) {
                return this.right.searchParent(value);
            } else {
                return null;
            }
        }
    }

    //返回当前节点的高度
    public int getHeight() {
        return Math.max(left == null ? 0 : left.getHeight(), right == null ? 0 : right.getHeight())+1;
    }
    //左子树高度
    public int leftTreeHeight() {
        if (left == null) {
            return 0;
        } else {
            return left.getHeight();
        }
    }
    //右子树高度
    public int rightTreeHeight() {
        if (right == null) {
            return 0;
        } else {
            return right.getHeight();
        }
    }

    //左旋
    public void leftRotate() {
        BNode newNode = new BNode(value);
        newNode.left = this.left;
        newNode.right = this.right.left;
        this.setValue(right.value);
        this.setLeft(newNode);
        this.setRight(this.right.right);
    }
    //右旋
    public void rightRotate() {
        BNode newNode = new BNode(value);
        newNode.right = this.right;
        newNode.left = this.left.right;
        this.setValue(left.value);
        this.setRight(newNode);
        this.setLeft(this.left.left);
    }
}