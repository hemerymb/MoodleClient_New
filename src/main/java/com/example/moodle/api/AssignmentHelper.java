package com.example.moodle.api;

import com.example.moodle.Student.Entities.Assignment;
import com.example.moodle.moodleclient.Moodleclient;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.util.ArrayList;

public class AssignmentHelper {
    private static final String GET_ASSIGNMENTS = "mod_assign_get_assignments";
    public AssignmentHelper() {
    }

    public ArrayList<Assignment> getAssignments(long courseid) {
        ArrayList<Assignment> assignments = new ArrayList<>();
        String urlStr = Moodleclient.serverAddress + "webservice/rest/server.php?wstoken=" + Moodleclient.superToken + "&wsfunction=" + GET_ASSIGNMENTS + "&moodlewsrestformat=json&courseids[0]=" + courseid;
        try {
            String res = RequestHelper.formRequest(urlStr);
            JSONParser parser = new JSONParser();
            JSONObject data = (JSONObject) parser.parse(res);
            JSONArray courses = (JSONArray) data.get("courses");
            JSONObject course = (JSONObject) courses.get(0);
            JSONArray array = (JSONArray) course.get("assignments");
            for(int i = 0; i < array.size(); i++) {
                JSONObject jsonObject = (JSONObject) array.get(i);

                Assignment assignment = new Assignment();
                assignment.setAssignmentid((Long)jsonObject.get("id"));
                assignment.setModuleid((Long)jsonObject.get("cmid"));
                assignment.setAssignmentname(jsonObject.get("name").toString());
                assignment.setCreated((Long)jsonObject.get("allowsubmissionsfromdate"));
                assignment.setDuedate((Long)jsonObject.get("duedate"));
                assignment.setAttemptnumber((Long)jsonObject.get("nosubmissions"));

                assignments.add(assignment);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return assignments;
    }
}
