package main;

import java.util.Map;
import java.util.TreeMap;

public class AnnualSummary {
	private static float totalIncome;
	private static float totalExpense;
	private static float balance;

	public AnnualSummary () {}
	
	public void displayIncomeReport(RentalIncomeReport iReport) {
		float total = 0;
		
		for(IncomeRecord i : iReport.getRecords()) {
			total += i.getRentAmount();
		}
				
		totalIncome = total; 
		System.out.println("\nIncome\nRent " + totalIncome);
	}	
	
	public void displayExpenseReport(ExpensePaymentReport eReport) {
		float total = 0;
		float insurance = 0;
		float utilities = 0;
		
		for(ExpenseRecord e : eReport.getRecords()) {
			total += e.getExpenseAmount();
			
			if (e.getCategory().equalsIgnoreCase("insurance"))
				insurance += e.getExpenseAmount();
			else if (e.getCategory().equalsIgnoreCase("utilities"))
				utilities += e.getExpenseAmount();
		}
		
		totalExpense = total;
		System.out.println("\nExpense" +
				"\nInsurance " + insurance + 
				"\nUtilities " + utilities);
	}
	
	public void displayBalance() {
		balance = totalIncome - totalExpense;
		System.out.println("\nBalance " + balance);
	}
}
