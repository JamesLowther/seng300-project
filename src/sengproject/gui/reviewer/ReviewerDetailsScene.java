package sengproject.gui.reviewer;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.DirectoryChooser;
import sengproject.gui.GuiController;
import sengproject.gui.reviewer.tvobjects.ReviewerPaper;
import sengproject.shared.PaperFunctions;

import java.io.File;

public class ReviewerDetailsScene {

    public static Scene getScene (ReviewerPaper r_paper) {

        // details box
        Label details_lb = new Label("Details");
        details_lb.setFont(new Font("Arial", 24));

        VBox name_vb = new VBox();
        name_vb.getChildren().addAll(details_lb);
        name_vb.setAlignment(Pos.TOP_LEFT);

        // return button
        Button back_b = new Button("Back");
        back_b.setPrefSize(70,40);
        back_b.setOnAction(action ->{
            if (r_paper.getPrevious_menu().equals("menu")) {
                GuiController.changeScene(ReviewerMenuScene.getScene());
            }
            else if (r_paper.getPrevious_menu().equals("browse")) {
                GuiController.changeScene(ReviewerBrowsePapersScene.getScene());
            }
        });

        // top spacer
        Region top_spacer_r = new Region();
        HBox.setHgrow(top_spacer_r, Priority.ALWAYS);

        // top hbox
        HBox top_hb = new HBox();
        top_hb.setPadding(new Insets(10,10,10,10));
        top_hb.getChildren().addAll(name_vb, top_spacer_r, back_b);

        // error label
        Label error_lb = new Label ("");
        error_lb.setFont(new Font("Arial", 16));

        // download button
        DirectoryChooser dir_chooser = new DirectoryChooser();

        Button download_b = new Button("Download");
        download_b.setPrefSize(150,40);
        download_b.setOnAction(action ->{
            File selected_path = dir_chooser.showDialog(GuiController.getStage());
            if (PaperFunctions.downloadFile(r_paper.getPaper_id(), selected_path)) {
                error_lb.setText("");
            } else {
                error_lb.setText("Error downloading file");
            }
        });

        // bottom spacer
        Region bottom_spacer1_r = new Region();
        HBox.setHgrow(bottom_spacer1_r, Priority.ALWAYS);

        Region bottom_spacer2_r = new Region();
        HBox.setHgrow(bottom_spacer2_r, Priority.ALWAYS);

        // bottom hbox
        HBox bottom_hb = new HBox();
        bottom_hb.setPadding(new Insets(10,50,10,50));
        bottom_hb.getChildren().addAll(bottom_spacer1_r, error_lb, bottom_spacer2_r, download_b);

        //  'Title of Paper' label
        Label paper_title_lb = new Label(r_paper.getTitle());
        paper_title_lb.setFont(new Font("Arial", 20));

        // 'Paper ID' label
        Label paper_id_lb = new Label("Paper ID: " + r_paper.getPaper_id());
        paper_id_lb.setFont(new Font("Arial", 16));

        // 'Submission Date: ' label
        Label submission_date_lb = new Label("Submission date: " + r_paper.getSub_date());
        submission_date_lb.setFont(new Font("Arial", 16));

        // paper info vbox
        VBox paper_info_vb = new VBox();
        paper_info_vb.getChildren().addAll(paper_title_lb, paper_id_lb, submission_date_lb);
        paper_info_vb.setPadding(new Insets(0,0,20,0));


        // 'Author Name' label
        Label author_name_lb = new Label("Author: " + r_paper.getAuthor_name());
        author_name_lb.setFont(new Font("Arial", 16));

        // 'Author ID' label
        Label author_id_lb = new Label("Author ID: " + r_paper.getAuthor_id());
        author_id_lb.setFont(new Font("Arial", 16));

        // author info vbox
        VBox author_info_vb = new VBox();
        author_info_vb.getChildren().addAll(author_name_lb, author_id_lb);
        author_info_vb.setPadding(new Insets(0,0,20,0));

        // 'Journal Name' label
        Label journal_name_lb = new Label("Journal: " + r_paper.getJournal_name());
        journal_name_lb.setFont(new Font("Arial", 16));

        // 'Journal ID' label
        Label journal_id_lb = new Label("Journal ID: " + r_paper.getJournal_id());
        journal_id_lb.setFont(new Font("Arial", 16));

        // journal vbox
        VBox journal_vb = new VBox();
        journal_vb.getChildren().addAll(journal_name_lb, journal_id_lb);

        // 'Volume Name' label
        Label volume_name_lb = new Label("Volume: " + r_paper.getVolume_name());
        volume_name_lb.setFont(new Font("Arial", 16));

        // 'Volume ID' label
        Label volume_id_lb = new Label("Volume ID: " + r_paper.getVolume_id());
        volume_id_lb.setFont(new Font("Arial", 16));

        // volume vbox
        VBox volume_vb = new VBox();
        volume_vb.getChildren().addAll(volume_name_lb, volume_id_lb);

        // journal_hbox
        HBox journal_hb = new HBox();
        journal_hb.getChildren().addAll(journal_vb, volume_vb);
        journal_hb.setSpacing(80);
        journal_hb.setPadding(new Insets(0,0,20,0));

        // 'File name of latest submission' label
        Label latest_submission_lb = new Label(r_paper.getFile_name());
        latest_submission_lb.setFont(new Font("Arial", 16));

        // 'Date of latest submission' label
        Label latest_date_lb = new Label("Latest Submission: " + r_paper.getLatest_date());
        latest_date_lb.setFont(new Font("Arial", 16));

        // submission info vbox
        VBox submission_info_vb = new VBox();
        submission_info_vb.getChildren().addAll(latest_submission_lb, latest_date_lb);
        submission_info_vb.setPadding(new Insets(0,0,20,0));

        // 'Deadline: ' label
        Label deadline_lb = new Label("Deadline: " + r_paper.getDeadline());
        deadline_lb.setFont(new Font("Arial", 16));

        // 'Reviewers: ' label
        Label reviewers_lb = new Label("Reviewers: " + r_paper.getReviewers());
        reviewers_lb.setFont(new Font("Arial", 16));

        // 'Revisions: ' label
        Label revisions_lb = new Label("Revisions: " + r_paper.getRevisions());
        revisions_lb.setFont(new Font("Arial", 16));

        // reviewer/revisions vbox
        VBox rev_revis_vb = new VBox();
        rev_revis_vb.getChildren().addAll(reviewers_lb, revisions_lb);

        // deadline info hbox
        HBox deadline_info_hb = new HBox();
        deadline_info_hb.getChildren().addAll(deadline_lb, rev_revis_vb);
        deadline_info_hb.setSpacing(80);
        deadline_info_hb.setPadding(new Insets(0,0,20,0));

        // 'status' label
        String status = r_paper.getStatus();

        Label status_lb = new Label("[" + status + "]");
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
        center_vb.getChildren().addAll(paper_info_vb, author_info_vb, journal_hb, submission_info_vb, deadline_info_hb, status_lb);
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
