package sengproject.gui.researcher.tvobjects;

public class ResearcherReviewer {

    private String name;
    private int num_major_rev;
    private int num_minor_rev;
    private int num_pub_rev;
    private float avg_rev_time;

    public ResearcherReviewer (String n, int maj_r, int min_r, int pub_r, float avg_t) {

        name = n;
        num_major_rev = maj_r;
        num_minor_rev = min_r;
        num_pub_rev = pub_r;
        avg_rev_time = avg_t;

    }

    public void setName (String n) {name = n;}

    public String getName () {return name;}

    public void setNum_major_rev (int n) {num_major_rev = n;}

    public int getNum_major_rev () {return num_major_rev;}

    public void setNum_minor_rev (int n) {num_minor_rev = n;}

    public int getNum_minor_rev () {return num_minor_rev;}

    public void setNum_pub_rev (int n) {num_pub_rev = n;}

    public int getNum_pub_rev () {return num_pub_rev;}

    public void setAvg_rev_time (float n) {avg_rev_time = n;}

    public float getAvg_rev_time () {return avg_rev_time;}

}
