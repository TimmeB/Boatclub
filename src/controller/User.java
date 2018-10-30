package controller;

import view.Console;
import model.Member;
import model.Registry;
import model.Boat.Type;

import java.io.IOException;



public class User {

	private Console c_view;
	private Registry registry;


	public boolean startProgram(Console console, Registry registry) {
		c_view = console;
		this.registry = registry;
		try {
			registry.readFromMemberList();
		}
		catch (IOException e) {
			
		}
		c_view.displayWelcomeMessage();

		c_view.displayMenu();

		return getMenuChoice();
	}


	public boolean getMenuChoice() {
		int input = c_view.readInput();

		switch (input) {
		case 1: return createMember();
		case 2: return listMembers();
		case 3: return deleteMember();
		case 4: return editMemberMain();
		case 5: return viewSpecificMember();
		case 6: return registerBoat();
		case 7: return deleteBoat();
		case 8: return editBoat();
		case 9: return keepProgramRunning();
		}
		return true;
	}

	
	
	//ADD MEMBERS


	public boolean createMember() {
		String name = c_view.askForName();
		String pNum = c_view.askForPNum();								
		try {
			registry.createMember(name, pNum);
		}
		catch (Exception e) {
		}
		return true;
	}	
	
	
	//LIST MEMBERS
	
	public boolean listMembers() {
		while(true) {
			c_view.listMembersMenu();
			int input = c_view.readInput();
			int lowest = 1, max = 2;
			if (wantsToGoBack(input)) {
				return true;
			}
			else if (inputIsInvalid(input, lowest, max)) {
				continue;
			}
			else {
				
				switch (input) {
				case 1: 
					c_view.printString("\n---------- COMPACT LIST ----------\n");
					for (Member m : registry.getMemberList()) {
						c_view.displayCompactInfo(m.getName(), m.getMemberID(), m.boatListSize());
					}
					c_view.pressEnterToContinue();
					break;
				case 2: 
					c_view.printString("\n---------- VERBOSE LIST ----------\n");
					for (Member m : registry.getMemberList()) {
						c_view.displayVerboseInfo(m.getName(), m.getMemberID(), m.getpNum(), registry.boatsToString(m.getMemberID()) );
					}
					c_view.pressEnterToContinue();
					break;
				}
				return true;
			}		
		}
	}
	
	
	//DELETE MEMBER
	
	public boolean deleteMember() {
		while (true) {
			c_view.memberToDelete();
			int input = c_view.readInput();
			if (wantsToGoBack(input)) {
				return true;
			}
			else if (registry.idExist(input)) {
				if (userConfirmation()) {					
					registry.deleteMember(input);
					return true;
				}
				continue;
			}
			else {
				c_view.displayInputError();
			}

		}
	}


	// EDIT MEMBER
	
	public boolean editMemberMain() {
		while (true) {
			c_view.memberToEdit();
			int input = c_view.readInput();
			if (wantsToGoBack(input)) {
				return true;
			}
			else if (registry.idExist(input)) {
				if (!makeEditChoice(input)) {					//This will be set as false if user chooses to go back
					continue;									//.. from makeEditChoice
				}
				break;
			}
			else {
				c_view.displayInputError();
			}

		}
		return true;									
	}
	public boolean makeEditChoice(int memberID) {
		int lowest = 1, max = 2;
		while (true) {
			c_view.displayEditMenu();
			int input = c_view.readInput();
			if (wantsToGoBack(input)) {
				return false;
			}
			else if (inputIsInvalid(input, lowest, max)) {
				continue;
			}
			else {
				switch (input) {
				case 1: return editName(memberID);
				case 2: return editpNum(memberID);
				}
			}
			break;
		}

		return true;
	}
	public boolean editName(int id) {
		String newName = c_view.askForName();
		registry.editName(newName, id);
		return true;
	}
	public boolean editpNum(int id) {
		String newpNum = c_view.askForPNum();
		registry.editpNum(newpNum, id);
		return true;
	}


	//VIEW MEMBER
	
	public boolean viewSpecificMember() {
		while (true) {
			int input = c_view.askForID();
			
			if (wantsToGoBack(input)) {
				return true;
			}
			else if (registry.idExist(input)) {
				Member m = registry.findMemberByID(input);
				c_view.displayVerboseInfo(m.getName(), m.getMemberID(), m.getpNum(), registry.boatsToString(m.getMemberID()) );
				c_view.pressEnterToContinue();
				break;
			}
			else {
				c_view.displayInputError();
			}
		}
		return true;
	}

	
	//ADD BOAT
	
	public boolean registerBoat() {
		while (true) {
			c_view.memberToAddBoat();
			int input = c_view.readInput();
			if (wantsToGoBack(input)) {
				return true;
			}
			else if (registry.idExist(input)) {
				if (userConfirmation()) {					
					Type type = chooseBoatType();
					int size = c_view.askForBoatSize();
					registry.addBoat(type, size, input);
					return true;
				}
			}
			else {
				c_view.displayInputError();
			}
		}
	}




	public Type chooseBoatType() {
		int min = 1, max = 4;
		while (true) {
			c_view.askForBoatType();
			int typeInput = c_view.readInput();
			if (inputIsInvalid(typeInput, min, max)) {
				continue;
			}
			else {
				switch (typeInput) {
				case 1: return Type.Sailboat;
				case 2: return Type.Motorsailer;
				case 3: return Type.Canoe;
				case 4: return Type.Other;
				}
			}
		}
	}
	
	
	//EDIT BOAT

	public boolean editBoat() {
		while (true) {
			//Choose which member
			c_view.membersBoatToEdit();
			int inputID = c_view.readInput();
			if (wantsToGoBack(inputID)) {
				return true;
			}
			else if (registry.idExist(inputID)) {
				if (userConfirmation()) {	
					//Choose which boat to edit
					int boatToEdit = chooseBoatToEdit(inputID);
					if (wantsToGoBack(boatToEdit)) {
						continue;
					}

					//Choose what to edit
					while (true) {
						int min = 1, max = 2;
						c_view.displayBoatEditMenu();
						int inputChoice = c_view.readInput();
						if (wantsToGoBack(inputChoice)) {
							return true;
						}
						else if (inputIsInvalid(inputChoice, min, max)) {
							continue;
						}
						else if (inputChoice == 1) {									//1 = edit type
							Type type = chooseBoatType();
							registry.editBoatType(boatToEdit, type, inputID);
						}
						else if (inputChoice == 2) {									//2 = edit size
							int size = c_view.askForBoatSize();
							registry.editBoatSize(boatToEdit, size, inputID);
						}
						return true;
					}
				}
			}
			else {
				continue;
			}
		}
	}

	public int chooseBoatToEdit(int memberID) {
		c_view.boatToEdit();
		String list = registry.boatsToString(memberID);
		Member m = registry.findMemberByID(memberID);
		int min = 1, max = m.boatListSize();
		while (true) {
			c_view.printString(list);
			int input = c_view.readInput();
			if (wantsToGoBack(input)) {
				return 0;
			}
			else if (inputIsInvalid(input, min, max)) {
				continue;
			}

			return input;
		}
	}
		
			
	//DELETE BOAT	

	public boolean deleteBoat() {
		while (true) {
			c_view.membersBoatToDelete();
			int inputID = c_view.readInput();
			if (wantsToGoBack(inputID)) {
				return true;
			}
			else if (registry.idExist(inputID)) {
				if (userConfirmation()) {	
					//Choose which boat to delete
					int boatToDelete = boatToDelete(inputID);
					if (wantsToGoBack(boatToDelete)) {
						continue;
					}
					else registry.deleteBoat(inputID, boatToDelete);
					return true;
				}
			}
		}
	}

	public int boatToDelete(int memberID) {
		c_view.boatToDelete();
		String list = registry.boatsToString(memberID);
		Member m = registry.findMemberByID(memberID);
		int min = 1, max = m.boatListSize();
		while (true) {
			c_view.printString(list);
			int input = c_view.readInput();
			if (wantsToGoBack(input)) {
				return 0;
			}
			else if (inputIsInvalid(input, min, max)) {
				continue;
			}

			return input;
		}
	}		

	
	//OTHER
	
	public boolean wantsToGoBack(int input) {
		return input == 0;
	}

	public boolean userConfirmation() {
		int yes = 1;
		while (true) {
			c_view.verifyChoice();
			int input = c_view.readInput();
			int lowest = 1, max = 2;
			if (inputIsInvalid(input, lowest, max)) {
				continue;
			}
			else if (input == yes) {
				return true;
			}
			else {
				return false;
			}
		}

	} 

	public boolean inputIsInvalid(int input, int lowestValue, int maxValue) {
		if (input < lowestValue || input > maxValue) {
			c_view.displayInputError();
			return true;
		}
		else {
			return false;
		}
	}
	public boolean keepProgramRunning() {
		if (userConfirmation()) {
			c_view.displayQuitMessage();
			try {
				registry.writeToMemberList();
			}
			catch (Exception e) {
									
			}
			return false;
		}
		return true;							//Returning true keeps program running
	}


}
