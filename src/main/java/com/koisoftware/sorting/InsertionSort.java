package com.koisoftware.sorting;

import java.util.Comparator;

public class InsertionSort {

    public <T> T[] insertionSort(T[] array, Comparator<T> comparator) {
        for (int outer = 1; outer < array.length; outer++) {
            T item = array[outer];
            int inner;
            for (inner = outer - 1; inner >= 0
                    && comparator.compare(array[inner], item) > 0; inner--) {
                array[inner + 1] = array[inner];
            }
            array[inner + 1] = item;
        }
        return array;
    }

    public Comparator<Integer> comparatorA = new Comparator<Integer>() {
        public int compare(Integer a, Integer b) {
            //ascending
            return a - b;
        }
    };

    public Comparator<Integer> comparatorB = new Comparator<Integer>() {
        public int compare(Integer a, Integer b) {
            //descending
            return b - a;
        }
    };

    private void printArray(Object[] array) {
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i].toString() + " ");
        }
        System.out.println("");
    }

    public static void main(String args[]) {
        InsertionSort obj = new InsertionSort();
        Integer[] arr = {12, 11, 13, 5, 6};
        obj.printArray(arr);
        Integer[] arrTemp2 = {12, 11, 13, 5, 6};
        Integer[] arr2 = obj.insertionSort(arrTemp2, obj.comparatorA);
        obj.printArray(arr2);
        Integer[] arrTemp3 = {12, 11, 13, 5, 6};
        Integer[] arr3 = obj.insertionSort(arrTemp3, obj.comparatorB);
        obj.printArray(arr3);
    }
}
