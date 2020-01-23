package 双序列型;

/**
 * 【双序列型】
 * 给出三个字符串:s1、s2、s3，判断s3是否由s1和s2交叉构成。
 *
 * 如果s3的长度不等于s1+s2，那肯定不能构成
 * 如果s3的长度 = s1+s2，那么s3的最后一个字符，要么是s1的最后一个字符，要么是s2的最后一个字符
 *      如果是s1的最后一个字符，那么s3[0...m+n-2]是由s1[0..m-2]与s2[0..n-1]交错形成的
 *      如果是s2的最后一个字符，那么s3[0...m+n-2]是由s1[0..m-1]与s2[0..n-2]交错形成的
 * 以上两种情况只要有一种成立即可
 *
 * 状态:设dp[s][i][j]为s3前s个字符是否由A前i个字符A[0..i-1]和B前j个字符B[0..j-1]交错形成
 * 但是s=i+j，所以可以简化为:设dp[i][j]为s3前i+j个字符是否由A前i个字符 A[0..i-1]和B前j个字符B[0..j-1]交错形成
 *
 * 状态转移方程dp[i][j] = (dp[i-1][j] && s1[i] == s3[i+j-1]) || (dp[i][j-1] and s2[j] == s3[]i+j-1)
 * 初始条件:空串由A的空串和B的空串交错形成 f[0][0] = True
 * 边界情况:如果i=0，不考虑情况一,因为已经没有s1[i-1];如果j=0，不考虑情况二，没有s2[j-1]
 *
 */
public class _029_交叉字符串 {

    public boolean isInterleave(String s1, String s2, String s3) {
        int n = s1.length();
        int m = s2.length();
        int l = s3.length();
        if (n + m != l) {
            return false;
        }
        boolean[][] dp = new boolean[n + 1][m + 1];


        //枚举s1所有字符
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= m; j++) {
                if (i == 0 && j == 0) {
                    dp[i][j] = true;
                    continue;
                }

                //如果当前s3的最后一个字符是s1中的最后一个字符
                if (i > 0 && s3.charAt(i + j - 1) == s1.charAt(i - 1) && dp[i - 1][j]) {
                    dp[i][j] = true;
                }
                //如果当前s3的最后一个字符是s1中的最后一个字符
                if (j > 0 && s3.charAt(i + j - 1) == s2.charAt(j - 1) && dp[i][j - 1]) {
                    dp[i][j] = true;
                }
            }
        }
        return dp[n][m];
    }
}