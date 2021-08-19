package es.us.isa.jsoninstrumenter.pojos;

import io.swagger.v3.oas.models.media.Schema;

import java.lang.reflect.Parameter;
import java.util.List;

import static es.us.isa.jsoninstrumenter.pojos.DeclsVariable.*;

public class DeclsExit {

//    private String exitName;
    private String packageName;
    private String endpoint;
    private String operationName;
    private String variableNameInput;
    private int exitNumber;
    private List<DeclsVariable> enterDeclsVariables;
    private List<DeclsVariable> exitDeclsVariables;


    public DeclsExit(String packageName, String endpoint, String operationName, String variableNameInput,
                     List<DeclsVariable> enterVariables, String variableNameOutput, Schema mapOfProperties, int exitNumber) {
        this.packageName = packageName;
        this.endpoint = endpoint;
        this.operationName = operationName;
        this.variableNameInput = variableNameInput;

        this.exitNumber = exitNumber;
        this.enterDeclsVariables = enterVariables;

        // TODO: Change singleton to normal list (a method can have several exits)
        // TODO: for loop increasing exitNumber
        this.exitDeclsVariables = generateDeclsVariablesOfOutput("return", "return", packageName,  variableNameOutput, mapOfProperties);
    }

    public String getExitName() {
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

    public int getExitNumber() {
        return exitNumber;
    }

    public void setExitNumber(int exitNumber) {
        this.exitNumber = exitNumber;
    }

    public List<DeclsVariable> getEnterDeclsVariables() {
        return enterDeclsVariables;
    }

    public void setEnterDeclsVariables(List<DeclsVariable> enterDeclsVariables) {
        this.enterDeclsVariables = enterDeclsVariables;
    }

    public List<DeclsVariable> getExitDeclsVariables() {
        return exitDeclsVariables;
    }

    public void setExitDeclsVariables(List<DeclsVariable> exitDeclsVariables) {
        this.exitDeclsVariables = exitDeclsVariables;
    }

    // TODO: Consider changing the type from subexit to exit in certain cases
    public String toString() {
        String res = "ppt " + this.getExitName() + ":::EXIT" + exitNumber + "\n" +
                "ppt-type subexit";

        for(DeclsVariable enterDeclsVariable: this.enterDeclsVariables) {
            res = res + "\n" + enterDeclsVariable;
        }

        for(DeclsVariable exitDeclsVariable: this.exitDeclsVariables) {
            res = res + "\n" + exitDeclsVariable;
        }

        return res;
    }

}
