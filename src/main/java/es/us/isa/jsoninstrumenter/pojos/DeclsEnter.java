package es.us.isa.jsoninstrumenter.pojos;

import java.util.List;

public class DeclsEnter {

    private String enterName;
    private List<DeclsVariable> declsVariables;

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
