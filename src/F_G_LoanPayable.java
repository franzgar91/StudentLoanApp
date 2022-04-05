/**
 * Program Name:F_G_LoanPayable.java
 * Purpose: Interface holding the constant for the annual rate to monthly rate	
 * 			and the abstract method to calculate monthly payment according to the loan
 * Code: Francisco Garcia 0892633
 * Date: Apr 8, 2020
 */

public interface F_G_LoanPayable
{
	double ANNUAL_RATE_TO_MONTHLY_RATE=(1.0/1200);
	
	public double calculateLoanPayment(double amount, double annualInterestRate, int amortizationPeriod) throws F_G_NegativeValueException;
}
//end class