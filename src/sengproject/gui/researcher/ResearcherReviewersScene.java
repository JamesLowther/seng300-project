package sengproject.gui.researcher;

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
import sengproject.gui.researcher.tvobjects.ResearcherPaper;
import sengproject.gui.researcher.tvobjects.ResearcherReviewer;
import sengproject.jsonparsing.JSONUserParser;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class ResearcherReviewersScene {

    public static Scene getScene (ResearcherPaper r_paper) {

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
            GuiController.changeScene(ResearcherMenuScene.getScene());
        });

        // top spacer
        Region top_spacer_r = new Region();
        HBox.setHgrow(top_spacer_r, Priority.ALWAYS);

        // top hbox
        HBox top_hb = new HBox();
        top_hb.setPadding(new Insets(10, 10, 10, 10));
        top_hb.getChildren().addAll(name_vb, top_spacer_r, logout_b);

        // current reviewers
        TableView<ResearcherReviewer> current_rev_tv = new TableView<ResearcherReviewer>();
        {
            TableColumn<ResearcherReviewer, String> name_column = new TableColumn<ResearcherReviewer, String>("Name");
            name_column.setCellValueFactory(new PropertyValueFactory<ResearcherReviewer, String>("name"));

            TableColumn<ResearcherReviewer, String> major_rev_column = new TableColumn<ResearcherReviewer, String>("Major revisions");
            major_rev_column.setStyle("-fx-alignment: CENTER-RIGHT;");
            major_rev_column.setCellValueFactory(new PropertyValueFactory<ResearcherReviewer, String>("num_major_rev"));

            TableColumn<ResearcherReviewer, String> minor_rev_column = new TableColumn<ResearcherReviewer, String>("Minor revisions");
            minor_rev_column.setStyle("-fx-alignment: CENTER-RIGHT;");
            minor_rev_column.setCellValueFactory(new PropertyValueFactory<ResearcherReviewer, String>("num_minor_rev"));

            TableColumn<ResearcherReviewer, String> publications_column = new TableColumn<ResearcherReviewer, String>("Total # of publications viewed");
            publications_column.setStyle("-fx-alignment: CENTER-RIGHT;");
            publications_column.setCellValueFactory(new PropertyValueFactory<ResearcherReviewer, String>("num_pub_rev"));

            TableColumn<ResearcherReviewer, String> average_column = new TableColumn<ResearcherReviewer, String>("Average review time (days)");
            average_column.setStyle("-fx-alignment: CENTER-RIGHT;");
            average_column.setCellValueFactory(new PropertyValueFactory<ResearcherReviewer, String>("avg_rev_time"));

            current_rev_tv.getColumns().addAll(name_column, major_rev_column, minor_rev_column, publications_column, average_column);
        }

        // preferred reviewers
        TableView<ResearcherReviewer> preferred_rev_tv = new TableView<ResearcherReviewer>();
        {
            TableColumn<ResearcherReviewer, String> name_column = new TableColumn<ResearcherReviewer, String>("Name");
            name_column.setCellValueFactory(new PropertyValueFactory<ResearcherReviewer, String>("name"));

            TableColumn<ResearcherReviewer, String> publications_column = new TableColumn<ResearcherReviewer, String>("Total # of publications viewed");
            publications_column.setStyle("-fx-alignment: CENTER-RIGHT;");
            publications_column.setCellValueFactory(new PropertyValueFactory<ResearcherReviewer, String>("num_pub_rev"));

            TableColumn<ResearcherReviewer, String> average_column = new TableColumn<ResearcherReviewer, String>("Average review time (days)");
            average_column.setStyle("-fx-alignment: CENTER-RIGHT;");
            average_column.setCellValueFactory(new PropertyValueFactory<ResearcherReviewer, String>("avg_rev_time"));

            preferred_rev_tv.getColumns().addAll(name_column, publications_column, average_column);
        }

        // current reviewers
        TableView<ResearcherReviewer> interested_rev_tv = new TableView<ResearcherReviewer>();
        {

            TableColumn<ResearcherReviewer, String> name_column = new TableColumn<ResearcherReviewer, String>("Name");
            name_column.setCellValueFactory(new PropertyValueFactory<ResearcherReviewer, String>("name"));

            TableColumn<ResearcherReviewer, String> publications_column = new TableColumn<ResearcherReviewer, String>("Total # of publications viewed");
            publications_column.setStyle("-fx-alignment: CENTER-RIGHT;");
            publications_column.setCellValueFactory(new PropertyValueFactory<ResearcherReviewer, String>("num_pub_rev"));

            TableColumn<ResearcherReviewer, String> average_column = new TableColumn<ResearcherReviewer, String>("Average review time (days)");
            average_column.setStyle("-fx-alignment: CENTER-RIGHT;");
            average_column.setCellValueFactory(new PropertyValueFactory<ResearcherReviewer, String>("avg_rev_time"));

            interested_rev_tv.getColumns().addAll(name_column, publications_column, average_column);
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

    // returns an array of preferred ResearcherReviewer objects for the selected paper
    private static ArrayList<ResearcherReviewer> getPreferredReviewers (ResearcherPaper paper) {

        ArrayList<ResearcherReviewer> reviewers = new ArrayList<ResearcherReviewer>();

        JSONArray pref_rev_uid = (JSONArray) paper.getJson_obj().get("pref_rev_uid");

        for (Object o : pref_rev_uid) {

            JSONObject rev = JSONUserParser.getUserUID((String) o);

            reviewers.add(new ResearcherReviewer(
                    (String) rev.get("username"),
                    (long) rev.get("major_rev"),
                    (long) rev.get("minor_rev"),
                    (long) rev.get("papers_reviewed"),
                    (double) ((JSONArray) rev.get("avg_time")).get(0)
            ));

        }
        return reviewers;
    }

    // returns an array of current ResearcherReviewer objects for the selected paper
    private static ArrayList<ResearcherReviewer> getCurrentReviewers (ResearcherPaper paper) {

        ArrayList<ResearcherReviewer> reviewers = new ArrayList<ResearcherReviewer>();

        JSONArray pref_rev_uid = (JSONArray) paper.getJson_obj().get("reviewers");

        for (Object o : pref_rev_uid) {

            JSONObject rev = JSONUserParser.getUserUID((String) ((JSONObject) o).get("rid"));

            reviewers.add(new ResearcherReviewer(
                    (String) rev.get("username"),
                    (long) rev.get("major_rev"),
                    (long) rev.get("minor_rev"),
                    (long) rev.get("papers_reviewed"),
                    (double) ((JSONArray) rev.get("avg_time")).get(0)
            ));

        }
        return reviewers;
    }

    // returns an array of interested ResearcherReviewer objects for the selected paper
    private static ArrayList<ResearcherReviewer> getInterestedReviewers (ResearcherPaper paper) {

        ArrayList<ResearcherReviewer> reviewers = new ArrayList<ResearcherReviewer>();

        JSONArray pref_rev_uid = (JSONArray) paper.getJson_obj().get("inter_rev_uid");

        for (Object o : pref_rev_uid) {

            JSONObject rev = JSONUserParser.getUserUID((String) o);

            reviewers.add(new ResearcherReviewer(
                    (String) rev.get("username"),
                    (long) rev.get("major_rev"),
                    (long) rev.get("minor_rev"),
                    (long) rev.get("papers_reviewed"),
                    (double) ((JSONArray) rev.get("avg_time")).get(0)
            ));

        }
        return reviewers;
    }

}
