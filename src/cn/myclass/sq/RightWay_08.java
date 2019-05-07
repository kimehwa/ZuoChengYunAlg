package cn.myclass.sq;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class RightWay_08 {
    public int[][] rightWay(int[] arr) {
        /**
         * 给定一个不含有重复值的数组arr，找到每一个i位置左边和右边离i位置最近且值比arr[i]小的位置
         * 如[3,4,1,5,6,2,7]
         * 返回所有位置相应的信息
         */
        int[][] res = new int[arr.length][2];
        for (int i = 0; i < arr.length; i++) {//暴力法。时间O（n^2)
            int leftLessIndex = -1;
            int rightLessIndex = -1;
            int cur = i - 1;//这里的cur是从i-1开始的,这个cur是干什么的
            while (cur >= 0) {
                if (arr[cur] < arr[i]) {
                    leftLessIndex = cur;
                    break;
                }
                cur--;

            }
            cur = i + 1;
            while (cur < arr.length) {
                if (arr[cur] < arr[i]) {
                    rightLessIndex = cur;
                    break;
                }
                cur++;
            }
            res[i][0] = leftLessIndex;
            res[i][1] = rightLessIndex;

        }
        return res;
    }

    public int[][] getNearLessNoRepeat(int[] arr) {
        /**
         * 这个是单调栈的用法，完成这个问题，但是单调栈的用途是什么呢？
         * 这里解决的是单调栈的无重复元素的问题
         */
        int res[][] = new int[arr.length][2];
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < arr.length; i++) {
            if (!stack.isEmpty() && arr[stack.peek()] > arr[i]) {
                int popIndex = stack.pop();
                int leftLessIndex = stack.isEmpty() ? -1 : stack.peek();
                res[popIndex][0] = leftLessIndex;
                res[popIndex][1] = i;
            }

        }
        while (!stack.isEmpty()) {
            int popIndex = stack.pop();
            int leftLessIndex = stack.isEmpty() ? -1 : stack.peek();
            res[popIndex][0] = leftLessIndex;
            res[popIndex][1] = -1;
        }
        return res;
    }
    /**
     * 单调栈解决有重复数组的问题,如[3,1,3,4,3,5,3,2,2]
     * 多组了一个list来搞定它
     */
    public int[][] getNearLess(int[] arr){
        int res[][] = new int[arr.length][2];
        Stack<List<Integer>> stack = new Stack<>();
        for (int i = 0; i <arr.length ; i++) {
            while(!stack.isEmpty()&&arr[stack.peek().get(0)]>arr[i]){
                List<Integer> popIs = stack.pop();
                //下面位置中最晚加入的那个
                int leftLessIndex = stack.isEmpty()?-1:stack.peek().get(stack.peek().size()-1);
                for(Integer popi:popIs){
                    res[popi][0]= leftLessIndex;
                    res[popi][1]= i;
                }
            }
            if(!stack.isEmpty()&& arr[stack.peek().get(0)]==arr[i]){
                stack.peek().add(Integer.valueOf(i));
            }else{
                ArrayList<Integer> list = new ArrayList<>();
                list.add(i);
                stack.push(list);
            }
        }
        while(!stack.isEmpty()){
            List<Integer> popIs = stack.pop();
            int leftLessIndex = stack.isEmpty()?-1:stack.peek().get(stack.peek().size()-1);
            for (Integer popi : popIs) {
                res[popi][0]= leftLessIndex;
                res[popi][1] = -1;
            }
        }
        return res;
    }
}
