package 序列型;

import java.util.Arrays;

/**
 * 原来是直线型房屋，现在变成了一个圈，其实只要考虑去头和去尾两种情况
 */
public class _534_打劫房屋II {

    public int houseRobber2(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            return nums[0];
        }
        // 去头
        int[] arr1 = Arrays.stream(nums).skip(1).toArray();
        int res1 = helper(arr1);
        //去尾巴
        int[] arr2 = Arrays.stream(nums).limit(nums.length - 1).toArray();
        int res2 = helper(arr2);
        return Math.max(res1, res2);
    }

    private int helper(int[] A) {
        int n = A.length;
        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = A[0];
        for (int i = 2; i <= n; i++) {
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + A[i - 1]);
        }
        return dp[n];
    }
}