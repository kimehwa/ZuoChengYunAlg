package cn.myclass.link02;

public class ListPartition_08 {
    /**
     * 将单向链表按照某值划分为左边小，中间相等，右边大的形式
     * 给定一个单向链表的头结点head，结点的值类型为整形，在给定一个整数pivot，实现一个调整链表的函数，
     * 链表的左半部分为小于pivot的值，中间都是等于pivot的值，右边是大于pivot的值，对调整后的节点顺序没有更多的要求
     * <p>
     * 进阶问题：
     * 在原问题的基础上1在左中右的三个部分的内部也做顺序要求，要求每个部分的节点从左到右的顺序与原链表中的节点顺序先后一致
     */
    public class Node {
        int value;
        Node next;

        public Node(int date) {
            this.value = date;
        }
    }

    public Node listPartition(Node head, int pivot) {//第一次我把int值写入数组中，貌似复原的时候不太方便
        /**if(head ==null ||head.next == null){
         return head;
         }
         int n = 0;
         Node cur = head;
         while(cur!=null){
         cur = cur.next;
         n++;
         }
         cur = head;
         int[] arr = new int[n];
         for(int i = 0;i<n;i++){
         arr[i]= cur.value;
         cur= cur.next;
         }
         partition(arr,pivot);
         for(int i=1;i<arr.length;i++){
         arr[i-1]
         }

         }**/
        if (head == null) {
            return head;
        }
        Node cur = head;
        int i = 0;
        while (cur != null) {
            i++;
            cur = cur.next;
        }
        Node[] nodeArr = new Node[i];
        for (i = 0; i < nodeArr.length; i++) {
            nodeArr[i] = cur;
            cur = cur.next;
        }
        arrPartition(nodeArr, pivot);
        for (i = 1; i < nodeArr.length; i++) {//把arr链表还原；
            nodeArr[i - 1].next = nodeArr[i];
        }
        nodeArr[i - 1] = null;//这里是为什么？
        return nodeArr[0];

    }

    public void arrPartition(Node[] nodeArr, int pivot) {
        int small = -1;
        int index = 0;
        int large = nodeArr.length;
        while (index != large) {
            if (nodeArr[index].value < pivot) {
                swap(nodeArr, ++small, index++);
            } else if (nodeArr[index].value == pivot) {
                index++;
            } else {
                swap(nodeArr, index, --large);
            }
        }
    }

    public void swap(Node[] nodeArr, int l, int r) {
        Node temp = nodeArr[l];
        nodeArr[l] = nodeArr[r];
        nodeArr[r] = temp;
    }

    /**
     * 进阶要求：
     * 对每一部分增加了节点顺序要求，时间复杂度为O（n），额外空间复杂度为1，说明只能使用有限的几个变量完成所有任务
     * 具体过程：
     * 1.将原链表的所有节点依次划分为3个链表，三个列表分别为small，代表左部分，equal代表中间部分，big代表右部分
     * 2.将三者链表重新串联起来即可
     * 3。整个过程中特别注意对null节点的判断与处理。
     * 此题主要考察面试者利用有限几个变量调整链表的代码实现能力
     */
    public static Node listPartition2(Node head, int pivot) {
        Node sH = null;
        Node sT = null;
        Node eH = null;
        Node eT = null;
        Node bH = null;
        Node bT = null;
        Node next = null;
        while (head != null) {
            next = head.next;
            head.next = null;//这里是怎么弄的呢？
            if (head.value < pivot) {
                if (sH == null) {
                    sH = head;
                    sT = head;
                } else {
                    sT.next = head;
                    sT = head;
                }
            } else if (head.value == pivot) {
                if (eH == null) {
                    eH = head;
                    eT = head;
                } else {
                    eT.next = head;
                    eH = head;
                }
            } else {
                if (bH == null) {
                    bH = head;
                    bT = head;
                } else {
                    bT.next = head;
                    bH = head;
                }
            }
            head = next;
        }
        if (sT != null) {
            sT.next = eH;
            eT = eT == null ? sT : eT;
        }
        if (eT != null) {
            eT.next = bH;
        }
        return sH != null ? sH : eH != null ? eH : bH;
    }
}
