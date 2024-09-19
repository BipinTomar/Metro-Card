package com.example.geektrust.Exception;

public class Passenger {

    private String name;
    private PassengerType type;
    private MetroCard metroCard;

    public Passenger(String name, PassengerType type, MetroCard metroCard) {
        this.name = name;
        this.type = type;
        this.metroCard = metroCard;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(PassengerType type) {
        this.type = type;
    }

    public void setMetroCard(MetroCard metroCard) {
        this.metroCard = metroCard;
    }

    public String getName() {
        return name;
    }

    public PassengerType getType() {
        return type;
    }

    public MetroCard getMetroCard() {
        return metroCard;
    }
}
