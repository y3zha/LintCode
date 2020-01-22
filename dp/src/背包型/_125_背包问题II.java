package 背包型;

/**
 * 有 n 个物品和一个大小为 m 的背包. 给定数组 A 表示每个物品的大小和数组 V 表示每个物品的价值.
 * 问最多能装入背包的总价值是多大?
 *
 * dp[i][j] 代表放前i个物品重量为j时的最高总价值
 */
public class _125_背包问题II {

    public int backPackII(int m, int[] A, int[] V) {
        int n = A.length;
        int[][] dp = new int[n + 1][m + 1];

        for (int i = 1; i <= n; i++) {
            //重量
            for (int j = 0; j <= m; j++) {
                //当前物品不放
                dp[i][j] = dp[i - 1][j];
                //当前物品放
                if (j >= A[i - 1]) {
                    dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - A[i - 1]] + V[i - 1]);
                }
            }
        }
        return dp[n][m];
    }
    //可以滚动数组优化下
}