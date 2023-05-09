package agora.beet.model;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.*;

import static agora.beet.dtrace.ExitArray.generateDtraceExitValueOfJSONArray;
import static agora.beet.dtrace.ParameterValues.getValueOfParameterForDtraceFile;
import static agora.beet.dtrace.VariableValues.getArrayFromHierarchy;
import static agora.beet.dtrace.VariableValues.getPrimitiveValueFromHierarchy;
import static agora.beet.main.GenerateDeclsFile.*;
import static agora.beet.util.TestCaseFileManager.removeNewLineChars;

/**
 * @author Juan C. Alonso
 */
public class DeclsVariable {

    private String variableName;
    private String varKind;
    private String decType;
    private String repType;
    private String enclosingVar;
    private boolean isArray;
    private List<DeclsVariable> enclosedVariables;

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

}
