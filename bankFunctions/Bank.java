package bankFunctions;
import User.Account;

public class Bank
{
private Account[] accounts;
public int accountNumberCounter;
private final double least_balance;

public Bank(int maxAccounts, double least_balance)
{
accounts=new Account[maxAccounts];
accountNumberCounter=1;
this.least_balance=least_balance;
}

public void createAccount(String accountHolder, double initialBalance, int age,String nationality,String occupation,double salary,int creditScore,String address, String Gender,String phoneNo,double workExpYear)
{
Account account=new Account(accountNumberCounter, accountHolder, initialBalance,age,nationality,occupation,salary,creditScore,address,Gender,phoneNo,workExpYear);
accounts[accountNumberCounter]=account;
System.out.println("Account created with account number: " + accountNumberCounter);
accountNumberCounter++;
}

public void deposit(int accountNumber, double...amounts)
{
Account account=accounts[accountNumber];
for(double amount:amounts)
{
account.deposit(amount);
System.out.println("Deposited " + amount + " into account " + accountNumber);
}
}

public void withdraw(int accountNumber, double amount)
{
if(ValidAccNum(accountNumber))
{
Account account=accounts[accountNumber];
if(account.withdraw(amount))
{
System.out.println("Withdrawn " + amount + " from account " + accountNumber);
}
else
{
System.out.println("Insufficient balance.");
}
}

else
{
System.out.println("Account not found.");
}
}

public void checkBalance(int accountNumber) 
{
if (ValidAccNum(accountNumber)) 
{
Account account = accounts[accountNumber];
System.out.println("Account " + accountNumber + " balance: " + account.getBalance());
} 
else 
{
System.out.println("Account not found.");
}
}

public boolean ValidAccNum(int accountNumber) 
{
return accountNumber >= 0 && accountNumber < accountNumberCounter;
}

public Account getAccount(int accountNumber)
{
if (ValidAccNum(accountNumber))
{
return accounts[accountNumber];
}
return null;
}

public int check_age(int accountNumber)
{
Account account = accounts[accountNumber];
return account.getAge();
}

public int check_CS(int accountNumber)
{
Account account = accounts[accountNumber];
return account.getCreditScore();
}

public  String check_nationality(int accountNumber)
{
Account account = accounts[accountNumber];
return account.getNationality();
}

}