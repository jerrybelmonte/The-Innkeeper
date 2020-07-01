package gui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

import main.AnnualSummary;
import main.ExpensePaymentReport;
import main.RentalIncomeReport;
import main.TenantList;

public class DisplayReportWindow extends JFrame {
    private static final long serialVersionUID = -93356174726117955L;
    private static TenantList tenants = null;
    private static RentalIncomeReport income = null;
    private static ExpensePaymentReport expense = null;
    private static AnnualSummary summary = new AnnualSummary();
    private JPanel contentPane;

    public DisplayReportWindow() {
	this.setTitle("Display Report");
	this.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
	this.setBounds(100, 100, 450, 300);
	this.setLocationRelativeTo(null);

	this.contentPane = new JPanel();
	this.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
	this.contentPane.setLayout(null);
	this.setContentPane(this.contentPane);

	JButton displayTenantsButton = new JButton("Tenants");
	displayTenantsButton.setToolTipText("Display the list of tenants");
	displayTenantsButton.addActionListener(new ActionListener() {

	    @Override
	    public void actionPerformed(ActionEvent e) {
		tenants = TenantList.getInstance();
		String[] columnTitles = tenants.getTitlesForTable();
		String[][] allData = tenants.getTableData();
		JTable table = new JTable(allData, columnTitles);

		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(0, 0, 300, 278);

		JFrame frame = new JFrame("List of Tenants");
		frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		frame.setBounds(40, 50, 300, 300);
		frame.getContentPane().add(scrollPane);
		frame.setVisible(true);
	    }
	});
	displayTenantsButton.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 20));
	displayTenantsButton.setBounds(129, 15, 185, 40);
	this.contentPane.add(displayTenantsButton);

	JButton displayRentButton = new JButton("Rents");
	displayRentButton.setToolTipText("Display the rental payments");
	displayRentButton.addActionListener(new ActionListener() {

	    @Override
	    public void actionPerformed(ActionEvent e) {
		income = RentalIncomeReport.getInstance();
		String[] columnTitles = income.getTitlesForTable();
		String[][] allData = income.getTableData();
		JTable table = new JTable(allData, columnTitles);

		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(0, 0, 450, 278);

		JFrame frame = new JFrame("Rent Income");
		frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		frame.setBounds(10, 30, 1000, 300);
		frame.getContentPane().add(scrollPane);
		frame.setVisible(true);
	    }
	});
	displayRentButton.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 20));
	displayRentButton.setBounds(129, 68, 185, 40);
	this.contentPane.add(displayRentButton);

	JButton displayExpenseButton = new JButton("Expenses");
	displayExpenseButton.setToolTipText("Display the expense payments");
	displayExpenseButton.addActionListener(new ActionListener() {

	    @Override
	    public void actionPerformed(ActionEvent e) {
		expense = ExpensePaymentReport.getInstance();
		String[] columnTitles = expense.getTitlesForTable();
		String[][] allData = expense.getTableData();
		JTable table = new JTable(allData, columnTitles);

		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(0, 0, 450, 278);

		JFrame frame = new JFrame("Expense Payments");
		frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		frame.setBounds(30, 50, 600, 300);
		frame.getContentPane().add(scrollPane);
		frame.setVisible(true);
	    }
	});
	displayExpenseButton.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 20));
	displayExpenseButton.setBounds(129, 119, 185, 40);
	this.contentPane.add(displayExpenseButton);

	JButton displaySummaryButton = new JButton("Summary");
	displaySummaryButton.setToolTipText("Display the annual summary");
	displaySummaryButton.addActionListener(new ActionListener() {

	    @Override
	    public void actionPerformed(ActionEvent e) {
		income = RentalIncomeReport.getInstance();
		expense = ExpensePaymentReport.getInstance();
		String message = summary.displayBalance(income, expense);
		JOptionPane.showMessageDialog(null, message, "Annual Summary", JOptionPane.PLAIN_MESSAGE);
	    }
	});
	displaySummaryButton.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 20));
	displaySummaryButton.setBounds(129, 171, 185, 40);
	this.contentPane.add(displaySummaryButton);

	JButton mainMenuButton = new JButton("Main Menu");
	mainMenuButton.setToolTipText("Return to the main menu screen");
	mainMenuButton.addActionListener(new ActionListener() {

	    @Override
	    public void actionPerformed(ActionEvent e) {
		DisplayReportWindow.this.dispose();
	    }
	});
	mainMenuButton.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 20));
	mainMenuButton.setBounds(129, 223, 185, 40);
	this.contentPane.add(mainMenuButton);
    }

}
