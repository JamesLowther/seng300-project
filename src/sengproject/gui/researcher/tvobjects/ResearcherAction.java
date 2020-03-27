package sengproject.gui.researcher.tvobjects;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import sengproject.gui.GuiController;
import sengproject.gui.researcher.ResearcherDetailsScene;

public class ResearcherAction {

    private String action_details;
    private String rev_uid;
    private String date_recommended;
    private String date_completed;

    private Button done_b;

    public ResearcherAction (String ad, String ruid, String dr, String dc) {

        action_details = ad;
        rev_uid = ruid;
        date_recommended = dr;
        date_completed = dc;

        done_b = new Button("Done");
        //done_b.setPrefSize(80,3);
        done_b.setOnAction(action ->{
            System.out.println("Done action: " + action_details);

            // todo: done button
        });

    }

    public void setAction_details (String a) { action_details = a; }

    public String getAction_details () { return action_details; }

    public void setRev_uid (String r) { rev_uid = r; }

    public String getRev_uid () { return rev_uid; }

    public void setDate_recommended (String d) { date_recommended = d; }

    public String getDate_recommended () { return date_recommended; }

    public void setDate_completed (String d) { date_completed = d; }

    public String getDate_completed () { return date_completed; }

    public void setDone_b (Button b) { done_b = b; }

    public Button getDone_b () { return done_b; }

}
