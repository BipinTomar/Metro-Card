package com.example.geektrust;

import java.util.*;

class Person implements Comparable<Person> {
    String name;
    int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    // Natural ordering by name (Comparable interface)
    @Override
    public int compareTo(Person other) {
        return this.name.compareTo(other.name); // Lexicographical order by name
    }

    @Override
    public String toString() {
        return name + " (" + age + ")";
    }
}

class AgeComparator implements Comparator<Person> {
    // Custom comparison by age (Comparator interface)
    @Override
    public int compare(Person p1, Person p2) {
        return Integer.compare(p1.age, p2.age); // Ascending order by age
    }
}

public class Main1 {
    public static void main(String[] args) {
        List<Person> people = Arrays.asList(
                new Person("Alice", 30),
                new Person("Bob", 25),
                new Person("Charlie", 35)
        );

        // Sorting by natural order (name) using Comparable
        Collections.sort(people);
        System.out.println("Sorted by name: " + people);

        // Sorting by custom comparator (age) using Comparator
        Collections.sort(people, new AgeComparator());
        System.out.println("Sorted by age: " + people);
    }
}

