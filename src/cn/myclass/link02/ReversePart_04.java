package cn.myclass.link02;

public class ReversePart_04 {
    /**
     * 给定一个单向链表的头结点head，以及两个整数 from和to，在单向链表上把第from点到to这个结点的一部分进行反转
     * 要求：1如果链表长度为N，时间复杂度为O（N），额外空间复杂度要求O（1），
     * 2.如果不满足1<=from<=to<N,则不用调整
     */
    public class Node {
        public int value;
        public Node next;

        public Node(int data) {
            this.value = data;
        }
    }

    public Node reversePart(Node head, int from, int to) {
        int len = 0;
        Node node1 = head;
        Node fPre = null;
        Node tPos = null;
        while (node1 != null) {
            len++;
            fPre = len == from - 1 ? node1 : fPre;//pre是反转的前一个值
            tPos = len == to + 1 ? node1 : tPos;//pos是反转的后一个值
            node1 = node1.next;
        }
        if (from > to || from < 1 || to > len) {//来判断是否相等
            return head;
        }
        node1 = fPre == null ? head : fPre.next;
        Node node2 = node1.next;
        node1.next = tPos;//这一步是有什么用的？
        Node next = null;
        while (node2 != tPos) {
            next = node2.next;
            node2.next = node1;
            node1 = node2;
            node2 = next;//反转链表的基础操作
        }
        if (fPre != null) {
            fPre.next = node1;
            return head;
        }
        return node1;

    }
}
