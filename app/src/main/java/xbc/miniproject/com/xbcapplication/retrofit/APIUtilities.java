package xbc.miniproject.com.xbcapplication.retrofit;

import java.util.HashMap;
import java.util.Map;

public class APIUtilities {
    public static String BASE_URL = "http://139.162.5.173:8080/xbc-ws/api/";

    public static RequestAPIServices getAPIServices() {
        return RetrofitClient.getClient(BASE_URL).create(RequestAPIServices.class);
    }

}

