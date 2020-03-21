package sengproject.researcher;

import org.json.simple.JSONObject;
import sengproject.Globals;
import sengproject.jsonparsing.JSONPaperParser;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;

public class PaperFunctions {

    public static Boolean createNewPaper (String title, String jid, String vid, File selected_file) {

        String todays_date = LocalDate.now().toString();



        JSONObject new_paper = JSONPaperParser.addPaper(title, "not_needed", todays_date,
                "TODO J_NAME", jid, vid, selected_file.getName(), todays_date, "not set", "none", "pending");

        if (new_paper != null) {

            String pid = new_paper.get("paper_id").toString();

            File dest = new File("./submissions/" + Globals.getUser().get("uid").toString());
            if (!dest.exists()) {
                dest.mkdirs();
            }

            try {
                String file_name = pid + ".pdf";

                Files.copy(Paths.get(selected_file.getPath()), Paths.get(dest.getPath() + "/" + file_name));
            } catch (IOException e) {
                System.out.println(e.getLocalizedMessage());
            }
        }
    }

    public static void updatePaperFile (int pid, File selected_file) {

        String base_path = "./submissions/";

        String todays_date = LocalDate.now().toString();

        JSONObject paper = JSONPaperParser.findPaper(pid);

        File dest = new File(base_path + Globals.getUser().get("uid").toString());
        if (!dest.exists()) {
            dest.mkdirs();
        }

        File temp = new File(dest.getPath() + "/" + Integer.toString(pid) + ".pdf");
        if (temp.exists()) {
            temp.delete();
            System.out.println("Deleted existing file");
        }

        try {
            String file_name = Integer.toString(pid) + ".pdf";

            Files.copy(Paths.get(selected_file.getPath()), Paths.get(dest.getPath() + "/" + file_name));

            JSONPaperParser.updatePaperFile(pid, selected_file.getName());

        } catch (IOException e) {
            System.out.println(e.getLocalizedMessage());
        }

    }

}
