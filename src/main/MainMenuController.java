package main;

import java.util.Scanner;

/**
 * The main menu controller singleton class.
 */
public class MainMenuController {
	/** The single and ONLY unique 'lazy' instance of the MainMenuController. */
	private static volatile MainMenuController mainMenu;
	/** Scanner to process user input. */
	private Scanner input = null;
	/** The list of tenant's in the apartment building. */
	private TenantList tenants;
	private static RentalIncomeReport iReport;
	private ExpensePaymentReport eReport;
	private AnnualSummary summary;
	
	/**
	 * The private constructor that only the MainMenuController class can access.
	 */
	private MainMenuController() {
		this.input = new Scanner(System.in);
		this.tenants = new TenantList();
		this.iReport = new RentalIncomeReport();
		this.eReport = new ExpensePaymentReport();
		this.summary = new AnnualSummary();
	} // End of the private constructor for MainMenuController.
	
	
	/**
	 * Gets the unique 'lazy' MainMenuController instance. 
	 * Used instead calling the default constructor.
	 * 
	 * @return The one and only instance of MainMenuController.
	 */
	public static MainMenuController getMainMenu() {
		if (MainMenuController.mainMenu == null) { 				// Null check #1
			synchronized(MainMenuController.class) {			// Make this thread safe
				if (MainMenuController.mainMenu == null) { 		// Null check #2
					//Create the main menu singleton instance
					MainMenuController.mainMenu = new MainMenuController();		
				} //end inner if null check #2
			} //end synchronized scope
		} //end outer if null check #1
		return MainMenuController.mainMenu;
	} // End of the static getMainMenu method

	
	/**
	 * Adds a new tenant to the list of tenants.
	 */
	public static void inputTenant() {
		MainMenuController menu = MainMenuController.getMainMenu();
		Tenant newTenant = null;
		
		boolean inputIsValid = false;
		do {
			System.out.print("\nEnter the tenant's name (Bob Smith): ");
			String tenantName = "";
			
			if (menu.input.hasNext()) {
				tenantName = menu.input.nextLine();
			} //end if
			
			System.out.print("Enter the tenant's apartment number (101): ");
			int apartmentNumber = 0;
			
			if (menu.input.hasNextInt()) {
				String line = menu.input.nextLine();
				apartmentNumber = Integer.valueOf(line);
			} //end if
			
			//TODO: regular expression for name: [A-Za-z]+
			if (apartmentNumber > 0 && tenantName.length() > 1) {
				newTenant = new Tenant(tenantName, apartmentNumber);
				inputIsValid = true;
			} //end if
		} while (!inputIsValid);
		
		try {
			menu.tenants.addTenant(newTenant);
		} //end try 
		catch (IllegalArgumentException IAE) {
			System.out.println(IAE.getMessage());
		} //end catch
	} // End of the inputTenant method
	
	
	/**
	 * Displays the list of current tenants living in the apartment building.
	 */
	public static void printTenantList() {
		MainMenuController menu = MainMenuController.getMainMenu();
		System.out.println("\nApt# Tenant Name");
		
		for (int i = 0; i < 20; i++) { 
			System.out.print("-"); 
		} //end for
		
		System.out.print("\n" + menu.tenants.displayTenants());
	} // End of the printTenantList method
	
	
	public static void inputIncomeRecord() {
		//TODO: method to input a rental record
		MainMenuController menu = MainMenuController.getMainMenu();
		IncomeRecord newRecord = null;
		
		boolean inputIsValid = false;
		do {
			System.out.print("\nEnter the tenant's name (Bob Smith): ");
			String tenantName = "";
			
			if (menu.input.hasNext()) {
				tenantName = menu.input.nextLine();
			} //end if
			
			System.out.print("Amount of rent recieved ($99.87): ");
			float amount = 0;
			
			if (menu.input.hasNextInt()) {
				String line = menu.input.nextLine();
				amount = Integer.valueOf(line);
			} //end if
			
			System.out.print("Enter the month the rental is for (1-12): ");
			int month = 0;
			
			if (menu.input.hasNextInt()) {
				String line = menu.input.nextLine();
				month = Integer.valueOf(line);
			} //end if

			if (tenantName.length() > 1 && amount > 0 && month > 0 && month < 13)
			{
				newRecord = new IncomeRecord(tenantName, amount, month);
				inputIsValid = true;
			}
			
		} while (!inputIsValid);

		try {
			menu.iReport.addRecord(newRecord);
		}
		catch (IllegalArgumentException IAE) {
			System.out.println(IAE.getMessage());
		}
		
	} // End of the inputTenant method
	
	
	
	public static void printIncomeReport() {
		//TODO: method to print the income report
		MainMenuController menu = MainMenuController.getMainMenu();
		System.out.println("\nAptNo Jan Feb Mar Apr May Jun Jul Aug Sep Oct Nov Dec");

		for (int i = 0; i < 55; i++) {
			System.out.print("-");
		} //end for

		System.out.print("\n" + menu.iReport.displayRecord());
	}
	
	
	public static void inputExpenseRecord() {
		//TODO: method to input an expense
	}
	
	
	public static void printExpenseReport() {
		//TODO: method to print the expense report
	}
	
	
	public static void printAnnualSummary() {
		MainMenuController menu = MainMenuController.getMainMenu();
		
		System.out.println("\nAnnual Summary\n---------------");

		menu.summary.displayIncomeReport(menu.iReport);
		menu.summary.displayExpenseReport(menu.eReport);
		menu.summary.displayBalance();
	}
	
}
