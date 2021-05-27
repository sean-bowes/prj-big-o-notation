package com.koisoftware.general;

import com.koisoftware.common.ArrayUtils;

import java.util.Random;

/**
 * Shuffle the contents of an array.
 */
public class Shuffle {
    public static void shuffle(int[] array) {
        if (array == null) {
            return;
        }

        Random rand = new Random();
        for (int index = array.length - 1; index >= 1; index--) {
            ArrayUtils.swap(array, index, rand.nextInt(index + 1));
        }
    }
}
