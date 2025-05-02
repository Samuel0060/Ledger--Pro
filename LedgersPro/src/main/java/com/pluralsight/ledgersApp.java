package com.pluralsight;

import java.awt.desktop.SystemEventListener;
import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Scanner;

public class ledgersApp {
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

                case "C":
                clearTransactions();
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


        LocalDateTime addedOn = LocalDateTime.now();

        TransactionRecord newInput = new TransactionRecord(addedOn, description, userVendor, (float) userDeposit);


        try {

            FileWriter fileWriter = new FileWriter("transactions.csv", true);
            BufferedWriter bufWriter = new BufferedWriter(fileWriter);
            bufWriter.newLine();
            bufWriter.write(newInput.toString());
            bufWriter.newLine();
            bufWriter.close();

            System.out.println("\nDeposit recorded!");

            System.out.println("New Deposit added \n" + newInput + "\n");


        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
        System.out.println("\nPress Enter to return to the main menu...");
        scanner.nextLine();
    }

    public static void displayMakePayment() {
        System.out.println("Make Payment");
        System.out.println("------");

        System.out.println("Please provide payment information");
        System.out.println("Description");
        String description = scanner.nextLine();

        System.out.println("Vendor");
        String userVendor = scanner.nextLine();

        System.out.println("Payment amount");
        double userDeposit = scanner.nextDouble();
        scanner.nextLine();

        LocalDateTime addedOn = LocalDateTime.now();
        TransactionRecord newInput = new TransactionRecord(addedOn, description, userVendor, (float) userDeposit);


        try {

            FileWriter fileWriter = new FileWriter("transactions.csv", true);
            BufferedWriter bufWriter = new BufferedWriter(fileWriter);
            bufWriter.newLine();
            bufWriter.write(newInput.toString());
            bufWriter.newLine();
            bufWriter.close();

            System.out.println("Payment Recorded!");

            System.out.println("New Payment added \n" + newInput + "\n");


        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());

        }
        System.out.println("\nPress Enter to return to the main menu...");
        scanner.nextLine();


    }

    public static void displayLedger() {

        System.out.println("Ledger!");
        System.out.println("-----");
        System.out.println("A - Display all entries");
        System.out.println("D - Your Deposits");
        System.out.println("P - Your Payments");
        System.out.println("R - View Report");
        System.out.println("C - Clear transactions");
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

            case "C":
                clearTransactions();
                break;

            case "H":
                displayHomeScreen();
                break;
        }
    }

    public static void displayAllEntries() {

        System.out.println("Transactions");
        System.out.println("-----");
        System.out.println();
        try {
            FileReader fileReader = new FileReader("transactions.csv");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            bufferedReader.readLine();

//            FileWriter fileWriter = new FileWriter("transactions.csv");
//            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);


            String all;
            while ((all = bufferedReader.readLine()) != null) {
                System.out.println(all);
            }

//            displayHomeScreen();
            System.out.println("Press Enter to go back to Ledger Menu");
            scanner.nextLine();
            displayLedger();
            bufferedReader.close();
        } catch (IOException e) {
            System.out.println("Error reading transactions.csv file" + e.getMessage());
        }

    }

    public static void displayDeposits() {
        System.out.println("Deposits");
        System.out.println("-----");
        System.out.println();

        try {
            FileReader fileReader = new FileReader("transactions.csv");
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            bufferedReader.readLine();

            String deposits;
            while ((deposits = bufferedReader.readLine()) != null) {
                String[] tokens = deposits.split("\\|");


                if (tokens.length < 5) {
                    continue;
                }

                float userDeposits = Float.parseFloat(tokens[4]);

                if (userDeposits > 0) {
                    LocalDateTime addedOn = LocalDateTime.parse(tokens[0] + "T" + tokens[1]);
                    String description = tokens[2];
                    String vendor = tokens[3];
                    float userDeposit = Float.parseFloat(tokens[4]);

                    TransactionRecord record = new TransactionRecord(addedOn, description, vendor, userDeposit);
                    System.out.println(record);
                }


            }
            System.out.println("Press Enter to go back to Ledger Menu");
            scanner.nextLine();
            displayLedger();
            bufferedReader.close();


        } catch (IOException e) {
            System.out.println("Error reading deposits." + e.getMessage());
        }
    }

    public static void displayPayments() {
        System.out.println("Payments");
        System.out.println("-----");
        System.out.println();

        try {
            FileReader fileReader = new FileReader("transactions.csv");
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            bufferedReader.readLine();

            String payments;
            while ((payments = bufferedReader.readLine()) != null) {
                String[] tokens = payments.split("\\|");


                if (tokens.length < 5) {
                    continue;
                }

                float userDeposit = Float.parseFloat(tokens[4].trim());

                if (userDeposit < 0) {
                    LocalDateTime addedOn = LocalDateTime.parse(tokens[0] + "T" + tokens[1]);
                    String description = tokens[2];
                    String vendor = tokens[3];

                    TransactionRecord addedPayment = new TransactionRecord(addedOn, description, vendor, userDeposit);


                    System.out.println(addedPayment);
                }


            }
            System.out.println("Press Enter to go back to Ledger Menu");
            scanner.nextLine();
            displayLedger();
            bufferedReader.close();


        } catch (IOException e) {
            System.out.println("Error reading deposits." + e.getMessage());
        }

    }

    public static void viewReports() {

        System.out.println("Reports");
        System.out.println("-----");
        System.out.println();

        System.out.println("1 - view transactions made this month");
        System.out.println("2 - view transactions made last month");
        System.out.println("3 - view all transactions made this year");
        System.out.println("4 - view all transactions made last year");
        System.out.println("5 - Search for a transaction by vendor");
        System.out.println("H - Home Page");

        String userChoice = scanner.next().toUpperCase();
        switch (userChoice) {


            case "1":
                displayMonthToDate();
                break;

            case "2":
                displayPreviousMonth();
                break;

            case "3":
                displayYearToDate();
                break;

            case "4":
                displayPreviousYear();
                break;

            case "5":
                displaySearchByVendor();
                break;

            case "H":
                displayHomeScreen();
                break;
        }

    }

    public static void displayMonthToDate() {
        System.out.println("Transactions this month");

        try {
            FileReader fileReader = new FileReader("transactions.csv");
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            bufferedReader.readLine();

            String transactions;

            LocalDate currentDate = LocalDate.now();

            while ((transactions = bufferedReader.readLine()) != null) {
                if (transactions.trim().isEmpty()) continue;


                String[] tokens = transactions.split("\\|");
                if (tokens.length < 5) {
                    continue;
                }
                tokens[0] = tokens[0].trim();
                tokens[1] = tokens[1].trim();

                LocalDateTime addedOn = LocalDateTime.parse(tokens[0] + "T" + tokens[1]);
                LocalDate transactionsDate = addedOn.toLocalDate();

                if (transactionsDate.getMonth() == currentDate.getMonth() &&
                        transactionsDate.getYear() == currentDate.getYear()) {

                    // Display or process the transaction
                    String description = tokens[2];
                    String vendor = tokens[3];
                    float userDeposit = Float.parseFloat(tokens[4].trim());

                    TransactionRecord transaction = new TransactionRecord(addedOn, description, vendor, userDeposit);
                    System.out.println(transaction);
                }

            }bufferedReader.close();
            System.out.println("\nPress Enter to return to the report menu...");
            scanner.nextLine();
            viewReports();


        } catch (IOException e) {
            System.out.println("Error reading transactions.csv: " + e.getMessage());
        }

    }

    public static void displayPreviousMonth() {
        try {
            FileReader fileReader = new FileReader("transactions.csv");
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            bufferedReader.readLine();

            String transactions;

            LocalDate currentDate = LocalDate.now();
            LocalDate lastMonthDate = currentDate.minusMonths(1);

            while ((transactions = bufferedReader.readLine()) != null) {
                String[] tokens = transactions.split("\\|");

                LocalDateTime addedOn = LocalDateTime.parse(tokens[0] + "T" + tokens[1]);
                LocalDate transactionsDate = addedOn.toLocalDate();

                if (transactionsDate.getMonth() == lastMonthDate.getMonth() &&
                        transactionsDate.getYear() == currentDate.getYear()) {

                    // Display or process the transaction
                    String description = tokens[2];
                    String vendor = tokens[3];
                    float userDeposit = Float.parseFloat(tokens[4].trim());

                    TransactionRecord transaction = new TransactionRecord(addedOn, description, vendor, userDeposit);
                    System.out.println(transaction);
                }

            }bufferedReader.close();
            System.out.println("\nPress Enter to return to the report menu...");
            scanner.nextLine();
            viewReports();

        } catch (IOException e) {
            System.out.println("Error reading transactions.csv: " + e.getMessage());
        }

    }

    public static void displayYearToDate() {
        System.out.println("Transactions this Year");

        try {
            FileReader fileReader = new FileReader("transactions.csv");
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            bufferedReader.readLine();

            String transactions;

            LocalDate currentDate = LocalDate.now();

            while ((transactions = bufferedReader.readLine()) != null) {
                String[] tokens = transactions.split("\\|");

                LocalDateTime addedOn = LocalDateTime.parse(tokens[0] + "T" + tokens[1]);
                LocalDate transactionsDate = addedOn.toLocalDate();

                if (transactionsDate.getYear() == currentDate.getYear()) {

                    // Display or process the transaction
                    String description = tokens[2];
                    String vendor = tokens[3];
                    float userDeposit = Float.parseFloat(tokens[4].trim());

                    TransactionRecord transaction = new TransactionRecord(addedOn, description, vendor, userDeposit);
                    System.out.println(transaction);
                }
                bufferedReader.close();
                System.out.println("\nPress Enter to return to the report menu...");
                scanner.nextLine();
                viewReports();

            }

        } catch (IOException e) {
            System.out.println("Error reading transactions.csv: " + e.getMessage());
        }

    }

    public static void displayPreviousYear() {

        System.out.println("Last Year's Transactions");
        System.out.println("-----");

        try {
            FileReader fileReader = new FileReader("transactions.csv");
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            bufferedReader.readLine();

            String transactions;

            LocalDate currentDate = LocalDate.now();
            int lastYearDate = LocalDate.now().getYear() -1;

            while ((transactions = bufferedReader.readLine()) != null) {
                String[] tokens = transactions.split("\\|");

                LocalDateTime addedOn = LocalDateTime.parse(tokens[0] + "T" + tokens[1]);
                LocalDate transactionsDate = addedOn.toLocalDate();

                if (transactionsDate.getYear() == lastYearDate) {

                    // Display or process the transaction
                    String description = tokens[2];
                    String vendor = tokens[3];
                    float userDeposit = Float.parseFloat(tokens[4].trim());

                    TransactionRecord transaction = new TransactionRecord(addedOn, description, vendor, userDeposit);
                    System.out.println(transaction);
                }

                bufferedReader.close();
                System.out.println("\nPress Enter to return to the report menu...");
                scanner.nextLine();
                viewReports();


            }
        } catch (IOException e) {
            System.out.println("Error reading transactions.csv: " + e.getMessage());
        }
    }

    public static void displaySearchByVendor() {

        System.out.println("Search for a transaction by vendor");
        if (scanner.hasNextLine()) {
            scanner.nextLine();
        }
        String search = scanner.nextLine().toUpperCase().trim();


        try{
            FileReader fileReader = new FileReader("transactions.csv");
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            bufferedReader.readLine();

            String byVendor;
            while ((byVendor = bufferedReader.readLine()) != null) {
                if (byVendor.trim().isEmpty()) {
                    continue;
                }
                String[] tokens = byVendor.split("\\|");

                if (tokens.length < 5) {
                    continue;
                }
                String vendor = tokens[3].toUpperCase();

                if (search.equals(vendor)) {

                    LocalDateTime addedOn = LocalDateTime.parse(tokens[0] + "T" + tokens[1]);
                    String description = tokens[2];
                    float userDeposit = Float.parseFloat(tokens[4].trim());

                    TransactionRecord transactionRecord = new TransactionRecord(addedOn, description, vendor, userDeposit);

                    System.out.println("Transaction Found\n");
                    System.out.println(transactionRecord);
                }

            }bufferedReader.close();
            System.out.println("\nPress Enter to return to the report menu...");
            scanner.nextLine();
            viewReports();


        } catch (IOException e) {
            System.out.println("Error reading transactions.csv: " + e.getMessage());
        }

    }

    public static void clearTransactions() {
        System.out.println("This will erase all transactions. Are you sure? (Y/N)");
        String confirm = scanner.next().toUpperCase();
        scanner.nextLine(); // consume newline

        if (confirm.equals("Y")) {
            try {
                FileWriter writer = new FileWriter("transactions.csv");
                // Write only the header back in, or leave blank to fully wipe
                writer.write("Date|Time|Description|Vendor|Amount\n");
                writer.close();
                System.out.println("All transactions have been cleared.");
            } catch (IOException e) {
                System.out.println("Error clearing the file: " + e.getMessage());
            }
        } else {
            System.out.println("Operation canceled.");
        }

        System.out.println("\nPress Enter to return to the main menu...");
        scanner.nextLine();
    }

}






