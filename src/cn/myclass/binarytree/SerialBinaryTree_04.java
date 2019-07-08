package cn.myclass.binarytree;

import java.util.LinkedList;
import java.util.Queue;

public class SerialBinaryTree_04 {
    public class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            this.value = data;
        }
    }

    /**
     * 通过先序遍历来进行序列化，每一行加引号是为了不产生歧义
     *
     * @param head
     * @return
     */
    public String serialByPre(Node head) {
        if (head == null) {
            return "#!";
        }
        String res = head.value + "!";
        serialByPre(head.left);
        serialByPre(head.right);
        return res;
    }

    //如何通过先序遍历的string来重构二叉树；
    public Node reconByPreString(String preStr) {
        String[] values = preStr.split("!");
        Queue<String> queue = new LinkedList<>();
        for (int i = 0; i != values.length; i++) {
            queue.offer(values[i]);
        }
        return reconPreOrder(queue);

    }

    public Node reconPreOrder(Queue<String> queue) {//又是特么的递归函数
        String value = queue.poll();
        if (value == null) {
            return null;
        }
        Node head = new Node(Integer.valueOf(value));
        head.left = reconPreOrder(queue);
        head.right = reconPreOrder(queue);
        return head;
    }
    //=====================方法2==============================================
    //通过层遍历实现序列化和反序列化

    /**
     * queue的增加元素方法add和offer的区别在于，add方法在队列满的情况下将选择抛异常的方
     * 来表示队列已经满了，而offer方法通过返回false表示队列已经满了；在有限队列的情况，
     * 使用offer方法优于add方法；
     * 2. remove方法和poll方法都是删除队列的头元素，remove方
     * 法在队列为空的情况下将抛异常，而poll方法将返回null；
     * 3. element和peek方法都是返回队列的头元素，但是不删除头元素，区别在与
     * element方法在队列为空的情况下，将抛异常，而peek方法将返回null.

     */
    public String serialByLevel(Node head){
        if (head==null){
            return "#!";
        }
        String res = head.value+"!";
        Queue<Node> queue = new LinkedList<>();
        queue.offer(head);
        while(!queue.isEmpty()){
            head = queue.poll();
            if(head.left!=null){
                res += head.left.value+"!";
                queue.offer(head.left);
            }else{
                res+="#!";
            }
            if (head.right!=null){
                res += head.right.value+"!";
                queue.offer(head.right);
            }else{
                res+="#!";
            }
        }
        return res;
    }
    //先序遍历的反序列化就是重做先序遍历，遇到"#"就生成null节点，结束生成后序子树的过程
    //层遍历的方法就是重做层遍历
    public Node reconByLevelString(String levelStr){
        String[] values = levelStr.split("!");
        int index = 0;
        Node head = new Node(Integer.valueOf(values[index++]));
        Queue<Node> queue = new LinkedList<>();
        if(head!=null){
            queue.offer(head);
        }
        Node node = null;
        while(!queue.isEmpty()){
            node = queue.poll();
            node.left = generateNodeByString(values[index++]);//这里写left的时候，因为不好写
            node.right = generateNodeByString(values[index++]);//所以分成了两步
            if (node.left!=null){
                queue.offer(node.left);
            }
            if(node.right!=null){
                queue.offer(node.right);
            }

        }
        return head;
    }
    public Node generateNodeByString(String val){
        if(val.equals("#!")){
            return null;
        }
        return new Node(Integer.valueOf(val));
    }

}
