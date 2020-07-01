package main;

/**
 * A tenant that rents an apartment in John's apartment building.
 */
public class Tenant implements Comparable<Tenant> {
    private String tenantName;
    private int apartmentNumber;

    /**
     * Normal constructor for the Tenant class.
     *
     * @param tenantName      Their full name.
     * @param apartmentNumber Their apartment number.
     */
    public Tenant(String tenantName, int apartmentNumber) {
	this.tenantName = tenantName;
	this.apartmentNumber = apartmentNumber;
    } // End of the normal constructor.

    /**
     * String constructor for the Tenant class.
     *
     * @param record The tenant record from a text file.
     */
    public Tenant(String record) {
	String[] tokens = record.split(",");

	this.tenantName = tokens[1].trim();
	this.apartmentNumber = Integer.valueOf(tokens[0].trim());
    } // End of the String constructor.

    /**
     * @return The tenant's name.
     */
    public String getTenantName() {
	return this.tenantName;
    } // End of the getter

    /**
     * @return The tenant's apartment number.
     */
    public int getApartmentNumber() {
	return this.apartmentNumber;
    } // End of the getter

    @Override
    public String toString() {
	return this.apartmentNumber + "," + this.tenantName;
    } // End of the toString override

    @Override
    public int compareTo(Tenant o) {
	int difference = this.apartmentNumber - o.getApartmentNumber();

	if (difference < 0) {
	    return -1;
	} else if (difference > 0) {
	    return 1;
	} else {
	    return 0;
	}
    } // End of the compareTo override

    @Override
    public int hashCode() {
	int prime = 31;
	return prime*this.apartmentNumber + this.tenantName.hashCode();
    } // End of the hashCode override

    @Override
    public boolean equals(Object obj) {
	if (this == obj) {
	    return true;
	}

	if (!(obj instanceof Tenant)) {
	    return false;
	}

	Tenant other = (Tenant) obj;
	return this.apartmentNumber == other.getApartmentNumber() 
		&& this.tenantName.equals(other.getTenantName());
    } // End of the equals override

} // End of the Tenant class.
