import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 直接dfs超时，同三数之和一样的思路
 *
 * 要找到a+b+c+d = target,就是 c+d = target - a - b，要求a <= b <= c <= d，所以依旧是用之前的思路
 */
public class _058_四数之和 {

    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return res;
        }
        int n = nums.length;
        Arrays.sort(nums);
        //固定a
        for (int i = 0; i < n - 3; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }

            //固定b
            for (int j = i + 1; j < n - 2; j++) {
                if (j > i + 1 && nums[j] == nums[j - 1]) {
                    continue;
                }
                int left = j + 1;
                int right = n - 1;
                //不能直接写target = target - nums[i] - nums[j];这样就修改了原本的额target
                int temptarget = target - nums[i] - nums[j];
                while (left < right) {
                    if (nums[left] + nums[right] < temptarget) {
                        left++;
                    } else if (nums[left] + nums[right] > temptarget) {
                        right--;
                    } else {
                        ArrayList<Integer> list = new ArrayList<>();
                        list.add(nums[i]);
                        list.add(nums[j]);
                        list.add(nums[left]);
                        list.add(nums[right]);
                        res.add(list);
                        left++;
                        right--;

                        while (left < right && nums[left] == nums[left - 1]) {
                            left++;
                        }
                        while (left < right && nums[right] == nums[right + 1]) {
                            right--;
                        }
                    }
                }
            }
        }
        return res;
    }

}