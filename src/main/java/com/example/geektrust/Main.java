package com.example.geektrust;

import com.example.geektrust.Model.MetroCard;
import com.example.geektrust.Model.MetroSystem;
import com.example.geektrust.Model.Passenger;
import com.example.geektrust.Model.PassengerType;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello World");

        /*
        Sample code to read from file passed as command line argument
        try {
            // the file to be opened for reading
            FileInputStream fis = new FileInputStream(args[0]);
            Scanner sc = new Scanner(fis); // file to be scanned
            // returns true if there is another line to read
            while (sc.hasNextLine()) {
               //Add your code here to process input commands
            }
            sc.close(); // closes the scanner
        } catch (IOException e) {
        }
        */


        MetroSystem metroSystem = new MetroSystem();

        MetroCard card1 = new MetroCard("MC001", 150);
        MetroCard card2 = new MetroCard("MC002", 50);
        MetroCard card3 = new MetroCard("MC003", 20);

        Passenger p1 = new Passenger("John", PassengerType.ADULT, card1);
        Passenger p2 = new Passenger("Alice", PassengerType.SENIOR_CITIZEN, card2);
        Passenger p3 = new Passenger("Bob", PassengerType.KID, card3);

        metroSystem.travel(p1, "Central", "Airport", false);
        metroSystem.travel(p2, "Central", "Airport", true);
        metroSystem.travel(p3, "Airport", "Central", false);
        metroSystem.travel(p1, "Airport", "Central", false);
        metroSystem.travel(p2, "Airport", "Central", false);

        metroSystem.printCollectionSummary();
        metroSystem.printPassengerSummary();
    }
}
