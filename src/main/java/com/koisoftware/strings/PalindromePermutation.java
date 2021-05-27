package com.koisoftware.strings;

import java.util.HashSet;

public class PalindromePermutation {
    public boolean canPermutePalindrome(String s) {
        HashSet<Character> app = new HashSet<Character>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (app.contains(c)) {
                app.remove(c);
            } else {
                app.add(c);
            }
        }
        return app.size() <= 1;
    }

    public static void main(String[] args) {
        PalindromePermutation obj = new PalindromePermutation();
        String s = "code";
        System.out.println(s);
        System.out.println(obj.canPermutePalindrome(s));
        s = "aab";
        System.out.println(s);
        System.out.println(obj.canPermutePalindrome(s));
        s = "carerac";
        System.out.println(s);
        System.out.println(obj.canPermutePalindrome(s));
    }
}
