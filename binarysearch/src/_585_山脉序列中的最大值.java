/**
 * 给 n 个整数的山脉数组，即先增后减的序列，找到山顶（最大值）
 *
 * 思路是：是山峰的条件是前半部分满足条件nums[i] < nums[i + 1]，而数组的后半部分满足条件nums[i] > nums[i + 1]
 * 这道题的数组中如果存在重复元素的话，就不能用二分法，只能遍历整个数组来做。这里做的时候默认数组中不存在重复元素
 * 这个题只有一个山峰，只要缩小区间即可
 *
 * 这个题的拓展是 75题，它有很多个山峰 升、降、升、降、升、降...
 */

public class _585_山脉序列中的最大值 {

    public int mountainSequence(int[] nums) {
        int start = 0;
        int end = nums.length - 1;
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] < nums[mid + 1]) {
                start = mid;
            } else {
                end = mid;
            }
        }
        return Math.max(nums[start], nums[end]);
    }
}