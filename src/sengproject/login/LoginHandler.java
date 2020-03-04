package sengproject.login;

import sengproject.gui.GuiController;
import sengproject.jsonparsing.JSONUserParser;
import sengproject.Globals;
import sengproject.gui.researcher.ResearcherMenuScene;
import sengproject.gui.reviewer.ReviewerMenuScene;
import sengproject.gui.editor.EditorMenuScene;

public class LoginHandler {

	// returns true if login successful
	public static boolean login(String username, String password) {
		
		// this code is just testing proof of concept
		// it will be replaced with code that parses the json to handle the login
		System.out.println("Username: " + username + "\nPassword: " + password);
		
		if (JSONUserParser.searchUser(username, password)) {
			Globals.setUser(JSONUserParser.getUser(username, password));

			String user_type = (String) Globals.getUser().get("role");
			System.out.println(user_type);

			if (user_type.equals("Researcher")) {
				GuiController.changeScene(ResearcherMenuScene.getScene());
			} else if (user_type.equals("Reviewer")) {
				GuiController.changeScene(ReviewerMenuScene.getScene());
			} else if (user_type.equals("Editor")) {
				GuiController.changeScene(EditorMenuScene.getScene());
			} else {
				return false; // user type not recognized
			}

			return true;
		} else {
			return false;
		}
		
	}

	public static void logout() { Globals.setUser(null); }
	
}
