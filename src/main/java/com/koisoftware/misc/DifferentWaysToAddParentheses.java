package com.koisoftware.misc;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DifferentWaysToAddParentheses {
    public List<Integer> diffWaysToCompute(String input) {
        List<Integer> resultList = new ArrayList<Integer>();
        for (int i = input.length() - 1; i >= 0; i--) {
            if (input.charAt(i) != '+' && input.charAt(i) != '-' && input.charAt(i) != '*') {
                continue;
            } else {
                String left = input.substring(0, i);
                String right = input.substring(i + 1);
                List<Integer> leftElementList = diffWaysToCompute(left);
                List<Integer> rightElementList = diffWaysToCompute(right);
                for (int leftElement : leftElementList) {
                    for (int rightElement : rightElementList) {
                        int tmp = 0;
                        switch (input.charAt(i)) {
                            case '+':
                                tmp = leftElement + rightElement;
                                break;
                            case '-':
                                tmp = leftElement - rightElement;
                                break;
                            case '*':
                                tmp = leftElement * rightElement;
                                break;
                        }
                        resultList.add(tmp);
                    }
                }
            }
        }
        if (resultList.size() == 0) {
            resultList.add(Integer.valueOf(input));
        }
        Collections.sort(resultList);
        return resultList;
    }

    public static void main(String args[]) {
        DifferentWaysToAddParentheses obj = new DifferentWaysToAddParentheses();
        System.out.println("Solution1=" + obj.diffWaysToCompute("2-1-1"));
        System.out.println("Solution1=" + obj.diffWaysToCompute("2*3-4*5"));
    }
}
