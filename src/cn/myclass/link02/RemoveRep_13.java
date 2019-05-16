package cn.myclass.link02;

import java.util.HashMap;
import java.util.HashSet;

public class RemoveRep_13 {
    /**
     * 给定一个无序单链表的头结点head，删除其中值重复出现的节点
     * 按照以下要求实现两种方法：
     * 1.如果链表的长度为N，时间复杂度到达O（N）
     * 2.额外空间复杂度达到O（1）
     */
    public class Node {
        public int value;
        public Node next;

        public Node(int data) {
            this.value = data;
        }
    }

    /**
     * 方法1 实现了用hashset来判断是否出现了重复的元素，时间复杂度为O（n）遍历了一次 空间复杂度为O（少于n）
     * 要删除链表的元素，一定要标记待删除元素的前一个元素。这里的方法要记住了
     *
     * @param head
     */
    public void removeRep1(Node head) {
        if (head == null) {
            return;
        }

        HashSet<Integer> set = new HashSet<>();//新知识 总结hashset与hashmap的区别https://www.cnblogs.com/codercui/p/6841730.html
        Node pre = head;//要删除一个节点，一定要找到要删除节点的前一个节点  比如pre；
        Node cur = head.next;//因为第一个先安装上去了
        set.add(head.value);
        while (cur != null) {
            if (set.contains(cur.value)) {
                pre.next = cur.next;
            } else {
                set.add(cur.value);
                pre = cur;//让pre向后移动一位
            }
            cur = cur.next;

        }

    }

    /**
     *类似选择排序一样，时间复杂度为O（n2），空间复杂度为O（1）
     * @param head
     */
    public void removeRep2(Node head) {
        Node cur = head;
        Node pre = null;
        Node next = null;
        while (cur != null) {
            pre = cur;
            next = cur.next;
            while (next != null) {
                if (cur.value == next.value) {
                    pre.next = cur.next;
                } else {
                    pre = next;
                }
                next = next.next;
            }
            cur = cur.next;
        }

    }

}
