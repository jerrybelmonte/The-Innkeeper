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
	private int apartmentNum;
	private String name;
	private String payee;
	
	public ExpenseRecord(String name, int month, int day, String category, String payee, float amount) {
		this.month = month;
		this.day = day;
		this.category = category;
		this.payee = payee;
		this.amount = amount;
		this.apartmentNum = tenant.getApartmentNumber(name); // Tenant's name and apartment number.
	}

	public int getApartmentNum() {
		return apartmentNum;
	}

	public void setApartmentNum(int apartmentNum) {
		this.apartmentNum = apartmentNum;
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

	public void setDay(int day) {
		this.day = day;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getPayee() {
		return payee;
	}

	public void setPayee(String payee) {
		this.payee = payee;
	}

	public float getExpenseAmount() {
		return amount;
	}

	public void setExpenseAmount(float amount) {
		this.amount = amount;
	}


	public String recordExpense() {
		return "Apartment Number: " + apartmentNum + " Month: " + month + " Day: " + day + " Category: "
				+ category + "Payee: " + payee + " Expense Amount: " + amount;
	}
	
}
