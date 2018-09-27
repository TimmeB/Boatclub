package controller;

import view.Console;

import java.io.IOException;

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
		case 3: return testOption();
		case 4: return editMemberMain();
		case 5: return quit();
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
	public boolean testOption() {
		System.out.println("Bitch-ass Johnson");
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
				case 1: c_view.displayMembers(registry);					//Create method for compact list
				case 2: return true;//c_view.displayMembers(registry);					//Create method for verbose list
				case 3: return true;
				}
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
