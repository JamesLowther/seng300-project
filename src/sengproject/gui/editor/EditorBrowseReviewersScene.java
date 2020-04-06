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
import sengproject.gui.editor.tvobjects.EditorReviewer;
import sengproject.gui.researcher.ResearcherMenuScene;
import sengproject.gui.researcher.tvobjects.ResearcherReviewer;
import sengproject.jsonparsing.JSONUserParser;

import java.util.ArrayList;

public class EditorBrowseReviewersScene {

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
            GuiController.changeScene(EditorMenuScene.getScene());
        });

        // top spacer
        Region top_spacer_r = new Region();
        HBox.setHgrow(top_spacer_r, Priority.ALWAYS);

        // top hbox
        HBox top_hb = new HBox();
        top_hb.setPadding(new Insets(10, 10, 10, 10));
        top_hb.getChildren().addAll(name_vb, top_spacer_r, back_b);

        // current reviewers
        TableView<EditorReviewer> all_rev_tv = new TableView<EditorReviewer>();
        {
            TableColumn<EditorReviewer, String> name_column = new TableColumn<EditorReviewer, String>("Name");
            name_column.setCellValueFactory(new PropertyValueFactory<EditorReviewer, String>("name"));

            TableColumn<EditorReviewer, String> major_rev_column = new TableColumn<EditorReviewer, String>("Major revisions");
            major_rev_column.setStyle("-fx-alignment: CENTER-RIGHT;");
            major_rev_column.setCellValueFactory(new PropertyValueFactory<EditorReviewer, String>("num_major_rev"));

            TableColumn<EditorReviewer, String> minor_rev_column = new TableColumn<EditorReviewer, String>("Minor revisions");
            minor_rev_column.setStyle("-fx-alignment: CENTER-RIGHT;");
            minor_rev_column.setCellValueFactory(new PropertyValueFactory<EditorReviewer, String>("num_minor_rev"));

            TableColumn<EditorReviewer, String> publications_column = new TableColumn<EditorReviewer, String>("Total # of publications viewed");
            publications_column.setStyle("-fx-alignment: CENTER-RIGHT;");
            publications_column.setCellValueFactory(new PropertyValueFactory<EditorReviewer, String>("num_pub_rev"));

            TableColumn<EditorReviewer, String> average_column = new TableColumn<EditorReviewer, String>("Average review time (days)");
            average_column.setStyle("-fx-alignment: CENTER-RIGHT;");
            average_column.setCellValueFactory(new PropertyValueFactory<EditorReviewer, String>("avg_rev_time"));

            all_rev_tv.getColumns().addAll(name_column, major_rev_column, minor_rev_column, publications_column, average_column);
        }

        // add reviewers to table views
        ArrayList<EditorReviewer> reviewers_array = getEditorReviewers();
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

    private static ArrayList<EditorReviewer> getEditorReviewers () {

        ArrayList<EditorReviewer> reviewers = new ArrayList<EditorReviewer>();
        ArrayList<JSONObject> all_reviewers = JSONUserParser.getUsersFromRole("Reviewer");

        for (JSONObject p : all_reviewers) {

            reviewers.add(new EditorReviewer(p, null, null, null));
        }

        return reviewers;


    }


}

