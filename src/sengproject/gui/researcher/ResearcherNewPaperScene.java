package sengproject.gui.researcher;

import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import org.json.simple.JSONArray;
import sengproject.gui.GuiController;
import sengproject.gui.researcher.tvobjects.ResearcherJournal;
import sengproject.jsonparsing.JSONJournalParser;
import sengproject.jsonparsing.JSONUserParser;
import sengproject.researcher.PaperFunctions;

import java.io.File;
import java.util.ArrayList;

import org.json.simple.JSONObject;

public class ResearcherNewPaperScene {

    private static File selected_file;
	
    public static Scene getScene () {

        // details box
        Label details_lb = new Label("Upload New Paper");
        details_lb.setFont(new Font("Arial", 24));

        VBox name_vb = new VBox();
        name_vb.getChildren().addAll(details_lb);
        name_vb.setAlignment(Pos.TOP_LEFT);

        // return button
        Button back_b = new Button("Back");
        back_b.setPrefSize(70, 40);
        back_b.setOnAction(action -> {
            GuiController.changeScene(ResearcherMenuScene.getScene());
        });

        // top spacer
        Region top_spacer_r = new Region();
        HBox.setHgrow(top_spacer_r, Priority.ALWAYS);

        // top hbox
        HBox top_hb = new HBox();
        top_hb.setPadding(new Insets(10, 10, 10, 10));
        top_hb.getChildren().addAll(name_vb, top_spacer_r, back_b);

        //  'Title'
        Label paper_title_lb = new Label("Title of paper");
        paper_title_lb.setFont(new Font("Arial", 20));

        TextField title_tf = new TextField();
        HBox title_hb = new HBox();
        title_hb.getChildren().addAll(paper_title_lb, title_tf);
        title_hb.setPadding(new Insets(10,0,20,0));
        title_hb.setSpacing(4);
        title_hb.setAlignment(Pos.CENTER);

        // 'Journal'
        Label journal_lb = new Label("Journal");
        journal_lb.setFont(new Font("Arial", 20));

        // Journal combobox
        ComboBox<ResearcherJournal> journal_cb = new ComboBox<ResearcherJournal>();

        journal_cb.getItems().addAll(getJournals());

        HBox journal_hb = new HBox();
        journal_hb.setSpacing(4);
        journal_hb.getChildren().addAll(journal_lb, journal_cb);

        // journal/volume vbox
        HBox jv_hb = new HBox();
        jv_hb.getChildren().addAll(journal_hb);//, volume_hb);
        jv_hb.setSpacing(50);
        jv_hb.setPadding(new Insets(0,0,20,0));
        jv_hb.setAlignment(Pos.CENTER);

        // reviewer label
        Label reviewer_lb = new Label("Preferred Reviewers");
        //volume_lb.setFont(new Font("Arial", 20));

        // reviewer listview
        ListView<String> reviewer_lv = new ListView<String>();
        reviewer_lv.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        reviewer_lv.getItems().addAll(getReviewerNames());

        VBox reviewer_vb = new VBox();
        reviewer_vb.getChildren().addAll(reviewer_lb, reviewer_lv);
        reviewer_vb.setPadding(new Insets(0,30,30,30));

        // error label
        Label error_lb = new Label("");
        error_lb.setFont(new Font("Arial", 16));

        // select file
        FileChooser file_chooser = new FileChooser();

        Button file_b = new Button("Select File");
        file_b.setPrefSize(100, 35);
        file_b.setOnAction(action -> {
            selected_file = file_chooser.showOpenDialog(GuiController.getStage());

            if (selected_file != null) {
                error_lb.setText(selected_file.getName());
            }
        });

        HBox file_hb = new HBox();
        file_hb.setAlignment(Pos.CENTER);
        file_hb.getChildren().addAll(file_b);

        // center vbox
        VBox center_vb = new VBox();
        center_vb.getChildren().addAll(title_hb, jv_hb, reviewer_vb, file_hb, error_lb);
        center_vb.setAlignment(Pos.CENTER);

        // submit button
        Button submit_b = new Button("Submit");
        submit_b.setPrefSize(150, 40);
        submit_b.setOnAction(action -> {

            ObservableList indicies = reviewer_lv.getSelectionModel().getSelectedIndices();
            ArrayList<JSONObject> pref_rev = new ArrayList<JSONObject>();
            ArrayList<JSONObject> all_rev = JSONUserParser.getUsersFromRole("Reviewer");

            ResearcherJournal j = journal_cb.getValue();

            // add reviewer JSONObjects based off their indices
            for (Object i : indicies) {
                pref_rev.add(all_rev.get((int) i));
            }

            if (j != null && PaperFunctions.createNewPaper(title_tf.getText(), j.getJ_title(), j.getJid(), j.getV_title(), j.getVid(), j.getDeadline(), pref_rev, selected_file)) {
                GuiController.changeScene(ResearcherMenuScene.getScene());

            } else {
                error_lb.setText("Error creating new paper");
            }
        });

        // bottom spacer
        Region bottom_spacer_r = new Region();
        HBox.setHgrow(bottom_spacer_r, Priority.ALWAYS);

        // bottom hbox
        HBox bottom_hb = new HBox();
        bottom_hb.setPadding(new Insets(10, 50, 10, 50));
        bottom_hb.getChildren().addAll(bottom_spacer_r, submit_b);

        BorderPane main_pane = new BorderPane();

        main_pane.setTop(top_hb);
        main_pane.setCenter(center_vb);
        main_pane.setBottom(bottom_hb);

        return new Scene(main_pane);

    }

    // returns an array of all reviewer usernames
    private static ArrayList<String> getReviewerNames() {

        ArrayList<JSONObject> reviewers = JSONUserParser.getUsersFromRole("Reviewer");
        ArrayList<String> rev_strings = new ArrayList<String>();

        for (JSONObject o : reviewers) {
            rev_strings.add((String) o.get("username"));
        }

        return rev_strings;
    }

    private static ArrayList<ResearcherJournal> getJournals() {

        ArrayList<JSONObject> journals = JSONJournalParser.getJournals();
        ArrayList<ResearcherJournal> j_strings = new ArrayList<ResearcherJournal>();

        for (JSONObject j : journals) {
            ArrayList<JSONObject> volumes = (JSONArray) j.get("volumes");

            for (JSONObject v : volumes) {

                j_strings.add(new ResearcherJournal(
                        (String) j.get("title"),
                        (String) v.get("volume_title"),
                        (String) j.get("jid"),
                        (String) v.get("vid"),
                        (String) v.get("deadline")
                ));
            }
        }

        return j_strings;

    }

}
