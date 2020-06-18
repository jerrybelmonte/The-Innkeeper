package main;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

/**
 * The main menu controller singleton class.
 */
public class MainMenuController {
	/** The single and ONLY unique 'lazy' instance of the MainMenuController. */
	private static volatile MainMenuController mainMenu;
	/** Scanner to process user input. */
	private static Scanner sc = null;
	/** The text file for the list of tenants. */
	private static String tenantsTxtFile = "tenants.txt";
	/** The text file for the list of income records. */
	private static String incomeTxtFile = "income.txt";
	/** The text file for the list of expense records. */
	private static String expenseTxtFile = "expense.txt";
	/** The list of tenant's in the apartment building. */
	private TenantList tenants;
	/** The rental income payment report. */
	private RentalIncomeReport income;
	/** The expense payment report. */
	private ExpensePaymentReport expense;
	/** The annual summary report. */
	private AnnualSummary summary;


	/**
	 * The private constructor that only the MainMenuController class can access.
	 * 
	 * @param sc The input Scanner for the System.
	 */
	private MainMenuController(Scanner sc) {
		MainMenuController.sc = sc;
		this.tenants = new TenantList();
		this.income = new RentalIncomeReport();
		this.expense = new ExpensePaymentReport();
		this.summary = new AnnualSummary();
		this.loadTextFiles();
		this.importTextFiles();
	} // End of the private constructor for MainMenuController.


	/**
	 * Loads the files by creating a path to the text file.
	 */
	private void loadTextFiles() {
		Path tenantsPath = FileSystems.getDefault().getPath("", tenantsTxtFile);
		tenantsTxtFile = tenantsPath.toAbsolutePath().toString();
		
		Path incomePath = FileSystems.getDefault().getPath("", incomeTxtFile);
		incomeTxtFile = incomePath.toAbsolutePath().toString();
		
		Path expensePath = FileSystems.getDefault().getPath("", expenseTxtFile);
		expenseTxtFile = expensePath.toAbsolutePath().toString();
	} // End of the loadTextFiles method


	private void importTextFiles() {
		Path tenantsPath = Paths.get(tenantsTxtFile);
		Path incomePath = Paths.get(incomeTxtFile);
		Path expensePath = Paths.get(expenseTxtFile);
		
		if (Files.exists(tenantsPath)) {
			try {
				String list = Files.readString(tenantsPath);
				this.tenants = new TenantList(list);
			} catch (IOException e) { e.printStackTrace(); }
		} //end if
		
		if (Files.exists(incomePath)) {
			try {
				String list = Files.readString(incomePath);
				this.income = new RentalIncomeReport(list);
			} catch (IOException e) { e.printStackTrace(); }
		} //end if
		
		if (Files.exists(expensePath)) {
			try {
				String list = Files.readString(expensePath);
				this.expense = new ExpensePaymentReport(list);
			} catch (IOException e) { e.printStackTrace(); }
		} //end if
	} // End of the importTextFiles method


	/**
	 * Gets the name of the tenant from the console input using a scanner.
	 * 
	 * @return The tenant's name.
	 */
	private String getNameFromScanner() {
		Scanner in = MainMenuController.sc;
		
		String tenantName = "";
		System.out.print("Enter the tenant's name: ");
			
		if (in.hasNextLine()) {
			tenantName = in.nextLine();
		} //end if
		
		return tenantName;
	} // End of the getNameFromScanner method


	/**
	 * Gets the apartment number of the tenant from the console input using a scanner.
	 * 
	 * @return The tenant's apartment number.
	 */
	private int getAptNumFromScanner() {
		Scanner in = MainMenuController.sc;
		
		int apartmentNumber = 0;
		System.out.print("Enter the tenant's apartment number: ");
		
		if (in.hasNextInt()) {
			String line = in.nextLine();
			apartmentNumber = Integer.valueOf(line);
		} //end if
		
		return apartmentNumber;
	} // End of the getAptNumFromScanner method


	/**
	 * Gets the amount paid from the console input using a scanner.
	 * 
	 * @return The dollar amount paid.
	 */
	private float getAmountPaidFromScanner() {
		Scanner in = MainMenuController.sc;
		
		float amountPaid = 0;
		System.out.print("Enter the amount paid: ");
		
		if (in.hasNextFloat()) {
			String line = in.nextLine();
			amountPaid = Float.valueOf(line);
		} //end if
		
		return amountPaid;
	} // End of the getAmountPaidFromScanner method


	/**
	 * Gets the month number from the console input using a scanner.
	 * 
	 * @return The month number (1-12).
	 */
	private int getMonthNumFromScanner() {
		Scanner in = MainMenuController.sc;
		
		int monthNumber = 0;
		System.out.print("Enter the number for the month (1-12): ");
		
		if (in.hasNextInt()) {
			String line = in.nextLine();
			monthNumber = Integer.valueOf(line);
		} //end if
		
		return monthNumber;
	} // End of the getMonthNumFromScanner method


	/**
	 * Gets the day number from the console input using a scanner.
	 * 
	 * @return The day number (1-31).
	 */
	private int getDayNumFromScanner() {
		Scanner in = MainMenuController.sc;
		
		int dayNumber = 0;
		System.out.print("Enter the number for the day (1-31): ");
		
		if (in.hasNextInt()) {
			String line = in.nextLine();
			dayNumber = Integer.valueOf(line);
		} //end if
		
		return dayNumber;
	} // End of the getDayNumFromScanner method


	/**
	 * Gets the expense category from the console input using a scanner.
	 * 
	 * @return The expense category.
	 */
	private String getCategoryFromScanner() {
		Scanner in = MainMenuController.sc;
		
		String expenseCategory = "";
		System.out.print("Enter the expense category (Utilities): ");
			
		if (in.hasNextLine()) {
			expenseCategory = in.nextLine();
		} //end if
		
		return expenseCategory;
	} // End of the getCategoryFromScanner method


	/**
	 * Gets the expense payee from the console input using a scanner.
	 * 
	 * @return The expense payee.
	 */
	private String getPayeeFromScanner() {
		Scanner in = MainMenuController.sc;
		
		String expensePayee = "";
		System.out.print("Enter the payee (City Water): ");
			
		if (in.hasNextLine()) {
			expensePayee = in.nextLine();
		} //end if
		
		return expensePayee;
	} // End of the getPayeeFromScanner method


	/**
	 * Gets the unique 'lazy' MainMenuController instance. 
	 * Used instead calling the default constructor.
	 * 
	 * @return The one and only instance of MainMenuController.
	 */
	public static MainMenuController getMainMenu() {
		if (MainMenuController.mainMenu == null) { 			// Null check #1
			synchronized(MainMenuController.class) {		// Make this thread safe
				if (MainMenuController.mainMenu == null) { 	// Null check #2
					//Create the main menu singleton instance
					MainMenuController.mainMenu = new MainMenuController(new Scanner(System.in));		
				} //end inner if null check #2
			} //end synchronized scope
		} //end outer if null check #1
		return MainMenuController.mainMenu;
	} // End of the static getMainMenu method


	/**
	 * Adds a new tenant to the list of tenants.
	 */
	public void inputTenant() {
		Tenant newTenant = null;
		
		boolean inputIsValid = false;
		do {
			String tenantName = this.getNameFromScanner();
			int apartmentNumber = this.getAptNumFromScanner();
			
			if (apartmentNumber > 0 && tenantName.length() > 1) {
				newTenant = new Tenant(tenantName, apartmentNumber);
				inputIsValid = true;
			} //end if
		} while (!inputIsValid);
		
		try {
			this.tenants.addTenant(newTenant);
		} //end try 
		catch (IllegalArgumentException IAE) {
			System.out.println(IAE.getMessage());
		} //end catch
	} // End of the inputTenant method


	/**
	 * Displays the list of current tenants living in the apartment building.
	 */
	public void printTenantList() {
		String list = this.tenants.displayTenants();
		
		if (list != null) {
			System.out.println("\nApt# Tenant Name");
			for (int i = 0; i < 20; i++) { System.out.print("-"); }
			System.out.print("\n" + list);
		} //end if
		else {
			System.out.println("The list of tenants is empty.");
		} //end else
	} // End of the printTenantList method


	/**
	 * Records a rental payment.
	 */
	public void inputIncomeRecord() {
		
		String tenantName = this.getNameFromScanner();
		Tenant currentTenant = this.tenants.getTenant(tenantName);
		
		if (currentTenant != null) {

			int apartmentNum = currentTenant.getApartmentNumber();
			float amountPaid = this.getAmountPaidFromScanner();
			int monthRentIsDue = this.getMonthNumFromScanner();
			
			if (this.income.checkForTenantName(tenantName)) {
				this.income.insertRent(apartmentNum, monthRentIsDue, amountPaid);
			} //end if
			else {
				IncomeRecord rentRecord = new IncomeRecord(currentTenant);
				rentRecord.setRentAmount(monthRentIsDue, amountPaid);
				this.income.addRecord(rentRecord);
			} //end else
		} //end if
		else {
			System.out.println("No tenant with that name."); //error message
		} //end else
	} // End of the inputIncomeRecord method


	/**
	 * Displays rents.
	 */
	public void printIncomeReport() {
		//TODO: method to print the income report
		System.out.printf("%n%s %s %s %s %s %s %s %s %s %s %s %s %s %n",
				"AptNo", "Jan", "Feb", "Mar", "Apr", "May", "Jun", 
				"Jul", "Aug", "Sep", "Oct", "Nov", "Dec");
		
		for (int i = 0; i < 60; i++) { 
			System.out.print("-"); 
		} //end for
		
		System.out.println();
		
		String report = this.income.displayRecord();
		System.out.println(report);
	} // End of the printIncomeRecord method


	/**
	 * Records expense.
	 */
	public void inputExpenseRecord() {
		
		int monthNumber = this.getMonthNumFromScanner();
		int dayNumber = this.getDayNumFromScanner();
		String category = this.getCategoryFromScanner();
		String payee = this.getPayeeFromScanner();
		float amountPaid = this.getAmountPaidFromScanner();
		
		ExpenseRecord newExpense = new ExpenseRecord(monthNumber, dayNumber, category, payee, amountPaid);
		this.expense.addRecord(newExpense);
	} // End of the inputExpenseRecord method


	/**
	 * Displays expenses.
	 */
	public void printExpenseReport() {
		System.out.printf("%n%s %s %s %s %n", 
				"Date", "Payee", "Amount", "Category");
		for (int i = 0; i < 30; i++) { 
			System.out.print("-"); 
		} //end for
		
		System.out.println();
		
		String report = this.expense.toString();
		System.out.println(report);
	} // End of the printExpenseReport method


	/**
	 * Displays annual report.
	 */
	public void printAnnualSummary() {
		System.out.println("\nAnnual Summary\n---------------");

		//this.summary.displayIncomeReport(this.income);
		//this.summary.displayExpenseReport(this.expense);
		this.summary.displayBalance(this.income, this.expense);
	} // End of the printAnnualSummary method


	public void saveTextFiles() {
		this.tenants.recordTenants(tenantsTxtFile);
		this.income.recordIncomePayment(incomeTxtFile);
		this.expense.recordExpensePayment(expenseTxtFile);
	} // End of the saveTextFiles method

} // End of the MainMenuController class.
