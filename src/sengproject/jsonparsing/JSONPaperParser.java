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
	public static JSONObject addPaper (String t, String pi, String sd, String jn, String jid, String vn, String vid, String fn, String ld, String dl, ArrayList<String> pref_rev, String rej) {
    	// TODO: Might need updating depending on Discord says
    	JSONObject paper = new JSONObject();
		String rndPaperId = Long.toString((long) (Math.random() * Integer.MAX_VALUE) + 1);

    	JSONArray pref_rev_array = new JSONArray();
    	pref_rev_array.addAll(pref_rev);

    	paper.put("title", t);
    	paper.put("paper_id", rndPaperId);
    	paper.put("sub_date", sd);
    	paper.put("author_name", (String) Globals.getUser().get("username"));
    	paper.put("author_id", (String) Globals.getUser().get("uid"));
    	paper.put("journal_name", jn);
    	paper.put("journal_id", jid);
    	paper.put("volume_name", vn);
    	paper.put("volume_id", vid);
    	paper.put("pending_actions", new JSONArray());
		paper.put("actions", new JSONArray());
		paper.put("complete_actions", new JSONArray());
    	paper.put("file_name", fn);
    	paper.put("latest_date", ld);
    	paper.put("deadline", dl);
    	paper.put("reviewers", new JSONArray());
    	paper.put("status", rej);
    	paper.put("pref_rev_uid", pref_rev_array);
    	paper.put("inter_rev_uid", new JSONArray());
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
    
    public static JSONObject findPaper(String pid) {
    	ArrayList<JSONObject> papers = getResearcherPapers();
    	if (papers.isEmpty()) {
    		return null;
    	}
    	for (JSONObject p : papers) {
    		if (pid.equals(p.get("paper_id"))) {
    			return p;
    		}
    	}
    	return null;
    }

	public static boolean removePaper(JSONObject paper) {
		String token1 = "";
		Scanner inFile1;
		JSONParser parser = new JSONParser();
		try {
			inFile1 = new Scanner(new File("Papers.json")).useDelimiter("\n");
			List<String> temps = new ArrayList<String>();
			while (inFile1.hasNext()) {
				token1 = inFile1.next();
				temps.add(token1);
			}
			inFile1.close();
			for (String s : temps) {
				JSONObject obj = (JSONObject) parser.parse(s);
				if (paper.equals(obj)) {
					temps.remove(s);
					FileWriter writer;
					try {
						writer = new FileWriter("Papers.json");
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

    @SuppressWarnings("unchecked")
	public static Boolean updatePaperFile (String pid, String file) {

    	JSONObject paper = findPaper(pid);
    	
    	if (paper != null && removePaper(paper)) {
    		paper.put("title", paper.get("title"));
    		paper.put("paper_id", pid);
        	paper.put("sub_date", paper.get("sub_date"));
        	paper.put("author_name", paper.get("author_name"));
        	paper.put("author_id", paper.get("author_id"));
        	paper.put("journal_name", paper.get("journal_name"));
        	paper.put("journal_id", paper.get("journal_id"));
        	paper.put("volume_name", paper.get("volume_name"));
        	paper.put("volume_id", paper.get("volume_id"));
        	paper.put("pending_actions", paper.get("pending_actions"));
        	paper.put("actions", paper.get("actions"));
        	paper.put("complete_actions", paper.get("complete_actions"));
        	paper.put("file_name", file);
        	paper.put("latest_date", paper.get("latest_date"));
        	paper.put("deadline", paper.get("deadline"));
        	paper.put("reviewers", paper.get("reviewers"));
        	paper.put("status", paper.get("status"));
        	paper.put("pref_rev_uid", paper.get("pref_rev_uid"));
			paper.put("inter_rev_uid", paper.get("inter_rev_uid"));
        	appendStrToFile("Papers.json", paper.toJSONString());
        	return true;
    	} else {
    		return false;
    	}

    }

	public static Boolean addPrefRev (String pid, JSONArray pref_rev, JSONArray rev) {

		JSONObject paper = findPaper(pid);

		if (paper != null && removePaper(paper)) {
			paper.put("title", paper.get("title"));
			paper.put("paper_id", pid);
			paper.put("sub_date", paper.get("sub_date"));
			paper.put("author_name", paper.get("author_name"));
			paper.put("author_id", paper.get("author_id"));
			paper.put("journal_name", paper.get("journal_name"));
			paper.put("journal_id", paper.get("journal_id"));
			paper.put("volume_name", paper.get("volume_name"));
			paper.put("volume_id", paper.get("volume_id"));
			paper.put("pending_actions", paper.get("pending_actions"));
			paper.put("actions", paper.get("actions"));
			paper.put("complete_actions", paper.get("complete_actions"));
			paper.put("file_name", paper.get("file_name"));
			paper.put("latest_date", paper.get("latest_date"));
			paper.put("deadline", paper.get("deadline"));
			paper.put("reviewers", rev);
			paper.put("status", paper.get("status"));
			paper.put("pref_rev_uid", pref_rev);
			paper.put("inter_rev_uid", paper.get("inter_rev_uid"));
			appendStrToFile("Papers.json", paper.toJSONString());
			return true;
		} else {
			return false;
		}
	}

	public static Boolean addInterRev (String pid, JSONArray inter_rev, JSONArray rev) {

		JSONObject paper = findPaper(pid);

		if (paper != null && removePaper(paper)) {
			paper.put("title", paper.get("title"));
			paper.put("paper_id", pid);
			paper.put("sub_date", paper.get("sub_date"));
			paper.put("author_name", paper.get("author_name"));
			paper.put("author_id", paper.get("author_id"));
			paper.put("journal_name", paper.get("journal_name"));
			paper.put("journal_id", paper.get("journal_id"));
			paper.put("volume_name", paper.get("volume_name"));
			paper.put("volume_id", paper.get("volume_id"));
			paper.put("pending_actions", paper.get("pending_actions"));
			paper.put("actions", paper.get("actions"));
			paper.put("complete_actions", paper.get("complete_actions"));
			paper.put("file_name", paper.get("file_name"));
			paper.put("latest_date", paper.get("latest_date"));
			paper.put("deadline", paper.get("deadline"));
			paper.put("reviewers", rev);
			paper.put("status", paper.get("status"));
			paper.put("pref_rev_uid", paper.get("pref_rev_uid"));
			paper.put("inter_rev_uid", inter_rev);
			appendStrToFile("Papers.json", paper.toJSONString());
			return true;
		} else {
			return false;
		}

	}

	public static Boolean updateInterRev (String pid, JSONArray new_inter_rev) {

		JSONObject paper = findPaper(pid);

		if (paper != null && removePaper(paper)) {
			paper.put("title", paper.get("title"));
			paper.put("paper_id", pid);
			paper.put("sub_date", paper.get("sub_date"));
			paper.put("author_name", paper.get("author_name"));
			paper.put("author_id", paper.get("author_id"));
			paper.put("journal_name", paper.get("journal_name"));
			paper.put("journal_id", paper.get("journal_id"));
			paper.put("volume_name", paper.get("volume_name"));
			paper.put("volume_id", paper.get("volume_id"));
			paper.put("pending_actions", paper.get("pending_actions"));
			paper.put("actions", paper.get("actions"));
			paper.put("complete_actions", paper.get("complete_actions"));
			paper.put("file_name", paper.get("file_name"));
			paper.put("latest_date", paper.get("latest_date"));
			paper.put("deadline", paper.get("deadline"));
			paper.put("reviewers", paper.get("reviewers"));
			paper.put("status", paper.get("status"));
			paper.put("pref_rev_uid", paper.get("pref_rev_uid"));
			paper.put("inter_rev_uid", new_inter_rev);
			appendStrToFile("Papers.json", paper.toJSONString());
			return true;
		} else {
			return false;
		}

	}

	public static Boolean updatePrefRev (String pid, JSONArray new_pref_rev) {

		JSONObject paper = findPaper(pid);

		if (paper != null && removePaper(paper)) {
			paper.put("title", paper.get("title"));
			paper.put("paper_id", pid);
			paper.put("sub_date", paper.get("sub_date"));
			paper.put("author_name", paper.get("author_name"));
			paper.put("author_id", paper.get("author_id"));
			paper.put("journal_name", paper.get("journal_name"));
			paper.put("journal_id", paper.get("journal_id"));
			paper.put("volume_name", paper.get("volume_name"));
			paper.put("volume_id", paper.get("volume_id"));
			paper.put("pending_actions", paper.get("pending_actions"));
			paper.put("actions", paper.get("actions"));
			paper.put("complete_actions", paper.get("complete_actions"));
			paper.put("file_name", paper.get("file_name"));
			paper.put("latest_date", paper.get("latest_date"));
			paper.put("deadline", paper.get("deadline"));
			paper.put("reviewers", paper.get("reviewers"));
			paper.put("status", paper.get("status"));
			paper.put("pref_rev_uid", new_pref_rev);
			paper.put("inter_rev_uid", paper.get("inter_rev_uid"));
			appendStrToFile("Papers.json", paper.toJSONString());
			return true;
		} else {
			return false;
		}

	}

	public static Boolean updateRev (String pid, JSONArray rev) {

		JSONObject paper = findPaper(pid);

		if (paper != null && removePaper(paper)) {
			paper.put("title", paper.get("title"));
			paper.put("paper_id", pid);
			paper.put("sub_date", paper.get("sub_date"));
			paper.put("author_name", paper.get("author_name"));
			paper.put("author_id", paper.get("author_id"));
			paper.put("journal_name", paper.get("journal_name"));
			paper.put("journal_id", paper.get("journal_id"));
			paper.put("volume_name", paper.get("volume_name"));
			paper.put("volume_id", paper.get("volume_id"));
			paper.put("pending_actions", paper.get("pending_actions"));
			paper.put("actions", paper.get("actions"));
			paper.put("complete_actions", paper.get("complete_actions"));
			paper.put("file_name", paper.get("file_name"));
			paper.put("latest_date", paper.get("latest_date"));
			paper.put("deadline", paper.get("deadline"));
			paper.put("reviewers", rev);
			paper.put("status", paper.get("status"));
			paper.put("pref_rev_uid", paper.get("pref_rev_uid"));
			paper.put("inter_rev_uid", paper.get("inter_rev_uid"));
			appendStrToFile("Papers.json", paper.toJSONString());
			return true;
		} else {
			return false;
		}
	}

	public static Boolean addPendingAction (String pid, JSONObject new_action) {

		JSONObject paper = findPaper(pid);

		if (paper != null && removePaper(paper)) {
			paper.put("title", paper.get("title"));
			paper.put("paper_id", pid);
			paper.put("sub_date", paper.get("sub_date"));
			paper.put("author_name", paper.get("author_name"));
			paper.put("author_id", paper.get("author_id"));
			paper.put("journal_name", paper.get("journal_name"));
			paper.put("journal_id", paper.get("journal_id"));
			paper.put("volume_name", paper.get("volume_name"));
			paper.put("volume_id", paper.get("volume_id"));
			JSONArray act = (JSONArray) paper.get("pending_actions");
			act.add(new_action);
			paper.put("pending_actions", act);
			paper.put("actions", paper.get("actions"));
			paper.put("complete_actions", paper.get("complete_actions"));
			paper.put("file_name", paper.get("file_name"));
			paper.put("latest_date", paper.get("latest_date"));
			paper.put("deadline", paper.get("deadline"));
			paper.put("reviewers", paper.get("reviewers"));
			paper.put("status", paper.get("status"));
			paper.put("pref_rev_uid", paper.get("pref_rev_uid"));
			paper.put("inter_rev_uid", paper.get("inter_rev_uid"));
			appendStrToFile("Papers.json", paper.toJSONString());
			return true;
		} else {
			return false;
		}
	}

	public static Boolean addAction (String pid, JSONArray new_action, JSONArray new_pending_action) {

		JSONObject paper = findPaper(pid);

		if (paper != null && removePaper(paper)) {
			paper.put("title", paper.get("title"));
			paper.put("paper_id", pid);
			paper.put("sub_date", paper.get("sub_date"));
			paper.put("author_name", paper.get("author_name"));
			paper.put("author_id", paper.get("author_id"));
			paper.put("journal_name", paper.get("journal_name"));
			paper.put("journal_id", paper.get("journal_id"));
			paper.put("volume_name", paper.get("volume_name"));
			paper.put("volume_id", paper.get("volume_id"));
			paper.put("pending_actions", new_pending_action);
			paper.put("actions", new_action);
			paper.put("complete_actions", paper.get("complete_actions"));
			paper.put("file_name", paper.get("file_name"));
			paper.put("latest_date", paper.get("latest_date"));
			paper.put("deadline", paper.get("deadline"));
			paper.put("reviewers", paper.get("reviewers"));
			paper.put("status", paper.get("status"));
			paper.put("pref_rev_uid", paper.get("pref_rev_uid"));
			paper.put("inter_rev_uid", paper.get("inter_rev_uid"));
			appendStrToFile("Papers.json", paper.toJSONString());
			return true;
		} else {
			return false;
		}
	}

	public static Boolean addCompleteAction (String pid, JSONArray new_complete_actions) {

		JSONObject paper = findPaper(pid);

		if (paper != null && removePaper(paper)) {
			paper.put("title", paper.get("title"));
			paper.put("paper_id", pid);
			paper.put("sub_date", paper.get("sub_date"));
			paper.put("author_name", paper.get("author_name"));
			paper.put("author_id", paper.get("author_id"));
			paper.put("journal_name", paper.get("journal_name"));
			paper.put("journal_id", paper.get("journal_id"));
			paper.put("volume_name", paper.get("volume_name"));
			paper.put("volume_id", paper.get("volume_id"));
			paper.put("pending_actions", paper.get("pending_actions"));
			paper.put("actions", paper.get("actions"));
			paper.put("complete_actions", new_complete_actions);
			paper.put("file_name", paper.get("file_name"));
			paper.put("latest_date", paper.get("latest_date"));
			paper.put("deadline", paper.get("deadline"));
			paper.put("reviewers", paper.get("reviewers"));
			paper.put("status", paper.get("status"));
			paper.put("pref_rev_uid", paper.get("pref_rev_uid"));
			paper.put("inter_rev_uid", paper.get("inter_rev_uid"));
			appendStrToFile("Papers.json", paper.toJSONString());
			return true;
		} else {
			return false;
		}
	}

	public static Boolean updateActions (String pid, JSONArray new_actions) {

		JSONObject paper = findPaper(pid);

		if (paper != null && removePaper(paper)) {
			paper.put("title", paper.get("title"));
			paper.put("paper_id", pid);
			paper.put("sub_date", paper.get("sub_date"));
			paper.put("author_name", paper.get("author_name"));
			paper.put("author_id", paper.get("author_id"));
			paper.put("journal_name", paper.get("journal_name"));
			paper.put("journal_id", paper.get("journal_id"));
			paper.put("volume_name", paper.get("volume_name"));
			paper.put("volume_id", paper.get("volume_id"));
			paper.put("pending_actions", paper.get("pending_actions"));
			paper.put("actions", new_actions);
			paper.put("complete_actions", paper.get("complete_actions"));
			paper.put("file_name", paper.get("file_name"));
			paper.put("latest_date", paper.get("latest_date"));
			paper.put("deadline", paper.get("deadline"));
			paper.put("reviewers", paper.get("reviewers"));
			paper.put("status", paper.get("status"));
			paper.put("pref_rev_uid", paper.get("pref_rev_uid"));
			paper.put("inter_rev_uid", paper.get("inter_rev_uid"));
			appendStrToFile("Papers.json", paper.toJSONString());
			return true;
		} else {
			return false;
		}
	}

	public static Boolean updatePendingActions (String pid, JSONArray new_pending_actions) {

		JSONObject paper = findPaper(pid);

		if (paper != null && removePaper(paper)) {
			paper.put("title", paper.get("title"));
			paper.put("paper_id", pid);
			paper.put("sub_date", paper.get("sub_date"));
			paper.put("author_name", paper.get("author_name"));
			paper.put("author_id", paper.get("author_id"));
			paper.put("journal_name", paper.get("journal_name"));
			paper.put("journal_id", paper.get("journal_id"));
			paper.put("volume_name", paper.get("volume_name"));
			paper.put("volume_id", paper.get("volume_id"));
			paper.put("pending_actions", new_pending_actions);
			paper.put("actions", paper.get("actions"));
			paper.put("complete_actions", paper.get("complete_actions"));
			paper.put("file_name", paper.get("file_name"));
			paper.put("latest_date", paper.get("latest_date"));
			paper.put("deadline", paper.get("deadline"));
			paper.put("reviewers", paper.get("reviewers"));
			paper.put("status", paper.get("status"));
			paper.put("pref_rev_uid", paper.get("pref_rev_uid"));
			paper.put("inter_rev_uid", paper.get("inter_rev_uid"));
			appendStrToFile("Papers.json", paper.toJSONString());
			return true;
		} else {
			return false;
		}
	}

	public static Boolean updateStatus (String pid, String status) {

		JSONObject paper = findPaper(pid);

		if (paper != null && removePaper(paper)) {
			paper.put("title", paper.get("title"));
			paper.put("paper_id", pid);
			paper.put("sub_date", paper.get("sub_date"));
			paper.put("author_name", paper.get("author_name"));
			paper.put("author_id", paper.get("author_id"));
			paper.put("journal_name", paper.get("journal_name"));
			paper.put("journal_id", paper.get("journal_id"));
			paper.put("volume_name", paper.get("volume_name"));
			paper.put("volume_id", paper.get("volume_id"));
			paper.put("pending_actions", paper.get("pending_actions"));
			paper.put("actions", paper.get("actions"));
			paper.put("complete_actions", paper.get("complete_actions"));
			paper.put("file_name", paper.get("file_name"));
			paper.put("latest_date", paper.get("latest_date"));
			paper.put("deadline", paper.get("deadline"));
			paper.put("reviewers", paper.get("reviewers"));
			paper.put("status", status);
			paper.put("pref_rev_uid", paper.get("pref_rev_uid"));
			paper.put("inter_rev_uid", paper.get("inter_rev_uid"));
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
