package tests;

import com.google.gson.Gson;
import test_data.LoginCred;

public class TestGSON {

    public static void main(String[] args) {
        LoginCred loginCred = new LoginCred("micheo@sth.com", "12345688");

        // Convert Object data to JSON
        Gson gson = new Gson();
        System.out.println(gson.toJson(loginCred));

        // Convert JSON to Object data
        String loginCredJSONData = "{\"username\":\"micheo@sth.com\",\"password\":\"12345688\"}";
        LoginCred convertFromJSON = gson.fromJson(loginCredJSONData, LoginCred.class);
        System.out.println(convertFromJSON);
    }
}
