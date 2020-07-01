package main;

import java.util.Scanner;

/**
 * The User Interface class.
 *
 * @author Kate Nguyen
 */

public class UserInterface {
    // Static input scanner that can be accessed by the static methods.
    private static Scanner input = null;
    // Main menu that records data and prints reports.
    private static MainMenuController controller = null;

    // UserInterface main method for the console application.
    public static void main(String[] args) {
	input = new Scanner(System.in);

	startTheProgram();

	String password = "p@ssw0rd";
	logIn(password);

	input.close();
    } // End of the main method

    public static void endTheProgram() {
	controller.saveTextFiles();
	System.out.println("\n*** Thank you for using the INNKEEPER system! ***\n" + "\nGoodbye!\n");
    } // End of the endTheProgram method

    // Starts the program and displays a welcome message.
    public static void startTheProgram() {
	System.out.println("*** Welcome to INNKEEPER system! ***\n");
	controller = MainMenuController.getMainMenu(); // load the main menu controller
    } // End of the startTheProgram method

    // Verification method used to log into the system.
    public static void logIn(String password) {
	int count = 1;
	String temp = "";
	System.out.println("Please enter your password to Log In: ");
	temp = input.next();

	while (count < 3 && !temp.equals(password)) {
	    System.out.println("Wrong Password!!\n" + "Please re-enter your password: ");
	    temp = input.next();
	    count++;
	}

	if (temp.equals(password)) {
	    System.out.println("Success!!");
	    mainMenu();
	} else {
	    System.out.println("Failed Credential Verification!! Goodbye!");
	    System.exit(1);
	}
    } // End of the logIn method

    // Displays the main menu after logging in to the user.
    public static void mainMenu() {
	System.out.println("\nMain Menu:\n" + "1 - Input Data.\n" + "2 - Display a Report.\n" + "0 - Exit.\n\n"
		+ "Please enter your selection: ");
	String select = input.next();
	mainChoice(select);
    } // End of the mainMenu method

    // Handles a main menu choice selection.
    public static void mainChoice(String select) {
	switch (select) {
	case "1":
	    inputMenu();
	    break;
	case "2":
	    displayMenu();
	    break;
	case "0":
	    endTheProgram();
	    break;
	default:
	    System.out.println("Invalid input. Please try again!");
	    mainMenu();
	}
    } // End of the mainChoice method

    // Displays the input data menu to the user.
    public static void inputMenu() {
	System.out.println("\nInput Data:\n" + "1 - Add a new Tenant.\n" + "2 - Record a Rental Payment.\n"
		+ "3 - Record an Expense Payment.\n" + "0 - Return to Main Menu.\n\n"
		+ "Please enter your selection: ");
	String select = input.next();
	inputChoice(select);
    } // End of the inputMenu method

    // Records data from the user based on their selection.
    public static void inputChoice(String select) {
	switch (select) {
	case "1":
	    controller.inputTenant();
	    break;
	case "2":
	    controller.inputIncomeRecord();
	    break;
	case "3":
	    controller.inputExpenseRecord();
	    break;
	case "0":
	    mainMenu();
	    break;
	default:
	    System.out.println("Invalid input. Please try again!");
	    inputMenu();
	}
	mainMenu(); // return to the main menu
    } // End of the inputChoice method

    // Displays the print menu options to the user.
    public static void displayMenu() {
	System.out.println("\nDisplay a Report:\n" + "1 - Display Tenants.\n" + "2 - Display Rental Records.\n"
		+ "3 - Display Expense Records.\n" + "4 - Display Annual Summary.\n" + "0 - Return to Main Menu.\n\n"
		+ "Please enter your selection: ");
	String select = input.next();
	displayChoice(select);
    } // End of the displayMenu method

    // Prints the choice selected by the user.
    public static void displayChoice(String select) {
	switch (select) {
	case "1":
	    controller.printTenantList();
	    break;
	case "2":
	    controller.printIncomeReport();
	    break;
	case "3":
	    controller.printExpenseReport();
	    break;
	case "4":
	    controller.printAnnualSummary();
	    break;
	case "0":
	    mainMenu();
	    break;
	default:
	    System.out.println("Invalid input. Please try again!");
	    displayMenu();
	}
	mainMenu(); // return to the main menu
    } // End of the displayChoice method

} // End of the UserInterface class.