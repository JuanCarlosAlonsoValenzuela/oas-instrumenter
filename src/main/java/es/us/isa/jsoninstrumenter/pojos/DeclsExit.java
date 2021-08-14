package es.us.isa.jsoninstrumenter.pojos;

import java.util.List;

public class DeclsExit {

    private String exitName;
    private int exitNumber;
    private List<DeclsVariable> declsVariables;

    public String getExitName() {
        return exitName;
    }

    public void setExitName(String exitName) {
        this.exitName = exitName;
    }

    public int getExitNumber() {
        return exitNumber;
    }

    public void setExitNumber(int exitNumber) {
        this.exitNumber = exitNumber;
    }

    public List<DeclsVariable> getDeclsVariables() {
        return declsVariables;
    }

    public void setDeclsVariables(List<DeclsVariable> declsVariables) {
        this.declsVariables = declsVariables;
    }

    // TODO: Consider changing the type from subexit to subexit in certain cases
    public String toString() {
        String res = "ppt " + exitName + ":::EXIT" + exitNumber + "\n" +
                "ppt-type subexit";

        for(DeclsVariable declsVariable: declsVariables) {
            res = res + "\n" + declsVariable;
        }

        return res;
    }

}
