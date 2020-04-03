package sengproject.gui.reviewer;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import sengproject.gui.GuiController;
import sengproject.gui.researcher.ResearcherMenuScene;
import sengproject.gui.researcher.tvobjects.ResearcherPaper;
import sengproject.gui.researcher.tvobjects.ResearcherReviewer;
import sengproject.gui.reviewer.tvobjects.ReviewerPaper;

import java.util.ArrayList;

public class ReviewerBrowsePapersScene {

    public static Scene getScene () {

        // details box
        Label details_lb = new Label("Browse Papers");
        details_lb.setFont(new Font("Arial", 24));

        VBox name_vb = new VBox();
        name_vb.getChildren().addAll(details_lb);
        name_vb.setAlignment(Pos.TOP_LEFT);

        // return button
        Button logout_b = new Button("Back");
        logout_b.setPrefSize(70, 40);
        logout_b.setOnAction(action -> {
            GuiController.changeScene(ReviewerMenuScene.getScene());
        });

        // top spacer
        Region top_spacer_r = new Region();
        HBox.setHgrow(top_spacer_r, Priority.ALWAYS);

        // top hbox
        HBox top_hb = new HBox();
        top_hb.setPadding(new Insets(10, 10, 10, 10));
        top_hb.getChildren().addAll(name_vb, top_spacer_r, logout_b);

        // available papers
        TableView<ReviewerPaper> available_papers_tv = new TableView<ReviewerPaper>();
        {

            TableColumn<ReviewerPaper, String> title_column = new TableColumn<ReviewerPaper, String>("Title");
            title_column.setCellValueFactory(new PropertyValueFactory<ReviewerPaper, String>("title"));

            TableColumn<ReviewerPaper, Button> details_column = new TableColumn<ReviewerPaper, Button>("");
            details_column.setStyle("-fx-alignment: CENTER-RIGHT;");
            details_column.setCellValueFactory(new PropertyValueFactory<ReviewerPaper, Button>("details_b"));
            details_column.setSortable(false);

            TableColumn<ReviewerPaper, CheckBox> star_column = new TableColumn<ReviewerPaper, CheckBox>("Preferred?");
            star_column.setStyle("-fx-alignment: CENTER-RIGHT;");
            star_column.setCellValueFactory(new PropertyValueFactory<ReviewerPaper, CheckBox>("star_cb"));
            star_column.setSortable(false);

            available_papers_tv.getColumns().addAll(title_column, details_column, star_column);
        }

        // add reviewers to table views
        ArrayList<ReviewerPaper> papers_array = getReviewerPapers();
        available_papers_tv.getItems().addAll(papers_array);


        // center vbox
        VBox center_vb = new VBox();
        center_vb.setPadding(new Insets(10,10,10,10));
        center_vb.getChildren().addAll(available_papers_tv);


        BorderPane main_pane = new BorderPane();

        main_pane.setTop(top_hb);
        main_pane.setCenter(center_vb);

        return new Scene(main_pane);

    }

    private static ArrayList<ReviewerPaper> getReviewerPapers () {

        //todo: this will all be replaced
        ArrayList<ReviewerPaper> papers = new ArrayList<ReviewerPaper>();

        papers.add(new ReviewerPaper("Test paper 1", "21232", "01/01/2020", "John Doe",
                "12319", "Journal of Smart", "123", "1", "1231",
                "test_2121.pdf", "02/02/2020", "03/03/2020", "3",
                "23", "accepted", "browse"));

        papers.add(new ReviewerPaper("Test paper 2", "21232", "01/01/2020", "John Doe",
                "12319", "Journal of Smart", "1", "123", "1231",
                "test_2121.pdf", "02/02/2020", "03/03/2020", "3",
                "23", "accepted", "browse"));

        papers.add(new ReviewerPaper("Test paper 3", "21232", "01/01/2020", "John Doe",
                "12319", "Journal of Smart", "123", "1", "1231",
                "test_2121.pdf", "02/02/2020", "03/03/2020", "3",
                "23", "accepted", "browse"));

        papers.add(new ReviewerPaper("Test paper 4", "21232", "01/01/2020", "John Doe",
                "12319", "Journal of Smart", "123", "1", "1231",
                "test_2121.pdf", "02/02/2020", "03/03/2020", "3",
                "23", "accepted", "browse"));

        return papers;

    }

}
