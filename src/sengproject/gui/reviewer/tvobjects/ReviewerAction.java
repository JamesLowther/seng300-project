package sengproject.gui.reviewer.tvobjects;

import javafx.scene.control.Button;
import org.json.simple.JSONObject;

public class ReviewerAction {

    private JSONObject action;

    private String action_details;
    private String type;
    private String rev_uid;
    private String date_recommended;
    private String date_completed;

    public ReviewerAction (JSONObject a) {

        action = a;

        action_details = (String) a.get("details");
        type = (String) a.get("type");
        rev_uid = (String) a.get("rid");
        date_recommended = (String) a.get("date_recommended");
        date_completed = (String) a.get("date_completed");

    }

    public void setAction_details (String a) { action_details = a; }

    public String getAction_details () { return action_details; }

    public String getType () { return type; }

    public void setRev_uid (String r) { rev_uid = r; }

    public String getRev_uid () { return rev_uid; }

    public void setDate_recommended (String d) { date_recommended = d; }

    public String getDate_recommended () { return date_recommended; }

    public void setDate_completed (String d) { date_completed = d; }

    public String getDate_completed () { return date_completed; }

}
