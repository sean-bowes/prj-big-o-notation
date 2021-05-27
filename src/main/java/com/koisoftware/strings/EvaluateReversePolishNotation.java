package com.koisoftware.strings;

import java.util.Stack;

public class EvaluateReversePolishNotation {
    public int evalRPN(String[] tokens) {
        Stack<Integer> nums = new Stack<Integer>();
        for (int i = 0; i < tokens.length; i++) {
            String op = tokens[i];
            if (op.equals("+")) {
                nums.push(nums.pop() + nums.pop());
                continue;
            }
            if (op.equals("-")) {
                int num = nums.pop();
                nums.push(nums.pop() - num);
                continue;
            }
            if (op.equals("*")) {
                nums.push(nums.pop() * nums.pop());
                continue;
            }
            if (op.equals("/")) {
                int num = nums.pop();
                nums.push(nums.pop() / num);
                continue;
            }
            nums.push(Integer.parseInt(op));
        }
        return nums.pop();
    }

    public static void main(String args[]) throws Exception {
        EvaluateReversePolishNotation obj = new EvaluateReversePolishNotation();
        String[] s = {"2", "1", "+", "3", "*"};
        System.out.println(obj.evalRPN(s));
    }
}
