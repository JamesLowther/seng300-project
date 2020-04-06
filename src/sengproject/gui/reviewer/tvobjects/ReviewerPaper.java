package sengproject.gui.reviewer.tvobjects;

import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import sengproject.Globals;
import sengproject.gui.GuiController;
import sengproject.gui.reviewer.ReviewerActionsScene;
import sengproject.gui.reviewer.ReviewerDetailsScene;

public class ReviewerPaper {

    private Button details_b;
    private Button actions_b;
    private CheckBox star_cb;

    private JSONObject paper;

    private String previous_menu = null;

    private String title;
    private String paper_id;
    private String sub_date;
    private String author_name;
    private String author_id;
    private String journal_name;
    private String journal_id;
    private String volume_name;
    private String volume_id;
    private String file_name;
    private String latest_date;
    private String deadline;
    private String reviewers;
    private String revisions;
    private String status;

    private String rev_deadline;


    public ReviewerPaper (JSONObject p, String prev, boolean checked) {

        paper = p;

        title = (String) p.get("title");
        paper_id = (String) p.get("paper_id");
        sub_date = (String) p.get("sub_date");
        author_name = (String) p.get("author_name");
        author_id = (String) p.get("author_id");
        journal_name = (String) p.get("journal_name");
        journal_id = (String) p.get("journal_id");
        volume_name = (String) p.get("volume_name");
        volume_id = (String) p.get("volume_id");
        file_name = (String) p.get("file_name");
        latest_date = (String) p.get("latest_date");
        deadline = (String) p.get("deadline");
        reviewers = Integer.toString(((JSONArray) p.get("reviewers")).size());
        revisions = "TODO";
        status = (String) p.get("status");

        // get deadline
        JSONArray revs = (JSONArray) p.get("reviewers");
        for (Object r : revs) {
            if (((JSONObject) r).get("rid").equals((String) Globals.getUser().get("uid"))) {
                rev_deadline = (String) ((JSONObject) r).get("deadline");
            }
        }

        previous_menu = prev;

        details_b = new Button("Details");
        details_b.setOnAction(action ->{
            GuiController.changeScene(ReviewerDetailsScene.getScene(this));
        });

        actions_b = new Button("Actions");
        actions_b.setOnAction(action ->{
            GuiController.changeScene(ReviewerActionsScene.getScene(this));
        });

        star_cb = new CheckBox();
        star_cb.setSelected(checked);

    }

    public JSONObject getPaper () {return paper;}

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

    public void setActions_b (Button b) {
        actions_b = b;
    }

    public Button getActions_b () {
        return actions_b;
    }

    public void setStar_cb (CheckBox cb) { star_cb = cb; }

    public CheckBox getStar_cb () { return star_cb; }

    public void setPrevious_menu (String p) { previous_menu = p; }

    public String getPrevious_menu () { return  previous_menu; }

    public String getPaper_id () {return paper_id;}

    public String getSub_date () {return sub_date;}

    public String getAuthor_name () {return author_name;}

    public String getAuthor_id () {return author_id;}

    public String getJournal_name () {return journal_name;}

    public String getJournal_id () {return journal_id;}

    public String getVolume_name () {return volume_name;}

    public String getVolume_id () {return volume_id;}

    public String getFile_name () {return file_name;}

    public String getLatest_date () {return latest_date;}

    public String getDeadline () {return deadline;}

    public String getReviewers () {return reviewers;}

    public String getRevisions () {return revisions;}

    public String getStatus () {return status;}

    public String getRev_deadline () {return rev_deadline;}

    public boolean getCheckedStatus () { return star_cb.isSelected(); }

}
