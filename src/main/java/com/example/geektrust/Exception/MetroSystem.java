package com.example.geektrust.Exception;

import java.util.*;

public class MetroSystem {

    private static final double ADULT_FARE = 200.0;
    private static final double SENIOR_CITIZEN_FARE = 100.0;
    private static final double KID_FARE = 50.0;
    private static final double RETURN_DISCOUNT = 0.5;
    private static final double SERVICE_FEE_PERCENTAGE = 0.02;

    private Map<String, Station> stations = new HashMap<>();

    private List<Journey> journeys = new ArrayList<>();
    private Map<PassengerType, Integer> passengerCount = new EnumMap<>(PassengerType.class);

    //these are metro card IDs with passengers object in a map
    private Map<String , Passenger> passengers = new HashMap<>();

    public MetroSystem() {
        stations.put("Central", new Station("Central"));
        stations.put("Airport", new Station("Airport"));
        for (PassengerType type : PassengerType.values()) {
            passengerCount.put(type, 0);
        }
    }

    public void addPassenger(Passenger passenger)
    {

        passengers.put(passenger.getMetroCard().getId() , passenger);
    }



    public void rechargeMetroCard(String cardId, double amount){
        Passenger passenger = passengers.get(cardId);

        if(passenger != null)
        {
            passenger.getMetroCard().recharge(amount);
        }
        else {
            System.out.println("No passenger found with card ID: " + cardId);
        }
    }
    public void travel(String cardId, String startStation, String endStation, boolean isReturn) {
        Passenger passenger = passengers.get(cardId);
        if (passenger == null) {
            System.out.println("No passenger found with card ID: " + cardId);
            return;
        }
        travel(passenger, startStation, endStation, isReturn);
    }



    public void travel(Passenger passenger, String startStation, String endStation, boolean isReturn){

        Journey journey = new Journey(passenger, startStation, endStation, isReturn);
        journeys.add(journey);

        //fare calculations according to the passenger category
        double fare = calculateFare(passenger.getType(), isReturn);
        Station station = stations.get(startStation);

        if(passenger.getMetroCard().getBalance() < fare)
        {
            double rechargeAmount = fare - passenger.getMetroCard().getBalance();
            passenger.getMetroCard().recharge(rechargeAmount * (1 + SERVICE_FEE_PERCENTAGE));
            station.addCollection(rechargeAmount*SERVICE_FEE_PERCENTAGE );
        }
        passenger.getMetroCard().deduct(fare);
        station.addCollection(fare);
        if (isReturn) {
            station.addDiscount(fare * RETURN_DISCOUNT);
        }
        //increasing the passenger count according to the passenger type
        passengerCount.put(passenger.getType(), passengerCount.get(passenger.getType()) +1);


    }
    private double calculateFare(PassengerType type, boolean isReturn) {
        double baseFare = 0;

        switch (type) {
            case ADULT:
                baseFare = ADULT_FARE;
                break;
            case SENIOR_CITIZEN:
                baseFare = SENIOR_CITIZEN_FARE;
                break;
            case KID:
                baseFare = KID_FARE;
                break;
        }
        return isReturn ? baseFare * RETURN_DISCOUNT : baseFare;
    }


    public void printCollectionSummary() {
        System.out.println("Collection Summary: ************************");

        for(Station station: stations.values())
        {
//            System.out.println( " Station contains Central True/False : " + stations.containsKey("Central"));

            System.out.println(station.getName() + " - Total Collection : " + station.getTotalCollection() + ", Total Discount : " + station.getTotalDiscount());
        }

    }

    public void printTransactionHistory(String cardId)
    {
        System.out.println("Transaction History Passengers");

    }
    public void printPassengerSummary() {
        System.out.println("Passenger Travel Summary: ************************");
        List<Map.Entry<PassengerType, Integer>> listPassengersCount = new ArrayList<>(passengerCount.entrySet());
        List<Map.Entry<String, Passenger>> listPassengers = new ArrayList<>(passengers.entrySet());


        //here b comes earlier for descending order
        listPassengersCount.sort((a, b) -> {
            int cmp = Integer.compare(b.getValue(), a.getValue());
            return cmp == 0 ? a.getKey().compareTo(b.getKey()) : cmp;
        });

        for (Map.Entry<PassengerType, Integer> entry : listPassengersCount) {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }
        System.out.println("Passenger Summary:  ************************");

        for (Map.Entry<String, Passenger> entry : listPassengers) {
            System.out.println( entry.getValue().getType() + " " + entry.getValue().getName()  + " "  + entry.getKey());
        }

    }





}
