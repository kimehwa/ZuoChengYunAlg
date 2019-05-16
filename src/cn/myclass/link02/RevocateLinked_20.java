package cn.myclass.link02;

public class RevocateLinked_20 {
    public class Node {
        public int value;
        public Node next;

        public Node(int data) {
            this.value = data;
        }
    }

    /**
     * 给定一个单链表的头部节点head，链表长度为N，如果N为偶数，那么前N/2个节点算做左半区，后N/2个节点算
     * 做右半区，如果为奇数，那么前N/2个节点算作左半区，后N/2个节点算做右半区，左半区从左到右题词记为L1,L2...
     * 右半区记为R1,R2..请将单链表调整为L1,R1,L2,R2...的形式
     */
    public void revocate(Node head) {
        if (head == null || head.next == null) {
            return;
        }
        Node mid = head;
        Node right = head.next;
        while (right.next != null && right.next.next != null) {
            mid = mid.next;
            right = right.next.next;//这里是找到两者的中点，跟前面的做的题挺像的
        }
        right = mid.next;
        mid.next = null;
        mergeLR(head, right);//这种相同的两个功能是可以分开
    }

    public void mergeLR(Node left, Node right) {
        /**if (left.next ==null){
         left.next =right;
         }
         Node curL = left;
         Node curR = right;
         Node pre = null;
         Node cur = null;
         while(curL!=null){
         pre= curL.next;
         curL.next =curR;
         cur=curR.next;
         curR.next=pre;
         curL=pre;
         curR=cur;**/
        Node next = null;
        while (left.next != null) {
            next = right.next;
            right.next = left.next;
            left.next = right;
            left = right.next;
            right = next;
        }
        left.next = right;

    }

}
