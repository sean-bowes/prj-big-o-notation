package com.koisoftware.stocks;

import java.util.*;

public class TopKStocksByVolume {
    class StockVolume {
        String stockName;
        int volume;

        StockVolume(String stockName, int volume) {
            this.stockName = stockName;
            this.volume = volume;
        }
    }

    HashMap<String, StockVolume> hm = new HashMap<>();

    PriorityQueue<StockVolume> pq = new PriorityQueue<>((a, b) -> {
        return b.volume - a.volume;
    });

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        TopKStocksByVolume topKStocksByVolume = new TopKStocksByVolume();
        topKStocksByVolume.addStocksVolume("INTC", 12);
        topKStocksByVolume.addStocksVolume("CSCO", 20);
        topKStocksByVolume.addStocksVolume("AA", 10);
        topKStocksByVolume.addStocksVolume("INTC", 18);
        topKStocksByVolume.addStocksVolume("UAL", 4);
        topKStocksByVolume.addStocksVolume("BOE", 2);
        topKStocksByVolume.addStocksVolume("BOA", 16);
        topKStocksByVolume.addStocksVolume("BOA", 24);

        List<String> ans = topKStocksByVolume.topKstocks(5);

        System.out.println("Top 5 stocks are as follows: ");
        for (String stock : ans) {
            System.out.println(stock);
        }

        // BOA, INTC, CSCO, AA, UAL, BOE
    }

    public void addStocksVolume(String stockSymbol, int volume) {
        if (hm.containsKey(stockSymbol)) {
            pq.remove(hm.get(stockSymbol));
            StockVolume temp = new StockVolume(stockSymbol, volume + hm.get(stockSymbol).volume);
            pq.add(temp);
            hm.replace(stockSymbol, temp);
        } else {
            StockVolume temp = new StockVolume(stockSymbol, volume);
            pq.add(temp);
            hm.put(stockSymbol, temp);
        }
    }

    public List<String> topKstocks(int K) {
        List<String> ans = new ArrayList<>();

        while (pq.size() > 1) {
            ans.add(pq.remove().stockName);
        }

        System.out.println("Hash Map to store volumes of stocks.");
        for (Map.Entry<String, StockVolume> entry : hm.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue().stockName + " : " + entry.getValue().volume);
        }

        return ans;
    }
}
