package main;

// Howard

import java.util.List;
import java.util.TreeMap;
import java.util.ArrayList;

public class ExpensePaymentReport {
	//TODO: columns

	// Set of Expense Record objects.
	private List<ExpenseRecord> records;

	// String keys = Name. Integer values = Apartment Number
	private TreeMap<String, Integer> tMap;

	public ExpensePaymentReport () {

		// Create a new array list of records.
		this.records = new ArrayList<ExpenseRecord>();

		// Create a new tree map.
		this.tMap = new TreeMap<String, Integer>();
	}

	public ExpensePaymentReport(ArrayList<ExpenseRecord> records) {
		// this.records = records;

		// Create tree map object.
		this.tMap = new TreeMap<>();

		for(ExpenseRecord expenseRecord : records) { // Loop the record for each apartment # and expense.

			// Key = apartment #. Value = record expense.
			tMap.put(expenseRecord.getApartmentN(), expenseRecord.recordExpense());
		}
	}
	
	public List<ExpenseRecord> getRecords()
	{
		return records;
	}
	
	public void recordExpensePayment(ExpenseRecord expense) {
		//TODO: record the payment
	}

}
