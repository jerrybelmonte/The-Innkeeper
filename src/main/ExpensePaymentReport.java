package main;

// Howard

import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

/**
 * @author Howard
 */
public class ExpensePaymentReport {
	/** List of ExpenseRecord objects. */
	private List<ExpenseRecord> records;
	/** String keys = expense category, Float values = amount paid. */
	private Map<String, Float> expensePayments;


	/**
	 * Default constructor for the ExpensePaymentReport class.
	 */
	public ExpensePaymentReport () {
		// Create a new array list of records.
		this.records = new ArrayList<>();
		// Create a new hash map.
		this.expensePayments = new HashMap<>();
	} // End of the default constructor.


	/**
	 * Overloaded constructor for the ExpensePaymentReport class.
	 * 
	 * @param records The list of expense records.
	 */
	public ExpensePaymentReport(List<ExpenseRecord> records) {
		this.records = records;
		this.expensePayments = new HashMap<>();
		this.populateMap(this.getRecords());
	} // End of the overloaded constructor.


	private void populateMap(Iterator<ExpenseRecord> records) {
		while (records.hasNext()) {
			ExpenseRecord expense = records.next();
			String category = expense.getCategory();
			float amount = expense.getExpenseAmount();
			
			try { 
				float value = this.expensePayments.getOrDefault(category, 0.0f);
				this.expensePayments.put(category, value + amount);
			} catch (NullPointerException NPE) {}
		} //end while
	} // End of the populateMap method


	public Iterator<ExpenseRecord> getRecords() {
		return this.records.listIterator();
	}

	public void addRecord(ExpenseRecord newRecord) { //insertExpense
		records.add(newRecord);
		String category = newRecord.getCategory();
		float amount = newRecord.getExpenseAmount();
		
		try { 
			float value = this.expensePayments.getOrDefault(category, 0.0f);
			this.expensePayments.put(category, value + amount);
		} catch (NullPointerException NPE) {}
	} // End of the addRecord method

	public Iterator<String> getExpenseCategories() {
		return this.expensePayments.keySet().iterator();
	}

	public float getAmountPaidFromCategory(String category) {
		return this.expensePayments.getOrDefault(category, 0.0f);
	}
	
	public void recordExpensePayment(ExpenseRecord expense) {
		//TODO: record the payment
	}
	
	public String displayExpenseCategories() {
		StringBuffer sb = new StringBuffer();
		String newline = System.lineSeparator();
		
		Iterator<String> iter = this.getExpenseCategories();
		
		while (iter.hasNext()) {
			String category = iter.next();
			float amount = this.expensePayments.get(category);
			
			sb.append(category + " ");
			sb.append(amount);
			sb.append(newline);
		} //end while
		
		return sb.toString();
	} // End of the displayExpenseCategories method

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		String newline = System.lineSeparator();
		
		Iterator<ExpenseRecord> iter = this.getRecords();
		
		while (iter.hasNext()) {
			ExpenseRecord expense = iter.next();
			sb.append(expense.getMonth());
			sb.append("/");
			sb.append(expense.getDay());
			sb.append(" " + expense.getPayee() + " ");
			sb.append(expense.getExpenseAmount());
			sb.append(" " + expense.getCategory() + newline);
		} //end while
		
		return sb.toString();
	} // End of the toString override

} // End of the ExpensePaymentReport class.
