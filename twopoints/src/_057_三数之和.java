import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 给出一个有n个整数的数组S，在S中找到三个整数a, b, c，找到所有使得a + b + c = 0的三元组。
 *
 * 思路：直接dfs三数只和会超出时间限制，转为 a +b = -c，固定一个dfs两数只和，实际上是固定最小的a
 *      由于要求放的不是下标，可以不用hashmap，直接sort，如果要求要放下标，那就要hashmap
 */
public class _057_三数之和 {

    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length < 3) {
            return res;
        }
        int n = nums.length;

        Arrays.sort(nums);
        //三个数中，规定最小的a,不能固定大的
        for (int i = 0; i < n - 2; i++) {
            //去重
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            //得到a的右面区间
            int left = i + 1;
            int right = n - 1;
            //target b+c = -a
            int target = -nums[i];
            while (left < right) {
                if (nums[left] + nums[right] < target) {
                    left++;
                } else if (nums[left] + nums[right] > target) {
                    right--;
                } else {
                    //相等情况,从小到大添加三个元素
                    List<Integer> list = new ArrayList<>();
                    list.add(-target);
                    list.add(nums[left]);
                    list.add(nums[right]);
                    res.add(list);
                    left++;
                    right--;

                    //如果left++后，这个数字和前面数字是一样的，那么继续加，因为上面做了去重操作
                    while (left < right && nums[left] == nums[left - 1]) {
                        left++;
                    }

                    //如果right--后，这个数字和后面数字是一样的，那么继续减，因为上面做了去重操作
                    while (left < right && nums[right] == nums[right + 1]) {
                        right--;
                    }
                }
            }
        }
        return res;
    }


}