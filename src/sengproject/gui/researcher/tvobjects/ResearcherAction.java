package sengproject.gui.researcher.tvobjects;

import javafx.scene.control.Button;
import org.json.simple.JSONObject;
import sengproject.gui.GuiController;
import sengproject.gui.researcher.ResearcherActionsScene;
import sengproject.jsonparsing.JSONPaperParser;
import sengproject.shared.ActionFunctions;

public class ResearcherAction {

    private String action_details;
    private String type;
    private String rev_uid;
    private String date_recommended;
    private String date_completed;

    private Button done_b;

    public ResearcherAction (JSONObject a, String pid) {

        action_details = (String) a.get("details");
        type = (String) a.get("type");
        rev_uid = (String) a.get("rid");
        date_recommended = (String) a.get("date_recommended");
        date_completed = (String) a.get("date_completed");

        done_b = new Button("Done");
        //done_b.setPrefSize(80,3);
        done_b.setOnAction(action ->{
            System.out.println("Done action: " + action_details);
            ActionFunctions.completeAction(pid, (String) a.get("aid"));
            GuiController.changeScene(ResearcherActionsScene.getScene(new ResearcherPaper(JSONPaperParser.findPaper(pid))));
        });

    }

    public void setAction_details (String a) { action_details = a; }

    public String getAction_details () { return action_details; }

    public void setType (String t) { type = t; }

    public String getType () { return type; }

    public void setRev_uid (String r) { rev_uid = r; }

    public String getRev_uid () { return rev_uid; }

    public void setDate_recommended (String d) { date_recommended = d; }

    public String getDate_recommended () { return date_recommended; }

    public void setDate_completed (String d) { date_completed = d; }

    public String getDate_completed () { return date_completed; }

    public void setDone_b (Button b) { done_b = b; }

    public Button getDone_b () { return done_b; }

}
