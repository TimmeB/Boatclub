package view;

import java.util.InputMismatchException;
import java.util.Scanner;
import model.Registry;

public class Console {

	public Console() {
		
	}
	
	public void displayWelcomeMessage() {
		System.out.println("Welcome to Boatclub Software");
	}
	public void displayMenu() {
		System.out.println("What would you like to do? (Enter number)");
		System.out.println("1. Add Member");
		System.out.println("2. List Members");
		System.out.println("3. Delete Member");
		System.out.println("4. Edit Member Information");
		System.out.println("5. Quit");
		
	}
	
	public int readInput() {
		Scanner scan = new Scanner(System.in);						//closing scanner creates error
		try {
			int input = scan.nextInt();
			return input;
		}
		catch (InputMismatchException e){
			//displayInputError() will be called from controller
		}
		
		return 0;
	}
	
	
	public String askForName() {
		System.out.println("Enter name:");
		Scanner scan = new Scanner(System.in);						//closing scanner creates error
		String name = scan.nextLine();
		return name;
	}
	public String askForPNum() {
		System.out.println("Enter personal number (YYYYMMDDXXXX): ");
		Scanner scan = new Scanner(System.in);
		String pNum = scan.nextLine();
		return pNum;
	}
	
	public void displayMembers(Registry registry) {						//Temporary method
		System.out.println(registry.toString());
	}
	
	public void listMembersMenu() {
		System.out.println("How would you like to list members?");
		System.out.println("1. Compact List");
		System.out.println("2. Verbose List");
		System.out.println("3. Back");
	}
	
	public void displayInputError() {
		System.out.println("Wrong input!");
	}
	public void verifyChoice() {
		System.out.println("Are you sure?");
		System.out.println("1. Yes");
		System.out.println("2. No");
	}
	public void displayQuitMessage() {
		System.out.println("Application closed");
	}
	
}
