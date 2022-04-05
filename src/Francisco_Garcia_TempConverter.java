import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

import javax.swing.*;



/**
 * Program Name:Francisco_Garcia_TempConverter.java
 * Purpose: temperature convertes between fahrenheit, celsius and kelvin for the final Java exam	
 * Code: Francisco Garcia 0892633
 * Date: Apr 21, 2020
 */

public class Francisco_Garcia_TempConverter extends JFrame
{
	JLabel title, fahrTempLabel, celsTempLabel, kelvTempLabel ;
	JPanel inputPanel, buttonPanel, titlePanel;
	JTextField fahr, cels, kelv;
	JButton clear, calc;
	GridLayout inputGrid, calcGrid;
	
	Francisco_Garcia_TempConverter(){
		
		super("Francisco Garcia's Temperature Converter");
		this.setLayout(new BorderLayout());
		this.setSize(410, 200);
		
		//creating label for title and it's container to change the background color
		title = new JLabel("Enter a temperature in any field and then press the Calculate button");
		title.setForeground(Color.WHITE);
		titlePanel = new JPanel();
		titlePanel.setBackground(Color.darkGray);
		titlePanel.add(title);

		//creating input panel
		fahrTempLabel= new JLabel("Fahrenheit Temp:");
		celsTempLabel= new JLabel("Celsius Temp:"); 
		kelvTempLabel= new JLabel("Kelvin Temp:");
		fahr = new JTextField("");
		cels = new JTextField("");
		kelv= new JTextField("");
		inputGrid= new GridLayout(3,2);
		inputGrid.setVgap(20);
		inputPanel = new JPanel(inputGrid);
		
		//adding items to the input panel
		inputPanel.add(fahrTempLabel);
		inputPanel.add(fahr);
		inputPanel.add(celsTempLabel);
		inputPanel.add(cels);
		inputPanel.add(kelvTempLabel);
		inputPanel.add(kelv);
		
		//creating button panel
		buttonPanel = new JPanel();
		calcGrid = new GridLayout(2,0);
		calcGrid.setVgap(25);
		buttonPanel.setLayout(calcGrid);
		buttonPanel.setBackground(Color.cyan);
		clear = new JButton("Clear Fields");
		calc = new JButton("Calculate");
		ButtonListener myListener = new ButtonListener();
		clear.addActionListener(myListener);
		calc.addActionListener(myListener);
		
		//adding buttons to button panel
		buttonPanel.add(clear);
		buttonPanel.add(calc);
		
		//adding all panels to the frame
		this.add(titlePanel,BorderLayout.NORTH);
		this.add(inputPanel, BorderLayout.CENTER);
		this.add(buttonPanel, BorderLayout.EAST);
		
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		
	}
	
	private class ButtonListener implements ActionListener, Francisco_Garcia_TempConvertable{

		public void actionPerformed(ActionEvent e)
		{
			DecimalFormat myFormatter = new DecimalFormat("0.00");
			double fahrenheit;
			double celsius;
			double kelvin;
			
			//if it comes from the clear button we reset all textFields
			if(e.getSource()==clear) {
				fahr.setText("");
				cels.setText("");
				kelv.setText("");
			}
			
			//if it comes from the calculate button 
			//we check what textfield has data and also check if it's greater than or equal to its equivalent of absolute zero
			//if it is, then we proceed to call the methods to calculate the values and put them in the textfields, if it's not
			//we alert the user, we reset fields and request the focus of the cursor. 
			else if(e.getSource()==calc) {
				
				    if(fahr.getText().length()>0) {
				    	
				    	fahrenheit=Double.parseDouble(fahr.getText());
				    	if(fahrenheit>=ABS_ZERO_F) {
					     cels.setText(myFormatter.format(convertFtoC(fahrenheit)));
					     kelv.setText(myFormatter.format(convertFtoK(fahrenheit)));
				    	}
				    	else {
				    		JOptionPane.showMessageDialog(null, "Invalid value was entered!! \n"+fahr.getText()+" is less than absolute zero");
				    		fahr.setText("");
				    		fahr.requestFocus();
				    	}
				    }
				    else if(cels.getText().length()>0) {
				    	celsius=Double.parseDouble(cels.getText());
				    	if(celsius>=ABS_ZERO_C) {
					     fahr.setText(myFormatter.format(convertCtoF(celsius)));
					     kelv.setText(myFormatter.format(convertCtoK(celsius)));
				    	}
				    	else {
				    		JOptionPane.showMessageDialog(null, "Invalid value was entered!! \n"+cels.getText()+" is less than absolute zero");
				    		cels.setText("");
				    		cels.requestFocus();
				    	}
				    }
				    else {
				    	
				    	kelvin=Double.parseDouble(kelv.getText());
				    	if(kelvin>=ABS_ZERO_K) {
					     cels.setText(myFormatter.format(convertKtoC(kelvin)));
					     fahr.setText(myFormatter.format(convertKtoF(kelvin)));
				    	}
				    	else {
				    		JOptionPane.showMessageDialog(null, "Invalid value was entered!! \n"+kelv.getText()+" is less than absolute zero");
				    		fahr.setText("");
				    		fahr.requestFocus();
				    	}
				    	
				    	
				    }
				    
				
			}
			
		}

		
		public double convertFtoC(double fTemp)
		{
			
			return (5.0/9.0)*(fTemp-32);
		}

		
		public double convertCtoF(double cTemp)
		{
			return (9.0/5.0*cTemp)+32;
		}

		public double convertCtoK(double cTemp)
		{
			return cTemp+273.15;
		}

		
		public double convertKtoC(double kTemp)
		{
			return kTemp-273.15;
		}

		public double convertFtoK(double fTemp)
		{
			
			return convertCtoK(convertFtoC(fTemp));
		}

		public double convertKtoF(double kTemp)
		{
			return convertCtoF(convertKtoC(kTemp));
		}
		
	}
	
	public static void main(String[]args) {
	new	Francisco_Garcia_TempConverter();
	}

}
//end class