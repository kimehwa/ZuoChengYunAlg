package cn.myclass.dp4;

public class Coin_5 {
    /**
     * 给定数组arr，arr中所有的值都是正数且不重复。每个值代表一种面值的货币，每一种面值的
     * 货币可以使用任意张，再给定一个整数aim，代表要找的钱的数量，求有多少种方法
     */
    //第一种方法：暴力递归法

    /**
     * @param ，三个解，一个是暴力递归，一个是 记忆搜索，一个事动态
     *                         规划，并且可以在动态规划的基础之上完成进一步的优化，跟前面的机器人
     *                         走路的套路联系在一起
     * @param aim
     * @return 如何写暴力递归的代码呢？
     */
    public int coins1(int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim < 0) {
            return 0;
        }
        return process1(arr, 0, aim);

    }

    public int process1(int[] arr, int index, int aim) {
        int res = 0;//res一般作为返回值
        if (index == arr.length) {
            res = aim == 0 ? 1 : 0;
        } else {
            for (int i = 0; arr[index] * i <= aim; i++) {
                res += process1(arr, 1 + index, aim - arr[index] * i);
                //这里的第二个 参数为啥是i+index呢？
            }
        }
        return res;
    }

    /**
     * 方法二：记忆化的递归方法，因为暴力破解法有大量的重复值
     * 因为每一个递归的过程的结果没有记下来，下次还要重复去求
     * 所以可以事先准备一个map，下次进行同样的递归的时候，先在
     * map中查询是否已经计算过。
     */
    public int coin2(int[] arr, int aim) {
        if (arr == null || arr.length == 0 | aim < 0) {
            return 0;
        }
        int[][] map = new int[arr.length + 1][aim + 1];
        return process2(arr, 0, aim, map);
    }

    public int process2(int[] arr, int index, int aim, int[][] map) {
        int res = 0;
        if (index == arr.length) {
            return res = res == aim ? 1 : 0;
        }
        //跟暴力递归不一样的地方，我们采用了一个数组进行保存
        else {
            int mapValue = 0;
            for (int i = 0; arr[index] * i <= aim; i++) {
                mapValue = map[index + 1][aim - arr[index] * i];
                if (mapValue == 0) {
                    res += mapValue == -1 ? 0 : mapValue;
                } else {
                    res += process2(arr, index + 1, aim - arr[index] * i, map);
                }
            }
        }
        map[index][aim] = res == 0 ? -1 : res;//这里才是每次递归到这里的时候保存的一些地方
        return res;
    }

    /**
     * 方法3：动态规划的问题，跟前面找机器人差不多的情况。
     * 这种是直接写动态规划的情况。
     * 目的是生成行数为N，列数为aim+1的二维矩阵dp规划，dp[i][j]的含义是在使用
     * arr[0...i]货币的情况下，组成钱数j有多少种方法，dp求解的情况如下
     * 1.....2......3......
     */
    public int coins3(int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim < 0) {
            return 0;
        }
        int[][] dp = new int[arr.length][aim + 1];
        //组成钱数为0的方法值，即j=0的情况，也就是列
        for (int i = 0; i < arr.length; i++) {
            dp[i][0] = 1;
        }
        //第二种情况，矩阵dp第一行的情况，但是只能使用arr[0]这种情况的货币
        for (int j = 0; arr[0] * j <= aim; j++) {
            dp[0][arr[0] * j] = 1;//没有的就初始化为0吗？
        }
        //第三种情况，普遍情况，dp可以通过前面的很多dp来推倒出来的情况
        //应该是一个双层for循环,结果是一个三层的循环。dp方法重点是这个地方搞定之
        int num = 0;//要有个东西进行保存才是啊
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j <= aim; j++) {
                num = 0;
                for (int k = 0; j - arr[i] * k >= 0; k++) {
                    num += dp[i - 1][j - arr[i] * k];
                }
                dp[i][j] = num;
            }
        }
        return dp[arr.length - 1][aim];//最后一返回的是这个dp的值
    }
}
