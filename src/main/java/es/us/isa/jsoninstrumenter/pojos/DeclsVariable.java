package es.us.isa.jsoninstrumenter.pojos;

import io.swagger.v3.oas.models.media.Schema;
import io.swagger.v3.oas.models.parameters.Parameter;

import java.util.*;
import java.util.Map.Entry;

public class DeclsVariable {

    private String variableName;
    private String varKind;
    private String decType;
    private String repType;
    private String enclosingVar;
    private List<DeclsVariable> enclosedVariables;

    // TODO: Change to simple constructor
    // TODO: Array, boolean and object
    public static List<DeclsVariable> getListOfDeclsVariables(String packageName, String objectName, List<Parameter> parameters) {
        // Father parameter
        // TODO: Reconsider the dec-type (main.Input) (Change to String or hashcode?)
        DeclsVariable father = new DeclsVariable("this", "variable", packageName + "." + objectName, "java.lang.String", null);

        List<DeclsVariable> enclosedVariables = new ArrayList<>();
        for(Parameter parameter: parameters) {
            DeclsVariable declsVariable = new DeclsVariable("this."+ parameter.getName(), "field " + parameter.getName(), translateDatatype(parameter.getSchema().getType()), translateDatatype(parameter.getSchema().getType()), father.getVariableName());
            enclosedVariables.add(declsVariable);
        }

        father.setEnclosedVariables(enclosedVariables);
        return Collections.singletonList(father);

    }

    public static List<DeclsVariable> generateDeclsVariablesOfOutput(String packageName, String objectName, Map<String, Schema> mapOfProperties) {
        // TODO: Set decType and repType
        DeclsVariable father = new DeclsVariable("this", "variable", packageName + "." + objectName, "java.lang.String", null);

        List<DeclsVariable> enclosedVars = generateDeclsVariablesOfOutput(mapOfProperties, "this");
        father.setEnclosedVariables(enclosedVars);

        return Collections.singletonList(father);
    }

    // TODO: Add father variable
    // TODO: Use res as a parameter? (or res.addAll)
    public static List<DeclsVariable> generateDeclsVariablesOfOutput(Map<String, Schema> mapOfProperties, String variablePath) {
        List<DeclsVariable> res = new ArrayList<>();

        for(Entry<String, Schema> property: mapOfProperties.entrySet()) {
            String parameterName = property.getKey();

            // TODO: Consider array
            if(property.getValue().getType().equalsIgnoreCase("object")) { // TODO: Recursive call
                // TODO: change dec-type and rec-type
                // TODO: "Increment" the parameter name
                // TODO: Use the entry as input
//                public DeclsVariable(String variableName, String varKind, String decType, String repType, String enclosingVar)
//                DeclsVariable father = new DeclsVariable("this", "variable", packageName + "." + objectName, "java.lang.String", null);
                DeclsVariable declsVariable = new DeclsVariable(variablePath + "." + parameterName, "variable", "java.lang.String", "java.lang.String", variablePath);

                // Recursive call
                // TODO: UPDATE and Complete parameterName as new father (this.x.y ,etc.)
                List<DeclsVariable> enclosedVariables = generateDeclsVariablesOfOutput(property.getValue().getProperties(), variablePath + "." + parameterName);

                // Set enclosed variables
                declsVariable.setEnclosedVariables(enclosedVariables);

                // Add to list
                res.add(declsVariable);


            } else { // TODO: Other datatype (base case)
                // Create new variable
                // TODO: Name extension (Include as parameter) (this.x.y ,etc.)
                String datatype = property.getValue().getType();
                DeclsVariable declsVariable = new DeclsVariable(variablePath + "." + parameterName, "field " + parameterName,
                        translateDatatype(datatype), translateDatatype(datatype), variablePath);

                // Add to list
                res.add(declsVariable);


            }

        }

        return res;


    }

    // Converts the datatype name from OAS to daikon
    // Returns String by default
    // TODO: boolean, array, object
    public static String translateDatatype(String input) {

        switch (input.toLowerCase()) {
            case "string":
                return "java.lang.String";
            case "number":
                return "double";
            case "integer":
                return "int";
            case "boolean":
                return "boolean";
            default:
                return "java.lang.String";
        }

    }

    public DeclsVariable(String variableName, String varKind, String decType, String repType, String enclosingVar) {
        this.variableName = variableName;
        this.varKind = varKind;
        this.decType = decType;
        this.repType = repType;
        this.enclosingVar = enclosingVar;
        this.enclosedVariables = new ArrayList<>();
    }

    public String getVariableName() { return variableName; }

    public void setVariableName(String variableName) {
        this.variableName = variableName;
    }

    public String getVarKind() {
        return varKind;
    }

    public void setVarKind(String varKind) {
        this.varKind = varKind;
    }

    public String getDecType() {
        return decType;
    }

    public void setDecType(String decType) {
        this.decType = decType;
    }

    public String getRepType() {
        return repType;
    }

    public void setRepType(String repType) {
        this.repType = repType;
    }

    public String getEnclosingVar() {
        return enclosingVar;
    }

    public void setEnclosingVar(String enclosingVar) {
        this.enclosingVar = enclosingVar;
    }

    public List<DeclsVariable> getEnclosedVariables() {
        return enclosedVariables;
    }

    public void setEnclosedVariables(List<DeclsVariable> enclosedVariables) {
        this.enclosedVariables = enclosedVariables;
    }

    public String toString() {
        String enclosingVarString;

        if(enclosingVar == null) {
            enclosingVarString = "";
        } else {
            enclosingVarString = "\t" + "enclosing-var " + enclosingVar + "\n";
        }

        String res = "variable " + variableName + "\n" +
                "\t" + "var-kind " + varKind + "\n" +
                enclosingVarString +
                "\t" + "dec-type " + decType + "\n" +
                "\t" + "rep-type " + repType;

        for(DeclsVariable declsVariable: enclosedVariables) {
            res = res + "\n" + declsVariable;
        }

        return res;
    }
}
