package sengproject.shared;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import sengproject.jsonparsing.JSONPaperParser;

public class ReviewerFunctions {

    // adds a reviewer to a paper if added by editor
    // takes the paper ID, reviewer ID, and the type of reviewer being added ("pref" or "int")
    public static boolean addReviewer (String pid, String rid, String type) {

        if (type == null) { return false; }

        JSONObject paper =  JSONPaperParser.findPaper(pid);

        JSONArray curr_rev = (JSONArray) paper.get("reviewers");
        JSONArray pref_rev = (JSONArray) paper.get("pref_rev_uid");
        JSONArray inter_rev = (JSONArray) paper.get("inter_rev_uid");

        JSONObject reviewer_obj = new JSONObject();
        reviewer_obj.put("rid", rid);
        reviewer_obj.put("deadline", "not set");

        // if a preferred reviewer
        if (type.equals("pref")) {
            pref_rev.remove(rid);
            curr_rev.add(reviewer_obj);
            if (inter_rev.contains(rid)) { inter_rev.remove(rid); }
            JSONPaperParser.addPrefRev(pid, pref_rev, curr_rev);
            JSONPaperParser.updateInterRev(pid, inter_rev);

        // if a interested reviewer
        } else if (type.equals("int")) {
            inter_rev.remove(rid);
            curr_rev.add(reviewer_obj);
            if (pref_rev.contains(rid)) { pref_rev.remove(rid); }
            JSONPaperParser.addInterRev(pid, inter_rev, curr_rev);
            JSONPaperParser.updatePrefRev(pid, pref_rev);

        } else {
            return false;
        }

        return true;

    }

    // adds an interested reviewer to a paper
    // takes the paper ID and reviewer ID
    public static boolean addInterestedReviewer (String pid, String rid) {

        JSONObject paper =  JSONPaperParser.findPaper(pid);
        JSONArray inter_rev = (JSONArray) paper.get("inter_rev_uid");

        if (!inter_rev.contains(rid)) {
            inter_rev.add(rid);
        }

        return JSONPaperParser.updateInterRev(pid, inter_rev);

    }

    // removes a interested reviewer from a paper
    // takes the paper ID and reviewer ID
    public static boolean removeInterestedReviewer (String pid, String rid) {

        JSONObject paper =  JSONPaperParser.findPaper(pid);
        JSONArray inter_rev = (JSONArray) paper.get("inter_rev_uid");

        if (inter_rev.contains(rid)) {
            inter_rev.remove(rid);
        }

        return JSONPaperParser.updateInterRev(pid, inter_rev);

    }

    // removes a reviewer from a paper
    // takes the paper ID and reviewer ID
    public static boolean removeReviewer (String pid, String rid) {

        JSONObject paper = JSONPaperParser.findPaper(pid);

        JSONArray curr_rev = (JSONArray) paper.get("reviewers");

        JSONArray new_curr_rev = (JSONArray) curr_rev.clone();

        for (Object r : curr_rev) {

            if (((JSONObject) r).get("rid").equals(rid)) {
                new_curr_rev.remove(r);
            }

        }

        return JSONPaperParser.updateRev(pid, new_curr_rev);

    }

    // updates the deadline for a specific reviewer
    // takes the paper ID, reviewer ID, and new deadline
    public static boolean updateDeadline (String pid, String rid, String deadline) {

        JSONObject paper = JSONPaperParser.findPaper(pid);

        JSONArray curr_rev = (JSONArray) paper.get("reviewers");

        JSONArray new_curr_rev = (JSONArray) curr_rev.clone();

        for (Object r : curr_rev) {

            if (((JSONObject) r).get("rid").equals(rid)) {
                new_curr_rev.remove(r);
                JSONObject temp = new JSONObject();
                temp.put("rid", rid);
                temp.put("deadline", deadline);
                new_curr_rev.add(temp);
            }

        }

        return JSONPaperParser.updateRev(pid, new_curr_rev);

    }
}
