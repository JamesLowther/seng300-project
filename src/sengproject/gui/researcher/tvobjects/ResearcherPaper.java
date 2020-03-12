package sengproject.gui.researcher.tvobjects;

import javafx.scene.control.Button;
import org.json.simple.JSONObject;
import sengproject.gui.GuiController;
import sengproject.gui.researcher.ResearcherDetailsScene;
import sengproject.gui.researcher.ResearcherReviewersScene;

public class ResearcherPaper {

    private JSONObject paper;

    private Button details_b;
    private Button reviewers_b;
    private Button actions_b;

    private String title;
    private String paper_id;
    private String sub_date;
    private String author_name;
    private String author_id;
    private String journal_name;
    private String journal_id;
    private String volume_id;
    private String file_name;
    private String latest_date;
    private String deadline;
    private String reviewers;
    private String status;

    // todo: replace string with JSONObject
    public ResearcherPaper(String t) {

        title = t;

        //todo: this is all just test data
        // it will need to be populated with data from the json paper object
        paper_id = "paper id";
        sub_date = "sub_date";
        author_name = "author_name";
        author_id = "author_id";
        journal_name = "journal_name";
        journal_id = "journal_id";
        volume_id = "volume_id";
        file_name = "file_name";
        latest_date = "latest_date";
        deadline = "deadline";
        reviewers = "reviewers";
        status = "rejected";

        details_b = new Button("Details");
        details_b.setOnAction(action ->{
            GuiController.changeScene(ResearcherDetailsScene.getScene(this));
        });

        reviewers_b = new Button("Reviewers");
        reviewers_b.setOnAction(action ->{
            GuiController.changeScene(ResearcherReviewersScene.getScene(this));
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

    public String getPaper_id () {return paper_id;}

    public String getSub_date () {return sub_date;}

    public String getAuthor_name () {return author_name;}

    public String getAuthor_id () {return author_id;}

    public String getJournal_name () {return journal_name;}

    public String getJournal_id () {return journal_id;}

    public String getVolume_id () {return volume_id;}

    public String getFile_name () {return file_name;}

    public String getLatest_date () {return latest_date;}

    public String getDeadline () {return deadline;}

    public String getReviewers () {return reviewers;}

    public String getStatus () {return status;}

}
