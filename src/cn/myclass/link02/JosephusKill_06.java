package cn.myclass.link02;

public class JosephusKill_06 {
    /**
     * 环形单链表的约瑟夫问题
     * 输入：一个环形单向链表的头结点head和报数的值m，
     * 返回：最后生存下来的节点，且这个结点自己组成环形单向链表，其他节点都删掉
     * <p>
     * 进阶：如果链表节点数为N，想在时间复杂度为O（N)完成原问题的要求，该怎么实现？
     */
    public class Node {
        int value;
        Node next;

        public Node(int date) {
            this.value = date;
        }
    }

    public Node josephusKill(Node head, int m) {
        if (head == null || head.next == head || m < 1) {//这个是三种界限
            return head;
        }
        Node last = head;
        while (last.next != head) {//找到了最后一个节点
            last = last.next;
        }
        int count = 0;
        while (head != last) {
            if (++count == m) {
                last.next = head.next;
                count = 0;
            } else {
                last = last.next;//不等于的话 last指针也后移
            }
            head = last.next;//这里的疑问 为啥不用head.next
        }
        return head;
    }

    /**
     * 进阶问题复杂度分析见书p54页，用到了一个基本函数是f(x)=x%i;
     * @param head
     * @param m
     * @return head
     */
    public Node josephusKill2(Node head, int m) {
        if (head == null || head.next == head || m < 1) {
            return head;
        }
        Node cur = head.next;
        int tmp = 1;//这个变量是链表的长度
        while (cur != head) {
            tmp++;
            cur = cur.next;
        }
        tmp = getLive(tmp, m);
        while (--tmp != 0) {
            head = head.next;
        }
        head.next = head;//最后处理器让其等于一个环
        return head;

    }

    //公式得到老编号 = （新编号+m-1）%i+1;跟斐波那契额的数列一样的递归过程
    public int getLive(int i, int m) {
        if (i == 1) {
            return 1;
        }
        return (getLive(i - 1, m) + m - 1) % +1;
    }
}
