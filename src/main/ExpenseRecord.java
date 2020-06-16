package main;

// Howard
// Create a record of expenses shown in month, day, amount, and category.
// Added name to show which expense belongs to who.

/**
 * @author Howard
 */
public class ExpenseRecord {
	/** The number of the month (1-12). */
	private int month;
	/** The number of the day (1-31). */
	private int day;
	/** The expense category. */
	private String category;
	/** The payee we are writing a check to. */
	private String payee;
	/** The amount paid in US dollars. */
	private float amount;


	/**
	 * Normal constructor for the ExpenseRecord class.
	 * 
	 * @param month	   The month number (1-12).
	 * @param day	   The day of the month (1-31).
	 * @param category The expense category.
	 * @param payee    The payee receiving a payment.
	 * @param amount   The amount paid.
	 */
	public ExpenseRecord(int month, int day, String category, String payee, float amount) {
		this.month = month;
		this.day = day;
		this.category = category;
		this.payee = payee;
		this.amount = amount;
	} // End of the normal constructor.


	public int getMonth() {
		return this.month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public int getDay() {
		return this.day;
	}

	public void setDay(int day) {
		this.day = day;
	}

	public String getCategory() {
		return this.category;
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
		return this.amount;
	}

	public void setExpenseAmount(float amount) {
		this.amount = amount;
	}

	public String recordExpense() {
		return " Month: " + month + " Day: " + day + " Category: " + category 
				+ "Payee: " + payee + " Expense Amount: " + amount;
	}


	@Override
	public String toString() {
		return this.month + "/" + this.day + " " + this.payee 
				+ " " + this.amount + " " + this.category;
	} // End of the toString override

} // End of the ExpenseRecord class.
