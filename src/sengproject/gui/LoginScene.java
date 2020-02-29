package sengproject.gui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

import sengproject.login.LoginHandler;
import sengproject.gui.RegistrationScene;

public class LoginScene {

	public static Scene getScene () {
		
		// login screen title
		Label login_title = new Label("Login");
		login_title.setFont(new Font("Arial", 30));
		VBox title = new VBox(login_title);
		title.setAlignment(Pos.TOP_CENTER);
		title.setPadding(new Insets(75, 50, 50, 50));
		title.setSpacing(40);
		
		// username section
		Label user_label = new Label("Username");
		TextField user_tf = new TextField();
		HBox username = new HBox(user_label, user_tf);
		username.setSpacing(2);
		username.setAlignment(Pos.CENTER);
		
		// password section
		Label pass_label = new Label("Password");
		PasswordField pw_tf = new PasswordField();
		HBox password = new HBox(pass_label, pw_tf);
		password.setSpacing(4);
		password.setAlignment(Pos.CENTER);
		
		// login message
		Label login_message = new Label("");
		login_message.setTextAlignment(TextAlignment.CENTER);
		
		// login button
		Button login_button = new Button("Login");
		login_button.setDefaultButton(true);
		login_button.setOnAction(action -> {
			
			if (!LoginHandler.login(user_tf.getText(), pw_tf.getText())) {
				login_message.setText("Incorrect username or password");
			};
		});
		
		// register button
		Button register_button = new Button("Register new user");
		register_button.setOnAction(action -> {
			
			GuiController.changeScene(RegistrationScene.getScene());
			
		});
		
		VBox login_ui = new VBox(username, password, login_button, login_message, register_button);
		login_ui.setSpacing(8);
		login_ui.setAlignment(Pos.CENTER);
		
		title.getChildren().add(login_ui);
		
		StackPane pane = new StackPane();
		pane.getChildren().add(title);
		
		return new Scene(pane);
	}
	
}

