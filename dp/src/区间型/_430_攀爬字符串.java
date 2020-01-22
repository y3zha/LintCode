package 区间型;

/**
 * 又叫扰乱字符串
 *
 * 给定两个字符串S和T，判断T是否由S扰乱过来的
 * 首先如果两个字符串长度不等，肯定不行。
 * 假设S能够拆分成S1和S2，T也能够拆分成T1和T2
 *      情况一：没有发生交换，S1->T1,S2->T2
 *      情况二：发生了交换，S1->T2,S2->T1
 * 可以设置数组dp[i][j][k][h],表示字符串T[k..h]能否由S[i..j]变换而来，
 * 因为长度不一样肯定是不能变换而来的，所以j - i = h - k,可以把四维数组降成三维
 * dp[i][j][len]  表示 从字符串S中i开始长度为len的字符串 是否能变换为 从字符串T中j开始长度为len的字符串
 */
public class _430_攀爬字符串 {

    public boolean isScramble(String s1, String s2) {
        int n = s1.length();
        int m = s2.length();
        if (n != m) {
            return false;
        }
        char[] sc1 = s1.toCharArray();
        char[] sc2 = s2.toCharArray();
        boolean[][][] dp = new boolean[n][n][n + 1];

        //初始化区间长度为1的情况
        // S从i开始
        for (int i = 0; i < n; i++) {
            // T从j开始
            for (int j = 0; j < n; j++) {
                if (sc1[i] == sc2[j]) {
                    dp[i][j][1] = true;
                }
            }
        }

        //枚举剩下的长度
        for (int len = 2; len <= n; len++) {
            //枚举S中的起点
            for (int i = 0; i <= n - len; i++) {
                //枚举T中的起点
                for (int j = 0; j <= n - len; j++) {
                    //枚举划分的位置，必须划分成两段，不能划分为空串
                    for (int k = 1; k <= len - 1; k++) {
                        //第一种情况：S1->T1,S2->T2
                        if (dp[i][j][k] && dp[i + k][j + k][len - k]) {
                            dp[i][j][len] = true;
                            break;
                        }
                        //第二种情况：S1->T2,S2->T1,后面那段长度为len-k，前面那段长度为k
                        if (dp[i][j + len - k][k] && dp[i + k][j][len - k]) {
                            dp[i][j][len] = true;
                            break;
                        }
                    }
                }
            }
        }
        return dp[0][0][n];
    }
}