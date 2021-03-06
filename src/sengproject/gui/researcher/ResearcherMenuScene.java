package sengproject.gui.researcher;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import sengproject.Globals;
import sengproject.gui.GuiController;
import sengproject.gui.LoginScene;
import sengproject.gui.researcher.tvobjects.ResearcherPaper;
import sengproject.jsonparsing.JSONJournalParser;
import sengproject.jsonparsing.JSONPaperParser;
import sengproject.login.LoginHandler;

import java.util.ArrayList;

import org.json.simple.JSONObject;

public class ResearcherMenuScene {

	public static ArrayList<ResearcherPaper> papers;
	
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
        logout_b.setPrefSize(70,40);
        logout_b.setOnAction(action ->{
            LoginHandler.logout();
            GuiController.changeScene(LoginScene.getScene());
        });

        // top spacer
        Region top_spacer_r = new Region();
        HBox.setHgrow(top_spacer_r, Priority.ALWAYS);

        // top hbox
        HBox top_hb = new HBox();
        top_hb.setPadding(new Insets(10,10,10,10));
        top_hb.getChildren().addAll(name_vb, top_spacer_r, logout_b);

        // new paper button
        Button new_paper_b = new Button("Upload New Paper");
        new_paper_b.setPrefSize(150,40);
        new_paper_b.setOnAction(action ->{
            GuiController.changeScene(ResearcherNewPaperScene.getScene());
        });

        // browser reviewers button
        Button browser_reviewers_b = new Button("Browse Reviewers");
        browser_reviewers_b.setPrefSize(150,40);
        browser_reviewers_b.setOnAction(action ->{
            GuiController.changeScene(ResearcherBrowseReviewersScene.getScene());
        });

        // bottom spacer
        Region bottom_spacer_r = new Region();
        HBox.setHgrow(bottom_spacer_r, Priority.ALWAYS);

        // bottom hbox
        HBox bottom_hb = new HBox();
        bottom_hb.setPadding(new Insets(10,50,10,50));
        bottom_hb.getChildren().addAll(new_paper_b, bottom_spacer_r, browser_reviewers_b);

        //  'your papers' label
        Label center_lb = new Label("Your papers");
        center_lb.setFont(new Font("Arial", 16));

        // table view
        TableView<ResearcherPaper> papers_tv = new TableView<ResearcherPaper>();

        TableColumn<ResearcherPaper, String> title_column = new TableColumn<ResearcherPaper, String> ("Title");
        title_column.setCellValueFactory(new PropertyValueFactory<ResearcherPaper, String> ("title"));

        TableColumn<ResearcherPaper, Button>  details_column = new TableColumn<ResearcherPaper, Button> ("");
        details_column.setStyle("-fx-alignment: CENTER;");
        details_column.setCellValueFactory(new PropertyValueFactory<ResearcherPaper, Button> ("details_b"));
        details_column.setSortable(false);

        TableColumn<ResearcherPaper, Button>  reviewers_column = new TableColumn<ResearcherPaper, Button> ("");
        reviewers_column.setStyle("-fx-alignment: CENTER;");
        reviewers_column.setCellValueFactory(new PropertyValueFactory<ResearcherPaper, Button> ("reviewers_b"));
        reviewers_column.setSortable(false);

        TableColumn<ResearcherPaper, Button>  actions_column = new TableColumn<ResearcherPaper, Button> ("");
        actions_column.setStyle("-fx-alignment: CENTER;");
        actions_column.setCellValueFactory(new PropertyValueFactory<ResearcherPaper, Button> ("actions_b"));
        actions_column.setSortable(false);

        TableColumn<ResearcherPaper, String>  deadline_column = new TableColumn<ResearcherPaper, String> ("Deadline");
        deadline_column.setCellValueFactory(new PropertyValueFactory<ResearcherPaper, String> ("deadline"));

        papers_tv.getColumns().addAll(title_column, details_column, reviewers_column, actions_column, deadline_column);

        papers_tv.getItems().addAll(getResearcherPapers()); // add papers to tableview

        // center vbox
        VBox center_vb = new VBox();
        center_vb.setPadding(new Insets(10,10,10,10));
        center_vb.getChildren().addAll(center_lb, papers_tv);

        BorderPane main_pane = new BorderPane();

        main_pane.setTop(top_hb);
        main_pane.setCenter(center_vb);
        main_pane.setBottom(bottom_hb);

        return new Scene(main_pane);

    }

    // returns an arraylist of all the ResearcherPaper objects to add to the main scene list
    private static ArrayList<ResearcherPaper> getResearcherPapers () {

    	ArrayList<JSONObject> paperJ = JSONPaperParser.getResearcherPapers();
    	if (paperJ == null) {
    		papers = new ArrayList<ResearcherPaper>();
    		return papers;
    	}
    	ArrayList<ResearcherPaper> temp = new ArrayList<ResearcherPaper>();
    	for (JSONObject pj : paperJ) {
        	String authorID = (String) pj.get("author_id");
        	String userID = Globals.getUser().get("uid").toString();
        	if (authorID.equals(userID)) {
        		temp.add(new ResearcherPaper(pj));
        	}
        }
    	papers = temp;
    	return papers;
    	
    }

}
