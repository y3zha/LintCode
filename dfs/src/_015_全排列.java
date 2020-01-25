import java.util.ArrayList;
import java.util.List;

public class _015_全排列 {

    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            res.add(new ArrayList<>());
            return res;
        }
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