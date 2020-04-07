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

public class JSONUserParser {
	
	@SuppressWarnings("unchecked")
	public static boolean addUser(String username, String password, String role) {
		JSONObject currentUser = findUser(username);
		if (currentUser == null) {
			JSONObject userObj = new JSONObject();
			userObj.put("username", username);
			int random = (int)(Math.random() * Integer.MAX_VALUE + 1);
			userObj.put("uid",  Integer.toString(random)); // change this
			userObj.put("password", Integer.toString(password.hashCode()));
			userObj.put("role", role);
			userObj.put("messages", null); // Possible JSONArray
			userObj.put("reviewer_due", null); // Possible JSONArray
			userObj.put("papers_reviewed", 0); // int
			userObj.put("major_rev", 0);
			userObj.put("minor_rev", 0);
			JSONArray avg_array = new JSONArray();
			avg_array.add(0.0);
			avg_array.add(0);
			userObj.put("avg_time", avg_array);
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
			 inFile1 = new Scanner(new File("Users.json")).useDelimiter(System.lineSeparator());
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
			userObj.put("uid",  user.get("uid"));
			userObj.put("password", Integer.toString(password.hashCode()));
			userObj.put("role", user.get("role"));
			userObj.put("messages", messages);
			userObj.put("reviewer_due", reviewerDue);
			userObj.put("papers_reviewed", papersReviewed);
			userObj.put("major_rev", user.get("major_rev"));
			userObj.put("minor_rev", user.get("minor_rev"));
			userObj.put("avg_time", user.get("avg_time"));
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
			userObj.put("uid",  user.get("uid"));
			userObj.put("password", user.get("password"));
			userObj.put("role", user.get("role"));
			userObj.put("messages", messages); // Possible JSONArray
			userObj.put("reviewer_due", reviewerDue); // Possible JSONArray
			userObj.put("papers_reviewed", papersReviewed);
			userObj.put("major_rev", user.get("major_rev"));
			userObj.put("minor_rev", user.get("minor_rev"));
			userObj.put("avg_time", user.get("avg_time"));
			appendStrToFile("Users.json", userObj.toJSONString());
			return true;
		} else {
			return false;
		}
	}

	public static boolean addMajorRev(String uid) {
		JSONObject user = findUserUID(uid);

		if (user != null && removeUser(user)) {
			JSONObject userObj = new JSONObject();
			userObj.put("username", user.get("username"));
			userObj.put("uid",  user.get("uid"));
			userObj.put("password", user.get("password"));
			userObj.put("role", user.get("role"));
			userObj.put("messages", user.get("messages"));
			userObj.put("reviewer_due", user.get("reviewer_due"));
			userObj.put("papers_reviewed", user.get("papers_reviewed"));
			userObj.put("major_rev", (long) user.get("major_rev") + 1);
			userObj.put("minor_rev", user.get("minor_rev"));
			userObj.put("avg_time", user.get("avg_time"));
			appendStrToFile("Users.json", userObj.toJSONString());
			return true;
		} else {
			return false;
		}
	}

	public static boolean addMinorRev(String uid) {
		JSONObject user = findUserUID(uid);

		if (user != null && removeUser(user)) {
			JSONObject userObj = new JSONObject();
			userObj.put("username", user.get("username"));
			userObj.put("uid",  user.get("uid"));
			userObj.put("password", user.get("password"));
			userObj.put("role", user.get("role"));
			userObj.put("messages", user.get("messages"));
			userObj.put("reviewer_due", user.get("reviewer_due"));
			userObj.put("papers_reviewed", user.get("papers_reviewed"));
			userObj.put("major_rev", user.get("major_rev"));
			userObj.put("minor_rev", (long) user.get("minor_rev") + 1);
			userObj.put("avg_time", user.get("avg_time"));
			appendStrToFile("Users.json", userObj.toJSONString());
			return true;
		} else {
			return false;
		}
	}

	public static boolean addPaperRev(String uid) {
		JSONObject user = findUserUID(uid);

		if (user != null && removeUser(user)) {
			JSONObject userObj = new JSONObject();
			userObj.put("username", user.get("username"));
			userObj.put("uid",  user.get("uid"));
			userObj.put("password", user.get("password"));
			userObj.put("role", user.get("role"));
			userObj.put("messages", user.get("messages"));
			userObj.put("reviewer_due", user.get("reviewer_due"));
			userObj.put("papers_reviewed", (long) user.get("papers_reviewed") + 1);
			userObj.put("major_rev", user.get("major_rev"));
			userObj.put("minor_rev", user.get("minor_rev"));
			userObj.put("avg_time", user.get("avg_time"));
			appendStrToFile("Users.json", userObj.toJSONString());
			return true;
		} else {
			return false;
		}
	}

	public static boolean addAvgTime(String uid, int days) {
		JSONObject user = findUserUID(uid);

		if (user != null && removeUser(user)) {
			JSONObject userObj = new JSONObject();
			userObj.put("username", user.get("username"));
			userObj.put("uid",  user.get("uid"));
			userObj.put("password", user.get("password"));
			userObj.put("role", user.get("role"));
			userObj.put("messages", user.get("messages"));
			userObj.put("reviewer_due", user.get("reviewer_due"));
			userObj.put("papers_reviewed", user.get("papers_reviewed"));
			userObj.put("major_rev", user.get("major_rev"));
			userObj.put("minor_rev", user.get("minor_rev"));

			JSONArray array = (JSONArray) user.get("avg_time");
			double mean = (double) array.get(0);
			long count = (long) array.get(1) + 1;

			double new_mean = mean + (days - mean)/count;

			JSONArray new_array = new JSONArray();
			new_array.add(new_mean);
			new_array.add(count);

			userObj.put("avg_time", new_array);

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

	private static JSONObject findUserUID(String uid) {
		JSONParser parser = new JSONParser();
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(
					"Users.json"));
			String line = reader.readLine();
			while (line != null) {
				JSONObject jsonObject = (JSONObject) parser.parse(line);
				String currentUsername = (String)jsonObject.get("uid");
				if (currentUsername.equals(uid)) {
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

	public static JSONObject getUserUID(String uid) {
		JSONObject currentUser = findUserUID(uid);
		if (currentUser != null) {
			return currentUser;
		} else {
			return null;
		}
	}

	public static ArrayList<JSONObject> getUsersFromRole(String role) {
		JSONParser parser = new JSONParser();
		BufferedReader reader;
		ArrayList<JSONObject> rev_array = new ArrayList<JSONObject>();
		try {
			reader = new BufferedReader(new FileReader(
					"Users.json"));
			String line = reader.readLine();
			while (line != null) {
				JSONObject jsonObject = (JSONObject) parser.parse(line);
				String currentRole = (String) jsonObject.get("role");
				if (currentRole.equals(role)) {
					rev_array.add(jsonObject);
				}
				line = reader.readLine();
			}
			reader.close();
			return rev_array;
		} catch (IOException e) {
			System.out.println(e.getLocalizedMessage());
		} catch (ParseException e) {
			System.out.println(e.getLocalizedMessage());
		}
		return null;

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
