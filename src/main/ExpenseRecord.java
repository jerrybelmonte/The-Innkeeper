package main;

// Howard
// Create a record of expenses shown in month, day, amount, and category.

public class ExpenseRecord {
	private int month;
	private int day;
	private String category;
	private float amount;
	
	
	public ExpenseRecord(int month, int day, String category, float amount) {
		this.month = month;
		this.day = day;
		this.category = category;
		this.amount = amount;
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


	public String recordExpense() {
		return "Month: " + month + " Day: " + day + " Category: " + category + " Expense Amount: " +
				amount;
	}
	
}
