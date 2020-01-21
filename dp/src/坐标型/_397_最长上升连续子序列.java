package 坐标型;

/**
 * 最长上升连续子序列可以定义为从右到左或从左到右的序列,必须是连续的
 * 思路就是正着求一遍，然后倒过来求一遍
 *
 * 上升....dp[i]代表到i为止的最长上升子序列 dp[i] = dp[i-1]+1 if A[i] > A[i-1]
 */
public class _397_最长上升连续子序列 {

    public int longestIncreasingContinuousSubsequence(int[] A) {
        if (A == null || A.length == 0) {
            return 0;
        }
        int res1 = LICS(A);
        //反转下数组
        int start = 0;
        int end = A.length - 1;
        while (start < end) {
            int temp = A[start];
            A[start] = A[end];
            A[end] = temp;
            start++;
            end--;
        }
        int res2 = LICS(A);
        return Math.max(res1, res2);
    }

    private int LICS(int[] A) {
        int n = A.length;
        int[] dp = new int[n];
        dp[0] = 1;
        int max = 1;
        for (int i = 1; i < n; i++) {
            dp[i] = 1;
            if (A[i] > A[i - 1]) {
                dp[i] = dp[i - 1] + 1;
            }
            max = Math.max(max, dp[i]);
        }
        return max;
    }

}