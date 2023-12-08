# Banking-Management-System-using-Java
This Java project implements a simple banking system that allows users to create accounts, deposit and withdraw funds, check balances, and apply for loans. The project utilizes JDBC for database connectivity to store account information and loan applications.

## Features

- **Account Management:**
  - Create new accounts with various details such as name, age, nationality, gender, occupation, credit score, phone number, address, salary, and work experience.
  - Validate inputs for account creation, ensuring accurate information.
  - Deposit and withdraw funds from existing accounts.

- **Loan Management:**
  - Apply for loans with specific eligibility criteria based on age, credit score, and loan type (Personal Loan or Home Loan).
  - Calculate and display monthly loan installment amounts.
  - Save loan application details to a file.

- **Database Connectivity:**
  - Utilize JDBC to connect to a database for storing and retrieving account information.
  - Create necessary tables in the database to store account details.

- **File Handling:**
  - Read and display loan application details from saved files.
  - Handle exceptions for file operations.

## Usage

1. **Compile and Run:**
   - Compile the project using `javac` and run it using `java`.
   ```bash
   javac MainApp/BankingSystem.java
   java MainApp.BankingSystem <maxAccounts> <least_balance>
   ```

2. **Command-Line Arguments:**
   - Provide the maximum number of accounts (`maxAccounts`) and the minimum initial balance (`least_balance`) as command-line arguments.

3. **Menu Options:**
   - The system presents a menu with various options:
     - Create Account
     - Deposit
     - Withdraw
     - Check Balance
     - Display Records
     - Apply for Loan
     - Display Loan Application
     - Exit

4. **Exception Handling:**
   - The system handles invalid inputs and ensures data integrity.

5. **Database Configuration:**
   - The project uses JDBC for database connectivity. Configure the JDBC settings in the `JDBC_Database` class.

