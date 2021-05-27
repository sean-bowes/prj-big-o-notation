package com.koisoftware.search;

import java.util.Iterator;
import java.util.LinkedList;

public class DepthFirstSearch {
    private int nmOfVertices;
    private LinkedList<Integer> adjacencyListRepresentation[];

    @SuppressWarnings("unchecked")
    public DepthFirstSearch(int v) {
        nmOfVertices = v;
        adjacencyListRepresentation = new LinkedList[v];
        for (int i = 0; i < v; ++i) {
            adjacencyListRepresentation[i] = new LinkedList();
        }
    }

    public void addEdge(int v, int w) {
        adjacencyListRepresentation[v].add(w);
    }

    public void depthFirstSearchUtil(int v, boolean visited[]) {
        visited[v] = true;
        System.out.print(v + " ");
        Iterator<Integer> i = adjacencyListRepresentation[v].listIterator();
        while (i.hasNext()) {
            int n = i.next();
            if (!visited[n]) {
                depthFirstSearchUtil(n, visited);
            }
        }
    }

    public void depthFirstSearchByVertex(int v) {
        boolean visited[] = new boolean[nmOfVertices];
        depthFirstSearchUtil(v, visited);
    }

    public static void main(String args[]) {
        DepthFirstSearch g = new DepthFirstSearch(4);

        g.addEdge(0, 1);
        g.addEdge(0, 2);
        g.addEdge(1, 2);
        g.addEdge(2, 0);
        g.addEdge(2, 3);
        g.addEdge(3, 3);

        System.out.println("Following is Depth First Search (starting from vertex 2)");
        g.depthFirstSearchByVertex(2);
    }
}
