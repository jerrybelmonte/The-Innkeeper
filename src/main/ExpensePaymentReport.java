package main;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * The report for year to date expense payments.
 */
public class ExpensePaymentReport {
    private static volatile ExpensePaymentReport expenseReport;
    private static String fileName = "expense.txt";
    private List<ExpenseRecord> records;
    private Map<String, Float> expensePayments;

    /**
     * Private constructor for the ExpensePaymentReport class.
     */
    private ExpensePaymentReport() {
	this.records = new ArrayList<>();
	this.expensePayments = new HashMap<>();
	this.loadFile();
    } // End of the default constructor.

    /**
     * Used instead of the default constructor.
     *
     * @return The one and only instance of the ExpensePaymentReport
     */
    public static ExpensePaymentReport getInstance() {
	if (ExpensePaymentReport.expenseReport == null) {
	    synchronized (ExpensePaymentReport.class) {
		if (ExpensePaymentReport.expenseReport == null) {
		    ExpensePaymentReport.expenseReport = new ExpensePaymentReport();
		}
	    }
	}
	return ExpensePaymentReport.expenseReport;
    } // End of the getInstance method

    private void loadFile() {
	Path targetFile = FileSystems.getDefault().getPath(fileName);
	fileName = targetFile.toAbsolutePath().toString();

	if (Files.exists(targetFile)) {
	    try {
		List<String> lines = Files.readAllLines(targetFile);
		this.importList(lines);
	    } catch (IOException e) {
		e.printStackTrace();
	    }
	}
    } // End of the loadFile method

    private void importList(List<String> listOfRecords) {
	if (!listOfRecords.isEmpty()) {
	    this.records = listOfRecords.stream()
		    .map(str -> new ExpenseRecord(str))
		    .collect(Collectors.toList());
	    
	    this.populateMap(this.records);
	}
    } // End of the importList method

    private void populateMap(List<ExpenseRecord> records) {
	for (ExpenseRecord expense : records) {
	    String category = expense.getCategory();
	    float amount = expense.getExpenseAmount();

	    try {
		float value = this.expensePayments.getOrDefault(category, 0.0f);
		this.expensePayments.put(category, value + amount);
	    } catch (NullPointerException NPE) {
		// ignore null return values from the map
	    }
	}
    } // End of the populateMap method

    /**
     * @return All of the expense records.
     */
    public Iterator<ExpenseRecord> getRecords() {
	this.records.sort(null);
	return this.records.iterator();
    } // End of the getRecords method

    /**
     * Adds a new expense record to the expense payment report.
     *
     * @param newRecord The expense record to add.
     */
    public void addRecord(ExpenseRecord newRecord) {
	if (newRecord != null) {
	    this.records.add(newRecord);
	    String category = newRecord.getCategory();
	    float amount = newRecord.getExpenseAmount();

	    try {
		float value = this.expensePayments.getOrDefault(category, 0.0f);
		this.expensePayments.put(category, value + amount);
	    } catch (NullPointerException NPE) {
		// ignore null return values from the map
	    }
	}
    } // End of the addRecord method

    /**
     * @return All of the expense categories in the expense payment report.
     */
    public Iterator<String> getExpenseCategories() {
	return this.expensePayments.keySet().iterator();
    } // End of the getExpenseCategories method

    /**
     * Gets the amount paid to a specific category. If the category does not exist,
     * it will report 0.0 paid.
     *
     * @param category The expense category.
     * @return The amount paid for the expense category given.
     */
    public float getAmountPaidFromCategory(String category) {
	return this.expensePayments.getOrDefault(category, 0.0f);
    } // End of the getAmountPaidFromCategory method

    /**
     * Creates a record of the expense payment report by saving it to a text file.
     */
    public void recordExpensePayment() {
	Path target = Paths.get(fileName).toAbsolutePath();

	this.records.sort(null);
	try (BufferedWriter bw = Files.newBufferedWriter(target)) {
	    for (ExpenseRecord each : this.records) {
		bw.write(each.recordExpense());
		bw.newLine();
	    }
	} catch (IOException e) {
	    throw new IllegalArgumentException(e.getMessage());
	}
    } // End of the recordExpensePayment method

    /**
     * @return List of expense categories to display to the user.
     */
    public String displayExpenseCategories() {
	StringBuffer sb = new StringBuffer();
	String newline = System.lineSeparator();

	Iterator<String> iter = this.getExpenseCategories();

	while (iter.hasNext()) {
	    String category = iter.next();
	    sb.append(category + ": $");
	    float amount = this.expensePayments.get(category);
	    sb.append(String.format("%,.2f", amount));
	    sb.append(newline);
	}

	return sb.toString();
    } // End of the displayExpenseCategories method

    /**
     * @return String list with the expense payments.
     */
    public String displayRecords() {
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
	}

	return sb.toString();
    } // End of the displayRecords method

    public String[] getTitlesForTable() {
	String[] titles = { "Date", "Payee", "Amount", "Category" };
	return titles;
    } // End of the getTitlesForTable method

    public String[][] getTableData() {
	this.records.sort(null);
	int numberOfExpenses = this.records.size();
	int numberOfColumns = 4;
	String[][] data = new String[numberOfExpenses][numberOfColumns];

	for (int i = 0; i < numberOfExpenses; i++) {
	    int dateNdx = 0;
	    int payeeNdx = 1;
	    int amountNdx = 2;
	    int categoryNdx = 3;

	    ExpenseRecord expense = this.records.get(i);
	    data[i][dateNdx] = String.format("%d/%d", expense.getMonth(), 
		    expense.getDay());
	    data[i][payeeNdx] = expense.getPayee();
	    data[i][amountNdx] = String.format("$%,.2f", 
		    expense.getExpenseAmount());
	    data[i][categoryNdx] = expense.getCategory();
	}

	return data;
    } // End of the getTableData method

} // End of the ExpensePaymentReport class.
