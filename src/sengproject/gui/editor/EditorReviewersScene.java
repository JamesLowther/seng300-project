package sengproject.gui.editor;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import sengproject.gui.GuiController;
import sengproject.gui.editor.tvobjects.EditorPaper;
import sengproject.gui.editor.tvobjects.EditorReviewer;
import sengproject.gui.researcher.ResearcherMenuScene;
import sengproject.gui.researcher.tvobjects.ResearcherPaper;
import sengproject.gui.researcher.tvobjects.ResearcherReviewer;
import sengproject.jsonparsing.JSONUserParser;

import java.util.ArrayList;

public class EditorReviewersScene {

    public static Scene getScene (EditorPaper r_paper) {

        // details box
        Label details_lb = new Label("Reviewers");
        details_lb.setFont(new Font("Arial", 24));

        VBox name_vb = new VBox();
        name_vb.getChildren().addAll(details_lb);
        name_vb.setAlignment(Pos.TOP_LEFT);

        // return button
        Button logout_b = new Button("Back");
        logout_b.setPrefSize(70, 40);
        logout_b.setOnAction(action -> {
            GuiController.changeScene(EditorMenuScene.getScene());
        });

        // top spacer
        Region top_spacer_r = new Region();
        HBox.setHgrow(top_spacer_r, Priority.ALWAYS);

        // top hbox
        HBox top_hb = new HBox();
        top_hb.setPadding(new Insets(10, 10, 10, 10));
        top_hb.getChildren().addAll(name_vb, top_spacer_r, logout_b);

        // current reviewers
        TableView<EditorReviewer> current_rev_tv = new TableView<EditorReviewer>();
        {
            TableColumn<EditorReviewer, String> name_column = new TableColumn<EditorReviewer, String>("Name");
            name_column.setCellValueFactory(new PropertyValueFactory<EditorReviewer, String>("name"));

            TableColumn<EditorReviewer, String> major_rev_column = new TableColumn<EditorReviewer, String>("Major revisions");
            major_rev_column.setStyle("-fx-alignment: CENTER-RIGHT;");
            major_rev_column.setCellValueFactory(new PropertyValueFactory<EditorReviewer, String>("num_major_rev"));

            TableColumn<EditorReviewer, String> minor_rev_column = new TableColumn<EditorReviewer, String>("Minor revisions");
            minor_rev_column.setStyle("-fx-alignment: CENTER-RIGHT;");
            minor_rev_column.setCellValueFactory(new PropertyValueFactory<EditorReviewer, String>("num_minor_rev"));

            TableColumn<EditorReviewer, String> deadline_column = new TableColumn<EditorReviewer, String>("Deadline");
            deadline_column.setStyle("-fx-alignment: CENTER-RIGHT;");
            deadline_column.setCellValueFactory(new PropertyValueFactory<EditorReviewer, String>("num_pub_rev"));

            TableColumn<EditorReviewer, Button> manage_column = new TableColumn<EditorReviewer, Button>("");
            manage_column.setStyle("-fx-alignment: CENTER;");
            manage_column.setCellValueFactory(new PropertyValueFactory<EditorReviewer, Button>("manage_b"));
            manage_column.setSortable(false);

            current_rev_tv.getColumns().addAll(name_column, major_rev_column, minor_rev_column, deadline_column, manage_column);
        }

        // preferred reviewers
        TableView<EditorReviewer> preferred_rev_tv = new TableView<EditorReviewer>();
        {
            TableColumn<EditorReviewer, String> name_column = new TableColumn<EditorReviewer, String>("Name");
            name_column.setCellValueFactory(new PropertyValueFactory<EditorReviewer, String>("name"));

            TableColumn<EditorReviewer, String> publications_column = new TableColumn<EditorReviewer, String>("Total # of publications viewed");
            publications_column.setStyle("-fx-alignment: CENTER-RIGHT;");
            publications_column.setCellValueFactory(new PropertyValueFactory<EditorReviewer, String>("num_pub_rev"));

            TableColumn<EditorReviewer, String> average_column = new TableColumn<EditorReviewer, String>("Average review time (days)");
            average_column.setStyle("-fx-alignment: CENTER-RIGHT;");
            average_column.setCellValueFactory(new PropertyValueFactory<EditorReviewer, String>("avg_rev_time"));

            TableColumn<EditorReviewer, Button> add_column = new TableColumn<EditorReviewer, Button>("");
            add_column.setStyle("-fx-alignment: CENTER;");
            add_column.setCellValueFactory(new PropertyValueFactory<EditorReviewer, Button>("add_b"));
            add_column.setSortable(false);

            preferred_rev_tv.getColumns().addAll(name_column, publications_column, average_column, add_column);
        }

        // current reviewers
        TableView<EditorReviewer> interested_rev_tv = new TableView<EditorReviewer>();
        {

            TableColumn<EditorReviewer, String> name_column = new TableColumn<EditorReviewer, String>("Name");
            name_column.setCellValueFactory(new PropertyValueFactory<EditorReviewer, String>("name"));

            TableColumn<EditorReviewer, String> publications_column = new TableColumn<EditorReviewer, String>("Total # of publications viewed");
            publications_column.setStyle("-fx-alignment: CENTER-RIGHT;");
            publications_column.setCellValueFactory(new PropertyValueFactory<EditorReviewer, String>("num_pub_rev"));

            TableColumn<EditorReviewer, String> average_column = new TableColumn<EditorReviewer, String>("Average review time (days)");
            average_column.setStyle("-fx-alignment: CENTER-RIGHT;");
            average_column.setCellValueFactory(new PropertyValueFactory<EditorReviewer, String>("avg_rev_time"));

            TableColumn<EditorReviewer, Button> add_column = new TableColumn<EditorReviewer, Button>("");
            add_column.setStyle("-fx-alignment: CENTER;");
            add_column.setCellValueFactory(new PropertyValueFactory<EditorReviewer, Button>("add_b"));
            add_column.setSortable(false);

            interested_rev_tv.getColumns().addAll(name_column, publications_column, average_column, add_column);
        }

        // add reviewers to table views
        current_rev_tv.getItems().addAll(getCurrentReviewers(r_paper));
        preferred_rev_tv.getItems().addAll(getPreferredReviewers(r_paper));
        interested_rev_tv.getItems().addAll(getInterestedReviewers(r_paper));

        // tab pane
        TabPane main_tp = new TabPane();
        main_tp.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
        Tab current_rev_t = new Tab("Current Reviewers", current_rev_tv);
        Tab preferred_rev_t = new Tab("Preferred Reviewers", preferred_rev_tv);
        Tab interested_rev_t = new Tab("Interested Reviewers", interested_rev_tv);
        main_tp.getTabs().addAll(current_rev_t, preferred_rev_t, interested_rev_t);

        // center vbox
        VBox center_vb = new VBox();
        center_vb.setPadding(new Insets(10,10,10,10));
        center_vb.getChildren().addAll(main_tp);


        BorderPane main_pane = new BorderPane();

        main_pane.setTop(top_hb);
        main_pane.setCenter(center_vb);

        return new Scene(main_pane);

    }

    private static ArrayList<EditorReviewer> getPreferredReviewers (EditorPaper paper) {

        ArrayList<EditorReviewer> reviewers = new ArrayList<EditorReviewer>();

        JSONArray pref_rev_uid = (JSONArray) paper.getJson_obj().get("pref_rev_uid");

        for (Object o : pref_rev_uid) {

            JSONObject rev = JSONUserParser.getUserUID((String) o);

            reviewers.add(new EditorReviewer(rev, paper.getPaper_id(), "pref"));

        }
        return reviewers;
    }

    private static ArrayList<EditorReviewer> getCurrentReviewers (EditorPaper paper) {

        ArrayList<EditorReviewer> reviewers = new ArrayList<EditorReviewer>();

        JSONArray pref_rev_uid = (JSONArray) paper.getJson_obj().get("reviewers");

        for (Object o : pref_rev_uid) {

            JSONObject rev = JSONUserParser.getUserUID((String) o);

            reviewers.add(new EditorReviewer(rev, paper.getPaper_id(), "curr"));

        }
        return reviewers;
    }

    private static ArrayList<EditorReviewer> getInterestedReviewers (EditorPaper paper) {

        ArrayList<EditorReviewer> reviewers = new ArrayList<EditorReviewer>();

        JSONArray pref_rev_uid = (JSONArray) paper.getJson_obj().get("inter_rev_uid");

        for (Object o : pref_rev_uid) {

            JSONObject rev = JSONUserParser.getUserUID((String) o);

            reviewers.add(new EditorReviewer(rev, paper.getPaper_id(), "int"));

        }
        return reviewers;
    }


}
