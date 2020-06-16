package main;
// Sirage
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
	public RentalIncomeReport()
	{
		this.rentRows = new TreeMap<>(); 
		this.tenantRecords = new HashMap<>();
	}


	/**
	 * Normal constructor for the RentalIncomeReport class.
	 * 
	 * @param records The list of income records.
	 */
	public RentalIncomeReport(List<IncomeRecord> records) {
		this.rentRows = new TreeMap<>();
		this.tenantRecords = new HashMap<>();
		
		this.rentRows = records.stream().map(r -> {
			return Map.entry(r.getApartmentNum(), r);
			}
		).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
		
		this.tenantRecords = records.stream().map(r -> {
			return Map.entry(r.getTenantName(), r);
			}
		).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
	}


	public List<String> getRentRows()
	{
		List<String> rows = new ArrayList<>();
		
		for (Integer aptNo : this.rentRows.keySet()) {
			IncomeRecord rent = this.rentRows.get(aptNo);
			rows.add(rent.toString());
		}
		
		return rows;
	}


	public List<String> getRecords()
	{
		List<String> records = new ArrayList<>();
		
		for (IncomeRecord income : this.tenantRecords.values()) {
			records.add(income.recordRent());
		}
		
		return records;
	}


	public void addRecord(IncomeRecord rentRecord) 
	{
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
	}


	public void insertRent(int apartmentNum, int monthNum, float amountPaid) {
		IncomeRecord row = this.rentRows.get(apartmentNum);
		String name = row.getTenantName();
		
		row.setRentAmount(monthNum, amountPaid);
		
		row = this.rentRows.put(apartmentNum, row);
		row = this.tenantRecords.put(name, row);
	}


	public String displayRecord()
	{
		if (!this.tenantRecords.isEmpty()) {

			String newline = System.lineSeparator();
			StringBuilder sb = new StringBuilder();
			List<String> lines = this.getRentRows();

			for (String row : lines)
			{
				sb.append(row);
				sb.append(newline);
			}

			return sb.toString();
		}
		else {
			return null;
		}
	}

	
	public boolean checkForTenantName(String tenantName) {
		return (this.tenantRecords.get(tenantName) != null);
	}

	
	public IncomeRecord getRowFromAptNum(int apartmentNumber) {
		return this.rentRows.get(apartmentNumber);
	}
	
	
	public float getSumOfRow(int apartmentNumber) {
		IncomeRecord row = this.getRowFromAptNum(apartmentNumber);
		return row.getSumOfRents();
	}
	
	public void recordIncomePayment(String rent) {
		//TODO: method to record a rent record
		
	}

} // End of the RentalIncomeReport class.
