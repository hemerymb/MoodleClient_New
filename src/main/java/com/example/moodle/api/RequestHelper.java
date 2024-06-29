package com.example.moodle.api;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class RequestHelper {
    public static String formRequest (String string) throws MalformedURLException, IOException {
        URL url = new URL(string);

        HttpURLConnection http = (HttpURLConnection) url.openConnection();
        http.setRequestMethod("GET");
        http.connect();

        int status = http.getResponseCode();

        if (status == 200) {
            String res = "";

            Scanner sc = new Scanner(url.openStream());

            while (sc.hasNext()) {
                res += sc.nextLine();
            }
            System.out.println(res);
            return res;
        }
        return "";
    }
}
