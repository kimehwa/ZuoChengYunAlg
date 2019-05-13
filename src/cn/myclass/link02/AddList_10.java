package cn.myclass.link02;

import java.util.Stack;

public class AddList_10 {
    public class Node {
        int value;
        Node next;

        public Node(int data) {
            this.value = data;
        }
    }

    /**
     * 题目：两个单链表生成相加链表
     * 假设链表中每一个节点的值都在0~9之间，那么链表整体就可以代表一个整数
     * 例如9->3->7,可以代表整数937,给定两个这种链表的头结点head1和head2，请生成代表两个整数相加值得结果链表
     * 比如9->3->7，链表2为6->3,相加后为1->0->0->0
     */
    public Node addList1(Node head1, Node head2) {
        /**
         * 自己想的直接思路是把head1与head2各自转化成数字，然后相加后的结果转化为一个数组.
         * 自己想的这种情况可能链表可能很长，然后可以表达一个很大的整数，int类型可以溢出，不推荐这种方法
         * 答案是用两个stack进行完成
         */
        Stack<Integer> s1 = new Stack<>();
        Stack<Integer> s2 = new Stack<>();
        while (head1 != null) {
            s1.push(head1.value);
            head1 = head1.next;
        }
        while (head2 != null) {
            s2.push(head2.value);
            head2 = head2.next;
        }
        int ca = 0;//ca 为进位输入
        int n1 = 0;
        int n2 = 0;
        int n = 0;
        Node node = null;
        Node pre = null;
        //这种进位输出数字的题目希望通过这个题可以掌握一下。

        while (!s1.isEmpty() || !s2.isEmpty()) {
            n1 = s1.isEmpty() ? 0 : s1.pop();//这里首先还是要判断二者是否为空
            n2 = s2.isEmpty() ? 0 : s2.pop();
            n = n1 + n2 + ca;
            pre = node;//下三行是生成新的列表，用pre代替前面的，然后node生成新的丽娜表，在吧生成新的链表添加到头结点。
            node = new Node(n % 10);
            node.next = pre;
            ca = n / 10; //更新ca的值
        }
        if (ca == 1) {
            pre = node;//这是之前的链表
            node = new Node(1);
            node.next = pre;
        }
        return node;
    }

    /**
     * 方法2：利用链表的逆序，可以节省使用栈的空间
     * 方法：将链表进行反转。然后可以利用从前到后的顺序进行相加并运行,最后需要反转链表
     */
    public Node addList2(Node head1, Node head2) {
        //如何反转一个链表？
        head1 = reverseList(head1);
        head2 = reverseList(head2);
        int ca = 0;
        int n1 = 0;
        int n2 = 0;
        int n = 0;
        Node c1 = head1;
        Node c2 = head2;
        Node node = null;
        Node pre = null;
        while (c1 != null || c2 != null) {
            n1 = c1 != null ? c1.value : 0;
            n2 = c2 != null ? c2.value : 0;
            n = n1 + n2 + ca;
            pre = node;
            node = new Node(n % 10);
            node.next = pre;
            ca = n / 10;
            c1 = c1 != null ? c1.next : null;
            c2 = c2 != null ? c2.next : null;
        }
        if (ca == 1) {
            pre = node;
            node = new Node(1);
            node.next = pre;
        }
        reverseList(head1);
        reverseList(head2);
        return node;//node不是反的  直接生成了正确的结果

    }

    public Node reverseList(Node head) {
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

}
