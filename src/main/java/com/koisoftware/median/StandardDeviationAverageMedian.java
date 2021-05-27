package com.koisoftware.median;

import java.util.Arrays;

public class StandardDeviationAverageMedian {
    private int n = 0;
    private double sum = 0;
    private double sum2 = 0;

    public double sd(double x) {
        n++;
        sum += x;
        sum2 += x * x;
        return Math.sqrt(sum2 / n - sum * sum / n / n);
    }

    public double average(double[] x) {
        int nm = 0;
        for (int i = 0; i < x.length; i++) {
            nm += x[i];
        }
        return nm / x.length;
    }

    public double findMedian(double a[]) {
        Arrays.sort(a);
        if (a.length % 2 != 0) { //even numbers
            return (double) a[a.length / 2];
        }
        return Double.sum(a[(a.length - 1) / 2], a[a.length / 2]) / 2.0;
    }

    public static void main(String[] args) {
        double[] testData = {2, 4, 4, 4, 5, 5, 7, 9};
        StandardDeviationAverageMedian sd = new StandardDeviationAverageMedian();
        System.out.println("StandardDeviation");
        for (double x : testData) {
            System.out.println(sd.sd(x)); // StandardDeviation = 2.0
        }
        System.out.println("Average");
        System.out.println(sd.average(testData)); //Average = 5.0
        double a[] = {1, 3, 4, 2, 7, 5, 8, 6};
        System.out.println("Median");
        System.out.println(sd.findMedian(a)); //Median = 4.5

        double q[] = {7, 1, 9, 13, 10};
        for (double x : q) {
            System.out.println(sd.sd(x));
        }
        System.out.println("average=" + sd.average(q));
        System.out.println("median" + sd.findMedian(q));
    }
}
