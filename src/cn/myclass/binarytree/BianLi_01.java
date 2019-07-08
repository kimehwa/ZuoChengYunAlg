package cn.myclass.binarytree;

import java.util.Stack;

public class BianLi_01 {
    public class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            this.value = data;
        }
    }

    /**
     * 用递归方式和非递归方式，分别按照二叉树先序，中序和后序打印所有的节点。我们约定：
     * 先序遍历顺序为根，左，右；中序遍历顺序为左，根，右；后序遍历顺序为左，右，根；
     * 这个题2月曾经做过，几个月没写了估计忘的差不多的。
     */
    public void preOrderRecur(Node head) {//中序以及后序只需要改变print的位置即可
        //递归函数的实现比较容易，非递归版本的实现如何实现呢？
        if (head == null) {
            return;
        }
        System.out.println(head.value + "");
        preOrderRecur(head.left);
        preOrderRecur(head.right);
    }

    /**
     * 非递归的版本，递归都可以改为非递归，只要压进栈保存即可
     */
    public void preOrderUnRecur(Node head) {
        /**
         * 前序1，2，4，5，3，6，7，我只是想起来了中序的问题，说明不复习的话，迟早会忘的光光的
         * 中序4251637  后序如果遍历是什么样的呢？
         */
        System.out.println("pre-order: ");
        if (head != null) {
            Stack<Node> stack = new Stack<>();
            stack.add(head);
            while (!stack.isEmpty()) {
                head = stack.pop();
                System.out.println(head.value + " ");
                if (head.right != null) {
                    stack.push(head.right);
                }
                if (head.left != null) {
                    stack.push(head.left);
                }
            }
        }
        System.out.println();
    }

    public void inOrderUnRecur(Node head) {
        if (head != null) {
            Stack<Node> stack = new Stack<>();
            while (!stack.isEmpty() || head != null) {//因为这里的判断条件是这样的
                if (head != null) {
                    stack.push(head);
                    head = head.left;
                } else {
                    head = stack.pop();
                    System.out.println(head.value + " ");
                    head = head.right;
                }
            }
        }
    }
    public void posOrderUnRecur1(Node head){
        if (head!=null){
            Stack<Node> s1 = new Stack<>();
            Stack<Node> s2 = new Stack<>();
            s1.push(head);
            while(!s1.isEmpty()){

                head = s1.pop();
                s2.push(head);
                if (head.left!=null){
                    s1.push(head.left);
                }
                if (head.right!=null){
                    s1.push(head.right);
                }
            }
            while(!s2.isEmpty()){
                System.out.println(s2.pop().value+" ");
            }
        }
        System.out.println();
    }
}
