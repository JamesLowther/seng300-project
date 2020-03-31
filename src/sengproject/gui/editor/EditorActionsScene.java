package sengproject.gui.editor;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import sengproject.gui.GuiController;
import sengproject.gui.editor.tvobjects.EditorAction;
import sengproject.gui.editor.tvobjects.EditorPaper;
import sengproject.gui.researcher.ResearcherMenuScene;
import sengproject.gui.researcher.tvobjects.ResearcherAction;
import sengproject.gui.researcher.tvobjects.ResearcherPaper;

import java.util.ArrayList;

public class EditorActionsScene {

    public static Scene getScene (EditorPaper r_paper) {

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
            GuiController.changeScene(EditorMenuScene.getScene());
        });

        // top spacer
        Region top_spacer_r = new Region();
        HBox.setHgrow(top_spacer_r, Priority.ALWAYS);

        // top hbox
        HBox top_hb = new HBox();
        top_hb.setPadding(new Insets(10, 10, 10, 10));
        top_hb.getChildren().addAll(name_vb, top_spacer_r, back_b);

        // pending actions
        TableView<EditorAction> pending_action_tv = new TableView<EditorAction>();
        {
            TableColumn<EditorAction, String> name_column = new TableColumn<EditorAction, String>("Action details");
            name_column.setCellValueFactory(new PropertyValueFactory<EditorAction, String>("action_details"));

            TableColumn<EditorAction, String> rev_uid_column = new TableColumn<EditorAction, String>("Reviewer name/UID");
            rev_uid_column.setCellValueFactory(new PropertyValueFactory<EditorAction, String>("rev_uid"));

            TableColumn<EditorAction, String> date_recommended_column = new TableColumn<EditorAction, String>("Date recommended");
            date_recommended_column.setCellValueFactory(new PropertyValueFactory<EditorAction, String>("date_recommended"));

            TableColumn<EditorAction, VBox>  buttons_column = new TableColumn<EditorAction, VBox> ("");
            buttons_column.setStyle("-fx-alignment: CENTER;");
            buttons_column.setCellValueFactory(new PropertyValueFactory<EditorAction, VBox> ("buttons_vb"));
            buttons_column.setSortable(false);

            pending_action_tv.getColumns().addAll(name_column, rev_uid_column, date_recommended_column, buttons_column);
        }

        // action history
        TableView<EditorAction> action_history_tv = new TableView<EditorAction>();
        {
            TableColumn<EditorAction, String> name_column = new TableColumn<EditorAction, String>("Action details");
            name_column.setCellValueFactory(new PropertyValueFactory<EditorAction, String>("action_details"));

            TableColumn<EditorAction, String> rev_uid_column = new TableColumn<EditorAction, String>("Reviewer name/UID");
            rev_uid_column.setCellValueFactory(new PropertyValueFactory<EditorAction, String>("rev_uid"));

            TableColumn<EditorAction, String> date_recommended_column = new TableColumn<EditorAction, String>("Date recommended");
            date_recommended_column.setCellValueFactory(new PropertyValueFactory<EditorAction, String>("date_recommended"));

            TableColumn<EditorAction, String> date_complete_column = new TableColumn<EditorAction, String>("Date completed");
            date_complete_column.setCellValueFactory(new PropertyValueFactory<EditorAction, String>("date_completed"));

            action_history_tv .getColumns().addAll(name_column, rev_uid_column, date_recommended_column, date_complete_column);
        }

        // add reviewers to table views
        ArrayList<EditorAction> actions_array = getEditorActions(r_paper);
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
        center_vb.setPadding(new Insets(10,10,10,10));
        center_vb.getChildren().addAll(main_tp);


        BorderPane main_pane = new BorderPane();

        main_pane.setTop(top_hb);
        main_pane.setCenter(center_vb);

        return new Scene(main_pane);

    }

    private static ArrayList<EditorAction> getEditorActions (EditorPaper r_paper) {

        ArrayList<EditorAction> actions = new ArrayList<EditorAction>();

        // todo: this is just test data
        actions.add(new EditorAction("Test Action 1", "1234", "01/01/2020", "02/02/2020"));
        actions.add(new EditorAction("Test Action 2", "1234", "01/01/2020", "02/02/2020"));
        actions.add(new EditorAction("Test Action 3", "1234", "01/01/2020", "02/02/2020"));
        actions.add(new EditorAction("Test Action 4", "1234", "01/01/2020", "02/02/2020"));

        return actions;
    }

}
