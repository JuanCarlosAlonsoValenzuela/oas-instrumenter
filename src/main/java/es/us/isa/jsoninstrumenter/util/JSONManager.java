package es.us.isa.jsoninstrumenter.util;


import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JSONManager {

    public static JSONObject stringToJson(String input) {

        JSONParser parser = new JSONParser();
        try {
            JSONObject json = (JSONObject) parser.parse(input);
            return  json;
        } catch (ParseException e) {
            System.err.println("Error converting the response body to string");
        }

        return null;
    }

}
