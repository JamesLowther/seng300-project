package sengproject.shared;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import sengproject.Globals;
import sengproject.jsonparsing.JSONPaperParser;

import java.time.LocalDate;

public class ActionFunctions {

    // adds a new action to a particular paper given its pid
    // takes the action details and its type
    public static void addNewAction (String pid, String details, String type) {

        String rndActionId = Long.toString((long) (Math.random() * Integer.MAX_VALUE) + 1);

        JSONObject new_action = new JSONObject();
        new_action.put("aid", rndActionId);
        new_action.put("type", type);
        new_action.put("rid", (String) Globals.getUser().get("uid"));
        new_action.put("details", details);
        new_action.put("date_recommended", LocalDate.now().toString());
        new_action.put("date_completed", "pending");

        JSONPaperParser.addPendingAction(pid, new_action);
    }

    // moves an action from pending actions to actions seen by the researcher
    // takes the paper ID and the action ID
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

    // moves an action from actions seen by the researcher to complete actions
    // takes the paper ID and the action ID
    public static void completeAction (String pid, String aid) {

        JSONObject paper = JSONPaperParser.findPaper(pid);
        JSONArray actions = (JSONArray) paper.get("actions");
        JSONArray complete_actions = (JSONArray) paper.get("complete_actions");

        JSONArray new_actions = (JSONArray) actions.clone();

        for (Object a : actions) {

            if (((String) ((JSONObject) a).get("aid")).equals(aid)) {
                new_actions.remove(a);

                JSONObject temp = new JSONObject();
                temp.put("details", ((JSONObject) a).get("details"));
                temp.put("date_recommended", ((JSONObject) a).get("date_recommended"));
                temp.put("date_completed", LocalDate.now().toString());
                temp.put("type", ((JSONObject) a).get("type"));
                temp.put("rid", ((JSONObject) a).get("rid"));
                temp.put("aid", ((JSONObject) a).get("aid"));

                complete_actions.add(temp);
            }
        }

        JSONPaperParser.updateActions(pid, new_actions);
        JSONPaperParser.addCompleteAction(pid, complete_actions);

    }

    // deletes a pending action (if editor denies it)
    // takes the paper ID and the action ID
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
