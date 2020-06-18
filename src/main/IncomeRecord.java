package main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

// Sirage
//TODO : inherit a tenant into income record class.
//search the apartment number based on the tenant name.
//Make a record list, sorted by apartment number in a TreeMap, with amount paid and month.

/**
 * The rental income record for an apartment in the apartment building.
 * 
 * @author Sirage
 */
public class IncomeRecord {
	/** The name of the tenant renting the apartment. */
	private String tenantName;
	/** The apartment number for the rental income record. */
	private int apartmentNum;
	/** 
	 * The list of rental payments. There are 12 elements in the list, one for each
	 * calendar month. For example, rentPayments.get(0) returns the rent for January.
	 */
	private List<Float> rentPayments;


	/**
	 * Default constructor for the IncomeRecord class.
	 * 
	 * @param currentTenant The tenant renting an apartment.
	 */
	public IncomeRecord(Tenant currentTenant) {
		this.tenantName = currentTenant.getTenantName();
		this.apartmentNum = currentTenant.getApartmentNumber();
		this.rentPayments = Arrays.asList(
				0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 
				0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f
				);
	} // End of the default constructor.


	/**
	 * Overloaded constructor for the IncomeRecord class.
	 * 
	 * @param currentTenant The tenant renting an apartment.
	 * @param rentPayments  The rent row of payments for each month.
	 */
	public IncomeRecord(Tenant currentTenant, List<Float> rentPayments) {
		this.tenantName = currentTenant.getTenantName();
		this.apartmentNum = currentTenant.getApartmentNumber();
		this.rentPayments = rentPayments;
	} // End of the overloaded constructor.


	/**
	 * Constructor that accepts single String income record.
	 * 
	 * @param record The income record from a text file.
	 */
	public IncomeRecord(String record) {
		String[] tokens = record.split(";");
		
		this.tenantName = tokens[0].trim();
		this.apartmentNum = Integer.valueOf(tokens[1]);
		this.rentPayments = new ArrayList<>();
		
		String str = tokens[2].substring(tokens[2].indexOf("[") + 1, 
				tokens[2].indexOf("]"));
		
		this.rentPayments = Arrays.asList(str.split(",")).stream().map(
						s -> Float.valueOf(s)).collect(Collectors.toList()
						);
	} // End of the IncomeRecord String constructor.


	public String getTenantName() {
		return this.tenantName;
	} // End of the getTenantName getter


	public void setTenantName(String name) {
		this.tenantName = name;
	} // End of the setTenantName setter


	public int getApartmentNum() {
		return this.apartmentNum;
	} // End of the getApartmentNum getter


	public void setApartmentNum(int apartmentNumber) {
		this.apartmentNum = apartmentNumber;
	} // End of the setApartmentNum setter


	/**
	 * Gets the amount paid by the tenant for one month.
	 * 
	 * @param monthNum The month number (1-12).
	 * @return The rent paid for the month in US dollars.
	 */
	public float getRentAmount(int monthNum) {
		int monthNdx = monthNum - 1;
		return this.rentPayments.get(monthNdx);
	} // End of the getRentAmount method


	/**
	 * Records the rent for one month.
	 * 
	 * @param monthNum   The month number (1-12).
	 * @param amountPaid The rent amount paid in US dollars.
	 */
	public void setRentAmount(int monthNum, float amountPaid) {
		int monthNdx = monthNum - 1;
		this.rentPayments.set(monthNdx, amountPaid);
	} // End of the setRentAmount method


	/**
	 * @return The sum of the rents.
	 */
	public float getSumOfRents() {
		float sum = 0.0f;
		
		for (Float rent : rentPayments) {
			sum += rent;
		}
		
		return sum;
	} // End of the getSumOfRents method


	public String recordRent() {
		return this.tenantName + ";" + this.apartmentNum 
				+ ";" + this.rentPayments;
	}


	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		sb.append(this.apartmentNum);
		
		for (Float rent : rentPayments) {
			sb.append(" ");
			sb.append(rent);
		}
		
		return sb.toString();
	} // End of the toString override

} // End of the IncomeRecord class.