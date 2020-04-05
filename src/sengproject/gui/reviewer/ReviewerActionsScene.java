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
import sengproject.gui.GuiController;
import sengproject.gui.researcher.ResearcherMenuScene;
import sengproject.gui.researcher.tvobjects.ResearcherAction;
import sengproject.gui.researcher.tvobjects.ResearcherPaper;
import sengproject.gui.reviewer.tvobjects.ReviewerAction;
import sengproject.gui.reviewer.tvobjects.ReviewerPaper;

import java.util.ArrayList;

public class ReviewerActionsScene {

    public static Scene getScene(ReviewerPaper r_paper) {

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
            GuiController.changeScene(ReviewerMenuScene.getScene());
        });

        // top spacer
        Region top_spacer_r = new Region();
        HBox.setHgrow(top_spacer_r, Priority.ALWAYS);

        // top hbox
        HBox top_hb = new HBox();
        top_hb.setPadding(new Insets(10, 10, 10, 10));
        top_hb.getChildren().addAll(name_vb, top_spacer_r, back_b);

        // pending actions
        TableView<ReviewerAction> pending_action_tv = new TableView<ReviewerAction>();
        {
            TableColumn<ReviewerAction, String> name_column = new TableColumn<ReviewerAction, String>("Action details");
            name_column.setCellValueFactory(new PropertyValueFactory<ReviewerAction, String>("action_details"));

            TableColumn<ReviewerAction, String> type_column = new TableColumn<ReviewerAction, String>("Type");
            type_column.setCellValueFactory(new PropertyValueFactory<ReviewerAction, String>("type"));

            TableColumn<ReviewerAction, String> rev_uid_column = new TableColumn<ReviewerAction, String>("Reviewer name/UID");
            rev_uid_column.setCellValueFactory(new PropertyValueFactory<ReviewerAction, String>("rev_uid"));

            TableColumn<ReviewerAction, String> date_recommended_column = new TableColumn<ReviewerAction, String>("Date recommended");
            date_recommended_column.setCellValueFactory(new PropertyValueFactory<ReviewerAction, String>("date_recommended"));


            pending_action_tv.getColumns().addAll(name_column, type_column, rev_uid_column, date_recommended_column);
        }

        // action history
        TableView<ReviewerAction> action_history_tv = new TableView<ReviewerAction>();
        {
            TableColumn<ReviewerAction, String> name_column = new TableColumn<ReviewerAction, String>("Action details");
            name_column.setCellValueFactory(new PropertyValueFactory<ReviewerAction, String>("action_details"));

            TableColumn<ReviewerAction, String> rev_uid_column = new TableColumn<ReviewerAction, String>("Reviewer name/UID");
            rev_uid_column.setCellValueFactory(new PropertyValueFactory<ReviewerAction, String>("rev_uid"));

            TableColumn<ReviewerAction, String> date_recommended_column = new TableColumn<ReviewerAction, String>("Date recommended");
            date_recommended_column.setCellValueFactory(new PropertyValueFactory<ReviewerAction, String>("date_recommended"));

            TableColumn<ReviewerAction, String> date_complete_column = new TableColumn<ReviewerAction, String>("Date completed");
            date_complete_column.setCellValueFactory(new PropertyValueFactory<ReviewerAction, String>("date_completed"));

            action_history_tv.getColumns().addAll(name_column, rev_uid_column, date_recommended_column, date_complete_column);
        }

        // add reviewers to table views
        ArrayList<ReviewerAction> actions_array = getReviewerActions(r_paper);
        pending_action_tv.getItems().addAll(actions_array);
        action_history_tv.getItems().addAll(actions_array);

        // tab pane
        TabPane main_tp = new TabPane();
        main_tp.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
        Tab pending_actions_t = new Tab("Pending Actions", pending_action_tv);
        Tab action_history_t = new Tab("Action History", action_history_tv);
        main_tp.getTabs().addAll(pending_actions_t, action_history_t);

        // center vbox
        VBox center_vb = new VBox();
        center_vb.setPadding(new Insets(10, 10, 10, 10));
        center_vb.getChildren().addAll(main_tp);

        // new action button
        Button new_action_b = new Button("Add New Action");
        new_action_b.setPrefSize(150, 40);
        new_action_b.setOnAction(action -> {
            GuiController.changeScene(ReviewerNewActionScene.getScene(r_paper));
        });

        // bottom hbox
        HBox bottom_hb = new HBox();
        bottom_hb.setPadding(new Insets(10, 50, 10, 50));
        bottom_hb.getChildren().addAll(new_action_b);

        BorderPane main_pane = new BorderPane();

        main_pane.setTop(top_hb);
        main_pane.setCenter(center_vb);
        main_pane.setBottom(bottom_hb);


        // new action popup
        ToggleGroup radio_tg = new ToggleGroup();

        RadioButton minor_rb = new RadioButton("Minor Revision");
        minor_rb.setToggleGroup(radio_tg);

        RadioButton major_rb = new RadioButton("Major Revision");
        major_rb.setToggleGroup(radio_tg);

        HBox action_radio_hb = new HBox();
        action_radio_hb.getChildren().addAll(minor_rb, major_rb);

        // popup pane
        BorderPane popup_bp = new BorderPane();

        popup_bp.setTop(action_radio_hb);

        return new Scene(main_pane);

    }

    private static ArrayList<ReviewerAction> getReviewerActions(ReviewerPaper r_paper) {

        ArrayList<ReviewerAction> actions = new ArrayList<ReviewerAction>();

        JSONArray paper_actions = (JSONArray) r_paper.getPaper().get("pending_actions");

        for (Object a : paper_actions) {
            actions.add(new ReviewerAction((JSONObject) a));
        }

        return actions;

    }
}
