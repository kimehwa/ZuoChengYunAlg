package cn.myclass.link02;

public class InsertNum_18 {
    /**
     * 一个环形单链表从头结点head开始不降序，同时由最后的节点指会头结点，给定一个这样的环形单链表的头结点head和一个整数num，
     * 请生成节点值为num的新节点，并插入到这个环形链表中，保证调整后的链表依然有序。
     * <p>
     * 思路:分为3个方面的思路，一个大小处于二者之间
     */
    public class Node {
        public int value;
        public Node next;

        public Node(int data) {
            this.value = data;
        }
    }

    public Node insertNum(Node head, int num) {
        /**Node cur = head.next;
         Node pre = head;
         if (pre.value > num) {
         Node newHead = new Node(num);
         newHead.next = pre;
         return newHead;
         }
         while (cur != head) {
         if (cur.value < num) {
         pre = cur;
         cur = cur.next;
         } else {
         Node insert = new Node(num);
         pre.next = insert;
         insert.next = cur;
         return head;
         }
         }
         return head;
         }**/
        Node node = new Node(num);//个人感觉跟我自己写的一样的
        if (head == null) {
            node.next = node;
            return node;
        }
        Node pre = head;
        Node cur = head.next;
        while (cur != head) {
            if (pre.value <= num && cur.value >= num) {
                break;
            }
            pre = cur;
            cur = cur.next;
        }
        pre.next = node;
        node.next = cur;
        return head.value < num ? head : node;
    }
}