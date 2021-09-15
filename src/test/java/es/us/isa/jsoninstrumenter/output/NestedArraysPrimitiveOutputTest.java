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
import org.junit.Ignore;
import org.junit.Test;

import java.util.List;
import java.util.Map;

import static es.us.isa.jsoninstrumenter.main.GenerateDeclsFile.*;
import static es.us.isa.jsoninstrumenter.main.GenerateDeclsFile.packageName;
import static es.us.isa.jsoninstrumenter.pojos.DeclsClass.generateOutputDeclsClasses;
import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class NestedArraysPrimitiveOutputTest {

    @Test
    @Ignore
    public void testGenerationOfNestedArraysPrimitiveOutput() {
        deleteAllDeclsClasses();

        String oasPath = "src/test/resources/sampleAPI/swagger_nestedArraysPrimitive.yaml";

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
                assertEquals("The size of the list of variables is not 1", 1, declsObject.getDeclsVariables());

                // VARIABLES
                // Father
                DeclsVariable declsFatherVariable = declsObject.getDeclsVariables();
                assertEquals("Incorrect variable name", "this", declsFatherVariable.getVariableName());
                assertEquals("Incorrect var-kind", "variable", declsFatherVariable.getVarKind());
                assertEquals("Incorrect decType", packageName + ".sampleEndpointId_Output_200", declsFatherVariable.getDecType());
                assertEquals("Incorrect repType", "java.lang.String", declsFatherVariable.getRepType());
                assertNull("The enclosing var should be null", declsFatherVariable.getEnclosingVar());
                assertFalse("This variable should not be an array", declsFatherVariable.isArray());
                // Arrays are counted twice (normal and [..])
                assertEquals("Unexpected number of son variables", 2, declsFatherVariable.getEnclosedVariables().size());

                // Sons (locations)
                List<DeclsVariable> declsSonVariables = declsFatherVariable.getEnclosedVariables();

                DeclsVariable locations1 = declsSonVariables.get(0);
                assertEquals("Incorrect variable name", "this.locations", locations1.getVariableName());
                assertEquals("Incorrect var-kind", "field locations", locations1.getVarKind());
                assertEquals("Incorrect decType", "java.lang.String[][][]", locations1.getDecType());
                assertEquals("Incorrect repType", "java.lang.String[][][]", locations1.getRepType());
                assertEquals("Incorrect enclosing var", "this", locations1.getEnclosingVar());
                assertFalse("This variable should not be an array", locations1.isArray());
                assertEquals("Unexpected number of son variables", 0, locations1.getEnclosedVariables().size());

                DeclsVariable locations2 = declsSonVariables.get(1);
                assertEquals("Incorrect variable name", "this.locations[..]", locations2.getVariableName());
                assertEquals("Incorrect var-kind", "array", locations2.getVarKind());
                assertEquals("Incorrect decType", "java.lang.String", locations2.getDecType());
                assertEquals("Incorrect repType", "java.lang.String", locations2.getRepType());
                assertEquals("Incorrect enclosing var", "this.locations", locations2.getEnclosingVar());
                assertTrue("This variable should be an array", locations2.isArray());
                assertEquals("Unexpected number of son variables", 0, locations2.getEnclosedVariables().size());

            }

        }


    }
}
