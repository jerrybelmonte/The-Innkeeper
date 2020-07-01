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

import main.IncomeRecord;
import main.RentalIncomeReport;
import main.Tenant;
import main.TenantList;

public class RentalPaymentWindow extends JFrame {
    private static final long serialVersionUID = 2084634194079008651L;
    private static RentalIncomeReport income = null;
    private static TenantList tenants = null;
    private JPanel contentPane;
    private JTextField nameTextField;

    public RentalPaymentWindow() {
	income = RentalIncomeReport.getInstance();
	tenants = TenantList.getInstance();

	this.setTitle("Rental Payment");
	this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
	this.setBounds(100, 100, 450, 300);
	this.setLocationRelativeTo(null);

	this.contentPane = new JPanel();
	this.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
	this.setContentPane(this.contentPane);
	this.contentPane.setLayout(null);

	JLabel nameLabel = new JLabel("Name:");
	nameLabel.setBounds(15, 38, 100, 30);
	nameLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
	nameLabel.setHorizontalAlignment(SwingConstants.TRAILING);
	this.contentPane.add(nameLabel);

	this.nameTextField = new JTextField();
	this.nameTextField.setToolTipText("Enter the tenant's full name");
	this.nameTextField.setHorizontalAlignment(SwingConstants.TRAILING);
	this.nameTextField.setBounds(120, 36, 205, 36);
	this.nameTextField.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
	this.contentPane.add(this.nameTextField);
	this.nameTextField.setColumns(10);

	JLabel lblAmountPaid = new JLabel("Amount:");
	lblAmountPaid.setBounds(20, 88, 95, 30);
	lblAmountPaid.setToolTipText("");
	lblAmountPaid.setHorizontalAlignment(SwingConstants.TRAILING);
	lblAmountPaid.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
	this.contentPane.add(lblAmountPaid);

	JTextField amountTextField = new JTextField();
	amountTextField.setToolTipText("Enter the amount paid $");
	amountTextField.setHorizontalAlignment(SwingConstants.TRAILING);
	amountTextField.setBounds(120, 86, 205, 36);
	amountTextField.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
	amountTextField.setText("0.0");
	this.contentPane.add(amountTextField);
	amountTextField.setColumns(10);

	JLabel lblRentMonth = new JLabel("Month:");
	lblRentMonth.setBounds(20, 138, 95, 30);
	lblRentMonth.setHorizontalAlignment(SwingConstants.TRAILING);
	lblRentMonth.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
	this.contentPane.add(lblRentMonth);

	SpinnerModel values = new SpinnerNumberModel(1, 1, 12, 1);
	JSpinner monthSpinner = new JSpinner(values);
	monthSpinner.setToolTipText("Choose the month for the rent (1-12)");
	monthSpinner.setBounds(120, 136, 100, 36);
	monthSpinner.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
	this.contentPane.add(monthSpinner);

	JButton submitButton = new JButton("Submit");
	submitButton.addActionListener(new ActionListener() {

	    @Override
	    public void actionPerformed(ActionEvent e) {
		String tenantName = RentalPaymentWindow.this.nameTextField.getText();
		Tenant currentTenant = tenants.getTenant(tenantName);

		if (currentTenant != null) {
		    int apartmentNumber = currentTenant.getApartmentNumber();
		    float amountPaid = Float.valueOf(amountTextField.getText());
		    Object monthValue = String.valueOf(monthSpinner.getValue());
		    int monthRentIsDue = Integer.valueOf((String) monthValue);

		    if (income.checkForTenantName(tenantName)) {
			income.insertRent(apartmentNumber, monthRentIsDue, amountPaid);
		    } else {
			IncomeRecord rentRecord = new IncomeRecord(currentTenant);
			rentRecord.setRentAmount(monthRentIsDue, amountPaid);
			income.addRecord(rentRecord);
		    }

		    IncomeRecord rent = income.getRowFromAptNum(apartmentNumber);
		    JOptionPane.showMessageDialog(null,
			    "Name: " + rent.getTenant().getTenantName() + "\nApartment: "
				    + rent.getTenant().getApartmentNumber()
				    + String.format("%nAmount: $%,.2f%n", rent.getRentAmount(monthRentIsDue)),
			    "Rent Payment", JOptionPane.PLAIN_MESSAGE);

		    income.recordIncomePayment();
		} else {
		    JOptionPane.showMessageDialog(null, "No tenant with that name", "Invalid Input",
			    JOptionPane.ERROR_MESSAGE);
		}
	    }
	});
	submitButton.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
	submitButton.setBounds(150, 210, 130, 36);
	this.contentPane.add(submitButton);
    }

}
