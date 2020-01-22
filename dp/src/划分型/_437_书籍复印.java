package 划分型;

/**
 * 书本分发，划分型dp
 * 假设一共n本书，由k个抄写员来抄写，每个人抄的都是连续的，问最短的抄写时间是多少
 * 解决思路从最后一个人开始看，最后一个人可能抄0本、1本、2本...n本书，有两种情况
 *      第一种情况：最后一个人抄书耗费时间比之前抄书耗费的时间少，那就跟他没关系了
 *      第二种情况：最后一个人抄书耗费的时间比前面抄书耗费的时间多，那就是这个时间
 * 我们要找的是 每个人抄完自己那段和的最大值（这是完成任务的最短时间） 中的最小值
 * dp[i][j] 代表第i个人要抄写j本书
 * 根据这两种情况可得出公式
 * dp[i][j] = max{dp[i-1][l],sum},sum是第i个抄写员抄写书花费的时间，
 */

public class _437_书籍复印 {

    //dp
    public int copyBooks(int[] pages, int k) {
        int n = pages.length;
        int[][] dp = new int[k + 1][n + 1];

        //初始化k个抄写员抄写0本书，时间为0
        for (int i = 0; i <= k; i++) {
            dp[i][0] = 0;
        }

        //0个抄写员抄写大于0本书的情况，时间都是无穷大
        for (int j = 1; j <= n; j++) {
            dp[0][j] = Integer.MAX_VALUE;
        }

        //一共派i个人员来抄书
        for (int i = 1; i <= k; i++) {
            //这i个人员一共抄几本
            for (int j = 1; j <= n; j++) {
                dp[i][j] = Integer.MAX_VALUE;
                //前i-1个人抄写它们的书所花的时间
                int sum = 0;
                //最后一位同志抄几本书，l是前i-1个人抄的书的数量，从j开始，这样就代表最后一位同志抄的书从0开始计算了
                for (int l = j; l >= 0; l--) {
                    dp[i][j] = Math.min(dp[i][j], Math.max(dp[i - 1][l], sum));
                    if (l > 0) {
                        sum += pages[l - 1];
                    }
                }
            }
        }
        return dp[k][n];
    }

    //二分

}