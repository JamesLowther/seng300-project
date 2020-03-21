package sengproject.jsonparsing;

import org.json.simple.JSONObject;
import sengproject.Globals;

import java.io.BufferedReader;
import java.io.BufferedWriter;
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

import java.io.File;

public class JSONPaperParser {

//	   title = "title"
//	   paper_id = "paper_id";
//     sub_date = "sub_date";
//     author_name = "author_name";
//     author_id = "author_id";
//     journal_name = "journal_name";
//     journal_id = "journal_id";
//     volume_id = "volume_id";
//     file_name = "file_name";
//     latest_date = "latest_date";
//     deadline = "deadline";
//     reviewers = "reviewers";
//     status = "rejected";

//     File file, String[] pref_rev_uid
	
    @SuppressWarnings("unchecked")
	public static JSONObject addPaper (String t, String pi, String sd, String jn, String jid, String vid, String fn, String ld, String dl, String rev, String rej) {
    	// TODO: Might need updating depending on Discord says
    	JSONObject paper = new JSONObject();
    	int rndPaperId = (int)(Math.random() * Integer.MAX_VALUE + 1);
    	paper.put("title", t);
    	paper.put("paper_id", rndPaperId);
    	paper.put("sub_date", sd);
    	paper.put("author_name", (String) Globals.getUser().get("username"));
    	paper.put("author_id", (String) Globals.getUser().get("uid").toString());
    	paper.put("journal_name", jn);
    	paper.put("journal_id", jid);
    	paper.put("volume_id", vid);
    	paper.put("file_name", fn);
    	paper.put("latest_date", ld);
    	paper.put("deadline", dl);
    	paper.put("reviewers", rev);
    	paper.put("status", rej);
    	paper.put("pref_rev_uid", null);
    	if (appendStrToFile("Papers.json", paper.toJSONString())) {
    		System.out.println("New paper created with title " + t);
    		return paper;
    	} else {
    		return null;
    	}
    }

    public static ArrayList<JSONObject> getResearcherPapers () {
    	ArrayList<JSONObject> papers = new ArrayList<JSONObject>();
    	JSONParser parser = new JSONParser();
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(
					"Papers.json"));
			String line = reader.readLine();
			while (line != null) {
				JSONObject jsonObject = (JSONObject) parser.parse(line);
				papers.add(jsonObject);
				line = reader.readLine();
			}
			reader.close();
			return papers;
		} catch (IOException e) {
			System.out.println(e.getLocalizedMessage());
		} catch (ParseException e) {
			System.out.println(e.getLocalizedMessage());
        }
        return null;
    }

    public static ArrayList<JSONObject> getResearcherPapers (String uid) {
		ArrayList<JSONObject> papers = new ArrayList<JSONObject>();
		JSONParser parser = new JSONParser();
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(
					"Papers.json"));
			String line = reader.readLine();
			while (line != null) {
				JSONObject jsonObject = (JSONObject) parser.parse(line);
				if ((String) jsonObject.get("author_id") == uid) {
					papers.add(jsonObject);
				}
				line = reader.readLine();
			}
			reader.close();
			return papers;
		} catch (IOException e) {
			System.out.println(e.getLocalizedMessage());
		} catch (ParseException e) {
			System.out.println(e.getLocalizedMessage());
		}
		return null;
	}
    
    public static JSONObject findPaper(int pid) {
    	ArrayList<JSONObject> papers = getResearcherPapers();
    	if (papers.isEmpty()) {
    		return null;
    	}
    	for (JSONObject p : papers) {
    		if (pid == ((long) p.get("paper_id"))) {
    			return p;
    		}
    	}
    	return null;
    }

    @SuppressWarnings("unchecked")
	public static Boolean updatePaperFile (int pid, String file) {
    	JSONObject paper = findPaper(pid);
    	if (paper != null) {
    		paper.put("title", paper.get("title"));
    		paper.put("paper_id", pid);
        	paper.put("sub_date", paper.get("sub_date"));
        	paper.put("author_name", paper.get("author_name"));
        	paper.put("author_id", paper.get("author_id"));
        	paper.put("journal_name", paper.get("journal_name"));
        	paper.put("journal_id", paper.get("journal_id"));
        	paper.put("volume_id", paper.get("volume_id"));
        	paper.put("file_name", file);
        	paper.put("latest_date", paper.get("latest_date"));
        	paper.put("deadline", paper.get("deadline"));
        	paper.put("reviewers", paper.get("reviewers"));
        	paper.put("status", paper.get("status"));
        	paper.put("pref_rev_uid", paper.get("pref_rev_uid"));
        	appendStrToFile("Papers.json", paper.toJSONString());
        	return true;
    	} else {
    		return false;
    	}
    }
    
    private static boolean appendStrToFile(String fileName, String str) { 
		try { 
			BufferedWriter out = new BufferedWriter( 
			new FileWriter(fileName, true)); 
			out.write(str); 
			out.newLine();
			out.close();
			return true;
		} catch (IOException e) { 
			System.out.println("exception occoured" + e);
			return false;
		} 
	} 

}
