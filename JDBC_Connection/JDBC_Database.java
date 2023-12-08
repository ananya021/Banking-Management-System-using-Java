package JDBC_Connection;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.io.IOException;

public class JDBC_Database
{
public static Connection con;
public static Statement stmt;
public static PreparedStatement pstmt;
public static final String DB_URL = "jdbc:mysql://localhost:3306/BankingSystem";
public static final String USER = "root";
public static final String PWD="root";

//1. Load and register the Driver
public static void loadDriver()
{
try{
Class.forName("com.mysql.cj.jdbc.Driver");
System.out.println("Driver Loaded Successfully.");
}
catch(ClassNotFoundException e)
{
System.out.println(e);
}
}

//2. create connection
public static Connection createConnection()
{
try 
{
con = DriverManager.getConnection(DB_URL, USER, PWD);
System.out.println("Connection Established.");
} 
catch (SQLException e) 
{
System.out.println(e);
}
return con;
}

//3. create statement
public static Statement createStatement()
{
try{
stmt = con.createStatement();
System.out.println("Statement created.");
}
catch(SQLException e)
{
System.out.println(e);
}
return stmt;
}

//4. create table
public static void createTable_accounts()
{
String sqlquery = "create table if not exists Accounts(accountNumber int auto_increment, Name varchar(100), age int, nationality varchar(30), occupation varchar(30), salary decimal(10,2), creditScore int, balance double, address varchar(225), Gender varchar(1), phoneNo varchar(20), workExpYear double, primary key(accountNumber))";
try
{
stmt.executeUpdate(sqlquery);
}
catch(SQLException e)
{
System.out.println(e);
}
}

//5. insert data
public static void insertData_accounts(String accountHolder, double balance,int age,String nationality,String occupation,double salary,int creditScore,String address, String Gender,String phoneNo,double workExpYear)
{
String sqlquery = "insert into Accounts (Name, age, nationality, occupation, salary, creditScore, balance, address, Gender, phoneNo, workExpYear) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
try
{
pstmt = con.prepareStatement(sqlquery);
pstmt.setString(1, accountHolder);
pstmt.setInt(2, age);
pstmt.setString(3, nationality);
pstmt.setString(4, occupation);
pstmt.setDouble(5, salary);
pstmt.setInt(6, creditScore);
pstmt.setDouble(7, balance);
pstmt.setString(8, address);
pstmt.setString(9, Gender);
pstmt.setString(10, phoneNo);
pstmt.setDouble(11, workExpYear);
int no_affectedRows = pstmt.executeUpdate();
}
catch(SQLException e)
{
System.out.println(e);
}
}

//6. Extract data
public static void extractData_AllUsers() 
{
String sqlquery = "select * from Accounts";
try 
{
ResultSet rs = stmt.executeQuery(sqlquery);

System.out.println("Details of all users are as follows: ");
System.out.println();
        
while (rs.next()) 
{
System.out.println("Account Number: " + rs.getInt("accountNumber"));
System.out.println("Name: " + rs.getString("Name"));
System.out.println("Age: " + rs.getInt("age"));
System.out.println("Nationality: " + rs.getString("Nationality"));
System.out.println("Occupation: " + rs.getString("occupation"));
System.out.println("Salary: " + rs.getDouble("salary"));
System.out.println("Credit Score: " + rs.getInt("creditScore"));
System.out.println("Balance: " + rs.getDouble("balance"));
System.out.println("Address: " + rs.getString("address"));
System.out.println("Gender: " + rs.getString("Gender"));
System.out.println("Phone Number: " + rs.getString("phoneNo"));
System.out.println("workExpYear: " + rs.getInt("workExpYear"));
System.out.println("--------------------------------");
}
} 
catch (SQLException e)
{
System.out.println(e);
}
}

public static void extractData_OneUser(int accountNumber) 
{
String sqlquery = "select * from Accounts where accountNumber = ?";
try 
{
pstmt = con.prepareStatement(sqlquery);
pstmt.setInt(1, accountNumber);
ResultSet rs = pstmt.executeQuery();

System.out.println("Details of user with account number " + accountNumber + " are as follows: ");
System.out.println();
        
while (rs.next()) 
{
System.out.println("Account Number: " + rs.getInt("accountNumber"));
System.out.println("Name: " + rs.getString("Name"));
System.out.println("Age: " + rs.getInt("age"));
System.out.println("Nationality: " + rs.getString("Nationality"));
System.out.println("Occupation: " + rs.getString("occupation"));
System.out.println("Salary: " + rs.getDouble("salary"));
System.out.println("Credit Score: " + rs.getInt("creditScore"));
System.out.println("Balance: " + rs.getDouble("balance"));
System.out.println("Address: " + rs.getString("address"));
System.out.println("Gender: " + rs.getString("Gender"));
System.out.println("Phone Number: " + rs.getString("phoneNo"));
System.out.println("workExpYear: " + rs.getInt("workExpYear"));
System.out.println("--------------------------------");
}
} 
catch (SQLException e) 
{
System.out.println(e);
}
}

//7. update data
public static void updateBalanceAfterDeposit(int accountNumber, double depositAmount) 
{
String sqlQuery = "update Accounts set balance = balance + ? where accountNumber = ?";
try 
{
pstmt = con.prepareStatement(sqlQuery);
pstmt.setDouble(1, depositAmount);
pstmt.setInt(2, accountNumber);
int noAffectedRows = pstmt.executeUpdate();
if (noAffectedRows > 0) 
{
System.out.println("Deposit successful. Balance updated.");
} else 
{
System.out.println("Deposit failed. Account not found.");
}
} 
catch (SQLException e) 
{
System.out.println(e);
}
}

public static void updateBalanceAfterWithdrawal(int accountNumber, double withdrawalAmount) 
{
String sqlQuery = "update Accounts set balance = balance - ? where accountNumber = ?";
try 
{
pstmt = con.prepareStatement(sqlQuery);
pstmt.setDouble(1, withdrawalAmount);
pstmt.setInt(2, accountNumber);
int noAffectedRows = pstmt.executeUpdate();
if (noAffectedRows > 0) 
{
System.out.println("Withdrawal successful. Balance updated.");
} 
else 
{
System.out.println("Withdrawal failed. Account not found or insufficient balance.");
}
} 
catch (SQLException e) 
{
System.out.println(e);
}
}

}