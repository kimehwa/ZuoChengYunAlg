package cn.myclass.dp4;

public class RobotWalkImp_04 {
    /**
     * 这个方法总结了暴力方法解决的方法如何转化为动态规划的套路
     * <p>
     * 关键是动态规划都不懂啊、关键怎么搞定追那个妹子的计划啊
     * <p>
     * 今天是7月4日了 时间过的还是很难对啊进口量的撒的恐惧感ioejkafdk打飞机雕刻机房卡看得见覅额；啊
     * 一共有4个参数，一个事位置N，一个是当前的步数cur，可以改变，一个是剩下的rest步没有走完
     * 一个是P，最终的目标
     */
    public int walk(int N, int cur, int rest, int P) {
        if (rest == 0) {
            return cur == P ? 1 : 0;
        }
        if (cur == 1) {
            return walk(N, 2, rest - 1, P);
        }
        if (cur == N) {
            return walk(N, N - 1, rest - 1, P);
        }
        return walk(N, cur + 1, rest - 1, P) + walk(N, cur - 1, rest - 1, P);

    }
    //找到参数无效的值。
    public int ways1(int N,int M,int K,int P){
        
    }
}
