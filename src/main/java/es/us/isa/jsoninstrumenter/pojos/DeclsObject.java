package es.us.isa.jsoninstrumenter.pojos;

import io.swagger.v3.oas.models.media.Schema;
import io.swagger.v3.oas.models.parameters.Parameter;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import static es.us.isa.jsoninstrumenter.pojos.DeclsVariable.generateDeclsVariablesOfOutput;
import static es.us.isa.jsoninstrumenter.pojos.DeclsVariable.getListOfDeclsVariables;

public class DeclsObject {

    private String packageName;
    private String objectName;
    private List<DeclsVariable> declsVariables;

    // DeclsObject With input Variables
    public DeclsObject(String packageName, String objectName, String rootVariableName, List<Parameter> parameters) {
        this.packageName = packageName;
        this.objectName = objectName;
        this.declsVariables = Collections.singletonList(getListOfDeclsVariables(packageName, objectName, rootVariableName, parameters));

    }

    // DeclsObject With Output
    public DeclsObject(String packageName, String objectName, Map<String, Schema> mapOfProperties) {
        this.packageName = packageName;
        this.objectName = objectName;
        this.declsVariables = generateDeclsVariablesOfOutput(packageName, objectName, mapOfProperties);

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

    public List<DeclsVariable> getDeclsVariables() {
        return declsVariables;
    }

    public void setDeclsVariables(List<DeclsVariable> declsVariables) {
        this.declsVariables = declsVariables;
    }

    public  String toString() {
        String res = "ppt " + packageName + "." +objectName + ":::OBJECT" + "\n" +
                "ppt-type object";

        for(DeclsVariable declsVariable: declsVariables) {
            res = res + "\n" + declsVariable;
        }

        return res;
    }

}
