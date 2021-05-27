package com.koisoftware.refrigerator;

import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

public class Refrigerator {
    enum Food {
        MILK,
        BANANA,
        EGG
    }

    private Map<Food, Integer> limit;
    private Integer milkCount;
    private Integer bananaCount;
    private Integer eggCount;
    private ReentrantLock milkLock;
    private ReentrantLock bananaLock;
    private ReentrantLock eggLock;

    public Refrigerator(Map<Food, Integer> limit) {
        this.limit = limit;
        milkCount = 0;
        bananaCount = 0;
        eggCount = 0;
        milkLock = new ReentrantLock();
        bananaLock = new ReentrantLock();
        eggLock = new ReentrantLock();
    }

    public boolean storeMilk() throws InterruptedException {
        milkLock.lock();
        try {
            if (milkCount >= limit.get(Food.MILK)) {
                return false;
            }
            milkCount++;
            print();
            return true;
        } finally {
            milkLock.unlock();
        }
    }

    public boolean storeBanana() throws InterruptedException {
        bananaLock.lock();
        try {
            if (bananaCount >= limit.get(Food.BANANA)) {
                return false;
            }
            bananaCount++;
            print();
            return true;
        } finally {
            bananaLock.unlock();
        }
    }

    public boolean storeEgg() throws InterruptedException {
        if (eggCount >= limit.get(Food.EGG)) return false;
        eggCount++;
        print();
        return true;
    }

    public boolean takeMilk() {
        milkLock.lock();
        try {
            if (milkCount <= 0) {
                return false;
            }
            milkCount--;
            print();
            return true;
        } finally {
            milkLock.unlock();
        }
    }

    public boolean takeBanana() {
        bananaLock.lock();
        try {
            if (bananaCount <= 0) {
                return false;
            }
            bananaCount--;
            print();
            return true;
        } finally {
            bananaLock.unlock();
        }
    }

    public boolean takeEgg() {
        if (eggCount <= 0) return false;
        eggCount--;
        print();
        return true;
    }

    public void print() {
        validation();
    }

    private boolean validation() {
        synchronized (this) {
            if (eggCount < 0 || eggCount > limit.get(Food.EGG) ||
                    milkCount < 0 || milkCount > limit.get(Food.MILK) ||
                    bananaCount < 0 || bananaCount > limit.get(Food.BANANA)) {

                System.out.print(
                        String.format("error message: egg %d milk %d banana %d \n",
                                eggCount, milkCount, bananaCount));
                return false;
            }
            return true;
        }
    }
}
