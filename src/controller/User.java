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
		c_view.readInput();
		
		if (c_view.wantsToAddMember()) 
			createMember();
		else if (c_view.wantsToListMembers())
			listMembers();
		else if (c_view.wantsToDeleteMember())
			deleteMember();
		else if (c_view.wantsToEditMember())
			editMemberMain();
		else if (c_view.wantsToViewMember())
			viewSpecificMember();
		else if (c_view.wantsToRegisterBoat())
			registerBoat();
		else if (c_view.wantsToDeleteBoat())
			deleteBoat();
		else if (c_view.wantsToEditBoat())
			editBoat();
		else if (c_view.wantsToQuit())
			return keepProgramRunning();
		
		
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
			c_view.readInput();
			if (c_view.wantsToGoBack()) {
				return true;
			}
			else if (c_view.wantsToListCompact()) {
				c_view.printString("\n---------- COMPACT LIST ----------\n");
				for (Member m : registry.getMemberList()) {
					c_view.displayCompactInfo(m.getName(), m.getMemberID(), m.boatListSize());
				}
				c_view.pressEnterToContinue();
				break;
			}
			else if (c_view.wantsToListVerbose()) { 
				c_view.printString("\n---------- VERBOSE LIST ----------\n");
				for (Member m : registry.getMemberList()) {
					c_view.displayVerboseInfo(m.getName(), m.getMemberID(), m.getpNum(), m.getBoatList() );
				}
				c_view.pressEnterToContinue();
				break;
			}
			else {
				continue;
			}
		}	
		return true;
	}
	
	
	//DELETE MEMBER
	
	public boolean deleteMember() {
		while (true) {
			c_view.memberToDelete();
			int input = c_view.askForID();
			if (c_view.wantsToGoBack()) {
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
			int input = c_view.askForID();
			if (c_view.wantsToGoBack()) {
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
		while (true) {
			c_view.displayEditMenu();
			c_view.readInput();
			if (c_view.wantsToGoBack()) {
				return false;
			}
			else if (c_view.wantsToEditMemberName()) {
				editName(memberID);
			}
			else if (c_view.wantsToEditPersonalNumber()) {
				editpNum(memberID);
			}
			else {
				continue;
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
			c_view.printString("Which member do you want to view? (Enter ID or '0' to go back)");
			int input = c_view.askForID();
			
			if (c_view.wantsToGoBack()) {
				return true;
			}
			else if (registry.idExist(input)) {
				Member m = registry.findMemberByID(input);
				c_view.displayVerboseInfo(m.getName(), m.getMemberID(), m.getpNum(), m.getBoatList() );
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
			int input = c_view.askForID();
			if (c_view.wantsToGoBack()) {
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
		while (true) {
			c_view.askForBoatType();
			c_view.readInput();
			if (c_view.wantsToRegSailboat()) {
				return Type.Sailboat;
			}
			else if (c_view.wantsToRegMotorsailer()) {
				return Type.Motorsailer;
			}
			else if (c_view.wantsToRegCanoe()) {
				return Type.Canoe;
			}
			else if (c_view.wantsToRegOther()) {
				return Type.Other;
			}
			else {
				continue;
			}
		}
	}
	
	
	//EDIT BOAT

	public boolean editBoat() {
		while (true) {
			//Choose which member
			c_view.membersBoatToEdit();
			int inputID = c_view.askForID();
			if (c_view.wantsToGoBack()) {
				return true;
			}
			else if (registry.idExist(inputID)) {
				if (userConfirmation()) {	
					//Choose which boat to edit
					int boatToEdit = chooseBoatToEdit(inputID);
					if (c_view.wantsToGoBack()) {
						continue;
					}

					//Choose what to edit
					while (true) {
						c_view.displayBoatEditMenu();
						c_view.readInput();
						if (c_view.wantsToGoBack()) {
							return true;
						}
						else if (c_view.wantsToEditBoatType()) {									
							Type type = chooseBoatType();
							registry.editBoatType(boatToEdit, type, inputID);
						}
						else if (c_view.wantsToEditBoatSize()) {									
							int size = c_view.askForBoatSize();
							registry.editBoatSize(boatToEdit, size, inputID);
						}
						else {
							continue;
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
		Member m = registry.findMemberByID(memberID);
		String list = c_view.boatsToString(m.getBoatList());
		while (true) {
			c_view.boatToEdit();
			c_view.printString(list);
			int input = c_view.askForBoatID();
			if (c_view.wantsToGoBack()) {
				return 0;
			}
			else {
				try {
					if (m.getBoatList().get(input-1) != null)
						return input;
					else
						continue;
				}
				catch (Exception e) {
					continue;
				}	
			}
		}
	}
		
			
	//DELETE BOAT	

	public boolean deleteBoat() {
		while (true) {
			c_view.membersBoatToDelete();
			int inputID = c_view.askForID();
			if (c_view.wantsToGoBack()) {
				return true;
			}
			else if (registry.idExist(inputID)) {
				if (userConfirmation()) {	
					//Choose which boat to delete
					int boatToDelete = boatToDelete(inputID);
					if (c_view.wantsToGoBack()) {
						continue;
					}
					else {
						registry.deleteBoat(inputID, boatToDelete);
					}
					return true;
				}
			}
		}
	}

	public int boatToDelete(int memberID) {
		Member m = registry.findMemberByID(memberID);
		String list = c_view.boatsToString(m.getBoatList());
		
		while (true) {
			c_view.boatToDelete();
			c_view.printString(list);
			int boatID = c_view.askForBoatID();
			if (c_view.wantsToGoBack()) {
				return 0;
			}
			else {
				try {
					if (m.getBoatList().get(boatID-1) != null)
						return boatID;
					else
						c_view.displayInputError();
						continue;
				}
				catch (Exception e) {
					c_view.displayInputError();
					continue;
				}	
			}
		}
	}		

	
	//OTHER

	public boolean userConfirmation() {
		while (true) {
			c_view.verifyChoice();
			c_view.readInput();
			if (c_view.wantsToProceed()) {
				return true;
			}
			else if (c_view.dontWantToProceed()) {
				return false;
			}
			continue;
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
