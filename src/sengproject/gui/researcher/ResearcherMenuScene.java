package sengproject.gui.researcher;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import sengproject.Globals;
import sengproject.gui.GuiController;
import sengproject.gui.LoginScene;
import sengproject.login.LoginHandler;

public class ResearcherMenuScene {

    public static Scene getScene() {

        // pane text
        Label scene_title = new Label("Welcome back " + (String) Globals.getUser().get("username") + "!");
        scene_title.setFont(new Font("Arial", 30));
        VBox title = new VBox(scene_title);
        title.setAlignment(Pos.TOP_CENTER);
        title.setPadding(new Insets(0, 50, 10, 50));
        title.setSpacing(10);

        // submit new paper
        Button new_submission_b = new Button("New Paper");
        new_submission_b.setAlignment(Pos.CENTER);
        new_submission_b.setPrefSize(150,50);
        new_submission_b.setOnAction(action -> {

        });


        // manage papers
        Button manage_papers_b = new Button("Manage Papers");
        manage_papers_b.setAlignment(Pos.CENTER);
        manage_papers_b.setPrefSize(150,50);
        manage_papers_b.setOnAction(action -> {

        });

        // view history
        Button view_history_b = new Button("View History");
        view_history_b.setAlignment(Pos.CENTER);
        view_history_b.setPrefSize(150,50);
        view_history_b.setOnAction(action -> {

            GuiController.changeScene(ResearcherHistoryScene.getScene());

        });

        // access reviewers
        Button access_reviewers_b = new Button("Search Reviewers");
        access_reviewers_b.setAlignment(Pos.CENTER);
        access_reviewers_b.setPrefSize(150,50);
        access_reviewers_b.setOnAction(action -> {

        });

        // menu button container
        GridPane menu_buttons = new GridPane();
        menu_buttons.setHgap(20);
        menu_buttons.setVgap(20);
        menu_buttons.setAlignment(Pos.CENTER);

        menu_buttons.add(new_submission_b, 0, 0, 1, 1);
        menu_buttons.setHalignment(new_submission_b, HPos.CENTER);
        menu_buttons.add(manage_papers_b,0, 1, 1, 1);
        menu_buttons.setHalignment(manage_papers_b, HPos.CENTER);
        menu_buttons.add(view_history_b, 1, 0 , 1, 1);
        menu_buttons.setHalignment(view_history_b, HPos.CENTER);
        menu_buttons.add(access_reviewers_b, 1, 1, 1, 1);
        menu_buttons.setHalignment(access_reviewers_b, HPos.CENTER);

        // return button
        Button return_button = new Button("Logout");
        return_button.setAlignment(Pos.BOTTOM_CENTER);
        return_button.setDefaultButton(true);
        return_button.setOnAction(action ->{

            LoginHandler.logout();
            GuiController.changeScene(LoginScene.getScene());

        });

        //vertical container
        VBox elements = new VBox();
        elements.getChildren().addAll(title, menu_buttons, return_button);
        elements.setSpacing(70);
        elements.setAlignment(Pos.CENTER);

        StackPane pane = new StackPane();
        pane.getChildren().add(elements);

        return new Scene(pane);

    }


}
