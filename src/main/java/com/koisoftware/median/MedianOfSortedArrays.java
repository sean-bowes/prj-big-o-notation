package com.koisoftware.median;

public class MedianOfSortedArrays {

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        // find a value such that left1 + left2 = right1 + right2
        int len1 = nums1.length;
        int len2 = nums2.length;
        if (len1 > len2) return findMedianSortedArrays(nums2, nums1);
        // **BUG: end = len
        int start = 0, end = len1;
        // **BUG: start <= end
        while (start <= end) {
            // **BUG: overflow
            int mid = start + (end - start) / 2;
            // **BUG: even and odd
            int mid2 = (len1 + len2 + 1) / 2 - mid;
            // **BUG: Index bound
            int left1 = mid == 0 ? Integer.MIN_VALUE : nums1[mid - 1];
            int right1 = mid == len1 ? Integer.MAX_VALUE : nums1[mid];
            // **BUG: Index bound
            int left2 = mid2 == 0 ? Integer.MIN_VALUE : nums2[mid2 - 1];
            int right2 = mid2 == len2 ? Integer.MAX_VALUE : nums2[mid2];

            if (right1 >= left2 && right2 >= left1) {
                // **BUG: even and odd
                if ((len1 + len2) % 2 == 0) {
                    return (double) (Math.min(right1, right2) +
                            Math.max(left1, left2)) / 2;
                } else {
                    return (double) Math.max(left1, left2);
                }
            } else if (right1 < left2) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        return -1;
    }

    public static void main(String args[]) {
        MedianOfSortedArrays obj = new MedianOfSortedArrays();
        int[] emptyArray = new int[0];
        int[] arrayLenOne = new int[]{1};
        int[] arrayLenTwo = new int[]{1, 2};
        int[] arrayLenTwoLarge = new int[]{8, 9};
        int[] arrayLenTwoDup = new int[]{2, 2};
        int[] arrayLenThree = new int[]{1, 2, 3};
        int[] arrayLenThreeLarge = new int[]{7, 8, 9};
        int[] arrayLenThreeDup = new int[]{1, 3, 3};
        int[] arrayLenMulti = new int[]{1, 2, 3, 4, 5, 6, 7};
        int[] arrayLenMultiLarge = new int[]{10, 11, 12, 13, 14, 15};
        int[] arrayLenMultiDup = new int[]{1, 2, 2, 4, 5, 5, 5};
        System.out.println("emptyArray,emptyArray=" + obj.findMedianSortedArrays(emptyArray, emptyArray));
        System.out.println("emptyArray,arrayLenOne=" + obj.findMedianSortedArrays(emptyArray, arrayLenOne));
        System.out.println("arrayLenOne,emptyArray=" + obj.findMedianSortedArrays(arrayLenOne, emptyArray));
        System.out.println("emptyArray,arrayLenTwo=" + obj.findMedianSortedArrays(arrayLenOne, arrayLenTwo));
        System.out.println("emptyArray,arrayLenTwoLarge=" + obj.findMedianSortedArrays(arrayLenTwo, arrayLenTwoLarge));
        System.out.println("emptyArray,arrayLenTwoDup=" + obj.findMedianSortedArrays(arrayLenTwo, arrayLenTwoDup));
        System.out.println("emptyArray,arrayLenThree=" + obj.findMedianSortedArrays(arrayLenTwo, arrayLenThree));
        System.out.println("emptyArray,arrayLenThreeLarge=" + obj.findMedianSortedArrays(arrayLenTwo, arrayLenThreeLarge));
        System.out.println("emptyArray,arrayLenThreeDup=" + obj.findMedianSortedArrays(arrayLenTwo, arrayLenThreeDup));
        System.out.println("arrayLenMulti,arrayLenThreeDup=" + obj.findMedianSortedArrays(arrayLenMulti, arrayLenThreeDup));
        System.out.println("arrayLenMultiLarge,arrayLenMulti=" + obj.findMedianSortedArrays(arrayLenMultiLarge, arrayLenMulti));
        System.out.println("arrayLenMultiLarge,arrayLenMultiDup=" + obj.findMedianSortedArrays(arrayLenMultiLarge, arrayLenMultiDup));
    }

}
