package cn.myclass.sq;

import java.util.Stack;

public class MyStack_01 {
    private Stack<Integer> stackDate;
    private Stack<Integer> stackMin;

    public MyStack_01() {
        this.stackMin = new Stack<>();
        this.stackDate = new Stack<>();
    }

    public void push(int newNum) {
        if (this.stackMin.isEmpty()) {//判断一个栈为空用isEmpty
            this.stackMin.push(newNum);
        } else {
            if (this.getmin() >= newNum) {
                this.stackMin.push(newNum);
            }
        }
        this.stackDate.push(newNum);
    }

    public int pop() {
        if(this.stackDate.isEmpty()){
            throw new RuntimeException("YOUR STACK IS EMPTY");
        }
        int value = this.stackDate.pop();
        if (this.getmin() == value) {
            this.stackMin.pop();
        }
        return value;

    }

    public int getmin() {
        if (stackMin.isEmpty()){
            throw new RuntimeException("your stack is empty");
        }
        return this.stackMin.peek();

    }

}
