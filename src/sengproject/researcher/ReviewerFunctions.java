package sengproject.researcher;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import sengproject.jsonparsing.JSONPaperParser;

public class ReviewerFunctions {

    public static boolean addReviewer (String pid, String rid, String type) {

        if (type == null) { return false; }

        JSONObject paper =  JSONPaperParser.findPaper(pid);

        JSONArray curr_rev = (JSONArray) paper.get("reviewers");
        JSONArray pref_rev = (JSONArray) paper.get("pref_rev_uid");
        JSONArray inter_rev = (JSONArray) paper.get("inter_rev_uid");

        if (type.equals("pref")) {
            pref_rev.remove(rid);
            curr_rev.add(rid);
            JSONPaperParser.addPrefRev(pid, pref_rev, curr_rev);

        } else if (type.equals("int")) {
            inter_rev.remove(rid);
            curr_rev.add(rid);
            JSONPaperParser.addInterRev(pid, inter_rev, curr_rev);

        } else {
            return false;
        }

        return true;

    }

    public static boolean removeReviewer (String pid, String rid) {

        JSONObject paper = JSONPaperParser.findPaper(pid);

        JSONArray curr_rev = (JSONArray) paper.get("reviewers");

        curr_rev.remove(rid);

        return JSONPaperParser.removeRev(pid, curr_rev);

    }



}
