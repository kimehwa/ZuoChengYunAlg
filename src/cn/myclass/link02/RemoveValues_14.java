package cn.myclass.link02;

import java.util.Stack;

public class RemoveValues_14 {
    /**
     * 在单链表中删除指定值的节点
     * 给定一个链表的头结点head和一个整数num，请实现函数将值为num的节点全部删除
     */
    public class Node {
        public int value;
        public Node next;

        public Node(int data) {
            this.value = data;
        }
    }

    public Node removeValues1(Node head, int num) {
        /**
         * 先找到第一个不是head.value ==num的节点 记为头结点
         * 然后用两个变量进行遍历得到结论
         */
        while (head != null) {
            if (head.value != num) {
                break;
            }
            head = head.next;
        }
        Node pre = head;
        Node cur = head;
        while (cur != null) {
            if (cur.value == num) {//这里是因为第一个一定不是等于num的数字了
                pre.next = cur.next;
            } else {
                pre = cur;
            }
            cur = cur.next;
        }
        return head;
    }

    public Node removeValues2(Node head, int num) {
        /**
         * 用栈或者其他容器收集节点，将值不等于num的节点用栈收集起来，完成之后重新连接即可。
         * 最后将栈底的头结点返回作为首节点
         */
        Stack<Node> stack = new Stack<>();
        //Node cur = head;//这里不用cur？
        while (head != null) {
            if (head.value != num) {
                stack.push(head);
            }
            head = head.next;
        }
        while (!stack.isEmpty()) {
            stack.peek().next = head;//这里不会把链表反转吗？
            //其实并不会反转，初始的head 为null，然后这个循环一下取出来两个进行连接，每一个连接到上一个上面
            head = stack.pop();
        }
        return head;
    }

}
