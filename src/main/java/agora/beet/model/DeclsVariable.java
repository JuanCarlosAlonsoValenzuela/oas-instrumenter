package agora.beet.model;

import io.swagger.v3.oas.models.Operation;
import io.swagger.v3.oas.models.media.ArraySchema;
import io.swagger.v3.oas.models.media.Content;
import io.swagger.v3.oas.models.media.MediaType;
import io.swagger.v3.oas.models.media.Schema;
import io.swagger.v3.oas.models.parameters.Parameter;
import io.swagger.v3.oas.models.parameters.RequestBody;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.*;

import static agora.beet.main.GenerateDeclsFile.*;
import static agora.beet.model.DeclsEnter.generateDtraceEnterValueOfArray;
import static agora.beet.model.DeclsExit.generateDtraceExitValueOfJSONArray;
import static agora.beet.util.JSONManager.stringToJsonObject;
import static agora.beet.util.TestCaseFileManager.removeNewLineChars;

public class DeclsVariable {

    private String variableName;
    private String varKind;
    private String decType;
    private String repType;
    private String enclosingVar;
    private boolean isArray;
    private List<DeclsVariable> enclosedVariables;

    public static DeclsVariable getListOfDeclsVariables(String objectName, String rootVariableName, Operation operation) {
        // Father parameter
        DeclsVariable father = new DeclsVariable(rootVariableName, "variable",
                objectName, HASHCODE_TYPE_NAME, null);

        List<DeclsVariable> enclosedVariables = new ArrayList<>();


        // Extract parameters from path, query and header
        List<Parameter> parameters = operation.getParameters();
        if(parameters != null) {
            for(Parameter parameter: parameters) {

                String parameterType = parameter.getSchema().getType();
                if(parameterType == null) {
                    throw new NullPointerException("Please specify the parameter type for the parameter " + parameter.getName() +
                            "\n If the error persists, specify it explicitly in the 'parameters' field of the API specification rather than using a '$ref'");
                }

                if(parameterType.equalsIgnoreCase(OBJECT_TYPE_NAME)) {  // Object
                    throw new NullPointerException("Please provide a primitive object or an array as input parameter");
                } else if(parameterType.equalsIgnoreCase(ARRAY_TYPE_NAME)) {    // Array
                    // Only the first nesting level for the ENTER program point
                    ArraySchema arraySchema = (ArraySchema) parameter.getSchema();
                    String itemsDataType = arraySchema.getItems().getType();

                    if(itemsDataType.equalsIgnoreCase(OBJECT_TYPE_NAME) || itemsDataType.equalsIgnoreCase(ARRAY_TYPE_NAME)) {   // The content is an array or an object
                        List<DeclsVariable> declsVariables = getDeclsVariablesArray(rootVariableName, parameter.getName(),
                                parameter.getName(), HASHCODE_TYPE_NAME);

                        enclosedVariables.addAll(declsVariables);

                    } else {    // The content is a primitive type
                        String translatedDatatype = translateDatatype(itemsDataType);

                        List<DeclsVariable> declsVariables = getDeclsVariablesArray(rootVariableName,
                                parameter.getName(), translatedDatatype, translatedDatatype);
                        enclosedVariables.addAll(declsVariables);

                    }
                } else {    // Primitive type
                    DeclsVariable declsVariable = new DeclsVariable(rootVariableName + "."+ parameter.getName(),
                            "field " + parameter.getName(), translateDatatype(parameterType),
                            translateDatatype(parameterType), father.getVariableName());
                    enclosedVariables.add(declsVariable);
                }


            }
        }

        List<DeclsVariable> declsVariablesFromBodyAndForm = getDeclsVariablesOfBodyAndFormParameters(operation, rootVariableName, objectName, "body");
        enclosedVariables.addAll(declsVariablesFromBodyAndForm);

        father.setEnclosedVariables(enclosedVariables);
        return father;

    }

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
                    "variable", objectName, false);
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
    public static DeclsVariable generateDeclsVariablesOfArrayOutput(ArraySchema arraySchema, String objectName, String variableName, String varKind) {

        DeclsVariable father = new DeclsVariable(variableName, varKind, objectName, HASHCODE_TYPE_NAME, null);

        List<DeclsVariable> enclosedVars;
        String itemsDatatype = arraySchema.getItems().getType();

        String translatedDatatype = translateDatatype(itemsDatatype);
        if(primitiveTypes.contains(translatedDatatype) && !itemsDatatype.equals(OBJECT_TYPE_NAME)) {
            enclosedVars = getDeclsVariablesArray(variableName, ARRAY_TYPE_NAME, translatedDatatype,
                    translatedDatatype);
        } else{
            enclosedVars = getDeclsVariablesArray(variableName, ARRAY_TYPE_NAME, ARRAY_TYPE_NAME,
                    HASHCODE_TYPE_NAME);
        }

        father.setEnclosedVariables(enclosedVars);

        return father;
    }

    // Used when the response is primitive (Bad practice)
    // Used for both output and exit
    // generateDeclsVariablesOfPrimitiveResponse(parameterType, objectName, "this", "variable")
    public static DeclsVariable generateDeclsVariablesOfPrimitiveResponse(String parameterType, String objectName,
                                                                          String variableName, String varKind) {
        DeclsVariable father = new DeclsVariable(variableName, varKind, objectName, HASHCODE_TYPE_NAME, null);

        String translatedDatatype = translateDatatype(parameterType);

        DeclsVariable enclosedVar = new DeclsVariable(variableName + ".primitive", "field primitive",
                translatedDatatype, translatedDatatype,
                variableName, false);

        father.setEnclosedVariables(Collections.singletonList(enclosedVar));

        return father;

    }

    // Used for both output and exit
    // Creates the father variable
    public static DeclsVariable generateDeclsVariablesOfOutput(String variableName, String varKind,
                                                                     String variableNameOutput, Schema mapOfProperties) {
        DeclsVariable father = new DeclsVariable(variableName, varKind,
                variableNameOutput, HASHCODE_TYPE_NAME, null);

        // Creates the son variables
        List<DeclsVariable> enclosedVars = generateDeclsVariablesOfOutput(mapOfProperties, variableName, varKind, variableNameOutput, false);
        father.setEnclosedVariables(enclosedVars);

        return father;
    }



    // Recursive method
    public static List<DeclsVariable> generateDeclsVariablesOfOutput(Schema mapOfProperties, String variablePath,
                                                                     String varKind, String variableNameOutput,
                                                                     boolean isArray) {
        List<DeclsVariable> res = new ArrayList<>();
        Map<String, Schema> properties = mapOfProperties.getProperties();
        // Warnings if properties == null
        if (properties == null) {
            if(mapOfProperties.getAdditionalProperties() == null) {
                System.err.println("WARNING: No properties found for object: " + variablePath);
            } else{
                System.err.println("WARNING: Object: " + variablePath + " only contains additional properties");
            }
        } else {
            Set<String> parameterNames = properties.keySet();

            for(String parameterName: parameterNames) {
                Schema schema = (Schema) mapOfProperties.getProperties().get(parameterName);
                String parameterType = schema.getType();

                // If there is an allOf, parameterType is null, but the schema contains all the properties
                if(parameterType == null || parameterType.equalsIgnoreCase(OBJECT_TYPE_NAME)) {  // Object
                    // Generate the father variable
                    DeclsVariable declsVariable = new DeclsVariable(variablePath + "." + parameterName, "field " + parameterName,
                            variableNameOutput + HIERARCHY_SEPARATOR + parameterName, HASHCODE_TYPE_NAME, variablePath, isArray);

                    // Recursive call for son variables
                    List<DeclsVariable> enclosedVariables =
                            generateDeclsVariablesOfOutput(schema, variablePath + "." + parameterName, varKind,
                                    variableNameOutput, false);
                    // Set enclosed variables
                    declsVariable.setEnclosedVariables(enclosedVariables);
                    // Add to list
                    res.add(declsVariable);

                } else if(parameterType.equalsIgnoreCase(ARRAY_TYPE_NAME)) {    // Array

                    // Obtain variables of nested arrays
                    List<DeclsVariable> declsVariables = getDeclsVariablesOfNestedArray(mapOfProperties, variablePath,
                            varKind,  parameterName, variableNameOutput);
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
        }

        return res;
    }

    public static List<DeclsVariable> getDeclsVariablesOfNestedArray(Schema mapOfProperties , String variablePath,
                                                                     String varKind, String parameterName, String variableNameOutput) {

        List<DeclsVariable> res = new ArrayList<>();

        ArraySchema arraySchema = (ArraySchema) mapOfProperties.getProperties().get(parameterName);
        String itemsDatatype = arraySchema.getItems().getType();

        // Three possible situations:
        // If there is an allOf, parameterType is null, but the schema contains all the properties
        if(itemsDatatype == null || itemsDatatype.equalsIgnoreCase(OBJECT_TYPE_NAME) || itemsDatatype.equalsIgnoreCase(ARRAY_TYPE_NAME)) {
            // 1. The content is of type OBJECT (recursive call) (It will be necessary to create a new class)
            // OR
            // 2. The content is another ARRAY

            // Generate the father variable
            List<DeclsVariable> declsVariables = getDeclsVariablesArray(variablePath, parameterName,
                    parameterName, HASHCODE_TYPE_NAME);

            res.addAll(declsVariables);

        } else {    // 3. The content is a primitive type (base case)
            String translatedDatatype = translateDatatype(itemsDatatype);
            List<DeclsVariable> declsVariablesArrays = getDeclsVariablesArray(variablePath, parameterName,
                    translatedDatatype, translatedDatatype);

            res.addAll(declsVariablesArrays);
        }

        return res;

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
                                                             String decType, String repType) {
        List<DeclsVariable> res = new ArrayList<>();

        String arrayIndicator = "[]";

        String variableName = variablePath + "." + parameterName;
        // The enclosing var does not contain the name of the variable (this)
        res.add(new DeclsVariable(variableName, "field " + parameterName, decType + arrayIndicator, HASHCODE_TYPE_NAME, variablePath));

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

    private static String getEnterParameterValue(TestCase testCase, List<String> hierarchy) {
        Map<String, String> queryParametersValues = testCase.getQueryParameters();
        Map<String, String> pathParametersValues = testCase.getPathParameters();
        Map<String, String> headerParametersValues = testCase.getHeaderParameters();
        Map<String, String> formParametersValues = testCase.getFormParameters();
        String bodyParameter = testCase.getBodyParameter();

        String value = null;

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

        // Set value to null if its value should be considered as null
        if(Arrays.asList(stringsToConsiderAsNull).contains(value)) {
            value = null;
        }


        return value;

    }

    // Used for ENTER parameters
    private static String getValueOfParameterForDtraceFile(TestCase testCase, String variableName, String decType, String repType) {
        String value = null;
        // Consider path, header and body parameter (First level)
        if(primitiveTypes.contains(decType)) { // If primitive value

            // Get the variable name (Without Wrapping)
            List<String> hierarchy = Arrays.asList(variableName.split("\\."));
            if(hierarchy.size() > 1) {
                value = getEnterParameterValue(testCase, hierarchy);
            } else {
                value = variableName;
            }

            if(repType.equals(STRING_TYPE_NAME) && value != null) {
                // Decode the parameter value. For example, "street+address" is decoded as "street address" and "1%2C2" is decoded as "1,2"
                value = decodeString(value);
                value = "\"" + value + "\"";
            }
        } else if(variableName.contains("[..]")){   // If array values
            List<String> hierarchy = Arrays.asList(variableName.replace("[..]", "").split("\\."));
            if(hierarchy.size() > 1) {
                // Get the array value. Example: element1%2element2%2Celement3
                value = getEnterParameterValue(testCase, hierarchy);
                if(value!=null){
                    // Decode the value. Example element1,element2,element3
                    value = decodeString(value);
                    // Convert the value to array (replace "," with spaces and add [])
                    value = generateDtraceEnterValueOfArray(testCase, value, decType, variableName);
                } else {
                    // If the array is null, return nonsensical
                    value = "nonsensical";
                }
            } else {
                value = variableName;
            }

        } else {    // If type = object or identifier of array
            value = "\"" + testCase.getTestCaseId() + variableName +  "input" + "\"";
            value = value.replace(HIERARCHY_SEPARATOR, "").replace("_", "");
            value = String.valueOf(Math.abs(value.hashCode()));

            List<String> hierarchy = Arrays.asList(variableName.split("\\."));
            hierarchy = hierarchy.subList(1, hierarchy.size());

            if(hierarchy.size()>0) {
                String key = getEnterParameterValue(testCase, hierarchy);
                if(key==null){
                    value = null;
                }
            }

        }

        return value;
    }

    public static String decodeString(String parameterValue) {
        try {
            return URLDecoder.decode(parameterValue, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            System.exit(1);
        }
        return null;
    }

    public String generateDtraceEnter(TestCase testCase) {
        // Father variable
        String value = getValueOfParameterForDtraceFile(testCase, this.variableName, this.decType, this.repType);

        String modified = "1";
        // If a value is nonsensical, the value of modified must be 2 instead of 1
        // A parameter of type double with a null value is nonsensical
        if(
                (this.repType.equalsIgnoreCase(DOUBLE_TYPE_NAME) || this.repType.equalsIgnoreCase(BOOLEAN_TYPE_NAME) || this.repType.equalsIgnoreCase(INTEGER_TYPE_NAME))
                        && (value == null || value.equals("null"))
        ) {
            value = "nonsensical";
            modified = "2";
        }
        // This happens with arrays
        if(value != null && value.equals("nonsensical")) {
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

            if(Arrays.asList(stringsToConsiderAsNull).contains(value)) {
                value = null;
            }

            // Daikon does not support null values for parameters of type double
            // For this reason, if a null value is of type double, it is considered nonsensical and the value of modified will be 2 instead of 1
            if(
                    (repType.equalsIgnoreCase(DOUBLE_TYPE_NAME) || repType.equalsIgnoreCase(INTEGER_TYPE_NAME) || repType.equalsIgnoreCase(BOOLEAN_TYPE_NAME))
                            && value == null
            ) {
                value = "nonsensical";
                isNonsensical = true;
            }

            if(repType.replace("[]", "").equals(STRING_TYPE_NAME) && value != null) {
                // Replace new line characters fron the string
                value = removeNewLineChars(value);
                value = "\"" + value + "\"";
            }

            if(isElementOfArray) {
                value = "[" + value + "]";
            }

        } else if (varKind.equals(ARRAY_TYPE_NAME)) {       // If array
            List<String> hierarchy = Arrays.asList(this.variableName.replace("[..]", "").split("\\."));
            hierarchy = hierarchy.subList(1, hierarchy.size()); // Remove the class name from the hierachy
            JSONArray elements = getArrayFromHierarchy(json, hierarchy);

            // If elements == null, the elements are set to nonsensical
            value = generateDtraceExitValueOfJSONArray(testCase, elements, this.decType, this.variableName);
            if(value.equals("nonsensical")) {
                isNonsensical = true;
            }

        } else {    // If type = object or identifier of array (both array of objects and array of primitives)
            // Use a hashcode as value
            value = "\"" + testCase.getTestCaseId() + this.variableName + "output" + "\"";
            value = value.replace(HIERARCHY_SEPARATOR, "").replace("_", "");
            value = String.valueOf(Math.abs(value.hashCode()));

            // If the element (either array or object) is not present in the response JSON, we set its value to null
            List<String> hierarchy = Arrays.asList(this.variableName.split("\\."));
            hierarchy = hierarchy.subList(1, hierarchy.size());
            if(hierarchy.size()>0) {        // hierarchy.size()==0 is the "return" object
                String key = getPrimitiveValueFromHierarchy(json, hierarchy);
                if(key==null){
                    value = "null";
                }
            }

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


        // Son variables
        for(DeclsVariable declsVariable: this.getEnclosedVariables()) {
            if(varKind.equals(ARRAY_TYPE_NAME)) {
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
                return null;
            }
        }

    }

}
