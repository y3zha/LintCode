/**
 * 585题的升级版
 * 给出一个整数数组(size为n)，其具有以下特点：
 *
 * 相邻位置的数字是不同的
 * A[0] < A[1] 并且 A[n - 2] > A[n - 1]
 * 假定P是峰值的位置则满足A[P] > A[P-1]且A[P] > A[P+1]，返回数组中任意一个峰值的位置。
 *
 * 如果这个题要求找出所有的极大值，那就是遍历了，如果是任意一个，就是二分
 * 二分的mid可能处于四个位置，可以画一下
 * 1、上坡中的某个位置  （右边某个位置一定存在峰，左半部分不确定）
 * 2、下坡中的某个位置  （左半部分一定存在某个峰，右半部分不确定）
 * 3、正好位于峰上      （左右都可以去）
 * 4、正好位于谷底 （这种情况去左右都没关系，可以合并到1和2两个情况中）
 *
 * 也就是：
 * 每次取中间元素，如果大于左右，则这就是peek。
 * 否则取大的一边，两个都大，可以随便取一边。最终会找到peek。
 */
public class _075_寻找峰值 {

    public int findPeak(int[] nums) {
        int start = 0;
        int end = nums.length - 1;
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            //如果处于上坡，右边一定存在某个峰，只能取右侧，如果处于下坡，左边一定存在某个峰，右侧不确定
            if (nums[mid] < nums[mid + 1]) {
                start = mid;
            } else if (nums[mid] < nums[mid - 1]) {
                end = mid;
            } else {    //极大值或是极小值的情况，去左去右都没关系,start = mid或者end = mid 都可以
                start = mid;
            }
        }
        //double check
        if (nums[start] < nums[end]) {
            return end;
        } else {
            return start;
        }
    }
}