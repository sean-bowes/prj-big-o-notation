package com.koisoftware.misc;

import java.util.Stack;

public class TemperatureHistory {
    class CityData {
        private Stack<CityObject> s1 = new Stack<>();
        private Stack<CityObject> s2 = new Stack<>();

        private void enQueue(CityObject x) {
            while (!s1.isEmpty()) {
                s2.push(s1.pop());
            }
            s1.push(x);
            while (!s2.isEmpty()) {
                s1.push(s2.pop());
            }
        }

        private CityObject deQueue() {
            if (s1.isEmpty()) {
                System.out.println("Q is Empty");
                System.exit(0);
            }
            CityObject x = s1.peek();
            s1.pop();
            return x;
        }
    }

    class CityObject {
        String city;
        String date;
        String temperature;
        String electricity;
        String consumption;

        public CityObject(String city, String date, String temperature, String electricity, String consumption) {
            city = city;
            date = date;
            temperature = temperature;
            electricity = electricity;
            consumption = consumption;
        }
    }

    public static void main(String[] args) {
        TemperatureHistory obj = new TemperatureHistory();

    }

}
