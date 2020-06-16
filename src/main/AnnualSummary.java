package main;

public class AnnualSummary {
	private static float totalIncome;
	private static float totalExpense;
	private static float balance;

	public AnnualSummary () {}
	
	private float getRentSum(RentalIncomeReport income) {
		float rentSum = 0.0f;
		
		for (var iter = income.getRecords(); iter.hasNext();) {
			rentSum += iter.next().getSumOfRents();
		} //end for
		
		return rentSum;
	} // End of the getRentSum method


	private float getExpenseSum(ExpensePaymentReport expense) {
		float expenseSum = 0.0f;
		
		for (var iter = expense.getRecords(); iter.hasNext();) {
			expenseSum += iter.next().getExpenseAmount();
		} //end for
		
		return expenseSum;
	} // End of the getExpenseSum method


	public void displayIncomeReport(RentalIncomeReport income) {
		float total = this.getRentSum(income);
	
		totalIncome = total;
		
		System.out.println("\nIncome\nRent " + totalIncome);
	}	


	public void displayExpenseReport(ExpensePaymentReport expense) {
		float total = this.getExpenseSum(expense);
		//float insurance = 0;
		//float utilities = 0;
		
		String expenseCategories = expense.displayExpenseCategories();
		
		//for(ExpenseRecord e : expense.getRecords()) {
		//	total += e.getExpenseAmount();
			
		//	if (e.getCategory().equalsIgnoreCase("insurance"))
		//		insurance += e.getExpenseAmount();
		//	else if (e.getCategory().equalsIgnoreCase("utilities"))
		//		utilities += e.getExpenseAmount();
		//}
		
		totalExpense = total;
		System.out.println("\nExpense\n" + expenseCategories);
				//"\nInsurance " + insurance + 
				//"\nUtilities " + utilities);
	}


	public void displayBalance(RentalIncomeReport income, ExpensePaymentReport expense) {
		this.displayIncomeReport(income);
		this.displayExpenseReport(expense);
		
		balance = totalIncome - totalExpense;
		System.out.println("\nBalance " + balance);
	}

} // End of the AnnualSumarry class.
