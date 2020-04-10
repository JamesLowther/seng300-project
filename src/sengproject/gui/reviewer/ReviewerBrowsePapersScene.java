package sengproject.gui.reviewer;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import sengproject.Globals;
import sengproject.gui.GuiController;
import sengproject.gui.reviewer.tvobjects.ReviewerPaper;
import sengproject.jsonparsing.JSONPaperParser;
import sengproject.shared.ReviewerFunctions;

import java.util.ArrayList;

public class ReviewerBrowsePapersScene {

    public static Scene getScene () {

        // available papers
        TableView<ReviewerPaper> available_papers_tv = new TableView<ReviewerPaper>();
        {

            TableColumn<ReviewerPaper, String> title_column = new TableColumn<ReviewerPaper, String>("Title");
            title_column.setCellValueFactory(new PropertyValueFactory<ReviewerPaper, String>("title"));

            TableColumn<ReviewerPaper, Button> details_column = new TableColumn<ReviewerPaper, Button>("");
            details_column.setStyle("-fx-alignment: CENTER-RIGHT;");
            details_column.setCellValueFactory(new PropertyValueFactory<ReviewerPaper, Button>("details_b"));
            details_column.setSortable(false);

            TableColumn<ReviewerPaper, CheckBox> star_column = new TableColumn<ReviewerPaper, CheckBox>("Preferred?");
            star_column.setStyle("-fx-alignment: CENTER-RIGHT;");
            star_column.setCellValueFactory(new PropertyValueFactory<ReviewerPaper, CheckBox>("star_cb"));
            star_column.setSortable(false);

            available_papers_tv.getColumns().addAll(title_column, details_column, star_column);
        }

        // add reviewers to table views
        ArrayList<ReviewerPaper> papers_array = getReviewerPapers();
        available_papers_tv.getItems().addAll(papers_array);

        // center vbox
        VBox center_vb = new VBox();
        center_vb.setPadding(new Insets(10,10,10,10));
        center_vb.getChildren().addAll(available_papers_tv);

        // details box
        Label details_lb = new Label("Browse Papers");
        details_lb.setFont(new Font("Arial", 24));

        VBox name_vb = new VBox();
        name_vb.getChildren().addAll(details_lb);
        name_vb.setAlignment(Pos.TOP_LEFT);

        // return button
        Button back_b = new Button("Back");
        back_b.setPrefSize(70, 40);
        back_b.setOnAction(action -> {
            updateStatus(papers_array);
            GuiController.changeScene(ReviewerMenuScene.getScene());
        });

        // top spacer
        Region top_spacer_r = new Region();
        HBox.setHgrow(top_spacer_r, Priority.ALWAYS);

        // top hbox
        HBox top_hb = new HBox();
        top_hb.setPadding(new Insets(10, 10, 10, 10));
        top_hb.getChildren().addAll(name_vb, top_spacer_r, back_b);

        BorderPane main_pane = new BorderPane();

        main_pane.setTop(top_hb);
        main_pane.setCenter(center_vb);

        return new Scene(main_pane);

    }

    // returns an array of ReviewerPaper objects for every paper
    private static ArrayList<ReviewerPaper> getReviewerPapers () {

        ArrayList<ReviewerPaper> papers = new ArrayList<ReviewerPaper>();

        ArrayList<JSONObject> all_papers = JSONPaperParser.getResearcherPapers();

        for (JSONObject p : all_papers) {

            JSONArray int_rev = (JSONArray) p.get("inter_rev_uid");

            papers.add(new ReviewerPaper(p, "browse", int_rev.contains((String) Globals.getUser().get("uid"))));
        }

        return papers;

    }

    // updates the whether a reviewer is interested in a paper
    private static void updateStatus(ArrayList<ReviewerPaper> arr) {

        for (ReviewerPaper r : arr) {

            if (r.getCheckedStatus()) {
                ReviewerFunctions.addInterestedReviewer(r.getPaper_id(), (String) Globals.getUser().get("uid"));
            } else {
                ReviewerFunctions.removeInterestedReviewer(r.getPaper_id(), (String) Globals.getUser().get("uid"));
            }

        }

    }

}
