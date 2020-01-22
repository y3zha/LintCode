package 序列型;

/**
 * 序列型dp
 *
 * 其实就是有两种状态，这个房子要么打劫要么不打劫
 * 从最后一步出发，最后一栋打不打劫
 * dp[i]代表到第i栋获得的最多的金币，开n+1
 * dp[i] = max{dp[i-1],dp[i-2]+A[i-1]}
 */
public class _392_打劫房屋 {

    //序列+状态写法,0代表不偷，1代表偷
    public long houseRobber(int[] A) {
        int n = A.length;
        int[][] dp = new int[n + 1][2];
        //初始化第0栋
        dp[0][0] = dp[0][1] = 0;
        for (int i = 1; i <= n; i++) {
            //不偷
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1]);
            //偷
            dp[i][1] = dp[i - 1][0] + A[i - 1];
        }
        return Math.max(dp[n][0], dp[n][1]);
    }

    //压缩下
    public long houseRobber2(int[] A) {
        if (A.length == 0) {
            return 0;
        }
        int n = A.length;
        long[] dp = new long[n + 1];
        dp[0] = 0;
        dp[1] = A[0];

        for (int i = 2; i <= n; i++) {
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + A[i - 1]);
        }
        return dp[n];
    }
}