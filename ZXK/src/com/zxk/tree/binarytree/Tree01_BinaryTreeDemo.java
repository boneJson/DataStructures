package com.zxk.tree.binarytree;


//二叉树的前序、中序、后序遍历,前中后序查找,删除三类方法
//主类+树类+节点类
public class Tree01_BinaryTreeDemo {
    public static void main(String[] args) {

        //手动创建二叉树
        BinaryTree tree = new BinaryTree();
        Hero root  = new Hero(1, "宋江");
        Hero node2 = new Hero(2, "吴用");
        Hero node3 = new Hero(3, "卢俊义");
        Hero node4 = new Hero(4, "林冲");
        Hero node5 = new Hero(5, "关胜");
        Hero node8 = new Hero(8, "晁盖");
        Hero node7 = new Hero(7, "武松");

        root.setLeft(node2);
        root.setRight(node3);
        node3.setRight(node4);
        node3.setLeft(node5);
        node2.setLeft(node8);
        node2.setRight(node7);
        tree.setRoot(root);

        //遍历
        tree.preOrder();//12354
//        tree.infixOrder();//21534
//        tree.postOrder();//25431

        //查找
        tree.postOrderSearch(4);

        //删除
//        tree.delNode(3);
//        System.out.println("删除后:");
//        tree.preOrder();
    }
}

//二叉树
class BinaryTree {
    private Hero root;

    public void setRoot(Hero root) {
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
            Hero result= root.postOrderSearch(no);
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
}

//树节点
//编写三种遍历方式
class Hero {
    private int no;
    private String name;
    private Hero left;
    private Hero right;

    public Hero(int no, String name) {
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

    public Hero getLeft() {
        return left;
    }

    public void setLeft(Hero left) {
        this.left = left;
    }

    public Hero getRight() {
        return right;
    }

    public void setRight(Hero right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return "HeroNode [no=" + no + ", name=" + name + "]";
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
    public Hero preOrderSearch(int no) {

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
    public Hero infixOrderSearch(int no) {

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
    public Hero postOrderSearch(int no) {
        Hero result = null;

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