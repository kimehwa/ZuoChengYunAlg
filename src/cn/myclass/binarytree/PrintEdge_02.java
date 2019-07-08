package cn.myclass.binarytree;

public class PrintEdge_02 {
    /**
     * 题目：给定一个二叉树的头结点head，按照如下两种标准分别实现二叉树边界节点的逆时针打印
     * 标准一：头结点为边界节点
     * 2叶节点为边界节点
     * 3如果节点在其所在的层中是最左或者最右的，那么该节点也是边界节点
     * 标准二：
     * 1头结点为边界节点
     * 2叶节点为边界节点
     * 3树左边界延伸下去的路径为边界节点
     * 4树右边界延伸下去的路径为边界节点
     * <p>
     * 要求：1如果节点数为N，两种标准实现的时间复杂度都要求为O（N），额外空间复杂度要求都为哦（h）
     * 2 两种标准都要求逆时针顺序且不重复打印所有的边界节点
     */
    public class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            this.value = data;
        }
    }

    /**
     * 这个题的解题思路就是递归就完事了，全特么的递归问题。
     * @param h
     * @param l
     * @return
     */

    //前面的几个函数都用的递归来实现的。等总结这个的递归跟标准2的递归的不同之处
    public int getHeight(Node h, int l) {//整体的思路
        if (h == null) {
            return l;
        }
        return Math.max(getHeight(h.left, l + 1), getHeight(h.right, l + 1));
    }

    public void setEdgeMap(Node h, int l, Node[][] edgeMap) {
        if (h == null) {
            return;
        }
        edgeMap[1][0] = edgeMap[1][0] == null ? h : edgeMap[1][0];
        edgeMap[1][1] = h;
        setEdgeMap(h.left, l + 1, edgeMap);
        setEdgeMap(h.right, l + 1, edgeMap);
    }

    public void printLeafNotInMap(Node h, int l, Node[][] m) {
        if (h == null) {
            return;
        }
        if (h.left != null && h.right != null && h != m[0][1] && h != m[1][1]) {
            System.out.println(h.value + " ");
        }
        printLeafNotInMap(h.left, l + 1, m);
        printLeafNotInMap(h.right, l + 1, m);

    }

    public void printEdge1(Node head) {
        int h = getHeight(head, 0);
        Node[][] edgeMap = new Node[h][1];
        setEdgeMap(head, 0, edgeMap);
        for (int i = 0; i < edgeMap.length - 1; i++) {
            System.out.println(edgeMap[i][0].value + " ");
        }
        printLeafNotInMap(head, 0, edgeMap);
        for (int i = edgeMap.length - 1; i != -1; i--) {
            if (edgeMap[i][0] != edgeMap[i][1]) {//这里为啥这么判断呢？
                System.out.println(edgeMap[i][1].value + " ");
            }
        }
        System.out.println();

    }

    //==================================标准2==================================
    public void printEdge2(Node head) {
        if (head == null) {
            return;
        }
        System.out.println(head.value + " ");
        if (head.left != null && head.right != null) {
            printLeftEdge(head.left, true);
            printRightEdge(head.right, true);
        } else {
            printEdge2(head.left != null ? head.left : head.right);
        }
        System.out.println();
    }

    public void printLeftEdge(Node h, Boolean print) {
        if (h == null) {
            return;
        }
        if (print || (h.left == null && h.right == null)) {
            System.out.println(h.value + " ");
        }
        printLeftEdge(h.left, print);
        printLeftEdge(h.right, print && h.left == null ? true : false);
    }

    public void printRightEdge(Node h, Boolean print) {//次序要对应起来
        if (h == null) {
            return;
        }
        printRightEdge(h.left, print && h.right == null ? true : false);
        printRightEdge(h.right, print);
        if (print || (h.left == null && h.left == null)) {
            System.out.println(h.value + " ");
        }
    }
}
