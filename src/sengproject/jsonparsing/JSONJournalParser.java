package sengproject.jsonparsing;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import sengproject.Globals;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class JSONJournalParser {

    public static JSONObject addJournal (String t, String eid) {
        JSONObject journal = new JSONObject();
        String rndJournalId = Long.toString((long) (Math.random() * Integer.MAX_VALUE) + 1);

        journal.put("title", t);
        journal.put("jid", rndJournalId);
        journal.put("editor_id", eid);
        journal.put("volumes", new JSONArray());
        if (appendStrToFile("Journals.json", journal.toJSONString())) {
            System.out.println("New journal created with title " + t);
            return journal;
        } else {
            return null;
        }
    }

    public static ArrayList<JSONObject> getJournals () {
        ArrayList<JSONObject> journals = new ArrayList<JSONObject>();
        JSONParser parser = new JSONParser();
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(
                    "Journals.json"));
            String line = reader.readLine();
            while (line != null) {
                JSONObject jsonObject = (JSONObject) parser.parse(line);
                journals.add(jsonObject);
                line = reader.readLine();
            }
            reader.close();
            return journals;
        } catch (IOException e) {
            System.out.println(e.getLocalizedMessage());
        } catch (ParseException e) {
            System.out.println(e.getLocalizedMessage());
        }
        return null;
    }

    public static ArrayList<JSONObject> getJournals (String uid) {
        ArrayList<JSONObject> papers = new ArrayList<JSONObject>();
        JSONParser parser = new JSONParser();
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(
                    "Journals.json"));
            String line = reader.readLine();
            while (line != null) {
                JSONObject jsonObject = (JSONObject) parser.parse(line);
                if (((String) jsonObject.get("editor_id")).equals(uid)) {
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

    public static boolean addVolume (String jid, String title, String deadline) {
        JSONObject journal = findJournal(jid);
        JSONObject new_journal = new JSONObject();

        String rndVolumeId = Long.toString((long) (Math.random() * Integer.MAX_VALUE) + 1);

        if (journal != null && removeJournal(journal)) {

            new_journal.put("title", journal.get("title"));
            new_journal.put("jid", journal.get("jid"));
            new_journal.put("editor_id", journal.get("editor_id"));

            JSONArray volumes = (JSONArray) journal.get("volumes");
            JSONObject new_volume = new JSONObject();
            new_volume.put("volume_title", title);
            new_volume.put("vid", rndVolumeId);
            new_volume.put("deadline", deadline);
            volumes.add(new_volume);

            new_journal.put("volumes", volumes);

            appendStrToFile("Journals.json", new_journal.toJSONString());
            return true;
        } else {
            return false;
        }
    }

    public static JSONObject findJournal(String jid) {
        ArrayList<JSONObject> journals = getJournals();
        if (journals.isEmpty()) {
            return null;
        }
        for (JSONObject p : journals) {
            if (jid.equals(p.get("jid"))) {
                return p;
            }
        }
        return null;
    }

    public static JSONObject findVolume(String pid) {

        JSONObject p = JSONPaperParser.findPaper(pid);
        String p_vid = (String) p.get("volume_id");

        JSONObject journal = findJournal((String) p.get("journal_id"));

        JSONArray volumes = (JSONArray) journal.get("volumes");

        for (Object v : volumes) {
            if (p_vid.equals(((JSONObject) v).get("vid"))) {

                return (JSONObject) v;

            }

        }

        return null;

    }

    public static boolean removeJournal(JSONObject journal) {
        String token1 = "";
        Scanner inFile1;
        JSONParser parser = new JSONParser();
        try {
            inFile1 = new Scanner(new File("Journals.json")).useDelimiter("\n");
            List<String> temps = new ArrayList<String>();
            while (inFile1.hasNext()) {
                token1 = inFile1.next();
                temps.add(token1);
            }
            inFile1.close();
            for (String s : temps) {
                JSONObject obj = (JSONObject) parser.parse(s);
                if (journal.equals(obj)) {
                    temps.remove(s);
                    FileWriter writer;
                    try {
                        writer = new FileWriter("Journals.json");
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
