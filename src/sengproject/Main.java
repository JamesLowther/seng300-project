package sengproject;

import javafx.application.Application;

import sengproject.gui.GuiController;


public class Main {

	public static void main(String[] args) {
		
		System.out.println("Intilializing SENG 300 Project");
		
		// Launch application GUI
		Application.launch(GuiController.class, args);

	}

}
