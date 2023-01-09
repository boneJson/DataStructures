package com.zxk.tree.binarytree;

//二叉树的中序线索化及其线型遍历
public class Tree03_ThreadedBinaryTreeDemo {
    public static void main(String[] args) {

        //手动创建二叉树
        Ho root = new Ho(1, "tom");
        Ho node2 = new Ho(3, "jack");
        Ho node3 = new Ho(6, "smith");
        Ho node4 = new Ho(8, "mary");
        Ho node5 = new Ho(10, "king");
        Ho node6 = new Ho(14, "dim");

        root.setLeft(node2);
        root.setRight(node3);
        node2.setLeft(node4);
        node2.setRight(node5);
        node3.setLeft(node6);

        ThreadedBinaryTree threadedBinaryTree = new ThreadedBinaryTree();
        threadedBinaryTree.setRoot(root);

        //测试
        threadedBinaryTree.threadedNode();
//        System.out.println(node5.getLeft().toString());
//        System.out.println(node5.getRight().toString());

        threadedBinaryTree.threadedList();


    }
}

//二叉树
class ThreadedBinaryTree {
    private Ho root;
    private Ho pre = null;//前驱节点

    public void setRoot(Ho root) {
        this.root = root;
    }

    //三种方式遍历树,从root起
    public void preOrder() {
        if (root != null) {
            root.preOrder();
        } else {
            System.out.println("二叉树为空");
        }
    }
    public void infixOrder() {
        if (root != null) {
            root.infixOrder();
        } else {
            System.out.println("二叉树为空");
        }
    }
    public void postOrder() {
        if (root != null) {
            root.postOrder();
        } else {
            System.out.println("二叉树为空");
        }
    }

    //三种方式查找树,从root起
    public void preOrderSearch(int no) {
        if (root != null) {
            if (root.preOrderSearch(no) != null) {
                System.out.println("查找结果为" + root.preOrderSearch(no));
            } else {
                System.out.println("没找到!!!");
            }
        } else {
            System.out.println("二叉树为空");
        }
    }
    public void infixOrderSearch(int no) {
        if (root != null) {
            if (root.infixOrderSearch(no) != null) {
                System.out.println("查找结果为" + root.infixOrderSearch(no));
            } else {
                System.out.println("没找到!!!");
            }
        } else {
            System.out.println("二叉树为空");
        }
    }
    public void postOrderSearch(int no) {
        if (root != null) {
            Ho result= root.postOrderSearch(no);
            if (result != null) {
                System.out.println("查找结果为" + result);
            } else {
                System.out.println("没找到!!!");
            }
        } else {
            System.out.println("二叉树为空");
        }
    }

    //删除节点
    public void delNode(int no) {
        if (root == null) {
            System.out.println("二叉树空,无法删除");
        } else {
            //删除的节点就是root,直接置空,否则遍历删除
            if (root.getNo() == no) {
                root = null;
            } else {
                root.delNode(no);
            }
        }
    }

    //中序线索化
    //切入点:线索化无非就是改变左右指向,从null改为指向前驱和后继节点,但是遍历到当前节点时只经过了前驱节点,不可能拿到后继节点,所以...
    //中序遍历的基本思想,处理当前节点时:若需要线索化则改变当前节点的左指向和类型(完成前驱线索化),
    //同时通过改变前驱节点的右指向和类型(完成后继线索化),2步处理完更新前驱节点
    //线索化后再遍历会死龟!!!
    public void threadedNode(Ho node) {
        if (node == null) {
            return;
        }

        threadedNode(node.getLeft());
        if (pre !=  null&&pre.getRight()==null) {
            pre.setRight(node);
            pre.setRightType(1);
        }
        if (node.getLeft() == null) {
            node.setLeft(pre);
            node.setLeftType(1);
        }
        pre = node;

        threadedNode(node.getRight());
    }
    //中序线索化方法重载
    public void threadedNode() {
        this.threadedNode(root);
    }

    //线型方式遍历(非递归),实现线索化二叉树的中序遍历
    //逻辑:左子树上寻找线索化节点并打印,持续输出后继节点,没有后继节点节点就取右子树后找线索化节点-->循环
    //宏观上:就是寻找线索化节点和它的后继节点
    public void threadedList() {
        Ho node = root;
        while (node!=null) {
            //找到线索化节点并打印8,打印8
            //找到线索化节点并打印10,打印10
            //找到线索化节点并打印14,打印14
            while (node.getLeftType()==0) {
                node = node.getLeft();
            }
            System.out.println(node);
            //后继节点打印3
            //后继节点打印1
            //后继节点打印6
            while ( node.getRightType() == 1) {
                node = node.getRight();
                System.out.println(node);
            }
            //右子节点10
            //右子节点6
            //右子节点null,退出
            node = node.getRight();
        }
    }
}

//树节点
//编写三种遍历方式
class Ho {
    private int no;
    private String name;
    private Ho left;
    private Ho right;
    //新增指针类型,0表示子树,1表示前驱/后继节点,问:有啥用?答:遍历防止死龟
    private int leftType;
    private int rightType;

    public Ho(int no, String name) {
        this.no = no;
        this.name = name;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Ho getLeft() {
        return left;
    }

    public void setLeft(Ho left) {
        this.left = left;
    }

    public Ho getRight() {
        return right;
    }

    public void setRight(Ho right) {
        this.right = right;
    }

    public int getLeftType() {
        return leftType;
    }

    public void setLeftType(int leftType) {
        this.leftType = leftType;
    }

    public int getRightType() {
        return rightType;
    }

    public void setRightType(int rightType) {
        this.rightType = rightType;
    }

    @Override
    public String toString() {
        return "HoNode [no=" + no + ", name=" + name + "]";
    }

    //前序遍历
    public void preOrder() {
        System.out.println(this);
        if (left != null) {
            left.preOrder();
        }
        if (right != null) {
            right.preOrder();
        }
    }
    //中序遍历,线索化后的
//    public void infixOrder() {
//        if (left != null&& leftType==0) {
//            left.infixOrder();
//        }
//        System.out.println(this);
//        if (right != null&& rightType==0) {
//            right.infixOrder();
//        }
//    }
    //中序遍历
    public void infixOrder() {
        if (left != null) {
            left.infixOrder();
        }
        System.out.println(this);
        if (right != null) {
            right.infixOrder();
        }
    }
    //后序遍历
    public void postOrder() {
        if (left != null) {
            left.postOrder();
        }
        if (right != null) {
            right.postOrder();
        }
        System.out.println(this);
    }

    //前序查找
    //这里当前节点的查找结果毫无疑问若正确可以直接返回
    //子节点的查找结果必须先收集再判断,若正确直接返回,为空则在方法末尾返回
    //  =>如果不立即判断返回则当查找到第一个叶子节点直接必出结果,即路径上遇到结果就返回,到叶子都没遇到就返回null了
    //整体判断逻辑:当前节点能否返回(能否返回:找到正确结果才返回)-->左递归能否返回-->右递归直接返回-->补上方法返回值null
    //总结:以前序为例,无论是遍历还是查找,对于当前节点的操作其实只是左递归前的附带操作,
    //      因此从执行结果来看,整体是先从上往下执行,(中+左)=>(中+左)=>(中+左)=>叶子,然后开始从下层处理到上层
    public Ho preOrderSearch(int no) {

        if (this.no == no) {
            return this;
        }

        if (left != null) {
            if (left.preOrderSearch(no) != null) {
                return left.preOrderSearch(no);
            }
        }

        if (right != null) {
            return right.preOrderSearch(no);
        }

        return null;
    }
    //中序查找
    public Ho infixOrderSearch(int no) {

        if (left != null) {
            if (left.infixOrderSearch(no) != null) {
                return left.infixOrderSearch(no);
            }
        }
        if (this.no == no) {
            return this;
        }
        if (right != null) {
            if (right.infixOrderSearch(no) != null) {
                return right.infixOrderSearch(no);
            }
        }
        return null;
    }
    //后序查找
    public Ho postOrderSearch(int no) {
        Ho result = null;

        if (left != null) {
            result = left.postOrderSearch(no);
            if (result != null) {
                return result;
            }
        }
        if (right != null) {
            result = right.postOrderSearch(no);
            if (result != null) {
                return result;
            }
        }

        //统计比较多少次,以后序查找为例,
        //注意为了得到正确的比较次数,需要将递归结果先收集再做判断或返回,否则次数会翻倍
        System.out.println("一次");
        if (this.no == no) {
            return this;
        }
        return result;
    }

    //删除节点
    //核心:在当前节点判断是否删除子节点(因为单向二叉树只能操作自己的左右指针),注意根节点的判断在树类中
    //若删除了子节点,则直接返回结束方法(递归),否则调用递归删除
    public void delNode(int no) {
        if (this.left != null && this.left.no == no) {
            this.left = null;
            return;
        }
        if (this.right != null && this.right.no == no) {
            this.right = null;
            return;
        }
        if (this.left != null) {
            this.left.delNode(no);
        }
        if (this.right != null) {
            this.right.delNode(no);
        }
    }
}