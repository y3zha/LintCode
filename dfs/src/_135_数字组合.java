import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 给定一个候选数字的集合 candidates 和一个目标值 target. 找到 candidates 中所有的和为 target 的组合.
 * 在同一个组合中, candidates 中的某个数字不限次数地出现.
 *
 * 注意，输入是可能重复的，并没有说数组中的数字是唯一的（leetcode上给的无重复元素的，但是lintcode给的是重复元素的）
 */
public class _135_数字组合 {

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        if (candidates == null || candidates.length == 0) {
            return res;
        }
        Arrays.sort(candidates);
        dfs(candidates, 0, target, new ArrayList<Integer>(), res);
        return res;
    }

    private void dfs(int[] candidates, int startIndex, int target, ArrayList<Integer> list, List<List<Integer>> res) {
        if (target < 0) {
            return;
        }
        if (target == 0) {
            res.add(new ArrayList<>(list));
            return;
        }
        for (int i = startIndex; i < candidates.length; i++) {
            if (target - candidates[i] < 0) {
                continue;
            }
            if (i != 0 && candidates[i] == candidates[i - 1]) {
                continue;
            }
            list.add(candidates[i]);
            //可以无限次选
            dfs(candidates, i, target - candidates[i], list, res);
            list.remove(list.size() - 1);
        }
    }
}