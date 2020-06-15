package main;
// Sirage
//TODO : inherit a tenant into income record class.
//search the apartment number based on the tenant name.
//Make a record list, sorted by apartment number in a TreeMap, with amount paid and month.

public class IncomeRecord {
	private String tenantName;
	private int apartmentNum;
	private float rentAmount;
	private int monthPaid;
	
	
	public IncomeRecord(Tenant currentTenant, float rentAmount, int monthPaid) {
		this.tenantName = currentTenant.getTenantName();
		this.apartmentNum = currentTenant.getApartmentNumber();
		this.rentAmount = rentAmount;
		this.monthPaid = monthPaid;
	}
	
	public String getTenantName() {
		return this.tenantName;
	}

	public void setTenantName(String name) {
		this.tenantName = name;
	}

	public float getRentAmount() {
		return this.rentAmount;
	}
	
	public void setRentAmount(float amount) {
		this.rentAmount = amount;
	}

	public void setMonthPaid(int month) {
		this.monthPaid = month;
	}
	
	public int getMonthPaid() {
		return this.monthPaid;
	}
	
	public void setApartmentNum(int apartmentNumber) {
		this.apartmentNum = apartmentNumber;
	}
	
	public int getApartmentNum() {
		return this.apartmentNum;
	}
	
	public String recordRent() {
		//TODO: return a rent record
		return " Rent Amount: " + rentAmount + " Month Paid: " + monthPaid;
	}
	
}
