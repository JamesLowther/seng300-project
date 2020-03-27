package sengproject.gui.researcher.tvobjects;

public class ResearcherReviewer {

    private String name;
    private long num_major_rev;
    private long num_minor_rev;
    private long num_pub_rev;
    private double avg_rev_time;

    public ResearcherReviewer (String n, long maj_r, long min_r, long pub_r, double avg_t) {

        name = n;
        num_major_rev = maj_r;
        num_minor_rev = min_r;
        num_pub_rev = pub_r;
        avg_rev_time = avg_t;

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

}
