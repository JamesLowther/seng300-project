package sengproject.gui.editor;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import sengproject.Globals;
import sengproject.gui.GuiController;
import sengproject.gui.LoginScene;
import sengproject.login.LoginHandler;

public class EditorMenuScene {

    public static Scene getScene() {

        // pane text
        Label scene_title = new Label("Welcome back " + (String) Globals.getUser().get("username") + "!");
        scene_title.setFont(new Font("Arial", 30));
        VBox title = new VBox(scene_title);
        title.setAlignment(Pos.TOP_CENTER);
        title.setPadding(new Insets(0, 50, 10, 50));
        title.setSpacing(10);

        // pane text
        Label todo_text = new Label("//todo: editor menu");
        todo_text.setFont(new Font("Arial", 15));

        // return button
        Button return_button = new Button("Logout");
        return_button.setAlignment(Pos.BOTTOM_CENTER);
        return_button.setDefaultButton(true);
        return_button.setOnAction(action -> {

            LoginHandler.logout();
            GuiController.changeScene(LoginScene.getScene());

        });

        //vertical container
        VBox elements = new VBox();
        elements.getChildren().addAll(title, todo_text, return_button);
        elements.setSpacing(70);
        elements.setAlignment(Pos.CENTER);

        StackPane pane = new StackPane();
        pane.getChildren().add(elements);

        return new Scene(pane);

    }

}
