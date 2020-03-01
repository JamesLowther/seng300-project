package sengproject.login;

import sengproject.gui.GuiController;
import sengproject.gui.MenuScene;
import sengproject.jsonparsing.JSONUserParser;
import sengproject.Globals;

public class LoginHandler {

	// returns true if login successful
	public static boolean login(String username, String password) {
		
		// this code is just testing proof of concept
		// it will be replaced with code that parses the json to handle the login
		System.out.println("Username: " + username + "\nPassword: " + password);
		
		if (JSONUserParser.searchUser(username, password)) {
			Globals.setUser(JSONUserParser.getUser(username, password));
			GuiController.changeScene(MenuScene.getScene());
			return true;
		} else {
			return false;
		}
		
	}
	
}
