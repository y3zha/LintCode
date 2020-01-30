/**
 * 62题是在旋转数组中找到指定元素，这里要找最小值
 *
 * 最简单的思路，遍历一边数组，找最小的，O（n）
 *
 * 但是可以用二分，看到排序就要想二分了
 *
 * 思路和62题差不多，前面半段是上升的，后面半段也是上升的，这个不连续间断点就是我们要找的点
 * 首先记录最后一个数字的值，我们不断缩小区间去找，如果mid上的数字大于last，就说明太靠前了，整个区间要往右移动，如果mid上的数字小于last，就说明太靠右了，要向左移动区间
 *
 * 本题数组中的元素都是不重复的！！
 */
public class _159_寻找旋转排序数组中的最小值 {

    public int findMin(int[] nums) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        int start = 0;
        int end = nums.length - 1;
        int last = nums[end];

        while (start + 1 < end) {
            int mid = start + (end - start) / 2;

            if (nums[mid] <= last) {
                //说明太靠右，要向左移动
                end = mid;
            } else {
                //说明太靠左，要向右移动
                start = mid;
            }
        }
        //double check
        if (nums[start] <= nums[end]) {
            return nums[start];
        } else {
            return nums[end];
        }
    }
}