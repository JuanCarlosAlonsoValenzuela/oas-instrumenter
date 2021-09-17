package es.us.isa.jsoninstrumenter.pojos;

import io.swagger.v3.oas.models.media.ArraySchema;
import io.swagger.v3.oas.models.media.MediaType;
import io.swagger.v3.oas.models.media.Schema;
import io.swagger.v3.oas.models.parameters.Parameter;
import io.swagger.v3.oas.models.responses.ApiResponse;
import io.swagger.v3.oas.models.responses.ApiResponses;

import java.util.Map.Entry;

import java.util.*;

import static es.us.isa.jsoninstrumenter.main.GenerateDeclsFile.*;
import static es.us.isa.jsoninstrumenter.pojos.DeclsVariable.getListOfDeclsVariables;

public class DeclsClass {

    private String packageName;
    private String className;
    private List<DeclsObject> declsObjects;
    private List<DeclsEnter> declsEnters;
    private List<DeclsExit> declsExits;

    // DeclsClass with List of parameters (Input)
    public DeclsClass(String packageName, String className, String objectName, List<Parameter> parameters){
        this.packageName = packageName;
        this.className = className;
        this.declsEnters = new ArrayList<>();
        this.declsExits = new ArrayList<>();
        this.declsObjects = Collections.singletonList(new DeclsObject(packageName, objectName, "this", parameters));

    }

    // DeclsClass with only DeclsObject (Output)
    public DeclsClass(String packageName, String className, List<DeclsObject> declsObject) {
        this.packageName = packageName;
        this.className = className;
        this.declsEnters = new ArrayList<>();
        this.declsExits = new ArrayList<>();
        this.declsObjects = declsObject;

    }

    public DeclsClass(String packageName, String className) {
        this.packageName = packageName;
        this.className = className;
        this.declsEnters = new ArrayList<>();
        this.declsExits = new ArrayList<>();
        this.declsObjects = new ArrayList<>();

    }

    public void addDeclsObject(DeclsObject declsObject) {
        this.declsObjects.add(declsObject);
    }

    // DeclsClass for ENTER
    public static void setDeclsClassEnterAndExit(String packageName, String endpoint, String operationName,
                                                 String variableNameInput, List<Parameter> parameters,
                                                 ApiResponses apiResponses) {
        DeclsClass declsClass = new DeclsClass(packageName, endpoint);

        // Variables of the enter
        DeclsVariable enterVariables = getListOfDeclsVariables(packageName, variableNameInput, "input",parameters);

        // for loop that adds all the possible subexits
        List<DeclsExit> declsExits = new ArrayList<>();

        for(Entry<String, ApiResponse> apiResponse: apiResponses.entrySet()) {
            String objectName = operationName + "_Output_" + apiResponse.getKey();

            for(MediaType mediaType: apiResponse.getValue().getContent().values()) {
                Schema mapOfProperties = mediaType.getSchema();

                List<DeclsExit> nestedDeclsExits = getAllNestedDeclsExits(packageName, endpoint, operationName,
                        variableNameInput, enterVariables, objectName, mapOfProperties, apiResponse.getKey());
                declsExits.addAll(nestedDeclsExits);
            }
        }

        List<DeclsEnter> declsEnters = new ArrayList<>();
        for(DeclsExit declsExit: declsExits) {
            declsEnters.add(new DeclsEnter(packageName, endpoint, operationName, variableNameInput, parameters, "input",
                    declsExit.getNameSuffix(), declsExit.getStatusCode()));
        }

        declsClass.setDeclsEnters(declsEnters);
        declsClass.setDeclsExits(declsExits);
        addNewDeclsClass(declsClass);

    }

    // TODO: Move to other class
    public static List<DeclsObject> getAllNestedDeclsObjects(String packageName, String objectName, Schema mapOfProperties) {
        List<DeclsObject> res = new ArrayList<>();

        Map<String, Schema> allSchemas = new HashMap<>();
        allSchemas.put("", mapOfProperties);
        allSchemas.putAll(getAllNestedSchemas("", mapOfProperties));

        for(String nestedSchema: allSchemas.keySet()) {
            DeclsObject declsObject = new DeclsObject(packageName, objectName + nestedSchema, allSchemas.get(nestedSchema));
            res.add(declsObject);
        }

        return res;
    }

    // TODO: Move to another class
    public static List<DeclsExit> getAllNestedDeclsExits(String packageName, String endpoint, String operationName, String variableNameInput,
                                                         DeclsVariable enterVariables, String variableNameOutput, Schema mapOfProperties, String statusCode) {

        List<DeclsExit> res = new ArrayList<>();

        Map<String, Schema> allSchemas = new HashMap<>();
        allSchemas.put("", mapOfProperties);
        allSchemas.putAll(getAllNestedSchemas("", mapOfProperties));

        for(String nameSuffix: allSchemas.keySet()) {
//            DeclsExit declsExit = new DeclsExit(packageName, endpoint, operationName + nameSuffix,
//                    variableNameInput, enterVariables, variableNameOutput + nameSuffix, allSchemas.get(nameSuffix));
            DeclsExit declsExit = new DeclsExit(packageName, endpoint, operationName,
                    variableNameInput, enterVariables, variableNameOutput, allSchemas.get(nameSuffix), nameSuffix, statusCode);
            res.add(declsExit);
        }

        return res;
    }

    // TODO: Move to another class?
    public static Map<String, Schema> getAllNestedSchemas(String nameSuffix, Schema mapOfProperties) {
        Map<String, Schema> res = new HashMap<>();
        Set<String> parameterNames = mapOfProperties.getProperties().keySet();

        for(String parameterName: parameterNames) {
            Schema schema = (Schema) mapOfProperties.getProperties().get(parameterName);
            String parameterType = schema.getType();

            if(parameterType.equalsIgnoreCase("object")) {
                // Recursive call with object.getParameter
                res.putAll(getAllNestedSchemas(nameSuffix + "_" + parameterName, schema));

            } else if(parameterType.equalsIgnoreCase("array")) {
                ArraySchema arraySchema = (ArraySchema) mapOfProperties.getProperties().get(parameterName);
                String itemsDatatype = arraySchema.getItems().getType();

                if(itemsDatatype.equalsIgnoreCase("object")) {
                    Schema subSchema = arraySchema.getItems();
                    res.put(nameSuffix + "_" + parameterName, subSchema);
                    res.putAll(getAllNestedSchemas(nameSuffix + "_" + parameterName, subSchema));
                } else if(itemsDatatype.equalsIgnoreCase("array")){
                    // TODO: Nested arrays (Consider simply flattening the array)
                }

            }

        }

        return res;
    }

    // Generate outputs
    // ClassName is derived (e.g., output_200)
    // ObjectName is derived (e.g., output_200)
    public static void generateOutputDeclsClasses(String operationName, String packageName, ApiResponses apiResponses) {
        List<DeclsClass> res = new ArrayList<>();

        // Create a new class for each of the possible response formats
        for(Entry<String, ApiResponse> apiResponse: apiResponses.entrySet()) {
            String objectName = operationName + "_Output_" + apiResponse.getKey();

            // TODO: Take into account that there is one class per mediaType
            for(MediaType mediaType: apiResponse.getValue().getContent().values()) {
                Schema mapOfProperties = mediaType.getSchema();

                // Create the objects and automatically add them to the class
                List<DeclsObject> nestedDeclsObjects = getAllNestedDeclsObjects(packageName, objectName, mapOfProperties);

                // Create the class that will contain the objects
                DeclsClass declsClass = new DeclsClass(packageName, objectName, nestedDeclsObjects);

                res.add(declsClass);
            }
        }

        addNewDeclsClasses(res);
    }

    public String getPackageName() { return packageName; }

    public void setPackageName(String packageName) { this.packageName = packageName; }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public List<DeclsObject> getDeclsObjects() {
        return declsObjects;
    }

    public void setDeclsObjects(List<DeclsObject> declsObjects) {
        this.declsObjects = declsObjects;
    }

    public List<DeclsEnter> getDeclsEnters() {
        return declsEnters;
    }

    public void setDeclsEnters(List<DeclsEnter> declsEnters) {
        this.declsEnters = declsEnters;
    }

    public List<DeclsExit> getDeclsExits() {
        return declsExits;
    }

    public void setDeclsExits(List<DeclsExit> declsExits) {
        this.declsExits = declsExits;
    }

    public String toString() {
        String res = "ppt " + packageName + "." + className + ":::CLASS" + "\n" +
                "ppt-type class" + "\n";

        for(DeclsObject declsObject: declsObjects) {
            res = res + "\n" + declsObject + "\n";
        }

        for(DeclsEnter declsEnter: declsEnters) {
            res = res + "\n" + declsEnter + "\n";
        }

        for(DeclsExit declsExit: declsExits) {
            res = res + "\n" + declsExit + "\n";
        }

        return  res;
    }

}
