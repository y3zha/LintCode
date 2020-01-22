package 背包型;

/**
 * 给出 n 个物品, 以及一个数组, nums[i] 代表第i个物品的大小, 保证大小均为正数, 正整数 target 表示背包的大小, 找到能填满背包的方案数。
 * 每个物品只能使用一次
 *
 * dp[i][j]代表用前i个物品拼出重量j有多少种方案,要把放和不放方案加起来
 */
public class _563_背包问题V {

    public int backPackV(int[] nums, int target) {
        int n = nums.length;
        int[][] dp = new int[n + 1][target + 1];

        //初始化：0拼出0，算是一种方案，0个物品拼出其他重量，不可能
        dp[0][0] = 1;
        for (int i = 1; i <= target; i++) {
            dp[0][i] = 0;
        }
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= target; j++) {
                //不放
                dp[i][j] = dp[i - 1][j];
                //放
                if (j >= nums[i - 1]) {
                    dp[i][j] += dp[i - 1][j - nums[i - 1]];
                }
            }
        }
        return dp[n][target];
    }

    //滚动数组优化
    public int backPackV2(int[] nums, int target) {
        int n = nums.length;
        int[][] dp = new int[n + 1][target + 1];

        //初始化：0拼出0，算是一种方案，0个物品拼出其他重量，不可能
        dp[0][0] = 1;
        for (int i = 1; i <= target; i++) {
            dp[0][i] = 0;
        }
        int old = 0, now = 0;
        for (int i = 1; i <= n; i++) {
            old = now;
            now = 1 - now;
            for (int j = 0; j <= target; j++) {
                //不放
                dp[now][j] = dp[old][j];
                //放
                if (j >= nums[i - 1]) {
                    dp[now][j] += dp[old][j - nums[i - 1]];
                }
            }
        }
        return dp[now][target];
    }
}