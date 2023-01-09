package com.zxk.tree.apply;

//二叉排序树
public class App04_BinarySortTree {
    public static void main(String[] args) {
        int[] arr = {7, 3, 10, 12, 5, 1, 9,2,11};
        BinarySortTree tree = new BinarySortTree();
        for (int i : arr) {
            tree.add(new Nd(i));
        }
        tree.infixOrder();
        System.out.println("-------删除前------");

        tree.delNode(11);
        tree.delNode(2);
        tree.delNode(9);
        tree.delNode(5);
        tree.delNode(1);
        tree.delNode(12);
        tree.delNode(3);
        tree.delNode(10);
        tree.delNode(7);
        tree.infixOrder();

    }
}

class BinarySortTree {
    private Nd root;

    public Nd getRoot() {
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
    public void add(Nd nd) {
        if (root == null) {
            root = nd;
        } else {
            root.add(nd);
        }
    }

    //找到删除节点及其父节点
    public Nd search(int value) {
        if (root == null) {
            return null;
        } else {
            return root.search(value);
        }
    }
    public Nd searchParent(int value) {
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
            Nd target = search(value);
            Nd parent = searchParent(value);//为空则只能是删根节点
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
    public int delLeftTreeMax(Nd nd) {
        Nd temp = nd;
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
    public int delRightTreeMin(Nd nd) {
        Nd temp = nd;
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
class Nd {
    private int value;
    private Nd left;
    private Nd right;

    public Nd(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public Nd getLeft() {
        return left;
    }

    public void setLeft(Nd left) {
        this.left = left;
    }

    public Nd getRight() {
        return right;
    }

    public void setRight(Nd right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return "Nd [value=" + value + "]";
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
    public void add(Nd nd) {
        if (nd == null) {
            return;
        }
        if (nd.value < this.value) {
            if (this.left == null) {
                this.left = nd;
            } else {
                this.left.add(nd);
            }
        } else {
            if (this.right == null) {
                this.right = nd;
            } else {
                this.right.add(nd);
            }
        }
    }

    //查找要删除节点
    public Nd search(int value) {
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
    public Nd searchParent(int value) {
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
}