package com.koisoftware.misc;

import java.util.ArrayList;

public class CourseSchedule {
    //https://leetcode.com/problems/course-schedule/solution/

    private ArrayList<ArrayList<Integer>> makeGraphByDFS(int numTasks, int[][] prerequisites) {
        ArrayList<ArrayList<Integer>> graph = new ArrayList<ArrayList<Integer>>(numTasks);
        for (int i = 0; i < numTasks; i++) {
            graph.add(new ArrayList<Integer>());
        }
        for (int i = 0; i < prerequisites.length; i++) {
            graph.get(prerequisites[i][1]).add(prerequisites[i][0]);
        }
        return graph;
    }

    private boolean dfsRoutine(ArrayList<ArrayList<Integer>> graph, int node, boolean withinPath[], boolean visited[]) {
        //DFS, it will first visit a node, then one neighbor of it, then one neighbor of this neighbor and so on
        //Time Complexity: O(E + V) where V is the number of courses, and E is the number of dependencies
        //Space Complexity: O(E + V)
        if (visited[node]) {
            return false;
        }
        withinPath[node] = true;
        visited[node] = true;
        for (int i : graph.get(node)) {
            if (withinPath[i] || dfsRoutine(graph, i, withinPath, visited)) {
                return true;
            }
        }
        return withinPath[node] = false;
    }

    public boolean canFinishByDFS(int numCourses, int[][] prerequisites) {
        ArrayList<ArrayList<Integer>> graph = makeGraphByDFS(numCourses, prerequisites);
        boolean onpath[] = new boolean[numCourses];
        boolean visited[] = new boolean[numCourses];
        for (int i = 0; i < numCourses; i++) {
            if (!visited[i] && dfsRoutine(graph, i, onpath, visited)) {
                return false;
            }
        }
        return true;
    }

    private ArrayList<ArrayList<Integer>> makeGraphByBFS(int numTasks, int[][] prerequisites) {
        ArrayList<ArrayList<Integer>> graph = new ArrayList<ArrayList<Integer>>(numTasks);
        for (int i = 0; i < numTasks; i++) {
            graph.add(new ArrayList<Integer>());
        }
        for (int i = 0; i < prerequisites.length; i++) {
            graph.get(prerequisites[i][1]).add(prerequisites[i][0]);
        }
        return graph;
    }

    private int[] bfsRoutine(ArrayList<ArrayList<Integer>> graph) {
        //BFS can be used to solve it using the idea of topological sort
        //Time Complexity: O(E + V) where V is the number of courses, and E is the number of dependencies
        //Space Complexity: O(E + V)
        int degrees[] = new int[graph.size()];
        for (ArrayList<Integer> neighbors : graph) {
            for (int i : neighbors) {
                degrees[i]++;
            }
        }
        return degrees;
    }

    public boolean canFinishByBFS(int numCourses, int[][] prerequisites) {
        ArrayList<ArrayList<Integer>> graph = makeGraphByBFS(numCourses, prerequisites);
        int degrees[] = bfsRoutine(graph);
        for (int i = 0; i < numCourses; i++) {
            int j = 0;
            for (; j < numCourses; j++) {
                if (degrees[j] == 0) {
                    break;
                }
            }
            if (j == numCourses) {
                return false;
            }
            degrees[j] = -1;
            for (int k : graph.get(j)) {
                degrees[k]--;
            }
        }
        return true;
    }

    public static void main(String args[]) {
        CourseSchedule obj = new CourseSchedule();
        int numTasks = 4;
        int[][] prerequisites = {{1, 0}, {2, 1}, {3, 2}};

        if (obj.canFinishByDFS(numTasks, prerequisites)) {
            System.out.println("DFS Possible to finish all tasks");
        } else {
            System.out.println("DFS Impossible to finish all tasks");
        }
        if (obj.canFinishByBFS(numTasks, prerequisites)) {
            System.out.println("BFS Possible to finish all tasks");
        } else {
            System.out.println("BFS Impossible to finish all tasks");
        }
    }
}
