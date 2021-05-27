package com.koisoftware.strings;

import java.util.Stack;

public class Palindrome {

    public static String longestPalindrome(String s) {
        int n = s.length();
        String res = null;
        boolean[][] dp = new boolean[n][n];
        for (int i = n - 1; i >= 0; i--) {
            for (int j = i; j < n; j++) {
                dp[i][j] = s.charAt(i) == s.charAt(j) && (j - i < 3 || dp[i + 1][j - 1]);
                if (dp[i][j] && (res == null || j - i + 1 > res.length())) {
                    res = s.substring(i, j + 1);
                }
            }
        }
        return res;
    }

    private static String charsNotInPalindrome(String s) {
        String str = longestPalindrome(s);
        str = s.replaceAll(str, "");
        return str;
    }

    private static boolean isPalindromeByStack(String original) {
        char[] data = original.toCharArray();
        Stack<Character> stack = new Stack<>();
        for (char c : data) {
            stack.push(c);
        }
        char[] data2 = new char[data.length];
        int len = stack.size();
        for (int i = 0; i < len; i++) {
            data2[i] = stack.pop();
        }
        String reversed = new String(data2);
        return original.equals(reversed);
    }

    private static boolean isPalindromeByStringBuilder(String original) {
        String reversed = new StringBuilder(original).reverse().toString();
        return original.equals(reversed);
    }

    private static boolean isPalindromeByChar(String original) {
        String reversed = "";
        int len = original.length();
        for (int i = len - 1; i >= 0; i--) {
            reversed = reversed + original.charAt(i);
        }
        return original.equals(reversed);
    }

    private static boolean isPalindromeByCharArray(String original) {
        char[] data = original.toCharArray();
        int i = 0;
        int j = data.length - 1;
        while (j > i) {
            if (data[i] != data[j]) {
                return false;
            }
            ++i;
            --j;
        }
        return true;
    }

    private static boolean almostPalindrome(String original) {
        if (original == null) {
            return false;
        }
        if (original.length() <= 3) {
            return false;
        }
        if (isPalindromeByCharArray(original)) {
            return false;
        }
        String data = original.substring(1, original.length());
        if (isPalindromeByCharArray(data)) {
            return true;
        }
        data = original.substring(0, original.length() - 1);
        if (isPalindromeByCharArray(data)) {
            return true;
        }
        data = original.substring(1, original.length() - 1);
        if (isPalindromeByCharArray(data)) {
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println("forest (1234325) (true) ByCharArray=" + almostPalindrome("1234325")); //true
        System.out.println("forest (123381) (true) ByCharArray=" + almostPalindrome("123381")); //true
        System.out.println("forest (12341123) (false) ByCharArray=" + almostPalindrome("12341123")); //false
        System.out.println("forest (1234312) (false) ByCharArray=" + almostPalindrome("1234312")); //false
        System.out.println("forest (1234321) (false) ByCharArray=" + almostPalindrome("1234321")); //false

//        System.out.println("bwabbawd longestPalindrome=" + longestPalindrome("bwabbawd"));
//        System.out.println("bwabbawd charsNotInPalindrome=" + charsNotInPalindrome("bwabbawd"));
//
//        System.out.println("radar ByStack=" + isPalindromeByStack("radar"));
//        System.out.println("kayak ByStack=" + isPalindromeByStack("kayak"));
//        System.out.println("forest ByStack=" + isPalindromeByStack("forest"));
//
//        System.out.println("radar ByStringBuilder=" + isPalindromeByStringBuilder("radar"));
//        System.out.println("kayak ByStringBuilder=" + isPalindromeByStringBuilder("kayak"));
//        System.out.println("forest ByStringBuilder=" + isPalindromeByStringBuilder("forest"));
//
//        System.out.println("radar ByChar=" + isPalindromeByChar("radar"));
//        System.out.println("kayak ByChar=" + isPalindromeByChar("kayak"));
//        System.out.println("forest ByChar=" + isPalindromeByChar("forest"));
//        System.out.println("radar ByCharArray=" + isPalindromeByCharArray("radar"));
//        System.out.println("kayak ByCharArray=" + isPalindromeByCharArray("kayak"));
//        System.out.println("forest ByCharArray=" + isPalindromeByCharArray("forest"));
    }

}
