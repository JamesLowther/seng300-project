package sengproject.gui.researcher;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import sengproject.gui.GuiController;
import sengproject.gui.researcher.tvobjects.ResearcherAction;
import sengproject.gui.researcher.tvobjects.ResearcherPaper;
import sengproject.gui.reviewer.tvobjects.ReviewerAction;

import java.util.ArrayList;

public class ResearcherActionsScene {

    public static Scene getScene (ResearcherPaper r_paper) {

        // details box
        Label details_lb = new Label("Actions");
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

        // pending actions
        TableView<ResearcherAction> pending_action_tv = new TableView<ResearcherAction>();
        {
            TableColumn<ResearcherAction, String> name_column = new TableColumn<ResearcherAction, String>("Action details");
            name_column.setCellValueFactory(new PropertyValueFactory<ResearcherAction, String>("action_details"));

            TableColumn<ResearcherAction, String> type_column = new TableColumn<ResearcherAction, String>("Type");
            type_column.setCellValueFactory(new PropertyValueFactory<ResearcherAction, String>("type"));

            TableColumn<ResearcherAction, String> rev_uid_column = new TableColumn<ResearcherAction, String>("Reviewer name/UID");
            rev_uid_column.setCellValueFactory(new PropertyValueFactory<ResearcherAction, String>("rev_uid"));

            TableColumn<ResearcherAction, String> date_recommended_column = new TableColumn<ResearcherAction, String>("Date recommended");
            date_recommended_column.setCellValueFactory(new PropertyValueFactory<ResearcherAction, String>("date_recommended"));

            TableColumn<ResearcherAction, Button>  done_column = new TableColumn<ResearcherAction, Button> ("");
            done_column.setStyle("-fx-alignment: CENTER;");
            done_column.setCellValueFactory(new PropertyValueFactory<ResearcherAction, Button> ("done_b"));
            done_column.setSortable(false);

            pending_action_tv.getColumns().addAll(name_column, type_column, rev_uid_column, date_recommended_column, done_column);
        }

        // action history
        TableView<ResearcherAction> action_history_tv = new TableView<ResearcherAction>();
        {
            TableColumn<ResearcherAction, String> name_column = new TableColumn<ResearcherAction, String>("Action details");
            name_column.setCellValueFactory(new PropertyValueFactory<ResearcherAction, String>("action_details"));

            TableColumn<ResearcherAction, String> rev_uid_column = new TableColumn<ResearcherAction, String>("Reviewer name/UID");
            rev_uid_column.setCellValueFactory(new PropertyValueFactory<ResearcherAction, String>("rev_uid"));

            TableColumn<ResearcherAction, String> date_recommended_column = new TableColumn<ResearcherAction, String>("Date recommended");
            date_recommended_column.setCellValueFactory(new PropertyValueFactory<ResearcherAction, String>("date_recommended"));

            TableColumn<ResearcherAction, String> date_complete_column = new TableColumn<ResearcherAction, String>("Date completed");
            date_complete_column.setCellValueFactory(new PropertyValueFactory<ResearcherAction, String>("date_completed"));

            action_history_tv .getColumns().addAll(name_column, rev_uid_column, date_recommended_column, date_complete_column);
        }

        // add reviewers to table views
        ArrayList<ResearcherAction> actions_array = getResearcherActions(r_paper);
        pending_action_tv.getItems().addAll(actions_array);
        ArrayList<ResearcherAction> completed_array = getCompletedActions(r_paper);
        action_history_tv.getItems().addAll(completed_array);

        // tab pane
        TabPane main_tp = new TabPane();
        main_tp.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
        Tab pending_actions_t = new Tab("Pending Actions", pending_action_tv);
        Tab action_history_t = new Tab("Action History", action_history_tv);
        main_tp.getTabs().addAll(pending_actions_t, action_history_t);

        // center vbox
        VBox center_vb = new VBox();
        center_vb.setPadding(new Insets(10,10,10,10));
        center_vb.getChildren().addAll(main_tp);


        BorderPane main_pane = new BorderPane();

        main_pane.setTop(top_hb);
        main_pane.setCenter(center_vb);

        return new Scene(main_pane);

    }

    // returns an array of ResearcherAction objects for the currently selected paper
    private static ArrayList<ResearcherAction> getResearcherActions (ResearcherPaper r_paper) {

        ArrayList<ResearcherAction> actions = new ArrayList<ResearcherAction>();

        JSONArray paper_actions = (JSONArray) r_paper.getJson_obj().get("actions");

        for (Object a : paper_actions) {
            actions.add(new ResearcherAction((JSONObject) a, r_paper.getPaper_id()));
        }

        return actions;
    }

    // returns an array of completed ResearcherAction objects for the currently selected paper
    private static ArrayList<ResearcherAction> getCompletedActions (ResearcherPaper r_paper) {

        ArrayList<ResearcherAction> actions = new ArrayList<ResearcherAction>();

        JSONArray paper_actions = (JSONArray) r_paper.getJson_obj().get("complete_actions");

        for (Object a : paper_actions) {
            actions.add(new ResearcherAction((JSONObject) a, r_paper.getPaper_id()));
        }

        return actions;
    }


}
