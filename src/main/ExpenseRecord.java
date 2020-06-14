package main;

// Howard
// Create a record of expenses shown in month, day, amount, and category.
// Added name to show which expense belongs to who.

public class ExpenseRecord {
	private int month;
	private int day;
	private String category;
	private float amount;
	private TenantList tenant = new TenantList();
	private int apartmentN;
	private String name;
	
	
	public ExpenseRecord(String name, int month, int day, String category, float amount) {
		this.month = month;
		this.day = day;
		this.category = category;
		this.amount = amount;
		this.apartmentN = tenant.getApartmentNumber(name); // Tenant's name and apartment number.
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public int getDay() {
		return day;
	}

	public void setDay() {
		this.day = day;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory() {
		this.category = category;
	}

	public float getExpenseAmount() {
		return amount;
	}

	public void setExpenseAmount() {
		this.amount = amount;
	}

	public void setApartmentN(int apartmentN) {
		this.apartmentN = apartmentN;
	}

	public int getApartmentN() {
		return apartmentN;
	}


	public String recordExpense() {
		return "Apartment Number: " + apartmentN + " Month: " + month + " Day: " + day + " Category: "
				+ category + " Expense Amount: " + amount;
	}
	
}
