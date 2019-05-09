package cn.myclass.link02;

import java.util.HashMap;

public class CopyWithListRand_09 {
    /**
     * 复制含有随机指针节点的链表
     * 给定一个含有节点类型组成的无环单链表的头结点head，实现一个函数完成这个链表中所有结构的复制
     * 进阶：不使用额外的数据结构，只用有限几个变量，且在时间复杂度为O（N）内完成原问题要实现的函数
     */
    public class Node {
        public int value;
        public Node next;
        public Node rand;

        public Node(int data) {
            this.value = data;
        }
    }

    /**
     * 普通解法使用到哈希表结构
     * 1.从左到右遍历链表，对每个节点都复制生成相应的副本节点，然后将对应关系放到哈希表map中
     * 2.再从左到右遍历链表，此时就可以设置每一个副本节点的next和rand值，然后返回第一个节点即可
     * 哈希表，HashMap，get与put方法，这是最基础的了
     */
    public Node copyWithListRand1(Node head) {
        HashMap<Node, Node> map = new HashMap<>();
        Node cur = head;
        while (cur != null) {
            map.put(cur, new Node(cur.value));
            cur = cur.next;
        }
        cur = head;
        while (cur != null) {
            map.get(cur).next = map.get(cur.next);//必须这样，这样才能把复制后的链表加起来，否则都到了开始的链表中 就混淆了
            map.get(cur).rand = map.get(cur.rand);
            cur = cur.next;
        }
        return map.get(head);
    }

    public Node copyWithListRand2(Node head) {
        /**
         * 1.从左到右遍历链表，对每个节点cur都生成相应的副本节点copy，然后把copy放在每一个cur和下一个要遍历的节点的中间
         * 2.从左到右遍历链表，设置每一个副本节点的rand指针
         * 3.步骤二完成之后，此时所有的节点和副本节点串联在一起，将其分离出来就好了
         *
         */
        if (head == null) {
            return null;
        }
        Node cur = head;//修改cur会修改head的点吗？会
        Node next = null;
        while (cur != null) {//步骤1有个疑惑，这里的生成的新链表是直接在cur里吗
            next = cur.next;//保存cur节点的next节点
            cur.next = new Node(cur.value);//新生成node节点并保存在cur的下一个节点
            cur.next.next = next;//将新生成的node节点的下一个节点连接到保存的cur节点
            cur = next;//将cur节点后推
        }
        //步骤2：遍历链表，找到所有节点的rand节点
        cur = head;
        Node copyNode = null;//跟我做的差不多
        while (cur != null) {
            next = cur.next.next;
            copyNode = cur.next;
            copyNode.rand = cur.rand != null ? cur.rand.next : null;
            cur = next;
        }
        //步骤三：如何分离两个链表？在图纸上打一个草稿就可以了
        Node res = head.next;
        cur = head;
        while (cur != null) {
            next = cur.next.next;
            copyNode = cur.next;
            cur.next = next;
            copyNode.next = next != null ? next.next : null;
            cur = next;
        }
        return res;

    }
}
