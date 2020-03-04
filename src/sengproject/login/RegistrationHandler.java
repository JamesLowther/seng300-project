package sengproject.login;

import sengproject.jsonparsing.JSONUserParser;

public class RegistrationHandler {

	// returns true if registration successful
	public static boolean register (String username, String password, String role) {
		if ((username.trim().isEmpty() || password.isEmpty())) {
			return false;
		}
		if (role == null) {return false;}
		if (JSONUserParser.addUser(username, password, role)) {
			return true;
		} else {
			return false;
		}
	}
	
}
