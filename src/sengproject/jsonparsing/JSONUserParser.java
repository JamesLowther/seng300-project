package sengproject.jsonparsing;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

//For Hashing
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class JSONUserParser {

	public static String getHash(String input){
		MessageDigest md = MessageDigest.getInstance("SHA-256");
		BigInteger number = new BigInteger(1, md.digest(input.getBytes(StandardCharsets.UTF_8)));
		StringBuilder hash = new StringBuilder(number.toString(16));
		while(hash.length()<32){
			hash.insert(0, '0');
		}
		return hash.toString();
	}
	
	@SuppressWarnings("unchecked")
	public static boolean addUser(String username, String password, String role) {
		JSONObject currentUser = findUser(username);
		if (currentUser == null) {
			JSONObject userObj = new JSONObject();
			userObj.put("username", username);
			int random = (int)(Math.random() * Integer.MAX_VALUE + 1);
			userObj.put("uid",  random); // change this
			userObj.put("password", Integer.toString(password.hashCode()));
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
	
	public static boolean removeUser(JSONObject user) {
		String token1 = "";
		Scanner inFile1;
		JSONParser parser = new JSONParser();
		try {
			 inFile1 = new Scanner(new File("Users.json")).useDelimiter("\n");
			 List<String> temps = new ArrayList<String>();
			 while (inFile1.hasNext()) {
			   token1 = inFile1.next();
			   temps.add(token1);
			 }
			 inFile1.close();
			 for (String s : temps) {
				 JSONObject obj = (JSONObject) parser.parse(s);
				 if (user.equals(obj)) {
					 temps.remove(s);
					 FileWriter writer;
					 try {
						 writer = new FileWriter("Users.json"); 
						 for(String str: temps) {
							   writer.write(str + System.lineSeparator());
						}
						 writer.close();
					 } catch (IOException e) {
						 System.out.println(e.getLocalizedMessage());
						 return false;
					 }
					 return true;
				 }
			 }
		} catch (IOException e) {
			System.out.println(e.getLocalizedMessage());
			return false;
		} catch (ParseException e) {
			System.out.println(e.getLocalizedMessage());
			return false;
		}
		return false;
	}
	
	public static boolean updateUser(JSONObject user, String username, String password, Object messages, Object reviewerDue, int papersReviewed) {
		if (removeUser(user)) {
			JSONObject userObj = new JSONObject();
			userObj.put("username", username);
			int random = (int)(Math.random() * Integer.MAX_VALUE + 1);
			userObj.put("uid",  random);
			userObj.put("password", Integer.toString(password.hashCode()));
			userObj.put("role", user.get("role"));
			userObj.put("messages", messages);
			userObj.put("reviewer_due", reviewerDue);
			userObj.put("papers_reviewed", papersReviewed);
			appendStrToFile("Users.json", userObj.toJSONString());
			return true;
		} else {
			return false;
		}
	}
	
	public static boolean updateUser(JSONObject user, Object messages, Object reviewerDue, int papersReviewed) {
		if (removeUser(user)) {
			JSONObject userObj = new JSONObject();
			userObj.put("username", user.get("username"));
			int random = (int)(Math.random() * Integer.MAX_VALUE + 1);
			userObj.put("uid",  random); // change this
			userObj.put("password", user.get("password"));
			userObj.put("role", user.get("role"));
			userObj.put("messages", messages); // Possible JSONArray
			userObj.put("reviewer_due", reviewerDue); // Possible JSONArray
			userObj.put("papers_reviewed", papersReviewed); 
			appendStrToFile("Users.json", userObj.toJSONString());
			return true;
		} else {
			return false;
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
	            if (currentUsername.equals(username) && currentPassword.equals(Integer.toString(password.hashCode()))) {
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
