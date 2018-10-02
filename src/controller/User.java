package controller;

import view.Console;

import java.io.IOException;

import model.Member;
import model.Registry;

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
			System.out.println(e);
		}
		c_view.displayWelcomeMessage();

		c_view.displayMenu();

		return readInput();
	}


	public boolean readInput() {
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
		case 9: return quit();
		}
		return true;
	}


	public boolean createMember() {
		String name = c_view.askForName();
		String pNum = c_view.askForPNum();								//Replace with method similar to askForName in Console
		try {
			registry.createMember(name, pNum);
		}
		catch (Exception e) {
			System.out.println(e);
		}
		return true;
	}	


	public boolean listMembers() {
		while(true) {
			c_view.listMembersMenu();
			int input = c_view.readInput();
			int lowest = 1, max = 2;
			if (input == 0) {
				return true;
			}
			else if (inputIsInvalid(input, lowest, max)) {
				continue;
			}
			else {
				switch (input) {
				case 1: return c_view.displayCompactList(registry);					
				case 2: return c_view.displayVerboseList(registry);					
				}
			}		
		}
	}


	public boolean deleteMember() {
		while (true) {
			c_view.memberToDelete();
			int input = c_view.readInput();
			if (input == 0) {
				return true;
			}
			else if (registry.idExist(input)) {
				if (areYouSure()) {					//User has chosen to go back
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
			if (input == 0) {
				return true;
			}
			else if (registry.idExist(input)) {
				if (!makeEditChoice(input)) {					//User has chosen to go back
					continue;
				}
				break;
			}
			else {
				c_view.displayInputError();
			}

		}
		return true;									//NOT STARTED
	}
	public boolean makeEditChoice(int memberID) {
		int lowest = 1, max = 2;
		while (true) {
			c_view.displayEditMenu();
			int input = c_view.readInput();
			if (input == 0) {
				return false;
			}
			else if (inputIsInvalid(input, lowest, max)) {
				continue;
			}
			else {
				//get specific member information
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


	public boolean viewSpecificMember() {
		while (true) {
			int input = c_view.askForID();

			if (input == 0) {
				return true;
			}
			else if (registry.idExist(input)) {
				Member m = registry.findMemberByID(input);
				c_view.printMemberInfo(m);
				c_view.pressEnterToContinue();
				break;
			}
			else {
				c_view.displayInputError();
			}
		}


		return true;
	}

	public boolean registerBoat() {
		while (true) {
			c_view.memberToAddBoat();
			int input = c_view.readInput();
			if (input == 0) {
				return true;
			}
			else if (registry.idExist(input)) {
				if (areYouSure()) {					//User has chosen to go back
					String type = chooseBoatType();
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




	public String chooseBoatType() {
		int min = 1, max = 4;
		while (true) {
			c_view.askForBoatType();
			int typeInput = c_view.readInput();
			if (inputIsInvalid(typeInput, min, max)) {
				continue;
			}
			else {
				switch (typeInput) {
				case 1: return "Sailboat";
				case 2: return "Motorsailer";
				case 3: return "Kayak/Canoe";
				case 4: return "Other";
				}
			}
		}
	}

	public boolean editBoat() {
		while (true) {
			c_view.membersBoatToEdit();
			int inputID = c_view.readInput();
			if (wantsToGoBack(inputID)) {
				return true;
			}
			else if (registry.idExist(inputID)) {
				if (areYouSure()) {	
					//Choose which boat to edit
					int boatToEdit = boatToEdit(inputID);
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
							String type = chooseBoatType();
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

	public int boatToEdit(int memberID) {
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

	public boolean deleteBoat() {
		while (true) {
			c_view.membersBoatToDelete();
			int inputID = c_view.readInput();
			if (wantsToGoBack(inputID)) {
				return true;
			}
			else if (registry.idExist(inputID)) {
				if (areYouSure()) {	
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


	public boolean wantsToGoBack(int input) {
		return input == 0;
	}

	public boolean areYouSure() {
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
	public boolean quit() {
		if (areYouSure()) {
			c_view.displayQuitMessage();
			try {
				registry.writeToMemberList();
			}
			catch (Exception e) {
				System.out.println(e);					//Should not be printed in final version
			}
			return false;
		}
		return true;							//Returning true keeps program running
	}


}
