/**
 * Program Name:StudentLoanApp.java
 * Purpose: prototype GUI student loan calculator app tosee how modifying the
 * 			repayment shcedules would affect the loan cost in terms of amount of interest 
 * Code: Francisco Garcia 0892633
 * Date: Apr 8, 2020
 */

import java.awt.*;
import java.text.DecimalFormat;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import javax.swing.*;

public class StudentLoanApp extends JFrame 
{
	private JComboBox prov;
	private JTextField cslMonthlyPayment, oslMonthlyPayment, combinedMonthtly, total, originalAmount, interestAmount, ID,firstN, middleN, lastN, stNum, stName, aptNum, city, zip, csl, osl, province,
					ID2,firstN2, middleN2, lastN2, stNum2, stName2, aptNum2, city2, zip2, csl2, osl2;
	private JLabel records, cslMonthlyPaymentLabel, oslMonthlyPaymentLabel, combinedMonthtlyLabel, totalLabel, originalAmountLabel, interestAmountLabel, amortizationLabel, interestRate,title,IDLabel, name, middle, last, stNumLabel, stNameLabel, aptNumLabel, cityLabel, provLabel, zipLabel, cslLabel, oslLabel,
					IDLabel2, name2, middle2, last2, stNumLabel2, stNameLabel2, aptNumLabel2, cityLabel2, provLabel2, zipLabel2, cslLabel2, oslLabel2;
	private JPanel paymentsPanel, inputPanel, calcPanel, bigContainer, outputPanel, buttonPanel;
	private JButton inButton, calcButton, submitButton, previousButton, nextButton, calculateButton;
	private ArrayList<Student> studentList = new ArrayList <Student>();
	private int i;
	private JSpinner interestRateSpin, amortizationSpinner;
	private JFrame inputFrame, calcFrame;
	
	//This class creates the Jframe for the input form
	public class InputForm extends JFrame{
		
		InputForm(){
			super("New Student Input Form");
			this.setLayout(new FlowLayout());
			this.setSize(400,400);
			
			buildInputPanel();
			submitButton = new JButton("Submit");
			ActionListener submitObj = new SubmitButtonListener();
			submitButton.addActionListener(submitObj);
			this.add(inputPanel);
			this.add(submitButton);
			this.setLocationRelativeTo(null);
			//this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			this.setVisible(true);
	
		}
		

		/**
		 * Method Name: buildInputPanel()
		 * Purpose: a public class method that builds a JPanel containing all the fields for the input form
		 * Accepts: nothing
		 * Returns: nothing
		 * Date: April 8/2020
		 */
		public void buildInputPanel()  {
			
			inputPanel = new JPanel();
			inputPanel.setLayout(new GridLayout(0,2));
			
			//all labels
			IDLabel = new JLabel("Student ID Number: ");
			name = new JLabel("First Name: ");
			middle = new JLabel("Middle Name: ");
			last = new JLabel("Last Name: ");
			stNumLabel = new JLabel("Street Number: ");
			stNameLabel = new JLabel("Street Name: ");
			aptNumLabel = new JLabel("Apartment Number: ");
			cityLabel = new JLabel("City: ");
			provLabel = new JLabel("Province: ");
			zipLabel = new JLabel("Postal Code: ");
			cslLabel = new JLabel("CSL: ");
			oslLabel = new JLabel("OSL: ");
			
			//all textfields
			ID = new JTextField();
			firstN = new JTextField();
			middleN = new JTextField();
			lastN= new JTextField();
			stNum = new JTextField();
			stName = new JTextField();
			aptNum = new JTextField();
			city = new JTextField();
			String provinces[] = {"Alberta","British Columbia", "Manitoba", "New Brunswick", "Newfoundland and Labrador","Nova Scotia", "Ontario", "Prince Edward"};
			prov = new JComboBox(provinces);
			zip = new JTextField();
			csl = new JTextField();
			osl = new JTextField();
			
			//adding all labels and textfields to panel
			inputPanel.add(IDLabel);
			inputPanel.add(ID);
			inputPanel.add(name);
			inputPanel.add(firstN);
			inputPanel.add(middle);
			inputPanel.add(middleN);
			inputPanel.add(last);
			inputPanel.add(lastN);
			inputPanel.add(stNumLabel);
			inputPanel.add(stNum);
			inputPanel.add(stNameLabel);
			inputPanel.add(stName);
			inputPanel.add(aptNumLabel);
			inputPanel.add(aptNum);
			inputPanel.add(cityLabel);
			inputPanel.add(city);
			inputPanel.add(provLabel);
			inputPanel.add(prov);
			inputPanel.add(zipLabel);
			inputPanel.add(zip);
			inputPanel.add(cslLabel);
			inputPanel.add(csl);
			inputPanel.add(oslLabel);
			inputPanel.add(osl);
		}
		
		
	}//end of inputForm class
	
	//class created to build the JFrame for the calculations form.
	public class CalcForm extends JFrame
	{
		CalcForm(){
			super("Calculation Form");
			this.setLayout(new FlowLayout());
			this.setSize(715,340);
			
			buildCalcPanel();
			this.add(bigContainer);
			this.setLocationRelativeTo(null);
			//this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			this.setVisible(true);
			
		}
		

		/**
		 * Method Name: buildCalcPanel()
		 * Purpose: build the canculations panel containing all the fields to display all the records information
		 * 			and also the buttons to navigate the records and make the calculations
		 * Accepts: nothing
		 * Returns: nothing
		 * Date: April 8/2020
		 */
		public void buildCalcPanel() {
			bigContainer = new JPanel();
			bigContainer.setLayout(new GridLayout(0,2));
			
			calcPanel = new JPanel();
			calcPanel.setLayout(new GridLayout(0,2));
			
			//all labels
			IDLabel2 = new JLabel("Student ID Number: ");
			name2 = new JLabel("First Name: ");
			middle2 = new JLabel("Middle Name: ");
			last2 = new JLabel("Last Name: ");
			stNumLabel2 = new JLabel("Street Number: ");
			stNameLabel2 = new JLabel("Street Name: ");
			aptNumLabel2 = new JLabel("Apartment Number: ");
			cityLabel2 = new JLabel("City: ");
			provLabel2 = new JLabel("Province: ");
			zipLabel2 = new JLabel("Postal Code: ");
			cslLabel2 = new JLabel("CSL: ");
			oslLabel2 = new JLabel("OSL: ");
			interestRate= new JLabel("Interest Rate: ");
			amortizationLabel= new JLabel("Amortization period: ");
			
			//all textfields
			ID2 = new JTextField(studentList.get(i).getStudentID());
			ID2.setEditable(false);
			firstN2 = new JTextField(studentList.get(i).getFirstName());
			firstN2.setEditable(false);
			middleN2 = new JTextField(studentList.get(i).getMiddleName());
			middleN2.setEditable(false);
			lastN2= new JTextField(studentList.get(i).getSurname());
			lastN2.setEditable(false);
			stNum2 = new JTextField(studentList.get(i).getStreetNumber());
			stNum2.setEditable(false);
			stName2 = new JTextField(studentList.get(i).getStreetName());
			stName2.setEditable(false);
			aptNum2 = new JTextField(studentList.get(i).getAptNumber());
			aptNum2.setEditable(false);
			city2 = new JTextField(studentList.get(i).getCity());
			city2.setEditable(false);
			province = new JTextField(studentList.get(i).getProvince());
			province.setEditable(false);
			zip2 = new JTextField(studentList.get(i).getPostalCode());
			zip2.setEditable(false);
			csl2 = new JTextField("$"+Double.toString(studentList.get(i).getCslLoanAmount()));
			csl2.setEditable(false);
			osl2 = new JTextField("$"+Double.toString(studentList.get(i).getOslLoanAmount()));
			osl2.setEditable(false);
			
			//creating spinners
			interestRateSpin = new JSpinner(new SpinnerNumberModel(1,1,5,0.25));
			JLabel percentage= new JLabel(" %");
			JPanel interestPanel = new JPanel();
			interestPanel.setLayout(new GridLayout(0,2));
			interestPanel.add(interestRateSpin);
			interestPanel.add(percentage);

			
			JPanel amortizationPanel = new JPanel();
			amortizationPanel.setLayout(new GridLayout(0,2));
			amortizationSpinner = new JSpinner(new SpinnerNumberModel(12,12,60,12));
			JLabel inMonths = new JLabel(" Months");
			
			amortizationPanel.add(amortizationSpinner);
			amortizationPanel.add(inMonths);
			
			//Creating buttons
			previousButton = new JButton("Previous");
			ActionListener prevBtnObj = new PreviousButtonListener();
			previousButton.addActionListener(prevBtnObj);
			
			nextButton = new JButton("Next");
			ActionListener nextBtnObj = new NextButtonListener();
			nextButton.addActionListener(nextBtnObj);
			
			calculateButton = new JButton("Calculate");
			ActionListener calculationBtnObj = new CalculationButtonListener();
			calculateButton.addActionListener(calculationBtnObj);
			
			//adding all labels and textfields to panel
			calcPanel.add(IDLabel2);
			calcPanel.add(ID2);
			calcPanel.add(name2);
			calcPanel.add(firstN2);
			calcPanel.add(middle2);
			calcPanel.add(middleN2);
			calcPanel.add(last2);
			calcPanel.add(lastN2);
			calcPanel.add(stNumLabel2);
			calcPanel.add(stNum2);
			calcPanel.add(stNameLabel2);
			calcPanel.add(stName2);
			calcPanel.add(aptNumLabel2);
			calcPanel.add(aptNum2);
			calcPanel.add(cityLabel2);
			calcPanel.add(city2);
			calcPanel.add(provLabel2);
			calcPanel.add(province);
			calcPanel.add(zipLabel2);
			calcPanel.add(zip2);
			calcPanel.add(cslLabel2);
			calcPanel.add(csl2);
			calcPanel.add(oslLabel2);
			calcPanel.add(osl2);
			calcPanel.add(interestRate);
			calcPanel.add(interestPanel);
			calcPanel.add(amortizationLabel);
			calcPanel.add(amortizationPanel);
			
			
			
			outputPanel = new JPanel();
			outputPanel.setLayout(new BorderLayout());
			
			buttonPanel = new JPanel();
			buttonPanel.setLayout(new GridLayout(0,3));
			
			paymentsPanel = new JPanel();
			paymentsPanel.setLayout(new GridLayout(6,2));
			
			buttonPanel.add(previousButton);
			buttonPanel.add(nextButton);
			buttonPanel.add(calculateButton);
			
			//labels for payments panel
			cslMonthlyPaymentLabel = new JLabel("Monthly Payment for CSL: ");
			oslMonthlyPaymentLabel = new JLabel("Montly Payment for OSL: ");
			combinedMonthtlyLabel= new JLabel("Combined Monthly Payment: ");
			totalLabel= new JLabel("Total Amount to be Paid: ");
			originalAmountLabel= new JLabel("Original Amount: ");
			interestAmountLabel= new JLabel("Total of Interests: ");
			
			
			//textfields for payments panel
			cslMonthlyPayment = new JTextField();
			oslMonthlyPayment= new JTextField();
			combinedMonthtly= new JTextField();
			total= new JTextField();
			originalAmount= new JTextField();
			interestAmount= new JTextField();
			
			paymentsPanel.add(cslMonthlyPaymentLabel);
			paymentsPanel.add(cslMonthlyPayment);
			paymentsPanel.add(oslMonthlyPaymentLabel);
			paymentsPanel.add(oslMonthlyPayment);
			paymentsPanel.add(combinedMonthtlyLabel);
			paymentsPanel.add(combinedMonthtly);
			paymentsPanel.add(totalLabel);
			paymentsPanel.add(total);
			paymentsPanel.add(originalAmountLabel);
			paymentsPanel.add(originalAmount);
			paymentsPanel.add(interestAmountLabel);
			paymentsPanel.add(interestAmount);
			
			
			records = new JLabel("Viewing record "+(i+1)+" of "+studentList.size());
			outputPanel.add(buttonPanel, BorderLayout.NORTH);
			outputPanel.add(paymentsPanel, BorderLayout.CENTER);
			outputPanel.add(records, BorderLayout.SOUTH);
			
			
			
			
			
			bigContainer.add(calcPanel);
			bigContainer.add(outputPanel);
			
			
		}
		
	}
	
	StudentLoanApp(){
		super("Francisco Garcia 0896233");
		this.setLayout(new BorderLayout());
		this.setSize(450,120);
		i=0;
		//creating title label and centering it to the top position
		title= new JLabel("This is Francisco's Student Loan Calculator");
		title.setHorizontalAlignment(JLabel.CENTER);

		//Creating panel to hold buttons
		JPanel buttonPanel = new JPanel();
		//creating buttons
		inButton = new JButton("Input Form");
		ActionListener inputObj = new InputButtonListener();
		inButton.addActionListener(inputObj);
		
		calcButton = new JButton("Calculation Form");
		ActionListener calcObj = new CalcButtonListener();
		calcButton.addActionListener(calcObj);
		buttonPanel.add(inButton);
		buttonPanel.add(calcButton);
		
		add(title, BorderLayout.NORTH);
		add(buttonPanel, BorderLayout.CENTER);
		
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	
	//class for the calculation button listener, it displays the calculations according to each record
	public class CalculationButtonListener implements ActionListener, F_G_LoanPayable{
		
		DecimalFormat myFormatter = new DecimalFormat("0.00");
		
		public void actionPerformed(ActionEvent e)
		{
			double cslAmount=0;
			double oslAmount=0;
			
			try {
				cslAmount = studentList.get(i).getCslLoanAmount();
			 if(cslAmount<0) {
				 cslAmount = Math.abs(cslAmount);
				 throw new F_G_NegativeValueException(cslAmount);
			 }
		
			}
			catch (F_G_NegativeValueException f) {
				JOptionPane.showMessageDialog(null, "Negative amount in CSL. changing it to positive.");		
			}
			
			try {
				oslAmount = studentList.get(i).getOslLoanAmount();
			 if(oslAmount<0) {
				 oslAmount= Math.abs(oslAmount);
				 throw new F_G_NegativeValueException(oslAmount);
			 }
			}
			catch (F_G_NegativeValueException g) {
				JOptionPane.showMessageDialog(null, "Negative amount in OSL. changing it to positive.");		
			}
			int months =(int)amortizationSpinner.getValue();
			double annualInterest= (double)interestRateSpin.getValue();
			
			cslMonthlyPayment.setText("$"+(myFormatter.format(calculateLoanPayment(cslAmount, annualInterest+2.5, months))));
			oslMonthlyPayment.setText("$"+(myFormatter.format(calculateLoanPayment(oslAmount, annualInterest+1.0, months))));
			combinedMonthtly.setText("$"+(myFormatter.format(calculateLoanPayment(cslAmount, annualInterest+2.5, months)+calculateLoanPayment(oslAmount, annualInterest+1.0, months))));
			total.setText("$"+((myFormatter.format(months*(calculateLoanPayment(cslAmount, annualInterest+2.5, months)+calculateLoanPayment(oslAmount, annualInterest+1.0, months))))));
			originalAmount.setText("$"+(cslAmount+oslAmount));
			interestAmount.setText("$"+((myFormatter.format(months*(calculateLoanPayment(cslAmount, annualInterest+2.5, months)+calculateLoanPayment(oslAmount, annualInterest+1.0, months))-cslAmount-oslAmount))));
			
		}

		//Implementation of the abstract method from the implemented interface
		//it received an amount owed as a double, the annual interest rate as a double and the duration of months to pay it back as an int.
		//it returns the monthly payment as a double
		public double calculateLoanPayment(double amount, double annualInterestRate, int amortizationPeriod)
		{
			double i = annualInterestRate*ANNUAL_RATE_TO_MONTHLY_RATE;
			
			return amount*i*Math.pow((1+i), amortizationPeriod)/(Math.pow((1+i), amortizationPeriod)-1) ;
		}
		
	}
	
	//Action listener for the input button to create the input Frame 
	public class InputButtonListener implements ActionListener {

		public void actionPerformed(ActionEvent e)
		{
		inputFrame =new InputForm();
			
		}
		
	}
	
	// Action listener for the calcform button to create the calculations form Frame
	public class CalcButtonListener implements ActionListener{

		public void actionPerformed(ActionEvent e)
		{
			calcFrame =new CalcForm();
			i=0;
			
		}		
		
	}
	
	//Action listener for the button submit, which will validate the information submitted by the user and then record it as a Student object
	//and add it to the ArrayList
	public class SubmitButtonListener  implements ActionListener {
		boolean isNumber=true;
		boolean isInteger=true;
		public void actionPerformed(ActionEvent e) 
		{
			if(ID.getText().isBlank()||lastN.getText().isBlank()||middleN.getText().isBlank()||firstN.getText().isBlank()||aptNum.getText().isBlank()||stNum.getText().isBlank()|| stName.getText().isBlank()|| city.getText().isBlank()|| prov.getSelectedItem().toString().isBlank()|| zip.getText().isBlank()|| csl.getText().isBlank()||osl.getText().isBlank()) {
				JOptionPane.showMessageDialog(null, "All Fields Are Requiered.");
			}
			else {
				try {
					Double.parseDouble(csl.getText());
				}
				catch(NumberFormatException r) {
					JOptionPane.showMessageDialog(null, "Not a valid number in the CSL field.");	
					return;
				}
				try {
					Double.parseDouble(osl.getText());
				}
				catch(NumberFormatException f) {
					JOptionPane.showMessageDialog(null, "Not a valid number in the OSL field.");
					return;
				}
					Student myStudent = new Student("0"+ID.getText(), lastN.getText(), middleN.getText(), firstN.getText(), aptNum.getText(),stNum.getText(), stName.getText(), city.getText(), prov.getSelectedItem().toString(), zip.getText(), Double.parseDouble(csl.getText()), Double.parseDouble(osl.getText()));
							studentList.add(myStudent);
							JOptionPane.showMessageDialog(null, "Submitted Succesfully");
							inputFrame.dispose();
		}			
	}
}
	//Action listener to navigate the records in a left direction
	public class PreviousButtonListener implements ActionListener{

		public void actionPerformed(ActionEvent e)
		{
			if(i>0) {
				i--;
				ID2.setText(studentList.get(i).getStudentID());
				ID2.setEditable(false);
				firstN2.setText(studentList.get(i).getFirstName());
				firstN2.setEditable(false);
				middleN2.setText(studentList.get(i).getMiddleName());
				middleN2.setEditable(false);
				lastN2.setText(studentList.get(i).getSurname());
				lastN2.setEditable(false);
				stNum2.setText(studentList.get(i).getStreetNumber());
				stNum2.setEditable(false);
				stName2.setText(studentList.get(i).getStreetName());
				stName2.setEditable(false);
				aptNum2.setText(studentList.get(i).getAptNumber());
				aptNum2.setEditable(false);
				city2.setText(studentList.get(i).getCity());
				city2.setEditable(false);
				province.setText(studentList.get(i).getProvince());
				province.setEditable(false);
				zip2.setText(studentList.get(i).getPostalCode());
				zip2.setEditable(false);
				csl2.setText("$"+Double.toString(studentList.get(i).getCslLoanAmount()));
				csl2.setEditable(false);
				osl2.setText("$"+Double.toString(studentList.get(i).getOslLoanAmount()));
				osl2.setEditable(false);

				cslMonthlyPayment.setText("");
				oslMonthlyPayment.setText("");
				combinedMonthtly.setText("");
				total.setText("");
				originalAmount.setText("");
				interestAmount.setText("");
				
				records.setText("Viewing record "+(i+1)+" of "+studentList.size());
			}
			
		}
		
		
	}
	
	//Action listener to navigate the records in a right direction
	public class NextButtonListener implements ActionListener{

		public void actionPerformed(ActionEvent e)
		{
			if(studentList.size()-1>i) {
				i++;
				ID2.setText(studentList.get(i).getStudentID());
				ID2.setEditable(false);
				firstN2.setText(studentList.get(i).getFirstName());
				firstN2.setEditable(false);
				middleN2.setText(studentList.get(i).getMiddleName());
				middleN2.setEditable(false);
				lastN2.setText(studentList.get(i).getSurname());
				lastN2.setEditable(false);
				stNum2.setText(studentList.get(i).getStreetNumber());
				stNum2.setEditable(false);
				stName2.setText(studentList.get(i).getStreetName());
				stName2.setEditable(false);
				aptNum2.setText(studentList.get(i).getAptNumber());
				aptNum2.setEditable(false);
				city2.setText(studentList.get(i).getCity());
				city2.setEditable(false);
				province.setText(studentList.get(i).getProvince());
				province.setEditable(false);
				zip2.setText(studentList.get(i).getPostalCode());
				zip2.setEditable(false);
				csl2.setText("$"+Double.toString(studentList.get(i).getCslLoanAmount()));
				csl2.setEditable(false);
				osl2.setText("$"+Double.toString(studentList.get(i).getOslLoanAmount()));
				osl2.setEditable(false);
				
				//deleting the calcs fields
				cslMonthlyPayment.setText("");
				oslMonthlyPayment.setText("");
				combinedMonthtly.setText("");
				total.setText("");
				originalAmount.setText("");
				interestAmount.setText("");
				
				records.setText("Viewing record "+(i+1)+" of "+studentList.size());

			}
			
		}		
		
	}

	public static void main(String[]args) {
		new StudentLoanApp();
		
	}

}

//end class