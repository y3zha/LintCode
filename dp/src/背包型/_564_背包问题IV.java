package 背包型;

/**
 * 组合总和（类似背包问题）
 * 给出一个都是正整数的数组 nums，其中没有重复的数。从中找出所有的和为 target 的组合个数。
 * 一个数可以在组合中出现多次。数的顺序不同则会被认为是不同的组合。
 *
 * 依旧是关注最后一步，最后一步物品重量是K，那么前面物品构成重量target-K，需要关注最后一个加进来的是谁，从A[0] ~ A[n-1],它们都有可能
 * dp[i]代表有多少种组合能够拼出重量i,dp[i] = dp[i-A[0]] + dp[i-A[1]] +...+ dp[i-A[n-1]]
 */
public class _564_背包问题IV {

    public int backPackVI(int[] nums, int target) {
        int[] dp = new int[target + 1];
        dp[0] = 1;

        for (int i = 1; i <= target; i++) {
            //遍历所有数字
            for (int j = 0; j < nums.length; j++) {
                if (i >= nums[j]) {
                    dp[i] += dp[i - nums[j]];
                }
            }
        }
        return dp[target];
    }

}