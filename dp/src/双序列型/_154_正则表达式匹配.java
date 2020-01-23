package 双序列型;

/**
 * 【双序列型】
 * 实现支持'.'和'*'的正则表达式匹配。'.'匹配任意一个字母。'*'匹配零个或者多个前面的元素。
 *
 * 从最后一步出发，就是看B的最后一个和A的最后一个字符匹不匹配
 * 假设A的长度为n，B的长度为m，就是看A[n-1]是否等于B[m-1]
 * 有这么几种情况
 *      1、B最后一个字符就是A最后一个字符，B[m-1] == A[n-1]，匹配
 *      2、B的最后一个字符为'.'，匹配
 *      3、B的最后一个字符为'*'，这就要看B的倒数第二个字符B[m-2]了，假设为 B[m-2]=c，c*要作为一个整体
 *          3.1 如果A此时最后一个字符不是'c'，B最后两个字符'c*'就废了
 *          3.2 如果A此时最后一个字符是'c'，可能是多个c中的一个，这种情况必须是A[n-1]=c或者c='.'
 *              A匹配完往前挪一个，B继续匹配，因为可以匹配多个
 *          只要分成看和不看两种情况即可
 *
 */
public class _154_正则表达式匹配 {

    //A普通串，B正则串
    public boolean isMatch(String A, String B) {
        int n = A.length();
        int m = B.length();
        boolean[][] dp = new boolean[n + 1][m + 1];
        //不能直接从1开始，空的字符串和非空正则表达式是有可能匹配的
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= m; j++) {
                if (j == 0) {
                    if (i == 0) {
                        //空串和空正则是匹配的
                        dp[i][j] = true;
                        continue;
                    }
                } else {    //j>0的情况
                    if (B.charAt(j - 1) != '*') {
                        //注意括号
                        if (i >= 1 && (B.charAt(j - 1) == A.charAt(i - 1) || B.charAt(j - 1) == '.')) {
                            dp[i][j] = dp[i - 1][j - 1];
                        }
                    } else {
                        //是*的情况，分为废掉和 没废两种情况（看和不看两种情况）
                        if (j >= 2) {
                            //直接不看
                            dp[i][j] |= dp[i][j - 2];
                        }
                        //看的情况
                        if (i >= 1 && j >= 2) {
                            if (A.charAt(i - 1) == B.charAt(j - 2) || B.charAt(j - 2) == '.') {
                                dp[i][j] |= dp[i - 1][j];
                            }
                        }
                    }
                }
            }
        }
        return dp[n][m];
    }

    //不用dp的方式
    public boolean isMatch2(String s, String p) {
        if (s.length() == 0) {
            return checkEmpty(p);
        }
        if (p.length() == 0){
            return false;
        }
        char s1 = s.charAt(0);
        char p1 = p.charAt(0);
        char p2 = '0';
        if (p.length() > 1) {
            p2 = p.charAt(1);
        }
        if (p2 != '*') {
            if (isCompared(s1, p1)) {
                return isMatch2(s.substring(1), p.substring(1));
            } else {
                return false;
            }
        } else {
            //没废
            if (isCompared(s1, p1)) {
                return isMatch2(s.substring(1), p);
            } else {
                //不匹配只能不看，就是废了
                return isMatch2(s, p.substring(2));
            }
        }
    }

    private boolean isCompared(char c1, char c2) {
        return c1 == c2 || c2 == '.';
    }

    private boolean checkEmpty(String p) {
        if ( p.length() % 2 != 0 ){
            return false;
        }
        for (int i = 1; i < p.length(); i+=2) {
            if (p.charAt(i) != '*'){
                return false;
            }
        }
        return true;
    }


}