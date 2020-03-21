package sengproject.gui.reviewer.tvobjects;

import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import sengproject.gui.GuiController;
import sengproject.gui.reviewer.ReviewerActionsScene;
import sengproject.gui.reviewer.ReviewerDetailsScene;

public class ReviewerPaper {

    private Button details_b;
    private Button actions_b;
    private CheckBox star_cb;

    private String previous_menu = null;

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
    private String revisions;
    private String status;

    // todo: 'previous_menu' is the previous menu to go back to. It is ether 'menu' or 'browse'
    // this is a solution until I can think of a better way
    public ReviewerPaper (String t, String pi, String sd, String an, String aid, String jn, String jid, String vid, String fn, String ld, String dl, String rev, String revis, String rej, String prev) {

        title = t;
        paper_id = pi;
        sub_date = sd;
        author_name = an;
        author_id = aid;
        journal_name = jn;
        journal_id = jid;
        volume_id = vid;
        file_name = fn;
        latest_date = ld;
        deadline = dl;
        reviewers = rev;
        revisions = revis;
        status = rej;

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

    public String getVolume_id () {return volume_id;}

    public String getFile_name () {return file_name;}

    public String getLatest_date () {return latest_date;}

    public String getDeadline () {return deadline;}

    public String getReviewers () {return reviewers;}

    public String getRevisions () {return revisions;}

    public String getStatus () {return status;}

    public boolean getCheckedStatus () { return star_cb.isSelected(); }

}