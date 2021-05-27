package com.koisoftware.twinsigma;

public class CircleOfFriends {

    public int findCircleNum(int[][] isConnected) {
        //can be solved by dfs, bfs, union
        //time complexity: O(n*n) - n is the number of students, traverse n*n matrix
        //space complexity: O(n) - visited array of size n
        if (isConnected == null || isConnected.length == 0 || isConnected[0].length == 0) {
            return 0;
        }
        //could use a boolean array (false-true) but we chose an int array 0-1 (int=8bytes / boolean = 1byte) (if graph was larger-boolean array)
        final int[] visitedArray = new int[isConnected.length];
//        final boolean[] visitedArray = new boolean[isConnected.length];
        int numCircles = 0;
        for (int i = 0; i < isConnected.length; i++) {
            if (visitedArray[i] == 0) {
//                if (!visitedArray[i]) {
                depthFirstSearch(isConnected, visitedArray, i);
                numCircles++;
            }
        }
        return numCircles;
    }

    private final void depthFirstSearch(int[][] isConnected, boolean[] visitedArray, int cur) {
        for (int i = 0; i < isConnected.length; i++) {
            if (!visitedArray[i] && isConnected[cur][i] == 1) {
                visitedArray[i] = true;
                depthFirstSearch(isConnected, visitedArray, i);
            }
        }
    }

    private final void depthFirstSearch(int[][] isConnected, int[] visitedArray, int cur) {
        for (int i = 0; i < isConnected.length; i++) {
            if (visitedArray[i] == 0 && isConnected[cur][i] == 1) {
                visitedArray[i] = 1;
                depthFirstSearch(isConnected, visitedArray, i);
            }
        }
    }

    public static void main(String args[]) {
        CircleOfFriends obj = new CircleOfFriends();
        final int[][] sarr1 = {{1, 1, 0}, {1, 1, 0}, {0, 0, 1}};
        long start, end;
        start = System.currentTimeMillis();
        System.out.println("sarr1 (2) size=" + sarr1.length + ",findCircleNum=" + obj.findCircleNum(sarr1));
        end = System.currentTimeMillis();
        System.out.println("sarr4 time=" + (end - start));
    }
}
