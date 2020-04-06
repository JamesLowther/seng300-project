package sengproject.gui.reviewer;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import sengproject.gui.GuiController;
import sengproject.gui.reviewer.tvobjects.ReviewerPaper;
import sengproject.jsonparsing.JSONPaperParser;
import sengproject.shared.ActionFunctions;

public class ReviewerNewActionScene {

    public static Scene getScene (ReviewerPaper r_paper) {

        // radio buttons
        ToggleGroup radio_tg = new ToggleGroup();

        RadioButton minor_rb = new RadioButton("Minor Revision");
        minor_rb.setStyle("-fx-font-family: 'Arial';" + "-fx-font-size: 16;");
        minor_rb.setToggleGroup(radio_tg);
        minor_rb.setSelected(true);

        RadioButton major_rb = new RadioButton("Major Revision");
        major_rb.setStyle("-fx-font-family: 'Arial';" + "-fx-font-size: 16;");
        major_rb.setToggleGroup(radio_tg);

        // top spacer
        Region top_spacer_r = new Region();
        HBox.setHgrow(top_spacer_r, Priority.ALWAYS);

        // top hbox
        HBox top_hb = new HBox();
        top_hb.setPadding(new Insets(40,60,10,60));
        top_hb.getChildren().addAll(minor_rb, top_spacer_r, major_rb);

        // main text area
        TextArea details_ta = new TextArea();
        details_ta.setPromptText("Enter details here...");

        // center vbox
        VBox center_vb = new VBox();
        center_vb.setPadding(new Insets(10,10,10,10));
        center_vb.getChildren().addAll(details_ta);

        // submit button
        Button new_paper_b = new Button("Submit");
        new_paper_b.setPrefSize(150,40);
        new_paper_b.setOnAction(action ->{

            if (minor_rb.isSelected()) {
                ActionFunctions.addNewAction(r_paper.getPaper_id(), details_ta.getText(), "minor");
            } else {
                ActionFunctions.addNewAction(r_paper.getPaper_id(), details_ta.getText(), "major");
            }

            GuiController.changeScene(ReviewerActionsScene.getScene(new ReviewerPaper(JSONPaperParser.findPaper(r_paper.getPaper_id()), "menu", false)));
        });

        // cancel button
        Button browser_reviewers_b = new Button("Cancel");
        browser_reviewers_b.setPrefSize(150,40);
        browser_reviewers_b.setOnAction(action ->{
            GuiController.changeScene(ReviewerActionsScene.getScene(r_paper));
        });

        // bottom spacer
        Region bottom_spacer_r = new Region();
        HBox.setHgrow(bottom_spacer_r, Priority.ALWAYS);

        // bottom hbox
        HBox bottom_hb = new HBox();
        bottom_hb.setPadding(new Insets(10,50,75,50));
        bottom_hb.getChildren().addAll(new_paper_b, bottom_spacer_r, browser_reviewers_b);

        BorderPane main_pane = new BorderPane();

        main_pane.setTop(top_hb);
        main_pane.setCenter(center_vb);
        main_pane.setBottom(bottom_hb);


        return new Scene(main_pane);

    }

}
