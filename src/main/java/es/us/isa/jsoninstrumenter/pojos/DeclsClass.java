package es.us.isa.jsoninstrumenter.pojos;

import io.swagger.v3.oas.models.Operation;
import io.swagger.v3.oas.models.media.ArraySchema;
import io.swagger.v3.oas.models.media.MediaType;
import io.swagger.v3.oas.models.media.Schema;
import io.swagger.v3.oas.models.responses.ApiResponse;
import io.swagger.v3.oas.models.responses.ApiResponses;

import java.util.Map.Entry;

import java.util.*;

import static es.us.isa.jsoninstrumenter.main.GenerateDeclsFile.*;
import static es.us.isa.jsoninstrumenter.pojos.DeclsVariable.getListOfDeclsVariables;
import static es.us.isa.jsoninstrumenter.pojos.DeclsVariable.translateDatatype;

public class DeclsClass {

    private String packageName;
    private String className;
    private List<DeclsObject> declsObjects;
    private List<DeclsEnter> declsEnters;
    private List<DeclsExit> declsExits;

    // DeclsClass with List of parameters (Input)
    public DeclsClass(String packageName, String className, String objectName, Operation operation){
        this.packageName = packageName;
        this.className = className;
        this.declsEnters = new ArrayList<>();
        this.declsExits = new ArrayList<>();
        this.declsObjects = Collections.singletonList(new DeclsObject(packageName, objectName, "this", operation));

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
                                                 String variableNameInput, Operation operation) {

        // ApiResponses = operation.getResponses
        DeclsClass declsClass = new DeclsClass(packageName, endpoint);

        // Variables of the enter
        DeclsVariable enterVariables = getListOfDeclsVariables(packageName, variableNameInput, "input", operation);

        // for loop that adds all the possible subexits
        List<DeclsExit> declsExits = new ArrayList<>();

        ApiResponses apiResponses = operation.getResponses();
        for(Entry<String, ApiResponse> apiResponse: apiResponses.entrySet()) {
            String objectName = operationName + "_Output_" + apiResponse.getKey();

            for(MediaType mediaType: apiResponse.getValue().getContent().values()) {

                List<DeclsExit> nestedDeclsExits = getAllNestedDeclsExits(packageName, endpoint, operationName,
                        variableNameInput, enterVariables, objectName, mediaType, apiResponse.getKey());

                declsExits.addAll(nestedDeclsExits);
            }
        }

        List<DeclsEnter> declsEnters = new ArrayList<>();
        for(DeclsExit declsExit: declsExits) {
            declsEnters.add(new DeclsEnter(packageName, endpoint, operationName, variableNameInput, operation, "input",
                    declsExit.getNameSuffix(), declsExit.getStatusCode()));
        }

        declsClass.setDeclsEnters(declsEnters);
        declsClass.setDeclsExits(declsExits);
        addNewDeclsClass(declsClass);

    }

    // TODO: Move to other class
    // Used when the return is of type array (Example: RESTcountries)
    public static List<DeclsObject> getAllNestedDeclsObjects(String packageName, String objectName, MediaType mediaType) {
        List<DeclsObject> res = new ArrayList<>();
        String parameterType = mediaType.getSchema().getType();
        Schema mapOfProperties = mediaType.getSchema();

        // TODO: Convert into a function
        // TODO: Duplicated code with DeclsExit
        if(parameterType.equalsIgnoreCase(ARRAY_TYPE_NAME)) {   // The return is of type array (Bad practice)
            // Get the schema as ArraySchema
            ArraySchema arraySchema = (ArraySchema) mediaType.getSchema();
            String nameSuffix = ".array";

            while(parameterType.equalsIgnoreCase(ARRAY_TYPE_NAME)) {
                DeclsObject declsObject = new DeclsObject(packageName, objectName + nameSuffix, arraySchema);
                res.add(declsObject);

                parameterType = arraySchema.getItems().getType();

                if(parameterType.equalsIgnoreCase(ARRAY_TYPE_NAME)) {
                    arraySchema = (ArraySchema) arraySchema.getItems();
                } else {
                    mapOfProperties = arraySchema.getItems();
                }
                nameSuffix = nameSuffix + ".array";
            }

        }  else if(primitiveTypes.contains(translateDatatype(parameterType))) {     // The return is of type primitive (Bad practice)
            // Generate DeclsObjectOfPrimitiveParameter
            DeclsObject primitiveObject = new DeclsObject(packageName, objectName, parameterType);

            return Collections.singletonList(primitiveObject);
        }

        // Create DeclsObjects for the elements of the array
        if(parameterType.equalsIgnoreCase(OBJECT_TYPE_NAME)) {    // If the schema is of primitive type
        // If the schema is of type object
            Map<String, Schema> allSchemas = new HashMap<>();
            allSchemas.put("", mapOfProperties);
            allSchemas.putAll(getAllNestedSchemas("", mapOfProperties));

            for(String nestedSchema: allSchemas.keySet()) {
                if(allSchemas.get(nestedSchema).getProperties()==null){ // If the element is of type array, call the constructor that receives an ArraySchema
                    ArraySchema arraySchema = (ArraySchema) allSchemas.get(nestedSchema);
                    DeclsObject declsObject = new DeclsObject(packageName, objectName + nestedSchema, arraySchema);
                    res.add(declsObject);
                } else {        // If the element is of type object, call the constructor that receives an Schema
                    DeclsObject declsObject = new DeclsObject(packageName, objectName + nestedSchema, allSchemas.get(nestedSchema));
                    res.add(declsObject);
                }

            }
        }

        return res;

    }

    // TODO: Move to another class
    public static List<DeclsExit> getAllNestedDeclsExits(String packageName, String endpoint, String operationName, String variableNameInput,
                                                         DeclsVariable enterVariables, String variableNameOutput, MediaType mediaType, String statusCode) {

        List<DeclsExit> res = new ArrayList<>();
        String parameterType = mediaType.getSchema().getType();
        Schema mapOfProperties = mediaType.getSchema();

        // TODO: Convert into a function
        // TODO: Duplicated code with DeclsObject
        if(parameterType.equalsIgnoreCase(ARRAY_TYPE_NAME)) {
            // Get the schema as ArraySchema
            ArraySchema arraySchema = (ArraySchema) mediaType.getSchema();
            String nameSuffix = ".array";       // TODO: Change "." for a different char

            while(parameterType.equalsIgnoreCase(ARRAY_TYPE_NAME)) {        // TODO: Check that the schema is properly iterated when there are multiple nested arrays
                DeclsExit declsExit = new DeclsExit(packageName, endpoint, operationName, variableNameInput, enterVariables, variableNameOutput,
                        arraySchema, nameSuffix, statusCode);
                res.add(declsExit);

                parameterType = arraySchema.getItems().getType();

                if(parameterType.equalsIgnoreCase(ARRAY_TYPE_NAME)) {
                    arraySchema = (ArraySchema) arraySchema.getItems();
                } else {
                    mapOfProperties = arraySchema.getItems();
                }
                nameSuffix = nameSuffix + ".array";     // TODO: Change "." for a different char
            }

        } else if(primitiveTypes.contains(translateDatatype(parameterType))) {
            DeclsExit primitiveExit = new DeclsExit(packageName, endpoint, operationName, variableNameInput, enterVariables,
                    variableNameOutput, parameterType, statusCode);
            return Collections.singletonList(primitiveExit);
        }

        // Create DeclsObjects with the elements of the array
        if(parameterType.equalsIgnoreCase(OBJECT_TYPE_NAME)) {
            Map<String, Schema> allSchemas = new HashMap<>();

            allSchemas.put("", mapOfProperties);
            allSchemas.putAll(getAllNestedSchemas("", mapOfProperties));

            for(String nameSuffix: allSchemas.keySet()) {
                if(allSchemas.get(nameSuffix).getProperties()==null){       // If the element is of type array, call the constructor that receives an ArraySchema
                    ArraySchema arraySchema = (ArraySchema) allSchemas.get(nameSuffix);
                    DeclsExit declsExit = new DeclsExit(packageName, endpoint, operationName, variableNameInput, enterVariables, variableNameOutput,
                            arraySchema, nameSuffix, statusCode);
                    res.add(declsExit);
                } else {    // If the element is of type object, call the constructor that receives an Schema
                    DeclsExit declsExit = new DeclsExit(packageName, endpoint, operationName,
                            variableNameInput, enterVariables, variableNameOutput, allSchemas.get(nameSuffix), nameSuffix, statusCode);
                    res.add(declsExit);
                }


            }

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

            if(parameterType.equalsIgnoreCase(OBJECT_TYPE_NAME)) {
                // TODO: 1. Esto parece indicar que un objeto se crea dos veces, revisar para evitar información redundante, posible bug, probar con otras APIs
                // TODO: 2. Probar con una API que contenga un objeto anidado dentro de otro, sin arrays de por medio
                // Recursive call with object.getParameter
                // TODO: Change char "_" for another char (conflicts with snake_case)
                res.putAll(getAllNestedSchemas(nameSuffix + "_" + parameterName, schema));

            } else if(parameterType.equalsIgnoreCase(ARRAY_TYPE_NAME)) {
                ArraySchema arraySchema = (ArraySchema) mapOfProperties.getProperties().get(parameterName);
                String itemsDatatype = arraySchema.getItems().getType();
                String nestingSuffix = ".array";    // TODO: Change "." for a different char

                while(itemsDatatype.equals(ARRAY_TYPE_NAME)) {
                    arraySchema = (ArraySchema) arraySchema.getItems();
                    res.put(nameSuffix + "_" + parameterName + nestingSuffix, arraySchema);
                    itemsDatatype = arraySchema.getItems().getType();
                    nestingSuffix = nestingSuffix + ".array";
                }

                if(itemsDatatype.equalsIgnoreCase(OBJECT_TYPE_NAME)) {
                    Schema subSchema = arraySchema.getItems();
                    // TODO: Change char "_" for another char (conflicts with snake_case)
                    res.put(nameSuffix + "_" + parameterName, subSchema);
                    // TODO: Change char "_" for another char (conflicts with snake_case)
                    res.putAll(getAllNestedSchemas(nameSuffix + "_" + parameterName, subSchema));
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
            String objectName = operationName + "_Output_" + apiResponse.getKey();      // operationName_Output_statusCode

            for(MediaType mediaType: apiResponse.getValue().getContent().values()) {
                if(mediaType != null) {
                    // Create the objects and automatically add them to the class
                    List<DeclsObject> nestedDeclsObjects = getAllNestedDeclsObjects(packageName, objectName, mediaType);

                    // Create the class that will contain the objects
                    DeclsClass declsClass = new DeclsClass(packageName, objectName, nestedDeclsObjects);

                    res.add(declsClass);
                }
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
