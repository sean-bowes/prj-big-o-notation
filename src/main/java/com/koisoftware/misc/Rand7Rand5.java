package com.koisoftware.misc;

public class Rand7Rand5 {
    public double generateRand5() {
        return Math.floor(Math.random() * 5);
    }

    public double generateRand7() {
        double randNum = Double.sum(generateRand5(), generateRand5());
        double nm = 0;
        if (randNum < 8) {
            nm = randNum;
        } else {
            nm = generateRand5();
        }
        return nm;
    }

    public int generateRand10() {
        int index;
        do {
            index = 7 * ((int) generateRand7() - 1) + (int) generateRand7();
        } while (index > 40);
        return index % 10 + 1;
    }

    public static void main(String args[]) {
        Rand7Rand5 obj = new Rand7Rand5();
        for (int i = 0; i < 10; i++) {
            System.out.println("generateRand5=" + obj.generateRand5());
            System.out.println("generateRand7=" + obj.generateRand7());
            System.out.println("generateRand10=" + obj.generateRand10());
        }
    }

}
