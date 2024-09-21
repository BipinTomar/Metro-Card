package com.example.geektrust.Exception;

public class Journey {
    private Passenger passenger;
    private String startStation;
    private String endStation;
    private boolean isReturn;

    public Journey(Passenger passenger, String startStation, String endStation, boolean isReturn) {
        this.passenger = passenger;
        this.startStation = startStation;
        this.endStation = endStation;
        this.isReturn = isReturn;
    }
    public Passenger getPassenger() {
        return passenger;
    }

    public boolean isReturn() {
        return isReturn;
    }
}
