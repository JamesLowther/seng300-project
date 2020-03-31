package sengproject.gui.editor.tvobjects;

import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class EditorAction {

    private String action_details;
    private String rev_uid;
    private String date_recommended;
    private String date_completed;

    private VBox buttons_vb;

    public EditorAction (String ad, String ruid, String dr, String dc) {

        action_details = ad;
        rev_uid = ruid;
        date_recommended = dr;
        date_completed = dc;

        Button accept_b = new Button("Accept");
        accept_b.setPrefSize(80,3);
        accept_b.setOnAction(action ->{
            System.out.println("Accepted action: " + action_details);
            // todo: accept button
            System.out.println("TODO:(forward to researcher)");
        });

        Button reject_b = new Button("Reject");
        reject_b.setPrefSize(80,3);
        reject_b.setOnAction(action ->{
            System.out.println("Rejected action: " + action_details);
            //todo: reject action
            System.out.println("TODO:(forward to researcher)");
        });

        buttons_vb = new VBox();
        buttons_vb.getChildren().addAll(accept_b, reject_b);

    }

    public void setAction_details (String a) { action_details = a; }

    public String getAction_details () { return action_details; }

    public void setRev_uid (String r) { rev_uid = r; }

    public String getRev_uid () { return rev_uid; }

    public void setDate_recommended (String d) { date_recommended = d; }

    public String getDate_recommended () { return date_recommended; }

    public void setDate_completed (String d) { date_completed = d; }

    public String getDate_completed () { return date_completed; }

    public void setButtons_vb (VBox vb) { buttons_vb = vb; }

    public VBox getButtons_vb () { return buttons_vb; }

}
