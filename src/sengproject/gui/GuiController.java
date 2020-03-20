package sengproject.gui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import sengproject.gui.LoginScene;

public class GuiController extends Application {

	private static double app_width = 650;
	private static double app_height = 550;
	private static String app_title = "Journal Submission System";
	
	private static Stage s = null;
	
	@Override
	public void start(Stage stage) throws Exception {

		s = stage;
		s.setWidth(app_width);
		s.setHeight(app_height);
		s.setTitle(app_title);

		changeScene(LoginScene.getScene());
		
		s.show();
		
	}
	
	public static void changeScene (Scene scene) {
		
		app_width = s.getWidth();
		app_height = s.getHeight();
		
		s.setScene(scene);
		s.setWidth(app_width);
		s.setHeight(app_height);
	
	}

	public static Stage getStage () {
		return s;
	}

}

