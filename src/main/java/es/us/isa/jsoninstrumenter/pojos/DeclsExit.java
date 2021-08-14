package es.us.isa.jsoninstrumenter.pojos;

import io.swagger.v3.oas.models.media.Schema;

import java.util.List;
import java.util.Map;

import static es.us.isa.jsoninstrumenter.pojos.DeclsVariable.*;

public class DeclsExit {

    private String exitName;
    private int exitNumber;
    private List<DeclsVariable> enterDeclsVariables;
    private List<DeclsVariable> exitDeclsVariables;


    public DeclsExit(String packageName, String EnterExitPptDeclaration, List<DeclsVariable> enterVariables,
                     String variableNameOutput, Map<String, Schema> mapOfProperties, int exitNumber) {
        // TODO: convert exit name to function (same as enterName)
        this.exitName = EnterExitPptDeclaration;
        this.exitNumber = exitNumber;
        this.enterDeclsVariables = enterVariables;

        // TODO: Change singleton to normal list (a method can have several exits)
        // TODO: for loop increasing exitNumber
        this.exitDeclsVariables = generateDeclsVariablesOfOutput("return", "return", packageName,  variableNameOutput, mapOfProperties);
//

    }

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

    public List<DeclsVariable> getEnterDeclsVariables() {
        return enterDeclsVariables;
    }

    public void setEnterDeclsVariables(List<DeclsVariable> enterDeclsVariables) {
        this.enterDeclsVariables = enterDeclsVariables;
    }

    public List<DeclsVariable> getExitDeclsVariables() {
        return exitDeclsVariables;
    }

    public void setExitDeclsVariables(List<DeclsVariable> exitDeclsVariables) {
        this.exitDeclsVariables = exitDeclsVariables;
    }

    // TODO: Consider changing the type from subexit to subexit in certain cases
    public String toString() {
        String res = "ppt " + exitName + ":::EXIT" + exitNumber + "\n" +
                "ppt-type subexit";


//        res = res + "\n" + enterDeclsVariables;
//        res = res + "\n" + exitDeclsVariables;

        for(DeclsVariable enterDeclsVariable: this.enterDeclsVariables) {
            res = res + "\n" + enterDeclsVariable;
        }

        for(DeclsVariable exitDeclsVariable: this.exitDeclsVariables) {
            res = res + "\n" + exitDeclsVariable;
        }

        return res;
    }

}
