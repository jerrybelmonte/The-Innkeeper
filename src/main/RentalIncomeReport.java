package main;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

/**
 * The rental income report with rows of rent payments.
 * 
 * @author Sirage
 */
public class RentalIncomeReport {
	/** The rent rows: Key = apartment number, Value = IncomeRecord */
	private Map<Integer, IncomeRecord> rentRows;
	/** The tenant records: Key = tenant name, Value = IncomeRecord */
	private Map<String, IncomeRecord> tenantRecords;


	/**
	 * Default constructor for the RentalIncomeReport class.
	 */
	public RentalIncomeReport() {
		this.rentRows = new TreeMap<>(); 
		this.tenantRecords = new HashMap<>();
	} // End of the default constructor.


	/**
	 * Normal constructor for the RentalIncomeReport class.
	 * 
	 * @param records The list of income records.
	 */
	public RentalIncomeReport(List<IncomeRecord> records) {
		this.rentRows = new TreeMap<>();
		this.tenantRecords = new HashMap<>();
		
		this.rentRows = records.stream().map(record -> {
			return Map.entry(record.getApartmentNum(), record);
			}
		).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
		
		this.tenantRecords = records.stream().map(record -> {
			return Map.entry(record.getTenantName(), record);
			}
		).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
	} // End of the normal constructor.


	/**
	 * String constructor for the RentalIncomeReport class.
	 * 
	 * @param list String of lines from a text file.
	 */
	public RentalIncomeReport(String list) {
		this.rentRows = new TreeMap<>(); 
		this.tenantRecords = new HashMap<>();
		
		this.rentRows = list.lines().map(str -> {
			IncomeRecord rent = new IncomeRecord(str);
			int apartmentNum = rent.getApartmentNum();
			return Map.entry(apartmentNum, rent);
			}
		).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
		
		this.tenantRecords = list.lines().map(str -> {
			IncomeRecord rent = new IncomeRecord(str);
			String tenantName = rent.getTenantName();
			return Map.entry(tenantName, rent);
			}
		).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
	} // End of the string constructor.


	/**
	 * Generates the rows of the rental income report.
	 * 
	 * @return Rows with rental payments.
	 */
	public List<String> getRentRows() {
		List<String> rows = new ArrayList<>();
		
		for (Integer aptNo : this.rentRows.keySet()) {
			IncomeRecord rent = this.rentRows.get(aptNo);
			rows.add(rent.toString());
		} //end for
		
		return rows;
	} // End of the getRentRows method


	/**
	 * Gets all the income records from the current tenants.
	 * 
	 * @return Iterator with the income records.
	 */
	public Iterator<IncomeRecord> getRecords() {
		List<IncomeRecord> records = new ArrayList<>();
		
		for (IncomeRecord income : this.tenantRecords.values()) {
			records.add(income);
		} //end for
		
		records.sort(null);
		
		return records.listIterator();
	} // End of the getRecords method


	/**
	 * Adds a rental income record to the rental income report.
	 * 
	 * @param rentRecord The rental income record to add.
	 */
	public void addRecord(IncomeRecord rentRecord) {
		int apartmentNumber = rentRecord.getApartmentNum();
		String tenantName = rentRecord.getTenantName();
		
		if (!this.checkForTenantName(tenantName)) {
			try { this.rentRows.putIfAbsent(apartmentNumber, rentRecord); } 
			catch (NullPointerException NPE) {}
			
			try { this.tenantRecords.putIfAbsent(tenantName, rentRecord); } 
			catch (NullPointerException NPE) {}
		} //end if
		else {
			throw new IllegalArgumentException("The apartment is occupied");
		} //end else
	} // End of the addRecord method


	/**
	 * Inserts a rental payment to one of the rows in the list of records.
	 * 
	 * @param apartmentNum The tenant's apartment number.
	 * @param monthNum	   The month that the rent payment is for.
	 * @param amountPaid   The amount paid by the tenant.
	 */
	public void insertRent(int apartmentNum, int monthNum, float amountPaid) {
		IncomeRecord row = this.rentRows.get(apartmentNum);
		String tenantName = row.getTenantName();
		
		row.setRentAmount(monthNum, amountPaid);
		
		row = this.rentRows.put(apartmentNum, row);
		row = this.tenantRecords.put(tenantName, row);
	} // End of the insertRent method


	/**
	 * Gets the rental income report to display to the user.
	 * 
	 * @return The rental income report.
	 */
	public String displayRecord() {
		String incomeReport = null;
		
		if (!this.tenantRecords.isEmpty()) {
			String newline = System.lineSeparator();
			StringBuilder sb = new StringBuilder();
			List<String> lines = this.getRentRows();

			for (String row : lines) {
				sb.append(row);
				sb.append(newline);
			} //end for

			incomeReport = sb.toString();
		} //end if

		return incomeReport;
	} // End of the displayRecord method


	/**
	 * Checks the income records for a given name from a tenant.
	 * 
	 * @param tenantName The name of the tenant.
	 * @return True if and only if there exists a tenant with the given name.
	 */
	public boolean checkForTenantName(String tenantName) {
		return (this.tenantRecords.get(tenantName) != null);
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
	 * 
	 * @param filename The path of the filename to save the record.
	 */
	public void recordIncomePayment(String filename) {
		Path target = Paths.get(filename).toAbsolutePath();
		Iterator<IncomeRecord> iter = this.getRecords();
		
		try (BufferedWriter bw = Files.newBufferedWriter(target)) {
			while (iter.hasNext()) {
				String line = iter.next().recordRent();
				bw.write(line);
				bw.newLine();
			} //end while
		} //end try 
		catch (IOException e) {
			throw new IllegalArgumentException(e.getMessage());
		} //end catch
	} // End of the recordIncomePayment method

} // End of the RentalIncomeReport class.