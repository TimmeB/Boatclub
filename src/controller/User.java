package controller;

import view.Console;
import model.Registry;

public class User {

	private Console c_view;
	private Registry registry;
	
	
	public boolean startProgram(Console console, Registry registry) {
		c_view = console;
		this.registry = registry;
		c_view.displayWelcomeMessage();
		
		
		c_view.displayMenu();
		
		return readInput();
	}
	
	
	public boolean readInput() {
		int input = c_view.readInput();
		
		//Add something that prints error message for invalid input
		
		switch (input) {
		case 1: return createMember();
		case 2: return testOption();
		case 3: return listMembers();
		case 4: return quit();
		}
		return true;
	}
	
	public boolean createMember() {
		String name = c_view.askForName();
		int pNum = 123;								//Replace with method similar to askForName in Console
		
		registry.createMember(name, pNum);
		
		return true;
	}
	public boolean testOption() {
		System.out.println("asd");
		return true;
	}
	
	public boolean listMembers() {
		c_view.displayMembers(registry);
		return true;
	}
	public boolean quit() {
		return false;
	}
	

}
