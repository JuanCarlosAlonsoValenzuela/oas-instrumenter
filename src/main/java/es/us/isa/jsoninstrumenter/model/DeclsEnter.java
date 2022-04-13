package es.us.isa.jsoninstrumenter.model;

import io.swagger.v3.oas.models.Operation;
import org.json.simple.JSONArray;

import static es.us.isa.jsoninstrumenter.main.GenerateDeclsFile.STRING_TYPE_NAME;
import static es.us.isa.jsoninstrumenter.model.DeclsExit.generateDtraceExitValueOfJSONArray;
import static es.us.isa.jsoninstrumenter.model.DeclsVariable.getListOfDeclsVariables;
import static es.us.isa.jsoninstrumenter.util.JSONManager.stringToJsonArray;

public class DeclsEnter {

    private String packageName;
    private String endpoint;
    private String operationName;
    private String variableNameInput;
    private String nameSuffix;
    private String statusCode;

    private DeclsVariable declsVariables;

    public DeclsEnter(String packageName, String endpoint,
                      String operationName, String variableNameInput,
                      Operation operation, String rootVariableName,
                      String nameSuffix, String statusCode) {
        this.packageName = packageName;
        this.endpoint = endpoint;
        this.operationName = operationName;
        this.variableNameInput = variableNameInput;
        this.declsVariables = getListOfDeclsVariables(packageName, variableNameInput, rootVariableName, operation);

        this.nameSuffix = nameSuffix;
        this.statusCode = statusCode;
    }

    public String getEnterName() {
        return this.packageName + "." + this.endpoint + "." + this.operationName + "_" + this.statusCode + this.nameSuffix + "(" +
                this.packageName + "." + this.variableNameInput + ")";
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

    public String getNameSuffix() { return nameSuffix; }

    public void setNameSuffix(String nameSuffix) { this.nameSuffix = nameSuffix; }

    public String getStatusCode() { return statusCode; }

    public void setStatusCode(String statusCode) { this.statusCode = statusCode; }

    public DeclsVariable getDeclsVariables() {
        return declsVariables;
    }

    public void setDeclsVariables(DeclsVariable declsVariables) {
        this.declsVariables = declsVariables;
    }

    public String toString() {
        String res = "ppt " + this.getEnterName() + ":::ENTER" + "\n" +
                "ppt-type enter";

        res = res + "\n" + declsVariables;

        return res;
    }

    public String generateDtrace(TestCase testCase) {

        String res = this.getEnterName() + ":::ENTER";

        res = res + "\n" + declsVariables.generateDtraceEnter(testCase);

        res = res + "\n";

        return res;
    }

    public static String generateDtraceEnterValueOfArray(TestCase testCase, String elements, String dectype, String variableName) {
        // Convert the input into a JSONArray in order to call the generateDtraceExitValueOfJSONArray
        JSONArray valueArray = null;
        if(dectype.replace("[]","").equals(STRING_TYPE_NAME)) {
            // Add double quotes to all array elementsApply if the elements are of type string
            valueArray = stringToJsonArray( "[\"" + elements.replace(",","\",\"") + "\"]");
        } else {
            valueArray = stringToJsonArray("[" + elements + "]");
        }

        return generateDtraceExitValueOfJSONArray(testCase, valueArray, dectype, variableName);
    }

}
