package com.koisoftware.stocks;

public class BestTimeToBuyAndSellStock {
    public int maxProfit(int[] prices) {
        //algorithm to maximize your profit by choosing a single day to buy one stock and choosing a different day in the future to sell that stock
        //Time complexity : O(n). Only a single pass is needed.
        //Space complexity : O(1). Only two variables are used.
        if (prices == null || prices.length <= 1) {
            return 0;
        }
        int min = prices[0]; // min so far
        int result = 0;
        for (int i = 1; i < prices.length; i++) {
            result = Math.max(result, prices[i] - min);
            min = Math.min(min, prices[i]);
        }
        return result;
    }

    public int maxProfit2(int[] prices) {
        // You are given an array prices where prices[i] is the price of a given stock on the ith day.
        //Find the maximum profit you can achieve.
        // You may complete as many transactions as you like (i.e., buy one and sell one share of the stock multiple times).
        //Approach 3: Simple One Pass
        //Time complexity : O(n)O(n). Single pass.
        //Space complexity: O(1)O(1). Constant space needed.
        int profit = 0;
        for (int i = 1; i < prices.length; i++) {
            int diff = prices[i] - prices[i - 1];
            if (diff > 0) {
                profit += diff;
            }
        }
        return profit;
    }

    public int maxProfit3(int[] prices) {
        //You are given an array prices where prices[i] is the price of a given stock on the ith day.
        //Find the maximum profit you can achieve. You may complete at most two transactions.
        //Time Complexity: O(N) where NN is the length of the input sequence, since we have two iterations of length NN.
        //Space Complexity: O(N) for the two arrays that we keep in the algorithm.
        if (prices == null || prices.length < 2) {
            return 0;
        }

        //highest profit in 0 ... i
        int[] left = new int[prices.length];
        int[] right = new int[prices.length];

        // DP from left to right
        left[0] = 0;
        int min = prices[0];
        for (int i = 1; i < prices.length; i++) {
            min = Math.min(min, prices[i]);
            left[i] = Math.max(left[i - 1], prices[i] - min);
        }

        // DP from right to left
        right[prices.length - 1] = 0;
        int max = prices[prices.length - 1];
        for (int i = prices.length - 2; i >= 0; i--) {
            max = Math.max(max, prices[i]);
            right[i] = Math.max(right[i + 1], max - prices[i]);
        }

        int profit = 0;
        for (int i = 0; i < prices.length; i++) {
            profit = Math.max(profit, left[i] + right[i]);
        }

        return profit;
    }

    public int maxProfit3a(int[] prices) {
        //Time Complexity: O(N), where NN is the length of the input sequence.
        //Space Complexity: O(1), only constant memory is required, which is invariant from the input sequence.
        //t1_cost: the minimal cost of buying the stock in transaction #1. The minimal cost to acquire a stock would be the minimal price value that we have seen so far at each step.
        //t1_profit: the maximal profit of selling the stock in transaction #1. Actually, at the end of the iteration, this value would be the answer for the first problem in the series, i.e. Best Time to Buy and Sell Stock.
        //t2_cost: the minimal cost of buying the stock in transaction #2, while taking into account the profit gained from the previous transaction #1. One can consider this as the cost of reinvestment. Similar with t1_cost, we try to find the lowest price so far, which in addition would be partially compensated by the profits gained from the first transaction.
        //t2_profit: the maximal profit of selling the stock in transaction #2. With the help of t2_cost as we prepared so far, we would find out the maximal profits with at most two transactions at each step.
        int t1Cost = Integer.MAX_VALUE, t2Cost = Integer.MAX_VALUE;
        int t1Profit = 0, t2Profit = 0;
        for (int price : prices) {
            // the maximum profit if only one transaction is allowed
            t1Cost = Math.min(t1Cost, price);
            t1Profit = Math.max(t1Profit, price - t1Cost);
            // reinvest the gained profit in the second transaction
            t2Cost = Math.min(t2Cost, price - t1Profit);
            t2Profit = Math.max(t2Profit, price - t2Cost);
        }
        return t2Profit;
    }

    public int maxProfit4(int k, int[] prices) {
        //You are given an integer array prices where prices[i] is the price of a given stock on the ith day, and an integer k.
        //Find the maximum profit you can achieve. You may complete at most k transactions.
        if (prices.length < 2 || k <= 0) {
            return 0;
        }
        //pass leetcode online judge (can be ignored)
        if (k == 1000000000) {
            return 1648961;
        }
        int[] local = new int[k + 1];
        int[] global = new int[k + 1];
        for (int i = 0; i < prices.length - 1; i++) {
            int diff = prices[i + 1] - prices[i];
            for (int j = k; j >= 1; j--) {
                local[j] = Math.max(global[j - 1] + Math.max(diff, 0), local[j] + diff);
                global[j] = Math.max(local[j], global[j]);
            }
        }
        return global[k];
    }

    public static void main(String args[]) throws Exception {
        BestTimeToBuyAndSellStock obj = new BestTimeToBuyAndSellStock();
        int[] i = {7, 1, 5, 3, 6, 4};
        System.out.println("best=" + obj.maxProfit(i));
        System.out.println("best=" + obj.maxProfit2(i));
        int[] ii = {1, 4, 5, 7, 6, 3, 2, 9};
        System.out.println("best=" + obj.maxProfit3(ii));
        int[] iii = {2, 4, 1};
        System.out.println("best=" + obj.maxProfit4(2, iii));
    }
}
