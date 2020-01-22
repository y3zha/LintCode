package 区间型;

/**
 * 【区间型dp】
 * 给一字符串 s, 找出在 s 中的最长回文子序列的长度. 你可以假设 s 的最大长度为 1000.
 * 子串必定是连续的，子序列不一定
 * 一种笨办法是，我们构造个回文串数组，先把计算结果都搞定，再去找最长的长度是多少
 * 第二种是dp思路，我们枚举所有长度，dp[i][j]代表从i到j这段区间，开n，而不是n+1
 * 计算区间长度为1 dp[0][0]，dp[1][1]...
 * 计算区间长度为2 dp[0][1]，dp[1][2]...
 * ...就这么计算下去
 * 结果是dp[0][n-1]
 *
 * 假设区间s[i,j]就是我们要找的最长回文子串，那么对这个子串去头去尾 s[i+1,j-1]，它仍然是一个回文子串，并且是在长度为j-i+1 - 2时的最长回文子串
 * 区间型最常见的做法就是去头、去尾、去两头，看一下，找最长的
 */
public class _667_最长的回文子串 {

    public int longestPalindromeSubseq(String s) {
        if (s.length() == 0) {
            return 0;
        }
        int n = s.length();
        int[][] dp = new int[n][n];
        //初始化长度为1的区间
        for (int i = 0; i < n; i++) {
            dp[i][i] = 1;
        }
        //从区间长度为2开始,一直到n
        for (int len = 2; len <= n; len++) {
            //枚举起点
            for (int i = 0; i <= n - len; i++) {
                //终点
                int j = i + len - 1;
                //首先拿到去头和去尾两种情况下的最长回文子串
                dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
                //如果头尾相等，
                if (s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = Math.max(dp[i][j], dp[i + 1][j - 1] + 2);
                }
            }
        }
        return dp[0][n - 1];
    }


    int[][] dp;
    char[] sc;
    int n;

    //记忆化搜索
    public int longestPalindromeSubseq2(String s) {
        if (s.length() == 0) {
            return 0;
        }
        dp = new int[n][n];
        sc = s.toCharArray();
        n = s.length();
        //记忆化搜索，先都要标记为未访问过
        for (int i = 0; i < n; i++) {
            //注意，j从i开始，j必须比i大
            for (int j = i; j < n; j++) {
                dp[i][j] = -1;
            }
        }

        //搜索
        dfs(0, n - 1);
        return dp[0][n - 1];
    }

    private void dfs(int i, int j) {
        if (dp[i][j] != -1) {
            return;
        }
        //递归出口
        if (i == j) {
            dp[i][j] = 1;
            return;
        }
        //search
        dfs(i + 1, j);
        dfs( i, j - 1);
        dfs(i + 1, j - 1);

        dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
        if (sc[i] == sc[j]) {
            dp[i][j] = Math.max(dp[i][j], dp[i + 1][j - 1] + 2);
        }
    }
}