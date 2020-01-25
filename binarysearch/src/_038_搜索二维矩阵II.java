/**
 * 写出一个高效的算法来搜索m×n矩阵中的值，返回这个值出现的次数。
 * 这个矩阵具有以下特性：每行中的整数从左到右是排序的。每一列的整数从上到下是排序的。在每一行或每一列中没有重复的整数。
 *
 * 这个题和28题的区别是，没有说下面的都比前面的大的。这个题的特点是左上角最小，右下角最大
 * 每次可以在O（1）内排除一行或者一列，可以选择从右上角往左下角逼近，为什么呢
 * 从最右上角的元素开始找，如果这个元素比target大，则说明找更小的，往左走；如果这个元素比target小，则说明应该找更大的，往下走。
 * 所以只能往左往下走，趋势是右上向左下逼近
 *
 * O(m+n) 时间复杂度
 */
public class _038_搜索二维矩阵II {

    //从右上角往左下角逼近
    public int searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0) {
            return -1;
        }
        int n = matrix.length;
        int m = matrix[0].length;
        int start = 0;
        int end = m - 1;
        //返回这个值出现的次数，每一行或每一列都不会重复出现这个数字
        int count = 0;
        //往左下角逼近
        while (start <= n - 1 && end >= 0) {
            if (matrix[start][end] < target) {
                //比target小，往下走
                start++;
            } else if (matrix[start][end] > target) {
                //比target大，往左走
                end--;
            } else {
                //相等，往下走、往左走，count++
                start++;
                end--;
                count++;
            }
        }
        return count;
    }
}