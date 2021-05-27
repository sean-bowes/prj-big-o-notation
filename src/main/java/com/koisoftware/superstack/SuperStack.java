package com.koisoftware.superstack;

import java.util.EmptyStackException;
import java.util.Stack;

public class SuperStack {
    //Average: maintain a total sum, average = sum / stack size
    //Min: use a stack to store min
    //Max: use a stack to store max
    //mode: Like LFU cache, but keep the max instead of min.
    //Median: Two queues and a map to track the removed ones

    private long sum;
    private Stack<Integer> stack;
    private Stack<Integer> minStack;
    private Stack<Integer> maxStack;
    private Mode mode;

    public SuperStack() {
        sum = 0;
        stack = new Stack<>();
        minStack = new Stack<>();
        maxStack = new Stack<>();
        mode = new Mode();
    }

    public void push(int val) {
        stack.push(val);
        sum += val;
        if (minStack.empty() || minStack.peek() >= val) minStack.push(val);
        if (maxStack.empty() || maxStack.peek() <= val) maxStack.push(val);

        mode.put(val);
    }

    public int peek() throws EmptyStackException {
        return stack.peek();
    }

    public void pop() throws EmptyStackException {
        int val = stack.pop();
        sum -= val;

        if (val == minStack.peek()) minStack.pop();
        if (val == maxStack.peek()) maxStack.pop();

        mode.remove(val);
    }

    public int min() throws EmptyStackException {
        return minStack.peek();
    }

    public int max() throws EmptyStackException {
        return maxStack.peek();
    }

    public double average() throws EmptyStackException {
        if (stack.empty()) throw new EmptyStackException();
        return (double) sum / stack.size();
    }

    public int mostFrequentValue() throws Exception {
        return mode.getMostFrequentValue();
    }
}
