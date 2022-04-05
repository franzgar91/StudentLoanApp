/**
 * Program Name:F_G_LoanPayable.java
 * Purpose: Exception class created for when the user try to make calculations with
 * 			a negative value in either of the loan fields.
 * Code: Francisco Garcia 0892633
 * Date: Apr 8, 2020
 */


public class F_G_NegativeValueException extends Exception
{
	F_G_NegativeValueException(double amount){
		super("Invalid amount. "+amount+" is negative");
	}
}
//end class