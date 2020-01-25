/**
 * 在159题的基础上，元素有重复的了。
 *
 * 普通O(n)就能解决
 *
 * 如果用二分得怎么写呢？
 * 因为有这种情况，比如[3,3,1,3]，我们记录了最后一个，last = 3，但是取mid，也是3，这就不知道该往左缩小区间还是往右缩小区间
 * 以前缩小区间都是end = mid这样，但若真出现这样的额情况，只能 end-- 这样缩小区间。最坏情况下时间复杂度还是O（n）
 */
public class _160_寻找旋转排序数组中的最小值II {

    public int findMin(int[] num) {
        //  这道题目在面试中不会让写完整的程序
        //  只需要知道最坏情况下 [1,1,1....,1] 里有一个0
        //  这种情况使得时间复杂度必须是 O(n)
        //  因此写一个for循环就好了。
        //  如果你觉得，不是每个情况都是最坏情况，你想用二分法解决不是最坏情况的情况，那你就写一个二分吧。
        //  反正面试考的不是你在这个题上会不会用二分法。这个题的考点是你想不想得到最坏情况。
        int min = num[0];
        for (int i = 1; i < num.length; i++) {
            if (num[i] < min) {
                min = num[i];
            }
        }
        return min;
    }

    //二分写法
    public int findMin2(int[] nums) {
        int start = 0;
        int end = nums.length - 1;
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;

            if (nums[mid] == nums[end]) {
                end--;
            } else if (nums[mid] < nums[end]) {
                end = mid;
            } else if (nums[mid] > nums[end]) { //说明在前半部分
                start = mid;
            }
        }
        if (nums[start] < nums[end]) {
            return nums[start];
        } else {
            return nums[end];
        }
    }


}