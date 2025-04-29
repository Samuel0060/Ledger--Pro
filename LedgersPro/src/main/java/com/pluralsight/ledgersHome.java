package com.pluralsight;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class ledgersHome {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {


        boolean running = true;
        while (running) {
            displayHomeScreen();
            String selectedMenuOption = scanner.next().toUpperCase();
            scanner.nextLine();

            switch (selectedMenuOption) {
                case "D":
                    displayDepositScreen();
                    break;

                case "P":
                    displayMakePayment();
                    break;

                case "L":
                    displayLedger();
                    break;

                case "X":
                    running = false;
                    System.out.println("Goodbye!");
                    break;

                default:
                    System.out.println("Not a valid option. Try again");
            }
        }

    }

    public static void displayHomeScreen() {

        System.out.println("Welcome to your ledger!");
        System.out.println("-------------");
        System.out.println("D - Add Deposit");
        System.out.println("P - Make Payment");
        System.out.println("L - See Ledger");
        System.out.println("X - Exit");
    }

    public static void displayDepositScreen() {


        System.out.println("Deposit");
        System.out.println("------");

        System.out.println("Please provide deposit information");
        System.out.println("Description");
        String description = scanner.nextLine();

        System.out.println("Vendor");
        String userVendor = scanner.nextLine();

        System.out.println("Deposit amount");
        double userDeposit = scanner.nextDouble();
        scanner.nextLine();
        TransactionRecord newInput = new TransactionRecord(description, userVendor, userDeposit);

        try {

            FileWriter fileWriter = new FileWriter("transactions.csv", true);
            BufferedWriter bufWriter = new BufferedWriter(fileWriter);
            bufWriter.newLine();
            bufWriter.write(newInput.toFileFormatString());
            bufWriter.close();

            System.out.println("New Deposit added \n" + newInput.toFileFormatString());


        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void displayMakePayment() {
        System.out.println("Make Payment");
        System.out.println("------");

        System.out.println("Please provide payment information");
        System.out.println("Description");
        String description = scanner.nextLine();

        System.out.println("Vendor");
        String userVendor = scanner.nextLine();

        System.out.println("Deposit amount");
        double userDeposit = scanner.nextDouble();
        scanner.nextLine();
        TransactionRecord newInput = new TransactionRecord(description, userVendor, userDeposit);

        try {

            FileWriter fileWriter = new FileWriter("transactions.csv", true);
            BufferedWriter bufWriter = new BufferedWriter(fileWriter);
            bufWriter.write(newInput.toFileFormatString());
            bufWriter.newLine();
            bufWriter.close();

            System.out.println("New Payment added \n" + newInput.toString());


        } catch (IOException e) {
            throw new RuntimeException(e);

        }


    }

    public static void displayLedger() {

        System.out.println("Ledger!");
        System.out.println("-----");
        System.out.println("A - Display all entries");
        System.out.println("D - Your Deposits");
        System.out.println("P - Your Payments");
        System.out.println("R - View Report");
        System.out.println("H - back to Homepage");

        String userChoice = scanner.next().toUpperCase();
        scanner.nextLine();

        switch (userChoice) {
            case "A":
                displayAllEntries();
                break;

            case "D":
                displayDeposits();
                break;

            case "P":
                displayPayments();
                break;

            case "R":
                viewReports();
                break;

            case "H":
                displayHomeScreen();
                break;
        }
    }
    public static void displayAllEntries() {
        System.out.println();
    }

    public static void displayDeposits() {

    }

    public static void displayPayments() {

    }

    public static void viewReports() {

    }
}


