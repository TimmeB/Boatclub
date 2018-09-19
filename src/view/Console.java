package view;

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
		System.out.println("2. Test Option");
		System.out.println("3. List Members");
		System.out.println("4. Quit");
		
	}
	
	public int readAction() {
		Scanner scan = new Scanner(System.in);
		int option = scan.nextInt();
		scan.close();
		return option;
	}
	
	
	
	public String askForName() {
		System.out.println("Enter name:");
		Scanner scan = new Scanner(System.in);
		String name = scan.nextLine();
		scan.close();
		return name;
	}
	
	public void displayMembers(Registry registry) {						//Temporary method
		System.out.println(registry.toString());
	}
	
	public void displayInputError() {
		System.out.println("Wrong input!");
	}
	
}
