package es.us.isa.jsoninstrumenter.pojos;

import io.swagger.v3.oas.models.media.ArraySchema;
import io.swagger.v3.oas.models.media.Schema;
import io.swagger.v3.oas.models.parameters.Parameter;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.util.*;

import static es.us.isa.jsoninstrumenter.main.GenerateDeclsFile.packageName;
import static es.us.isa.jsoninstrumenter.main.GenerateDeclsFile.primitiveTypes;
import static es.us.isa.jsoninstrumenter.util.JSONManager.stringToJson;

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
    public static List<DeclsVariable> generateDeclsVariablesOfOutput(String variableName, String varKind, String packageName,
                                                                     String variableNameOutput, Schema mapOfProperties) {
        // TODO: Set decType and repType
        // TODO: Reconsider the dec-type (main.Input) (Change to String or hashcode?)
        DeclsVariable father = new DeclsVariable(variableName, varKind, packageName + "." + variableNameOutput, "java.lang.String", null);

        // Creates the son variables
        List<DeclsVariable> enclosedVars = generateDeclsVariablesOfOutput(mapOfProperties, variableName, varKind, false, 1);
        father.setEnclosedVariables(enclosedVars);

        return Collections.singletonList(father);
    }

    // TODO: Use res as a parameter? (or res.addAll)
    // TODO: Split into several functions to ease maintenance
    // Recursive method
    public static List<DeclsVariable> generateDeclsVariablesOfOutput(Schema mapOfProperties, String variablePath,
                                                                     String varKind, boolean isArray, int nestingLevel) {
        List<DeclsVariable> res = new ArrayList<>();
        Set<String> parameterNames = mapOfProperties.getProperties().keySet();

        for(String parameterName: parameterNames) {
            Schema schema = (Schema) mapOfProperties.getProperties().get(parameterName);
            String parameterType = schema.getType();

            if(parameterType.equalsIgnoreCase("object")) {
                // TODO: change dec-type and rec-type
                // TODO: "Increment" the parameter name
                // TODO: Use the entry as input
                // Generate the father variable
                DeclsVariable declsVariable = new DeclsVariable(variablePath + "." + parameterName, varKind,
                        "java.lang.String", "java.lang.String", variablePath, isArray);

                // Recursive call for son variables
                List<DeclsVariable> enclosedVariables =
                        generateDeclsVariablesOfOutput(schema, variablePath + "." + parameterName, varKind, false, nestingLevel);
                // Set enclosed variables
                declsVariable.setEnclosedVariables(enclosedVariables);
                // Add to list
                res.add(declsVariable);

            } else if(parameterType.equalsIgnoreCase("array")) {

                // Obtain variables of nested arrays
                List<DeclsVariable> declsVariables = getDeclsVariablesOfNestedArray(mapOfProperties, variablePath,
                        varKind,  parameterName, nestingLevel);
                // Add to list
                res.addAll(declsVariables);

            } else {

                // Create new variable
                DeclsVariable declsVariable = new DeclsVariable(variablePath + "." + parameterName,"field " + parameterName,
                        translateDatatype(parameterType), translateDatatype(parameterType), variablePath, isArray);
                // Add to list
                res.add(declsVariable);
            }

        }
        return res;
    }

    public static List<DeclsVariable> getDeclsVariablesOfNestedArray(Schema mapOfProperties , String variablePath,
                                                                     String varKind, String parameterName, int nestingLevel) {

        List<DeclsVariable> res = new ArrayList<>();

        // TODO: Array of type enum
        ArraySchema arraySchema = (ArraySchema) mapOfProperties.getProperties().get(parameterName);
        String itemsDatatype = arraySchema.getItems().getType();

        // Three possible situations:
        if(itemsDatatype.equalsIgnoreCase("object")) { // 1. The content is of type OBJECT (recursive call) (It will be necessary to create a new class)

            // Generate the father variable
            // TODO: Create a new class with the created object
            // TODO: Create a jUnit test for creating new classes
            // Consider changing the repType
            List<DeclsVariable> declsVariables = getDeclsVariablesOfNestedObject(variablePath, parameterName, varKind,
                    packageName + "." + parameterName, "java.lang.String", nestingLevel, arraySchema.getItems());

            // Add to list
            res.addAll(declsVariables);

        } else if(itemsDatatype.equalsIgnoreCase("array")) { // 2. The content is another ARRAY (recursive call) [][]
            // TODO: Adapt to change observed in Spotify (i.e., there are no primitive variables inside an array)
            List<DeclsVariable> declsVariableList = getDeclsVariablesOfRecursiveArray(variablePath, varKind, parameterName, arraySchema, nestingLevel);
            // Add to list
            res.addAll(declsVariableList);

        } else {    // 3. The content is a primitive type (base case)
            String translatedDatatype = translateDatatype(itemsDatatype);
            List<DeclsVariable> declsVariablesArrays = getDeclsVariablesArray(variablePath, parameterName,
                    translatedDatatype, translatedDatatype, nestingLevel);

            res.addAll(declsVariablesArrays);
        }
        return res;
    }

    public static List<DeclsVariable> getDeclsVariablesOfRecursiveArray(String variablePath, String varKind, String parameterName,
                                                                        ArraySchema arraySchema, int nestingLevel) {
        List<DeclsVariable> res = new ArrayList<>();
        // TODO: Recursive call
        // TODO: Check the number of []s

        Schema subArraySchema = ((ArraySchema) arraySchema.getItems()).getItems();
        if(subArraySchema.getType().equals("object")) { // 1. Type object (Recursive call with increased [])

            List<DeclsVariable> declsVariables = getDeclsVariablesOfNestedObject(variablePath, parameterName, varKind,
                    packageName + "." + parameterName, "java.lang.String", nestingLevel + 1, subArraySchema);

            // Add to list
            res.addAll(declsVariables);

        } else if(subArraySchema.getType().equals("array")) { // 2. Array (Repeat iteratively)
            int newNestingLevel = nestingLevel + 1;
            Schema recursiveArraySchema = subArraySchema;
            while(recursiveArraySchema.getType().equals("array")) {
                newNestingLevel++;
                recursiveArraySchema = ((ArraySchema) recursiveArraySchema).getItems();
            }
            if(recursiveArraySchema.getType().equals("object")) {

                List<DeclsVariable> declsVariables = getDeclsVariablesOfNestedObject(variablePath, parameterName, varKind,
                        packageName + "." + parameterName, "java.lang.String", newNestingLevel, recursiveArraySchema);

                // Add to list
                res.addAll(declsVariables);
            } else {
                String translatedDatatype = translateDatatype(recursiveArraySchema.getType());
                List<DeclsVariable> declsVariablesArrays = getDeclsVariablesArray(variablePath, parameterName,
                        translatedDatatype, translatedDatatype, newNestingLevel);
                res.addAll(declsVariablesArrays);
            }

        } else {    // 3. Primitive type (Base case)
            String translatedDatatype = translateDatatype(subArraySchema.getType());
            List<DeclsVariable> declsVariablesArrays = getDeclsVariablesArray(variablePath, parameterName,
                    translatedDatatype, translatedDatatype, nestingLevel + 1);
            res.addAll(declsVariablesArrays);

        }

        return res;

    }

    public static List<DeclsVariable> getDeclsVariablesOfNestedObject(String variablePath, String parameterName, String varKind, String decType, String repType, int nestingLevel,
                                                                      Schema schema) {

        List<DeclsVariable> declsVariables = getDeclsVariablesArray(variablePath, parameterName,
                decType, repType, nestingLevel);

        // Recursive call for son variables
        // Iterate over items
        // TODO: Correct the bug in var-kind (new objects)
        List<DeclsVariable> enclosedVariables = generateDeclsVariablesOfOutput(schema,
                variablePath + "." + parameterName + "[..]", varKind, true, nestingLevel);

        // Set the son variables and add to res
        declsVariables.get(1).setEnclosedVariables(enclosedVariables);

        return declsVariables;

    }


    // Converts the datatype name from OAS to daikon
    // Returns String by default
    // TODO: boolean, array, object
    public static String translateDatatype(String input) {

        switch (input.toLowerCase()) {
            case "number":
                return "double";
            case "integer":
                return "int";
            case "boolean":
                return "boolean";
            default:    // Including case "string"
                return "java.lang.String";
        }

    }

    public static List<DeclsVariable> getDeclsVariablesArray(String variablePath, String parameterName,
                                                             String decType, String repType, int nestingLevel) {
        List<DeclsVariable> res = new ArrayList<>();

        String arrayIndicator = new String(new char[nestingLevel]).replace("\0", "[]");

        String variableName = variablePath + "." + parameterName;
        // The enclosing var does not contain the name of the variable (this)
        // TODO: The rep type used to contain the array indicator too (changed when testing Spotify), Chicory uses hashcode for repType
        res.add(new DeclsVariable(variableName, "field " + parameterName, decType + arrayIndicator, repType, variablePath));

        // TODO: Check whether I should include []s in dectype and reptype (Inconsistency in docs)
        // The enclosing var name contains the name of the variable (this.array)
        // TODO: The rep type DID NOT used to contain the array indicator too (changed when testing Spotify), Chicory uses hashcode for repType
        res.add(new DeclsVariable(variableName + "[..]", "array", decType + arrayIndicator, repType  + arrayIndicator, variableName));

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

        if(isArray) {
            this.decType = decType + "[]";
            this.repType = repType + "[]";
        } else {
            this.decType = decType;
            this.repType = repType;
        }
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


    private static String getValueOfParameterForDtraceFile(TestCase testCase, String variableName, String decType, String repType) {
        Map<String, String> queryParametersValues = testCase.getQueryParameters();
        Map<String, String> pathParametersValues = testCase.getPathParameters();
        Map<String, String> headerParametersValues = testCase.getHeaderParameters();
        Map<String, String> formParametersValues = testCase.getFormParameters();

        String value = null;
        // TODO: Consider arrays
        // TODO: Consider path, header and form variables
        if(primitiveTypes.contains(decType)) { // If primitive value

            // Get the variable name (Without Wrapping)
            List<String> hierarchy = Arrays.asList(variableName.split("\\."));
            if(hierarchy.size() > 1) {
                String key = hierarchy.get(hierarchy.size()-1);
                value = queryParametersValues.get(key);
                if(value == null) {
                    value = pathParametersValues.get(key);
                }
                if(value == null) {
                    value = headerParametersValues.get(key);
                }
                if(value == null) {
                    value = formParametersValues.get(key);
                }
            } else {
                value = variableName;
            }

            if(repType.equals("java.lang.String") && value != null) {
                value = "\"" + value + "\"";
            }
        } else {    // If type = object
            value = "\"" + testCase.getTestCaseId() + "_" + variableName +  "_input" + "\"";
        }

        return value;
    }

    public String generateDtraceEnter(TestCase testCase) {
        // TODO: Consider arrays

        // Father variable
        String value = getValueOfParameterForDtraceFile(testCase, this.variableName, this.decType, this.repType);

        String res = this.variableName + "\n" +
                value + "\n" + "1";

        // Son variables
        for(DeclsVariable declsVariable: this.getEnclosedVariables()) {
            res = res + "\n" + declsVariable.generateDtraceEnter(testCase);
        }

        return res;
    }

    public String generateDtraceExit(TestCase testCase, JSONObject json, Boolean isElementOfArray) {
        // TODO: Consider arrays
        // TODO: Consider objects
        // TODO: Consider path, header and form variables
        // TODO: Check all datatypes (boolean, string, int, double, object)

//        JSONObject json = stringToJson(testCase.getResponseBody());

        String value = null;

        if(     // See Spotify example
                (primitiveTypes.contains(this.decType) || primitiveTypes.contains(this.decType.replace("[]", ""))) && !varKind.equals("array")
        ) { // If primitive type
            // Get the variable name (Withut wrapping)
            List<String> hierarchy = Arrays.asList(this.variableName.split("\\."));
            String key = hierarchy.get(hierarchy.size()-1);
            if(hierarchy.size() > 1) {
                value = String.valueOf(json.get(key));
            } else {
                value = variableName;
            }

            if(repType.replace("[]", "").equals("java.lang.String") && value != null) {
                value = "\"" + value + "\"";
            }

            if(isElementOfArray) {
                value = "[" + value + "]";
            }

        } else if (varKind.equals("array")) {       // If array TODO: Consider recursivity (Primitive, object and another array)
            // TODO: factor común
            List<String> hierarchy = Arrays.asList(this.variableName.replace("[..]", "").split("\\."));
            String key = hierarchy.get(hierarchy.size()-1);
            JSONArray elements = (JSONArray) json.get(key);

            String hashcode = "";   // TODO: Refactor
            for(int i = 1; i <= elements.size(); i++) {
                hashcode = hashcode + "\"" + testCase.getTestCaseId() + "_" + this.variableName.replace("[..]", "") + "_output_" + i + "\"" + " ";
            }

            // TODO: Nesting
            value = "[" + hashcode.trim() + "]";

        } else {    // If type = object
            value = "\"" + testCase.getTestCaseId() + "_" + this.variableName + "_output" + "\"";
            if(isElementOfArray) {
                value = "[" + value + "]";
            }
        }

        String res = this.variableName + "\n" +
                value + "\n" + "1";

        // Son variables
        for(DeclsVariable declsVariable: this.getEnclosedVariables()) {
            if(varKind.equals("array")) {
                // TODO: Factor común
                List<String> hierarchy = Arrays.asList(this.variableName.replace("[..]", "").split("\\."));
                String key = hierarchy.get(hierarchy.size()-1);
                JSONArray elements = (JSONArray) json.get(key);

                for(int i=0; i<elements.size(); i++) {
                    JSONObject element = (JSONObject) elements.get(0);
                    res = res + "\n" + declsVariable.generateDtraceExit(testCase, element, true);
                }


            } else {    // The element was an object or primitive
                res = res + "\n" + declsVariable.generateDtraceExit(testCase, json, false);
            }
        }

        return res;
    }

}
