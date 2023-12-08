package bankFunctions;
import User.Account;

public abstract class Loan
{
public abstract boolean checkEligibility(Account account, double loanAmount);
public abstract double calcMonthlyInstallments(double loanAmount, int loanTerm);
}