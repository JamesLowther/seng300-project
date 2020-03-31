package sengproject.gui.editor;

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
import org.json.simple.JSONObject;
import sengproject.Globals;
import sengproject.gui.GuiController;
import sengproject.gui.LoginScene;
import sengproject.gui.editor.tvobjects.EditorPaper;
import sengproject.jsonparsing.JSONJournalParser;
import sengproject.jsonparsing.JSONPaperParser;
import sengproject.login.LoginHandler;

import java.util.ArrayList;

public class EditorMenuScene {

    public static ArrayList<EditorPaper> papers;

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

        // browser reviewers button
        Button browser_reviewers_b = new Button("Browse Reviewers");
        browser_reviewers_b.setPrefSize(150,40);
        browser_reviewers_b.setOnAction(action ->{
            GuiController.changeScene(EditorBrowseReviewersScene.getScene());
        });

        // bottom spacer
        Region bottom_spacer_r = new Region();
        HBox.setHgrow(bottom_spacer_r, Priority.ALWAYS);

        // bottom hbox
        HBox bottom_hb = new HBox();
        bottom_hb.setPadding(new Insets(10,50,10,50));
        bottom_hb.getChildren().addAll(bottom_spacer_r, browser_reviewers_b);

        //  'Journal Name' label
        //todo: journal name
        //  needs to get the journal associated in with the editor and set the label text
        ArrayList<JSONObject> journals = JSONJournalParser.getJournals((String) Globals.getUser().get("uid"));
        String journal_name;
        try {
            journal_name = (String) journals.get(0).get("title");
        } catch (IndexOutOfBoundsException e) {
            journal_name = "No Journal Associated With User";
        }

        Label center_lb = new Label(journal_name);
        center_lb.setFont(new Font("Arial", 16));

        // table view
        TableView<EditorPaper> papers_tv = new TableView<EditorPaper>();

        TableColumn<EditorPaper, String> title_column = new TableColumn<EditorPaper, String> ("Title");
        title_column.setCellValueFactory(new PropertyValueFactory<EditorPaper, String>("title"));

        TableColumn<EditorPaper, Button>  details_column = new TableColumn<EditorPaper, Button> ("");
        details_column.setStyle("-fx-alignment: CENTER;");
        details_column.setCellValueFactory(new PropertyValueFactory<EditorPaper, Button> ("details_b"));
        details_column.setSortable(false);

        TableColumn<EditorPaper, Button>  reviewers_column = new TableColumn<EditorPaper, Button> ("");
        reviewers_column.setStyle("-fx-alignment: CENTER;");
        reviewers_column.setCellValueFactory(new PropertyValueFactory<EditorPaper, Button> ("reviewers_b"));
        reviewers_column.setSortable(false);

        TableColumn<EditorPaper, Button>  actions_column = new TableColumn<EditorPaper, Button> ("");
        actions_column.setStyle("-fx-alignment: CENTER;");
        actions_column.setCellValueFactory(new PropertyValueFactory<EditorPaper, Button> ("actions_b"));
        actions_column.setSortable(false);

        TableColumn<EditorPaper, String>  due_date_column = new TableColumn<EditorPaper, String> ("Deadline");
        due_date_column.setStyle("-fx-alignment: CENTER;");
        due_date_column.setCellValueFactory(new PropertyValueFactory<EditorPaper, String> ("deadline"));

        papers_tv.getColumns().addAll(title_column, details_column, reviewers_column, actions_column, due_date_column);

        papers_tv.getItems().addAll(getEditorPapers()); // add papers to tableview

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

    private static ArrayList<EditorPaper> getEditorPapers () {

        // get jid
        ArrayList<JSONObject> journals = JSONJournalParser.getJournals((String) Globals.getUser().get("uid"));
        String jid;
        try {
            jid = (String) journals.get(0).get("jid");
        } catch (IndexOutOfBoundsException e) {
            papers = new ArrayList<EditorPaper>();
            return papers;
        }

        System.out.println(jid);

        ArrayList<JSONObject> paperJ = JSONPaperParser.getResearcherPapers();
        if (paperJ == null) {
            papers = new ArrayList<EditorPaper>();
            return papers;
        }
        ArrayList<EditorPaper> temp = new ArrayList<EditorPaper>();
        for (JSONObject pj : paperJ) {
            String journalID = (String) pj.get("journal_id");
            if (journalID.equals(jid)) {
                temp.add(new EditorPaper(pj));
            }
        }

        return temp;

    }

}
