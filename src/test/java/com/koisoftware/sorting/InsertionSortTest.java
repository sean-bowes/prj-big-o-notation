package com.koisoftware.sorting;

import org.junit.jupiter.api.Test;

import java.util.Comparator;

import static org.junit.Assert.assertArrayEquals;

public class InsertionSortTest {

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
        Integer[] array = {5, 4, 3, 2, 1};
        Integer[] sorted = new InsertionSort().insertionSort(array,
                ascOrderComparator);
        assertArrayEquals("Failure => Array returned is not sorted.", sorted,
                new Integer[]{1, 2, 3, 4, 5});
    }

    @Test
    public void descendingOrderSort() {
        Integer[] array = {1, 2, 3, 4, 5};
        Integer[] sorted = new InsertionSort().insertionSort(array,
                descOrderComparator);
        assertArrayEquals("Failure => Array returned is not sorted.", sorted,
                new Integer[]{5, 4, 3, 2, 1});
    }

    @Test
    public void randomOrderSort() {
        Integer[] array = {5, 1, 3, 2, 4};
        Integer[] sorted = new InsertionSort().insertionSort(array,
                ascOrderComparator);
        assertArrayEquals("Failure => Array returned is not sorted.", sorted,
                new Integer[]{1, 2, 3, 4, 5});
    }

}
