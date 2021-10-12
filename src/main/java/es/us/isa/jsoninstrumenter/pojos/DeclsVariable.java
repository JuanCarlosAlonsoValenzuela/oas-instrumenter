package es.us.isa.jsoninstrumenter.pojos;

import io.swagger.v3.oas.models.media.*;
import io.swagger.v3.oas.models.Operation;
import io.swagger.v3.oas.models.parameters.Parameter;
import io.swagger.v3.oas.models.parameters.RequestBody;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.*;

import static es.us.isa.jsoninstrumenter.main.GenerateDeclsFile.*;
import static es.us.isa.jsoninstrumenter.util.JSONManager.stringToJsonObject;

public class DeclsVariable {

    private String variableName;
    private String varKind;
    private String decType;
    private String repType;
    private String enclosingVar;
    private boolean isArray;
    private List<DeclsVariable> enclosedVariables;

    // TODO: Array, boolean and object
    public static DeclsVariable getListOfDeclsVariables(String packageName, String objectName, String rootVariableName, Operation operation) {
        // Father parameter
        DeclsVariable father = new DeclsVariable(rootVariableName, "variable", packageName + "." +
                objectName, STRING_TYPE_NAME, null);

        List<DeclsVariable> enclosedVariables = new ArrayList<>();

        // Extract parameters from path, query and header
        List<Parameter> parameters = operation.getParameters();
        if(parameters != null) {
            for(Parameter parameter: parameters) {
                // TODO: Add try catch for null pointer exception explaining that a property of the parameter was not found
                DeclsVariable declsVariable = new DeclsVariable(rootVariableName + "."+ parameter.getName(),
                        "field " + parameter.getName(), translateDatatype(parameter.getSchema().getType()),
                        translateDatatype(parameter.getSchema().getType()), father.getVariableName());
                enclosedVariables.add(declsVariable);
            }
        }

        // Extract parameters from body
        // TODO: JSONArray and JSONObject (Otherwise throw NullPointerException)
        // TODO: anyOf and oneOf can be used to specify different Schemas
//        List<DeclsVariable> declsVariablesOfBody = getDeclsVariablesOfBodyAndFormParameters(operation, "application/json",
//                rootVariableName, objectName, "body");
//        enclosedVariables.addAll(declsVariablesOfBody);
//
//        // Extract parameters from the form
//        List<DeclsVariable> declsVariablesOfForm = getDeclsVariablesOfBodyAndFormParameters(operation, "application/x-www-form-urlencoded",
//                rootVariableName, objectName, "form");
//        enclosedVariables.addAll(declsVariablesOfForm);

        List<DeclsVariable> declsVariablesFromBodyAndForm = getDeclsVariablesOfBodyAndFormParameters(operation, rootVariableName, objectName, "body");
        enclosedVariables.addAll(declsVariablesFromBodyAndForm);

        father.setEnclosedVariables(enclosedVariables);
        return father;

    }

    // TODO: Refactor this method to delete the null checks
    // TODO: Take duplicates into account (e.g., application/json and application/xml) (Idea: extract common schemas?)
    // TODO: If this method is finally used, remove (or change) the parameter "sourceOfParameter"
    public static List<DeclsVariable> getDeclsVariablesOfBodyAndFormParameters(Operation operation,
                                                                               String rootVariableName, String objectName, String sourceOfParameter) {
        List<DeclsVariable> res = new ArrayList<>();

        RequestBody requestBody = operation.getRequestBody();
        if(requestBody != null) {
            Content content = requestBody.getContent();
            if (content != null) {
                for (String key : content.keySet()) {
                    res.addAll(getDeclsVariablesOfBodyAndFormParameters(operation, key, rootVariableName, objectName, sourceOfParameter));
                }

            }
        }

        return res;
    }

    // TODO: Move to a different class
    // TODO: Refactor this method and add a try/catch instead of several ifs
    // TODO: Primitive type
    public static List<DeclsVariable> getDeclsVariablesOfBodyAndFormParameters(Operation operation, String key,
                                                                               String rootVariableName, String objectName, String sourceOfParameter) {
        Schema schema = null;

        RequestBody requestBody = operation.getRequestBody();
        if(requestBody != null) {
            Content content = requestBody.getContent();
            if(content != null) {
                MediaType mediaType = content.get(key);
                if(mediaType != null) {
                    String schemaType = mediaType.getSchema().getType();
                    if(schemaType != null && schemaType.equals("array")) {       // The parameter is of type array
                        ArraySchema arraySchema = (ArraySchema) mediaType.getSchema();      // objectName = createPlaylist_Input    rootVariableName = this
                        return Collections.singletonList(generateDeclsVariablesOfArrayOutput(arraySchema, objectName + "." + sourceOfParameter + "Array",
                                rootVariableName + "." + sourceOfParameter, "variable", rootVariableName));
                    } else {                                                    // The body is of type object
                        schema = mediaType.getSchema();
                        if(schema.getProperties() == null) {
                            schema = null;
                        }
                    }

                }
            }
        }

        List<DeclsVariable> res = new ArrayList<>();
        if(schema != null) {
            res = generateDeclsVariablesOfOutput(schema, rootVariableName,
                    "variable", objectName, false, 1);
        }

        return res;
    }

    public static DeclsVariable generateDeclsVariablesOfArrayOutput(ArraySchema arraySchema, String objectName, String variableName, String varKind, String enclosingVar){
        DeclsVariable res = generateDeclsVariablesOfArrayOutput(arraySchema, objectName, variableName, varKind);
        res.setEnclosingVar(enclosingVar);
        return res;
    }


    // Used when the return type is an array of objects (Bad practice)
    // Used for both output and exit
    // TODO: Create tests for nested objects and nested primitives with corresponding dtraces
    // TODO: Move to other class
    public static DeclsVariable generateDeclsVariablesOfArrayOutput(ArraySchema arraySchema, String objectName, String variableName, String varKind) {

        DeclsVariable father = new DeclsVariable(variableName, varKind,
                packageName + "." + objectName, STRING_TYPE_NAME, null);

        List<DeclsVariable> enclosedVars;
        String itemsDatatype = arraySchema.getItems().getType();

        String translatedDatatype = translateDatatype(itemsDatatype);
        if(primitiveTypes.contains(translatedDatatype) && !itemsDatatype.equals(OBJECT_TYPE_NAME)) {
            enclosedVars = getDeclsVariablesArray(variableName, ARRAY_TYPE_NAME, translatedDatatype,
                    translatedDatatype, 1);
        } else{
            enclosedVars = getDeclsVariablesArray(variableName, ARRAY_TYPE_NAME, packageName + "." + ARRAY_TYPE_NAME,
                    STRING_TYPE_NAME, 1);
        }

        father.setEnclosedVariables(enclosedVars);

        return father;
    }

    // Used when the response is primitive (Bad practice)
    // Used for both output and exit
    // TODO: Move to other class
    // generateDeclsVariablesOfPrimitiveResponse(parameterType, objectName, "this", "variable")
    public static DeclsVariable generateDeclsVariablesOfPrimitiveResponse(String parameterType, String objectName,
                                                                          String variableName, String varKind) {
        DeclsVariable father = new DeclsVariable(variableName, varKind, packageName + "." + objectName, STRING_TYPE_NAME, null);

        String translatedDatatype = translateDatatype(parameterType);

        DeclsVariable enclosedVar = new DeclsVariable(variableName + ".primitive", "field primitive",
                translatedDatatype, translatedDatatype,
                variableName, false);

        father.setEnclosedVariables(Collections.singletonList(enclosedVar));

        return father;

    }

    // Used for both output and exit
    // Creates the father variable
    public static DeclsVariable generateDeclsVariablesOfOutput(String variableName, String varKind, String packageName,
                                                                     String variableNameOutput, Schema mapOfProperties) {
        DeclsVariable father = new DeclsVariable(variableName, varKind,
                packageName + "." + variableNameOutput, STRING_TYPE_NAME, null);

        // Creates the son variables
        List<DeclsVariable> enclosedVars = generateDeclsVariablesOfOutput(mapOfProperties, variableName, varKind, variableNameOutput, false, 1);
        father.setEnclosedVariables(enclosedVars);

        return father;
    }



    // Recursive method
    public static List<DeclsVariable> generateDeclsVariablesOfOutput(Schema mapOfProperties, String variablePath,
                                                                     String varKind, String variableNameOutput,
                                                                     boolean isArray, int arrayNestingLevel) {
        List<DeclsVariable> res = new ArrayList<>();
        Set<String> parameterNames = mapOfProperties.getProperties().keySet();

        for(String parameterName: parameterNames) {
            Schema schema = (Schema) mapOfProperties.getProperties().get(parameterName);
            String parameterType = schema.getType();

            if(parameterType == null) {
                throw new NullPointerException("Please specify the parameter type for the parameter " + parameterName +
                        "\n If the error persists, specify it explicitly in the 'parameters' field of the API specification rather than using a '$ref'");
            }

            if(parameterType.equalsIgnoreCase(OBJECT_TYPE_NAME)) {  // Object
                // Generate the father variable
                DeclsVariable declsVariable = new DeclsVariable(variablePath + "." + parameterName, varKind,
                        packageName + "." + variableNameOutput + "_" + parameterName, STRING_TYPE_NAME, variablePath, isArray);

                // Recursive call for son variables
                List<DeclsVariable> enclosedVariables =
                        generateDeclsVariablesOfOutput(schema, variablePath + "." + parameterName, varKind,
                                variableNameOutput, false, arrayNestingLevel);
                // Set enclosed variables
                declsVariable.setEnclosedVariables(enclosedVariables);
                // Add to list
                res.add(declsVariable);

            } else if(parameterType.equalsIgnoreCase(ARRAY_TYPE_NAME)) {    // Array

                // Obtain variables of nested arrays
                List<DeclsVariable> declsVariables = getDeclsVariablesOfNestedArray(mapOfProperties, variablePath,
                        varKind,  parameterName, arrayNestingLevel, variableNameOutput);
                // Add to list
                res.addAll(declsVariables);

            } else {    // Primitive type

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

        ArraySchema arraySchema = (ArraySchema) mapOfProperties.getProperties().get(parameterName);
        String itemsDatatype = arraySchema.getItems().getType();

        // Three possible situations:
        if(itemsDatatype.equalsIgnoreCase(OBJECT_TYPE_NAME)) { // 1. The content is of type OBJECT (recursive call) (It will be necessary to create a new class)

            // Generate the father variable
            List<DeclsVariable> declsVariables = getDeclsVariablesArray(variablePath, parameterName,
                    packageName + "." + parameterName, STRING_TYPE_NAME, arrayNestingLevel);

            res.addAll(declsVariables);

        } else if(itemsDatatype.equalsIgnoreCase(ARRAY_TYPE_NAME)) { // 2. The content is another ARRAY (recursive call) [][]
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
    public static String translateDatatype(String input) {

        switch (input.toLowerCase()) {
            case "number":
                return DOUBLE_TYPE_NAME;
            case "integer":
                return INTEGER_TYPE_NAME;
            case "boolean":
                return BOOLEAN_TYPE_NAME;
            case "object":
                return OBJECT_TYPE_NAME;
            case "array":
                return ARRAY_TYPE_NAME;
            default:    // Including case "string"
                return STRING_TYPE_NAME;
        }

    }

    public static List<DeclsVariable> getDeclsVariablesArray(String variablePath, String parameterName,
                                                             String decType, String repType, int arrayNestingLevel) {
        List<DeclsVariable> res = new ArrayList<>();

        String arrayIndicator = "[]";

        String variableName = variablePath + "." + parameterName;
        // The enclosing var does not contain the name of the variable (this)
        res.add(new DeclsVariable(variableName, "field " + parameterName, decType + arrayIndicator, STRING_TYPE_NAME, variablePath));

        // The enclosing var name contains the name of the variable (this.array)
        res.add(new DeclsVariable(variableName + "[..]", ARRAY_TYPE_NAME, decType + arrayIndicator,
                repType  + arrayIndicator, variableName));

        return res;
    }

    public DeclsVariable(String variableName, String varKind, String decType, String repType, String enclosingVar) {
        this.variableName = variableName;
        this.varKind = varKind;
        this.decType = decType;
        this.repType = repType;
        this.enclosingVar = enclosingVar;
        this.enclosedVariables = new ArrayList<>();
        this.isArray = varKind.equals(ARRAY_TYPE_NAME);
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
        if(varKind.equals(ARRAY_TYPE_NAME) || this.isArray){
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
        String bodyParameter = testCase.getBodyParameter();

        String value = null;
        // TODO: Consider arrays
        // Consider path, header and body parameter (First level)
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

                // Search in body parameter
                if(value == null &&  bodyParameter != null && !bodyParameter.equals("")) {
                    JSONObject jsonBodyParameter = stringToJsonObject(bodyParameter);
                    if(jsonBodyParameter != null) {
                        List<String> hierarchyBody = hierarchy.subList(1, hierarchy.size());
                        value = getPrimitiveValueFromHierarchy(jsonBodyParameter, hierarchyBody);
                    }
                }

            } else {
                value = variableName;
            }

            if(repType.equals(STRING_TYPE_NAME) && value != null) {
                value = "\"" + value + "\"";
            }
        } else if(decType.equals(ARRAY_TYPE_NAME)){
          // TODO: Parameter of type array
        } else {    // If type = object
            value = "\"" + testCase.getTestCaseId() + "_" + variableName +  "_input" + "\"";
        }

        return value;
    }

    public String generateDtraceEnter(TestCase testCase) {
        // TODO: Consider arrays

        // TODO: The body parameter may contain an object
        // Father variable
        String value = getValueOfParameterForDtraceFile(testCase, this.variableName, this.decType, this.repType);

        String modified = "1";
        // If a value is nonsensical, the value of modified must be 2 instead of 1
        // A parameter of type double with a null value is nonsensical
        if(this.repType.equalsIgnoreCase(DOUBLE_TYPE_NAME) && (value == null || value.equals("null"))) {
            value = "nonsensical";
            modified = "2";
        }

        String res = this.variableName + "\n" +
                value + "\n" + modified;

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

        // If a value is nonsensical, the value of modified must be 2 instead of 1
        boolean isNonsensical = false;

        if(     // If primitive type
                primitiveTypes.contains(this.decType) && !varKind.equals(ARRAY_TYPE_NAME)
        ) {
            // Get the variable name (Withut wrapping)
            List<String> hierarchy = Arrays.asList(this.variableName.split("\\."));
            hierarchy = hierarchy.subList(1, hierarchy.size());
            value = getPrimitiveValueFromHierarchy(json, hierarchy);

            // Daikon does not support null values for parameters of type double
            // For this reason, if a null value is of type double, it is considered nonsensical and the value of modified will be 2 instead of 1
            if(repType.equalsIgnoreCase(DOUBLE_TYPE_NAME) && value == null) {
                value = "nonsensical";
                isNonsensical = true;
            }

            if(repType.replace("[]", "").equals(STRING_TYPE_NAME) && value != null) {
                value = "\"" + value + "\"";
            }

            if(isElementOfArray) {
                value = "[" + value + "]";
            }

        } else if (varKind.equals("array")) {       // If array TODO: Consider recursivity (Primitive, object and another array)
            List<String> hierarchy = Arrays.asList(this.variableName.replace("[..]", "").split("\\."));
            hierarchy = hierarchy.subList(1, hierarchy.size()); // Remove the class name from the hierachy TODO: Refactor to make it more intutive
            // TODO: Convert to list of arrays (flattening)
            JSONArray elements = getArrayFromHierarchy(json, hierarchy);

            // TODO: Convert to a separate function
            if(primitiveTypes.contains(this.decType.replace("[]", ""))) { // If array of primitives
                boolean isString = false;
                if(this.decType.replace("[]", "").equals(STRING_TYPE_NAME)) {
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

        String res;
        if(isNonsensical) {
            res = this.variableName + "\n" +
                    value + "\n" + "2";
        } else {
            res = this.variableName + "\n" +
                    value + "\n" + "1";
        }


        // Son variables        // TODO: Is this necessary? (isElementOfArray should never be true)
        for(DeclsVariable declsVariable: this.getEnclosedVariables()) {
            if(varKind.equals(ARRAY_TYPE_NAME)) {
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

}
