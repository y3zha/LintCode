package 双序列型;

/**
 * 【双序列型】经典面试题
 * 给出两个单词word1和word2，计算出将word1 转换为word2的最少操作次数。
 * 你总共三种操作方法：插入一个字符、删除一个字符、替换一个字符
 *
 * 首先从最后一步出发，假设我们已经到了最后一步，通过增删改某个操作就能将s1变为s，假设A的长度为n，B的长度为m
 * A[n-1]->B[m-1]，最后一步就是将A的最后一个字符变为B的最后一个字符
 *      1、A通过增操作，变为B的最后一个字符，A[n-2]->A[n-1]->B[m-1]，也就是此时A的前
 *      2、A通过删操作，A[n-1]->A[n-2]->B[m-1]
 *      3、A通过替换操作，A[n-1]->A[n-1]->B[m-1]
 *      4、A不需要做任何操作，A的最后一个字符和B的最后一个字符相等
 * dp[i][j]代表A的前i个字符和B的前j个字符他们的的最小编辑距离
 *      1、增操作：i是当前A的前i个，它此时和B的j个不匹配，也就是说，i和B的j-1个是匹配的，此时要在这个基础上添加一个,dp[i][j] = dp[i][j-1]+1
 *      2、删操作：i是当前A的前i个，它此时和B的j个不匹配，也就是说，i-1和B的j个是匹配的，所以要删去一个,dp[i][j] = dp[i][j-1]+1
 *      3、改操作：dp[i][j] = dp[i-1][j-1]+1
 *
 *      //谁过长，谁-1
 *      //增操作（A要增加说名B过长，此时A和B的j-1是匹配的，所以是用dp[i][j-1]+1）
 *      //删操作(A要减少说明A过长，此时A的i和B的j-1是匹配的，所以是用dp[i-1][j]+1)
 *      //改操作（替换某个字符即可dp[i-1][j-1]+1）
 */
public class _119_编辑距离 {

    public static int minDistance(String A, String B) {
        int n = A.length();
        int m = B.length();
        int[][] dp = new int[n + 1][m + 1];

        //对空串进行初始化
        for (int j = 0; j <= m; j++) {
            dp[0][j] = j;
        }
        for (int i = 0; i <= n; i++) {
            dp[i][0] = i;
        }
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                dp[i][j] = Math.min(dp[i][j - 1] + 1, Math.min(dp[i - 1][j] + 1, dp[i - 1][j - 1] + 1));
                if (A.charAt(i - 1) == B.charAt(j - 1)) {
                    dp[i][j] = Math.min(dp[i][j], dp[i - 1][j - 1]);
                }
            }
        }
        return dp[n][m];
    }

    public static void main(String[] args) {
        minDistance("horse", "ros");
    }
}