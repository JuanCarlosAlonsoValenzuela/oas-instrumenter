package es.us.isa.jsoninstrumenter.exit;

import es.us.isa.jsoninstrumenter.pojos.DeclsClass;
import es.us.isa.jsoninstrumenter.pojos.DeclsExit;
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
import static es.us.isa.jsoninstrumenter.pojos.DeclsClass.setDeclsClassEnterAndExit;
import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class NestedArraysObjectExitTest {

    @Test
    @Ignore
    public void testGenerationOfNestedArraysObjectExit() {

        deleteAllDeclsClasses();

        String oasPath = "src/test/resources/sampleAPI/swagger_nestedArraysObject.yaml";

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

                // Extracting the input parameters
                String objectName = operationName + "_Input";

                setDeclsClassEnterAndExit(packageName, operationEndpoint, operationName,
                        objectName, operation);

                List<DeclsClass> allDeclsClasses = getAllDeclsClasses();
                assertEquals("Incorrect number of classes", allDeclsClasses.size(), 1);

                DeclsClass declsClassEnterAndExit = allDeclsClasses.get(0);

                System.out.println(declsClassEnterAndExit);

                // CLASS
                assertEquals("Incorrect package name", packageName, declsClassEnterAndExit.getPackageName());
                assertEquals("Incorrect class name", "sampleEndpoint", declsClassEnterAndExit.getClassName());
                assertEquals("The size of the list of objects is not 0", 0, declsClassEnterAndExit.getDeclsObjects().size());
                assertEquals("The size of the list of enters is not 1", 1, declsClassEnterAndExit.getDeclsEnters().size());
                assertEquals("The size of the list of exits is not 1", 1, declsClassEnterAndExit.getDeclsExits().size());

                // OBJECT (ONLY EXIT)
                DeclsExit declsExit = declsClassEnterAndExit.getDeclsExits().get(0);
                String exitName = packageName + "." + operationEndpoint + "." + operationName + "(" + packageName + "." + operationName + "_" + "Input" + ")";

                assertEquals("Incorrect exit name", exitName, declsExit.getExitName());
//                assertEquals("The size of the list of enter variables is not 1", 1, declsExit.getEnterDeclsVariables().size());
//                assertEquals("The size of the list of exit variables is not 1", 1, declsExit.getExitDeclsVariables().size());
                assertEquals("The exit number is not correct", numberOfExits, declsExit.getExitNumber() + 1);

                // VARIABLES
                // ENTER
                // Only Father
                DeclsVariable enterDeclsFatherVariable = declsExit.getEnterDeclsVariables();
                assertEquals("Incorrect variable name", "input", enterDeclsFatherVariable.getVariableName());
                assertEquals("Incorrect var-kind", "variable", enterDeclsFatherVariable.getVarKind());
                assertEquals("Incorrect decType", packageName + ".sampleEndpointId_Input", enterDeclsFatherVariable.getDecType());
                assertEquals("Incorrect repType", "java.lang.String", enterDeclsFatherVariable.getRepType());
                assertNull("The enclosing var should be null", enterDeclsFatherVariable.getEnclosingVar());
                assertFalse("This variable should not be an array", enterDeclsFatherVariable.isArray());
                assertEquals("Unexpected number of son variables", 2, enterDeclsFatherVariable.getEnclosedVariables().size());

                // EXIT
                // Father
                DeclsVariable exitDeclsFatherVariable = declsExit.getExitDeclsVariables();

                assertEquals("Incorrect variable name", "return", exitDeclsFatherVariable.getVariableName());
                assertEquals("Incorrect var-kind", "return", exitDeclsFatherVariable.getVarKind());
                assertEquals("Incorrect decType", packageName + ".sampleEndpointId_Output_200", exitDeclsFatherVariable.getDecType());
                assertEquals("Incorrect repType", "java.lang.String", exitDeclsFatherVariable.getRepType());
                assertNull("The enclosing var should be null", exitDeclsFatherVariable.getEnclosingVar());
                assertFalse("This variable should not be an array", exitDeclsFatherVariable.isArray());
                assertEquals("Unexpected number of son variables", 2, exitDeclsFatherVariable.getEnclosedVariables().size());

                // Sons (locations)
                List<DeclsVariable> declsSonVariables = exitDeclsFatherVariable.getEnclosedVariables();

                DeclsVariable locations1 = declsSonVariables.get(0);
                assertEquals("Incorrect variable name", "return.locations", locations1.getVariableName());
                assertEquals("Incorrect var-kind", "field locations", locations1.getVarKind());
                assertEquals("Incorrect decType", "main.locations[][][]", locations1.getDecType());
                assertEquals("Incorrect repType", "java.lang.String[][][]", locations1.getRepType());
                assertEquals("Incorrect enclosing var", "return", locations1.getEnclosingVar());
                assertFalse("This variable should not be an array", locations1.isArray());
                assertEquals("Unexpected number of son variables", 0, locations1.getEnclosedVariables().size());

                DeclsVariable locations2 = declsSonVariables.get(1);
                assertEquals("Incorrect variable name", "return.locations[..]", locations2.getVariableName());
                assertEquals("Incorrect var-kind", "array", locations2.getVarKind());
                assertEquals("Incorrect decType", "main.locations", locations2.getDecType());
                assertEquals("Incorrect repType", "java.lang.String", locations2.getRepType());
                assertEquals("Incorrect enclosing var", "return.locations", locations2.getEnclosingVar());
                assertTrue("This variable should be an array", locations2.isArray());
                assertEquals("Unexpected number of son variables", 2, locations2.getEnclosedVariables().size());

                // Sons of the object locations (locationId and provider)
                List<DeclsVariable> sonsOfLocation = locations2.getEnclosedVariables();

                DeclsVariable locationId = sonsOfLocation.get(0);
                assertEquals("Incorrect variable name", "return.locations[..].locationId", locationId.getVariableName());
                assertEquals("Incorrect var-kind", "field locationId", locationId.getVarKind());
                assertEquals("Incorrect decType", "java.lang.String", locationId.getDecType());
                assertEquals("Incorrect repType", "java.lang.String", locationId.getRepType());
                assertEquals("Incorrect enclosing var", "return.locations[..]", locationId.getEnclosingVar());
                assertTrue("This variable should be an array", locationId.isArray());
                assertEquals("Unexpected number of son variables", 0, locationId.getEnclosedVariables().size());

                DeclsVariable provider = sonsOfLocation.get(1);
                assertEquals("Incorrect variable name", "return.locations[..].provider", provider.getVariableName());
                assertEquals("Incorrect var-kind", "field provider", provider.getVarKind());
                assertEquals("Incorrect decType", "java.lang.String", provider.getDecType());
                assertEquals("Incorrect repType", "java.lang.String", provider.getRepType());
                assertEquals("Incorrect enclosing var", "return.locations[..]", provider.getEnclosingVar());
                assertTrue("This variable should be an array", provider.isArray());
                assertEquals("Unexpected number of son variables", 0, provider.getEnclosedVariables().size());

            }


        }


    }

}
