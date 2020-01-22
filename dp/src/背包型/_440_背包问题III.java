package 背包型;

/**
 * 将Backpack II的物品改为无穷多个，背包最大承重m，求能带走的最大价值。
 * 现在每个物品变为无穷多个了，那么就可以对物品分种类
 *
 * f[i][w] = max k>=0{f[i-1] [w-kAi-1] + kVi-1}，表示用前i种物品拼出重量w时的最大总价值
 * 等于用前i-1种物品拼出重量w-kAi-1 时最大总价值，加上k个第i种物品，当k = 0和1时，就可以直接用在Backpack II 中了。
 *
 */
public class _440_背包问题III {
    public int backPackIII(int[] A, int[] V, int m) {
        // Write your code here
        int[] f = new int[m + 1];

        for(int i = 0; i < A.length; i++){
            for(int j = A[i]; j <= m; j++){
                //对于当前物品i，若j从小到大的话，很可能在j之前的j-A[i]时已经放过第i件物品了，在j时再放就是重复放入；
                // 若j从大到小，则j之前的所有情况都没有更新过，不可能放过第i件物品，所以不会重复放入。
                if(f[j - A[i]] + V[i] > f[j]){
                    f[j] = f[j - A[i]] + V[i];
                }
            }
        }

        return f[m];
    }
}