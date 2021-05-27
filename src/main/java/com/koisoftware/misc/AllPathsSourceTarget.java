package com.koisoftware.misc;

import java.util.LinkedList;
import java.util.List;

public class AllPathsSourceTarget {
    public static void main(String args[]) {
        int[][] nm1 = {{1, 2}, {3}, {3}, {}};
        List<List<Integer>> lst = allPathsSourceTarget(nm1);
        //two paths: 0 -> 1 -> 3 and 0 -> 2 -> 3
        System.out.println(lst);
        int[][] nm2 = {{4, 3, 1}, {3, 2, 4}, {3}, {4}, {}};
        lst = allPathsSourceTarget(nm2);
        System.out.println(lst);
    }

    private static List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        List<List<Integer>> rst = new LinkedList<>();
        List<Integer> path = new LinkedList<>();
        path.add(0);
        dfs(graph, 0, path, rst);
        return rst;
    }

    private static void dfs(int[][] graph, int index, List<Integer> path, List<List<Integer>> rst) {
        if (index == graph.length - 1) {
            rst.add(new LinkedList<>(path));
            return;
        }
        for (int neigh : graph[index]) {
            path.add(neigh);
            dfs(graph, neigh, path, rst);
            // Backtracking
            path.remove(path.size() - 1);
        }
    }
}
