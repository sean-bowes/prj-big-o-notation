package com.koisoftware.stocks;

import java.util.ArrayList;
import java.util.List;

public class NumberOfWaysToPurchaseOil {
    static Integer count = Integer.valueOf(0); // Global

    public static void main(String args[]) throws Exception {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT */
        int[] given = {10, 15, 50};
        int target = 60;
        List<Integer> quantities = new ArrayList<>();

        for (int i = 0; i < given.length; i++) {
            int multiple = 1;
            while (given[i] * multiple < target) {
                if (!quantities.contains(given[i] * multiple))
                    quantities.add(given[i] * multiple);
                multiple++;
            }
        }
        //System.out.println(quantities);
        List<Integer> temp = new ArrayList<>();
        subset(quantities, temp, 0, target);
        System.out.println(count);

        System.out.println(solve(given, target));
    }

    public static void subset(List<Integer> quantities, List<Integer> temp, int index, int target) {
        int sum = 0;
        for (Integer i : temp) {
            sum += i;
        }
        if (sum == target) {
            count++;
        }
        for (int i = index; i < quantities.size(); i++) {
            temp.add(quantities.get(i));
            subset(quantities, temp, i + 1, target);
            temp.remove(Integer.valueOf(quantities.get(i)));
        }
    }

    private static int solve(int[] nums, int target) {
        int[][] dp = new int[nums.length][target + 1];
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j <= target; j++) {
                if (i == 0) {
                    if (j % nums[i] == 0)
                        dp[i][j] = 1;
                } else {
                    if (j < nums[i]) {
                        dp[i][j] = dp[i - 1][j];
                    } else {
                        dp[i][j] = dp[i - 1][j] + dp[i][j - nums[i]];
                    }
                }
            }
        }
        return dp[nums.length - 1][target];
    }
}