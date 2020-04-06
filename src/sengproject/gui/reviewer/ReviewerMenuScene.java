package sengproject.gui.reviewer;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import sengproject.Globals;
import sengproject.gui.GuiController;
import sengproject.gui.LoginScene;
import sengproject.gui.reviewer.tvobjects.ReviewerPaper;
import sengproject.jsonparsing.JSONPaperParser;
import sengproject.login.LoginHandler;

import java.util.ArrayList;

public class ReviewerMenuScene {

    public static Scene getScene () {

        // name box
        Label name_lb = new Label((String) Globals.getUser().get("username"));
        name_lb.setFont(new Font("Arial", 24));
        Label role_lb = new Label((String) Globals.getUser().get("role"));
        role_lb.setFont(new Font("Arial", 18));

        VBox name_vb = new VBox();
        name_vb.getChildren().addAll(name_lb, role_lb);
        name_vb.setAlignment(Pos.TOP_LEFT);

        // logout button
        Button logout_b = new Button("Logout");
        logout_b.setPrefSize(70, 40);
        logout_b.setOnAction(action -> {
            LoginHandler.logout();
            GuiController.changeScene(LoginScene.getScene());
        });

        // top spacer
        Region top_spacer_r = new Region();
        HBox.setHgrow(top_spacer_r, Priority.ALWAYS);

        // top hbox
        HBox top_hb = new HBox();
        top_hb.setPadding(new Insets(10, 10, 10, 10));
        top_hb.getChildren().addAll(name_vb, top_spacer_r, logout_b);


        // browser papers button
        Button browser_reviewers_b = new Button("Browse Papers");
        browser_reviewers_b.setPrefSize(150, 40);
        browser_reviewers_b.setOnAction(action -> {
            GuiController.changeScene(ReviewerBrowsePapersScene.getScene());
        });

        // bottom spacer
        Region bottom_spacer_r = new Region();
        HBox.setHgrow(bottom_spacer_r, Priority.ALWAYS);

        // bottom hbox
        HBox bottom_hb = new HBox();
        bottom_hb.setPadding(new Insets(10, 50, 10, 50));
        bottom_hb.getChildren().addAll(bottom_spacer_r, browser_reviewers_b);

        //  'your papers' label
        Label center_lb = new Label("Papers you are currently reviewing");
        center_lb.setFont(new Font("Arial", 16));

        // table view
        TableView<ReviewerPaper> papers_tv = new TableView<ReviewerPaper>();
        {

            TableColumn<ReviewerPaper, String> title_column = new TableColumn<ReviewerPaper, String>("Title");
            title_column.setCellValueFactory(new PropertyValueFactory<ReviewerPaper, String>("title"));

            TableColumn<ReviewerPaper, Button> details_column = new TableColumn<ReviewerPaper, Button>("");
            details_column.setStyle("-fx-alignment: CENTER;");
            details_column.setCellValueFactory(new PropertyValueFactory<ReviewerPaper, Button>("details_b"));
            details_column.setSortable(false);

            TableColumn<ReviewerPaper, Button> actions_column = new TableColumn<ReviewerPaper, Button>("");
            actions_column.setStyle("-fx-alignment: CENTER;");
            actions_column.setCellValueFactory(new PropertyValueFactory<ReviewerPaper, Button>("actions_b"));
            actions_column.setSortable(false);

            TableColumn<ReviewerPaper, String> deadline_column = new TableColumn<ReviewerPaper, String>("Review Deadline");
            deadline_column.setCellValueFactory(new PropertyValueFactory<ReviewerPaper, String>("rev_deadline"));

            papers_tv.getColumns().addAll(title_column, details_column, actions_column, deadline_column);

        }

        papers_tv.getItems().addAll(getReviewerPapers()); // add papers to tableview

        // center vbox
        VBox center_vb = new VBox();
        center_vb.setPadding(new Insets(10, 10, 10, 10));
        center_vb.getChildren().addAll(center_lb, papers_tv);


        BorderPane main_pane = new BorderPane();

        main_pane.setTop(top_hb);
        main_pane.setCenter(center_vb);
        main_pane.setBottom(bottom_hb);


        return new Scene(main_pane);

    }

    private static ArrayList<ReviewerPaper> getReviewerPapers () {

        ArrayList<ReviewerPaper> papers = new ArrayList<ReviewerPaper>();
        ArrayList<JSONObject> all_papers = JSONPaperParser.getResearcherPapers();

        for (JSONObject p : all_papers) {
            ArrayList<String> reviewers = (JSONArray) p.get("reviewers");

            for (Object r : reviewers) {
                String rid = (String) ((JSONObject) r).get("rid");
                if(rid.equals(Globals.getUser().get("uid"))) {
                    papers.add(new ReviewerPaper(p, "menu", false));
                }
            }
        }

        return papers;

    }
}
