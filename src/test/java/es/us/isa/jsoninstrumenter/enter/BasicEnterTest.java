package es.us.isa.jsoninstrumenter.enter;

import es.us.isa.jsoninstrumenter.pojos.DeclsClass;
import es.us.isa.jsoninstrumenter.pojos.DeclsEnter;
import es.us.isa.jsoninstrumenter.pojos.DeclsVariable;
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
import static es.us.isa.jsoninstrumenter.pojos.DeclsClass.setDeclsClassEnterAndExit;
import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class BasicEnterTest {

    @Test
    public void testGenerationOfBasicEnter() {
        deleteAllDeclsClasses();

        String oasPath = "src/test/resources/airportInfo/swagger_simplified.yaml";

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

                setDeclsClassEnterAndExit(packageName, operationEndpoint, operationName,
                        "Input", operation.getParameters(), operation.getResponses());

                List<DeclsClass> allDeclsClasses = getAllDeclsClasses();
                assertEquals("Incorrect number of classes", allDeclsClasses.size(), 1);

                DeclsClass declsClassEnterAndExit = allDeclsClasses.get(0);

                System.out.println(declsClassEnterAndExit);

                // CLASS
                assertEquals("Incorrect package name", packageName, declsClassEnterAndExit.getPackageName());
                assertEquals("Incorrect class name", operationEndpoint, declsClassEnterAndExit.getClassName());
                assertEquals("The size of the list of objects is not 0", 0, declsClassEnterAndExit.getDeclsObjects().size());
                assertEquals("The size of the list of enters is not 1", 1, declsClassEnterAndExit.getDeclsEnters().size());
                assertEquals("The size of the list of exits is not 1", 1, declsClassEnterAndExit.getDeclsExits().size());


                // OBJECT (ONLY ENTER)
                DeclsEnter declsEnter = declsClassEnterAndExit.getDeclsEnters().get(0);
                String enterName = packageName + "." + operationEndpoint + "." + operationName + "(" + packageName + "." + operationName + "_" + "Input" + ")";

                assertEquals("Incorrect enter name", enterName, declsEnter.getEnterName());


                // VARIABLES

                // Father
                DeclsVariable declsFatherVariable = declsEnter.getDeclsVariables();
                assertEquals("Incorrect variable name", "input", declsFatherVariable.getVariableName());
                assertEquals("Incorrect var-kind", "variable", declsFatherVariable.getVarKind());
                assertEquals("Incorrect decType", packageName + ".findAirports_Input", declsFatherVariable.getDecType());
                assertEquals("Incorrect repType", "java.lang.String", declsFatherVariable.getRepType());
                assertNull("The enclosing var should be null", declsFatherVariable.getEnclosingVar());
                assertFalse("This variable should not be an array", declsFatherVariable.isArray());
                assertEquals("Unexpected number of son variables", 2, declsFatherVariable.getEnclosedVariables().size());

                // Sons
                List<DeclsVariable> declsSonVariables = declsFatherVariable.getEnclosedVariables();
                DeclsVariable iata = declsSonVariables.get(0);
                assertEquals("Incorrect variable name", "input.iata", iata.getVariableName());
                assertEquals("Incorrect var-kind", "field iata", iata.getVarKind());
                assertEquals("Incorrect decType", "java.lang.String", iata.getDecType());
                assertEquals("Incorrect repType", "java.lang.String", iata.getRepType());
                assertEquals("Incorrect enclosing var", "input", iata.getEnclosingVar());
                assertFalse("This variable should not be an array", iata.isArray());
                assertEquals("Unexpected number of son variables", 0, iata.getEnclosedVariables().size());


                DeclsVariable icao = declsSonVariables.get(1);
                assertEquals("Incorrect variable name", "input.icao", icao.getVariableName());
                assertEquals("Incorrect var-kind", "field icao", icao.getVarKind());
                assertEquals("Incorrect decType", "java.lang.String", icao.getDecType());
                assertEquals("Incorrect repType", "java.lang.String", icao.getRepType());
                assertEquals("Incorrect enclosing var", "input", icao.getEnclosingVar());
                assertFalse("This variable should not be an array", icao.isArray());
                assertEquals("Unexpected number of son variables", 0, icao.getEnclosedVariables().size());




            }

            assertEquals("The expected total number of classes is one", getAllDeclsClasses().size(), 1);

        }

    }

}
