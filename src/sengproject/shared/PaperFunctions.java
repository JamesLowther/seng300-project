package sengproject.shared;

import org.json.simple.JSONObject;
import sengproject.Globals;
import sengproject.jsonparsing.JSONPaperParser;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;

public class PaperFunctions {

    private static String base_path = "./submissions/";

    // creates a new paper for a particular researcher
    // returns true if paper sucessfully create
    // returns false otherwise
    public static Boolean createNewPaper (String title, String j_title, String jid, String v_title, String vid, String deadline, ArrayList<JSONObject> pref_rev, File selected_file) {

        if (!checkValidity(selected_file) || title.equals("")) {return false;}

        String todays_date = LocalDate.now().toString();
        ArrayList<String> pref_rev_array = new ArrayList<String>();

        for (JSONObject p : pref_rev) {
            pref_rev_array.add((String) p.get("uid"));
        }

        JSONObject new_paper = JSONPaperParser.addPaper(title, "not_needed", todays_date,
                j_title, jid, v_title, vid, selected_file.getName(), todays_date, deadline, pref_rev_array, "pending");

        if (new_paper != null) {

            String pid = (String) new_paper.get("paper_id");

            File dest = new File(base_path + Globals.getUser().get("uid").toString());
            if (!dest.exists()) {
                dest.mkdirs();
            }

            try {
                String file_name = pid + ".pdf";

                Files.copy(Paths.get(selected_file.getPath()), Paths.get(dest.getPath() + "/" + file_name));
            } catch (IOException e) {
                System.out.println(e.getLocalizedMessage());
                return false;
            }
        }
        else {return false;}

        return true;
    }

    // updates the file associated with a paper's pid
    // returns true if the paper is successfully submitted
    // returns false otherwise
    public static Boolean updatePaperFile (String pid, File selected_file) {

        if (! checkValidity(selected_file)) {return false;}

        String todays_date = LocalDate.now().toString();

        JSONObject paper = JSONPaperParser.findPaper(pid);

        File dest = new File(base_path + Globals.getUser().get("uid").toString());
        if (!dest.exists()) {
            dest.mkdirs();
        }

        File temp = new File(dest.getPath() + "/" + pid + ".pdf");
        if (temp.exists()) {
            temp.delete();
            System.out.println("Deleted existing file");
        }

        try {
            String file_name = pid + ".pdf";

            Files.copy(Paths.get(selected_file.getPath()), Paths.get(dest.getPath() + "/" + file_name));

            JSONPaperParser.updatePaperFile(pid, selected_file.getName());

        } catch (IOException e) {
            System.out.println(e.getLocalizedMessage());
            return false;
        }

        JSONPaperParser.updateStatus(pid, "pending");

        return true;

    }

    // checks to make sure the file is valid
    // not null and has a pdf extension
    public static Boolean checkValidity (File file) {

        // ensure file is selected
        if (file == null) {
            return false;
        }

        String fn = file.getName();
        int dot_index = fn.indexOf('.');

        // check if pdf file type
        if (!".pdf".equals(fn.substring(dot_index, fn.length()))) {
            return false;
        }

        return true;

    }

    // downloads the document related to the pid
    // returns true if file downloaded successfully
    public static Boolean downloadFile (String pid, File dest) {

        if (dest == null) { return false; }

        JSONObject paper = JSONPaperParser.findPaper(pid);

        String file_name = pid + ".pdf";
        String src = base_path + paper.get("author_id") + "/" + file_name;

        System.out.println(src);

        if (src == null) {
            return false;
        }

        try {

            Files.copy(Paths.get(src), Paths.get(dest.getPath() + "/" + file_name));
        } catch (IOException e) {
            System.out.println(e.getLocalizedMessage());
            return false;
        }

        return true;

    }

}
