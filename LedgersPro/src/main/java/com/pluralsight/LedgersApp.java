package com.pluralsight;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LedgersApp {
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

            BufferedWriter bufWriter = new BufferedWriter(new FileWriter("C:\\Pluralsight\\LearntoCodeCapstone\\Ledger--Pro\\LedgersPro\\SampleTransactions.csv"));
            bufWriter.newLine();
            bufWriter.write(newInput.toString());
            bufWriter.close();

            System.out.println("Deposit recorded!");

            System.out.println("New Deposit added \n" + newInput);


        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
        System.out.println("Press Enter to return to the main menu...");
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
        double userDeposit = -Math.abs(scanner.nextDouble());
        scanner.nextLine();

        LocalDateTime addedOn = LocalDateTime.now();
        TransactionRecord newInput = new TransactionRecord(addedOn, description, userVendor, (float) userDeposit);


        try {

            BufferedWriter bufWriter = new BufferedWriter(new FileWriter("C:\\Pluralsight\\LearntoCodeCapstone\\Ledger--Pro\\LedgersPro\\SampleTransactions.csv"));
            bufWriter.newLine();
            bufWriter.write(newInput.toString());
            bufWriter.close();

            System.out.println("Payment Recorded!");

            System.out.println("New Payment added \n" + newInput);


        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());

        }
        System.out.println("Press Enter to return to the main menu...");
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
            BufferedReader bufferedReader = new BufferedReader(new FileReader("C:\\Pluralsight\\LearntoCodeCapstone\\Ledger--Pro\\LedgersPro\\SampleTransactions.csv"));
            bufferedReader.readLine();

//            FileWriter fileWriter = new FileWriter("sample_25_transactions.csv");
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
            BufferedReader reader = new BufferedReader(new FileReader("C:\\Pluralsight\\LearntoCodeCapstone\\Ledger--Pro\\LedgersPro\\SampleTransactions.csv"));

            reader.readLine();

            String deposits;
            while ((deposits = reader.readLine()) != null) {
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
            reader.close();


        } catch (IOException e) {
            System.out.println("Error reading deposits." + e.getMessage());
        }
    }

    public static void displayPayments() {
        System.out.println("Payments");
        System.out.println("-----");
        System.out.println();

        try {
            BufferedReader reader = new BufferedReader(new FileReader("C:\\Pluralsight\\LearntoCodeCapstone\\Ledger--Pro\\LedgersPro\\SampleTransactions.csv"));

            reader.readLine();

            String payments;
            while ((payments = reader.readLine()) != null) {
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
            reader.close();


        } catch (IOException e) {
            System.out.println("Error reading payments." + e.getMessage());
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

        List<TransactionRecord> records = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader("C:\\Pluralsight\\LearntoCodeCapstone\\Ledger--Pro\\LedgersPro\\SampleTransactions.csv"));

            reader.readLine();

            String transactions;

            LocalDate currentDate = LocalDate.now();

            while ((transactions = reader.readLine()) != null) {
                if (transactions.trim().isEmpty()) continue;


                String[] tokens = transactions.split("\\|");
                if (tokens.length < 5) {
                    continue;
                }
                tokens[0] = tokens[0].trim();
                tokens[1] = tokens[1].trim();

                LocalDateTime addedOn = LocalDateTime.parse(tokens[0].trim() + "T" + tokens[1].trim());
                LocalDate transactionsDate = addedOn.toLocalDate();

                if (transactionsDate.getMonth() == currentDate.getMonth() &&
                        transactionsDate.getYear() == currentDate.getYear()) {

                    // Display or process the transaction
                    String description = tokens[2];
                    String vendor = tokens[3];
                    float userDeposit = Float.parseFloat(tokens[4].trim());

                    TransactionRecord transaction = new TransactionRecord(addedOn, description, vendor, userDeposit);
                    records.add(transaction);

                }
            }
            reader.close();

            if (records.isEmpty()) {
                System.out.println("No transactions found for this month.");
            } else {
                sortNewestFirst(records);
                for (TransactionRecord record : records) {
                    System.out.println(record);
                }
            }


        } catch (IOException e) {
            System.out.println("Error reading SampleTransactions.csv: " + e.getMessage());
        }
            System.out.println("Press Enter to return to the report menu...");
            scanner.nextLine();
            viewReports();

    }

    public static void displayPreviousMonth() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("C:\\Pluralsight\\LearntoCodeCapstone\\Ledger--Pro\\LedgersPro\\SampleTransactions.csv"));

            reader.readLine();

            String transactions;

            LocalDate currentDate = LocalDate.now();
            LocalDate lastMonthDate = currentDate.minusMonths(1);
            boolean found = false;

            while ((transactions = reader.readLine()) != null) {
                String[] tokens = transactions.split("\\|");

                if (tokens.length < 5) {
                    continue;
                }

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
                    found = true;
                }

            }reader.close();


            if (!found) {
                System.out.println("Transaction not recorded");
            }


        } catch (IOException e) {
            System.out.println("Error reading SampleTransactions.csv: " + e.getMessage());
        }
            System.out.println("Press Enter to return to the report menu...");
            scanner.nextLine();
            viewReports();

    }

    public static void displayYearToDate() {
        System.out.println("Transactions this Year");

        try {
            BufferedReader reader = new BufferedReader(new FileReader("C:\\Pluralsight\\LearntoCodeCapstone\\Ledger--Pro\\LedgersPro\\SampleTransactions.csv"));

            reader.readLine();

            String transactions;

            LocalDate currentDate = LocalDate.now();
            boolean found = false;

            while ((transactions = reader.readLine()) != null) {
                String[] tokens = transactions.split("\\|");
                if (tokens.length < 5) {
                    continue;
                }

                LocalDateTime addedOn = LocalDateTime.parse(tokens[0] + "T" + tokens[1]);
                LocalDate transactionsDate = addedOn.toLocalDate();

                if (transactionsDate.getYear() == currentDate.getYear()) {

                    // Display or process the transaction
                    String description = tokens[2];
                    String vendor = tokens[3];
                    float userDeposit = Float.parseFloat(tokens[4].trim());

                    TransactionRecord transaction = new TransactionRecord(addedOn, description, vendor, userDeposit);
                    System.out.println(transaction);
                    found = true;
                }
            }reader.close();

            if (!found) {
                System.out.println("No transactions recorded.");
            }
        } catch (IOException e) {
            System.out.println("Error reading SampleTransactions.csv: " + e.getMessage());
        }
            System.out.println("\nPress Enter to return to the report menu...");
            scanner.nextLine();
            viewReports();

    }

    public static void displayPreviousYear() {

        System.out.println("Last Year's Transactions");
        System.out.println("-----");

        try {
            BufferedReader reader = new BufferedReader(new FileReader("C:\\Pluralsight\\LearntoCodeCapstone\\Ledger--Pro\\LedgersPro\\SampleTransactions.csv"));

            reader.readLine();

            String transactions;

            LocalDate currentDate = LocalDate.now();
            int lastYearDate = LocalDate.now().getYear() -1;

            while ((transactions = reader.readLine()) != null) {
                String[] tokens = transactions.split("\\|");
                if (tokens.length < 5) {
                    continue;
                }

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


            } reader.close();
        }
        catch (IOException e) {
            System.out.println("Error reading SampleTransactions.csv: " + e.getMessage());
        }
            System.out.println("Press Enter to return to the report menu...");
            scanner.nextLine();
            viewReports();
    }

    public static void displaySearchByVendor() {
    Scanner input = new Scanner(System.in);
        System.out.println("Search for a transaction by vendor");
//        if (scanner.hasNextLine()) {
//            scanner.nextLine();
//        }
        String search = input.nextLine().trim().toUpperCase();


        try{
            BufferedReader reader = new BufferedReader(new FileReader("C:\\Pluralsight\\LearntoCodeCapstone\\Ledger--Pro\\LedgersPro\\SampleTransactions.csv"));

            reader.readLine();

            String byVendor;
            boolean found = false;

            while ((byVendor = reader.readLine()) != null) {
                if (byVendor.trim().isEmpty()) {
                    continue;
                }
                String[] tokens = byVendor.split("\\|");

                if (tokens.length < 5) {
                    continue;
                }
                String vendor = tokens[3].trim().toUpperCase();

                if (search.equals(vendor)) {

                    LocalDateTime addedOn = LocalDateTime.parse(tokens[0] + "T" + tokens[1]);
                    String description = tokens[2];
                    float userDeposit = Float.parseFloat(tokens[4].trim());

                    TransactionRecord transactionRecord = new TransactionRecord(addedOn, description, vendor, userDeposit);

                    System.out.println("Transaction Found\n");
                    System.out.println(transactionRecord);
                    found = true;
                }
            }

            reader.close();

            if (!found) {
                System.out.println("No transaction found for vendor:" + search);
            }
            System.out.println("Press Enter to return to the report menu...");



        } catch (IOException e) {
            System.out.println("Error reading SampleTransactions.csv: " + e.getMessage());
        }   input.nextLine();
            viewReports();
    }

    public static void clearTransactions() {
        System.out.println("This will erase all transactions. Are you sure? (Y/N)");
        String confirm = scanner.next().toUpperCase();
        scanner.nextLine(); // consume newline

        if (confirm.equals("Y")) {
            try {
                FileWriter writer = new FileWriter("SampleTransactions.csv");
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

    public static void sortNewestFirst(List<TransactionRecord> records) {
        records.sort((a, b) -> b.getAddedOn().compareTo(a.getAddedOn()));
    }
}






