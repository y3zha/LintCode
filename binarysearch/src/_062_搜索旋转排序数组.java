/**
 * 给定了一个数组，这个数组原来是排序的，然后数组是旋转过的，给定target，找到它的下标，要求时间复杂度O(logN)
 * 输入: [4, 5, 1, 2, 3] and target=1,
 * 输出: 2
 *
 * 思路，分成两段，前面那段是上升的，后面那段也是上升的，二分取mid的时候，判断这个mid取在了前面半段还是后面半段（只要和最后一个元素比较下即可）
 *      确定在哪一段后， 然后需要去判断target在mid的左半部分还是右半部分
 *            如果 A[start] <= target <= A[mid],那就在左半部分
 *            如果 A[mid] <= target <= A[end],那就在右半部分
 *
 */
public class _062_搜索旋转排序数组 {

    public int search(int[] A, int target) {
        if (A == null || A.length == 0) {
            return -1;
        }
        int start = 0;
        int end = A.length - 1;
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            int last = A[end];
            if (A[mid] == target) {
                return mid;
            }
            //在前半部分
            if (A[mid] > last) {
                //缩小范围
                if (A[start] <= target && target <= A[mid]) {
                    end = mid;
                } else {
                    start = mid;
                }
            } else {
                //在后半部分，缩小范围
                if (A[mid] <= target && target <= A[end]) {
                    start = mid;
                } else {
                    end = mid;
                }
            }
        }
        //double check
        if (A[start] == target) {
            return start;
        }
        if (A[end] == target) {
            return end;
        }
        return -1;
    }
}