package es.us.isa.jsoninstrumenter.pojos;

import io.swagger.v3.oas.models.media.ArraySchema;
import io.swagger.v3.oas.models.media.Schema;
import io.swagger.v3.oas.models.Operation;

import java.util.List;

import static es.us.isa.jsoninstrumenter.pojos.DeclsVariable.*;

public class DeclsObject {

    private String packageName;
    private String objectName;
    private DeclsVariable declsVariables;

    // DeclsObject With input Variables
    public DeclsObject(String packageName, String objectName, String rootVariableName, Operation operation) {
        this.packageName = packageName;
        this.objectName = objectName;
        this.declsVariables = getListOfDeclsVariables(packageName, objectName, rootVariableName, operation);

    }

    // DeclsObject With Output
    public DeclsObject(String packageName, String objectName, Schema mapOfProperties) {
        this.packageName = packageName;
        this.objectName = objectName;
        this.declsVariables = generateDeclsVariablesOfOutput("this", "variable", packageName,
                objectName, mapOfProperties);
    }

    public DeclsObject(String packageName, String objectName, ArraySchema arraySchema) {
        this.packageName = packageName;
        this.objectName = objectName;
        this.declsVariables = generateDeclsVariablesOfArrayOutput(arraySchema, objectName, "this", "variable");
    }



    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getObjectName() {
        return objectName;
    }

    public void setObjectName(String objectName) {
        this.objectName = objectName;
    }

    public DeclsVariable getDeclsVariables() {
        return declsVariables;
    }

    public void setDeclsVariables(DeclsVariable declsVariables) {
        this.declsVariables = declsVariables;
    }

    public  String toString() {
        String res = "ppt " + packageName + "." +objectName + ":::OBJECT" + "\n" +
                "ppt-type object";

        res = res + "\n" + declsVariables;


        return res;
    }

}
