package MainApp;

import Custom_Exceptions.*;
import JDBC_Connection.JDBC_Database;
import FileHandling_Loan.LoanApplication;
import User.Account;
import bankFunctions.Bank;
import bankFunctions.Loan;
import bankFunctions.PersonalLoan;
import bankFunctions.HomeLoan;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.SQLException;
import java.io.IOException;

public class BankingSystem 
{
public static void main(String[] args) throws InvalidInputException
{
if (args.length < 2) 
{
System.out.println("Usage: java MainApp.BankingSystem <maxAccounts> <least_balance>");
System.exit(1);
}

int maxAccounts = Integer.parseInt(args[0]);
double least_balance = Double.parseDouble(args[1]);

Bank bank = new Bank(maxAccounts, least_balance);
Scanner scanner = new Scanner(System.in);

JDBC_Database.loadDriver();
Connection con = JDBC_Database.createConnection();
Statement stmt = JDBC_Database.createStatement();
JDBC_Database.createTable_accounts();
int accountNumber = -1;
int choice;
double loanAmount;
int loanTerm;
int loanType ;

while (true) 
{
System.out.println("1. Create Account");
System.out.println("2. Deposit");
System.out.println("3. Withdraw");
System.out.println("4. Check Balance");
System.out.println("5. Display record of all users");
System.out.println("6. Display record for a particular user");
System.out.println("7. Apply for Loan");
System.out.println("8. Display Loan Application of a particular user");
System.out.println("9. Exit");
try 
{
System.out.print("Enter your choice: ");
choice = scanner.nextInt();
} 
catch (InputMismatchException e) 
{
System.out.println("Invalid input. Please enter a valid integer choice.");
scanner.next();
continue;
}

switch (choice) 
{
case 1:
try {
if (bank.accountNumberCounter < maxAccounts) 
{
System.out.print("Enter account holder's name: ");
String accountHolder = scanner.next();

if (!accountHolder.matches("[a-zA-Z]+")) 
{
throw new InvalidInputException("Invalid Name. Please enter only alphabets.");
}

System.out.print("Enter account holder's age: ");

int age = Integer.parseInt(scanner.next());

if (age < 18 || age > 70) 
{
throw new InvalidInputException("Invalid Age. Age should be between 18 and 70.");
}

else
{
System.out.print("Enter account holder's nationality: ");
String nationality = scanner.next();
if (!nationality.matches("[a-zA-Z]+")) 
{
throw new InvalidInputException("Invalid Nationality. Please enter only alphabets.");
}
System.out.print("Enter account holder's gender(M/F/O): ");
String Gender = scanner.next().toUpperCase();
if (!Gender.matches("[MFO]")) 
{
throw new InvalidInputException("Invalid Gender. Please enter 'M' for Male, 'F' for Female, or 'O' for Other.");
}
System.out.print("Enter account holder's occupation: ");
String occupation = scanner.next();
if (!occupation.matches("[a-zA-Z]+")) 
{
throw new InvalidInputException("Invalid Occupation. Please enter only alphabets.");
}
System.out.print("Enter account holder's credit score: ");
int creditScore = Integer.parseInt(scanner.next());

if(creditScore>=300 && creditScore<=900)
{
System.out.print("Enter account holder's phone number: ");
String phoneNo= scanner.next();

if (!phoneNo.matches("\\d+")) 
{
throw new InvalidInputException("Invalid Phone Number. Please enter whole numbers only.");
}

if(phoneNo.length()==10)
{
if(phoneNo.charAt(0)!='0')
{
System.out.print("Enter account holder's address: ");
String address = scanner.next();
scanner.nextLine();
System.out.print("Enter account holder's salary (per month): ");
int salary = Integer.parseInt(scanner.nextLine());
System.out.print("Enter account holder's work experience (in years): ");
int workExpYear = Integer.parseInt(scanner.nextLine());
System.out.print("Enter initial balance: ");
double initialBalance = Double.parseDouble(scanner.nextLine());

if (initialBalance >= least_balance) 
{
bank.createAccount(accountHolder, initialBalance,age,nationality,occupation,salary,creditScore,address,Gender,phoneNo,workExpYear);

JDBC_Database.insertData_accounts(accountHolder, initialBalance,age,nationality,occupation,salary,creditScore,address,Gender,phoneNo,workExpYear);
} 
else 
{
System.out.println("Insufficient initial balance. Account cannot be created.");
}
}

else
{
System.out.println("Invalid Phone Number.");
}
}

else
{
System.out.println("Invalid Phone Number.");
}
}

else
{
System.out.println("Out of bound credit score. Account cannot be created.");
}
}
}

else 
{
System.out.println("Maximum number of accounts reached.");
}
} 
catch (NumberFormatException e) 
{
System.out.println("Invalid input: Enter a number. ");
}
catch(InvalidInputException e)
{
System.out.println("Invalid input: " + e.getMessage());
}

break;

case 2:
try{
System.out.print("Enter account number: ");
accountNumber = scanner.nextInt();
if (bank.ValidAccNum(accountNumber)) 
{
while (true) 
{
System.out.print("Enter deposit amount (or -1 to finish): ");
double depositAmount = scanner.nextDouble();
if (depositAmount == -1) 
{
break; 
}
bank.deposit(accountNumber, depositAmount);
JDBC_Database.updateBalanceAfterDeposit(accountNumber,depositAmount);
}
}
else 
{
System.out.println("Account not found.");
}
}
catch (InputMismatchException e) 
{
System.out.println("Invalid input. Please enter a valid integer choice.");
scanner.next();
continue;
}
break;

case 3:
try
{
System.out.print("Enter account number: ");
accountNumber = scanner.nextInt();
if (bank.ValidAccNum(accountNumber)) 
{
System.out.print("Enter withdrawal amount: ");
double withdrawalAmount = scanner.nextDouble();
bank.withdraw(accountNumber, withdrawalAmount);
JDBC_Database.updateBalanceAfterWithdrawal(accountNumber,withdrawalAmount);
} 
else 
{
System.out.println("Account not found.");
}
}
catch (InputMismatchException e) 
{
System.out.println("Invalid input. Please enter a valid integer choice.");
scanner.next();
continue;
}
break;

case 4:
try
{
System.out.print("Enter account number: ");
accountNumber = scanner.nextInt();
bank.checkBalance(accountNumber);
}
catch (InputMismatchException e) 
{
System.out.println("Invalid input. Please enter a valid integer choice.");
scanner.next();
continue;
}
break;

case 5:
JDBC_Database.extractData_AllUsers();
break;

case 6:
try
{
System.out.print("Enter account number: ");
accountNumber = scanner.nextInt();
JDBC_Database.extractData_OneUser(accountNumber);
}
catch (InputMismatchException e) 
{
System.out.println("Invalid input. Please enter a valid integer choice.");
scanner.next();
continue;
}
break;

case 7:
try
{
System.out.print("Enter account number: ");
accountNumber = scanner.nextInt();
}
catch (InputMismatchException e) 
{
System.out.println("Invalid input. Please enter a valid integer choice.");
scanner.next();
continue; 
}
if (bank.ValidAccNum(accountNumber)) 
{
if (bank.check_age(accountNumber) >= 18 && bank.check_age(accountNumber) <= 70) 
{
if (bank.check_CS(accountNumber) >= 700) 
{
try 
{
System.out.print("Enter loan amount: ");
loanAmount = scanner.nextDouble();
} 
catch (InputMismatchException e) 
{
System.out.println("Invalid input: Enter a valid loan amount.");
scanner.next();
break;
}
try
{
System.out.print("Enter loan term (in months): ");
loanTerm = scanner.nextInt();
}
catch (InputMismatchException e) 
{
System.out.println("Invalid input: Enter a valid loan term.");
scanner.next();
break;
}
try
{
System.out.print("Enter loan type (1 for Personal Loan, 2 for Home Loan): ");
loanType = scanner.nextInt();
}
catch (InputMismatchException e) 
{
System.out.println("Invalid input: Enter a valid loan type.");
scanner.next();
break;
}

Loan loan;
if (loanType == 1) 
{
loan = new PersonalLoan();
} 
else if (loanType == 2) 
{
loan = new HomeLoan();
} 
else 
{
System.out.println("Invalid loan type.");
break;
}

Account account = bank.getAccount(accountNumber);
if (account != null) 
{
if (loan.checkEligibility(account, loanAmount)) 
{
double monthlyInstallment = loan.calcMonthlyInstallments(loanAmount, loanTerm);
System.out.println("You are eligible for the loan.\nThe Monthly installment will be Rs. " + monthlyInstallment);
System.out.println("Do you want to apply for it (y/n):");
String applyForLoan = scanner.next();
if (applyForLoan.equalsIgnoreCase("y")) 
{
try 
{
LoanApplication fileHandler = new LoanApplication();
fileHandler.createFile("LoanApplication_" + accountNumber + ".txt");
String fileContent =
"Account Holder's Name: " + account.getAccountHolder() + "\n" +
"Account Number: " + accountNumber + "\n" +
"Phone Number: " + account.getPhoneNo() + "\n" +
"Credit Score: " + account.getCreditScore() + "\n" +
"Income: " + account.getSalary() + "\n" +
"Loan Amount: Rs. " + loanAmount + "\n" +
"Loan Term: " + loanTerm + " months\n" +
"Loan Type: " + (loanType == 1 ? "Personal Loan" : "Home Loan" +"\n")+"\n"+"---------------------------------------";

fileHandler.writeFile(fileContent);
System.out.println("Loan application details saved to file.");
} 
catch (IOException e) 
{
System.out.println("Error writing to the file: " + e);
}
} 

else 
{
System.out.println("You are not eligible for the loan. Insufficient balance.");
}
} 
else 
{
System.out.println("Account not found.");
}
} 
else 
{
System.out.println("You cannot apply for a loan as your credit score is not up to the mark.");
}
} 
else 
{
System.out.println("You cannot apply for a loan as your age is out of the limits of the loan criteria.");
}
} 
else 
{
System.out.println("Account not found.");
}
}
break;

case 8:
try
{
System.out.print("Enter account number: ");
accountNumber = scanner.nextInt();
System.out.println("Loan Application for account number " + accountNumber);

LoanApplication loanApplication = new LoanApplication();
loanApplication.file = new File("C:/Users/anany/OneDrive/Documents/COLLEGE/SECOND YEAR/JAVA Programming/JAVA_Project/Loan_Applications/LoanApplication_" + accountNumber + ".txt");

loanApplication.readFile();
}
catch (NumberFormatException e) 
{
System.out.println("Invalid input: Enter a number. ");
}
catch (InputMismatchException e) 
{
System.out.println("Invalid input: Enter a number. ");
}
catch (FileNotFoundException e) 
{
System.out.println("Loan application file not found.");
} 
catch (IOException e) 
{
System.out.println("Error reading the loan application file: " + e.getMessage());
}

break;

case 9:
System.out.println("Exiting the banking system.");
System.exit(0);
break;

default:
System.out.println("Invalid choice. Please try again.");
break;
}
}
}
}

