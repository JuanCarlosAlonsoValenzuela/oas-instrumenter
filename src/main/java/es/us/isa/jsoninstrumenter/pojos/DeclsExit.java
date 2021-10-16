package es.us.isa.jsoninstrumenter.pojos;

import io.swagger.v3.oas.models.media.ArraySchema;
import io.swagger.v3.oas.models.media.Schema;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static es.us.isa.jsoninstrumenter.main.GenerateDeclsFile.*;
import static es.us.isa.jsoninstrumenter.pojos.DeclsVariable.*;
import static es.us.isa.jsoninstrumenter.util.ArrayNestingManager.doBubbleSort;
import static es.us.isa.jsoninstrumenter.util.ArrayNestingManager.getJSONArraysOfSpecifiedNestingLevel;
import static es.us.isa.jsoninstrumenter.util.JSONManager.*;

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
    private boolean isNestedArray;  // Bad practice

    // Used when the exit is of type object
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
        this.isNestedArray = false;

        this.exitDeclsVariables = generateDeclsVariablesOfOutput("return", "return", packageName,
                variableNameOutput + nameSuffix, mapOfProperties);
    }

    // Used when the exit is of type array (bad practice)
    public DeclsExit(String packageName, String endpoint, String operationName, String variableNameInput,
                     DeclsVariable enterVariables, String variableNameOutput, ArraySchema arraySchema, String nameSuffix, String statusCode) {

        this.packageName = packageName;
        this.endpoint = endpoint;
        this.operationName = operationName;
        this.variableNameInput = variableNameInput;
        this.nameSuffix = nameSuffix;
        this.statusCode = statusCode;

        this.exitNumber = numberOfExits;
        numberOfExits = numberOfExits + 1;

        this.enterDeclsVariables = enterVariables;
        this.isNestedArray = true;

        this.exitDeclsVariables = generateDeclsVariablesOfArrayOutput(arraySchema, variableNameOutput + nameSuffix, "return", "return");

    }

    // Used when the exit is primitive (Bad pracice)
    public DeclsExit(String packageName, String endpoint, String operationName, String variableNameInput,
                     DeclsVariable enterVariables, String variableNameOutput, String parameterType, String statusCode) {
        this.packageName = packageName;
        this.endpoint = endpoint;
        this.operationName = operationName;
        this.variableNameInput = variableNameInput;
        this.nameSuffix = "";
        this.statusCode = statusCode;

        this.exitNumber = numberOfExits;
        numberOfExits = numberOfExits + 1;

        this.enterDeclsVariables = enterVariables;
        this.isNestedArray = false;

        this.exitDeclsVariables = generateDeclsVariablesOfPrimitiveResponse(parameterType, variableNameOutput, "return", "return");
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

    public boolean isNestedArray() {
        return isNestedArray;
    }

    public void setNestedArray(boolean nestedArray) {
        isNestedArray = nestedArray;
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

        String responseBody = testCase.getResponseBody();

        // If the exit is of type array (Bad practice)
        if(isStringJsonArray(responseBody)) {
            JSONArray jsonArray = stringToJsonArray(responseBody);

            if(this.isNestedArray) {    // (Bad practice) If the response is parseable to array and the exit is of type nestedArray
                // Count the number of arrays (Nesting level)
                int targetNestingLevel = (int) Arrays.stream(this.getNameSuffix().split("\\.")).filter(x-> x.equalsIgnoreCase("array")).count();
                // Count the number of arrays corresponding to the number of .arrays and return the dtrace
                try {
                    List<JSONArray> jsonArraysToGenerateDtrace = getJSONArraysOfSpecifiedNestingLevel(jsonArray, targetNestingLevel, 1);

                    // For all the elements of the list of jsonArrays, generate a dtrace
                    for(JSONArray element: jsonArraysToGenerateDtrace) {
                        res = res + this.generateSingleDtraceEnterAndExitArray(element, testCase, declsEnter);
                    }

                } catch (IllegalArgumentException e) {
                    e.printStackTrace();
                }


            } else {                    // (Bad practice) If the response is parseable to array but the exit is not of type nestedArray (i.e., we are generating a dtrace for one of its elements)
                // Remove all the arrays until reaching an object and continue with normal behaviour
                // Flatten the elements of the nested arrays
                List<JSONObject> flatList = doBubbleSort(jsonArray);
                // Write in res, generate dtraces from the list
                res = res + this.generateSingleDtraceEnterAndExit(flatList, testCase, declsEnter);

            }

        } else {        // If the response is parseable to JSONObject (Expected practice)
            // Expected behaviour
            JSONObject json = stringToJsonObject(responseBody);
            res = res + this.generateSingleDtraceEnterAndExit(Collections.singletonList(json), testCase, declsEnter);
        }

        return res;

    }

    private String generateSingleDtraceEnterAndExit(List<JSONObject> jsonObjectList, TestCase testCase, DeclsEnter declsEnter){
        String res = "";

        for(JSONObject json: jsonObjectList) {

            // Name suffix
            List<String> elementRoute = Arrays.stream(this.nameSuffix.split("_"))
                    .filter(e -> e.trim().length() > 0)
                    .collect(Collectors.toList());


            List<JSONObject> nestedJsonObjects = new ArrayList<>();

            if(elementRoute.isEmpty()){
                nestedJsonObjects.add(json);
            } else {
                nestedJsonObjects = getListOfJsonElementsForDeclsExit(json, elementRoute);
            }

            for(JSONObject jsonElement: nestedJsonObjects){
                // There must be one Decls Enter per DeclsExit
                res = res + declsEnter.generateDtrace(testCase) + "\n";             // DeclsEnter
                res = res + this.generateSingleDtraceExit(testCase, jsonElement);   // DeclsExit
            }

        }

        return res;

    }

    private String generateSingleDtraceEnterAndExitArray(JSONArray jsonArray, TestCase testCase, DeclsEnter declsEnter) {
        // The JSONArray is the required one
        String res = "";

        res = res + declsEnter.generateDtrace(testCase) + "\n";
        res = res + this.generateSingleDtraceExitArray(testCase, jsonArray);
        return res;
    }

    public String generateSingleDtraceExitArray(TestCase testCase, JSONArray jsonArray) {

        String res = this.getExitName() + ":::EXIT" + exitNumber;

        res = res + "\n" + enterDeclsVariables.generateDtraceEnter(testCase) + "\n";

        // Generate the dtrace exit for the array
        // Group 1
        res = res + "return" + "\n";
        res = res + "\"" + testCase.getTestCaseId() + "_" + this.operationName + "_return_output" + "\"" + "\n";
        res = res + "1" + "\n";

        // Group 2
        res = res + "return.array" + "\n";
        res = res + "\"" + testCase.getTestCaseId() + "_" + this.operationName + "_return" + this.nameSuffix + "_output" + "\"" + "\n";
        res = res + "1" + "\n";

        // Group 3
        res = res + "return.array[..]" + "\n";
        // The declaration type of the first enclosed variable will always reveal the type of the elements of the array
        res = res + generateDtraceExitValueOfJSONArray(testCase, jsonArray, this.exitDeclsVariables.getEnclosedVariables().get(0).getDecType(), "array") + "\n";
        res = res + "1" + "\n\n";

        return res;

    }

    public String generateSingleDtraceExit(TestCase testCase, JSONObject jsonElement) {
        String res = this.getExitName() + ":::EXIT" + exitNumber;

        res = res + "\n" + enterDeclsVariables.generateDtraceEnter(testCase);

        res = res + "\n" + this.exitDeclsVariables.generateDtraceExit(testCase, jsonElement, false) + "\n\n";

        return res;

    }

    public static String generateDtraceExitValueOfJSONArray(TestCase testCase, JSONArray elements, String dectype, String variableName) {
        String value = null;

        // If elements == null, the elements are set to null
        if(elements != null){
            if(primitiveTypes.contains(dectype.replace("[]", ""))) { // If array of primitives
                boolean isString = false;
                if(dectype.replace("[]", "").equals(STRING_TYPE_NAME)) {
                    isString = true;
                }
                value = "";
                for(int i = 0; i < elements.size(); i++) {

                    if(isString && elements.get(i)!=null) {
                        value = value + " \"" + elements.get(i) + "\"";
                    } else {
                        value = value + " " + elements.get(i);
                    }
                }

                value = "[" + value.trim() + "]";

            } else {    // If array of objects  TODO: Array of arrays
                String hashcode = "";
                for(int i = 1; i <= elements.size(); i++) {
                    hashcode = hashcode + "\"" + testCase.getTestCaseId() + "_" + variableName.replace("[..]", "") + "_output_" + i + "\"" + " ";
                }

                value = "[" + hashcode.trim() + "]";
            }
        }
        return value;
    }


    public static List<JSONObject> getListOfJsonElementsForDeclsExit(JSONObject json, List<String> elementRoute){

        List<JSONObject> res = new ArrayList<>();

        String element = elementRoute.get(0);

        if(json == null) {
            throw new NullPointerException("The response of the test case cannot be null");
        }

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
