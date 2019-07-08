package cn.myclass.dp4;

public class MinPathSum {
    public int minPathSum1(int[][] m) {
        if (m == null || m.length == 0 || m[0] == null || m[0].length == 0) {
            return 0;
        }
        int row = m.length;
        int cow = m[0].length;
        int[][] dp = new int[row][cow];
        dp[0][0] = m[0][0];
        for (int i = 1; i < row; i++) {
            dp[i][0] = dp[i - 1][0] + m[i][0];
        }
        for (int j = 1; j < cow; j++) {
            dp[0][j] = dp[0][j - 1] + m[0][j];
        }
        for (int i = 1; i < row; i++) {
            for (int j = 1; j < cow; j++) {
                dp[i][j] = Math.min(dp[i][j - 1], dp[i - 1][j]) + m[i][j];
            }
        }
        return dp[row - 1][cow - 1];
    }

    /**
     * dp算法计算最短路径，前面是基础的动态dp问题，后面是基于空间压缩的算法
     * 可以节省空间复杂度，好好研究空间压缩的算法
     * 遗留的问题是如何采用滚动压缩的方法进行空间简化
     * 空间简化其实实际上就是用之前的数组来进行操作的
     * 如果给定的行数列数相等，给定的矩阵的列数小于行数，依然可以采用横着更新
     * 如果给定的行数小于列数，那么要竖着滚动更新
     */
    public int minPathSum2(int[][] m) {
        if (m == null || m.length == 0 || m[0] == null || m[0].length == 0) {
            return 0;
        }
        int more = Math.max(m.length, m[0].length);//选其中较大的一个
        int less = Math.min(m.length, m[0].length);
        //判断行数跟列数是否相等
        boolean rowmore = more == m.length;

        int[] arr = new int[less];
        arr[0] = m[0][0];//首先赋值第一个的语句
        for (int i = 1; i < less; i++) {
            //arr[i] =arr[i-1]+m[0][i];
            arr[i] = arr[i - 1] + (rowmore ? m[0][i] : m[i][0]);
            // 这里还要判断行数和列数是否相等，要是相等的话就更新值
        }
        //下面进行数组的滚动更新
        for (int i = 0; i < more; i++) {
            arr[0] = arr[0] + (rowmore ? m[i][0] : m[0][i]);
            for (int j = 1; j < less; j++) {
                arr[j] = Math.min(arr[j - 1], arr[j]) +
                        (rowmore ? m[i][j] : m[j][i]);
            }
        }
        return arr[less - 1];
        /**
         * 压缩空间的方法几乎可以应用到深所有需要的二维动态数组中去，通过滚动更新可以节省大量的
         * 空间。程序的常数时间也得到了改善。但是局限性是覆盖掉之前求解的值，让轨迹变得不可
         * 回溯。  更新于2019、7、8
         */

    }
}
