package es.us.isa.jsoninstrumenter.input;

import es.us.isa.jsoninstrumenter.pojos.DeclsClass;
import es.us.isa.jsoninstrumenter.pojos.DeclsObject;
import es.us.isa.jsoninstrumenter.pojos.DeclsVariable;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.Paths;
import io.swagger.v3.parser.OpenAPIV3Parser;
import io.swagger.v3.parser.core.models.ParseOptions;
import org.junit.Assert;
import org.junit.Test;
import io.swagger.v3.oas.models.PathItem;
import static org.junit.Assert.*;

import io.swagger.v3.oas.models.Operation;
import io.swagger.v3.oas.models.PathItem.HttpMethod;


import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import static es.us.isa.jsoninstrumenter.main.GenerateDeclsFile.getOperationName;
import static es.us.isa.jsoninstrumenter.main.GenerateDeclsFile.packageName;


public class BasicInputTest {

    // jUnit for swagger with multiple operations

    @Test
    public void testGenerationOfBasicInputs() {
        String oasPath = "src/test/resources/airportInfo/OpenAPISpec.yaml";

        // Equivalent to the getOpenAPISpecification private function
        ParseOptions parseOptions = new ParseOptions();
        parseOptions.setResolveFully(true);
        parseOptions.setFlatten(true);
        OpenAPI specification = new OpenAPIV3Parser().read(oasPath, null, parseOptions);

        Paths paths = specification.getPaths();

        List<DeclsClass> declsClassList = new ArrayList<>();

        for(Entry<String, PathItem> path: paths.entrySet()) {
            PathItem pathItem = path.getValue();

            for (Entry<HttpMethod, Operation> operationEntry: pathItem.readOperationsMap().entrySet()) {

                Operation operation = operationEntry.getValue();
                String operationEndpoint = path.getKey().replace("/", "");

                // Set the operation name for the .decls file
                String operationName = getOperationName(operation, operationEntry, operationEndpoint);

                DeclsClass declsClassInput = new DeclsClass(packageName, operationName + "_Input", operationName + "_Input", operation.getParameters());

                System.out.println(declsClassInput);

                // CLASS
                assertEquals("Incorrect package name", packageName, declsClassInput.getPackageName());
                assertEquals("Incorrect class name", operationName + "_Input", declsClassInput.getClassName());
                assertEquals("The size of the list of objects is not 1", 1, declsClassInput.getDeclsObjects().size());
                assertEquals("The size of the list of enters is not 0", 0, declsClassInput.getDeclsEnters().size());
                assertEquals("The size of the list of exits is not 0", 0, declsClassInput.getDeclsExits().size());

                // OBJECT
                DeclsObject declsObject = declsClassInput.getDeclsObjects().get(0);
                assertEquals("Incorrect package name", packageName, declsObject.getPackageName());
                assertEquals("Incorrect object name", operationName + "_Input", declsObject.getObjectName());
                assertEquals("The size of the list of variables is not 1", 1, declsObject.getDeclsVariables().size());

                // VARIABLES
                // Father
                DeclsVariable declsFatherVariable = declsObject.getDeclsVariables().get(0);
                assertEquals("Incorrect variable name", "this", declsFatherVariable.getVariableName());
                assertEquals("Incorrect var-kind", "variable", declsFatherVariable.getVarKind());
                assertEquals("Incorrect decType", packageName + ".findAirports_Input", declsFatherVariable.getDecType());
                assertEquals("Incorrect repType", "java.lang.String", declsFatherVariable.getRepType());
                assertNull("The enclosing var should be null", declsFatherVariable.getEnclosingVar());
                assertFalse("This variable should not be an array", declsFatherVariable.isArray());
                assertEquals("Unexpected number of son variables", 2, declsFatherVariable.getEnclosedVariables().size());

                // Sons (iata and icao)
                List<DeclsVariable> declsSonVariables = declsFatherVariable.getEnclosedVariables();
                DeclsVariable iata = declsSonVariables.get(0);
                assertEquals("Incorrect variable name", "this.iata", iata.getVariableName());
                assertEquals("Incorrect var-kind", "field iata", iata.getVarKind());
                assertEquals("Incorrect decType", "java.lang.String", iata.getDecType());
                assertEquals("Incorrect repType", "java.lang.String", iata.getRepType());
                assertEquals("Incorrect enclosing var", "this", iata.getEnclosingVar());
                assertFalse("This variable should not be an array", iata.isArray());
                assertEquals("Unexpected number of son variables", 0, iata.getEnclosedVariables().size());


                DeclsVariable icao = declsSonVariables.get(1);
                assertEquals("Incorrect variable name", "this.icao", icao.getVariableName());
                assertEquals("Incorrect var-kind", "field icao", icao.getVarKind());
                assertEquals("Incorrect decType", "java.lang.String", icao.getDecType());
                assertEquals("Incorrect repType", "java.lang.String", icao.getRepType());
                assertEquals("Incorrect enclosing var", "this", icao.getEnclosingVar());
                assertFalse("This variable should not be an array", icao.isArray());
                assertEquals("Unexpected number of son variables", 0, icao.getEnclosedVariables().size());

                declsClassList.add(declsClassInput);

            }

        }

        assertEquals("The expected number of classes is one", declsClassList.size(), 1);



    }

    // TODO: Create a test for classes (Check toString)
    // TODO: Create the test for blank lines
    // TODO: Test cases for datatypes
}
