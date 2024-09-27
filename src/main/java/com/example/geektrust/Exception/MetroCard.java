package com.example.geektrust.Exception;

import java.util.List;

public class MetroCard {
        private String id;
        private double balance;

        public MetroCard(String id, double balance) {
                this.id = id;
                this.balance = balance;
        }

        public String getId() {
                return id;
        }

        public double getBalance() {
                return balance;
        }

        public void recharge(double amount) {
                balance += amount;
        }

        public void deduct(double amount) {
                balance -= amount;
        }
}


