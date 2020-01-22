package 序列型;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

/**
 * 也算是属于最长序列型dp
 *
 * 比较笨的做法，和最长上升子序列一样
 * 首先指定一个维度，长度或宽度，比如指定长度，按长度升序排序，长度一样按宽度升序排序
 * 然后对这个信封，遍历它前面的。。
 * dp[i]代表到第i个信封，它最多能嵌套多少个
 */
public class _602_俄罗斯套娃信封 {

    //这么写过不去，超时
    public int maxEnvelopes(int[][] envelopes) {
        if (envelopes == null || envelopes.length == 0) {
            return 0;
        }
        if (envelopes.length == 1) {
            return 1;
        }
        //排序
        Arrays.sort(envelopes, Comparator.comparing((int[] a) -> a[0]).thenComparing((int[] a) -> a[1]));
        int n = envelopes.length;
        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = 1;
        int res = 1;
        for (int i = 2; i <= n; i++) {
            dp[i] = 1;
            for (int j = 1; j < i; j++) {
                if (envelopes[i - 1][0] > envelopes[j - 1][0] && envelopes[i - 1][1] > envelopes[j - 1][1]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
                res = Math.max(res, dp[i]);
            }
        }
        return res;
    }

    //使用二分
    public int maxEnvelopes2(int[][] envelopes) {
        if (envelopes == null || envelopes.length == 0 || envelopes[0] == null || envelopes[0].length != 2) {
            return 0;
        }
        // 先按 w 升序排序，再按 h 降序 排序！！
        // 然后只需考虑h即可，因为w已经升序排列好，因为h大的在前，所以相同的w下的不同h，只会选择最大的那个h，来看以这个h结尾的最长上升子序列
        // 当w相同的情况下，h高的在前面，也就是说同样w中是不可能满足increasing subsequence的序列存在，所以任何的increasing subsequence的w一定都是升序的
        // 就可以将问题转换为 h 的 Longest Increasing subSequence
        Arrays.sort(envelopes, Comparator.comparing((int[] a) -> a[0]).thenComparing((int[] a) -> a[1], Comparator.reverseOrder()));

        int dp[] = new int[envelopes.length];
        int len = 0;
        for (int[] a : envelopes) {
            int index = Arrays.binarySearch(dp, 0, len, a[1]);
            if (index < 0) {
                index = -index - 1;
            }
            dp[index] = a[1];
            if (index == len) {
                len++;
            }
        }
        return len;
    }
}