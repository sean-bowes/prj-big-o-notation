package com.koisoftware.misc;

public class BrowserHistory {
    private String[] history;
    private int curr;
    private int max;

    public BrowserHistory(String homepage) {
        history = new String[5001];
        history[0] = homepage;
        curr = 0;
        max = 0;
    }

    public void visit(String url) {
        curr++;
        history[curr] = url;
        max = curr;
    }

    public String back(int steps) {
        if (curr - steps < 0) {
            curr = 0;
            return history[0];
        }
        curr = curr - steps;
        return history[curr];
    }

    public String forward(int steps) {
        if (curr + steps > max) {
            curr = max;
            return history[curr];
        }
        curr = curr + steps;
        return history[curr];
    }
}
