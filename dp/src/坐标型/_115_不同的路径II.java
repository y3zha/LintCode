package 坐标型;

/**
 * 坐标型dp
 *
 * 考虑网格中有障碍物，那样将会有多少条不同的路径？
 * 网格中的障碍和空位置分别用 1 和 0 来表示。
 * 有障碍就把dp标记为0，路径数为0
 */
public class _115_不同的路径II {

    public int uniquePathsWithObstacles(int[][] A) {
        int n = A.length;
        int m = A[0].length;
        //如果起点和终点有障碍，直接不可达，路径为0
        if (A[0][0] == 1 || A[n - 1][m - 1] == 1) {
            return 0;
        }
        int[][] dp = new int[n][m];
        dp[0][0] = 1;
        //初始化第一行和第一列
        int i, j;
        for (j = 1; j < m; j++) {
            if (A[0][j] == 1) {
                dp[0][j] = 0;
                break;
            }
            dp[0][j] = 1;
        }
        for (i = 1; i < n; i++) {
            if (A[i][0] == 1) {
                dp[i][0] = 0;
                break;
            }
            dp[i][0] = 1;
        }
        for (i = 1; i < n; i++) {
            for (j = 1; j < m; j++) {
                if (A[i][j] == 1) {
                    dp[i][j] = 0;
                    continue;
                }
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }
        return dp[n - 1][m - 1];
    }

}