package com.koisoftware.refrigerator;

import java.util.HashMap;
import java.util.Map;

public class RefrigeratorImpl {
    public static void main(String[] args) throws InterruptedException {
        Map<Refrigerator.Food, Integer> limit = new HashMap<>();
        limit.put(Refrigerator.Food.MILK, 4);
        limit.put(Refrigerator.Food.BANANA, 5);
        limit.put(Refrigerator.Food.EGG, 9);
        Refrigerator refrigerator = new Refrigerator(limit);

        long pSleepTime = 1;
        long cSleepTime = 1;

        Thread goodMateEgg = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 20; i++) {
                    try {
                        refrigerator.storeEgg();
                        Thread.sleep(pSleepTime >= 0 ? pSleepTime : 400 + (int) (Math.random() * 200));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        Thread goodMateMilk = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 20; i++) {
                    try {
                        refrigerator.storeMilk();
                        Thread.sleep(pSleepTime >= 0 ? pSleepTime : 400 + (int) (Math.random() * 200));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        Thread goodMateBanana = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 20; i++) {
                    try {
                        refrigerator.storeBanana();
                        Thread.sleep(pSleepTime >= 0 ? pSleepTime : 400 + (int) (Math.random() * 200));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        Thread badMateOne = new Thread(new Runnable() {

            @Override
            public void run() {
                for (int i = 0; i < 30; i++) {
                    try {
                        if (refrigerator.takeEgg()) {
                        } else if (refrigerator.takeBanana()) {
                        } else if (refrigerator.takeMilk()) {
                        }
                        Thread.sleep(cSleepTime >= 0 ? cSleepTime : 400 + (int) (Math.random() * 200));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        Thread badMateTwo = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 30; i++) {
                    try {
                        if (refrigerator.takeEgg()) {
                        } else if (refrigerator.takeBanana()) {
                        } else if (refrigerator.takeMilk()) {
                        }
                        Thread.sleep(cSleepTime >= 0 ? cSleepTime : 400 + (int) (Math.random() * 200));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        Thread badMateThree = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 30; i++) {
                    try {
                        if (refrigerator.takeEgg()) {
                        } else if (refrigerator.takeBanana()) {
                        } else if (refrigerator.takeMilk()) {
                        }
                        Thread.sleep(cSleepTime >= 0 ? cSleepTime : 400 + (int) (Math.random() * 200));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        goodMateEgg.start();
        goodMateMilk.start();
        goodMateBanana.start();

        badMateOne.start();
        badMateTwo.start();
        badMateThree.start();
    }
}
