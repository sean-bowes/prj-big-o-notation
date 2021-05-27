package com.koisoftware.misc;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class OrgStructure {

    public static void main(String[] args) {
        Organization org = new Organization();
        org.add(10, "Sharilyn Gruber", -1);
        org.add(7, "Denice Mattice", 10);
        org.add(3, "Lawana Futrell", -1);
        org.add(34, "Lissette Gorney", 7);
        org.add(5, "Lan Puls", 3);
        org.print();
        System.out.println("------------------");
        org.move(5, 10);
        org.print();
        System.out.println("-------------------");
        System.out.println(org.count(10));
    }

    static class Organization {
        Map<Integer, Employee> map = new HashMap<>();

        public void add(int id, String name, int managerId) {
            Employee emp = new Employee(id, name, managerId);
            map.put(id, emp);
        }

        public void remove(int id) {
            map.remove(id);
        }

        public void move(int id, int managerId) {
            map.get(id).managerId = managerId;
        }

        private int count(int managerId) {
            if (!map.containsKey(managerId))
                return -1;
            Set<Integer> visited = new HashSet<>();
            Map<Integer, Set<Employee>> g = new HashMap<>();
            for (Employee emp : map.values()) {
                g.putIfAbsent(emp.managerId, new HashSet<>());
                g.get(emp.managerId).add(emp);
            }
            return dfs(g, visited, managerId);
        }

        private int dfs(Map<Integer, Set<Employee>> g, Set<Integer> visited, int managerId) {
            int res = 0;
            for (Employee emp : g.getOrDefault(managerId, new HashSet<>())) {
                if (!visited.contains(emp.id)) {
                    visited.add(emp.id);
                    res += (1 + dfs(g, visited, emp.id));
                }
            }
            return res;
        }

        public void print() {
            Set<Integer> visited = new HashSet<>();
            Map<Integer, Set<Employee>> g = new HashMap<>();
            for (Employee emp : map.values()) {
                g.putIfAbsent(emp.managerId, new HashSet<>());
                g.get(emp.managerId).add(emp);
            }
            dfs(g, visited, -1, "");
        }

        private void dfs(Map<Integer, Set<Employee>> g, Set<Integer> visited, int managerId, String s) {
            for (Employee emp : g.getOrDefault(managerId, new HashSet<>())) {
                if (!visited.contains(emp.id)) {
                    System.out.println(s + emp);
                    visited.add(emp.id);
                    dfs(g, visited, emp.id, s + "  ");
                }
            }
        }

    }

    static class Employee {
        int id;
        String name;
        int managerId;

        public Employee(int id, String name, int managerId) {
            super();
            this.id = id;
            this.name = name;
            this.managerId = managerId;
        }

        @Override
        public String toString() {
            return name + "[" + id + "]";
        }
    }
}
