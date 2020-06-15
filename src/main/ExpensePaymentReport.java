package main;

// Howard

import java.util.Map.Entry;
import java.util.stream.Collectors;
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

		this.records = new ArrayList<>(records);

		// Create tree map object.
		this.tMap = new TreeMap<>();

		// this.tMap = (TreeMap<String, Integer>);

		this.tMap = (TreeMap<String, Integer>) records.stream().collect(Collectors.toMap(ExpenseRecord::getApartmentN,
				ExpenseRecord::recordExpense));

	}
	
	public List<ExpenseRecord> getRecords() {
		return records;
	}

	public void addRecord(ExpenseRecord newRecord) {
		records.add(newRecord);
	}

	public TreeMap<String, Integer> getTmap() {
		return tMap;
	}
	
	public void recordExpensePayment(ExpenseRecord expense) {
		//TODO: record the payment
	}

}
