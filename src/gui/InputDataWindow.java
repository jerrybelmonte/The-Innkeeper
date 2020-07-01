package gui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

import main.ExpensePaymentReport;
import main.RentalIncomeReport;
import main.TenantList;

public class InputDataWindow extends JFrame {
    private static final long serialVersionUID = 2681075136266263124L;
    private JPanel contentPane;
    private static NewTenantWindow inputTenant = null;
    private static RentalPaymentWindow inputRent = null;
    private static ExpensePaymentWindow inputExpense = null;
    private static TenantList tenants = null;
    private static RentalIncomeReport income = null;
    private static ExpensePaymentReport expense = null;

    public InputDataWindow() {
	WindowListener listener = new WindowAdapter() {

	    @Override
	    public void windowClosed(WindowEvent e) {
		InputDataWindow.this.setVisible(true);
	    }
	};
	this.setTitle("Input Data");
	this.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
	this.setBounds(100, 100, 450, 300);
	this.setLocationRelativeTo(null);

	this.contentPane = new JPanel();
	this.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
	this.setContentPane(this.contentPane);
	this.contentPane.setLayout(null);

	JButton addTenantButton = new JButton("New Tenant");
	addTenantButton.setBounds(130, 35, 185, 40);
	addTenantButton.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 20));
	addTenantButton.setToolTipText("Add a new tenant");
	addTenantButton.addActionListener(new ActionListener() {

	    @Override
	    public void actionPerformed(ActionEvent e) {
		InputDataWindow.this.setVisible(false);

		if (tenants == null) {
		    tenants = TenantList.getInstance();
		}

		if (inputTenant == null) {
		    inputTenant = new NewTenantWindow();
		    inputTenant.addWindowListener(listener);
		}

		inputTenant.setVisible(true);
	    }
	});
	this.contentPane.add(addTenantButton);

	JButton addRentButton = new JButton("Rental Payment");
	addRentButton.setBounds(130, 90, 185, 40);
	addRentButton.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 20));
	addRentButton.setToolTipText("Record an income payment");
	addRentButton.addActionListener(new ActionListener() {

	    @Override
	    public void actionPerformed(ActionEvent e) {
		InputDataWindow.this.setVisible(false);

		if (income == null) {
		    income = RentalIncomeReport.getInstance();
		}

		if (inputRent == null) {
		    inputRent = new RentalPaymentWindow();
		    inputRent.addWindowListener(listener);
		}

		inputRent.setVisible(true);
	    }
	});
	this.contentPane.add(addRentButton);

	JButton addExpenseButton = new JButton("Expense Payment");
	addExpenseButton.setBounds(130, 145, 185, 40);
	addExpenseButton.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 20));
	addExpenseButton.setToolTipText("Record an expense payment");
	addExpenseButton.addActionListener(new ActionListener() {

	    @Override
	    public void actionPerformed(ActionEvent e) {
		InputDataWindow.this.setVisible(false);

		if (expense == null) {
		    expense = ExpensePaymentReport.getInstance();
		}

		if (inputExpense == null) {
		    inputExpense = new ExpensePaymentWindow();
		    inputExpense.addWindowListener(listener);
		}

		inputExpense.setVisible(true);
	    }
	});
	this.contentPane.add(addExpenseButton);

	JButton mainMenuButton = new JButton("Main Menu");
	mainMenuButton.setBounds(130, 200, 185, 40);
	mainMenuButton.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 20));
	mainMenuButton.setToolTipText("Return to the main menu screen");
	mainMenuButton.addActionListener(new ActionListener() {

	    @Override
	    public void actionPerformed(ActionEvent e) {
		if (tenants != null) {
		    tenants.recordTenants();
		}

		if (income != null) {
		    income.recordIncomePayment();
		}

		if (expense != null) {
		    expense.recordExpensePayment();
		}

		InputDataWindow.this.dispose();
	    }
	});
	this.contentPane.add(mainMenuButton);
    }

}
