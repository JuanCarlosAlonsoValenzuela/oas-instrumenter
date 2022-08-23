package es.us.isa.jsoninstrumenter.model;

import io.swagger.v3.oas.models.Operation;
import io.swagger.v3.oas.models.media.ArraySchema;
import io.swagger.v3.oas.models.media.MediaType;
import io.swagger.v3.oas.models.media.Schema;
import io.swagger.v3.oas.models.responses.ApiResponse;
import io.swagger.v3.oas.models.responses.ApiResponses;

import java.util.Map.Entry;

import java.util.*;

import static es.us.isa.jsoninstrumenter.main.GenerateDeclsFile.*;
import static es.us.isa.jsoninstrumenter.model.DeclsVariable.getListOfDeclsVariables;
import static es.us.isa.jsoninstrumenter.model.DeclsVariable.translateDatatype;

public class DeclsClass {

    private String packageName;
    private String className;
    private List<DeclsEnter> declsEnters;
    private List<DeclsExit> declsExits;


    public DeclsClass(String packageName, String className) {
        this.packageName = packageName;
        this.className = className;
        this.declsEnters = new ArrayList<>();
        this.declsExits = new ArrayList<>();

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
            String objectName = operationName + HIERARCHY_SEPARATOR + "Output" + HIERARCHY_SEPARATOR + apiResponse.getKey();

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

    // TODO: Move to another class
    public static List<DeclsExit> getAllNestedDeclsExits(String packageName, String endpoint, String operationName, String variableNameInput,
                                                         DeclsVariable enterVariables, String variableNameOutput, MediaType mediaType, String statusCode) {

        List<DeclsExit> res = new ArrayList<>();
        String parameterType = mediaType.getSchema().getType();
        Schema mapOfProperties = mediaType.getSchema();

        // TODO: Convert into a function
        // TODO: Duplicated code with DeclsObject
        if(parameterType !=null && parameterType.equalsIgnoreCase(ARRAY_TYPE_NAME)) {
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

        } else if(parameterType !=null && primitiveTypes.contains(translateDatatype(parameterType))) {
            DeclsExit primitiveExit = new DeclsExit(packageName, endpoint, operationName, variableNameInput, enterVariables,
                    variableNameOutput, parameterType, statusCode);
            return Collections.singletonList(primitiveExit);
        }

        // Create DeclsObjects with the elements of the array
        // If there is an allOf, parameterType is null, but the schema contains all the properties
        if(parameterType ==null || parameterType.equalsIgnoreCase(OBJECT_TYPE_NAME)) {
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

            // If there is an allOf, parameterType is null, but the schema contains all the properties
            if(parameterType == null || parameterType.equalsIgnoreCase(OBJECT_TYPE_NAME)) {     // If object
                // TODO: 1. Esto parece indicar que un objeto se crea dos veces, revisar para evitar información redundante, posible bug, probar con otras APIs
                // TODO: 2. Probar con una API que contenga un objeto anidado dentro de otro, sin arrays de por medio
                // Recursive call with object.getParameter
                res.putAll(getAllNestedSchemas(nameSuffix + HIERARCHY_SEPARATOR + parameterName, schema));

            } else if(parameterType.equalsIgnoreCase(ARRAY_TYPE_NAME)) {    // If array
                ArraySchema arraySchema = (ArraySchema) mapOfProperties.getProperties().get(parameterName);
                String itemsDatatype = arraySchema.getItems().getType();
                String nestingSuffix = ".array";    // TODO: Change "." for a different char

                // If there is an allOf, parameterType is null, but the schema contains all the properties
                while(itemsDatatype != null && itemsDatatype.equals(ARRAY_TYPE_NAME)) {
                    arraySchema = (ArraySchema) arraySchema.getItems();
                    res.put(nameSuffix + HIERARCHY_SEPARATOR + parameterName + nestingSuffix, arraySchema);
                    itemsDatatype = arraySchema.getItems().getType();
                    nestingSuffix = nestingSuffix + ".array";
                }

                // If there is an allOf, parameterType is null, but the schema contains all the properties
                if(itemsDatatype == null || itemsDatatype.equalsIgnoreCase(OBJECT_TYPE_NAME)) {
                    Schema subSchema = arraySchema.getItems();

                    res.put(nameSuffix + HIERARCHY_SEPARATOR + parameterName, subSchema);

                    res.putAll(getAllNestedSchemas(nameSuffix + HIERARCHY_SEPARATOR + parameterName, subSchema));
                }

            }

        }

        return res;
    }

    public String getPackageName() { return packageName; }

    public void setPackageName(String packageName) { this.packageName = packageName; }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
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

        for(DeclsEnter declsEnter: declsEnters) {
            res = res + "\n" + declsEnter + "\n";
        }

        for(DeclsExit declsExit: declsExits) {
            res = res + "\n" + declsExit + "\n";
        }

        return  res;
    }

}
