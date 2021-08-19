package es.us.isa.jsoninstrumenter.main;

import es.us.isa.jsoninstrumenter.pojos.TestCase;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.util.List;

import static es.us.isa.jsoninstrumenter.util.TestCaseFileManager.getTestCasesFromFile;

public class Draft {

    public static void main(String[] args) {

        List<TestCase> testCaseList = getTestCasesFromFile("src/main/resources/AirportInfo/testCases.csv");

        TestCase testCase = testCaseList.get(0);

        String jsonString = testCase.getResponseBody();

        JSONParser parser = new JSONParser();


        JSONObject json;
        try {
            json = (JSONObject) parser.parse(jsonString);
            json.get("iata");





        } catch (ParseException e) {
            e.printStackTrace();
        }





    }
}
