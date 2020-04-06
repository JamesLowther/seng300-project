package sengproject.gui.editor.tvobjects;

import javafx.scene.control.Button;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import sengproject.gui.GuiController;
import sengproject.gui.editor.EditorDetailsScene;
import sengproject.gui.editor.EditorManageReviewerScene;
import sengproject.gui.editor.EditorReviewersScene;
import sengproject.gui.researcher.ResearcherReviewersScene;
import sengproject.jsonparsing.JSONPaperParser;
import sengproject.jsonparsing.JSONUserParser;
import sengproject.researcher.ReviewerFunctions;

public class EditorReviewer {

    private String name;
    private long num_major_rev;
    private long num_minor_rev;
    private long num_pub_rev;
    private double avg_rev_time;
    private String deadline;

    private JSONObject reviewer;
    private String pid;
    private String type;

    private Button manage_b;
    private Button add_b;

    public EditorReviewer (JSONObject rev, String p, String d, String t) {

        name = (String) rev.get("username");
        num_major_rev = (long) rev.get("major_rev");
        num_minor_rev = (long) rev.get("minor_rev");
        num_pub_rev = (long) rev.get("papers_reviewed");
        avg_rev_time = (double) ((JSONArray) rev.get("avg_time")).get(0);
        deadline = d;

        reviewer = rev;
        pid = p;
        type = t;

        manage_b = new Button("Manage");
        manage_b.setOnAction(action ->{
            GuiController.changeScene(EditorManageReviewerScene.getScene(this));
        });

        add_b = new Button("Add");
        add_b.setOnAction(action ->{
            if (ReviewerFunctions.addReviewer(pid, (String) rev.get("uid"), type)) {
                JSONUserParser.addPaperRev((String) rev.get("uid"));
                GuiController.changeScene(EditorReviewersScene.getScene(new EditorPaper((JSONObject) JSONPaperParser.findPaper(p))));
            }
            System.out.println("TODO: add reviewer to the paper");
        });

    }

    public void setName (String n) {name = n;}

    public String getName () {return name;}

    public JSONObject getReviewer () {return reviewer;}

    public String getPid () {return pid;}

    public void setNum_major_rev (long n) {num_major_rev = n;}

    public long getNum_major_rev () {return num_major_rev;}

    public void setNum_minor_rev (long n) {num_minor_rev = n;}

    public long getNum_minor_rev () {return num_minor_rev;}

    public void setNum_pub_rev (long n) {num_pub_rev = n;}

    public long getNum_pub_rev () {return num_pub_rev;}

    public void setAvg_rev_time (double n) {avg_rev_time = n;}

    public double getAvg_rev_time () {return avg_rev_time;}

    public void setDeadline (String d) { deadline = d; }

    public String getDeadline () { return deadline; }

    public void setManage_b (Button b) { manage_b = b; }

    public Button getManage_b () { return manage_b; }

    public void setAdd_b (Button b) { add_b = b; }

    public Button getAdd_b () { return add_b; }

}
