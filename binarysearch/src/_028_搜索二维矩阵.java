/**
 * 写出一个高效的算法来搜索 m × n矩阵中的值。
 *
 * 这个矩阵具有以下特性：每行中的整数从左到右是排序的。每行的第一个数大于上一行的最后一个整数。
 * 时间复杂度O(log(n) + log(m))
 *
 * 思路：把二维数组摊平成一维数组,利用二维和一维之间的转化
 *
 *
 */

public class _028_搜索二维矩阵 {

    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0) {
            return false;
        }
        int n = matrix.length;
        int m = matrix[0].length;
        int start = 0;
        //一维数组的终点
        int end = n * m - 1;

        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            //在二维数组中的下标
            int i = mid / m;
            int j = mid % m;
            if (matrix[i][j] == target) {
                return true;
            } else if (matrix[i][j] < target) {
                start = mid;
            } else {
                end = mid;
            }
        }

        //double check
        if (matrix[start / m][start % m] == target || matrix[end / m][end % m] == target) {
            return true;
        } else {
            return false;
        }
    }
}