package 划分型;

/**
 * 划分型，划分成多段，每段满足一定条件
 *
 * 给定字符串 s, 需要将它分割成一些子串, 使得每个子串都是回文串.
 * 最少需要分割几次?
 *
 * 首先就可以先把判断回文串这件事做好
 * 从最后一步出发，划分完变为，所有段都变为回文串,dp[i]代表到第i个位置之前划分为回文串的最少次数是多少
 * 它的前一步，划分到j位置，0～j就是前j个都划分为回文串的最少次数，加上这个最后一次，就是结果
 */
public class _108_分割回文串II {

    public int minCut(String s) {
        int n = s.length();
        boolean[][] f = calcPalindrome(s);
        int[] dp = new int[n + 1];
        dp[0] = 0;

        for (int i = 1; i <= n; i++) {
            dp[i] = Integer.MAX_VALUE;
            for (int j = 0; j < i; j++) {
                //如果最后一段是回文串，只要加上dp[j]都划分为回文串的最少次数即可
                if (f[j][i - 1]) {
                    dp[i] = Math.min(dp[i], dp[j] + 1);
                }
            }
        }
        //划的次数=段数-1
        return dp[n] - 1;
    }

    private boolean[][] calcPalindrome(String s) {
        int n = s.length();
        char[] sc = s.toCharArray();
        boolean[][] f = new boolean[n][n];

        //中心点扩展
        for (int c = 0; c < n; c++) {
            int i = c, j = c;
            while (i >= 0 && j < n && sc[i] == sc[j]) {
                f[i][j] = true;
                i--;
                j++;
            }
        }

        //由中心线扩展
        for (int c = 0; c < n; c++) {
            int i = c, j = c + 1;
            while (i >= 0 && j < n && sc[i] == sc[j]) {
                f[i][j] = true;
                i--;
                j++;
            }
        }
        return f;
    }
}