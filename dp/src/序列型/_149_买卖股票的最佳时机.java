package 序列型;

/**
 * 非序列型dp
 * 只允许一次买卖股票
 * 只需到当前的的最低价格，每次更新下利润
 */
public class _149_买卖股票的最佳时机 {

    public int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0) {
            return 0;
        }
        int res = 0;
        int crtMin = Integer.MAX_VALUE;
        for (int i = 0; i < prices.length; i++) {
            if (prices[i] < crtMin) {
                crtMin = prices[i];
            }
            res = Math.max(res, prices[i] - crtMin);
        }
        return res;
    }
}