package com.example.moodle.api;

import com.example.moodle.Student.Entities.Section;
import com.example.moodle.moodleclient.Moodleclient;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.util.ArrayList;

public class SectionHelper {
    private static final String COURSE_CONTENT = "core_course_get_contents";

    public SectionHelper() {
    }

    public ArrayList<Section> getSections(long courseid) {
        ArrayList<Section> sections = new ArrayList<>();
        String urlStr = Moodleclient.serverAddress + "webservice/rest/server.php?wstoken=" + Moodleclient.superToken + "&wsfunction=" + COURSE_CONTENT + "&moodlewsrestformat=json&courseid=" + courseid;

        try {
            String res = RequestHelper.formRequest(urlStr);
            JSONParser parser = new JSONParser();
            JSONArray data = (JSONArray) parser.parse(res);
            for(int i = 0; i < data.size(); i++) {
                JSONObject jsonObject = (JSONObject) data.get(i);

                Section section = new Section();
                section.setSectionid((Long) jsonObject.get("id"));
                section.setSectionname(jsonObject.get("name").toString());
                section.setCourseid(courseid);

                sections.add(section);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sections;
    }
}
