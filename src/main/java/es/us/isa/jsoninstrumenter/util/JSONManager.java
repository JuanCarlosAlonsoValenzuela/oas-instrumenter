package es.us.isa.jsoninstrumenter.util;


import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JSONManager {

    public static JSONArray stringToJsonArray(String input) {

        JSONParser parser = new JSONParser();
        try {
            JSONArray jsonArray = (JSONArray) parser.parse(input);
            return jsonArray;
        } catch (ParseException e) {
            System.err.println("Error converting the response body to string");
            System.exit(1);
        }

        return null;
    }

    public static JSONObject stringToJsonObject(String input) {

        JSONParser parser = new JSONParser();
        try {
            JSONObject json = (JSONObject) parser.parse(input);
            return  json;
        } catch (ParseException e) {
            System.err.println("Error converting the response body to string");
            System.exit(1);
        }

        return null;
    }

    public static boolean isStringJsonArray(String input) {
        JSONParser parser = new JSONParser();

        try{
            if(parser.parse(input) instanceof JSONArray) {
                return true;
            } else {
                return false;
            }
        } catch (ParseException e) {
            System.err.println("Error converting the response body to string");
            System.exit(1);
        }

        return false;

    }

}
