package com.example.moodle.api;

import com.example.moodle.Student.Entities.Module;
import com.example.moodle.moodleclient.Moodleclient;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.util.ArrayList;

public class ModuleHelper {
    private static final String COURSE_CONTENT = "core_course_get_contents";
    private static final String GET_MODULE = "core_course_get_course_module";

    public ArrayList<Module> getModules(long courseid, long sectionid) {
        ArrayList<Module> modules = new ArrayList<>();
        String urlStr = Moodleclient.serverAddress + "webservice/rest/server.php?wstoken=" + Moodleclient.superToken + "&wsfunction=" + COURSE_CONTENT + "&moodlewsrestformat=json&courseid=" + courseid;

        try {
            String res = RequestHelper.formRequest(urlStr);
            JSONParser parser = new JSONParser();
            JSONArray data = (JSONArray) parser.parse(res);
            JSONObject section = (JSONObject) data.get((int)sectionid);

            JSONArray jsonArray = (JSONArray) section.get("modules");
            for(int i = 0; i < jsonArray.size(); i++) {
                JSONObject jsonObject = (JSONObject) jsonArray.get(i);

                Module module = new Module();
                module.setCmid((Long)jsonObject.get("id"));
                module.setSectionid(sectionid);
                module.setName(jsonObject.get("name").toString());
                module.setModname(jsonObject.get("modname").toString());
                module.setModplural(jsonObject.get("modplural").toString());
                module.setDownloadcontent((Long)jsonObject.get("downloadcontent"));

                modules.add(module);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return modules;
    }

    public Module getModule(long cmid) {
        Module module = null;
        String urlStr = Moodleclient.serverAddress + "webservice/rest/server.php?wstoken=" + Moodleclient.superToken + "&wsfunction=" + GET_MODULE + "&moodlewsrestformat=json&cmid=" + cmid;
        try {
            String res = RequestHelper.formRequest(urlStr);
            JSONParser parser = new JSONParser();
            JSONObject data = (JSONObject) parser.parse(res);
            JSONObject jsonObject = (JSONObject) data.get("cm");

            module = new Module();
            module.setCmid((Long)jsonObject.get("id"));
            module.setSectionid((Long)jsonObject.get("section"));
            module.setName(jsonObject.get("name").toString());
            module.setModname(jsonObject.get("modname").toString());
            module.setModplural("Files");
            module.setDownloadcontent((Long)jsonObject.get("downloadcontent"));

        } catch (Exception e) {
            e.printStackTrace();
        }
        return module;
    }
}
