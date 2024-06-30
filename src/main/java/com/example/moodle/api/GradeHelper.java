package com.example.moodle.api;

import com.example.moodle.Student.Entities.Grade;
import com.example.moodle.moodleclient.Moodleclient;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class GradeHelper {
    private static final String SAVE_GRADE = "mod_assign_save_grade";
    private static final String GET_GRADE = "mod_assign_get_submission_status";

    public GradeHelper() {
    }

    public Grade getGrade(long assignmentid, long submissionid, long studentid) {
        Grade grade = null;
        String urlStr = Moodleclient.serverAddress + "webservice/rest/server.php?wstoken=" + Moodleclient.superToken + "&wsfunction=" + GET_GRADE + "&moodlewsrestformat=json&assignid=" + assignmentid +
                "&userid=" + studentid;
        try {
            String res = RequestHelper.formRequest(urlStr);
            JSONParser parser = new JSONParser();
            JSONObject data = (JSONObject) parser.parse(res);
            JSONObject info = (JSONObject) data.get("lastattempt");

            JSONObject submission = (JSONObject) info.get("submission");
            if((Long) submission.get("id") == submissionid) {
                JSONObject feedback = (JSONObject) info.get("feedback");
                JSONObject gr = (JSONObject) feedback.get("grade");
                grade = new Grade();
                grade.setSubmissionid(submissionid);
                grade.setGrade((Long) gr.get("grade"));
                grade.setGrader((Long) gr.get("grader"));

                JSONArray plugins = (JSONArray) feedback.get("plugins");
                JSONObject plugin = (JSONObject) plugins.get(0);
                JSONArray editorfields = (JSONArray) plugin.get("editorfields");
                JSONObject field = (JSONObject) editorfields.get(0);
                grade.setComment(field.get("text").toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return grade;
    }
}
