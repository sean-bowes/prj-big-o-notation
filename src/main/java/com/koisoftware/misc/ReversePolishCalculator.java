package com.koisoftware.misc;

import java.util.EmptyStackException;
import java.util.Stack;

public class ReversePolishCalculator {

    /**
     * Computes the outcome of a given expression in Reverse Polish Notation
     *
     * @param expr the expression to compute
     */
    public static void compute(String expr) throws ArithmeticException, EmptyStackException {
        Stack<Double> stack = new Stack<>();

        System.out.println(expr);
        System.out.println("Input\tOperation\tStack after");

        for (String token : expr.split("\\s+")) {
            System.out.print(token + "\t");
            switch (token) {
                case "+":
                    System.out.print("Operate\t\t");
                    stack.push(stack.pop() + stack.pop());
                    break;
                case "-":
                    System.out.print("Operate\t\t");
                    stack.push(-stack.pop() + stack.pop());
                    break;
                case "*":
                    System.out.print("Operate\t\t");
                    stack.push(stack.pop() * stack.pop());
                    break;
                case "/":
                    System.out.print("Operate\t\t");
                    double divisor = stack.pop();
                    stack.push(stack.pop() / divisor);
                    break;
                case "^":
                    System.out.print("Operate\t\t");
                    double exponent = stack.pop();
                    stack.push(Math.pow(stack.pop(), exponent));
                    break;
                default:
                    System.out.print("Push\t\t");
                    stack.push(Double.parseDouble(token));
                    break;
            }

            System.out.println(stack);
        }

        System.out.println("Final Answer: " + stack.pop());
    }

    /**
     * Runs the calculation for the RPN expression in args[0].
     */
    public static void main(String[] args) {
        try {
            compute("3 5 +");
        } catch (Exception err) {
            System.out.println(err.getMessage());
        }
    }
}

