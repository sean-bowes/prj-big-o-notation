package com.koisoftware.search;

import java.util.Iterator;
import java.util.LinkedList;

public class BreadthFirstSearch {
    private int nmOfVertices;
    private LinkedList<Integer> adjacencyListRepresentation[];

    public BreadthFirstSearch(int v) {
        nmOfVertices = v;
        adjacencyListRepresentation = new LinkedList[v];
        for (int i = 0; i < v; ++i) {
            adjacencyListRepresentation[i] = new LinkedList();
        }
    }

    public void addEdge(int v, int w) {
        adjacencyListRepresentation[v].add(w);
    }

    public void breadthFirstSearchUtil(int s) {
        boolean visited[] = new boolean[nmOfVertices];
        LinkedList<Integer> queue = new LinkedList<Integer>();
        visited[s] = true;
        queue.add(s);
        while (queue.size() != 0) {
            s = queue.poll();
            System.out.print(s + " ");
            Iterator<Integer> i = adjacencyListRepresentation[s].listIterator();
            while (i.hasNext()) {
                int n = i.next();
                if (!visited[n]) {
                    visited[n] = true;
                    queue.add(n);
                }
            }
        }
    }

    // Driver method to
    public static void main(String args[]) {
        BreadthFirstSearch g = new BreadthFirstSearch(4);

        g.addEdge(0, 1);
        g.addEdge(0, 2);
        g.addEdge(1, 2);
        g.addEdge(2, 0);
        g.addEdge(2, 3);
        g.addEdge(3, 3);

        System.out.println("Following is Breadth First Traversal (starting from vertex 2)");

        g.breadthFirstSearchUtil(2);
    }
}
