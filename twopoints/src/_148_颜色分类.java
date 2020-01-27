/**
 * 给定一个包含红，白，蓝且长度为 n 的数组，将数组元素进行分类使相同颜色的元素相邻，并按照红、白、蓝的顺序进行排序。
 *
 * 红白蓝三个颜色放在一起
 * 就是按照0，1，2放，不能排序
 *
 * 思路：三指针！
 *      一根指向头，一根指向尾，还有一根遍历数组。把0丢到左边去，把2丢到右边去，遍历指针碰到1就继续走
 *      如果cur指针指向0了，需要将当前元素0和left指针指向的元素交换，left++
 *      如果cur指针指向2了，需要将当前元素2和right指针指向的元素交换，right--
 */
public class _148_颜色分类 {

    public void sortColors(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        int cur = 0;

        while (cur <= right) {
            if (nums[cur] == 0) {
                swap(nums, left, cur);
                cur++;
                left++;
            } else if (nums[cur] == 2) {
                //这里只需要right--，不需要cur++，因为不知道right交换过来的是什么，如果交换过来的是2呢，对吧
                //但是对于等于0的情况，我们是把它扔到前面去，不回头特殊情况的。
                swap(nums, right, cur);
                right--;
            } else {
                cur++;
            }
        }
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}