package 序列型;

import java.util.Arrays;
import java.util.TreeSet;

/**
 * 序列型dp
 * 不要求连续了，从最后一步出发看，假设已经找到了最长上升子序列，去掉最后一个，它的前面那部分也是在这个区域的最长上升子序列
 */
public class _076_最长上升子序列 {

    //O(n^2)
    public int longestIncreasingSubsequence(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        int n = nums.length;
        int[] dp = new int[n + 1];
        //空串
        dp[0] = 0;
        int res = 1;
        for (int i = 1; i <= n; i++) {
            dp[i] = 1;
            //遍历它之前的dp[j]
            for (int j = 1; j < i; j++) {
                if (nums[j - 1] < nums[i - 1]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            res = Math.max(res, dp[i]);
        }
        return res;
    }

    /**
     * 优化成O(N logN),看到这个就只有二分法了
     * 优化:一旦前面有两个dp值一样了，比如dp[i] = dp[j],并缺nums[i] > nums[j] ，那就只要考虑第j个就可以了
     * 也就是 同样的dp值，存一个坐标，这个坐标对应的nums[index]值最小。那么对于每个dp值，保存一下对应的nums[i]的值
     * 序列是单调上升的，在单调上升中找最后一个比自己小的数用二分法
     * 我们开个数组，数组的下表为dp值，对应存的是该dp值下最小的nums[idx]
     */

    //1、使用 binarySearch()
    public static int longestIncreasingSubsequence2(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int n = nums.length;
        int[] a = new int[n];
        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            //在a数组的这个区间内找有没有nums[i]，如果key在数组中，则返回搜索值的索引；否则返回-1或“-”（插入点）。插入点是索引键将要插入数组的那一点
            int index = Arrays.binarySearch(a, 0, res, nums[i]);
            //如果如果这个数比之前的数大，就找不到插入位置，它就会在新位置插入，如果这个数比之前的数小，就会直接覆盖之前的数
            if (index < 0) {
                index = -index - 1;
            }
            //把这个数放在插入点上
            a[index] = nums[i];
            if (index == res) {
                res++;
            }
        }
        return res;
    }

    /**
     * 使用TreeSet
     * TreeSet基本操作全是log(n)复杂度（欢迎纠正），时间复杂度也一致。
     * TreeSet.ceiling(x)方法可以直接找出set中大于x的最小数字，如果不存在则返回null。
     *
     * 1. 如果这个数字存在，则删除这个数字，然后把x插入set中，相当于代替该数字。
     * 2. 如果这个数字不存在，说明x大于set中任何数字，直接把x插入set中。
     * 最后返回set的大小即可。
     */

    public int longestIncreasingSubsequence3(int[] nums) {
        TreeSet<Integer> set = new TreeSet<>();
        for (int num : nums) {
            Integer ceiling = set.ceiling(num);
            //如果set中大于num的最小数字存在，删除这个数字，放入num
            if (ceiling != null) {
                set.remove(ceiling);
            }
            set.add(num);
        }
        return set.size();
    }

    public static void main(String[] args) {
        int[] nums = {2, 1, 4, 5, 3};
        longestIncreasingSubsequence2(nums);
    }



}