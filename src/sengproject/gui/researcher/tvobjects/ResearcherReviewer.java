package sengproject.gui.researcher.tvobjects;

public class ResearcherReviewer {

    private String name;
    private int num_major_rev;
    private int num_minor_rev;
    private int num_pub_rev;
    private int avg_rev_time;

    //todo: likely have a JSONObject as a input
    public ResearcherReviewer (String n) {

        //todo: this is all just test date
        // it will need to be populated with data from the json files
        name = n;
        num_major_rev = 0;
        num_minor_rev = 1;
        num_pub_rev = 2;
        avg_rev_time = 3;


    }

    public void setName (String n) {name = n;}

    public String getName () {return name;}

    public void setNum_major_rev (int n) {num_major_rev = n;}

    public int getNum_major_rev () {return num_major_rev;}

    public void setNum_minor_rev (int n) {num_minor_rev = n;}

    public int getNum_minor_rev () {return num_minor_rev;}

    public void setNum_pub_rev (int n) {num_pub_rev = n;}

    public int getNum_pub_rev () {return num_pub_rev;}

    public void setAvg_rev_time (int n) {avg_rev_time = n;}

    public int getAvg_rev_time () {return avg_rev_time;}

}
