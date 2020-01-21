package 序列型;

/**
 * 变成k种颜色罢了，还是拿房屋染色1的代码提交就行，但是效率不行，因为做了很多重复的操作，时间复杂度O（房子数*K^2），需要加快
 * 我们来记录最小值和次小值
 * 每次枚举完i后，先过一遍i-1栋房子的所有颜色得到最小值和次小值，比如最小值为dp[i-1][a]次小值为dp[i-1][b]
 * 如果j的颜色和a不一样，那么dp[i][j] = dp[i-1][a]+costs[i-1][j]，如果j的颜色和a一样,那么就用次小值dp[i][j] = dp[i-1][b]+costs[i-1][j]
 */

public class _516_房屋染色II {

    public static int minCostII(int[][] costs) {
        if (costs == null || costs.length == 0 || costs[0].length == 0) {
            return 0;
        }
        int n = costs.length;
        int m = costs[0].length;
        int[][] dp = new int[n + 1][m];
        //初始化第0栋
        for (int i = 0; i < m; i++) {
            dp[0][i] = 0;
        }

        //记录最小值和次小值以及它们的下标
        int min1, min2;
        int id1 = 0, id2 = 0;

        for (int i = 1; i <= n; i++) {
            //每次遍历一栋房子，先初始化最小值和次小值，找到前一栋房子的最小值和次小值
            min1 = min2 = Integer.MAX_VALUE;
            for (int j = 0; j < m; j++) {
                //如果小于最小值，那它就是新的最小值，原来最小值变为次小值
                if (dp[i - 1][j] < min1) {
                    min2 = min1;
                    id2 = id1;
                    min1 = dp[i - 1][j];
                    id1 = j;
                    continue;
                }
                //如果只是小于次小值，只要更新次小值
                if (dp[i - 1][j] < min2) {
                    min2 = dp[i - 1][j];
                    id2 = j;
                }
            }

            //找到了前一栋的最小值和次小值，开始遍历这栋房子的所有状态,累加的
            for (int j = 0; j < m; j++) {
                //如果和最小值一样，那只能是次小值了
                if (j != id1) {
                    dp[i][j] += min1 + costs[i - 1][j];
                } else {
                    dp[i][j] += min2 + costs[i - 1][j];
                }
            }
        }

        int res = Integer.MAX_VALUE;
        for (int j = 0; j < m; j++) {
            res = Math.min(res, dp[n][j]);
        }
        return res;
    }
}