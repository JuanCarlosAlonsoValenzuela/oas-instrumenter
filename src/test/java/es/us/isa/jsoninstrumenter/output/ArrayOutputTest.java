package es.us.isa.jsoninstrumenter.output;

import es.us.isa.jsoninstrumenter.model.DeclsClass;
import es.us.isa.jsoninstrumenter.model.DeclsObject;
import es.us.isa.jsoninstrumenter.model.DeclsVariable;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.Operation;
import io.swagger.v3.oas.models.PathItem;
import io.swagger.v3.oas.models.Paths;
import io.swagger.v3.parser.OpenAPIV3Parser;
import io.swagger.v3.parser.core.models.ParseOptions;
import org.junit.Test;

import java.util.List;
import java.util.Map;

import static es.us.isa.jsoninstrumenter.main.GenerateDeclsFile.*;
import static es.us.isa.jsoninstrumenter.main.GenerateDeclsFile.packageName;
import static es.us.isa.jsoninstrumenter.model.DeclsClass.generateOutputDeclsClasses;
import static org.junit.Assert.*;

public class ArrayOutputTest {

    @Test
    public void testGenerationOfArrayExit() {
        deleteAllDeclsClasses();

        String oasPath = "src/test/resources/RestCountries/swagger.yaml";

        // Equivalent to the getOpenAPISpecification private function
        ParseOptions parseOptions = new ParseOptions();
        parseOptions.setResolveFully(true);
        parseOptions.setFlatten(true);
        OpenAPI specification = new OpenAPIV3Parser().read(oasPath, null, parseOptions);

        Paths paths = specification.getPaths();

        for(Map.Entry<String, PathItem> path: paths.entrySet()) {
            PathItem pathItem = path.getValue();

            for (Map.Entry<PathItem.HttpMethod, Operation> operationEntry: pathItem.readOperationsMap().entrySet()) {

                Operation operation = operationEntry.getValue();
                String operationEndpoint = path.getKey().replace("/", "");

                // Set the operation name for the .decls file
                String operationName = getOperationName(operation, operationEntry, operationEndpoint);

                generateOutputDeclsClasses(operationName, packageName, operation.getResponses());

                List<DeclsClass> allDeclsClasses = getAllDeclsClasses();

                assertEquals("The expected number of output classes is one", allDeclsClasses.size(), 1);

                DeclsClass declsClassOutput = allDeclsClasses.get(0);
                System.out.println(declsClassOutput);

                // CLASS
                assertEquals("Incorrect package name", packageName, declsClassOutput.getPackageName());
                assertEquals("Incorrect class name", operationName + "_Output_200", declsClassOutput.getClassName());
                assertEquals("The size of the list of objects is not 1", 2, declsClassOutput.getDeclsObjects().size());
                assertEquals("The size of the list of enters is not 0", 0, declsClassOutput.getDeclsEnters().size());
                assertEquals("The size of the list of exits is not 0", 0, declsClassOutput.getDeclsExits().size());

                // OBJECT 1
                DeclsObject declsObject1 = declsClassOutput.getDeclsObjects().get(0);
                assertEquals("Incorrect package name", packageName, declsObject1.getPackageName());
                assertEquals("Incorrect object name", operationName + "_Output_200.array", declsObject1.getObjectName());

                // VARIABLES
                // Father
                DeclsVariable declsFatherVariable1 = declsObject1.getDeclsVariables();
                assertEquals("Incorrect variable name", "this", declsFatherVariable1.getVariableName());
                assertEquals("Incorrect var-kind", "variable", declsFatherVariable1.getVarKind());
                assertEquals("Incorrect decType", packageName + ".v1Name_Output_200.array", declsFatherVariable1.getDecType());
                assertEquals("Incorrect repType", "java.lang.String", declsFatherVariable1.getRepType());
                assertNull("The enclosing var should be null", declsFatherVariable1.getEnclosingVar());
                assertFalse("This variable should not be an array", declsFatherVariable1.isArray());
                // The sons are url and location
                assertEquals("Unexpected number of son variables", 2, declsFatherVariable1.getEnclosedVariables().size());

                // Array type with another nested object
                DeclsVariable array1 = declsFatherVariable1.getEnclosedVariables().get(0);
                assertEquals("Incorrect variable name", "this.array",  array1.getVariableName());
                assertEquals("Incorrect var-kind", "field array", array1.getVarKind());
                assertEquals("Incorrect decType",  "main.array[]", array1.getDecType());
                assertEquals("Incorrect repType", "java.lang.String", array1.getRepType());
                assertEquals("Incorrect enclosing var", "this", array1.getEnclosingVar());
                assertFalse("This variable should not be an array", array1.isArray());
                assertEquals("Unexpected number of son variables", 0, array1.getEnclosedVariables().size());

                DeclsVariable array2 = declsFatherVariable1.getEnclosedVariables().get(1);
                assertEquals("Incorrect variable name", "this.array[..]",  array2.getVariableName());
                assertEquals("Incorrect var-kind", "array", array2.getVarKind());
                assertEquals("Incorrect decType",  "main.array[]", array2.getDecType());
                assertEquals("Incorrect repType", "java.lang.String[]", array2.getRepType());
                assertEquals("Incorrect enclosing var", "this.array", array2.getEnclosingVar());
                assertTrue("This variable should be an array", array2.isArray());
                assertEquals("Unexpected number of son variables", 0, array2.getEnclosedVariables().size());

                // OBJECT 2
                DeclsObject declsObject2 = declsClassOutput.getDeclsObjects().get(1);
                assertEquals("Incorrect package name", packageName, declsObject2.getPackageName());
                assertEquals("Incorrect object name", operationName + "_Output_200", declsObject2.getObjectName());

                // VARIABLES
                // Only the father
                DeclsVariable declsFatherVariable2 = declsObject2.getDeclsVariables();
                assertEquals("Incorrect variable name", "this", declsFatherVariable2.getVariableName());
                assertEquals("Incorrect var-kind", "variable", declsFatherVariable2.getVarKind());
                assertEquals("Incorrect decType", packageName + ".v1Name_Output_200", declsFatherVariable2.getDecType());
                assertEquals("Incorrect repType", "java.lang.String", declsFatherVariable2.getRepType());
                assertNull("The enclosing var should be null", declsFatherVariable2.getEnclosingVar());
                assertFalse("This variable should not be an array", declsFatherVariable2.isArray());
                // The sons are url and location
                assertEquals("Unexpected number of son variables", 29, declsFatherVariable2.getEnclosedVariables().size());

            }

        }

    }

}
