package com.koisoftware.search;

public class Search2DMatrix {
    public boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length - 1;
        int n = matrix[0].length - 1;
        int i = m;
        int j = 0;
        while (i >= 0 && j <= n) {
            if (target < matrix[i][j]) {
                i--;
            } else if (target > matrix[i][j]) {
                j++;
            } else {
                return true;
            }
        }
        return false;
    }

    public static void main(String args[]) {
        Search2DMatrix obj = new Search2DMatrix();
        int[][] matrix = {{1, 4, 7, 11, 15}, {2, 5, 8, 12, 19}, {3, 6, 9, 16, 22}, {10, 13, 14, 17, 24}, {18, 21, 23, 26, 30}};
        System.out.println("searchMatrix(5)=" + obj.searchMatrix(matrix, 5));
        System.out.println("searchMatrix(-1)=" + obj.searchMatrix(matrix, -1));
    }
}
