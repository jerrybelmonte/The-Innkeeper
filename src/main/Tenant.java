package main;

/**
 * A tenant that rents an apartment in John's apartment building.
 * 
 * @author Jerry Belmonte
 */
public class Tenant {
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
	}


	public String getTenantName() {
		return tenantName;
	}


	public void setTenantName(String tenantName) {
		this.tenantName = tenantName;
	}


	public int getApartmentNumber() {
		return apartmentNumber;
	}


	public void setApartmentNumber(int apartmentNumber) {
		this.apartmentNumber = apartmentNumber;
	}

} // End of the Tenant class.
