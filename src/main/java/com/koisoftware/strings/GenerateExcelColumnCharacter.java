package com.koisoftware.strings;

import static java.lang.Math.floor;
import static java.lang.Math.log;

public class GenerateExcelColumnCharacter {

    private static String getString(int n) {
        char[] buf = new char[(int) floor(log(25 * (n + 1)) / log(26))];
//        System.out.println(log(25 * (n + 1)) / log(26));
//        System.out.println(floor(log(25 * (n + 1)) / log(26)));
        for (int i = buf.length - 1; i >= 0; i--) {
            n--;
            buf[i] = (char) ('A' + n % 26);
            n /= 26;
//            System.out.println(buf[i]);
        }
        return new String(buf);
    }

    public static void main(String[] args) {
//        System.out.println(getString(5));
        System.out.println("Column =" + getString(5003));
    }

}
