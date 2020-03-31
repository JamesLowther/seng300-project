package sengproject.gui.editor.tvobjects;

import javafx.scene.control.Button;
import sengproject.gui.GuiController;
import sengproject.gui.editor.EditorDetailsScene;

public class EditorReviewer {

    private String name;
    private long num_major_rev;
    private long num_minor_rev;
    private long num_pub_rev;
    private double avg_rev_time;
    private String deadline;

    private Button manage_b;
    private Button add_b;

    public EditorReviewer (String n, long maj_r, long min_r, long pub_r, double avg_t, String dl) {

        name = n;
        num_major_rev = maj_r;
        num_minor_rev = min_r;
        num_pub_rev = pub_r;
        avg_rev_time = avg_t;
        deadline = deadline;

        manage_b = new Button("Manage");
        manage_b.setOnAction(action ->{
            //todo: call manage scene
        });

        add_b = new Button("Add");
        add_b.setOnAction(action ->{
            //todo: add reviewer to paper
            System.out.println("TODO: add reviewer to the paper");
        });

    }

    public void setName (String n) {name = n;}

    public String getName () {return name;}

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
