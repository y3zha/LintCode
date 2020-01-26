import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 组合数类问题
 * 给定一个含不同整数的集合，返回其所有的子集
 *
 * 没有顺序
 *
 * 组合数不像排序，它的[1,2]和[2,1]是同一个，所以需要一个startIndex
 */
public class _017_子集 {

    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            res.add(new ArrayList<>());
            return res;
        }
        Arrays.sort(nums);
        dfs(nums, 0, new ArrayList<Integer>(), res);
        return res;
    }

    private void dfs(int[] nums, int startIndex, ArrayList<Integer> list, List<List<Integer>> res) {
        res.add(new ArrayList<>(list));
        for (int i = startIndex; i < nums.length; i++) {
            list.add(nums[i]);
            dfs(nums, i + 1, list, res);    //从i+1开始
            list.remove(list.size() - 1);
        }
    }

}