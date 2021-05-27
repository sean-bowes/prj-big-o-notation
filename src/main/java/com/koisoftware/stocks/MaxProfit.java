package com.koisoftware.stocks;

import java.util.Arrays;

public class MaxProfit {
    public int maxProfit(int[] prices) {
        int T_i10 = 0, T_i11 = Integer.MIN_VALUE;
        for (int price : prices) {
            T_i10 = Math.max(T_i10, T_i11 + price);
            T_i11 = Math.max(T_i11, -price);
        }
        return T_i10;
    }

    public int maxProfitNoneMultipleTransactions(int[] prices) {
        int T_ik0 = 0, T_ik1 = Integer.MIN_VALUE;
        for (int price : prices) {
            int T_ik0_old = T_ik0;
            T_ik0 = Math.max(T_ik0, T_ik1 + price);
            T_ik1 = Math.max(T_ik1, T_ik0_old - price);
        }
        return T_ik0;
    }

    public int maxProfitWithAtMostTwoTransactions(int[] prices) {
        int T_i10 = 0, T_i11 = Integer.MIN_VALUE;
        int T_i20 = 0, T_i21 = Integer.MIN_VALUE;
        for (int price : prices) {
            T_i20 = Math.max(T_i20, T_i21 + price);
            T_i21 = Math.max(T_i21, T_i10 - price);
            T_i10 = Math.max(T_i10, T_i11 + price);
            T_i11 = Math.max(T_i11, -price);
        }
        return T_i20;
    }

    public int maxProfitWithLimitedTransactions(int k, int[] prices) {
        if (k >= prices.length >>> 1) {
            int T_ik0 = 0, T_ik1 = Integer.MIN_VALUE;
            for (int price : prices) {
                int T_ik0_old = T_ik0;
                T_ik0 = Math.max(T_ik0, T_ik1 + price);
                T_ik1 = Math.max(T_ik1, T_ik0_old - price);
            }
            return T_ik0;
        }
        int[] T_ik0 = new int[k + 1];
        int[] T_ik1 = new int[k + 1];
        Arrays.fill(T_ik1, Integer.MIN_VALUE);
        for (int price : prices) {
            for (int j = k; j > 0; j--) {
                T_ik0[j] = Math.max(T_ik0[j], T_ik1[j] + price);
                T_ik1[j] = Math.max(T_ik1[j], T_ik0[j - 1] - price);
            }
        }
        return T_ik0[k];
    }

    public int maxProfitWithCoolDown(int[] prices) {
        int T_ik0_pre = 0, T_ik0 = 0, T_ik1 = Integer.MIN_VALUE;
        for (int price : prices) {
            int T_ik0_old = T_ik0;
            T_ik0 = Math.max(T_ik0, T_ik1 + price);
            T_ik1 = Math.max(T_ik1, T_ik0_pre - price);
            T_ik0_pre = T_ik0_old;
        }
        return T_ik0;
    }

    public int maxProfitWithTransactionFee(int[] prices, int fee) {
        long T_ik0 = 0, T_ik1 = Integer.MIN_VALUE;
        for (int price : prices) {
            long T_ik0_old = T_ik0;
            T_ik0 = Math.max(T_ik0, T_ik1 + price - fee);
            T_ik1 = Math.max(T_ik1, T_ik0_old - price);
        }
        return (int) T_ik0;
    }
}
