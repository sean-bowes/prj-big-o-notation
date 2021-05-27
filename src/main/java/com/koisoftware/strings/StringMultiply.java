package com.koisoftware.strings;

public class StringMultiply {
    public String multiply(String num1, String num2) {
        int len1 = num1.length();
        int len2 = num2.length();
        if (len1 == 0 || len2 == 0) {
            return num1 + num2;
        }

        if (num1.equals("0") || num2.equals("0")) {
            return "0";
        }

        String res = "0";
        for (int i = len1 - 1; i >= 0; i--) {
            int n = num1.charAt(i) - '0';
            if (n == 0) continue;
            String oneMultiple = multipleOneDigit(num2, n, len1 - i - 1);
            res = addString(oneMultiple, res);
        }

        return res;
    }

    private String multipleOneDigit(String num, int n, int tailingZeros) {
        char[] charArray = num.toCharArray();
        int len = charArray.length;
        int carry = 0;
        String res = "";
        for (int i = len - 1; i >= 0; i--) {
            int c = charArray[i] - '0';
            int curr = c * n + carry;
            res = Integer.toString(curr % 10) + res;
            carry = curr / 10;
        }
        res = carry > 0 ? Integer.toString(carry) + res : res;
        while (tailingZeros > 0) {
            res += "0";
            tailingZeros--;
        }
        return res;
    }

    private String addString(String n1, String n2) {
        int len1 = n1.length();
        int len2 = n2.length();
        if (len1 == 0 || len2 == 0) {
            return n1 + n2;
        }

        int carry = 0;
        String res = "";
        while (len1 > 0 && len2 > 0) {
            int d1 = n1.charAt(--len1) - '0';
            int d2 = n2.charAt(--len2) - '0';
            int sum = d1 + d2 + carry;
            res = Integer.toString(sum % 10) + res;
            carry = sum / 10;
        }

        while (len1 > 0) {
            int d1 = n1.charAt(--len1) - '0';
            int sum = d1 + carry;
            res = Integer.toString(sum % 10) + res;
            carry = sum / 10;
        }

        while (len2 > 0) {
            int d2 = n2.charAt(--len2) - '0';
            int sum = d2 + carry;
            res = Integer.toString(sum % 10) + res;
            carry = sum / 10;
        }
        if (carry > 0) {
            res = Integer.toString(carry) + res;
        }
        return res;
    }
}
