package es.us.isa.jsoninstrumenter.pojos;

import io.swagger.v3.oas.models.media.ArraySchema;
import io.swagger.v3.oas.models.media.ObjectSchema;
import io.swagger.v3.oas.models.media.Schema;
import io.swagger.v3.oas.models.parameters.Parameter;

import java.util.*;
import java.util.Map.Entry;

import static es.us.isa.jsoninstrumenter.main.GenerateDeclsFile.packageName;
import static es.us.isa.jsoninstrumenter.pojos.DeclsClass.generateOutputDeclsClasses;

public class DeclsVariable {

    private String variableName;
    private String varKind;
    private String decType;
    private String repType;
    private String enclosingVar;
    private boolean isArray;
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
        List<DeclsVariable> enclosedVars = generateDeclsVariablesOfOutput(mapOfProperties, variableName, varKind, false);
        father.setEnclosedVariables(enclosedVars);

        return Collections.singletonList(father);
    }

    // TODO: Add father variable
    // TODO: Use res as a parameter? (or res.addAll)
    // TODO: Split into several functions to ease maintenance
    // Recursive method
    public static List<DeclsVariable> generateDeclsVariablesOfOutput(Schema mapOfProperties, String variablePath, String varKind, boolean isArray) {
        List<DeclsVariable> res = new ArrayList<>();

//        Map<String, Schema> map = mapOfProperties.getProperties();
        Set<String> parameterNames = mapOfProperties.getProperties().keySet();

        for(String parameterName: parameterNames) {
            Schema schema = (Schema) mapOfProperties.getProperties().get(parameterName);
            String parameterType = schema.getType();

            // TODO: Consider array
            if(parameterType == null) {
                System.out.println("Create breakpoint");
            }
            if(parameterType.equalsIgnoreCase("object")) {
                // TODO: change dec-type and rec-type
                // TODO: "Increment" the parameter name
                // TODO: Use the entry as input
                // Generate the father variable
                DeclsVariable declsVariable = new DeclsVariable(variablePath + "." + parameterName, varKind, "java.lang.String", "java.lang.String", variablePath, isArray);

                // Recursive call for son variables
                List<DeclsVariable> enclosedVariables =
                        generateDeclsVariablesOfOutput(schema,
                                variablePath + "." + parameterName, varKind, false);

                // Set enclosed variables
                declsVariable.setEnclosedVariables(enclosedVariables);

                // Add to list
                res.add(declsVariable);

            } else if(parameterType.equalsIgnoreCase("array")) {
                // TODO: Create jUnits for every possible situation
                // TODO: Array of enums
                ArraySchema arraySchema = (ArraySchema) mapOfProperties.getProperties().get(parameterName);
                String itemsDatatype = arraySchema.getItems().getType();

                // TODO: Three possible situations:
                if(itemsDatatype.equalsIgnoreCase("object")) { // 1. The content is of type OBJECT (recursive call) (It will be necessary to create a new class)
                    // TODO: Create a new class with an object
                    // TODO: Object Recursive call
                    // TODO: All the objects have nesting of []s

                    // Generate the father variable
                    // TODO: Create a new class with the created object
                    // TODO: Create a jUnit test for creating new classes
                    // Consider changing the repType

                    List<DeclsVariable> declsVariables = getDeclsVariablesArray(variablePath, parameterName,
                            packageName + "." + parameterName, "java.lang.String");

                    // Recursive call for son variables
                    // Iterate over items
                    // TODO: Correct the bug in var-kind (new objects)
                    List<DeclsVariable> enclosedVariables = generateDeclsVariablesOfOutput(arraySchema.getItems(),
                                    variablePath + "." + parameterName + "[..]", varKind, true);

                    // Set the son variables and add to res
                    declsVariables.get(1).setEnclosedVariables(enclosedVariables);

                    // Add to list
                    res.addAll(declsVariables);


                } else if(itemsDatatype.equalsIgnoreCase("array")) { // TODO: 2. The content is another ARRAY (recursive call) [][]
                    // TODO: Recursive call
                    // TODO: Check the number of []s

                } else {    // 3. The content is a primitive type (base case)
                    String translatedDatatype = translateDatatype(itemsDatatype);
                    List<DeclsVariable> declsVariablesArrays = getDeclsVariablesArray(variablePath, parameterName,
                            translatedDatatype, translatedDatatype);

                    res.addAll(declsVariablesArrays);
                }

            } else {

                // Create new variable
                DeclsVariable declsVariable = new DeclsVariable(variablePath + "." + parameterName, "field " + parameterName,
                        translateDatatype(parameterType), translateDatatype(parameterType), variablePath, isArray);
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

    public static List<DeclsVariable> getDeclsVariablesArray(String variablePath, String parameterName, String decType, String repType) {
        List<DeclsVariable> res = new ArrayList<>();

        String variableName = variablePath + "." + parameterName;
        // The enclosing var does not contain the name of the variable (this)
        res.add(new DeclsVariable(variableName, "field " + parameterName, decType + "[]", repType + "[]", variablePath));

        // TODO: Check whether I should include []s in dectype and reptype (Inconsistency in docs)
        // The enclosing var name contains the name of the variable (this.array)
        res.add(new DeclsVariable(variableName + "[..]", "array", decType, repType, variableName));

        return res;
    }

    public DeclsVariable(String variableName, String varKind, String decType, String repType, String enclosingVar) {
        this.variableName = variableName;
        this.varKind = varKind;
        this.decType = decType;
        this.repType = repType;
        this.enclosingVar = enclosingVar;
        this.enclosedVariables = new ArrayList<>();
        this.isArray = varKind.equals("array");
    }

    // Constructor with flags
    public DeclsVariable(String variableName, String varKind, String decType, String repType, String enclosingVar, boolean isArray) {
        this.variableName = variableName;
        this.varKind = varKind;
        this.decType = decType;
        this.repType = repType;
        this.enclosingVar = enclosingVar;
        this.enclosedVariables = new ArrayList<>();
        this.isArray = isArray;
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

    public boolean isArray() {
        return isArray;
    }

    public void setArray(boolean array) {
        isArray = array;
    }

    public String toString() {

        // Enclosing-var
        String enclosingVarString;
        if(enclosingVar == null) {
            enclosingVarString = "";
        } else {
            enclosingVarString = "\t" + "enclosing-var " + enclosingVar + "\n";
        }

        // If varkind = array, add array 1
        String array;
        if(varKind.equals("array") || this.isArray){
            array = "\t" + "array 1" + "\n";
        } else {
            array = "";
        }

        String res = "variable " + variableName + "\n" +
                "\t" + "var-kind " + varKind + "\n" +
                enclosingVarString +
                array +
                "\t" + "dec-type " + decType + "\n" +
                "\t" + "rep-type " + repType;

        for(DeclsVariable declsVariable: enclosedVariables) {
            res = res + "\n" + declsVariable;
        }

        return res;
    }
}
