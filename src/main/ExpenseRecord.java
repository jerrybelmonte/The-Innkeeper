package main;

import java.util.Objects;

/**
 * An expense category payment record.
 */
public class ExpenseRecord implements Comparable<ExpenseRecord> {
    private int month;
    private int day;
    private String category;
    private String payee;
    private float amount;

    /**
     * Normal constructor for the ExpenseRecord class.
     *
     * @param month    The month number (1-12).
     * @param day      The day of the month (1-31).
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
    } // End of the String constructor.

    /**
     * @return The month number.
     */
    public int getMonth() {
	return this.month;
    } // End of the getMonth getter

    /**
     * @return The day number.
     */
    public int getDay() {
	return this.day;
    } // End of the getDay getter

    /**
     * @return The expense category.
     */
    public String getCategory() {
	return this.category;
    } // End of the getCategory getter

    /**
     * @return The payee that we wrote a check to.
     */
    public String getPayee() {
	return this.payee;
    } // End of the getPayee getter

    /**
     * @return The amount paid.
     */
    public float getExpenseAmount() {
	return this.amount;
    } // End of the getExpenseAmount getter

    /**
     * @return A record of the expense.
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

	sb.append(String.format("%d/%d", this.month, this.day));
	sb.append(", ");
	sb.append(this.payee);
	sb.append(", ");
	sb.append(String.format("$%,.2f", this.amount));
	sb.append(", ");
	sb.append(this.category);

	return sb.toString();
    }// End of the toString override

    @Override
    public int compareTo(ExpenseRecord o) {
	int monthDifference = this.month - o.getMonth();

	if (monthDifference > 0) {
	    return 1;
	} else if (monthDifference < 0) {
	    return -1;
	} else { // months are equal, check day
	    int dayDifference = this.day - o.getDay();

	    if (dayDifference > 0) {
		return 1;
	    } else if (dayDifference < 0) {
		return -1;
	    } else {
		return 0;
	    }
	}
    } // End of the compareTo method

    @Override
    public int hashCode() {
	return Objects.hash(this.amount, this.category, 
		this.day, this.month, this.payee);
    } // End of the hashCode override

    @Override
    public boolean equals(Object obj) {
	if (this == obj) {
	    return true;
	}

	if (!(obj instanceof ExpenseRecord)) {
	    return false;
	}

	ExpenseRecord other = (ExpenseRecord) obj;
	int dateDifference = this.compareTo(other);

	if (dateDifference != 0) {
	    return false;
	} else {
	    return Float.floatToIntBits(this.amount) 
		    == Float.floatToIntBits(other.getExpenseAmount())
		    && Objects.equals(this.category, other.getCategory())
		    && Objects.equals(this.payee, other.getPayee());
	}
    } // End of the equals override

} // End of the ExpenseRecord class.
