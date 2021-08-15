package es.us.isa.jsoninstrumenter.pojos;

import io.swagger.v3.oas.models.media.ArraySchema;
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
    public static DeclsVariable getListOfDeclsVariables(String packageName, String objectName, String rootVariableName, List<Parameter> parameters) {
        // Father parameter
        // TODO: Reconsider the dec-type (main.Input) (Change to String or hashcode?)
        DeclsVariable father = new DeclsVariable(rootVariableName, "variable", packageName + "." +
                objectName, "java.lang.String", null);

        List<DeclsVariable> enclosedVariables = new ArrayList<>();
        for(Parameter parameter: parameters) {
            DeclsVariable declsVariable = new DeclsVariable(rootVariableName + "."+ parameter.getName(),
                    "field " + parameter.getName(), translateDatatype(parameter.getSchema().getType()),
                    translateDatatype(parameter.getSchema().getType()), father.getVariableName());
            enclosedVariables.add(declsVariable);
        }

        father.setEnclosedVariables(enclosedVariables);
        return father;

    }

    // Used for both output and exit
    // Creates the father variable
    public static List<DeclsVariable> generateDeclsVariablesOfOutput(String variableName, String varKind, String packageName, String variableNameOutput, Schema mapOfProperties) {
        // TODO: Set decType and repType
        // TODO: Reconsider the dec-type (main.Input) (Change to String or hashcode?)
        DeclsVariable father = new DeclsVariable(variableName, varKind, packageName + "." + variableNameOutput, "java.lang.String", null);

        // Creates the son variables
        List<DeclsVariable> enclosedVars = generateDeclsVariablesOfOutput(mapOfProperties, variableName, varKind);
        father.setEnclosedVariables(enclosedVars);

        return Collections.singletonList(father);
    }

    // TODO: Add father variable
    // TODO: Use res as a parameter? (or res.addAll)
    // TODO: Split into several functions to ease maintenance
    // Recursive method
    public static List<DeclsVariable> generateDeclsVariablesOfOutput(Schema mapOfProperties, String variablePath, String varKind) {
        List<DeclsVariable> res = new ArrayList<>();

//        Map<String, Schema> map = mapOfProperties.getProperties();
        Set<String> parameterNames = mapOfProperties.getProperties().keySet();

        for(String parameterName: parameterNames) {
            Schema schema = (Schema) mapOfProperties.getProperties().get(parameterName);
            String parameterType = schema.getType();

            // TODO: Consider array
            if(parameterType.equalsIgnoreCase("object")) {
                // TODO: change dec-type and rec-type
                // TODO: "Increment" the parameter name
                // TODO: Use the entry as input
                DeclsVariable declsVariable = new DeclsVariable(variablePath + "." + parameterName, varKind, "java.lang.String", "java.lang.String", variablePath);

                // Recursive call
                // TODO: UPDATE and Complete parameterName as new father (this.x.y ,etc.)
                List<DeclsVariable> enclosedVariables =
                        generateDeclsVariablesOfOutput(schema,
                                variablePath + "." + parameterName, varKind);

                // Set enclosed variables
                declsVariable.setEnclosedVariables(enclosedVariables);

                // Add to list
                res.add(declsVariable);

            } else if(parameterType.equalsIgnoreCase("array")) {
                // TODO: Array of enums
                // TODO: Array inside array
                ArraySchema arraySchema = (ArraySchema) mapOfProperties.getProperties().get(parameterName);
                String itemsDatatype = arraySchema.getItems().getType();

                // TODO: Three possible situations:
                if(itemsDatatype.equalsIgnoreCase("object")) { // TODO: 1. The content is of type OBJECT (recursive call) (It will be necessary to create a new class)
                    // TODO: Object Recursive call
                } else if(itemsDatatype.equalsIgnoreCase("array")) { // TODO: 2. The content is another ARRAY (recursive call) [][]
                    // TODO: Recursive call
                } else {    // 3. The content is a primitive type (base case)
                    String translatedDatatype = translateDatatype(itemsDatatype) + "[]";
                    DeclsVariable declsVariable = new DeclsVariable(variablePath + "." + parameterName, "field" + parameterName,
                            translatedDatatype, translatedDatatype, variablePath);
                    res.add(declsVariable);
                }

            } else {

                // Create new variable
                DeclsVariable declsVariable = new DeclsVariable(variablePath + "." + parameterName, "field " + parameterName,
                        translateDatatype(parameterType), translateDatatype(parameterType), variablePath);

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
