package main;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

/**
 * The expense payment report class.
 * 
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
		this.records = new ArrayList<>();
		this.expensePayments = new HashMap<>();
	} // End of the default constructor.


	/**
	 * Overloaded constructor for the ExpensePaymentReport class.
	 * 
	 * @param records The list of expense records.
	 */
	public ExpensePaymentReport(List<ExpenseRecord> records) {
		this.records = new ArrayList<>(records);
		this.expensePayments = new TreeMap<>();
		this.populateMap(records);
	} // End of the overloaded constructor.


	/**
	 * String constructor for the ExpensePaymentReport class.
	 * 
	 * @param list The string list of expense records.
	 */
	public ExpensePaymentReport(String list) {
		this.records = list.lines().map(str -> {
			ExpenseRecord expense = new ExpenseRecord(str);
			return expense;
			}
		).collect(Collectors.toList());
		
		this.expensePayments = new TreeMap<>();
		this.populateMap(this.records);
	} // End of the String constructor.


	/**
	 * Populates the map of expense payments.
	 * 
	 * @param records The expense records to map.
	 */
	private void populateMap(List<ExpenseRecord> records) {
		for (ExpenseRecord expense : records) {
			String category = expense.getCategory();
			float amount = expense.getExpenseAmount();
			
			try { 
				float value = this.expensePayments.getOrDefault(category, 0.0f);
				this.expensePayments.put(category, value + amount);
			} //end try 
			catch (NullPointerException NPE) {} // ignore null return values from the map
		} //end for
	} // End of the populateMap method


	/**
	 * Gets all of the expense records.
	 * 
	 * @return Iterator of the expense records. 
	 */
	public Iterator<ExpenseRecord> getRecords() {
		this.records.sort(null);
		return this.records.iterator();
	} // End of the getRecord method


	/**
	 * Adds a new expense record to the expense payment report.
	 * 
	 * @param newRecord The expense record to add.
	 */
	public void addRecord(ExpenseRecord newRecord) {
		records.add(newRecord);
		String category = newRecord.getCategory();
		float amount = newRecord.getExpenseAmount();
		
		try { 
			float value = this.expensePayments.getOrDefault(category, 0.0f);
			this.expensePayments.put(category, value + amount);
		} //end try
		catch (NullPointerException NPE) {}
	} // End of the addRecord method


	/**
	 * Gets all of the expense categories in the expense payment report.
	 * 
	 * @return String iterator with all the expense categories.
	 */
	public Iterator<String> getExpenseCategories() {
		return this.expensePayments.keySet().iterator();
	} // End of the getExpenseCategories method


	/**
	 * Gets the amount paid to a specific category. If the category does not
	 * exist, it will report 0.0 paid.
	 * 
	 * @param category The expense category.
	 * @return The amount paid for the expense category given.
	 */
	public float getAmountPaidFromCategory(String category) {
		return this.expensePayments.getOrDefault(category, 0.0f);
	} // End of the getAmountPaidFromCategory method


	/**
	 * Creates a record of the expense payment report by saving it to a text file.
	 * 
	 * @param filename The path to write the report to.
	 */
	public void recordExpensePayment(String filename) {
		Path target = Paths.get(filename).toAbsolutePath();
		Iterator<ExpenseRecord> iter = this.getRecords();
		
		try (BufferedWriter bw = Files.newBufferedWriter(target)) {
			while (iter.hasNext()) {
				String line = iter.next().recordExpense();
				bw.write(line);
				bw.newLine();
			} //end while
		} //end try 
		catch (IOException e) {
			throw new IllegalArgumentException(e.getMessage());
		} //end catch
	} // End of the recordExpensePayment method


	/**
	 * Returns a list of expense categories to display to the user.
	 * 
	 * @return String list with the expense categories.
	 */
	public String displayExpenseCategories() {
		StringBuffer sb = new StringBuffer();
		String newline = System.lineSeparator();
		
		Iterator<String> iter = this.getExpenseCategories();
		
		while (iter.hasNext()) {
			String category = iter.next();
			sb.append(category + " ");
			float amount = this.expensePayments.get(category);
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
