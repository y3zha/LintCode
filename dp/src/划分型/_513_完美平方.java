package 划分型;

/**
 *
 * 给一个正整数 n, 请问最少多少个完全平方数(比如1, 4, 9...)的和等于n。
 * dp[i]代表这个数字最少由多少个平方组成
 */
public class _513_完美平方 {

    public int numSquares(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 0;
        for (int i = 1; i <= n; i++) {
            dp[i] = Integer.MAX_VALUE;
            for (int j = 1; j * j <= i; j++) {
                dp[i] = Math.min(dp[i], dp[i - j * j] + 1);
            }
        }
        return dp[n];
    }
}