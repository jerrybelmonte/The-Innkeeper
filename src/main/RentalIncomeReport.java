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
import java.util.TreeMap;
import java.util.stream.Collectors;

/**
 * The rental income report with rows of rent payments.
 */
public class RentalIncomeReport {
    private static volatile RentalIncomeReport incomeReport;
    private static String fileName = "income.txt";
    private Map<Integer, IncomeRecord> rentRows;
    private Map<String, IncomeRecord> tenantRecords;

    /**
     * Private constructor for the RentalIncomeReport class.
     */
    private RentalIncomeReport() {
	this.rentRows = new TreeMap<>();
	this.tenantRecords = new HashMap<>();
	this.loadFile();
    } // End of the default constructor.

    /**
     * Used instead of the normal constructor.
     *
     * @return The one and only instance of the RentalIncomeReport.
     */
    public static RentalIncomeReport getInstance() {
	if (RentalIncomeReport.incomeReport == null) {
	    synchronized (RentalIncomeReport.class) {
		if (RentalIncomeReport.incomeReport == null) {
		    RentalIncomeReport.incomeReport = new RentalIncomeReport();
		}
	    }
	}
	return RentalIncomeReport.incomeReport;
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
	    this.rentRows = listOfRecords.stream().map(str -> {
		IncomeRecord rent = new IncomeRecord(str);
		int apartment = rent.getTenant().getApartmentNumber();
		return Map.entry(apartment, rent);
	    }).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

	    this.tenantRecords = listOfRecords.stream().map(str -> {
		IncomeRecord rent = new IncomeRecord(str);
		String tenantName = rent.getTenant().getTenantName();
		return Map.entry(tenantName, rent);
	    }).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
	}
    } // End of the importList method

    /**
     * @return Rows with rental payments.
     */
    public List<String> getRentRows() {
	List<String> rows = new ArrayList<>();

	for (Integer aptNo : this.rentRows.keySet()) {
	    IncomeRecord rent = this.rentRows.get(aptNo);
	    rows.add(rent.toString());
	}

	return rows;
    } // End of the getRentRows method

    /**
     * @return The income records from the current tenants.
     */
    public Iterator<IncomeRecord> getRecords() {
	List<IncomeRecord> records = new ArrayList<>();

	for (IncomeRecord income : this.tenantRecords.values()) {
	    records.add(income);
	}

	records.sort(null);

	return records.listIterator();
    } // End of the getRecords method

    /**
     * Adds a rental income record to the rental income report.
     *
     * @param rentRecord The rental income record to add.
     * @throws IllegalArgumentException If the apartment is occupied.
     */
    public void addRecord(IncomeRecord rentRecord) {
	int apartmentNumber = rentRecord.getTenant().getApartmentNumber();
	String tenantName = rentRecord.getTenant().getTenantName();

	if (!this.checkForTenantName(tenantName)) {
	    try {
		this.rentRows.putIfAbsent(apartmentNumber, rentRecord);
	    } catch (NullPointerException NPE) {
		// ignore null return values from map
	    }

	    try {
		this.tenantRecords.putIfAbsent(tenantName, rentRecord);
	    } catch (NullPointerException NPE) {
		// ignore null return values from map
	    }
	} else {
	    throw new IllegalArgumentException("The apartment is occupied");
	}
    } // End of the addRecord method

    /**
     * Inserts a rental payment to one of the rows in the list of records.
     *
     * @param apartmentNum The tenant's apartment number.
     * @param monthNum     The month that the rent payment is for.
     * @param amountPaid   The amount paid by the tenant.
     */
    public void insertRent(int apartmentNum, int monthNum, float amountPaid) {
	IncomeRecord row = this.rentRows.get(apartmentNum);
	String tenantName = row.getTenant().getTenantName();

	row.setRentAmount(monthNum, amountPaid);

	row = this.rentRows.put(apartmentNum, row);
	row = this.tenantRecords.put(tenantName, row);
    } // End of the insertRent method

    /**
     * Gets the rental income report to display to the user.
     *
     * @return The rental income report.
     */
    public String displayRecords() {
	String incomeReport = null;

	if (!this.tenantRecords.isEmpty()) {
	    String newline = System.lineSeparator();
	    StringBuilder sb = new StringBuilder();

	    List<String> lines = this.getRentRows();
	    for (String row : lines) {
		sb.append(row);
		sb.append(newline);
	    }

	    incomeReport = sb.toString();
	}

	return incomeReport;
    } // End of the displayRecord method

    /**
     * Checks the income records for a given name from a tenant.
     *
     * @param tenantName The name of the tenant.
     * @return True if and only if there exists a tenant with the given name.
     */
    public boolean checkForTenantName(String tenantName) {
	return this.tenantRecords.get(tenantName) != null;
    } // End of the checkForTenantName method

    /**
     * Gets the row from the rental income report of the specified apartment number.
     *
     * @param apartmentNumber The rows's apartment number.
     * @return The row from the income report for the apartment.
     */
    public IncomeRecord getRowFromAptNum(int apartmentNumber) {
	return this.rentRows.get(apartmentNumber);
    } // End of the getRowFromAptNum method

    /**
     * Gets the sum of all the rental payments from a row in the income report.
     *
     * @param apartmentNumber The row's apartment number.
     * @return The sum of the amount of rent paid.
     */
    public float getSumOfRow(int apartmentNumber) {
	IncomeRecord row = this.getRowFromAptNum(apartmentNumber);
	return row.getSumOfRents();
    } // End of the getSumOfRow method

    /**
     * Records the rental income payment report to a text file.
     */
    public void recordIncomePayment() {
	Path target = Paths.get(fileName).toAbsolutePath();
	Iterator<IncomeRecord> iter = this.getRecords();

	try (BufferedWriter bw = Files.newBufferedWriter(target)) {
	    while (iter.hasNext()) {
		String line = iter.next().recordRent();
		bw.write(line);
		bw.newLine();
	    }
	} catch (IOException e) {
	    throw new IllegalArgumentException(e.getMessage());
	}
    } // End of the recordIncomePayment method

    public String[] getTitlesForTable() {
	String[] titles = { "Apartment #", "January", "February", "March", "April", "May", "June", "July", "August",
		"September", "October", "November", "December" };

	return titles;
    } // End of the getTitlesForTable method

    public String[][] getTableData() {
	int numberOfRents = this.rentRows.size();
	int numberOfColumns = 13;
	String[][] data = new String[numberOfRents][numberOfColumns];

	int index = 0;
	for (Iterator<IncomeRecord> iter = this.getRecords(); iter.hasNext(); index++) {
	    IncomeRecord row = iter.next();
	    int apartmentNdx = 0;
	    data[index][apartmentNdx] = String.valueOf(row.getTenant().getApartmentNumber());
	    data[index][1] = String.format("$%,.2f", row.getRentAmount(1));
	    data[index][2] = String.format("$%,.2f", row.getRentAmount(2));
	    data[index][3] = String.format("$%,.2f", row.getRentAmount(3));
	    data[index][4] = String.format("$%,.2f", row.getRentAmount(4));
	    data[index][5] = String.format("$%,.2f", row.getRentAmount(5));
	    data[index][6] = String.format("$%,.2f", row.getRentAmount(6));
	    data[index][7] = String.format("$%,.2f", row.getRentAmount(7));
	    data[index][8] = String.format("$%,.2f", row.getRentAmount(8));
	    data[index][9] = String.format("$%,.2f", row.getRentAmount(9));
	    data[index][10] = String.format("$%,.2f", row.getRentAmount(10));
	    data[index][11] = String.format("$%,.2f", row.getRentAmount(11));
	    data[index][12] = String.format("$%,.2f", row.getRentAmount(12));
	}

	return data;
    } // End of the getTableData method

} // End of the RentalIncomeReport class.
