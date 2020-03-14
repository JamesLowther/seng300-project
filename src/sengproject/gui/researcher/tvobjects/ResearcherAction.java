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

    private VBox buttons_vb;

    public ResearcherAction () {

        //todo: this is just test data (remove later)

        action_details = "Test detail";
        rev_uid = "12345";
        date_recommended = "2020/01/02";
        date_completed = "2020/01/03";


        Button accept_b = new Button("Accept");
        accept_b.setPrefSize(80,3);
        accept_b.setOnAction(action ->{
            //todo: accept action
        });

        Button reject_b = new Button("Reject");
        reject_b.setPrefSize(80,3);
        reject_b.setOnAction(action ->{
            //todo: accept action
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

    public void setButtons_vb (VBox v) { buttons_vb = v; }

    public VBox getButtons_vb () { return buttons_vb; }


}
