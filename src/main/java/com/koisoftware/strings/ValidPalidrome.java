package com.koisoftware.strings;

public class ValidPalidrome {
    public boolean isPalindromeWithPointers(String s) {
        //time complexity : O(n)
        //space complexity : O(1)
        for (int i = 0, j = s.length() - 1; i < j; i++, j--) {
            while (i < j && !Character.isLetterOrDigit(s.charAt(i))) {
                i++;
            }
            while (i < j && !Character.isLetterOrDigit(s.charAt(j))) {
                j--;
            }

            if (Character.toLowerCase(s.charAt(i)) != Character.toLowerCase(s.charAt(j)))
                return false;
        }
        return true;
    }

    public boolean isPalindromeWithLoops(String s) {
        //time complexity : O(n^3)
        //space complexity : O(n) - n=string.length()
        if (s == null) {
            return false;
        }
        s = s.toLowerCase();
        int i = 0;
        int j = s.length() - 1;
        while (i < j) {
            while (i < j && !((s.charAt(i) >= 'a' && s.charAt(i) <= 'z')
                    || (s.charAt(i) >= '0' && s.charAt(i) <= '9'))) {
                i++;
            }
            while (i < j && !((s.charAt(j) >= 'a' && s.charAt(j) <= 'z')
                    || (s.charAt(j) >= '0' && s.charAt(j) <= '9'))) {
                j--;
            }
            if (s.charAt(i) != s.charAt(j)) {
                return false;
            }
            i++;
            j--;
        }
        return true;
    }

    public static void main(String[] args) {
        ValidPalidrome obj = new ValidPalidrome();
        System.out.println("bwabbawd isPalindrome=" + obj.isPalindromeWithLoops("bwabbawd"));
        System.out.println("wabbaw isPalindrome=" + obj.isPalindromeWithLoops("wabbaw"));
        System.out.println("bwabbawd isPalindrome=" + obj.isPalindromeWithPointers("bwabbawd"));
        System.out.println("wabbaw isPalindrome=" + obj.isPalindromeWithPointers("wabbaw"));
    }
}
