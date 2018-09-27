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
		case 3: return deleteMember();
		case 4: return editMember();
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
	public boolean deleteMember() {
		
		return true;
	}
	
	public boolean listMembers() {
		while(true) {
			c_view.listMembersMenu();
			int input = c_view.readInput();
			int lowest = 1, max = 3;
			if (inputIsInvalid(input, lowest, max)) {
				c_view.displayInputError();
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
	
	public boolean editMember() {
		return true;									//NOT STARTED
	}
	
	
	
	public boolean areYouSure() {
		int yes = 1;
		while (true) {
			c_view.verifyChoice();
			int input = c_view.readInput();
			int lowest = 1, max = 2;
			if (inputIsInvalid(input, lowest, max)) {
				c_view.displayInputError();
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
