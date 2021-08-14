package es.us.isa.jsoninstrumenter.pojos;

import io.swagger.annotations.Api;
import io.swagger.v3.oas.models.media.Content;
import io.swagger.v3.oas.models.media.MediaType;
import io.swagger.v3.oas.models.media.Schema;
import io.swagger.v3.oas.models.parameters.Parameter;
import io.swagger.v3.oas.models.responses.ApiResponse;
import io.swagger.v3.oas.models.responses.ApiResponses;
import java.util.Map.Entry;

import java.util.*;

public class DeclsClass {

    private String packageName;
    private String className;
    private List<DeclsObject> declsObjects;
    private List<DeclsEnter> declsEnters;
    private List<DeclsExit> declsExits;

    // DeclsClass With Object
    public DeclsClass(String packageName, String className, String objectName, List<Parameter> parameters){
        this.packageName = packageName;
        this.className = className;
        this.declsEnters = new ArrayList<>();
        this.declsExits = new ArrayList<>();
        this.declsObjects = Collections.singletonList(new DeclsObject(packageName, objectName, parameters));

    }

    // Generate outputs
    // ClassName is derived (e.g., output_200)
    // ObjectName is derived (e.g., output_200)
    // TODO: Complete
    public List<DeclsClass> generateOutputDeclsClasses(String packageName, ApiResponses apiResponses) {
        List<DeclsClass> res = new ArrayList<>();

        // Create a new class for each of the possible response formats
        for(Entry<String, ApiResponse> apiResponse: apiResponses.entrySet()) {

            String className = "Output_" + apiResponse.getKey();

            // TODO: Take into account that there is one class per mediaType
            for(MediaType mediaType: apiResponse.getValue().getContent().values()) {
                Map<String, Schema> mapOfProperties = mediaType.getSchema().getProperties();
                // TODO: Call method using mapOfProperties as parameter

                for(Entry<String, Schema> property: mapOfProperties.entrySet()) {
                    String parameterName = property.getKey();

                    // TODO: Consider array
                    if(property.getValue().getType().equals("object")) {
                        // TODO: object
                        // TODO: Recursive method
                    } else {
                        // TODO: Other datatype (Use translation)
                    }
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
            res = res + "\n" + declsObject;
        }

        for(DeclsEnter declsEnter: declsEnters) {
            res = res + "\n" + declsEnter;
        }

        for(DeclsExit declsExit: declsExits) {
            res = res + "\n" + declsExit;
        }

        return  res;
    }

}
