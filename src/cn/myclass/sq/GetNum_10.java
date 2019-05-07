package cn.myclass.sq;

import java.util.LinkedList;

public class GetNum_10 {
    /**
     * 最大值减去最小值小于或等于num的子数组数量
     * 给定数组arr和整数num，共返回多少个子数组满足如下情况，子数组的最大值减去最小值
     *
     * 要求：数组的长度为N，实现复杂度为O（N）的解法。
     * 跟前面07的解法有关联，用双端队列linkedlist，这个用两个双端队列，一个maxq，一个minq,这两个队列可以改变滑动的窗口？
     * 写代码的水平太差了，怎么提高自己的编码能力呢？划窗法的代码。
     *
     */
    public int getNum(int[] arr,int num){
        if(arr==null||arr.length==0||num<0){
            return 0;
        }
        LinkedList<Integer> maxq=new LinkedList<>();
        LinkedList<Integer> minq= new LinkedList<>();
        //两个滑动窗口，一个维护最大值，一个维护最小值
        int i = 0;
        int j = 0;
        int res =0;
        while(i<arr.length){
            while(j<arr.length){
                if(minq.isEmpty()||minq.peekLast()!=j){
                    while (!minq.isEmpty()&&arr[minq.peekLast()]>=arr[j]){
                        minq.pollLast();
                    }
                    minq.addLast(j);
                    while(!maxq.isEmpty()&&arr[maxq.peekLast()]<=arr[j]){
                        maxq.pollLast();
                    }
                    maxq.addLast(j);
                }
                if(arr[maxq.getFirst()]-arr[minq.getFirst()]>num){
                    break;
                }
                j++;
            }
            res+=j-i;
            if(minq.peekFirst()==i){//这里是什么意思？
                minq.pollFirst();
            }
            if(maxq.peekFirst() ==i){//这里是检测是否过期？然后把过期的那个去掉，这个跟那个有点类似
                maxq.pollFirst();
            }
            i++;
        }
        return res;

    }
}
