package sengproject.gui.researcher;

import javafx.scene.control.Button;
import org.json.simple.JSONObject;
import sengproject.gui.GuiController;

public class ResearcherPaper {

    private String title = null;
    private Button details_b = null;
    private Button reviewers_b = null;
    private Button actions_b = null;

    private JSONObject paper_object;

    public ResearcherPaper(String t) {

        title = t;

        details_b = new Button("Details");
        details_b.setOnAction(action ->{
            GuiController.changeScene(ResearcherDetailsScene.getScene(this));
        });

        reviewers_b = new Button("Reviewers");
        reviewers_b.setOnAction(action ->{
            // todo: call reviewer scene
        });

        actions_b = new Button("Actions");
        actions_b.setOnAction(saction ->{
            // todo: call action scene
        });

        System.out.println("paper created with title: " + title);

    }

    public void setTitle (String t) {
        title = t;
    }

    public String getTitle () {
        return title;
    }

    public void setDetails_b (Button b) {
        details_b = b;
    }

    public Button getDetails_b () {
        return details_b;
    }

    public void setReviewers_b (Button b) {
        reviewers_b = b;
    }

    public Button getReviewers_b () {
        return reviewers_b;
    }

    public void setActions_b (Button b) {
        actions_b = b;
    }

    public Button getActions_b () {
        return actions_b;
    }

}
