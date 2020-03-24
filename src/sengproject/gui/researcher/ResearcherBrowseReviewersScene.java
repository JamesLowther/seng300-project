package sengproject.gui.researcher;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import org.json.simple.JSONObject;
import sengproject.gui.GuiController;
import sengproject.gui.researcher.tvobjects.ResearcherReviewer;
import sengproject.jsonparsing.JSONUserParser;

import java.util.ArrayList;

public class ResearcherBrowseReviewersScene {

    public static Scene getScene () {

        // details box
        Label details_lb = new Label("Browse Reviewers");
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

        // current reviewers
        TableView<ResearcherReviewer> all_rev_tv = new TableView<ResearcherReviewer>();
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

            all_rev_tv.getColumns().addAll(name_column, major_rev_column, minor_rev_column, publications_column, average_column);
        }

        // add reviewers to table views
        ArrayList<ResearcherReviewer> reviewers_array = getResearcherReviewers();
        all_rev_tv.getItems().addAll(reviewers_array);

        // center vbox
        VBox center_vb = new VBox();
        center_vb.setPadding(new Insets(10,10,10,10));
        center_vb.getChildren().addAll(all_rev_tv);

        BorderPane main_pane = new BorderPane();

        main_pane.setTop(top_hb);
        main_pane.setCenter(center_vb);

        return new Scene(main_pane);

    }

    private static ArrayList<ResearcherReviewer> getResearcherReviewers () {

        ArrayList<ResearcherReviewer> reviewers = new ArrayList<ResearcherReviewer>();
        ArrayList<JSONObject> all_reviewers = JSONUserParser.getUsersFromRole("Reviewer");

        for (JSONObject p : all_reviewers) {

            //todo: calculate averages
            reviewers.add(new ResearcherReviewer(
                    (String) p.get("username"),
                    1,
                    2,
                    3,
                    4
            ));
        }

        return reviewers;


    }


}
