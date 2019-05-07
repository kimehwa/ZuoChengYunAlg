package cn.myclass.link02;

public class RemoveLastKthNode_02 {
    /**
     * 在单链表与双链表中删除倒数第k个节点的问题
     */
    public class Node {
        public int value;
        public Node next;

        public Node(int data) {
            this.value = data;
        }
    }

    public Node removeLastKthNode(Node head, int lastKth) {
        // 1第一步，先比较k与链表长度的关系
        if (head == null || lastKth < 1) {
            return head;
        }
        Node cur = head;

        while (cur != null) {//这里为啥不是cur.next呢？
            cur = cur.next;
            lastKth--;
        }
        if (lastKth == 0) {
            head = head.next;
        }
        if (lastKth < 0) {
            cur = head;
            while (++lastKth != 0) {
                cur = cur.next;
            }
            cur.next = cur.next.next;
        }
        return head;//比我自己写的代码精简多了卧槽
    }
    public class LinkedNode{
        public int data;
        LinkedNode next;
        LinkedNode pre;
        public LinkedNode(int data){
            this.data =data;
        }

    }

    public LinkedNode removeLastKthLinkedNode(LinkedNode head, int lastKth) {
        if (head == null || lastKth < 1) {
            return head;
        }
        LinkedNode cur = head;
        while(cur!=null){
            lastKth--;
            cur=cur.next;
        }
        if(lastKth==0){
            head= head.next;
            head.pre =null;
        }
        if(lastKth<0){
            //如果小于0，就重新跑
            cur = head;
            while(++lastKth!=0){
                cur =cur.next;
            }
            //cur.next =cur.next.next;
            //cur.pre =cur.pre.pre;
            LinkedNode newNext=cur.next.next;
            cur.next=newNext;
            if(newNext!=null){
                newNext.pre = cur;
            }
        }
        return head;
    }
}
