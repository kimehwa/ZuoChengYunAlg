package cn.myclass.link02;

public class ReverseList_03 {
    public class Node {
        /**
         * 反转单链表，采用了两个链表中的指针进行循环？
         * 这个是迭代法
         */
        public int value;
        public Node next;

        public Node(int data) {
            this.value = data;
        }
    }

    public Node reverseNode(Node head) {
        Node pre = null;
        Node next = null;
        while (head != null) {
            next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }
        return pre;
    }

    public class DoubleNode {
        public int value;
        public DoubleNode last;
        public DoubleNode next;

        public DoubleNode(int data) {
            this.value = data;
        }
    }

    public DoubleNode reverseDoubleNode(DoubleNode head) {
        //反转双链表
        DoubleNode pre = null;
        DoubleNode next = null;
        while (head != null) {
            next = head.next;
            head.next = pre;
            head.last = next;
            pre = head;
            head = next;
        }
        return pre;
    }

}
