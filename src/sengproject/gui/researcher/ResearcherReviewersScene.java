package sengproject.gui.researcher;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import sengproject.gui.GuiController;
import sengproject.gui.researcher.tvobjects.ResearcherPaper;
import sengproject.gui.researcher.tvobjects.ResearcherReviewer;

import java.util.ArrayList;

public class ResearcherReviewersScene {

    public static Scene getScene (ResearcherPaper r_paper) {

        // details box
        Label details_lb = new Label("Reviewers");
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

        // current reviewers
        TableView<ResearcherReviewer> current_rev_tv = new TableView<ResearcherReviewer>();
        {
            TableColumn<ResearcherReviewer, String> name_column = new TableColumn<ResearcherReviewer, String>("Name");
            name_column.setCellValueFactory(new PropertyValueFactory<ResearcherReviewer, String>("name"));

            TableColumn<ResearcherReviewer, String> major_rev_column = new TableColumn<ResearcherReviewer, String>("Major revisions");
            major_rev_column.setStyle("-fx-alignment: CENTER-RIGHT;");
            major_rev_column.setCellValueFactory(new PropertyValueFactory<ResearcherReviewer, String>("num_major_rev"));

            TableColumn<ResearcherReviewer, String> minor_rev_column = new TableColumn<ResearcherReviewer, String>("Minor revisions");
            minor_rev_column.setStyle("-fx-alignment: CENTER-RIGHT;");
            minor_rev_column.setCellValueFactory(new PropertyValueFactory<ResearcherReviewer, String>("num_minor_rev"));

            TableColumn<ResearcherReviewer, String> publications_column = new TableColumn<ResearcherReviewer, String>("Total # of publications viewed");
            publications_column.setStyle("-fx-alignment: CENTER-RIGHT;");
            publications_column.setCellValueFactory(new PropertyValueFactory<ResearcherReviewer, String>("num_pub_rev"));

            TableColumn<ResearcherReviewer, String> average_column = new TableColumn<ResearcherReviewer, String>("Average review time (days)");
            average_column.setStyle("-fx-alignment: CENTER-RIGHT;");
            average_column.setCellValueFactory(new PropertyValueFactory<ResearcherReviewer, String>("avg_rev_time"));

            current_rev_tv.getColumns().addAll(name_column, major_rev_column, minor_rev_column, publications_column, average_column);
        }

        // preferred reviewers
        TableView<ResearcherReviewer> preferred_rev_tv = new TableView<ResearcherReviewer>();
        {
            TableColumn<ResearcherReviewer, String> name_column = new TableColumn<ResearcherReviewer, String>("Name");
            name_column.setCellValueFactory(new PropertyValueFactory<ResearcherReviewer, String>("name"));

            TableColumn<ResearcherReviewer, String> publications_column = new TableColumn<ResearcherReviewer, String>("Total # of publications viewed");
            publications_column.setStyle("-fx-alignment: CENTER-RIGHT;");
            publications_column.setCellValueFactory(new PropertyValueFactory<ResearcherReviewer, String>("num_pub_rev"));

            TableColumn<ResearcherReviewer, String> average_column = new TableColumn<ResearcherReviewer, String>("Average review time (days)");
            average_column.setStyle("-fx-alignment: CENTER-RIGHT;");
            average_column.setCellValueFactory(new PropertyValueFactory<ResearcherReviewer, String>("avg_rev_time"));

            preferred_rev_tv.getColumns().addAll(name_column, publications_column, average_column);
        }

        // current reviewers
        TableView<ResearcherReviewer> interested_rev_tv = new TableView<ResearcherReviewer>();
        {

            TableColumn<ResearcherReviewer, String> name_column = new TableColumn<ResearcherReviewer, String>("Name");
            name_column.setCellValueFactory(new PropertyValueFactory<ResearcherReviewer, String>("name"));

            TableColumn<ResearcherReviewer, String> publications_column = new TableColumn<ResearcherReviewer, String>("Total # of publications viewed");
            publications_column.setStyle("-fx-alignment: CENTER-RIGHT;");
            publications_column.setCellValueFactory(new PropertyValueFactory<ResearcherReviewer, String>("num_pub_rev"));

            TableColumn<ResearcherReviewer, String> average_column = new TableColumn<ResearcherReviewer, String>("Average review time (days)");
            average_column.setStyle("-fx-alignment: CENTER-RIGHT;");
            average_column.setCellValueFactory(new PropertyValueFactory<ResearcherReviewer, String>("avg_rev_time"));

            interested_rev_tv.getColumns().addAll(name_column, publications_column, average_column);
        }

        // add reviewers to table views
        ArrayList<ResearcherReviewer> reviewers_array = getResearcherReviewers(r_paper);
        current_rev_tv.getItems().addAll(reviewers_array);
        preferred_rev_tv.getItems().addAll(reviewers_array);
        interested_rev_tv.getItems().addAll(reviewers_array);

        // tab pane
        TabPane main_tp = new TabPane();
        main_tp.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
        Tab current_rev_t = new Tab("Current Reviewers", current_rev_tv);
        Tab preferred_rev_t = new Tab("Preferred Reviewers", preferred_rev_tv);
        Tab interested_rev_t = new Tab("Interested Reviewers", interested_rev_tv);
        main_tp.getTabs().addAll(current_rev_t, preferred_rev_t, interested_rev_t);

        // center vbox
        VBox center_vb = new VBox();
        center_vb.setPadding(new Insets(10,10,10,10));
        center_vb.getChildren().addAll(main_tp);


        BorderPane main_pane = new BorderPane();

        main_pane.setTop(top_hb);
        main_pane.setCenter(center_vb);

        return new Scene(main_pane);

    }

    private static ArrayList<ResearcherReviewer> getResearcherReviewers (ResearcherPaper paper) {
        // todo: get reviewers


        ArrayList<ResearcherReviewer> reviewers = new ArrayList<ResearcherReviewer>();

        // todo: this is just test data
        reviewers.add(new ResearcherReviewer("Bob Barker", 3, 2, 2, 12.5f));
        reviewers.add(new ResearcherReviewer("Adam Apple", 2, 1, 2, 5.3f));
        reviewers.add(new ResearcherReviewer("Jack Joe", 1, 0, 2, 30.1f));

        return reviewers;


    }

}
