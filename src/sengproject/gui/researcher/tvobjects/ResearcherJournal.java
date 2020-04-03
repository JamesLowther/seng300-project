package sengproject.gui.researcher.tvobjects;

import org.json.simple.JSONObject;

public class ResearcherJournal {

    private String j_title;
    private String v_title;
    private String jid;
    private String vid;

    public ResearcherJournal (String jt, String vt, String jd, String vd) {

        j_title = jt;
        v_title = vt;
        jid = jd;
        vid = vd;
    }

    @Override
    public String toString () {

        return j_title + " | " + v_title;

    }

    public String getJ_title() {
        return j_title;
    }

    public String getV_title() {
        return v_title;
    }

    public String getJid() {
        return jid;
    }

    public String getVid() {
        return vid;
    }


}
