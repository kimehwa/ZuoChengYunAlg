package cn.myclass.link02;

import java.util.Stack;

public class IsPalindrome_07 {
    /**
     * 给定一个链表的头结点，判断该链表是否为回文结构，
     * 解题思路是：采取一个栈结构，栈为先进后出的，从栈顶到栈底的节点值出现顺序会跟原链表的从左到右的次序反过来，如果
     * 是回文结构，出现的次序还是一样的，如果不是，说明不是回文结构  空间复杂度为O（N）
     * <p>
     * 进阶问题：如果链表的长度为N，时间复杂度达到O（N），额外空间复杂度达到O（1）
     */
    public class Node {
        int value;
        Node next;

        public Node(int data) {
            this.value = data;
        }
    }

    public boolean isPalindrome1(Node head) {
        /**
         * 第一种方法，没有进行优化
         * 思路是跟答案是一样的，但是答案的stack里面放的是Node 我这个跟他差不多
         */
        /**if (head == null || head.next == null) {//答案没有这个，可以省略
         return true;
         }**/
        Stack<Integer> stack = new Stack<>();
        Node cur = head;
        while (cur != null) {
            stack.push(cur.value);
            cur = cur.next;
        }
        //如何设计一个两个同步进行对比的？
        while (head != null) {
            if (head.value != stack.pop()) {
                return false;
            } else {
                head = head.next;
            }
        }
        return true;
    }

    public boolean isPalindrome2(Node head) {
        /**
         * 第二种方法，对第一种方法进行了优化，其实可以分为左区和右区，奇数的话去掉中间的元素，
         * 偶数那个分为左右的两个部分，用stack的右区跟链表的左区进行对比
         * 1.编程的关键是找到右区并放进stack,并且用快慢两个指针找到右区、
         */
        if (head == null || head.next == null) {
            return true;
        }
        //一个快指针，一个慢指针，搞定之
        Node right = head.next;
        Node cur = head;
        while (cur.next != null && cur.next.next != null) {
            right = right.next;//直接是右边区第一个点
            cur = cur.next.next;
        }
        Stack<Node> stack = new Stack<>();
        while (right != null) {
            stack.push(right);
            right = right.next;
        }
        while (!stack.isEmpty()) {
            if (head.value != stack.pop().value) {
                return false;
            }
            head = head.next;
        }
        return true;
    }

    public boolean isPalindrome3(Node head) {
        /**
         * 此方法不需要申请栈结构，但是很难写，比较考察代码能力
         * 满足进阶的要求
         * 1.改变链表右半区的结构，使整个右半区进行反转，将左半区的第一个节点记为leftStart，右半区的反转之后的最右边的节点为rightStart
         * 2leftStart、与rightStart同时向中间点进行移动，移动每一步都比较两者的值看是否一样，如果一样说明是回文
         * 3不管最后返回的是true还是false，都要把链表恢复成原来的样子？？？？？
         * 4链表恢复成原来的结构，返回检查结果
         *
         * 想要在有限几个变量完成以上所有的操作，在实现上还是比较考察代码实现能力的。实际上，此方法只需要申请三个Node变量即可
         */
        if (head == null || head.next == null) {
            return true;
        }
        Node n1 = head;
        Node n2 = head;
        while (n2.next != null && head.next != null) {
            n1 = head.next; //n1为中间部分
            n2 = head.next.next;//n2为结尾部分
        }
        n2 = n1.next;//变为右半区第一个链表
        n1.next = null;//中间部分改为n1==null
        //接下来反转n2链表
        Node n3 = null;
        while (n2 != null) {
            n3 = n2.next;
            n2.next = n1;
            n1 = n2;
            n2 = n3;
        }
        n3 = n1;//保存最后一个节点
        n2 = head;//保存左边的第一个节点
        boolean res = true;
        while (n1 != null && n2 != null) {
            if (n1.value != n2.value) {
                res = false;
                break;
            }
            n1 = n1.next;
            n2 = n2.next;
        }
        n1 = n3.next;
        n3.next = null;
        while (n1 != null) {//把前面反转的链表在反转回去。。。
            n2 = n1.next;
            n1.next = n3;
            n3 = n1;
            n1 = n2;
        }
        return res;

    }
}
