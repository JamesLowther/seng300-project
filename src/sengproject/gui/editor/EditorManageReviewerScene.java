package sengproject.gui.editor;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import sengproject.gui.GuiController;
import sengproject.gui.editor.tvobjects.EditorReviewer;
import sengproject.researcher.ReviewerFunctions;

public class EditorManageReviewerScene {

    public static Scene getScene(EditorReviewer reviewer) {

        // reviewer name label
        Label name_lb = new Label(reviewer.getName());
        name_lb.setFont(new Font("Arial", 24));

        // top hbox
        HBox top_hb = new HBox();
        top_hb.setPadding(new Insets(20,30,30,30));
        top_hb.getChildren().addAll(name_lb);

        // deadline label
        Label deadline_lb = new Label("Review Deadline:");
        name_lb.setFont(new Font("Arial", 20));

        // deadline textfield
        TextField deadline_tf = new TextField();
        deadline_tf.setPromptText("YYYY-MM-DD");

        // deadline hbox
        HBox deadline_hb = new HBox();
        deadline_hb.setAlignment(Pos.CENTER);
        deadline_hb.setSpacing(10);
        deadline_hb.getChildren().addAll(deadline_lb, deadline_tf);

        // remove button
        Button remove_b = new Button("Remove");
        remove_b.setPrefSize(150,40);
        remove_b.setOnAction(action ->{
            ReviewerFunctions.removeReviewer(reviewer.getPid(), (String) reviewer.getReviewer().get("uid"));
            GuiController.changeScene(EditorMenuScene.getScene());
        });

        // center vbox
        VBox center_vb = new VBox();
        center_vb.setAlignment(Pos.CENTER);
        center_vb.setSpacing(40);
        center_vb.getChildren().addAll(deadline_hb, remove_b);

        // apply button
        Button apply_b = new Button("Apply");
        apply_b.setPrefSize(150,40);
        apply_b.setOnAction(action ->{
            // todo: apply the changes
            GuiController.changeScene(EditorMenuScene.getScene());
        });

        // cancel button
        Button cancel_b = new Button("Cancel");
        cancel_b.setPrefSize(150,40);
        cancel_b.setOnAction(action ->{
            GuiController.changeScene(EditorMenuScene.getScene());
        });

        // bottom spacer
        Region bottom_spacer_r = new Region();
        HBox.setHgrow(bottom_spacer_r, Priority.ALWAYS);

        // bottom hbox
        HBox bottom_hb = new HBox();
        bottom_hb.setPadding(new Insets(10,50,20,50));
        bottom_hb.getChildren().addAll(apply_b, bottom_spacer_r, cancel_b);

        BorderPane main_pane = new BorderPane();

        main_pane.setTop(top_hb);
        main_pane.setCenter(center_vb);
        main_pane.setBottom(bottom_hb);

        return new Scene(main_pane);

    }

}
