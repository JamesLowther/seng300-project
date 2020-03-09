package sengproject.gui.researcher;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import sengproject.Globals;
import sengproject.gui.GuiController;
import sengproject.gui.LoginScene;
import sengproject.login.LoginHandler;

public class ResearcherMenuScene {

    public static Scene getScene () {

        // name box
        Label name_lb = new Label((String) Globals.getUser().get("username"));
        name_lb.setFont(new Font("Arial", 24));
        Label role_lb = new Label((String) Globals.getUser().get("role"));
        role_lb.setFont(new Font("Arial", 18));

        VBox name_vb = new VBox();
        name_vb.getChildren().addAll(name_lb, role_lb);
        name_vb.setAlignment(Pos.TOP_LEFT);

        // logout button
        Button logout_b = new Button("Logout");
        logout_b.setPrefSize(70,40);
        logout_b.setOnAction(action ->{
            LoginHandler.logout();
            GuiController.changeScene(LoginScene.getScene());
        });

        // top spacer
        Region top_spacer_r = new Region();
        HBox.setHgrow(top_spacer_r, Priority.ALWAYS);

        // top hbox
        HBox top_hb = new HBox();
        top_hb.setPadding(new Insets(10,10,10,10));
        top_hb.getChildren().addAll(name_vb, top_spacer_r, logout_b);

        // new paper button
        Button new_paper_b = new Button("Upload New Paper");
        new_paper_b.setPrefSize(150,40);
        new_paper_b.setOnAction(action ->{
            // todo: call upload new paper scene
        });

        // browser reviewers button
        Button browser_reviewers_b = new Button("Browse Reviewers");
        browser_reviewers_b.setPrefSize(150,40);
        browser_reviewers_b.setOnAction(action ->{
            // todo: call browse reviewer scene
        });

        // bottom spacer
        Region bottom_spacer_r = new Region();
        HBox.setHgrow(bottom_spacer_r, Priority.ALWAYS);

        // bottom hbox
        HBox bottom_hb = new HBox();
        bottom_hb.setPadding(new Insets(10,50,10,50));
        bottom_hb.getChildren().addAll(new_paper_b, bottom_spacer_r, browser_reviewers_b);

        //  'your papers' label
        Label center_lb = new Label("Your papers");
        center_lb.setFont(new Font("Arial", 16));

        // table view
        TableView papers_tv = new TableView();

        // center vbox
        VBox center_vb = new VBox();
        center_vb.setPadding(new Insets(10,10,10,10));
        center_vb.getChildren().addAll(center_lb, papers_tv);


        BorderPane main_pane = new BorderPane();

        main_pane.setTop(top_hb);
        main_pane.setCenter(center_vb);
        main_pane.setBottom(bottom_hb);


        return new Scene(main_pane);


    }


}
