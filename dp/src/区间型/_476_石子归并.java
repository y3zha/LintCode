package 区间型;

/**
 * 有一个石子归并的游戏。最开始的时候，有n堆石子排成一列，目标是要将所有的石子合并成一堆。合并规则如下：
 * 每一次可以合并相邻位置的两堆石子,每次合并的代价为所合并的两堆石子的重量之和,求出最小的合并代价。
 *
 * 贪心的思路是错的，用区间型dp
 * dp[i][j] 表示把第i到第j个石子合并到一起的最小花费
 * 预处理前缀和数组sum[i,j] 表示i到j所有石子价值和
 * dp[i][j] = min(dp[i][k]+dp[k+1][j]+sum[i,j]) 对于所有k属于{i,j}
 */
public class _476_石子归并 {

    public static int stoneGame(int[] A) {
        if (A.length == 0) {
            return 0;
        }
        int n = A.length;
        int[][] dp = new int[n][n];

        //首先预处理前缀和数组，当从i到j的石子合并起来时要用到
        int[] sum = new int[n];
        sum[0] = A[0];
        for (int i = 1; i < n; i++) {
            sum[i] = sum[i - 1] + A[i];
        }

        //初始化长度为1的情况，自己不用合并自己
        for (int i = 0; i < n; i++) {
            dp[i][i] = 0;
        }

        //从长度为2开始
        for (int len = 2; len <= n; len++) {
            //枚举起点
            for (int i = 0; i <= n - len; i++) {
                int j = i + len - 1;
                dp[i][j] = Integer.MAX_VALUE;
                //枚举该区间内所有可能的划分点
                for (int k = i; k < j; k++) {
                    if (i == 0) {
                        dp[i][j] = Math.min(dp[i][j], dp[i][k] + dp[k + 1][j] + sum[j]);
                    } else {
                        dp[i][j] = Math.min(dp[i][j], dp[i][k] + dp[k + 1][j] + sum[j] - sum[i - 1]);
                    }
                }
            }
        }
        return dp[0][n - 1];
    }

    //记忆化搜索优化（自顶向下）
    public int stoneGame2(int[] A) {
        if (A.length == 0) {
            return 0;
        }
        int n = A.length;
        int[][] dp = new int[n][n];
        int[] sum = new int[n];
        sum[0] = A[0];
        for (int i = 1; i < n; i++) {
            sum[i] = sum[i - 1] + A[i];
        }
        //初始化长度为1的情况
        for (int i = 0; i < n; i++) {
            dp[i][i] = 0;
        }
        //把其他位置标记为-1，表示没访问过
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i != j) {
                    dp[i][j] = -1;
                }
            }
        }
        dfs(dp, sum, 0, n - 1);
        return dp[0][n - 1];
    }

    private int dfs(int[][] dp, int[] sum, int i, int j) {
        //递归出口
        if (dp[i][j] != -1) {
            return dp[i][j];
        }
        dp[i][j] = Integer.MAX_VALUE;
        //枚举划分位置
        for (int k = i; k < j; k++) {
            if (i == 0) {
                dp[i][j] = Math.min(dp[i][j], dfs(dp, sum, i, k) + dfs(dp, sum, k + 1, j) + sum[j]);
            } else {
                dp[i][j] = Math.min(dp[i][j], dfs(dp, sum, i, k) + dfs(dp, sum, k + 1, j) + sum[j] - sum[i - 1]);
            }
        }
        return dp[i][j];
    }

}