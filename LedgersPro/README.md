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
![Screenshot (44)](https://github.com/user-attachments/assets/98119ab2-690c-4324-b748-db9770a15f38)
![![Screenshot (4![Screenshot (43)](https://github.com/user-attachments/assets/09792dcd-a31f-41ae-90a5-29a493c633ca)
9)](https://github.com/user-attachments/assets/503a77de-a34e-4b91-a73f-2cef801f41da)
Screenshot (48)](https://github.com/user-attachments/assets/409018ed-d25a-4668-a017-b449cf84aa28)
- ![Screenshot (46)]![Screenshot (47)](https://github.com/user-attachments/assets/bf5d0d43-f255-4a3b-ad66-23c21fbff9e1)
(https://github.com/user-attachments/assets/d4640ef7-fc31-4176-99d2-8c379d4204c1)
V![Screenshot (45)](https://github.com/user-attachments/assets/0449bc00-67ca-44a7-b459-61b4e85b21a2)
endor
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
