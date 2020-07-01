package main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * The rental income record for an apartment unit in the apartment building.
 */
public class IncomeRecord implements Comparable<IncomeRecord> {
    /** The tenant that is currently renting out the apartment. */
    private Tenant currentTenant;
    /**
     * The list of rent payments. There are 12 elements in the list, one for each
     * calendar month. For example, rentPayments.get(0) returns the rent for January.
     */
    private List<Float> rentPayments;

    /**
     * Default constructor for the IncomeRecord class.
     *
     * @param currentTenant The tenant renting an apartment.
     */
    public IncomeRecord(Tenant currentTenant) {
	this.currentTenant = currentTenant;
	this.rentPayments = Arrays.asList(
		0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 
		0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f
		);
    } // End of the default constructor.

    /**
     * Constructor that accepts single String income record.
     *
     * @param record The income record from a text file.
     */
    public IncomeRecord(String record) {
	String[] tokens = record.split(";");
	String rents = tokens[1].substring(
		tokens[1].indexOf("[") + 1, 
		tokens[1].indexOf("]")
		);

	this.currentTenant = new Tenant(tokens[0]);
	this.rentPayments = new ArrayList<>();
	this.rentPayments = Arrays.asList(rents.split(",")).stream()
		.map(str -> Float.valueOf(str))
		.collect(Collectors.toList());
    } // End of the String constructor.

    /**
     * @return The tenant that lives in the apartment.
     */
    public Tenant getTenant() {
	return this.currentTenant;
    } // End of the getTenant getter

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
     * Calculates the total sum of all the rents for the apartment.
     *
     * @return The sum of the rents.
     */
    public float getSumOfRents() {
	float sum = 0.0f;

	for (Float rent : this.rentPayments) {
	    sum += rent;
	}

	return sum;
    } // End of the getSumOfRents method

    /**
     * Calculates the total sum of all the rent payments for the apartment.
     *
     * @return The sum of the rents.
     */
    public String recordRent() {
	return this.currentTenant + ";" + this.rentPayments;
    } // End of the recordRent method

    @Override
    public String toString() {
	StringBuilder sb = new StringBuilder();
	sb.append(this.currentTenant.getApartmentNumber());

	for (Float rent : this.rentPayments) {
	    sb.append(" ");
	    sb.append(rent);
	}

	return sb.toString();
    } // End of the toString override

    @Override
    public int hashCode() {
	return this.currentTenant.hashCode();
    } // End of the hashCode override

    @Override
    public boolean equals(Object obj) {
	if (this == obj) {
	    return true;
	}

	if (!(obj instanceof IncomeRecord)) {
	    return false;
	}

	IncomeRecord other = (IncomeRecord) obj;
	return this.currentTenant.equals(other.getTenant());
    } // End of the equals override

    /**
     * Compares this income record on the left to the income record on the right.
     * 
     * @return +1 if the apartment number on the left is greater, 
     *         -1 if the apartment number on the left is less than, 
     *         0 if the apartment numbers are the same.
     */
    @Override
    public int compareTo(IncomeRecord o) {
	int difference = this.currentTenant.getApartmentNumber() 
		- o.getTenant().getApartmentNumber();

	if (difference > 0) {
	    return 1;
	} else if (difference < 0) {
	    return -1;
	} else {
	    return 0;
	}
    } // End of the compareTo override

} // End of the IncomeRecord class.
