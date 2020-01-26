import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 和组合数1相比，多个重复数字
 */
public class _018_子集II {

    public List<List<Integer>> subsetsWithDup(int[] nums) {
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
            //如果当前数字和前面数字一样但是前面数字没有选，那么当前数字就不能选，如何代表前面数字没选呢
            if (i > 0 && nums[i] == nums[i - 1] && i != startIndex) {
                continue;
            }
            list.add(nums[i]);
            dfs(nums, i + 1, list, res);
            list.remove(list.size() - 1);
        }
    }
}