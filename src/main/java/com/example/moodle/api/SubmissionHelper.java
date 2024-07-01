package com.example.moodle.api;

import com.example.moodle.Student.Entities.Submission;
import com.example.moodle.moodleclient.Moodleclient;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.util.ArrayList;

public class SubmissionHelper {
    private static final String GET_SUBMISSIONS = "mod_assign_get_submissions";

    public SubmissionHelper() {
    }

    public ArrayList<Submission> getSubmissions(long assignmentid, long studentid) {
        ArrayList<Submission> submissions = new ArrayList<>();
        String urlStr = Moodleclient.serverAddress + "webservice/rest/server.php?wstoken=" + Moodleclient.superToken + "&wsfunction=" + GET_SUBMISSIONS + "&moodlewsrestformat=json&assignmentids[0]=" + assignmentid;
        try {
            String res = RequestHelper.formRequest(urlStr);
            JSONParser parser = new JSONParser();
            JSONObject data = (JSONObject) parser.parse(res);
            JSONArray assignments = (JSONArray) data.get("assignments");
            for(int i = 0; i < assignments.size(); i++) {
                JSONObject assign = (JSONObject)assignments.get(i);
                if((Long)assign.get("assignmentid") == assignmentid) {
                    JSONArray jsonArray = (JSONArray) assign.get("submissions");
                    for(int j = 0; j < jsonArray.size(); j++) {
                        JSONObject jsonObject = (JSONObject) jsonArray.get(j);
                        if((Long)jsonObject.get("userid") == studentid) {
                            Submission submission = new Submission();
                            submission.setSubmissionid((Long)jsonObject.get("id"));
                            submission.setAssignmentid(assignmentid);
                            submission.setStatus(jsonObject.get("status").toString());
                            submission.setStudentid(studentid);
                            submission.setCreated((Long)jsonObject.get("timecreated"));
                            submission.setUpdated((Long)jsonObject.get("timemodified"));

                            submissions.add(submission);
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return submissions;
    }

    // For the teacher
    public ArrayList<Submission> getAllSubmissions(long assignmentid) {
        ArrayList<Submission> submissions = new ArrayList<>();
        String urlStr = Moodleclient.serverAddress + "webservice/rest/server.php?wstoken=" + Moodleclient.superToken + "&wsfunction=" + GET_SUBMISSIONS + "&moodlewsrestformat=json&assignmentids[0]=" + assignmentid;
        try {
            String res = RequestHelper.formRequest(urlStr);
            JSONParser parser = new JSONParser();
            JSONObject data = (JSONObject) parser.parse(res);
            JSONArray assignments = (JSONArray) data.get("assignments");
            for(int i = 0; i < assignments.size(); i++) {
                JSONObject assign = (JSONObject)assignments.get(i);
                if((Long)assign.get("assignmentid") == assignmentid) {
                    JSONArray jsonArray = (JSONArray) assign.get("submissions");
                    for(int j = 0; j < jsonArray.size(); j++) {
                        JSONObject jsonObject = (JSONObject) jsonArray.get(j);
                        Submission submission = new Submission();
                        submission.setSubmissionid((Long)jsonObject.get("id"));
                        submission.setAssignmentid(assignmentid);
                        submission.setStatus(jsonObject.get("status").toString());
                        submission.setStudentid((Long)jsonObject.get("userid"));
                        submission.setCreated((Long)jsonObject.get("timecreated"));
                        submission.setUpdated((Long)jsonObject.get("timemodified"));

                            submissions.add(submission);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return submissions;
    }
}
