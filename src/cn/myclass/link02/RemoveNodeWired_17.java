package cn.myclass.link02;

public class RemoveNodeWired_17 {
    /**
     * 链表节点值类型值为int，给定一个链表中的节点node，但不给定整个链表的头节点。
     * 如何删除这个函数？实现这个函数，分析这样会出现什么问题
     *
     * 第一思路，删除一般是找到node的前一个节点，然后把next指向next。next
     * 可是这个题找不到next节点 ，所以直接复制节点，然后将下一个节点删除
     */
    public class Node {
        public int value;
        public Node next;

        public Node(int data) {
            this.value = data;
        }
    }
    public void removeNodeWired(Node node){
        if (node ==null){
            return ;
        }
        Node next = node.next;
        if(next == null){
            throw new RuntimeException("can not remove last node");
        }
        node.value = next.value;
        node.next = next.next;

    }
}
