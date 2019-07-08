package cn.myclass.binarytree;

public class Morris_05 {
    public class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            this.value = data;
        }
    }
    /**
     * 题目，给定一颗二叉树的头结点head，完成二叉树的先序，中序，后序遍历。如果二叉树的节点数为
     * N，则要求时间复杂度为N，空间复杂度为1
     */
    public void morris(Node head){
        /**if (head == null){
            return;
        }
        Node cur = head;
        while (cur!=null){
            if(cur.left!=null){
                Node mostRight = cur.left;
                while (mostRight.right!=null&&mostRight.right!=cur){
                    mostRight = mostRight.right;
                }
                if (mostRight==null){
                    mostRight.right=cur;
                    cur =cur.left;

                }else{
                    cur = mostRight.right;
                    mostRight.right = null;
                }
            }
            cur = cur.right;

        }**/
        if (head==null){
            return;
        }
        Node cur = head;
        Node mostRight = null;
        while (cur!=null){
            if (cur.left!=null){
                mostRight = cur.left;
                while (mostRight.right!=null&&mostRight.right !=cur){//找到其最右的端点,第二个判断条件是防止进入死循环
                    mostRight=mostRight.right;
                }
                if (mostRight.right ==null){//如果是空的话 就给其赋值，然后左推
                    mostRight.right = cur;
                    cur = cur.left;
                    continue;//continue是让循环继续进行
                }else{
                    mostRight.right = null;
                }
                cur = cur.right;
            }

        }
    }
    //用morris遍历来遍历前中后序，1对于cur只能到达一次的节点，cur到达后直接打印
    //2对于cur可以到达两次的节点，cur第一次到达的时候不打印，第二次到达的时候打印，但是这个如何改为代码呢？
    public void morrisPre(Node head){//morris的前序遍历
        if (head == null){
            return ;
        }
        Node cur= head;
        Node mostRight = null;
        while(cur!=null){
            if(cur.left!=null){
                mostRight= cur.left;
                while(mostRight.right!=null&&mostRight.right!=cur){
                    mostRight=mostRight.right;
                }
                if(mostRight.right ==null){
                    mostRight.right=cur;
                    System.out.println(cur.value+" ");
                    cur=cur.left;
                    continue;
                }else{
                    mostRight.right=null;
                }
            }else{
                System.out.println(cur.value+" ");
            }
            cur = cur.right;
        }

    }
}
