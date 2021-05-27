package com.koisoftware.stocks;

import java.util.*;

public class TopKStocksByPrice {

    SortedMap<Integer, TreeSet<String>> smap = new TreeMap<Integer, TreeSet<String>>(Collections.reverseOrder());

    public void addScore(String stockTicker, int price) {
        if (smap.containsKey(price)) {
            TreeSet<String> set = smap.get(price);
            set.add(stockTicker);
            smap.put(price, set);
        } else {
            TreeSet<String> set = new TreeSet<>();
            set.add(stockTicker);
            smap.put(price, set);
        }
    }

    public String[] top(int K) {
        String[] arr = new String[K];
        int nm = 0;
        HashSet<String> tmp = new HashSet<>();
        for (Map.Entry entry : smap.entrySet()) {
            Integer key = (Integer) entry.getKey();
            TreeSet<String> set = (TreeSet) entry.getValue();
            for (String s : set) {
                if (tmp.contains(s)) {
                    continue;
                } else {
                    tmp.add(s);
                    arr[nm] = "Ticker=" + s + ":Price=" + key;
                    if (nm == K) {
                        break;
                    }
                    nm++;
                }
            }
            if (nm == K) {
                break;
            }
        }
        return arr;
    }

    public void reset(String stockTicker) {
        for (Map.Entry entry : smap.entrySet()) {
            Integer key = (Integer) entry.getKey();
            TreeSet<String> set = (TreeSet) entry.getValue();
            if (set.contains(stockTicker)) {
                set.remove(stockTicker);
            }
        }
    }

    public static void main(String[] args) {
        TopKStocksByPrice leaderboard = new TopKStocksByPrice();
        String[] arr;

        leaderboard.addScore("BOA", 73);
        leaderboard.addScore("BOA", 56);
        leaderboard.addScore("CITI", 39);
        leaderboard.addScore("AOL", 51);
        leaderboard.addScore("APL", 4);
        arr = leaderboard.top(3);
        for (String s : arr) {
            System.out.println("stock leader=" + s);
        }
        System.out.println("##################");
        leaderboard.reset("CITI");
        leaderboard.reset("BOA");
        leaderboard.addScore("SPX", 51);
        arr = leaderboard.top(3);
        for (String s : arr) {
            System.out.println("stock leader=" + s);
        }
        System.out.println("##################");
    }
}
