package com.koisoftware.misc;

import java.util.Random;

public class RandomPick {
    public static void main(String[] args) {
        RandomPick obj = new RandomPick(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10});
        int nm = obj.pickIndex();
        System.out.println("Number=" + nm);
    }

    private double[] lottery;
    private final Random random;

    public RandomPick(int[] w) {
        this.lottery = new double[w.length + 1];
        double total = 0;
        for (int weight : w) total += weight;
        for (int i = 1; i < lottery.length; i++) {
            lottery[i] += (lottery[i - 1] + w[i - 1] / total);
        }
        this.random = new Random();
    }

    public int pickIndex() {
        int lo = 1;
        int hi = lottery.length - 1;
        double lucky = random.nextDouble();
        while (lo <= hi) {
            int mid = (lo + hi) / 2;
            if (lottery[mid] >= lucky && lottery[mid - 1] < lucky) {
                return mid - 1;
            } else if (lottery[mid] > lucky) {
                hi = mid - 1;
            } else {
                lo = mid + 1;
            }
        }
        return lo - 1; // unreachable;
    }
}
