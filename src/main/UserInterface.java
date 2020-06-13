package main;

import java.util.Scanner;

public class UserInterface {

	public static void main(String[] args) {
        // read input from the user
		Scanner input = new Scanner(System.in);	
		
		startTheProgram();
		logIn(input);
		mainMenu(input);					
	}
	
	public static void startTheProgram() {
		System.out.println("*** Welcome to INNKEEPER system! ***\n\n");
				
	}
	
	public static void logIn(Scanner input) {
		System.out.println("Please enter your password to Log In: ");
	}
	
	public static void mainMenu(Scanner input) {
		System.out.println("\nMain Menu:\n"
				+ "1 - Input Data.\n"
				+ "2 - Display a Report.\n"
				+ "0 - Exit.\n\n"
				+ "Please enter your selection: ");
		String select = input.next();
		mainChoice(select, input);
	}
	
	public static void mainChoice(String select, Scanner input) {
		switch (select) {
			case "1":
				inputMenu(input);
				break;
			case "2":
				displayMenu(input);
				break;
			case "0":
				System.out.println("Goodbye!");
				break;
			default:
				System.out.println("Invalid input. Please try again!");
				mainMenu(input);
		}
	}
	
	public static void inputMenu(Scanner input) {
		System.out.println("\nInput Data:\n"
				+ "1 - Add a new Tenant.\n"
				+ "2 - Record a Rental Payment.\n"
				+ "3 - Record an Expense Payment.\n"
				+ "0 - Return to Main Menu.\n\n"
				+ "Please enter your selection: ");
		String select = input.next();
		inputChoice(select, input);
	}
	
	public static void inputChoice(String select, Scanner input) {
		switch (select) {
			case "1":
				;
				break;
			case "2":
				;
				break;
			case "3":
				;
				break;	
			case "0":
				mainMenu(input);
				break;
			default:
				System.out.println("Invalid input. Please try again!");
				inputMenu(input);
		}
	}
	
	public static void displayMenu(Scanner input) {
		System.out.println("\nDisplay a Report:\n"
				+ "1 - Display Tenants.\n"
				+ "2 - Display Rental Records.\n"
				+ "3 - Display Expense Records.\n"
				+ "4 - Display Annual Summary.\n"
				+ "0 - Return to Main Menu.\n\n"
				+ "Please enter your selection: ");
		String select = input.next();
		displayChoice(select, input);
	}
	
	public static void displayChoice(String select, Scanner input) {
		switch (select) {
			case "1":
				;
				break;
			case "2":
				;
				break;
			case "3":
				;
				break;	
			case "4":
				;
				break;	
			case "0":
				mainMenu(input);
				break;
			default:
				System.out.println("Invalid input. Please try again!");
				displayMenu(input);
		}
	}

}
