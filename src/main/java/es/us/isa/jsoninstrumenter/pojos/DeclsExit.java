package es.us.isa.jsoninstrumenter.pojos;

import io.swagger.v3.oas.models.media.Schema;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static es.us.isa.jsoninstrumenter.main.GenerateDeclsFile.numberOfExits;
import static es.us.isa.jsoninstrumenter.pojos.DeclsVariable.*;
import static es.us.isa.jsoninstrumenter.util.JSONManager.stringToJson;

public class DeclsExit {

    private String packageName;
    private String endpoint;
    private String operationName;
    private String statusCode;
    private String variableNameInput;
    private int exitNumber;
    private DeclsVariable enterDeclsVariables;
    private DeclsVariable exitDeclsVariables;
    private String nameSuffix;

    public DeclsExit(String packageName, String endpoint, String operationName, String variableNameInput,
                     DeclsVariable enterVariables, String variableNameOutput, Schema mapOfProperties, String nameSuffix, String statusCode) {
        this.packageName = packageName;
        this.endpoint = endpoint;
        this.operationName = operationName;
        this.variableNameInput = variableNameInput;
        this.nameSuffix = nameSuffix;
        this.statusCode = statusCode;

        this.exitNumber = numberOfExits;
        numberOfExits = numberOfExits + 1;

        this.enterDeclsVariables = enterVariables;

        this.exitDeclsVariables = generateDeclsVariablesOfOutput("return", "return", packageName,
                variableNameOutput + nameSuffix, mapOfProperties);
    }

    public String getExitName() {
        return this.packageName + "." + this.endpoint + "." + this.operationName + "_" + this.statusCode +  this.nameSuffix + "(" +
                this.packageName + "." + this.variableNameInput + ")";
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public String getOperationName() {
        return operationName;
    }

    public void setOperationName(String operationName) {
        this.operationName = operationName;
    }

    public String getVariableNameInput() {
        return variableNameInput;
    }

    public void setVariableNameInput(String variableNameInput) {
        this.variableNameInput = variableNameInput;
    }

    public int getExitNumber() {
        return exitNumber;
    }

    public void setExitNumber(int exitNumber) {
        this.exitNumber = exitNumber;
    }

    public DeclsVariable getEnterDeclsVariables() {
        return enterDeclsVariables;
    }

    public void setEnterDeclsVariables(DeclsVariable enterDeclsVariables) {
        this.enterDeclsVariables = enterDeclsVariables;
    }

    public DeclsVariable getExitDeclsVariables() {
        return exitDeclsVariables;
    }

    public void setExitDeclsVariables(DeclsVariable exitDeclsVariables) {
        this.exitDeclsVariables = exitDeclsVariables;
    }

    public String getNameSuffix() {
        return nameSuffix;
    }

    public void setNameSuffix(String nameSuffix) {
        this.nameSuffix = nameSuffix;
    }

    public String toString() {
        String res = "ppt " + this.getExitName() + ":::EXIT" + exitNumber + "\n" +
                "ppt-type subexit";

        res = res + "\n" + enterDeclsVariables;

        res = res + "\n" + exitDeclsVariables;

        return res;
    }

    public String generateDtrace(TestCase testCase, DeclsEnter declsEnter) {
        String res = "";

        JSONObject json = stringToJson(testCase.getResponseBody());
        // Name suffix
        List<String> elementRoute = Arrays.stream(this.nameSuffix.split("_"))
                .filter(e -> e.trim().length() > 0)
                .collect(Collectors.toList());


        List<JSONObject> jsonObjectList = new ArrayList<>();

        if(elementRoute.isEmpty()){
            jsonObjectList.add(json);
        } else {
            jsonObjectList = getListOfJsonElementsForDeclsExit(json, elementRoute);
        }

        for(JSONObject jsonElement: jsonObjectList){
            // There must be one Decls Enter per DeclsExit
            res = res + declsEnter.generateDtrace(testCase) + "\n";         // DeclsEnter
            res = res + this.generateSingleDtrace(testCase, jsonElement);   // DeclsExit
        }

        return res;

    }

    public String generateSingleDtrace(TestCase testCase, JSONObject jsonElement) {
        String res = this.getExitName() + ":::EXIT" + exitNumber;

        res = res + "\n" + enterDeclsVariables.generateDtraceEnter(testCase);

        res = res + "\n" + this.exitDeclsVariables.generateDtraceExit(testCase, jsonElement, false) + "\n\n";

        return res;

    }


    public static List<JSONObject> getListOfJsonElementsForDeclsExit(JSONObject json, List<String> elementRoute){

        List<JSONObject> res = new ArrayList<>();

        String element = elementRoute.get(0);

        Object jsonSon = json.get(element);

        if(jsonSon instanceof  JSONObject) {        // If jsonSon is of type JSONObject
            // TODO: Complete
            JSONObject jsonSonObject = (JSONObject) jsonSon;
            if(elementRoute.size() == 1) {      // If element is the last element of elementRoute (i.e. is the object we are looking for)
                res.add((JSONObject) jsonSonObject.get(element));
            } else {
                // Recursive call if element is not the last element of elementRoute
                res.addAll(getListOfJsonElementsForDeclsExit(jsonSonObject, elementRoute.subList(1, elementRoute.size())));
            }
        } else if(jsonSon instanceof JSONArray) {   // If jsonSon is of type JSONArray
            // TODO: Consider that there may be nested arrays
            JSONArray jsonSonArray = (JSONArray) jsonSon;

            // Iterate over all elements of the JSONArray
            for(int i = 0; i < jsonSonArray.size(); i++) {
                Object jsonSonElement = jsonSonArray.get(i);

                if(jsonSonElement instanceof JSONObject) {
                    if(elementRoute.size()==1){
                        res.add((JSONObject) jsonSonElement);
                    } else {
                        res.addAll(getListOfJsonElementsForDeclsExit((JSONObject) jsonSonElement, elementRoute.subList(1, elementRoute.size())));
                    }
                } else {
                    // TODO: Complete
                }
            }

        }

        return res;

    }

}
