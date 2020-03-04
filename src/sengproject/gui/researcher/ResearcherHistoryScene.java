package sengproject.gui.researcher;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import sengproject.gui.GuiController;
import sengproject.gui.LoginScene;
import sengproject.login.LoginHandler;

public class ResearcherHistoryScene {

    public static Scene getScene () {

        // pane text
        Label scene_title = new Label("View Submission History");
        scene_title.setFont(new Font("Arial", 24));
        VBox title = new VBox(scene_title);
        title.setAlignment(Pos.TOP_CENTER);
        title.setPadding(new Insets(0, 50, 10, 50));
        title.setSpacing(10);

        TableView history_tv = new TableView();

        // return button
        Button return_button = new Button("Back to Menu");
        return_button.setAlignment(Pos.BOTTOM_CENTER);
        return_button.setDefaultButton(true);
        return_button.setOnAction(action ->{

            GuiController.changeScene(ResearcherMenuScene.getScene());

        });

        VBox elements = new VBox();
        elements.setAlignment(Pos.CENTER);
        elements.setSpacing(30);
        elements.getChildren().addAll(title, history_tv, return_button);

        StackPane pane = new StackPane();
        pane.getChildren().add(elements);

        return new Scene(pane);

    }

}
