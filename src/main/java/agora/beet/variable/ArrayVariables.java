package agora.beet.variable;

import agora.beet.model.DeclsVariable;
import io.swagger.v3.oas.models.media.ArraySchema;

import java.util.ArrayList;
import java.util.List;

import static agora.beet.main.GenerateDeclsFile.*;
import static agora.beet.variable.VariableUtils.translateDatatype;

public class ArrayVariables {

    public static DeclsVariable generateDeclsVariablesOfArray(ArraySchema arraySchema, String objectName, String variableName, String varKind, String enclosingVar){
        DeclsVariable res = generateDeclsVariablesOfArray(arraySchema, objectName, variableName, varKind);
        res.setEnclosingVar(enclosingVar);
        return res;
    }


    // Used when the return type is an array of objects (Bad practice)
    // Used for both output and exit
    public static DeclsVariable generateDeclsVariablesOfArray(ArraySchema arraySchema, String objectName, String variableName, String varKind) {

        DeclsVariable father = new DeclsVariable(variableName, varKind, objectName, HASHCODE_TYPE_NAME, null);

        List<DeclsVariable> enclosedVars;
        String itemsDatatype = arraySchema.getItems().getType();

        String translatedDatatype = translateDatatype(itemsDatatype);
        if(primitiveTypes.contains(translatedDatatype) && !itemsDatatype.equals(OBJECT_TYPE_NAME)) {
            enclosedVars = getDeclsVariablesArray(variableName, ARRAY_TYPE_NAME, translatedDatatype,
                    translatedDatatype);
        } else{
            enclosedVars = getDeclsVariablesArray(variableName, ARRAY_TYPE_NAME, ARRAY_TYPE_NAME,
                    HASHCODE_TYPE_NAME);
        }

        father.setEnclosedVariables(enclosedVars);

        return father;
    }

    public static List<DeclsVariable> getDeclsVariablesArray(String variablePath, String parameterName,
                                                             String decType, String repType) {
        List<DeclsVariable> res = new ArrayList<>();

        String arrayIndicator = "[]";

        String variableName = variablePath + "." + parameterName;
        // The enclosing var does not contain the name of the variable (this)
        res.add(new DeclsVariable(variableName, "field " + parameterName, decType + arrayIndicator, HASHCODE_TYPE_NAME, variablePath));

        // The enclosing var name contains the name of the variable (this.array)
        res.add(new DeclsVariable(variableName + "[..]", ARRAY_TYPE_NAME, decType + arrayIndicator,
                repType  + arrayIndicator, variableName));

        return res;
    }

}
