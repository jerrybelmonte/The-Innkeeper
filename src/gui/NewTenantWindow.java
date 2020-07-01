package gui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

import main.Tenant;
import main.TenantList;

public class NewTenantWindow extends JFrame {
    private static final long serialVersionUID = 5231090802365917413L;
    private static TenantList tenants = null;
    private JPanel contentPane;
    private JTextField nameTextField;
    private JTextField apartmentTextField;

    public NewTenantWindow() {
	tenants = TenantList.getInstance();

	this.setTitle("Add New Tenant");
	this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
	this.setBounds(100, 100, 450, 300);
	this.setLocationRelativeTo(null);

	this.contentPane = new JPanel();
	this.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
	this.setContentPane(this.contentPane);
	this.contentPane.setLayout(null);

	JLabel nameLabel = new JLabel("Name:");
	nameLabel.setBounds(15, 70, 100, 20);
	nameLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
	nameLabel.setToolTipText("Enter the full name of the tenant");
	nameLabel.setHorizontalAlignment(SwingConstants.TRAILING);
	this.contentPane.add(nameLabel);

	this.nameTextField = new JTextField();
	this.nameTextField.setToolTipText("Enter the tenant's name");
	this.nameTextField.setBounds(120, 62, 205, 36);
	this.nameTextField.setHorizontalAlignment(SwingConstants.TRAILING);
	this.nameTextField.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
	this.contentPane.add(this.nameTextField);
	this.nameTextField.setColumns(10);

	JLabel apartmentLabel = new JLabel("Apartment:");
	apartmentLabel.setBounds(15, 135, 100, 20);
	apartmentLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
	apartmentLabel.setHorizontalAlignment(SwingConstants.TRAILING);
	this.contentPane.add(apartmentLabel);

	this.apartmentTextField = new JTextField();
	this.apartmentTextField.setToolTipText("Enter the tenant's apartment number");
	this.apartmentTextField.setBounds(120, 127, 205, 36);
	this.apartmentTextField.setHorizontalAlignment(SwingConstants.TRAILING);
	this.apartmentTextField.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
	this.contentPane.add(this.apartmentTextField);
	this.apartmentTextField.setColumns(10);

	JButton addTenantButton = new JButton("Add Tenant");
	addTenantButton.addActionListener(new ActionListener() {

	    @Override
	    public void actionPerformed(ActionEvent e) {
		String tenantName = NewTenantWindow.this.nameTextField.getText();
		String apartment = NewTenantWindow.this.apartmentTextField.getText();
		int apartmentNumber = Integer.valueOf(apartment);

		Tenant newTenant = null;
		if (apartmentNumber > 0 && tenantName.length() > 1) {
		    newTenant = new Tenant(tenantName, apartmentNumber);
		}

		try {
		    tenants.addTenant(newTenant);

		    JOptionPane
			    .showMessageDialog(null,
				    "Tenant's Name: " + tenants.getTenant(tenantName).getTenantName()
					    + "\nApartment #: " + tenants.getApartmentNumber(tenantName),
				    "New Tenant", JOptionPane.PLAIN_MESSAGE);

		    tenants.recordTenants();
		} catch (IllegalArgumentException IAE) {
		    JOptionPane.showMessageDialog(null, IAE.getMessage(), "Invalid Input", JOptionPane.ERROR_MESSAGE);
		}
	    }
	});
	addTenantButton.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
	addTenantButton.setBounds(150, 200, 130, 36);
	this.contentPane.add(addTenantButton);
    }

}
