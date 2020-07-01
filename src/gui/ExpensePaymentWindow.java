package gui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

import main.ExpensePaymentReport;
import main.ExpenseRecord;

public class ExpensePaymentWindow extends JFrame {
    private static final long serialVersionUID = -2165499756996423797L;
    private static ExpensePaymentReport expense = null;
    private JPanel contentPane;
    private JTextField categoryTextField;
    private JTextField payeeTextField;
    private JTextField amountTextField;

    public ExpensePaymentWindow() {
	expense = ExpensePaymentReport.getInstance();

	this.setTitle("Expense Payment");
	this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
	this.setBounds(100, 100, 450, 300);
	this.setLocationRelativeTo(null);

	this.contentPane = new JPanel();
	this.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
	this.setContentPane(this.contentPane);
	this.contentPane.setLayout(null);

	JLabel lblCategory = new JLabel("Category:");
	lblCategory.setBounds(11, 42, 104, 20);
	lblCategory.setHorizontalAlignment(SwingConstants.TRAILING);
	lblCategory.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
	this.contentPane.add(lblCategory);

	this.categoryTextField = new JTextField();
	this.categoryTextField.setBounds(120, 35, 205, 36);
	this.categoryTextField.setToolTipText("Enter the expense category (Insurance, Utilities)");
	this.categoryTextField.setHorizontalAlignment(SwingConstants.TRAILING);
	this.categoryTextField.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
	this.contentPane.add(this.categoryTextField);
	this.categoryTextField.setColumns(10);

	JLabel lblPayee = new JLabel("Payee:");
	lblPayee.setBounds(66, 82, 49, 20);
	lblPayee.setHorizontalAlignment(SwingConstants.TRAILING);
	lblPayee.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
	this.contentPane.add(lblPayee);

	this.payeeTextField = new JTextField();
	this.payeeTextField.setBounds(120, 75, 205, 36);
	this.payeeTextField.setToolTipText("Enter the payee (All State, City Water)");
	this.payeeTextField.setHorizontalAlignment(SwingConstants.TRAILING);
	this.payeeTextField.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
	this.payeeTextField.setColumns(10);
	this.contentPane.add(this.payeeTextField);

	JLabel lblAmount = new JLabel("Amount:");
	lblAmount.setBounds(48, 122, 67, 20);
	lblAmount.setHorizontalAlignment(SwingConstants.TRAILING);
	lblAmount.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
	this.contentPane.add(lblAmount);

	this.amountTextField = new JTextField();
	this.amountTextField.setBounds(120, 115, 205, 36);
	this.amountTextField.setToolTipText("Enter the amount paid $");
	this.amountTextField.setHorizontalAlignment(SwingConstants.TRAILING);
	this.amountTextField.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
	this.amountTextField.setColumns(10);
	this.amountTextField.setText("0.0");
	this.contentPane.add(this.amountTextField);

	JLabel lblMonth = new JLabel("Month:");
	lblMonth.setBounds(11, 163, 104, 20);
	lblMonth.setHorizontalAlignment(SwingConstants.TRAILING);
	lblMonth.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
	this.contentPane.add(lblMonth);

	SpinnerModel monthValues = new SpinnerNumberModel(1, 1, 12, 1);
	JSpinner monthSpinner = new JSpinner(monthValues);
	monthSpinner.setBounds(120, 155, 75, 36);
	monthSpinner.setToolTipText("Expense payment month(1-12)");
	monthSpinner.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
	this.contentPane.add(monthSpinner);

	JLabel lblDay = new JLabel("Day:");
	lblDay.setBounds(205, 163, 40, 20);
	lblDay.setHorizontalAlignment(SwingConstants.TRAILING);
	lblDay.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
	this.contentPane.add(lblDay);

	SpinnerModel dayValues = new SpinnerNumberModel(1, 1, 31, 1);
	JSpinner daySpinner = new JSpinner(dayValues);
	daySpinner.setBounds(250, 155, 75, 36);
	daySpinner.setToolTipText("Expense payment day(1-31)");
	daySpinner.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
	this.contentPane.add(daySpinner);

	JButton submitButton = new JButton("Submit");
	submitButton.setBounds(150, 220, 130, 36);
	submitButton.addActionListener(new ActionListener() {

	    @Override
	    public void actionPerformed(ActionEvent e) {
		String category = ExpensePaymentWindow.this.categoryTextField.getText();
		String payee = ExpensePaymentWindow.this.payeeTextField.getText();
		float amount = Float.valueOf(ExpensePaymentWindow.this.amountTextField.getText());

		Object month = String.valueOf(monthSpinner.getValue());
		int monthNumber = Integer.valueOf((String) month);
		Object day = String.valueOf(daySpinner.getValue());
		int dayNumber = Integer.valueOf((String) day);

		ExpenseRecord payment = new ExpenseRecord(monthNumber, dayNumber, category, payee, amount);
		expense.addRecord(payment);

		JOptionPane.showMessageDialog(null, payment.toString(), "New Expense Payment",
			JOptionPane.PLAIN_MESSAGE);

		expense.recordExpensePayment();
	    }
	});
	submitButton.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
	this.contentPane.add(submitButton);
    }

}
