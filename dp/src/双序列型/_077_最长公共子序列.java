package 双序列型;

import java.util.Set;
import java.util.TreeSet;

/**
 * 【双序列型】
 *
 * 给出两个字符串，找到最长公共子序列(LCS)，返回LCS的长度。
 * 思路：
 *      假设我们找到了字符串A（长为n）和字符串B（长为m）的最长公共子序列，从最后一步出发，加上这最后一个字符，它就成为了最长公共子序列
 *      那么这么字符有三种可能：
 *          1、这个最后一个字符来自A的最后一个字符，但不来自B的最后一个字符，那么只要看A(0~n-1)和B（0～m-2）
 *          2、这个最后一个字符来自B的最后一个字符，但不来自A的最后一个字符，那么只要看A(0~n-2)和B（0～m-1）
 *          3、这个最后一个字符来自A的最后一个字符，也来自B的最后一个字符，那么只要看A(0~n-2)和B（0～m-2）
 *      dp[i][j]代表A的前i个字符，j代表B的前j个字符，这里的i、j就是指的是字符串的长度
 *      dp[i][j] = max{dp[i][j-1],dp[i-1][j]}, 如果字符相等，dp[i][j] = max{dp[i][j],dp[i-1][j-1]+1}
 *      结果 dp[n][m]
 */
public class _077_最长公共子序列 {

    public int longestCommonSubsequence(String A, String B) {
        int n = A.length();
        int m = B.length();
        int[][] dp = new int[n + 1][m + 1];

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                if (A.charAt(i - 1) == B.charAt(j - 1)) {
                    dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - 1] + 1);
                }
            }
        }
        return dp[n][m];
    }

    //如果要求打印所有最长的公共子序列，可以利用dp数组直接打印
    public static void printAllLCS(String A, String B) {
        int n = A.length();
        int m = B.length();
        int[][] dp = new int[n + 1][m + 1];

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                if (A.charAt(i - 1) == B.charAt(j - 1)) {
                    dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - 1] + 1);
                }
            }
        }
        int longest = dp[n][m];
        Set<String> set = new TreeSet<>();
        search(dp, set, n, m, "", longest, A, B);
        for (String s : set) {
            System.out.println(s);
        }
    }

    private static void search(int[][] dp, Set<String> set, int i, int j, String temp, int longest, String A, String B) {
        if (temp.length() == longest) {
            set.add(new StringBuilder(temp).reverse().toString());
            return;
        }
        if (A.charAt(i - 1) == B.charAt(j - 1)) {
            temp += A.charAt(i - 1) + "";
            search(dp, set, i - 1, j - 1, temp, longest, A, B);
        } else {
            //如果左面大值大于等于上面，搜左面
            if (dp[i][j - 1] >= dp[i - 1][j]) {
                search(dp, set, i, j - 1, temp, longest, A, B);
            }
            //如果上面大于等于左面，搜上面
            if (dp[i - 1][j] >= dp[i][j - 1]) {
                search(dp, set, i - 1, j, temp, longest, A, B);
            }
        }
    }

    public static void main(String[] args) {
        printAllLCS("ABCDEF", "ACBEF");
    }

}