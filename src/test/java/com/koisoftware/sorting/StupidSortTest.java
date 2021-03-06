package com.koisoftware.sorting;

import org.junit.jupiter.api.Test;

import java.util.Comparator;

import static org.junit.Assert.assertArrayEquals;

public class StupidSortTest {

    private final Comparator<Integer> ascOrderComparator = new Comparator<Integer>() {
        @Override
        public int compare(Integer a, Integer b) {
            return a - b;
        }
    };

    private final Comparator<Integer> descOrderComparator = new Comparator<Integer>() {
        @Override
        public int compare(Integer a, Integer b) {
            return b - a;
        }
    };

    @Test
    public void ascendingOrderSort() {
        int[] array = {5, 4, 3, 2, 1};
        int[] sorted = StupidSort.stupidSort(array, ascOrderComparator);
        assertArrayEquals("Failure => Array returned is not sorted.", sorted,
                new int[]{1, 2, 3, 4, 5});
    }

    @Test
    public void descendingOrderSort() {
        int[] array = {1, 2, 3, 4, 5};
        int[] sorted = StupidSort.stupidSort(array, descOrderComparator);
        assertArrayEquals("Failure => Array returned is not sorted.", sorted,
                new int[]{5, 4, 3, 2, 1});
    }

    @Test
    public void randomOrderSort() {
        int[] array = {5, 1, 3, 2, 4};
        int[] sorted = StupidSort.stupidSort(array, ascOrderComparator);
        assertArrayEquals("Failure => Array returned is not sorted.", sorted,
                new int[]{1, 2, 3, 4, 5});
    }

}
