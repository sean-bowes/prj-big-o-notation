package com.koisoftware.misc;

import java.util.ArrayList;
import java.util.List;

public class WeightedProbability<T extends Object> {
    private List<Entry> entries = new ArrayList<>();
    private double accumulatedWeight = 0;

    public class Entry {
        public double weight;
        public T object;
    }

    public void addEntry(T object, double weight) {
        accumulatedWeight += weight;
        Entry e = new Entry();
        e.object = object;
        e.weight = weight;
        entries.add(e);
    }

    public static void main(String args[]) {
        WeightedProbability<String> obj = new WeightedProbability<>();
        obj.addEntry("10 Gold", 5.0);
        obj.addEntry("Sword", 20.0);
        obj.addEntry("Shield", 45.0);
        obj.addEntry("Armor", 20.0);
        obj.addEntry("Potion", 10.0);
        //probability = weight/total weights
        for (WeightedProbability.Entry e : obj.entries) {
            System.out.println("weight=" + e.weight + ",accumulatedWeight=" + obj.accumulatedWeight + ",probability=" + e.weight / obj.accumulatedWeight);
        }
    }
}
