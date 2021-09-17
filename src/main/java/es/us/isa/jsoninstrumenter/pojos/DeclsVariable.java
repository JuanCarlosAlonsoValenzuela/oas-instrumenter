package es.us.isa.jsoninstrumenter.pojos;

import io.swagger.v3.oas.models.media.ArraySchema;
import io.swagger.v3.oas.models.media.Schema;
import io.swagger.v3.oas.models.parameters.Parameter;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.*;

import static es.us.isa.jsoninstrumenter.main.GenerateDeclsFile.packageName;
import static es.us.isa.jsoninstrumenter.main.GenerateDeclsFile.primitiveTypes;

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
    public static DeclsVariable generateDeclsVariablesOfOutput(String variableName, String varKind, String packageName,
                                                                     String variableNameOutput, Schema mapOfProperties) {
        // TODO: Set decType and repType
        // TODO: Reconsider the dec-type (main.Input) (Change to String or hashcode?)
        DeclsVariable father = new DeclsVariable(variableName, varKind,
                packageName + "." + variableNameOutput, "java.lang.String", null);

        // Creates the son variables
        List<DeclsVariable> enclosedVars = generateDeclsVariablesOfOutput(mapOfProperties, variableName, varKind, variableNameOutput, false, 1);
        father.setEnclosedVariables(enclosedVars);

        return father;
    }

    // TODO: Use res as a parameter? (or res.addAll)
    // TODO: Split into several functions to ease maintenance
    // Recursive method
    public static List<DeclsVariable> generateDeclsVariablesOfOutput(Schema mapOfProperties, String variablePath,
                                                                     String varKind, String variableNameOutput,
                                                                     boolean isArray, int arrayNestingLevel) {
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
                        packageName + "." + variableNameOutput + "_" + parameterName, "java.lang.String", variablePath, isArray);

                // TODO: Create a second object
                // Recursive call for son variables
                List<DeclsVariable> enclosedVariables =
                        generateDeclsVariablesOfOutput(schema, variablePath + "." + parameterName, varKind,
                                variableNameOutput, false, arrayNestingLevel);
                // Set enclosed variables
                declsVariable.setEnclosedVariables(enclosedVariables);
                // Add to list
                res.add(declsVariable);

            } else if(parameterType.equalsIgnoreCase("array")) {

                // Obtain variables of nested arrays
                List<DeclsVariable> declsVariables = getDeclsVariablesOfNestedArray(mapOfProperties, variablePath,
                        varKind,  parameterName, arrayNestingLevel, variableNameOutput);
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
                                                                     String varKind, String parameterName, int arrayNestingLevel, String variableNameOutput) {

        List<DeclsVariable> res = new ArrayList<>();

        // TODO: Array of type enum
        ArraySchema arraySchema = (ArraySchema) mapOfProperties.getProperties().get(parameterName);
        String itemsDatatype = arraySchema.getItems().getType();

        // Three possible situations:
        if(itemsDatatype.equalsIgnoreCase("object")) { // 1. The content is of type OBJECT (recursive call) (It will be necessary to create a new class)

            // Generate the father variable
            // TODO: Create a new class with the created object
            // TODO: Create a jUnit test for creating new classes

            List<DeclsVariable> declsVariables = getDeclsVariablesArray(variablePath, parameterName,
                    packageName + "." + parameterName, "java.lang.String", arrayNestingLevel);

            res.addAll(declsVariables);

            return res;
            // TODO: Create a new object
            // Consider changing the repType
//            List<DeclsVariable> declsVariables = getDeclsVariablesOfNestedObject(variablePath, variableNameOutput, parameterName, varKind,
//                    packageName + "." + parameterName, "java.lang.String", arrayNestingLevel, arraySchema.getItems(), declsClass, isExit);

            // Add to list
//            res.addAll(declsVariables);

        } else if(itemsDatatype.equalsIgnoreCase("array")) { // 2. The content is another ARRAY (recursive call) [][]
            // TODO: Adapt to change observed in Spotify (i.e., there are no primitive variables inside an array)
            // TODO: UNCOMMENT
//            List<DeclsVariable> declsVariableList = getDeclsVariablesOfRecursiveArray(variablePath, varKind, parameterName, arraySchema, arrayNestingLevel);
            // Add to list
//            res.addAll(declsVariableList);

        } else {    // 3. The content is a primitive type (base case)
            String translatedDatatype = translateDatatype(itemsDatatype);
            List<DeclsVariable> declsVariablesArrays = getDeclsVariablesArray(variablePath, parameterName,
                    translatedDatatype, translatedDatatype, arrayNestingLevel);

            res.addAll(declsVariablesArrays);
        }
        return res;
    }

    // TODO: Uncomment and refactor
//    public static List<DeclsVariable> getDeclsVariablesOfRecursiveArray(String variablePath, String varKind, String parameterName,
//                                                                        ArraySchema arraySchema, int arrayNestingLevel) {
//        List<DeclsVariable> res = new ArrayList<>();
//        // TODO: Recursive call
//        // TODO: Check the number of []s
//
//        Schema subArraySchema = ((ArraySchema) arraySchema.getItems()).getItems();
//        if(subArraySchema.getType().equals("object")) { // 1. Type object (Recursive call with increased [])
//
//            List<DeclsVariable> declsVariables = getDeclsVariablesOfNestedObject(variablePath, parameterName, varKind,
//                    packageName + "." + parameterName, "java.lang.String", arrayNestingLevel + 1, subArraySchema);        // This method was modified in the last update
//
//            // Add to list
//            res.addAll(declsVariables);
//
//        } else if(subArraySchema.getType().equals("array")) { // 2. Array (Repeat iteratively)
//            int newNestingLevel = arrayNestingLevel + 1;
//            Schema recursiveArraySchema = subArraySchema;
//            while(recursiveArraySchema.getType().equals("array")) {
//                newNestingLevel++;
//                recursiveArraySchema = ((ArraySchema) recursiveArraySchema).getItems();
//            }
//            if(recursiveArraySchema.getType().equals("object")) {
//
//                List<DeclsVariable> declsVariables = getDeclsVariablesOfNestedObject(variablePath, parameterName, varKind,
//                        packageName + "." + parameterName, "java.lang.String", newNestingLevel, recursiveArraySchema);    // This method was modified in the last update
//
//                // Add to list
//                res.addAll(declsVariables);
//            } else {
//                String translatedDatatype = translateDatatype(recursiveArraySchema.getType());
//                List<DeclsVariable> declsVariablesArrays = getDeclsVariablesArray(variablePath, parameterName,
//                        translatedDatatype, translatedDatatype, newNestingLevel);
//                res.addAll(declsVariablesArrays);
//            }
//
//        } else {    // 3. Primitive type (Base case)
//            String translatedDatatype = translateDatatype(subArraySchema.getType());
//            List<DeclsVariable> declsVariablesArrays = getDeclsVariablesArray(variablePath, parameterName,
//                    translatedDatatype, translatedDatatype, arrayNestingLevel + 1);
//            res.addAll(declsVariablesArrays);
//
//        }
//
//        return res;
//
//    }

    public static List<DeclsVariable> getDeclsVariablesOfNestedObject(String variablePath, String variableNameOutput, String parameterName, String varKind, String decType, String repType, int arrayNestingLevel,
                                                                      Schema schema) {

        List<DeclsVariable> declsVariables = getDeclsVariablesArray(variablePath, parameterName,
                decType, repType, arrayNestingLevel);

        // Recursive call for son variables
        // Iterate over items
        // TODO: Correct the bug in var-kind (new objects)
        // TODO: Create new Object with the elements of the array


        // TODO: Remove isExit
        DeclsObject nestedDeclsObject = new DeclsObject("this",
                    variableNameOutput + "_" + parameterName,
                    schema);

//        declsClass.addDeclsObject(nestedDeclsObject);

//        System.out.println(nestedDeclsObject);

//        List<DeclsVariable> enclosedVariables = generateDeclsVariablesOfOutput(schema,
//                variablePath + "." + parameterName + "[..]", varKind, true, arrayNestingLevel);

        // Set the son variables and add to res
//        declsVariables.get(1).setEnclosedVariables(enclosedVariables);

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
                                                             String decType, String repType, int arrayNestingLevel) {
        List<DeclsVariable> res = new ArrayList<>();

        String arrayIndicator = new String(new char[arrayNestingLevel]).replace("\0", "[]");

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

        String value = null;

        if(     // If primitive type
                primitiveTypes.contains(this.decType) && !varKind.equals("array")
        ) {
            // Get the variable name (Withut wrapping)
            List<String> hierarchy = Arrays.asList(this.variableName.split("\\."));
            hierarchy = hierarchy.subList(1, hierarchy.size());
            value = getPrimitiveValueFromHierarchy(json, hierarchy);

            if(repType.replace("[]", "").equals("java.lang.String") && value != null) {
                value = "\"" + value + "\"";
            }

            if(isElementOfArray) {
                value = "[" + value + "]";
            }

        } else if (varKind.equals("array")) {       // If array TODO: Consider recursivity (Primitive, object and another array)
            List<String> hierarchy = Arrays.asList(this.variableName.replace("[..]", "").split("\\."));
            hierarchy = hierarchy.subList(1, hierarchy.size()); // Remove the class name from the hierachy TODO: Refactor to make it more intutive
//            String key = hierarchy.get(hierarchy.size()-1);
//            JSONArray elements = (JSONArray) json.get(key);
            // TODO: Convert to list of arrays (flattening)
            JSONArray elements = getArrayFromHierarchy(json, hierarchy);

            // TODO: Convert to a separate function
            if(primitiveTypes.contains(this.decType.replace("[]", ""))) { // If array of primitives
                boolean isString = false;
                if(this.decType.replace("[]", "").equals("java.lang.String")) {
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
                    hashcode = hashcode + "\"" + testCase.getTestCaseId() + "_" + this.variableName.replace("[..]", "") + "_output_" + i + "\"" + " ";
                }

                value = "[" + hashcode.trim() + "]";
            }


        } else {    // If type = object or identifier of array (both array of objects and array of primitives)
            value = "\"" + testCase.getTestCaseId() + "_" + this.variableName + "_output" + "\"";
            if(isElementOfArray) {
                value = "[" + value + "]";
            }
        }

        String res = this.variableName + "\n" +
                value + "\n" + "1";

        // Son variables        // TODO: Is this necessary (isElementOfArray should never be true)
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

    public static String getPrimitiveValueFromHierarchy(JSONObject json, List<String> hierarchy) {
        String key = hierarchy.get(0);

        if(hierarchy.size() == 1) {

            if(json.get(key) == null){
                return null;
            } else {
                return String.valueOf(json.get(key));
            }

        } else {
            Object jsonSon = json.get(key);

            if(jsonSon instanceof JSONObject) {     // If JSONObject
                JSONObject jsonSonObject = (JSONObject) jsonSon;
                return getPrimitiveValueFromHierarchy(jsonSonObject, hierarchy.subList(1, hierarchy.size()));
            } else {    // If JSONArray
                // TODO: Complete
                return null;
            }
        }

    }

    public static JSONArray getArrayFromHierarchy(JSONObject json, List<String> hierarchy) {
        String key = hierarchy.get(0);

        if(hierarchy.size() ==1){
            return (JSONArray) json.get(key);
        } else {
            Object jsonSon = json.get(key);

            if(jsonSon instanceof JSONObject) {     // If JSONObject
                JSONObject jsonSonObject = (JSONObject) jsonSon;
                return getArrayFromHierarchy(jsonSonObject, hierarchy.subList(1, hierarchy.size()));

            } else{     // If JSONArray
                JSONArray jsonSonArray = (JSONArray) jsonSon;
                // TODO: Complete this type of nesting
                return null;
            }
        }

    }

    //            String key = hierarchy.get(hierarchy.size()-1);
//            JSONArray elements = (JSONArray) json.get(key);

}
