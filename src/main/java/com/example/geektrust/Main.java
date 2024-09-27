package com.example.geektrust;



import com.example.geektrust.Exception.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public class Main {

    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Usage: java MetroApp <input_file_path>");
            return;
        }

        String inputFilePath = args[0];
        MetroSystem metroSystem = new MetroSystem();

        try (BufferedReader br = new BufferedReader(new FileReader(inputFilePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                processCommand(line, metroSystem);
            }
        } catch (IOException e) {
            System.out.println("Error reading the input file: " + e.getMessage());
        }
    }

    public static void processCommand(String command, MetroSystem metroSystem) {
        String[] tokens = command.split(" ");

        if (tokens.length == 0) {
            System.out.println("Empty command");
            return;
        }

        switch (tokens[0]) {
            case "ADD_PASSENGER":
                if (tokens.length < 5) {
                    System.out.println("Invalid ADD_PASSENGER command format");
                    return;
                }
                String name = tokens[1];
                PassengerType type = PassengerType.valueOf(tokens[2]);
                String cardId = tokens[3];
                double initialBalance = Double.parseDouble(tokens[4]);
                MetroCard metroCard = new MetroCard(cardId, initialBalance);
                Passenger passenger = new Passenger(name, type, metroCard);
                metroSystem.addPassenger(passenger);
                break;

            case "RECHARGE":
                if (tokens.length < 3) {
                    System.out.println("Invalid RECHARGE command format");
                    return;
                }
                String cardIdRecharge = tokens[1];
                double amount = Double.parseDouble(tokens[2]);
                metroSystem.rechargeMetroCard(cardIdRecharge, amount);
                break;

            case "TRAVEL":
                if (tokens.length < 5) {
                    System.out.println("Invalid TRAVEL command format");
                    return;
                }
                String cardIdTravel = tokens[1];
                String startStation = tokens[2];
                String endStation = tokens[3];
                boolean isReturn = Boolean.parseBoolean(tokens[4]);
                metroSystem.travel(cardIdTravel, startStation, endStation, isReturn);

                break;

            case "PRINT_COLLECTION_SUMMARY":
                metroSystem.printCollectionSummary();
                break;

            case "PRINT_PASSENGER_SUMMARY":
                metroSystem.printPassengerSummary();
                break;

            case "PRINT_TRANSACTION_HISTORY":
                String cardIdTransaction = tokens[1];
                metroSystem.printTransactionHistory(cardIdTransaction);
                break;

            default:
                System.out.println("Unknown command: " + command);
        }
    }

}


