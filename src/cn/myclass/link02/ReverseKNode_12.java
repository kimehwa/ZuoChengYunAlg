package cn.myclass.link02;

import java.util.Stack;

public class ReverseKNode_12 {
    /**
     * 问题：给定一个单链表的头结点head，实现一个调整单链表的函数，使得每k个节点之间逆序
     * 如果最后不够k个节点一组，则不调整最后几个节点
     * 例如：链表1，2，3，4，5，6，7，8，null，k=3
     * 调整后：3，2，1，6，5，4，7，8，null。7，8不调整，因为不够一组
     */
    public class Node {
        public int value;
        public Node next;

        public Node(int data) {
            this.value = data;
        }
    }

    public Node reversekNode(Node head, int k) {//这种功能分为了两个函数
        if (k < 2) {
            return head;
        }
        Stack<Node> stack = new Stack<>();
        Node newHead = head;
        Node cur = head;//设置了3个指针，一个以前一个现在一个接下来的一个
        Node pre = null;
        Node next = null;
        while (cur != null) {
            next = cur.next;
            stack.push(cur);
            if (stack.size() == k) {
                //这里实现的是一个resign函数是重新排序
                pre = resign1(stack, pre, next);//分为两个函数的这种算法题好麻烦的,
                newHead = newHead == head ? cur : newHead;//这里是为什么？
            }
            cur = next;
        }
        return newHead;
    }

    public Node resign1(Stack<Node> stack, Node left, Node right) {
        /**
         * 这里的函数是有一个左边界和右边界也就是比如1，2，3，4，5，6，7，给了3为left节点，7为right结点。正
         * 要正确的把这5个节点连在一起
         */
        Node cur = stack.pop();
        if (left != null) {
            left.next = cur;
        }
        Node next = null;
        while (!stack.isEmpty()) {
            next = stack.pop();
            cur.next = next;
            cur = next;
        }
        cur.next = right;
        return cur;

    }

    /**
     * 方法2：不需要栈结构，在链表中直接调整
     * 用变量记录每一组开始的第一个节点和最后一个节点，然后直接逆序调整，把这一组的节点都逆序
     * 注意：第一组节点的特殊处理，以及之后每一组在逆序重连之后，需要将该组的第一个节点（原来是最后一个节点）被
     * 之前组的最后一个节点连接上，将该组的最后一个节点（原来是第一个节点）连接到下一个节点？
     * 这个代码写起来比上一个还要复杂一些
     */
    public Node reserveKNode2(Node head, int k) {
        if (k < 2) {
            return head;
        }
        Node cur = head;
        Node start = null;
        Node pre = null;
        Node next = null;//next是内部更新的时候用
        int count = 1;//计数用
        while (cur != null) {
            next = cur.next;
            if (count == k) {
                start = pre == null ? head : pre.next;//这里还是按照次序的呀？
                head = pre == null ? cur : head;
                resign2(pre, start, cur, next);
                pre = start;
                count = 0;
            }
            count++;
            cur = next;
        }
        return head;
    }

    public void resign2(Node left, Node start, Node end, Node right) {
        Node pre = start;
        Node cur = start.next;
        Node next = null;
        while (cur != right) {
            next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        if (left != null) {
            left.next = end;//正确的处理左右两个边界的连接
        }
        start.next = right;
    }
}
