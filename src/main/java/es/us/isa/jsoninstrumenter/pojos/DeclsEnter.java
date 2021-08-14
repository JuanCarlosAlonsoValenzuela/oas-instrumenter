package es.us.isa.jsoninstrumenter.pojos;

import io.swagger.v3.oas.models.parameters.Parameter;

import java.util.Collections;
import java.util.List;

import static es.us.isa.jsoninstrumenter.pojos.DeclsVariable.getListOfDeclsVariables;

public class DeclsEnter {

    private String enterName;
    private List<DeclsVariable> declsVariables;

    // TODO: Add flag is_param? (See example in demo001)
    public DeclsEnter(String packageName,  String enterName, String variableName, String rootVariableName, List<Parameter> parameters) {
        this.enterName = enterName;
        this.declsVariables = Collections.singletonList(getListOfDeclsVariables(packageName, variableName, rootVariableName, parameters));

    }

    public String getEnterName() {
        return enterName;
    }

    public void setEnterName(String enterName) {
        this.enterName = enterName;
    }

    public List<DeclsVariable> getDeclsVariables() {
        return declsVariables;
    }

    public void setDeclsVariables(List<DeclsVariable> declsVariables) {
        this.declsVariables = declsVariables;
    }

    public String toString() {
        String res = "ppt " + enterName + ":::ENTER" + "\n" +
                "ppt-type enter";

        for(DeclsVariable declsVariable: declsVariables) {
            res = res + "\n" + declsVariable;
        }

        return res;
    }

}
