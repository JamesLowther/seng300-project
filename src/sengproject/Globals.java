package sengproject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Globals {
	
	@SuppressWarnings("unchecked")
	public static boolean addUser(String username, String password, String role) {
		JSONObject currentUser = findUser(username);
		if (currentUser == null) {
			JSONObject userObj = new JSONObject();
			userObj.put("username", username);
			int random = (int)(Math.random() * Integer.MAX_VALUE + 1);
			userObj.put("uid",  random); // change this
			userObj.put("password", password);
			userObj.put("role", role);
			userObj.put("messages", null); // Possible JSONArray
			userObj.put("reviewer_due", null); // Possible JSONArray
			userObj.put("papers_reviewed", null); // int
			appendStrToFile("Users.json", userObj.toJSONString());
			return true;
		} else {
			return false;
		}
	}
	
	private static void appendStrToFile(String fileName, String str) { 
		try { 
			BufferedWriter out = new BufferedWriter( 
			new FileWriter(fileName, true)); 
			out.write(str); 
			out.newLine();
			out.close();
		} catch (IOException e) { 
			System.out.println("exception occoured" + e); 
		} 
	} 
	
	private static JSONObject findUser(String username) {
		JSONParser parser = new JSONParser();
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(
					"Users.json"));
			String line = reader.readLine();
			while (line != null) {
				JSONObject jsonObject = (JSONObject) parser.parse(line);
	            String currentUsername = (String) jsonObject.get("username");
	            if (currentUsername.equals(username)) {
	            	reader.close();
	            	return jsonObject;
	            }
				line = reader.readLine();
			}
			reader.close();
			return null;
		} catch (IOException e) {
			System.out.println(e.getLocalizedMessage());
		} catch (ParseException e) {
			System.out.println(e.getLocalizedMessage());
        }
		return null;
	}
	
	private static JSONObject findUser(String username, String password) {
		JSONParser parser = new JSONParser();
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(
					"Users.json"));
			String line = reader.readLine();
			while (line != null) {
				JSONObject jsonObject = (JSONObject) parser.parse(line);
	            String currentUsername = (String) jsonObject.get("username");
	            String currentPassword = (String) jsonObject.get("password");
	            if (currentUsername.equals(username) && currentPassword.equals(password)) {
	            	reader.close();
	            	return jsonObject;
	            }
				line = reader.readLine();
			}
			reader.close();
			return null;
		} catch (IOException e) {
			System.out.println(e.getLocalizedMessage());
		} catch (ParseException e) {
            System.out.println(e.getLocalizedMessage());
        }
		return null;
	}
	
	public static boolean searchUser(String username, String password) {
		JSONObject currentUser = findUser(username, password);
		if (currentUser != null) {
			return true;
		} else {
			return false;
		}
	}
	
	public static JSONObject getUser(String username, String password) {
		JSONObject currentUser = findUser(username, password);
		if (currentUser != null) {
			return currentUser;
		} else {
			return null;
		}
	}
	
	// TODO: Update user information
	public static void updateUser(String user) {
		
	}
	
	public static void listUsersInDatabase() {
		JSONParser parser = new JSONParser();
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(
					"Users.json"));
			String line = reader.readLine();
			while (line != null) {
				JSONObject jsonObject = (JSONObject) parser.parse(line);
	            String currentUsername = (String) jsonObject.get("username");
	            String currentPassword = (String) jsonObject.get("password");
	            System.out.println("Username: " + currentUsername);
	            System.out.println("Password: " + currentPassword);
				line = reader.readLine();
			}
			reader.close();
		} catch (IOException e) {
			System.out.println(e.getLocalizedMessage());
		} catch (ParseException e) {
            System.out.println(e.getLocalizedMessage());
        }
	}
	
		
}
