package sengproject.gui.editor.tvobjects;

import javafx.scene.control.Button;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import sengproject.gui.GuiController;
import sengproject.gui.editor.EditorActionsScene;
import sengproject.gui.editor.EditorDetailsScene;
import sengproject.gui.researcher.ResearcherActionsScene;
import sengproject.gui.researcher.ResearcherDetailsScene;
import sengproject.gui.researcher.ResearcherReviewersScene;

public class EditorPaper {

    private JSONObject json_obj;

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

    public EditorPaper(JSONObject paper) {

        json_obj = paper;

        title = (String) paper.get("title");
        paper_id = (String) paper.get("paper_id").toString();
        sub_date = (String) paper.get("sub_date");
        author_name = (String) paper.get("author_name");
        author_id = (String) paper.get("author_id");
        journal_name = (String) paper.get("journal_name");
        journal_id = (String) paper.get("journal_id");
        volume_id = (String) paper.get("volume_id");
        file_name = (String) paper.get("file_name");
        latest_date = (String) paper.get("latest_date");
        deadline = (String) paper.get("deadline");
        reviewers = Integer.toString(((JSONArray) paper.get("reviewers")).size());
        status = (String) paper.get("status");

        details_b = new Button("Details");
        details_b.setOnAction(action ->{
            GuiController.changeScene(EditorDetailsScene.getScene(this));
        });

        reviewers_b = new Button("Reviewers");
        reviewers_b.setOnAction(action ->{
            //todo: call reviewers scene
        });

        actions_b = new Button("Actions");
        actions_b.setOnAction(action ->{
            GuiController.changeScene(EditorActionsScene.getScene(this));
        });

        System.out.println("paper created with title: " + title);
    }

    public JSONObject getJson_obj () { return json_obj; }

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
