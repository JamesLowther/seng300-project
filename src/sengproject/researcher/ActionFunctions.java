package sengproject.researcher;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import sengproject.Globals;
import sengproject.jsonparsing.JSONPaperParser;

import java.time.LocalDate;

public class ActionFunctions {

    public static void addNewAction (String pid, String details, String type) {

        String rndActionId = Long.toString((long) (Math.random() * Integer.MAX_VALUE) + 1);

        JSONObject new_action = new JSONObject();
        new_action.put("aid", rndActionId);
        new_action.put("type", type);
        new_action.put("rid", (String) Globals.getUser().get("uid"));
        new_action.put("details", details);
        new_action.put("date_recommended", LocalDate.now().toString());
        new_action.put("date_completed", "");

        JSONPaperParser.addPendingAction(pid, new_action);
    }

    public static void moveAction (String pid, String aid) {

        JSONObject paper = JSONPaperParser.findPaper(pid);

        JSONArray pending_actions = (JSONArray) paper.get("pending_actions");

        JSONArray new_actions = (JSONArray) paper.get("actions");

        JSONArray new_pending_actions = (JSONArray) pending_actions.clone();

        for (Object a : pending_actions) {

            if (((String) ((JSONObject) a).get("aid")).equals(aid)) {
                new_actions.add(a);
                new_pending_actions.remove(a);
            }
        }

        JSONPaperParser.addAction(pid, new_actions, new_pending_actions);


    }

    public static void deleteAction (String pid, String aid) {

        JSONObject paper = JSONPaperParser.findPaper(pid);
        JSONArray actions = (JSONArray) paper.get("actions");

        JSONArray new_actions = (JSONArray) actions.clone();

        for (Object a : actions) {

            if (((String) ((JSONObject) a).get("aid")).equals(aid)) {
                new_actions.remove(a);
            }
        }

        JSONPaperParser.updateActions(pid, new_actions);

    }

    public static void deletePendingAction (String pid, String aid) {

        JSONObject paper = JSONPaperParser.findPaper(pid);
        JSONArray pending_actions = (JSONArray) paper.get("pending_actions");

        JSONArray new_pending_actions = (JSONArray) pending_actions.clone();

        for (Object a : pending_actions) {

            if (((String) ((JSONObject) a).get("aid")).equals(aid)) {
                new_pending_actions.remove(a);
            }
        }

        JSONPaperParser.updatePendingActions(pid, new_pending_actions);

    }

}
