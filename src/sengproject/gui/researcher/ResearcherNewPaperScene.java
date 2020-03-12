package sengproject.gui.researcher;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import sengproject.gui.GuiController;

import java.io.File;

public class ResearcherNewPaperScene {

    public static Scene getScene () {

        // details box
        Label details_lb = new Label("Upload New Paper");
        details_lb.setFont(new Font("Arial", 24));

        VBox name_vb = new VBox();
        name_vb.getChildren().addAll(details_lb);
        name_vb.setAlignment(Pos.TOP_LEFT);

        // return button
        Button logout_b = new Button("Back");
        logout_b.setPrefSize(70, 40);
        logout_b.setOnAction(action -> {
            GuiController.changeScene(ResearcherMenuScene.getScene());
        });

        // top spacer
        Region top_spacer_r = new Region();
        HBox.setHgrow(top_spacer_r, Priority.ALWAYS);

        // top hbox
        HBox top_hb = new HBox();
        top_hb.setPadding(new Insets(10, 10, 10, 10));
        top_hb.getChildren().addAll(name_vb, top_spacer_r, logout_b);

        //  'Title'
        Label paper_title_lb = new Label("Title of paper");
        paper_title_lb.setFont(new Font("Arial", 20));

        TextField title_tf = new TextField();
        HBox title_hb = new HBox();
        title_hb.getChildren().addAll(paper_title_lb, title_tf);
        title_hb.setPadding(new Insets(10,0,20,0));
        title_hb.setSpacing(4);
        title_hb.setAlignment(Pos.CENTER);

        // 'Journal'
        Label journal_lb = new Label("Journal");
        journal_lb.setFont(new Font("Arial", 20));
        ComboBox<String> journal_cb = new ComboBox<String>();
        HBox journal_hb = new HBox();
        journal_hb.setSpacing(4);
        journal_hb.getChildren().addAll(journal_lb, journal_cb);

        // 'Volume'
        Label volume_lb = new Label("Volume");
        volume_lb.setFont(new Font("Arial", 20));
        ComboBox<String> volume_cb = new ComboBox<String>();
        HBox volume_hb = new HBox();
        volume_hb.setSpacing(4);
        volume_hb.getChildren().addAll(volume_lb, volume_cb);

        // journal/volume vbox
        HBox jv_hb = new HBox();
        jv_hb.getChildren().addAll(journal_hb, volume_hb);
        jv_hb.setSpacing(50);
        jv_hb.setPadding(new Insets(0,0,20,0));
        jv_hb.setAlignment(Pos.CENTER);

        // reviewer label and listview
        Label reviewer_lb = new Label("Preferred Reviewers");
        volume_lb.setFont(new Font("Arial", 20));

        ListView<String> reviewer_lv = new ListView<String>();

        VBox reviewer_vb = new VBox();
        reviewer_vb.getChildren().addAll(reviewer_lb, reviewer_lv);
        reviewer_vb.setPadding(new Insets(0,30,30,30));

        // select file
        FileChooser file_chooser = new FileChooser();

        Button file_b = new Button("Select File");
        file_b.setPrefSize(100, 35);
        file_b.setOnAction(action -> {
            File selected_file = file_chooser.showOpenDialog(GuiController.getStage());
        });

        HBox file_hb = new HBox();
        file_hb.setAlignment(Pos.CENTER);
        file_hb.getChildren().addAll(file_b);

        // center vbox
        VBox center_vb = new VBox();
        center_vb.getChildren().addAll(title_hb, jv_hb, reviewer_vb, file_hb);
        center_vb.setAlignment(Pos.CENTER);

        // submit button
        Button submit_b = new Button("Submit");
        submit_b.setPrefSize(150, 40);
        submit_b.setOnAction(action -> {
            //todo: implament submit button
        });

        // bottom spacer
        Region bottom_spacer_r = new Region();
        HBox.setHgrow(bottom_spacer_r, Priority.ALWAYS);

        // bottom hbox
        HBox bottom_hb = new HBox();
        bottom_hb.setPadding(new Insets(10, 50, 10, 50));
        bottom_hb.getChildren().addAll(bottom_spacer_r, submit_b);

        BorderPane main_pane = new BorderPane();

        main_pane.setTop(top_hb);
        main_pane.setCenter(center_vb);
        main_pane.setBottom(bottom_hb);

        return new Scene(main_pane);

    }

}
