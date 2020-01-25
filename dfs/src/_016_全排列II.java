import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 带重复元素的全排列
 *
 * 首先给nums排序，因为它并没有告诉我们是有序的，由于有重复数字，举例
 * 比如1 2 2，求全排列
 * 可以认为是1 2' 2''全排列，你如果不去重，就会得到[1,2',2'']与[1，2''，2'],但实际情况都是一样的
 * 我们肯定选择第一种，这个按顺序出现的，也就是如果当前数字和前一个数字相等，但是前一个数字我们没选，那么当前数字就不能选
 */

public class _016_全排列II {

    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null) {
            res.add(new ArrayList<>());
            return res;
        }
        //老是忘记这里要排序
        Arrays.sort(nums);
        boolean[] visited = new boolean[nums.length];
        dfs(nums, new ArrayList<Integer>(), res, visited);
        return res;
    }

    private void dfs(int[] nums, ArrayList<Integer> list, List<List<Integer>> res, boolean[] visited) {
        if (list.size() == nums.length) {
            res.add(new ArrayList<>(list));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            //首先判断:如果当前数字的前一个数字和这个数字相当，但是前一个数字没用，那么只个数字也不能用
            if (i > 0 && !visited[i - 1] && nums[i] == nums[i - 1]) {
                continue;
            }
            if (!visited[i]) {
                visited[i] = true;
                list.add(nums[i]);
                dfs(nums, list, res, visited);
                list.remove(list.size() - 1);
                visited[i] = false;
            }
        }
    }

}