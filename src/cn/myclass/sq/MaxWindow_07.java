package cn.myclass.sq;

import java.util.LinkedList;

public class MaxWindow_07 {
    public int[] getMaxWindow(int[] arr, int w) {
        if (arr == null || w < 1 || arr.length < w) {
            return null;
        }
        LinkedList<Integer> qmax = new LinkedList<>();
        int[] res = new int[arr.length - w + 1];
        int index = 0;//这里的index是为了给res数组赋值用的
        for (int i = 0; i < arr.length; i++) {
            while (!qmax.isEmpty() && arr[i] > arr[qmax.peekLast()]) {
                qmax.pollLast();
            }
            qmax.addLast(i);
            if (qmax.peekFirst() == i - w) {//防止窗口的长度比qmax短了
                qmax.pollFirst();
            }
            if (i >= w - 1) {
                res[index++] = arr[qmax.peekFirst()];
            }
        }
        return res;

    }
}
