package api_learning.gson;

import com.google.gson.Gson;
import test_data.models.LoginCred;


public class TestGSON {

    public static void main(String[] args) {
        LoginCred loginCred = new LoginCred("teo@sth.com", "12345678");

        // Convert from Object data to JSON
        Gson gson = new Gson();
        System.out.println(gson.toJson(loginCred));

        // Convert from JSON to Object Data
        String loginCredJSONData = "{\"email\":\"teo@sth.com\",\"password\":\"12345678\"}";
        LoginCred convertedFromJSON = gson.fromJson(loginCredJSONData, LoginCred.class);
        System.out.println(convertedFromJSON);
    }
}
