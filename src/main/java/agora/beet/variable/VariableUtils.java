package agora.beet.variable;

import static agora.beet.main.GenerateDeclsFile.*;
import static agora.beet.main.GenerateDeclsFile.STRING_TYPE_NAME;

public class VariableUtils {

    // Converts the datatype name from OAS to daikon
    // Returns String by default
    public static String translateDatatype(String input) {

        switch (input.toLowerCase()) {
            case "number":
                return DOUBLE_TYPE_NAME;
            case "integer":
                return INTEGER_TYPE_NAME;
            case "boolean":
                return BOOLEAN_TYPE_NAME;
            case "object":
                return OBJECT_TYPE_NAME;
            case "array":
                return ARRAY_TYPE_NAME;
            default:    // Including case "string"
                return STRING_TYPE_NAME;
        }

    }

}
