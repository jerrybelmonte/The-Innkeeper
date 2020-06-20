package main;

import java.util.Objects;

/**
 * The expense record class.
 * 
 * @author Howard
 */
public class ExpenseRecord implements Comparable<ExpenseRecord> {
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


	/**
	 * Constructor that accepts single String expense record.
	 * 
	 * @param record The expense record from a text file.
	 */
	public ExpenseRecord(String record) {
		String[] tokens = record.split(",");
		
		this.month = Integer.valueOf(tokens[0]);
		this.day = Integer.valueOf(tokens[1]);
		this.category = tokens[2].trim();
		this.payee = tokens[3].trim();
		this.amount = Float.valueOf(tokens[4]);
	} // End of the ExpenseRecord String constructor.


	/**
	 * Getter for the ExpenseRecord class.
	 * 
	 * @return The month number.
	 */
	public int getMonth() {
		return this.month;
	} // End of the getMonth getter


	/**
	 * Setter for the ExpenseRecord class.
	 * 
	 * @param month The month number.
	 */
	public void setMonth(int month) {
		this.month = month;
	} // End of the setMonth setter


	/**
	 * Getter for the ExpenseRecord class.
	 * 
	 * @return The day number.
	 */
	public int getDay() {
		return this.day;
	} // End of the getDay getter


	/**
	 * Setter for the ExpenseRecord class.
	 * 
	 * @param day The day number.
	 */
	public void setDay(int day) {
		this.day = day;
	} // End of the setDay setter


	/**
	 * Getter for the ExpenseRecord class.
	 * 
	 * @return The expense category.
	 */
	public String getCategory() {
		return this.category;
	} // End of the getCategory getter


	/**
	 * Setter for the ExpenseRecord class.
	 * 
	 * @param category The expense category.
	 */
	public void setCategory(String category) {
		this.category = category;
	} // End of the setCategory setter


	/**
	 * Getter for the ExpenseRecord class.
	 * 
	 * @return The payee that we wrote a check to.
	 */
	public String getPayee() {
		return payee;
	} // End of the getPayee getter


	/**
	 * Setter for the ExpenseRecord class.
	 * 
	 * @param payee The payee that we wrote a check to.
	 */
	public void setPayee(String payee) {
		this.payee = payee;
	} // End of the setPayee setter


	/**
	 * Getter for the ExpenseRecord class.
	 * 
	 * @return The amount paid.
	 */
	public float getExpenseAmount() {
		return this.amount;
	} // End of the getExpenseAmount getter


	/**
	 * Setter for the ExpenseRecord class.
	 * 
	 * @param amount The amount paid.
	 */
	public void setExpenseAmount(float amount) {
		this.amount = amount;
	} // End of the setExpenseAmount setter


	/**
	 * Returns a string record of the expense.
	 * 
	 * @return Record of the expense.
	 */
	public String recordExpense() {
		StringBuilder sb = new StringBuilder();
		
		sb.append(this.month);
		sb.append(",");
		sb.append(this.day);
		sb.append("," + this.category);
		sb.append("," + this.payee + ",");
		sb.append(this.amount);
		
		return sb.toString();
	} // End of the recordExpense method


	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		sb.append(this.month);
		sb.append("/");
		sb.append(this.day);
		sb.append(" " + this.payee + " ");
		sb.append(this.amount);
		sb.append(" " + this.category);
		
		return sb.toString();
	} // End of the toString override


	@Override
	public int compareTo(ExpenseRecord o) {
		int monthDifference = this.month - o.getMonth();
		
		if (monthDifference > 0) {
			return 1;
		} //end if
		else if (monthDifference < 0) {
			return -1;
		} //end else if
		else {	// months are equal, check day
			int dayDifference = this.day - o.getDay();
			
			if (dayDifference > 0) {
				return 1;
			} //end inner if
			else if (dayDifference < 0) {
				return -1;
			} //end inner else if
			else { // month and day are both equal
				return 0;
			} //end inner else
		} //end else
	} // End of the compareTo method


	@Override
	public int hashCode() {
		return Objects.hash(amount, category, day, month, payee);
	} // End of the hashCode override


	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		} //end if
		if (!(obj instanceof ExpenseRecord)) {
			return false;
		} //end if
		ExpenseRecord other = (ExpenseRecord) obj;
		int dateDifference = this.compareTo(other);
		
		if (dateDifference != 0) {
			return false;
		} //end if
		else {
			return Float.floatToIntBits(this.amount) == Float.floatToIntBits(other.getExpenseAmount())
					&& Objects.equals(this.category, other.getCategory()) 
					&& Objects.equals(this.payee, other.getPayee());
		} //end else
	} // End of the equals override

} // End of the ExpenseRecord class.
