package sengproject.gui.researcher;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import sengproject.gui.GuiController;
import sengproject.gui.researcher.tvobjects.ResearcherPaper;

public class ResearcherDetailsScene {

    public static Scene getScene (ResearcherPaper paper) {

        // details box
        Label details_lb = new Label("Details");
        details_lb.setFont(new Font("Arial", 24));

        VBox name_vb = new VBox();
        name_vb.getChildren().addAll(details_lb);
        name_vb.setAlignment(Pos.TOP_LEFT);

        // return button
        Button logout_b = new Button("Back");
        logout_b.setPrefSize(70,40);
        logout_b.setOnAction(action ->{
            GuiController.changeScene(ResearcherMenuScene.getScene());
        });

        // top spacer
        Region top_spacer_r = new Region();
        HBox.setHgrow(top_spacer_r, Priority.ALWAYS);

        // top hbox
        HBox top_hb = new HBox();
        top_hb.setPadding(new Insets(10,10,10,10));
        top_hb.getChildren().addAll(name_vb, top_spacer_r, logout_b);

        // upload new version button
        Button new_version_b = new Button("Upload New Version");
        new_version_b.setPrefSize(150,40);
        new_version_b.setOnAction(action ->{
            // todo: call upload new version
        });

        // browser reviewers button
        Button download_b = new Button("Download");
        download_b.setPrefSize(150,40);
        download_b.setOnAction(action ->{
            // todo: call download
        });

        // bottom spacer
        Region bottom_spacer_r = new Region();
        HBox.setHgrow(bottom_spacer_r, Priority.ALWAYS);

        // bottom hbox
        HBox bottom_hb = new HBox();
        bottom_hb.setPadding(new Insets(10,50,10,50));
        bottom_hb.getChildren().addAll(new_version_b, bottom_spacer_r, download_b);

        //  'Title of Paper' label
        Label paper_title_lb = new Label(paper.getTitle()); //todo
        paper_title_lb.setFont(new Font("Arial", 20));

        // 'Paper ID' label
        Label paper_id_lb = new Label(paper.getPaper_id()); //todo
        paper_id_lb.setFont(new Font("Arial", 16));

        // 'Submission Date: ' label
        Label submission_date_lb = new Label(paper.getSub_date()); //todo
        submission_date_lb.setFont(new Font("Arial", 16));

        // paper info vbox
        VBox paper_info_vb = new VBox();
        paper_info_vb.getChildren().addAll(paper_title_lb, paper_id_lb, submission_date_lb);
        paper_info_vb.setPadding(new Insets(0,0,20,0));


        // 'Author Name' label
        Label author_name_lb = new Label(paper.getAuthor_name()); //todo
        author_name_lb.setFont(new Font("Arial", 16));

        // 'Author ID' label
        Label author_id_lb = new Label(paper.getAuthor_id()); //todo
        author_id_lb.setFont(new Font("Arial", 16));

        // author info vbox
        VBox author_info_vb = new VBox();
        author_info_vb.getChildren().addAll(author_name_lb, author_id_lb);
        author_info_vb.setPadding(new Insets(0,0,20,0));

        // 'Journal Name' label
        Label journal_name_lb = new Label(paper.getJournal_name()); //todo
        journal_name_lb.setFont(new Font("Arial", 16));

        // 'Journal ID' label
        Label journal_id_lb = new Label(paper.getJournal_id()); //todo
        journal_id_lb.setFont(new Font("Arial", 16));

        // 'Volume ID' label
        Label volume_id_lb = new Label(paper.getVolume_id()); //todo
        volume_id_lb.setFont(new Font("Arial", 16));

        // journal_hbox
        HBox journal_hb = new HBox();
        journal_hb.getChildren().addAll(journal_id_lb, volume_id_lb);
        journal_hb.setSpacing(80);

        // journal info vbox
        VBox journal_info_vb = new VBox();
        journal_info_vb.getChildren().addAll(journal_name_lb, journal_hb);
        journal_info_vb.setPadding(new Insets(0,0,20,0));

        // 'File name of latest submission' label
        Label latest_submission_lb = new Label(paper.getFile_name()); //todo
        latest_submission_lb.setFont(new Font("Arial", 16));

        // 'Date of latest submission' label
        Label latest_date_lb = new Label(paper.getLatest_date()); //todo
        latest_date_lb.setFont(new Font("Arial", 16));

        // submission info vbox
        VBox submission_info_vb = new VBox();
        submission_info_vb.getChildren().addAll(latest_submission_lb, latest_date_lb);
        submission_info_vb.setPadding(new Insets(0,0,20,0));

        // 'Deadline: ' label
        Label deadline_lb = new Label("Deadline: " + paper.getDeadline()); //todo
        deadline_lb.setFont(new Font("Arial", 16));

        // 'Reviewers: ' label
        Label reviewers_lb = new Label("Reviewers: " + paper.getReviewers()); //todo
        reviewers_lb.setFont(new Font("Arial", 16));

        // deadline info hbox
        HBox deadline_info_hb = new HBox();
        deadline_info_hb.getChildren().addAll(deadline_lb, reviewers_lb);
        deadline_info_hb.setSpacing(80);
        deadline_info_hb.setPadding(new Insets(0,0,20,0));

        // 'status' label
        String status = paper.getStatus();

        Label status_lb = new Label("[" + status + "]"); //todo
        status_lb.setFont(new Font("Arial", 16));

        switch (status) {

            case "accepted":
                status_lb.setTextFill(Color.GREEN);
                break;

            case "pending":
                status_lb.setTextFill(Color.ORANGE);
                break;

            case "rejected":
                status_lb.setTextFill(Color.RED);
                break;
        }

        // center vbox
        VBox center_vb = new VBox();
        center_vb.setPadding(new Insets(10,10,10,10));
        center_vb.getChildren().addAll(paper_info_vb, author_info_vb, journal_info_vb, submission_info_vb, deadline_info_hb, status_lb);
        center_vb.setStyle("-fx-padding: 5;" + "-fx-border-style: solid inside;"
                + "-fx-border-width: 3;" + "-fx-border-insets: 5;"
                + "-fx-border-radius: 5;" + "-fx-border-color: black;");

        BorderPane main_pane = new BorderPane();

        main_pane.setTop(top_hb);
        main_pane.setCenter(center_vb);
        main_pane.setBottom(bottom_hb);


        return new Scene(main_pane);

    }

}
