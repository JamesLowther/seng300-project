package sengproject;

import org.json.simple.JSONObject;

public class Globals {

	// this value represents the currently signed in user
	private static JSONObject user = null;
	
	public static void setUser (JSONObject u) {
		user = u;
	}
	
	public static JSONObject getUser () {
		return user;
	}
	
}
