package es.us.isa.jsoninstrumenter.pojos;

import io.swagger.v3.oas.models.parameters.Parameter;

import java.util.List;

import static es.us.isa.jsoninstrumenter.pojos.DeclsVariable.getListOfDeclsVariables;

public class DeclsExit {

    private String exitName;
    private int exitNumber;
    private DeclsVariable inputDeclsVariable;
    private DeclsVariable outputDeclsVariable;


    public DeclsExit(String packageName, String endpoint, String operationName, String variableName, String rootVariableName, int exitNumber, List<Parameter> parameters) {
        // TODO: convert exit name to function (same as enterName)
        String exitName = packageName + "." + endpoint + "." + operationName + "(" + packageName + "." + variableName + ")";
        this.exitName = exitName;
        this.exitNumber = exitNumber;
        this.inputDeclsVariable = getListOfDeclsVariables(packageName, variableName, rootVariableName, parameters);
    }

    public String getExitName() {
        return exitName;
    }

    public void setExitName(String exitName) {
        this.exitName = exitName;
    }

    public int getExitNumber() {
        return exitNumber;
    }

    public void setExitNumber(int exitNumber) {
        this.exitNumber = exitNumber;
    }

    public DeclsVariable getInputDeclsVariable() {
        return inputDeclsVariable;
    }

    public void setInputDeclsVariable(DeclsVariable inputDeclsVariable) {
        this.inputDeclsVariable = inputDeclsVariable;
    }

    public DeclsVariable getOutputDeclsVariable() {
        return outputDeclsVariable;
    }

    public void setOutputDeclsVariable(DeclsVariable outputDeclsVariable) {
        this.outputDeclsVariable = outputDeclsVariable;
    }

    // TODO: Consider changing the type from subexit to subexit in certain cases
    public String toString() {
        String res = "ppt " + exitName + ":::EXIT" + exitNumber + "\n" +
                "ppt-type subexit";


        res = res + "\n" + inputDeclsVariable;
        res = res + "\n" + outputDeclsVariable;


        return res;
    }

}
