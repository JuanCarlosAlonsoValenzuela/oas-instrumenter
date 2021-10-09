package es.us.isa.jsoninstrumenter.input;

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

import java.util.List;
import java.util.Map;

import static es.us.isa.jsoninstrumenter.main.GenerateDeclsFile.*;
import static es.us.isa.jsoninstrumenter.main.GenerateDeclsFile.packageName;
import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class InputReadBodyParameters {

    @Test
    public void testInputReadPrimitiveParametersFromBody() {
        deleteAllDeclsClasses();
        String oasPath = "src/test/resources/Spotify_createPlaylist/spec.yaml";

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

                DeclsClass declsClassInput = new DeclsClass(packageName, operationName + "_Input", operationName + "_Input", operation);

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

                // VARIABLES
                // Father
                DeclsVariable declsFatherVariable = declsObject.getDeclsVariables();
                assertEquals("Incorrect variable name", "this", declsFatherVariable.getVariableName());
                assertEquals("Incorrect var-kind", "variable", declsFatherVariable.getVarKind());
                assertEquals("Incorrect decType", packageName + ".createPlaylist_Input", declsFatherVariable.getDecType());
                assertEquals("Incorrect repType", "java.lang.String", declsFatherVariable.getRepType());
                assertNull("The enclosing var should be null", declsFatherVariable.getEnclosingVar());
                assertFalse("This variable should not be an array", declsFatherVariable.isArray());
                assertEquals("Unexpected number of son variables", 5, declsFatherVariable.getEnclosedVariables().size());

                // Sons
                List<DeclsVariable> declsSonVariables = declsFatherVariable.getEnclosedVariables();
                DeclsVariable user_id = declsSonVariables.get(0);
                assertEquals("Incorrect variable name", "this.user_id", user_id.getVariableName());
                assertEquals("Incorrect var-kind", "field user_id", user_id.getVarKind());
                assertEquals("Incorrect decType", "java.lang.String", user_id.getDecType());
                assertEquals("Incorrect repType", "java.lang.String", user_id.getRepType());
                assertEquals("Incorrect enclosing var", "this", user_id.getEnclosingVar());
                assertFalse("This variable should not be an array", user_id.isArray());
                assertEquals("Unexpected number of son variables", 0, user_id.getEnclosedVariables().size());

                DeclsVariable Accept = declsSonVariables.get(1);
                assertEquals("Incorrect variable name", "this.Accept", Accept.getVariableName());
                assertEquals("Incorrect var-kind", "field Accept", Accept.getVarKind());
                assertEquals("Incorrect decType", "java.lang.String", Accept.getDecType());
                assertEquals("Incorrect repType", "java.lang.String", Accept.getRepType());
                assertEquals("Incorrect enclosing var", "this", Accept.getEnclosingVar());
                assertFalse("This variable should not be an array", Accept.isArray());
                assertEquals("Unexpected number of son variables", 0, Accept.getEnclosedVariables().size());

                // Name, description and public are body parameters
                DeclsVariable name = declsSonVariables.get(2);
                assertEquals("Incorrect variable name", "this.name", name.getVariableName());
                assertEquals("Incorrect var-kind", "field name", name.getVarKind());
                assertEquals("Incorrect decType", "java.lang.String", name.getDecType());
                assertEquals("Incorrect repType", "java.lang.String", name.getRepType());
                assertEquals("Incorrect enclosing var", "this", name.getEnclosingVar());
                assertFalse("This variable should not be an array", name.isArray());
                assertEquals("Unexpected number of son variables", 0, name.getEnclosedVariables().size());

                DeclsVariable description = declsSonVariables.get(3);
                assertEquals("Incorrect variable name", "this.description", description.getVariableName());
                assertEquals("Incorrect var-kind", "field description", description.getVarKind());
                assertEquals("Incorrect decType", "java.lang.String", description.getDecType());
                assertEquals("Incorrect repType", "java.lang.String", description.getRepType());
                assertEquals("Incorrect enclosing var", "this", description.getEnclosingVar());
                assertFalse("This variable should not be an array", description.isArray());
                assertEquals("Unexpected number of son variables", 0, description.getEnclosedVariables().size());

                DeclsVariable isPublic = declsSonVariables.get(4);
                assertEquals("Incorrect variable name", "this.public", isPublic.getVariableName());
                assertEquals("Incorrect var-kind", "field public", isPublic.getVarKind());
                assertEquals("Incorrect decType", "boolean", isPublic.getDecType());
                assertEquals("Incorrect repType", "boolean", isPublic.getRepType());
                assertEquals("Incorrect enclosing var", "this", isPublic.getEnclosingVar());
                assertFalse("This variable should not be an array", isPublic.isArray());
                assertEquals("Unexpected number of son variables", 0, isPublic.getEnclosedVariables().size());

            }

        }

    }

    // Nested objects in body parameter
    // It also contains primitive arrays
    @Test
    public void testInputReadNestedObjectParametersFromBody() {
        deleteAllDeclsClasses();
        String oasPath = "src/test/resources/Spotify_createPlaylist/spec_nestedObjects_body.yaml";

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

                DeclsClass declsClassInput = new DeclsClass(packageName, operationName + "_Input", operationName + "_Input", operation);

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

                // VARIABLES
                // Father
                DeclsVariable declsFatherVariable = declsObject.getDeclsVariables();
                assertEquals("Incorrect variable name", "this", declsFatherVariable.getVariableName());
                assertEquals("Incorrect var-kind", "variable", declsFatherVariable.getVarKind());
                assertEquals("Incorrect decType", packageName + ".createPlaylist_Input", declsFatherVariable.getDecType());
                assertEquals("Incorrect repType", "java.lang.String", declsFatherVariable.getRepType());
                assertNull("The enclosing var should be null", declsFatherVariable.getEnclosingVar());
                assertFalse("This variable should not be an array", declsFatherVariable.isArray());
                assertEquals("Unexpected number of son variables", 5, declsFatherVariable.getEnclosedVariables().size());

                // Sons
                List<DeclsVariable> declsSonVariables = declsFatherVariable.getEnclosedVariables();
                DeclsVariable user_id = declsSonVariables.get(0);
                assertEquals("Incorrect variable name", "this.user_id", user_id.getVariableName());
                assertEquals("Incorrect var-kind", "field user_id", user_id.getVarKind());
                assertEquals("Incorrect decType", "java.lang.String", user_id.getDecType());
                assertEquals("Incorrect repType", "java.lang.String", user_id.getRepType());
                assertEquals("Incorrect enclosing var", "this", user_id.getEnclosingVar());
                assertFalse("This variable should not be an array", user_id.isArray());
                assertEquals("Unexpected number of son variables", 0, user_id.getEnclosedVariables().size());

                DeclsVariable Accept = declsSonVariables.get(1);
                assertEquals("Incorrect variable name", "this.Accept", Accept.getVariableName());
                assertEquals("Incorrect var-kind", "field Accept", Accept.getVarKind());
                assertEquals("Incorrect decType", "java.lang.String", Accept.getDecType());
                assertEquals("Incorrect repType", "java.lang.String", Accept.getRepType());
                assertEquals("Incorrect enclosing var", "this", Accept.getEnclosingVar());
                assertFalse("This variable should not be an array", Accept.isArray());
                assertEquals("Unexpected number of son variables", 0, Accept.getEnclosedVariables().size());

                // Name, description and public are body parameters
                DeclsVariable name = declsSonVariables.get(2);
                assertEquals("Incorrect variable name", "this.name", name.getVariableName());
                assertEquals("Incorrect var-kind", "field name", name.getVarKind());
                assertEquals("Incorrect decType", "java.lang.String", name.getDecType());
                assertEquals("Incorrect repType", "java.lang.String", name.getRepType());
                assertEquals("Incorrect enclosing var", "this", name.getEnclosingVar());
                assertFalse("This variable should not be an array", name.isArray());
                assertEquals("Unexpected number of son variables", 0, name.getEnclosedVariables().size());

                DeclsVariable description = declsSonVariables.get(3);
                assertEquals("Incorrect variable name", "this.description", description.getVariableName());
                assertEquals("Incorrect var-kind", "field description", description.getVarKind());
                assertEquals("Incorrect decType", "java.lang.String", description.getDecType());
                assertEquals("Incorrect repType", "java.lang.String", description.getRepType());
                assertEquals("Incorrect enclosing var", "this", description.getEnclosingVar());
                assertFalse("This variable should not be an array", description.isArray());
                assertEquals("Unexpected number of son variables", 0, description.getEnclosedVariables().size());

                DeclsVariable location = declsSonVariables.get(4);
                assertEquals("Incorrect variable name", "this.location", location.getVariableName());
                assertEquals("Incorrect var-kind", "variable", location.getVarKind());
                assertEquals("Incorrect decType", packageName + ".createPlaylist_Input_location", location.getDecType());
                assertEquals("Incorrect repType", "java.lang.String", location.getRepType());
                assertEquals("Incorrect enclosing var", "this", location.getEnclosingVar());
                assertFalse("This variable should not be an array", location.isArray());
                assertEquals("Unexpected number of son variables", 3, location.getEnclosedVariables().size());

                List<DeclsVariable> locationSons = location.getEnclosedVariables();

                DeclsVariable locationId = locationSons.get(0);
                assertEquals("Incorrect variable name", "this.location.locationId", locationId.getVariableName());
                assertEquals("Incorrect var-kind", "field locationId", locationId.getVarKind());
                assertEquals("Incorrect decType", "java.lang.String", locationId.getDecType());
                assertEquals("Incorrect repType", "java.lang.String", locationId.getRepType());
                assertEquals("Incorrect enclosing var", "this.location", locationId.getEnclosingVar());
                assertFalse("This variable should not be an array", locationId.isArray());
                assertEquals("Unexpected number of son variables", 0, locationId.getEnclosedVariables().size());

                DeclsVariable keywords1 = locationSons.get(1);
                assertEquals("Incorrect variable name", "this.location.keywords", keywords1.getVariableName());
                assertEquals("Incorrect var-kind", "field keywords", keywords1.getVarKind());
                assertEquals("Incorrect decType", "java.lang.String[]", keywords1.getDecType());
                assertEquals("Incorrect repType", "java.lang.String", keywords1.getRepType());
                assertEquals("Incorrect enclosing var", "this.location", keywords1.getEnclosingVar());
                assertFalse("This variable should not be an array", keywords1.isArray());
                assertEquals("Unexpected number of son variables", 0, keywords1.getEnclosedVariables().size());

                DeclsVariable keywords2 = locationSons.get(2);
                assertEquals("Incorrect variable name", "this.location.keywords[..]", keywords2.getVariableName());
                assertEquals("Incorrect var-kind", "array", keywords2.getVarKind());
                assertEquals("Incorrect decType", "java.lang.String[]", keywords2.getDecType());
                assertEquals("Incorrect repType", "java.lang.String[]", keywords2.getRepType());
                assertEquals("Incorrect enclosing var", "this.location.keywords", keywords2.getEnclosingVar());
                assertTrue("This variable should be an array", keywords2.isArray());
                assertEquals("Unexpected number of son variables", 0, keywords2.getEnclosedVariables().size());

            }

        }

    }
}
