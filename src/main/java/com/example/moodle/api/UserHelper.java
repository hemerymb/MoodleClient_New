package com.example.moodle.api;

import com.example.moodle.Student.Entities.User;
import com.example.moodle.moodleclient.Moodleclient;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.net.MalformedURLException;

public class UserHelper {
    private static final String GET_USER = "core_user_get_users";

    public UserHelper() {
    }

    public String getUserToken(String username, String password) throws MalformedURLException, IOException, ParseException {
        String token = "";
        String urlStr = Moodleclient.serverAddress + "login/token.php?username=" + username + "&password=" + password + "&service=moodle";
        String res = RequestHelper.formRequest(urlStr);
        if(!res.isEmpty()) {
            JSONParser parser = new JSONParser();
            JSONObject jsonObject = (JSONObject) parser.parse(res);

            if(jsonObject.keySet().contains("token")) {
                token = jsonObject.get("token").toString();
                System.out.println(token);
                return  token;
            }
        }
        return token;
    }

    public User getUser(String username, String password, int role) {
        User user = null;
        try {
            String token = getUserToken(username, password);
            if(!token.isEmpty()) {
                System.out.println(token);
                String urlStr = Moodleclient.serverAddress + "webservice/rest/server.php?wstoken=" + Moodleclient.superToken + "&wsfunction=" + GET_USER + "&moodlewsrestformat=json&criteria[0][key]=username&criteria[0][value]=" + username;
                String res = RequestHelper.formRequest(urlStr);
                if(!res.isEmpty()) {
                    JSONParser parser = new JSONParser();
                    JSONObject data = (JSONObject) parser.parse(res);
                    JSONArray users = (JSONArray) data.get("users");
                    JSONObject temp = (JSONObject) users.get(0);
                    System.out.println(temp.toString());

                    user = new User();
                    user.setUserid((Long) temp.get("id"));
                    user.setPassword(password);
                    user.setUsername(username);
                    user.setToken(token);
                    user.setPicture(temp.get("profileimageurl").toString());
                    user.setRole(role);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }
}
