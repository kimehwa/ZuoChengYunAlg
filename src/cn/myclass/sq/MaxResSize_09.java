package cn.myclass.sq;

import java.util.List;
import java.util.Stack;

public class MaxResSize_09 {
    /**
     * 求最大子矩阵的问题，0，1构成
     * 这个题也是用单调栈来进行解答。单调栈对于找不大于两边的数好像挺管用的
     * 分成了两个函数，一个是计算每一行的最大子数组为多少
     * 另外一个是用单调栈计算每一行的最大的面积是多少
     */
    public int maxResSize(int[][] map) {//二维列表的行列
        if (map == null || map.length == 0 || map[0].length == 0) {
            return 0;
        }
        int maxArea = 0;
        int[] height = new int[map[0].length];
        for (int i = 0; i < map.length; i++) {//这里每次循环一次出来
            for (int j = 0; j < map[0].length; j++) {
                height[j] = map[i][j] == 0 ? 0 : height[j] + 1;//这里的循环如何处理啊？,用之前的数组搞其他的
            }
            maxArea = Math.max(maxRecFromBottom(height), maxArea);

        }
        return maxArea;
    }

    public int maxRecFromBottom(int[] height) {
        //这里的关键还是用单调栈来求解最大值问题
        if (height == null || height.length == 0) {
            return 0;
        }
        int maxArea = 0;
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < height.length; i++) {
            while (!stack.isEmpty() && height[stack.peek()] >= height[i]) {
                int j = stack.pop();
                int k = stack.isEmpty() ? -1 : stack.peek();
                int curArea = (i - k - 1) * height[j];
                maxArea = Math.max(maxArea, curArea);
            }
            stack.push(i);
        }
        while (!stack.isEmpty()){
            int j = stack.pop();
            int k = stack.isEmpty()?-1:stack.peek();
            int curArea = (height.length -k-1)*height[j];
            maxArea=Math.max(maxArea, curArea);
        }
        return maxArea;
        /**
         * 如果矩阵的大小为O（n*m）,那么时间复杂度为O（m*n）
          */

    }
}
