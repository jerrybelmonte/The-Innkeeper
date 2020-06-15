package main;

import java.util.Objects;

/**
 * A tenant that rents an apartment in John's apartment building.
 * 
 * @author Jerry Belmonte
 */
public class Tenant implements Comparable<Tenant> {
	/** The name of the tenant. */
	private String tenantName;
	/** The tenant's apartment number. */
	private int apartmentNumber;


	/**
	 * Normal constructor for the Tenant class.
	 * 
	 * @param tenantName	  Their full name.
	 * @param apartmentNumber Their apartment number.
	 */
	public Tenant(String tenantName, int apartmentNumber) {
		this.tenantName = tenantName;
		this.apartmentNumber = apartmentNumber;
	} // End of the normal constructor.


	/**
	 * @return The tenant's name.
	 */
	public String getTenantName() {
		return this.tenantName;
	} // End of the getter


	/**
	 * @param tenantName Their full name.
	 */
	public void setTenantName(String tenantName) {
		this.tenantName = tenantName;
	} // End of the setter


	/**
	 * @return The tenant's apartment number.
	 */
	public int getApartmentNumber() {
		return this.apartmentNumber;
	} // End of the getter


	/**
	 * @param apartmentNumber Their apartment number.
	 */
	public void setApartmentNumber(int apartmentNumber) {
		this.apartmentNumber = apartmentNumber;
	} // End of the setter


	@Override
	public int hashCode() {
		return Objects.hash(this.apartmentNumber, this.tenantName);
	} // End of the hashCode override


	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		} //end if ==
		if (!(obj instanceof Tenant)) {
			return false;
		} //end if !instanceof
		Tenant other = (Tenant) obj;
		return this.apartmentNumber == other.apartmentNumber 
				&& Objects.equals(this.tenantName, other.tenantName);
	} // End of the equals override


	@Override
	public int compareTo(Tenant o) {
		int result = this.apartmentNumber - o.getApartmentNumber();
		if (result < 0) {
			return -1;
		} //end if
		else if (result > 0) {
			return 1;
		} //end else if
		else {
			return 0;
		} //end else
	} // End of the compareTo override


	@Override
	public String toString() {
		return this.apartmentNumber + ", " + this.tenantName;
	} // End of the toString override

} // End of the Tenant class.
