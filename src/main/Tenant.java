package main;

public class Tenant {
	private String tenantName;
	private int apartmentNumber;

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

}
