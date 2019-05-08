package cn.myclass.link02;

public class RemoveMidNode {
    public class Node {
        public int value;
        public Node next;

        public Node(int data) {
            this.value = data;
        }
    }

    /**
     * 删除中间部分的节点，链表的长度为2，删除第一个，3删除第二个，4也删除第二个，5第三个，6三个
     * 每次链表的长度增加2，要删除的节点就后移一个节点
     * 删除一个节点，我们要找到待删除节点的前一个节点
     * 但是这个删除这个怎么转化为代码？,两个指针，一个快指针一个慢指针
     */
    public Node removeMidNode(Node head) {
        if (head == null || head.next == null) {
            return head;
        }
        if (head.next.next == null) {
            return head.next;
        }
        Node pre = head;
        Node cur = head.next.next;
        while (cur.next != null && cur.next.next != null) {//这里的两个为什么要判断两个
            pre = pre.next;
            cur = cur.next.next;
        }
        pre.next = pre.next.next;
        return head;

    }

    public Node removeByRatio(Node head, int a, int b) {
        /**
         * java的向上取整是math.ceil
         * 这个是删除a/b的问题
         */
        if (a < 1 || a > b) {
            return head;
        }
        if (head.next.next == null) {
            return head.next;
        }
        Node cur = head;
        int n = 0;
        while (cur != null) {
            n++;
            cur = cur.next;
        }
        n = (int) Math.ceil(((double) (a * n)) / (double) b);
        //问题变为删除链表的第n个元素
        if (n > 0) {
            cur = head;
            while (--n != 1) {
                cur = cur.next;
            }
            cur.next = cur.next.next;
        }
        return head;
        //这里有个疑惑：为什么cur修改了此链表，返回的是head，前面修改了cur  head没有变化呢？
        //个人只能理解修改了cur与head指向了同一个内存地址，然后cur修改了此地址的东西导致了head也发生了变化


    }
}
