package User;

public class Account
{
private int accountNumber;
private String accountHolder;
private int age;
private String nationality;
private String occupation;
private double salary;
private int creditScore;
private double balance;
private String address;
private String Gender;
private String phoneNo;
private double workExpYear;

public Account(int accountNumber, String accountHolder, double balance,int age,String nationality,String occupation,double salary,int creditScore,String address, String Gender,String phoneNo,double workExpYear)
{
this.accountNumber=accountNumber;
this.accountHolder=accountHolder;
this.balance=balance;
this.age = age;
this.nationality=nationality;
this.occupation=occupation;
this.salary=salary;
this.creditScore = creditScore;
this.address=address;
this.Gender=Gender;
this.workExpYear=workExpYear;
this.phoneNo = phoneNo;
}

public String getPhoneNo()
{
return phoneNo;
}

public double getWorkExpYear()
{
return workExpYear;
}

public String getAddress()
{
return address;
}

public String getGender()
{
return Gender;
}

public int getAccountNumber()
{
return accountNumber;
}

public String getAccountHolder()
{
return accountHolder;
}

public int getCreditScore()
{
return creditScore;
}

public int getAge()
{
return age;
}

public double getSalary()
{
return salary;
}

public String getOccupation()
{
return occupation;
}

public String getNationality()
{
return nationality;
}

public double getBalance()
{
return balance;
}

public void setBalance(double balance)
{
this.balance=balance;
}

public void deposit(double amount)
{
balance=balance+amount;
}

public boolean withdraw(double amount)
{
if(balance>=amount)
{
balance=balance-amount;
return true;
}
return false;
}
}