package 序列型;

/**
 * 较上一题的变化是可以完成多笔交易，贪心做
 * 只要是涨了就卖
 */
public class _150_买卖股票的最佳时机II {

    public int maxProfit(int[] prices) {
        int res = 0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] > prices[i - 1]) {
                res += prices[i] - prices[i - 1];
            }
        }
        return res;
    }
}