package main;
// Sirage
//TODO : inherit a tenant into income record class.
//search the apartment number based on the tenant name.
//Make a record list, sorted by apartment number in a TreeMap, with amount paid and month.

public class IncomeRecord{
	private String name;
	private float amount;
	private int month;	
	
	public IncomeRecord(String name, float amount, int month) {
		this.name = name;
		this.amount = amount;
		this.month = month;
	}
	
	public String getTenantName() {
		return name;
	}

	public void setTenantName(String name) {
		this.name = name;
	}

	public float getRentAmount() {
		return amount;
	}
	
	public void setRentAmount(float amount) {
		this.amount = amount;
	}

	public void setMonthPaid(int month) {
		this.month = month;
	}
	
	public int getMonthPaid() {
		return month;
	}
	
	public String recordRent() {
		//TODO: return a rent record
		return "Name: " + name + " Rent Amount: " + amount + " Month Paid: " + month;
	}
	
}
