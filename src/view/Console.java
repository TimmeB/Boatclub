package view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.InputMismatchException;
import java.util.Scanner;
import model.Registry;
import model.Member;

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
		System.out.println("5. View Specific Member");
		System.out.println("6. Register Boat");
		System.out.println("7. Quit");
		
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
		
		return -1;
	}
	public void pressEnterToContinue() {
		Scanner scan = new Scanner(System.in);
		System.out.println("Press enter to continue..");
		scan.nextLine();
	}
	
	
	public String askForName() {
		System.out.println("Enter name:");
		
		Scanner scan = new Scanner(System.in);						//closing scanner creates error
		String name = scan.nextLine();
		
		/*
		InputStreamReader reader = new InputStreamReader(System.in);
	    BufferedReader in = new BufferedReader(reader);
	    String name = "";
	    boolean validInput = true;
	    while(validInput)
	    try {
	    	name = in.readLine();
	    	return name;
	    }
	    catch (IOException e) {
	    	//e.printStackTrace();
	    } */
		return name;
	}
	public String askForPNum() {
		System.out.println("Enter personal number (YYYYMMDDXXXX): ");
		Scanner scan = new Scanner(System.in);
		String pNum = scan.nextLine();
		return pNum;
	}
	public int askForID() {
		while(true) {
			System.out.println("Which member do you want to view? (Enter ID or '0' to go back)");
			int input = readInput();
			int badInput = -1;
			if (input == badInput) {
				displayInputError();
				continue;
			}
			else {
				return input;
			}
		}
	}
	
	public boolean displayCompactList(Registry registry) {						//Temporary method
		System.out.println("\n---------- COMPACT LIST ----------\n");
		System.out.println(registry.toCompactListString());
		pressEnterToContinue();
		return true;
	}
	public boolean displayVerboseList(Registry registry) {
		System.out.println("\n---------- VERBOSE LIST ----------\n");
		System.out.println(registry.toVerboseListString());
		pressEnterToContinue();
		return true;
	}
	
	public void listMembersMenu() {
		System.out.println("How would you like to list members?");
		System.out.println("1. Compact List");
		System.out.println("2. Verbose List");
		System.out.println("0. Back");
	}
	
	public void displayEditMenu() {
		System.out.println("What would you like to edit?");
		System.out.println("1. Name");
		System.out.println("2. Personal Number");
		System.out.println("0. Back");
	}
	
	public void memberToDelete() {
		System.out.println("Which member would you like to delete? (Enter ID or '0' to go back)");
	}
	
	public void memberToEdit() {
		System.out.println("Which member would you like to edit? (Enter ID or '0' to go back)");
	}
	
	public void printMemberInfo(Member m) {
		String name = m.getName(), pNum = m.getpNum();
		int memberID = m.getMemberID();
		
		System.out.println("Name: " + name + "\t\tPNR: " + pNum + "\t\tMember ID: " + memberID + "\n");
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
