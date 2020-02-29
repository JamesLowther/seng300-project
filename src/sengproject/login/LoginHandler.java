package sengproject.login;

import sengproject.Globals;
import sengproject.gui.GuiController;
import sengproject.gui.MenuScene;

public class LoginHandler {

	// returns true if login successful
	public static boolean login(String username, String password) {
		
		// this code is just testing proof of concept
		// it will be replaced with code that parses the json to handle the login
		System.out.println("Username: " + username + "\nPassword: " + password);
		
		if (username.equals("admin") && password.equals("root")) {
			
			Globals.username = username;
			
		}
		
		else if (username.equals("james") && password.equals("12345")) {
			
			Globals.username = username;

		}
		
		else {
			return false;
		}
		
		
		GuiController.changeScene(MenuScene.getScene());
		
		return true;
	}
	
}
