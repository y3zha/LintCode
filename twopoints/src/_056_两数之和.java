import java.util.Arrays;
import java.util.HashMap;

/**
 * 给一个整数数组，找到两个数使得他们的和等于一个给定的数 target。
 * 返回这两个数的下标, 并且第一个下标小于第二个下标。注意这里下标的范围是 0 到 n-1
 *
 * 这里的数组是没有排序的，所以要用个hash表来记录数字对应的原始位置
 *
 * 如果是排序的可以用双指针
 */
public class _056_两数之和 {

    public int[] twoSum(int[] numbers, int target) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < numbers.length; i++) {
            map.put(numbers[i], i);
        }
        for (int i = 0; i < numbers.length; i++) {
            if (map.containsKey(target - numbers[i])) {
                return new int[]{i, map.get(target - numbers[i])};
            }
        }
        return new int[]{};
    }
}