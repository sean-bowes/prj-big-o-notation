package com.koisoftware.sorting;

import java.util.Arrays;

public class AlphaNumericPositionSort {
    static String sort(String s) {
        char[] c = new char[s.length() + 1];
        c = s.toCharArray();
        Arrays.sort(c);
        int al_c = 0; //starting index of the alphabet part
        int nu_c = 0; //starting index of numeric part
        while (c[al_c] < 97) { //get the index from where the alphabets start
            al_c++;
        }
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) < 97) {
                //if the position was occupied by an alphabet then replace it with alphabet
                s = s.substring(0, i) + c[nu_c++] + s.substring(i + 1);
            } else {
                //else replace it with a number
                s = s.substring(0, i) + c[al_c++] + s.substring(i + 1);
            }
        }
        return s;
    }

    public static void main(String[] args) {
        String s = "d974c3b2a10";
        System.out.println(s);
        System.out.println(sort(s));
    }
}
