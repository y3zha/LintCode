package 划分型;

/**
 * 划分型dp
 *
 * 从最后一步来看，还剩最后两个字母，他可以单独拆开来看（1～9），如果在（10～26）之间，也可以当作一个看
 * 开n+1
 * dp[i]代表到当前位置有多少种解码方式,dp[i] = dp[i-1]+dp[i-2]
 */
public class _512_解码方法 {

    public static int numDecodings(String s) {
        int n = s.length();
        char[] sc = s.toCharArray();
        int[] dp = new int[n + 1];
        //如果就一个数组，它前面没有数字了，需要加上前面这个，所以设置dp[0] = 1
        dp[0] = 1;

        for (int i = 1; i <= n; i++) {
            if (sc[i - 1] >= '1' && sc[i - 1] <= '9') {
                dp[i] += dp[i - 1];
            }
            //如果前面数字能和它成两位数
            if (i >= 2) {
                int temp = (sc[i - 2] - '0') * 10 + (sc[i - 1] - '0');
                if (temp >= 10 && temp <= 26) {
                    dp[i] += dp[i - 2];
                }
            }
        }
        return dp[n];
    }
}