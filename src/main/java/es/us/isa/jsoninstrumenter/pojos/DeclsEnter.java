package es.us.isa.jsoninstrumenter.pojos;

import io.swagger.v3.oas.models.parameters.Parameter;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import static es.us.isa.jsoninstrumenter.pojos.DeclsVariable.getListOfDeclsVariables;

public class DeclsEnter {

    private String packageName;
    private String endpoint;
    private String operationName;
    private String variableNameInput;

    private List<DeclsVariable> declsVariables;

    // TODO: Add flag is_param? (See example in demo001)
    public DeclsEnter(String packageName, String endpoint,
                      String operationName, String variableNameInput,
                      List<Parameter> parameters, String rootVariableName) {
        this.packageName = packageName;
        this.endpoint = endpoint;
        this.operationName = operationName;
        this.variableNameInput = variableNameInput;
        this.declsVariables = Collections.singletonList(getListOfDeclsVariables(packageName, operationName + "_" + variableNameInput, rootVariableName, parameters));
    }

    public String getEnterName() {
        return this.packageName + "." + this.endpoint + "." + this.operationName + "(" +
                this.packageName + "." + this.operationName + "_" + this.variableNameInput + ")";
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

    public List<DeclsVariable> getDeclsVariables() {
        return declsVariables;
    }

    public void setDeclsVariables(List<DeclsVariable> declsVariables) {
        this.declsVariables = declsVariables;
    }

    public String toString() {
        String res = "ppt " + this.getEnterName() + ":::ENTER" + "\n" +
                "ppt-type enter";

        for(DeclsVariable declsVariable: declsVariables) {
            res = res + "\n" + declsVariable;
        }

        return res;
    }

    public String generateDtrace(TestCase testCase) {

        String res = this.getEnterName() + ":::ENTER";

        for(DeclsVariable declsVariable: declsVariables) {
            res = res + "\n" + declsVariable.generateDtraceEnter(testCase);
        }

        res = res + "\n";


        return res;
    }

}
