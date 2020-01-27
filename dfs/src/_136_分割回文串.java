import java.util.ArrayList;
import java.util.List;

/**
 * 给定字符串 s, 需要将它分割成一些子串, 使得每个子串都是回文串.
 * 返回所有可能的分割方案.
 *
 * 输入: "aab"
 * 输出: [["aa", "b"], ["a", "a", "b"]]
 *
 * 所有的切割问题都是【组合】问题==> dfs ，见135、153
 * 对于abc,有a b c、ab c、a bc、abc这几种切割方式，实际上就相当于a1b2c，在哪里切
 * a b c：选择在1和2切
 * ab c：选择在2切
 * a bc：选择在1切
 * abc：都不选择
 *
 * 3个字符的切割对应2个数字的组合问题
 * n个数字的切割，对应n-1个数字的组合问题 =========>套用subsets的方案
 */

public class _136_分割回文串 {

    public List<List<String>> partition(String s) {
        List<List<String>> res = new ArrayList<>();
        boolean[][] f = getPalin(s);
        dfs(s, 0, f, new ArrayList<String>(), res);
        return res;
    }

    private void dfs(String s, int startIndex, boolean[][] f, ArrayList<String> list, List<List<String>> res) {
        if (startIndex == s.length()) {
            res.add(new ArrayList<>(list));
        }
        for (int i = startIndex; i < s.length(); i++) {
            String sub = s.substring(startIndex, i + 1);
            if (!f[startIndex][i]) {
                continue;
            }
            list.add(sub);
            dfs(s, i + 1, f, list, res);
            list.remove(list.size() - 1);
        }
    }

    private boolean[][] getPalin(String s) {
        char[] sc = s.toCharArray();
        int n = s.length();
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
        //中心线扩展
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