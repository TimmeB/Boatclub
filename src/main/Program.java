package main;

import controller.User;
import view.Console;
import model.Registry;

public class Program {

	public static void main(String[] args) {
		User user = new User();
		Console console = new Console();
		Registry registry = new Registry();
		
		
		while(user.startProgram(console, registry)) {
			
		}
		System.out.println("Application closed");
		
	}
	
}
