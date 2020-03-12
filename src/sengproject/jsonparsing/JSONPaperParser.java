package sengproject.jsonparsing;

import org.json.simple.JSONObject;

import java.io.File;

public class JSONPaperParser {

    public static Boolean addPaper (String title, String journal_jid, String volume_vid, File file, String[] pref_rev_uid) {

        //todo: this should add a paper to the system

        System.out.println("New paper created with title " + title);

        return true;
    }

    public static JSONObject[] getResearcherPapers (String uid) {

        //todo: this should get all of a researcher's papers


        return null;

    }

    public static Boolean updatePaperFile (String pid, File file) {

        //todo: this should update the file submission for a paper

        return true;

    }

}
