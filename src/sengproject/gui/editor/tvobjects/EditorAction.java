package sengproject.gui.editor.tvobjects;

import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import org.json.simple.JSONObject;
import sengproject.gui.GuiController;
import sengproject.gui.editor.EditorActionsScene;
import sengproject.jsonparsing.JSONPaperParser;
import sengproject.jsonparsing.JSONUserParser;
import sengproject.shared.ActionFunctions;

public class EditorAction {

    private JSONObject action;

    private String action_details;
    private String type;
    private String rev_uid;
    private String date_recommended;
    private String date_completed;

    private VBox buttons_vb;

    public EditorAction (JSONObject a, String pid) {

        action = a;

        action_details = (String) a.get("details");
        type = (String) a.get("type");
        rev_uid = (String) a.get("rid");
        date_recommended = (String) a.get("date_recommended");
        date_completed = (String) a.get("date_completed");

        Button accept_b = new Button("Accept");
        accept_b.setPrefSize(80,3);
        accept_b.setOnAction(action ->{
            System.out.println("Accepted action: " + action_details);

            ActionFunctions.moveAction(pid, (String) a.get("aid"));

            if (type.equals("minor")){
                JSONUserParser.addMinorRev(rev_uid);
            } else {
                JSONUserParser.addMajorRev(rev_uid);
            }

            GuiController.changeScene(EditorActionsScene.getScene(new EditorPaper(JSONPaperParser.findPaper(pid))));

        });

        Button reject_b = new Button("Reject");
        reject_b.setPrefSize(80,3);
        reject_b.setOnAction(action ->{
            System.out.println("Rejected action: " + action_details);

            ActionFunctions.deletePendingAction(pid, (String) a.get("aid"));
            GuiController.changeScene(EditorActionsScene.getScene(new EditorPaper(JSONPaperParser.findPaper(pid))));
        });

        buttons_vb = new VBox();
        buttons_vb.getChildren().addAll(accept_b, reject_b);

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

    public void setButtons_vb (VBox vb) { buttons_vb = vb; }

    public VBox getButtons_vb () { return buttons_vb; }

}
