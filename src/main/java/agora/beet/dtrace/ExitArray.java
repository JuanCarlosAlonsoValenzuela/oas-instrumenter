package agora.beet.dtrace;

import agora.beet.model.TestCase;
import agora.beet.util.TestCaseFileManager;
import org.json.simple.JSONArray;

import java.util.Arrays;

import static agora.beet.main.GenerateDeclsFile.*;
import static agora.beet.main.GenerateDeclsFile.HIERARCHY_SEPARATOR;

public class ExitArray {

    public static String generateDtraceExitValueOfJSONArray(TestCase testCase, JSONArray elements, String dectype,
                                                            String variableName) {
        String value = "nonsensical";

        // If elements == null, the elements are set to nonsensical
        if(elements != null){
            if(primitiveTypes.contains(dectype.replace("[]", ""))) { // If array of primitives
                boolean isString = false;
                if(dectype.replace("[]", "").equals(STRING_TYPE_NAME)) {
                    isString = true;
                }
                value = "";
                for(int i = 0; i < elements.size(); i++) {

                    if(isString) {
                        if(elements.get(i) == null || Arrays.asList(stringsToConsiderAsNull).contains(elements.get(i))) {
                            value = value + " " + null;
                        } else {
                            value = value + " \"" + TestCaseFileManager.removeNewLineChars((String) elements.get(i)) + "\"";
                        }
                    } else {
                        value = value + " " + elements.get(i);
                    }
                }

                value = "[" + value.trim() + "]";

            } else {    // If array of objects
                String hashcode = "";
                for(int i = 1; i <= elements.size(); i++) {
                    if(elements.get(i-1) != null) {
                        String v = "\"" + testCase.getTestCaseId() + HIERARCHY_SEPARATOR +
                                variableName.replace("[..]", "") + HIERARCHY_SEPARATOR + "output"
                                + HIERARCHY_SEPARATOR + i + "\"";
                        v = v.replace(HIERARCHY_SEPARATOR, "").replace("_", "");
                        hashcode = hashcode + Math.abs(v.hashCode()) + " ";
                    } else {
                        hashcode = hashcode + "null ";
                    }

                }

                value = "[" + hashcode.trim() + "]";
            }
        }
        return value;
    }

}
