package 坐标型;

/**
 * 找到一条从左上角到右下角的可以使数字和最小的路径。
 * 要么向右走，要么向下走
 * dp[i][j] = min{dp[i-1][j]+A[i][j],dp[i][j-1]+A[i][j]}
 */
public class _110_最小路径和 {

    public int minPathSum1(int[][] A) {
        int n = A.length;
        int m = A[0].length;
        int[][] dp = new int[n][m];

        //初始化直接放在里面了
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (i == 0 && j == 0) {
                    dp[i][j] = A[i][j];
                    continue;
                }
                dp[i][j] = Integer.MAX_VALUE;
                //向下走的情况
                if (i > 0) {
                    dp[i][j] = Math.min(dp[i][j], dp[i - 1][j]);
                }
                //向右走的情况
                if (j > 0) {
                    dp[i][j] = Math.min(dp[i][j], dp[i][j - 1]);
                }
                dp[i][j] += A[i][j];
            }
        }
        return dp[n - 1][m - 1];
    }

    //使用滚动数组优化

    public int minPathSum2(int[][] A) {
        int n = A.length;
        int m = A[0].length;
        int[][] dp = new int[2][m];
        int old = 0, now = 0;

        //初始化直接放在里面了
        for (int i = 0; i < n; i++) {
            old = now;
            now = 1 - now;
            for (int j = 0; j < m; j++) {
                dp[now][j] = Integer.MAX_VALUE;
                if (i == 0 && j == 0) {
                    dp[now][j] = A[i][j];
                    continue;
                }
                int t = Integer.MAX_VALUE;
                //向下走的情况
                if (i > 0) {
                    t = Math.min(t, dp[old][j]);
                }
                //向右走的情况
                if (j > 0) {
                    t = Math.min(t, dp[now][j - 1]);
                }
                dp[now][j] = t + A[i][j];
            }
        }
        return dp[now][m - 1];
    }
}