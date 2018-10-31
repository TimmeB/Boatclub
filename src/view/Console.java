package view;


import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import model.Boat;

public class Console {
	private int userInput;	
	
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
		System.out.println("7. Delete Boat");
		System.out.println("8. Edit Boat Information");
		System.out.println("9. Quit");
		
	}
	
	public void readInput() {
		Scanner scan = new Scanner(System.in);						//closing scanner creates error
		try {
			userInput = scan.nextInt();
		}
		catch (InputMismatchException e){
			userInput = -1;
		}
		
	}
	public void pressEnterToContinue() {
		Scanner scan = new Scanner(System.in);						//closing scanner creates error
		System.out.println("Press enter to continue..");
		scan.nextLine();
	}
	
	public String askForName() {
		System.out.println("Enter name:");
		Scanner scan = new Scanner(System.in);						//closing scanner creates error
		String name = scan.nextLine();
		return name;
	}
	public String askForPNum() {
		System.out.println("Enter personal number (YYYYMMDDXXXX): ");
		Scanner scan = new Scanner(System.in);								//closing scanner creates error
		String pNum = scan.nextLine();
		return pNum;
	}
	public int askForID() {
		while(true) {
			readInput();
			int badInput = -1;
			if (userInput == badInput) {
				return badInput;
			}
			else {
				return userInput;
			}
		}
	}
	public int askForBoatID() {
		while(true) {
			readInput();
			int badInput = -1;
			if (userInput == badInput) {
				return badInput;
			}
			else {
				return userInput;
			}
		}
	}
	
	public void displayCompactInfo(String name, int memberID, int numberOfBoats) {						
		System.out.println("Name: " + name + "\t\t\tID: " + memberID + "\t\t\tNumber of Boats: " 
					+ numberOfBoats + "\n" + "-------------------------------------------------------------------------------\n");
	}
	public void displayVerboseInfo(String name, int memberID, String pNum, ArrayList<Boat> boats) {
		System.out.print("-------------------------------------------------------------------------------\n"
				+ "Name: " + name + "\t\t\tID: " + memberID + "\t\t\tPNR: " + pNum
				+ "\n\nBoats: \n"
				+ boatsToString(boats) 
				+ "\n" + "-------------------------------------------------------------------------------\n");
	}
	
	public String boatsToString(ArrayList<Boat> boats) {
		if (boats.size() == 0)
			return "No boats currently registered"; 
		try {
			String boatListStr = "";
			int count = 1;
			for (Boat b : boats) {
				boatListStr += count + ". " + "Type: " + b.getType() + "\t\t\tLength: " + b.getLength() + "\n";
				count++;
			}
			return boatListStr;
		}
		catch (IndexOutOfBoundsException e) {
			return "No boats currently registered";
		}
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
	
	public void displayBoatEditMenu() {
		System.out.println("What would you like to edit?");
		System.out.println("1. Type");
		System.out.println("2. Size");
		System.out.println("0. Back");
	}
	
	public void memberToDelete() {
		System.out.println("Which member would you like to delete? (Enter ID or '0' to go back)");
	}
	
	public void memberToEdit() {
		System.out.println("Which member would you like to edit? (Enter ID or '0' to go back)");
	}
	
	public void boatToEdit() {
		System.out.println("Which boat would you like to edit? ('0' to go back)");
	}
	
	public void boatToDelete() {
		System.out.println("Which boat would you like to Delete? ('0' to go back)");
	}
	
	public void memberToAddBoat() {
		System.out.println("To which member should the boat be added? (Enter ID or '0' to go back)");
	}
	public void membersBoatToEdit() {
		System.out.println("Which members boat would you like to edit? (Enter ID or '0' to go back)");
	}
	
	public void membersBoatToDelete() {
		System.out.println("Which members boat would you like to delete? (Enter ID or '0' to go back)");
	}
	
	public void printString(String s) {
		System.out.println(s);
	}
	public void askForBoatType() {				
		System.out.println("What type of boat would you like to register?");
		System.out.println("1. Sailboat");
		System.out.println("2. Motorsailer");
		System.out.println("3. Kayak/Canoe");
		System.out.println("4. Other");
	}
	
	public int askForBoatSize() {
		System.out.println("Enter boatsize: ");
		Scanner sc = new Scanner(System.in);					//closing scanner creates error
		int size = sc.nextInt();
		return size;
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
	
	
	// SECTION FOR USER CHOICES
	 public boolean wantsToAddMember() {
	  return (userInput == 1);
	 }
	 
	 public boolean wantsToListMembers() {
	  return userInput == 2;
	 }
	 
	 public boolean wantsToDeleteMember() {
	  return userInput == 3;
	 }
	 
	 public boolean wantsToEditMember() {
	  return userInput == 4;
	 }
	 
	 public boolean wantsToViewMember() {
	  return userInput == 5;
	 }
	 
	 public boolean wantsToRegisterBoat() {
	  return userInput == 6;
	 }
	 
	 public boolean wantsToDeleteBoat() {
	  return userInput == 7;
	 }
	 
	 public boolean wantsToEditBoat() {
	  return userInput == 8;
	 }
	 
	 public boolean wantsToQuit() {
	  return userInput == 9;
	 }
	 
	 public boolean wantsToListCompact() {
	  return userInput == 1;
	 }
	 
	 public boolean wantsToListVerbose() {
	  return userInput == 2;
	 }
	 
	 public boolean wantsToGoBack() {
	  return userInput == 0;
	 }
	 
	 public boolean wantsToEditMemberName() {
	  return userInput == 1;
	 }
	 
	 public boolean wantsToEditPersonalNumber() {
	  return userInput == 2;
	 }
	 
	 public boolean wantsToEditBoatType() {
	  return userInput == 1;
	 }
	 
	 public boolean wantsToEditBoatSize() {
	  return userInput == 2;
	 }
	 
	 public boolean wantsToProceed() {
		 return userInput == 1;
	 }
	 public boolean dontWantToProceed() {
		 return userInput == 2;
	 }
	 
	 public boolean wantsToRegSailboat() {
		 return userInput == 1;
	 }
	 public boolean wantsToRegMotorsailer() {
		 return userInput == 2;
	 }
	 public boolean wantsToRegCanoe() {
		 return userInput == 3;
	 }
	 public boolean wantsToRegOther() {
		 return userInput == 4;
	 }
}
