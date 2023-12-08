package bankFunctions;
import User.Account;

public class PersonalLoan extends Loan
{
public boolean checkEligibility(Account account, double loanAmount)
{
if (account.getSalary()>=20000)
{
return account.getBalance() >= loanAmount * 0.5;
}
else
{
return false;
}
}

public double calcMonthlyInstallments(double loanAmount, int loanTerm) 
{
double interestRate = 0.10;
double loanTerm_1 = loanTerm/12;
double payable_amount = loanAmount + ((loanAmount*loanTerm_1*interestRate)/100);
double monthlyPayment = payable_amount/loanTerm;
return monthlyPayment;
}
}
