package es.us.isa.jsoninstrumenter.output;

import es.us.isa.jsoninstrumenter.pojos.DeclsClass;
import es.us.isa.jsoninstrumenter.pojos.DeclsObject;
import es.us.isa.jsoninstrumenter.pojos.DeclsVariable;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.Operation;
import io.swagger.v3.oas.models.PathItem;
import io.swagger.v3.oas.models.Paths;
import io.swagger.v3.parser.OpenAPIV3Parser;
import io.swagger.v3.parser.core.models.ParseOptions;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static es.us.isa.jsoninstrumenter.main.GenerateDeclsFile.*;
import static es.us.isa.jsoninstrumenter.pojos.DeclsClass.generateOutputDeclsClasses;
import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class PrimitiveArrayOutputTest {

    @Test
    public void testGenerationOfPrimitiveOutputArray() {
        deleteAllDeclsClasses();

        String oasPath = "src/test/resources/DHL/swagger_primitiveOutputArray.yaml";

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
                assertEquals("The size of the list of objects is not 1", 1, declsClassOutput.getDeclsObjects().size());
                assertEquals("The size of the list of enters is not 0", 0, declsClassOutput.getDeclsEnters().size());
                assertEquals("The size of the list of exits is not 0", 0, declsClassOutput.getDeclsExits().size());

                // OBJECT
                DeclsObject declsObject = declsClassOutput.getDeclsObjects().get(0);
                assertEquals("Incorrect package name", packageName, declsObject.getPackageName());
                assertEquals("Incorrect object name", operationName + "_Output_200", declsObject.getObjectName());
                assertEquals("The size of the list of variables is not 1", 1, declsObject.getDeclsVariables().size());

                // VARIABLES
                // Father
                DeclsVariable declsFatherVariable = declsObject.getDeclsVariables().get(0);
                assertEquals("Incorrect variable name", "this", declsFatherVariable.getVariableName());
                assertEquals("Incorrect var-kind", "variable", declsFatherVariable.getVarKind());
                assertEquals("Incorrect decType", packageName + ".findByAddress_Output_200", declsFatherVariable.getDecType());
                assertEquals("Incorrect repType", "java.lang.String", declsFatherVariable.getRepType());
                assertNull("The enclosing var should be null", declsFatherVariable.getEnclosingVar());
                assertFalse("This variable should not be an array", declsFatherVariable.isArray());
                // The array is counted twice (normal and [..])
                assertEquals("Unexpected number of son variables", 5, declsFatherVariable.getEnclosedVariables().size());

                // Sons
                List<DeclsVariable> declsSonVariables = declsFatherVariable.getEnclosedVariables();
                // url, name, distance, servicesTypes (twice)
                DeclsVariable url = declsSonVariables.get(0);
                assertEquals("Incorrect variable name", "this.url", url.getVariableName());
                assertEquals("Incorrect var-kind", "field url", url.getVarKind());
                assertEquals("Incorrect decType", "java.lang.String", url.getDecType());
                assertEquals("Incorrect repType", "java.lang.String", url.getRepType());
                assertEquals("Incorrect enclosing var", "this", url.getEnclosingVar());
                assertFalse("This variable should not be an array", url.isArray());
                assertEquals("Unexpected number of son variables", 0, url.getEnclosedVariables().size());

                DeclsVariable name = declsSonVariables.get(1);
                assertEquals("Incorrect variable name", "this.name", name.getVariableName());
                assertEquals("Incorrect var-kind", "field name", name.getVarKind());
                assertEquals("Incorrect decType", "java.lang.String", name.getDecType());
                assertEquals("Incorrect repType", "java.lang.String", name.getRepType());
                assertEquals("Incorrect enclosing var", "this", name.getEnclosingVar());
                assertFalse("This variable should not be an array", name.isArray());
                assertEquals("Unexpected number of son variables", 0, name.getEnclosedVariables().size());

                DeclsVariable distance = declsSonVariables.get(2);
                assertEquals("Incorrect variable name", "this.distance", distance.getVariableName());
                assertEquals("Incorrect var-kind", "field distance", distance.getVarKind());
                assertEquals("Incorrect decType", "double", distance.getDecType());
                assertEquals("Incorrect repType", "double", distance.getRepType());
                assertEquals("Incorrect enclosing var", "this", distance.getEnclosingVar());
                assertFalse("This variable should not be an array", distance.isArray());
                assertEquals("Unexpected number of son variables", 0, distance.getEnclosedVariables().size());

                // Analyzing the parameters of type array:
                DeclsVariable serviceTypes1 = declsSonVariables.get(3);
                assertEquals("Incorrect variable name", "this.serviceTypes", serviceTypes1.getVariableName());
                assertEquals("Incorrect var-kind", "field serviceTypes", serviceTypes1.getVarKind());
                assertEquals("Incorrect decType", "java.lang.String[]", serviceTypes1.getDecType());
                assertEquals("Incorrect repType", "java.lang.String", serviceTypes1.getRepType());
                assertEquals("Incorrect enclosing var", "this", serviceTypes1.getEnclosingVar());
                // The first ocurrence should not be an array (flaga array 1)
                assertFalse("This variable should not be an array", serviceTypes1.isArray());
                assertEquals("Unexpected number of son variables", 0, serviceTypes1.getEnclosedVariables().size());

                DeclsVariable serviceTypes2 = declsSonVariables.get(4);
                assertEquals("Incorrect variable name", "this.serviceTypes[..]", serviceTypes2.getVariableName());
                assertEquals("Incorrect var-kind", "array", serviceTypes2.getVarKind());
                assertEquals("Incorrect decType", "java.lang.String[]", serviceTypes2.getDecType());
                assertEquals("Incorrect repType", "java.lang.String[]", serviceTypes2.getRepType());
                assertEquals("Incorrect enclosing var", "this.serviceTypes", serviceTypes2.getEnclosingVar());
                // The first ocurrence should not be an array (flaga array 1)
                assertTrue("This variable should be an array", serviceTypes2.isArray());
                assertEquals("Unexpected number of son variables", 0, serviceTypes2.getEnclosedVariables().size());

            }

        }


    }

}
