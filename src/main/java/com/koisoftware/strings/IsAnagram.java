package com.koisoftware.strings;

public class IsAnagram {
    public boolean isAnagram(String s, String t) {
        if (s == null || t == null) {
            return false;
        }
        if (s.length() != t.length()) {
            return false;
        }
        int[] arr = new int[26];
        for (int i = 0; i < s.length(); i++) {
            arr[s.charAt(i) - 'a']++;
            arr[t.charAt(i) - 'a']--;
        }
        for (int i : arr) {
            if (i != 0) {
                return false;
            }
        }
        return true;
    }

    public static void main(String args[]) {
        IsAnagram obj = new IsAnagram();
        String s = "anagram";
        String t = "nagaram";
        System.out.println(obj.isAnagram(s, t));
        s = "rat";
        t = "car";
        System.out.println(obj.isAnagram(s, t));
    }
}
