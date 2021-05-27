package com.koisoftware.misc;

import java.util.Random;

public class Rand7WithEqualProbability {

    // generate a pseudo-random integer in range `[min, max]`
    public static int rand(int min, int max) {
        if (min > max || (max - min + 1 > Integer.MAX_VALUE)) {
            throw new IllegalArgumentException("Invalid range");
        }
        return new Random().nextInt(max - min + 1) + min;
    }

    // generate a random number from 1 to 7 with equal probability by using the specified function
    public static int generate() {
        int r;
        do {
            r = 5 * (rand(1, 5) - 1) + rand(1, 5);
        } while (r > 7);
        return r;
    }

    public static void main(String args[]) {
        Rand7WithEqualProbability obj = new Rand7WithEqualProbability();
        int[] count = new int[8];
        for (int i = 1; i <= 1000000; i++) {
            int val = obj.generate();
            count[val]++;
        }
        for (int i = 1; i <= 7; i++) {
            System.out.println(i + " ~ " + (count[i] / 10000.0) + "%");
        }
    }

}
