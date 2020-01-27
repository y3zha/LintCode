/**
 * 给定一个有n个对象（包括k种不同的颜色，并按照1到k进行编号）的数组，将对象进行分类使相同颜色的对象相邻，并按照1,2，...k的顺序进行排序。
 * 最直观的是计数排序，但是不允许使用额外空间
 * 最好的思路，时间复杂度nlogk（分治）
 *      从k个颜色下手，比如[3,2,2,1,4]，一共4种颜色，在O(n)的时间复杂度内把颜色分为两边，一边[2,2,1]，一边[3,4]，就是一边是1～k/2个颜色，一边k/2 +1 ~ k
 *      然后再分。。。mergesort的思想，每一层划分时间复杂度都是O(n)，整棵树高度logk
 * 这个实质上是快排+归并，用快排的思想对颜色进行partition，每一层O(n)，用归并排序限制了递归的层次一共logk层
 */
public class _143_排颜色II {

    public void sortColors2(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return;
        }
        rainbowSort(nums, 0, nums.length - 1, 1, k);
    }

    //left和right对nums划分，划分为左区间和右区间，在每个区间内，颜色从哪里到哪里
    private void rainbowSort(int[] nums, int left, int right, int colorFrom, int colorTo) {
        //递归出口
        if (left >= right|| colorFrom == colorTo) {
            return;
        }
        //在nums中根据颜色partition为两部分
        int colorMid = colorFrom + (colorTo - colorFrom) / 2;   //即(colorFrom + colorTo) / 2;
        int l = left, r = right;
        while (l <= r) {
            while (l <= r && nums[l] <= colorMid) {
                l++;
            }
            while (l <= r && nums[r] > colorMid) {
                r--;
            }
            if (l <= r) {
                int temp = nums[l];
                nums[l] = nums[r];
                nums[r] = temp;
                l++;
                r--;
            }
        }
        //由于我上面写的是nums[l] <= colorMid，nums[r] > colorMid，所以l最后指向了>colorMid,r指向了<=colorMid
        //所以left到r是小于等于colorMid的区域，l到right是对于cloudMid的区域
        rainbowSort(nums, left, r, colorFrom, colorMid);
        rainbowSort(nums, l, right, colorMid + 1, colorTo);
    }
}