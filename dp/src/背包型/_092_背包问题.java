package 背包型;

/**
 * 在n个物品中挑选若干物品装入背包，最多能装多满？假设背包的大小为m，每个物品的大小为A[i]
 *
 * 每个物品只有放或者不放
 */
public class _092_背包问题 {

    public int backPack(int m, int[] A) {
        int n = A.length;
        int[][] dp = new int[n + 1][m + 1];
        //遍历每个物品
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= m; j++) {
                //不放
                dp[i][j] = dp[i - 1][j];
                //放
                if (j >= A[i - 1]) {
                    dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - A[i - 1]] + A[i - 1]);
                }
            }
        }
        return dp[n][m];
    }

    //滚动数组优化
    public int backPack2(int m, int[] A) {
        int n = A.length;
        int[][] dp = new int[2][m + 1];
        int old = 0, now = 0;
        //遍历每个物品
        for (int i = 1; i <= n; i++) {
            old = now;
            now = 1 - now;
            for (int j = 0; j <= m; j++) {
                //不放
                dp[now][j] = dp[old][j];
                //放
                if (j >= A[i - 1]) {
                    dp[now][j] = Math.max(dp[now][j], dp[old][j - A[i - 1]] + A[i - 1]);
                }
            }
        }
        return dp[now][m];
    }
}