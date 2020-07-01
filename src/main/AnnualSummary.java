package main;

import java.util.Iterator;

/**
 * Generates the year to date annual summary report.
 */
public class AnnualSummary {
    private static float totalIncome = 0.0f;
    private static float totalExpense = 0.0f;
    private static float balance = 0.0f;

    public String displayBalance(RentalIncomeReport income, ExpensePaymentReport expense) {
	String newline = System.lineSeparator();
	StringBuilder sb = new StringBuilder();

	sb.append(this.displayIncomeReport(income));
	sb.append(newline + "--------------------" + newline);
	sb.append(this.displayExpenseReport(expense));
	sb.append("--------------------" + newline);
	sb.append("Balance: $");

	balance = totalIncome - totalExpense;
	sb.append(String.format("%,.2f%n", balance));
	return sb.toString();
    } // End of the displayBalance method

    public String displayIncomeReport(RentalIncomeReport income) {
	float total = this.getRentSum(income);

	totalIncome = total;

	return String.format("Income%nRent: $%,.2f", totalIncome);
    } // End of the displayIncomeReport method

    private float getRentSum(RentalIncomeReport income) {
	float rentSum = 0.0f;

	for (Iterator<IncomeRecord> iter = income.getRecords(); iter.hasNext();) {
	    rentSum += iter.next().getSumOfRents();
	}

	return rentSum;
    } // End of the getRentSum method

    public String displayExpenseReport(ExpensePaymentReport expense) {
	float total = this.getExpenseSum(expense);
	totalExpense = total;

	String expenseCategories = expense.displayExpenseCategories();

	return "Expense" + System.lineSeparator() + expenseCategories;
    } // End of the displayExpenseReport method

    private float getExpenseSum(ExpensePaymentReport expense) {
	float expenseSum = 0.0f;

	for (Iterator<ExpenseRecord> iter = expense.getRecords(); iter.hasNext();) {
	    expenseSum += iter.next().getExpenseAmount();
	}

	return expenseSum;
    } // End of the getExpenseSum method

} // End of the AnnualSumarry class.
