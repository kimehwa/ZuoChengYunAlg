package cn.myclass.link02;

public class MergeLink_19 {
    public class Node {
        public int value;
        public Node next;

        public Node(int data) {
            this.value = data;
        }
    }
    /**
     * 给定两个有序单链表的头结点head1和head2，请合并两个有序链表，合并后的链表依然有序，并返回链表的头节点
     *
     * 解题思路是
     */
    /**
     * public Node mergeLink(Node head1,Node head2){
     * //我的思路是分别用两个指针往后遍历链表，但是写程序遇到了很大的困难
     * <p>
     * if (head1 ==null){
     * return head2;
     * }
     * if (head2==null){
     * return head1;
     * }
     * Node cur1 = head1;
     * Node cur2 = head2;
     * Node head = head = cur1.value>cur2.value?cur2:cur1;
     * cur1 = cur1.value>cur2.value?cur1:cur1.next;
     * cur2 = cur1.value>cur2.value?cur2.next:cur2;
     * <p>
     * while (cur1!=null&&cur2!=null){
     * head.next = cur1.value>cur2.value?cur2:cur1;
     * cur1 = cur1.value>cur2.value?cur1:cur1.next;
     * cur2 = cur1.value>cur2.value?cur2.next:cur2;
     * <p>
     * }
     * if (cur1!=null){
     * head.next = cur1;
     * }
     * if(cur2!=null){
     * head.next = cur2;
     * }
     * return head;
     * <p>
     * }
     **/
    public Node mergeLink(Node head1, Node head2) {//体会是每一个节点可以看做一个节点，然后不要管他后面的链有多长就行
        if (head1 == null || head2 == null) {
            return head1 == null ? head2 : head1;//这里相比较我写的节约了两行
        }
        Node head = head1.value < head2.value ? head1 : head2;
        Node cur1 = head == head1 ? head1 : head2;//cur1永远是首位小的那个链表
        Node cur2 = head == head1 ? head2 : head1;
        Node pre = null;
        Node next = null;
        while (cur1 != null && cur2 != null) {
            if (cur1.value <= cur2.value) {
                pre = cur1;//pre永远是指向上次最小的节点？但是这个pre有什么用呢？
                cur1 = cur1.next;
            } else {
                next = cur2.next;//这里是什么意思？保存next的下一个节点
                pre.next = cur2;
                cur2.next = cur1;//他的思路是把cur节点以此插入到cur2中
                pre = cur2;//新节点的pre更新为cur2,这样的话结束后就是结束了
                cur2 = next;
            }
        }
        pre.next = cur1 == null ? cur2 : cur1;//如果cur1==null说明遍历完pre是链表1的最后一个节点
        //如果链表2先走完说明所有节点已经插入到链表1中，调整结束
        return head;
    }
}
