package 背包型;

/**
 * 【背包型】
 * 持有m个0和n个1，给定一组01字符串，找到可以由 m个 0s 和 n个 1s 构成的字符串的最大个数. 每一个 0 和 1 均只能使用一次
 *
 * 假定0的个数为m个，1的个数为n个，01串一共给定了T个，就是问最多能组成多少个给定的01串
 * 依旧是和背包一样，看最后一个物品有没有进去(实际上就是放不放的问题)
 *      情况一：不放，最后一个物品（字符串）没有进去，
 *              一共给定了T个串，那就是去看前T-1个串中，用给的0和1最多能组成多少个
 *      情况二：放，最后一个物品（字符串）进去了
 *              最后一个串中有多少个0和1，那么就在m和n中减去，比如最后一个串中有j个0，k个1，那么剩下0就是m-j，剩下1就是n-k
 *              就看这些剩下的在前T-1个串中最多能组成多少个
 * 开数组
 *      dp[i][j][k] 前i个01串最多能有多少个被j个0和k个1组成
 *      就是放和放两种情况 dp[i][j][k] = max{dp[i-1][j][k],dp[i-1][j - a][k - b]}    //a代表放的这个01串中0的个数，b代表放的这个01串中1的个数
 *
 * 初始条件:f[0][0~m][0~n] = 0，无论有多少0和1，前0个01串中最多能组成0个
 */



public class _668_一和零 {

    public int findMaxForm(String[] strs, int m, int n) {
        int sum = strs.length;
        int[][][] dp = new int[sum + 1][m + 1][n + 1];

        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                dp[0][m][n] = 0;
            }
        }

        for (int i = 1; i <= sum; i++) {
            for (int j = 0; j <= m; j++) {
                for (int k = 0; k <= n; k++) {
                    //不放
                    dp[i][j][k] = dp[i - 1][j][k];
                    //放的情况,,这个不能写在这，增加了时间复杂度，要放在一开始计算
                    String s = strs[i - 1];
                    int p = s.length();
                    int count0 = 0;
                    int count1 = 0;
                    for (int l = 0; l < s.length(); l++) {
                        if (s.charAt(l) == '0') {
                            count0++;
                        }
                    }
                    count1 = p - count0;
                    if (j >= count0 && k >= count1) {
                        dp[i][j][k] = Math.max(dp[i][j][k], dp[i - 1][j - count0][k - count1] + 1);
                    }
                }
            }
        }
        return dp[sum][m][n];
    }
}