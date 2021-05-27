package com.koisoftware.sorting;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class SortingHashMap {
    public static void main(String args[]) {
        TreeMap<Integer, String> tm = new TreeMap<Integer, String>();
        tm.put(10, "Bill");
        tm.put(1, "Bob");
        tm.put(11, "Frank");
        tm.put(9, "Joe");
        tm.put(3, "Sally");
        // Get a set of the entries
        Set set = tm.entrySet();
        // Get an iterator
        Iterator i = set.iterator();
        // Display elements
        while (i.hasNext()) {
            Map.Entry me = (Map.Entry) i.next();
            System.out.print(me.getKey() + ": ");
            System.out.println(me.getValue());
        }
    }
}
