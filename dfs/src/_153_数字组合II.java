import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 组合数2，和i不同的是每个元素只能用一次,但是输入依旧是有重复的
 */
public class _153_数字组合II {

    public List<List<Integer>> combinationSum2(int[] num, int target) {
        List<List<Integer>> res = new ArrayList<>();
        if (num == null || num.length == 0) {
            return res;
        }
        Arrays.sort(num);
        dfs(num, 0, target, new ArrayList<Integer>(), res);
        return res;
    }

    private void dfs(int[] num, int startIndex, int target, ArrayList<Integer> list, List<List<Integer>> res) {
        if (target < 0) {
            return;
        }
        if (target == 0) {
            res.add(new ArrayList<>(list));
            return;
        }
        for (int i = startIndex; i < num.length; i++) {
            if (target - num[i] < 0) {
                continue;
            }
            if (i != startIndex && num[i] == num[i - 1]) {
                continue;
            }
            list.add(num[i]);
            dfs(num, i + 1, target - num[i], list, res);
            list.remove(list.size() - 1);
        }
    }
}