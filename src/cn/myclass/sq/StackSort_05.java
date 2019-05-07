package cn.myclass.sq;

import java.util.Stack;

public class StackSort_05 {
    public static void verseSort(Stack<Integer> stack){//自己做的
        Stack<Integer> help = new Stack<>();
        while (!stack.isEmpty()){
            int res = stack.pop();
            if(help.isEmpty()){
                help.push(res);
            }else if(res>help.peek()){//应该还有一步最后倒入stack中的一步
                help.push(res);//这里的两步可以合并的，简化代码量
            }else{
                while(help.peek()>res){
                    stack.push(help.pop());
                }
                help.push(res);//接下来的循环会重新搞定的
            }

        }

    }
    public static void sortStackByStack(Stack<Integer> stack){
        Stack<Integer> help = new Stack<>();
        while(!stack.isEmpty()){
            int cur = stack.pop();
            while(!help.isEmpty()&&help.peek()<cur){
                stack.push(help.pop());//按从大到小
            }
            help.push(cur);
        }
        while (!help.isEmpty()){
            stack.push(help.pop());//从help中排好序号
        }
    }




}
