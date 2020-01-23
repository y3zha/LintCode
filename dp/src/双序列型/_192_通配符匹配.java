package 双序列型;

/**
 * 判断两个可能包含通配符“？”和“*”的字符串是否匹配。匹配规则如下：
 * '?' 可以匹配任何单个字符。->同正则表达式的'.'
 * '*' 可以匹配任意字符串（包括空字符串）。->不同于正则表达式
 *
 * 假设A的长度为n，B的长度为m，依旧是从最后一步出发，看B的最后一个字符
 *      1、如果B的最后一个字符是正常字符，就看和A中最后一个字符匹不匹配   相等则dp[i][j] = dp[i-1][j-1]
 *      2、如果B的最后一个字符是? ,dp[i][j] = dp[i-1][j-1],这个和上面可以合并
 *      3、如果B的最后一个字符是* ,有两种情况
 *              一种情况视作匹配0个：dp[i][j] = dp[i][j-1]        A不动，B动
 *              另一种情况是匹配1个再往后走:dp[i-1][j]           A动，B不动 //这里*能匹配任意字符串！
 */


public class _192_通配符匹配 {

    public boolean isMatch(String A, String B) {
        int n = A.length();
        int m = B.length();
        boolean[][] dp = new boolean[n + 1][m + 1];

        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= m; j++) {
                //都是空的
                if (j == 0) {
                    if (i == 0) {
                        dp[i][j] = true;
                        continue;
                    }
                } else {
                    if (B.charAt(j - 1) != '*') {
                        if (i >= 1) {
                            if (A.charAt(i - 1) == B.charAt(j - 1) || B.charAt(j - 1) == '?') {
                                dp[i][j] = dp[i - 1][j - 1];
                            }
                        }
                    } else {
                        //不看
                        dp[i][j] |= dp[i][j - 1];
                        //看
                        if (i >= 1) {
                            dp[i][j] |= dp[i - 1][j];
                        }
                    }
                }
            }
        }
        return dp[n][m];
    }
}