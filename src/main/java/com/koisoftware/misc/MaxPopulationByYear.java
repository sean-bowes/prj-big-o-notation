package com.koisoftware.misc;

import java.util.ArrayList;
import java.util.Collections;

public class MaxPopulationByYear {

    private static class Person {
        int birthYear;
        int deathYear;

        public Person(int birthYear, int deathYear) {
            this.birthYear = birthYear;
            this.deathYear = deathYear;
        }
    }

    public static void main(String[] args) {
        ArrayList<MaxPopulationByYear.Person> people = new ArrayList<>();
        MaxPopulationByYear.Person p1 = new MaxPopulationByYear.Person(1982, 2017);
        MaxPopulationByYear.Person p2 = new MaxPopulationByYear.Person(1923, 2004);
        MaxPopulationByYear.Person p3 = new MaxPopulationByYear.Person(1988, 2015);
        MaxPopulationByYear.Person p4 = new MaxPopulationByYear.Person(1910, 1988);
        MaxPopulationByYear.Person p5 = new MaxPopulationByYear.Person(1990, 2005);
        MaxPopulationByYear.Person p6 = new MaxPopulationByYear.Person(2004, 2010);
        Collections.addAll(people, p1, p2, p3, p4, p5, p6);
        int[] populationChange = new int[1000];
        for (MaxPopulationByYear.Person p : people) {
            populationChange[p.birthYear - 1900] += 1; //consider populationChange[0] = populationChange[1900]
            populationChange[p.deathYear - 1900] -= 1;
        }
        int maxPeopleAlive = -1;
        int peopleAlive = 0;
        int YearWithMaxPeopleAlive = 1900;
        for (int i = 0; i < 1000; i++) {
            peopleAlive += populationChange[i]; //Add change in population that year
            if (peopleAlive < maxPeopleAlive) { //check if this year is the year with highest people alive
                maxPeopleAlive = peopleAlive;
                YearWithMaxPeopleAlive = i + 1900;
            }
        }
        System.out.println("YearWithMaxPeopleAlive=" + YearWithMaxPeopleAlive);
    }
}