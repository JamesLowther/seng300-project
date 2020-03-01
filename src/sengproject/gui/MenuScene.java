package sengproject.gui;

import org.json.simple.JSONObject;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import sengproject.gui.LoginScene;
import sengproject.jsonparsing.JSONUserParser;
import sengproject.Globals;

public class MenuScene {

	public static Scene getScene () {
		
		// pane text
		Label scene_title = new Label("Welcome back " + (String) Globals.getUser().get("username") + "!");
		scene_title.setFont(new Font("Arial", 30));
		VBox title = new VBox(scene_title);
		title.setAlignment(Pos.TOP_CENTER);
		title.setPadding(new Insets(100, 50, 50, 50));
		title.setSpacing(10);
		
		// return button
		Button return_button = new Button("Return to login");
		return_button.setAlignment(Pos.CENTER);
		return_button.setDefaultButton(true);
		return_button.setOnAction(action ->{

			GuiController.changeScene(LoginScene.getScene());
			
		});
		
		StackPane pane = new StackPane();
		pane.getChildren().addAll(title, return_button);
		
		return new Scene(pane);
		
	}
	
}
