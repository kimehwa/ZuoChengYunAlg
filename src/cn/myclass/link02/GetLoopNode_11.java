package cn.myclass.link02;

/**
 * 两个单链表相交的一系列问题
 * 问题：单链表可能有环，也可能无环，给定两个单链表的头结点 head1和head2，这两个链表可能相交，也可能不想交
 * 请实现一个函数，如果两个链表相交，请返回相交的第一个节点，如果不相交，返回null即可
 * <p>
 * 1解决的问题有三个。1是解决如何判断一个链表是否有环
 * 2 判断两个无环链表是否相交，相交就返回相交第一个节点
 * 3 判断两个有环链表是否相交，相交就返回相交的第一个节点（不可能存在一个无环一个有环的  这样导致一个node有两个next节点）
 */

public class GetLoopNode_11 {
    public class Node {
        public int value;
        public Node next;

        public Node(int data) {
            this.value = data;
        }
    }

    public Node getLoopNode(Node head) {
        if (head == null || head.next == null || head.next.next == null) {
            return null;
        }
        Node n1 = head.next;
        Node n2 = head.next.next;
        while (n1 != n2) {
            if (n2.next == null || n2.next.next == null) {
                return null;
            }
            n1 = n1.next;
            n2 = n2.next.next;
        }
        n2 = head;
        while (n1 != n2) {
            n1 = n1.next;
            n2 = n2.next;
        }
        return n1;

    }

    /**
     * 下面是问题2：问题二是两个没环链表的相交问题。
     *
     * @param head1
     * @param head2
     * @return
     */
    public Node noLoop(Node head1, Node head2) {
        if (head1 == null || head2 == null) {
            return null;
        }
        Node cur1 = head1;
        Node cur2 = head2;
        int n = 0;//这个n来判断是cur1长还是cur2长，如果n>0那么cur1长，否则cur2长
        while (cur1.next != null) {//cur!=null 和 cur.next!= null两者有什么区别呢，cur！=null 最后cur变为了null已经过了最后一个
            //链表的末尾了，但是cur。next！=null最后是链表的最后一个元素
            n++;
            cur1 = cur1.next;
        }
        while (cur2.next != null) {
            n--;
            cur2 = cur2.next;
        }
        if (cur1 != cur2) {//如果二者不相等，说明二者没有相交
            return null;
        }
        //接下来通过n来判断两个谁先跑多少，然后，因为两者相交后最终都是一样的 前面的长度为长的先跑两者的差值
        cur1 = n > 0 ? head1 : head2;
        cur2 = cur1 == head1 ? head2 : head1;
        n = Math.abs(n);//这里有正负用一个绝对值就可以了
        while (n != 0) {
            cur1 = cur1.next;
            n--;
        }
        while (cur1 != cur2) {
            cur1 = cur1.next;
            cur2 = cur2.next;
        }
        return cur1;

    }

    /**
     * 问题3：如何判断两个有环链表是否相交，相交则返回第一个相交节点
     * 第一种情况：1loop1==loop2，那么跟第二种情况处理相同，
     * 如果二者不等：又有两种情况：1二者分离 2二者相交 进环不是同一个
     */
    public Node bothLoop(Node head1, Node loop1, Node head2, Node loop2) {
        Node cur1 = null;
        Node cur2 = null;
        if (loop1 == loop2) {
            cur1 = head1;
            cur2 = head2;
            int n = 0;
            while (cur1 != loop1) {
                n++;
                cur1 = cur1.next;
            }
            while (cur2 != loop2) {
                n--;
                cur2 = cur2.next;
            }
            cur1 = n > 0 ? cur1 : cur2;//让cur1是最长的那一组
            cur2 = cur1 == cur1 ? cur2 : cur1;
            n = Math.abs(n);
            while (n != 0) {
                cur1 = cur1.next;
                n--;
            }
            while (cur1 != cur2) {
                cur1 = cur1.next;
                cur2 = cur2.next;
            }
            return cur1;
        } else {
            cur1 = loop1.next;
            while (cur1 != loop1) {
                if (cur1 == loop2) {
                    return loop1;
                }
                cur1 = cur1.next;
            }
        }
        return null;
    }

    /**
     * 下面是函数的主方法,按照分析的方法写下来即可
     */
    public Node getIntersectNode(Node head1, Node head2) {
        if (head1 == null || head2 == null) {
            return null;
        }
        Node loop1 = getLoopNode(head1);
        Node loop2 = getLoopNode(head2);
        if (loop1 == null && loop2 == null) {
            return noLoop(head1, head2);
        }
        if (loop1 != null && loop2 != null) {
            return bothLoop(head1, loop1, head2, loop2);
        }
        return null;

    }
}
