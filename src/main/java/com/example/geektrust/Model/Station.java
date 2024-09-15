package com.example.geektrust.Model;

public class Station {

    private String name;
    private double totalCollection;
    private double totalDiscount;

    public Station(String name)
    {
        this.name = name;
        this.totalCollection = 0;
        this.totalDiscount = 0;
    }

    public String getName() {
        return name;
    }

    public double getTotalCollection() {
        return totalCollection;
    }

    public double getTotalDiscount() {
        return totalDiscount;
    }

    public void addCollection(double amount) {
        totalCollection += amount;
    }

    public void addDiscount(double amount) {
        totalDiscount += amount;
    }
}
