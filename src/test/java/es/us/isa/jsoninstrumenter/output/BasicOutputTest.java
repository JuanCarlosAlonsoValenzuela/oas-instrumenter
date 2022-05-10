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
import static es.us.isa.jsoninstrumenter.model.DeclsClass.generateOutputDeclsClasses;
import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class BasicOutputTest {


    // Operation with a single response
    @Test
    public void testGenerationOfBasicOutputs() {
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

                generateOutputDeclsClasses(operationName, packageName, operation.getResponses());

                List<DeclsClass> allDeclsClasses = getAllDeclsClasses();

                assertEquals("The expected number of output classes is one", 1, allDeclsClasses.size());

                DeclsClass declsClassOutput = allDeclsClasses.get(0);
                System.out.println(declsClassOutput);

                // CLASS
                assertEquals("Incorrect package name", packageName, declsClassOutput.getPackageName());
                assertEquals("Incorrect class name", operationName + HIERARCHY_SEPARATOR + "Output" + HIERARCHY_SEPARATOR + "200", declsClassOutput.getClassName());
                assertEquals("The size of the list of objects is not 1", 1, declsClassOutput.getDeclsObjects().size());
                assertEquals("The size of the list of enters is not 0", 0, declsClassOutput.getDeclsEnters().size());
                assertEquals("The size of the list of exits is not 0", 0, declsClassOutput.getDeclsExits().size());

                // OBJECT
                DeclsObject declsObject = declsClassOutput.getDeclsObjects().get(0);
                assertEquals("Incorrect package name", packageName, declsObject.getPackageName());
                assertEquals("Incorrect object name", operationName + HIERARCHY_SEPARATOR + "Output" + HIERARCHY_SEPARATOR + "200", declsObject.getObjectName());

                // VARIABLES
                // Father
                DeclsVariable declsFatherVariable = declsObject.getDeclsVariables();
                assertEquals("Incorrect variable name", "this", declsFatherVariable.getVariableName());
                assertEquals("Incorrect var-kind", "variable", declsFatherVariable.getVarKind());
                assertEquals("Incorrect decType", packageName + ".findAirports" + HIERARCHY_SEPARATOR + "Output" + HIERARCHY_SEPARATOR + "200", declsFatherVariable.getDecType());
                assertEquals("Incorrect repType", "hashcode", declsFatherVariable.getRepType());
                assertNull("The enclosing var should be null", declsFatherVariable.getEnclosingVar());
                assertFalse("This variable should not be an array", declsFatherVariable.isArray());
                assertEquals("Unexpected number of son variables", 5, declsFatherVariable.getEnclosedVariables().size());

                // Sons (id, iata, icao, name, version)
                List<DeclsVariable> declsSonVariables = declsFatherVariable.getEnclosedVariables();

                DeclsVariable id = declsSonVariables.get(0);
                assertEquals("Incorrect variable name", "this.id", id.getVariableName());
                assertEquals("Incorrect var-kind", "field id", id.getVarKind());
                assertEquals("Incorrect decType", "int", id.getDecType());
                assertEquals("Incorrect repType", "int", id.getRepType());
                assertEquals("Incorrect enclosing var", "this", id.getEnclosingVar());
                assertFalse("This variable should not be an array", id.isArray());
                assertEquals("Unexpected number of son variables", 0, id.getEnclosedVariables().size());

                DeclsVariable iata = declsSonVariables.get(1);
                assertEquals("Incorrect variable name", "this.iata", iata.getVariableName());
                assertEquals("Incorrect var-kind", "field iata", iata.getVarKind());
                assertEquals("Incorrect decType", "java.lang.String", iata.getDecType());
                assertEquals("Incorrect repType", "java.lang.String", iata.getRepType());
                assertEquals("Incorrect enclosing var", "this", iata.getEnclosingVar());
                assertFalse("This variable should not be an array", iata.isArray());
                assertEquals("Unexpected number of son variables", 0, iata.getEnclosedVariables().size());

                DeclsVariable icao = declsSonVariables.get(2);
                assertEquals("Incorrect variable name", "this.icao", icao.getVariableName());
                assertEquals("Incorrect var-kind", "field icao", icao.getVarKind());
                assertEquals("Incorrect decType", "java.lang.String", icao.getDecType());
                assertEquals("Incorrect repType", "java.lang.String", icao.getRepType());
                assertEquals("Incorrect enclosing var", "this", icao.getEnclosingVar());
                assertFalse("This variable should not be an array", icao.isArray());
                assertEquals("Unexpected number of son variables", 0, icao.getEnclosedVariables().size());

                DeclsVariable name = declsSonVariables.get(3);
                assertEquals("Incorrect variable name", "this.name", name.getVariableName());
                assertEquals("Incorrect var-kind", "field name", name.getVarKind());
                assertEquals("Incorrect decType", "java.lang.String", name.getDecType());
                assertEquals("Incorrect repType", "java.lang.String", name.getRepType());
                assertEquals("Incorrect enclosing var", "this", name.getEnclosingVar());
                assertFalse("This variable should not be an array", name.isArray());
                assertEquals("Unexpected number of son variables", 0, name.getEnclosedVariables().size());

                DeclsVariable version = declsSonVariables.get(4);
                assertEquals("Incorrect variable name", "this.version", version.getVariableName());
                assertEquals("Incorrect var-kind", "field version", version.getVarKind());
                assertEquals("Incorrect decType", "double", version.getDecType());
                assertEquals("Incorrect repType", "double", version.getRepType());
                assertEquals("Incorrect enclosing var", "this", version.getEnclosingVar());
                assertFalse("This variable should not be an array", version.isArray());
                assertEquals("Unexpected number of son variables", 0, version.getEnclosedVariables().size());

            }

        }

    }




}
