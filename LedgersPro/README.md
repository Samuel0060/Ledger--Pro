# LedgerApp

**LedgerApp** is a console-based Java application that helps users track their financial activity by allowing them to record deposits and payments, and view or search through past transactions with ease.

## 📋 Features

- 💰 **Add Transactions**
    - Record deposits and payments with descriptions and vendor names.

- 📂 **View Transaction History**
    - Display all transactions.
    - Filter by:
        - Last month
        - Last year
        - Vendor search

- 🔍 **Search by Vendor**
    - Search transactions by the vendor name (case-insensitive).

## 🖥️ How It Works

Users interact with LedgerApp through a text-based menu. The menu options allow for:
- Entering a new deposit or payment
- Viewing transactions based on time or vendor
- Exiting the program

All transaction data is stored and retrieved from a CSV file (`transactions.csv`), with each record including:
- Date
- Time
- Description
- Vendor
- Amount

## 🚀 Getting Started

### Prerequisites
- Java 8 or higher
- A terminal or IDE that supports console input/output

### How to Run
1. Clone or download this repository.
2. Make sure `transactions.csv` is in the same directory as the app.
3. Compile and run the app using your IDE or the command line:
   ```bash
   javac LedgerApp.java
   java LedgerApp
