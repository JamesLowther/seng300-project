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

import sengproject.login.RegistrationHandler;
import sengproject.gui.GuiController;
import sengproject.gui.LoginScene;

public class RegistrationScene {

	public static Scene getScene() {
		
		// login screen title
		Label register_title = new Label("User Registration");
		register_title.setFont(new Font("Arial", 30));
		VBox title = new VBox(register_title);
		title.setAlignment(Pos.CENTER);
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
		
		// role section
		Label role_label = new Label("User Role");
		TextField role_tf = new TextField();
		HBox role = new HBox(role_label, role_tf);
		role.setSpacing(4);
		role.setAlignment(Pos.CENTER);
		
		// register message
		Label register_message = new Label("");
		register_message.setTextAlignment(TextAlignment.CENTER);
		
		// register button
		Button register_button = new Button("Register");
		register_button.setDefaultButton(true);
		register_button.setOnAction(action -> {
			
			if (!RegistrationHandler.register(user_tf.getText(), pw_tf.getText(), role_tf.getText())) {
				register_message.setText("Error registering user");
			};
		});
		
		// return button
		Button return_button = new Button("Return to login");
		return_button.setOnAction(action -> {
			
			GuiController.changeScene(LoginScene.getScene());
			
		});
		
		VBox login_ui = new VBox(username, password, role, register_button, register_message, return_button);
		login_ui.setSpacing(8);
		login_ui.setAlignment(Pos.CENTER);
		
		title.getChildren().add(login_ui);
		
		StackPane pane = new StackPane();
		pane.getChildren().add(title);
		
		return new Scene(pane);
		
	}
	
}
