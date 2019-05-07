package cn.myclass.sq;

import java.util.Stack;

public class TwoStacksQueue_02 {
    private Stack<Integer> stackPush;
    private Stack<Integer> stackPop;

    public TwoStacksQueue_02() {
        this.stackPush = new Stack<>();
        this.stackPop = new Stack<>();
    }

    private void pushToPop() {//push栈向其中倾倒pop栈
        if (stackPop.empty()) {
            while (!stackPush.empty()) {
                stackPop.push(stackPush.pop());
            }
        }
    }

    public void add(int pushInt) {
        stackPush.push(pushInt);
        pushToPop();

    }

    public int poll() {
        if (stackPush.empty() && stackPop.empty()) {
            throw new RuntimeException("Queue is empty");
        }
        pushToPop();
        return stackPop.pop();

    }

    public int peek() {
        if (stackPop.empty() && stackPush.empty()) {
            throw new RuntimeException("queue is empty");
        }
        pushToPop();
        return stackPop.peek();

    }
}
