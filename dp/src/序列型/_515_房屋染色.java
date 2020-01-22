package 序列型;

/**
 * 序列+状态
 *
 * 开n+1
 */
public class _515_房屋染色 {

    public int minCost(int[][] costs) {
        if (costs == null || costs.length == 0) {
            return 0;
        }
        int n = costs.length;
        //几种颜色
        int m = costs[0].length;
        int[][] dp = new int[n + 1][m];

        //初始化第0栋栋额花费
        for (int i = 0; i < 3; i++) {
            dp[0][i] = 0;
        }

        //从第一栋开始
        for (int i = 1; i <= n; i++) {
            //第j栋染什么颜色
            for (int j = 0; j < m; j++) {
                dp[i][j] = Integer.MAX_VALUE;
                //第j栋的前一栋染什么颜色
                for (int k = 0; k < m; k++) {
                    if (j != k) {
                        dp[i][j] = Math.min(dp[i][j], dp[i - 1][k] + costs[i - 1][j]);
                    }
                }
            }
        }
        //返回最小值
        int min = Integer.MAX_VALUE;
        for (int j = 0; j < m; j++) {
            min = Math.min(min, dp[n][j]);
        }
        return min;
    }
}