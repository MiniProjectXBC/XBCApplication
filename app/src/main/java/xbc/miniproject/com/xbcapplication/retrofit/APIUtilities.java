package xbc.miniproject.com.xbcapplication.retrofit;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.HashMap;
import java.util.Map;

import okhttp3.MediaType;

public class APIUtilities {
    public static String BASE_URL = "http://139.162.5.173:8080/";

    public static RequestAPIServices getAPIServices() {
        return RetrofitClient.getClient(BASE_URL).create(RequestAPIServices.class);
    }

    public static MediaType mediaType() {
        return okhttp3.MediaType.parse("application/json; charset=utf-8");
    }

    public static String generateLoginMap(String username, String password) {
        Map<String, String> map = new HashMap<>();
        if (username != null) map.put("username", username);
        if (password != null) map.put("password", password);

        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.serializeNulls().create();
        String json = gson.toJson(map);

        return json;
    }

        //generate get idleNews MAP params
        public static String generateIdleNewsMap (String title, String category, String content){
            Map<String, Object> map = new HashMap<>();
            if (title != null) map.put("title", title);
            if (content != null) map.put("content", content);

            if (category != null) {
                Map<String, String> unitObj = new HashMap<>();
                unitObj.put("name", category);
                map.put("name", unitObj);
            }

            GsonBuilder builder = new GsonBuilder();
            Gson gson = builder.serializeNulls().create();
            String json = gson.toJson(map);

            return json;
        }



        //generated get assignment MAP param
        public static String generateAssignmentMap (String name, String title, String startDate, String endDate, String description){
            Map<String, Object> map = new HashMap<>();
            if (title != null) map.put("title", title);
            if (description != null) map.put("content", description);
            if (startDate != null) map.put("startDate", startDate);
            if (endDate != null) map.put("endDate", endDate);

            if (name != null) {
                Map<String, String> unitObj = new HashMap<>();
                unitObj.put("name", name);
                map.put("name", unitObj);
            }

            GsonBuilder builder = new GsonBuilder();
            Gson gson = builder.serializeNulls().create();
            String json = gson.toJson(map);

            return json;
        }
}



