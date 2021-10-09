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

public class EnterReadBodyParameters {

    @Test
    public void testEnterReadPrimitiveParametersFromBody() {
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
                assertEquals("Incorrect class name", operationEndpoint, declsClassEnterAndExit.getClassName());
                assertEquals("The size of the list of objects is not 0", 0, declsClassEnterAndExit.getDeclsObjects().size());
                assertEquals("The size of the list of enters is not 3", 3, declsClassEnterAndExit.getDeclsEnters().size());
                assertEquals("The size of the list of exits is not 3", 3, declsClassEnterAndExit.getDeclsExits().size());


                // ONLY ENTERS
                DeclsEnter declsEnter1 = declsClassEnterAndExit.getDeclsEnters().get(0);
                String enterName1 = packageName + "." + operationEndpoint + "." + operationName +  "_201(" + packageName + "." + operationName + "_" + "Input" + ")";
                assertEquals("Incorrect enter name", enterName1, declsEnter1.getEnterName());

                DeclsEnter declsEnter2 = declsClassEnterAndExit.getDeclsEnters().get(1);
                String enterName2 = packageName + "." + operationEndpoint + "." + operationName +  "_201_images(" + packageName + "." + operationName + "_" + "Input" + ")";
                assertEquals("Incorrect enter name", enterName2, declsEnter2.getEnterName());

                DeclsEnter declsEnter3 = declsClassEnterAndExit.getDeclsEnters().get(2);
                String enterName3 = packageName + "." + operationEndpoint + "." + operationName +  "_201_tracks_items(" + packageName + "." + operationName + "_" + "Input" + ")";
                assertEquals("Incorrect enter name", enterName3, declsEnter3.getEnterName());

                // Father
                DeclsVariable declsFatherVariable1 = declsEnter1.getDeclsVariables();
                assertEquals("Incorrect variable name", "input", declsFatherVariable1.getVariableName());
                assertEquals("Incorrect var-kind", "variable", declsFatherVariable1.getVarKind());
                assertEquals("Incorrect decType", packageName + ".createPlaylist_Input", declsFatherVariable1.getDecType());
                assertEquals("Incorrect repType", "java.lang.String", declsFatherVariable1.getRepType());
                assertNull("The enclosing var should be null", declsFatherVariable1.getEnclosingVar());
                assertFalse("This variable should not be an array", declsFatherVariable1.isArray());
                assertEquals("Unexpected number of son variables", 5, declsFatherVariable1.getEnclosedVariables().size());

                // Sons
                List<DeclsVariable> declsSonVariables1 = declsFatherVariable1.getEnclosedVariables();
                DeclsVariable user_id = declsSonVariables1.get(0);
                assertEquals("Incorrect variable name", "input.user_id", user_id.getVariableName());
                assertEquals("Incorrect var-kind", "field user_id", user_id.getVarKind());
                assertEquals("Incorrect decType", "java.lang.String", user_id.getDecType());
                assertEquals("Incorrect repType", "java.lang.String", user_id.getRepType());
                assertEquals("Incorrect enclosing var", "input", user_id.getEnclosingVar());
                assertFalse("This variable should not be an array", user_id.isArray());
                assertEquals("Unexpected number of son variables", 0, user_id.getEnclosedVariables().size());

                DeclsVariable Accept = declsSonVariables1.get(1);
                assertEquals("Incorrect variable name", "input.Accept", Accept.getVariableName());
                assertEquals("Incorrect var-kind", "field Accept", Accept.getVarKind());
                assertEquals("Incorrect decType", "java.lang.String", Accept.getDecType());
                assertEquals("Incorrect repType", "java.lang.String", Accept.getRepType());
                assertEquals("Incorrect enclosing var", "input", Accept.getEnclosingVar());
                assertFalse("This variable should not be an array", Accept.isArray());
                assertEquals("Unexpected number of son variables", 0, Accept.getEnclosedVariables().size());

                // Name, description and public are body parameters
                DeclsVariable name = declsSonVariables1.get(2);
                assertEquals("Incorrect variable name", "input.name", name.getVariableName());
                assertEquals("Incorrect var-kind", "field name", name.getVarKind());
                assertEquals("Incorrect decType", "java.lang.String", name.getDecType());
                assertEquals("Incorrect repType", "java.lang.String", name.getRepType());
                assertEquals("Incorrect enclosing var", "input", name.getEnclosingVar());
                assertFalse("This variable should not be an array", name.isArray());
                assertEquals("Unexpected number of son variables", 0, name.getEnclosedVariables().size());

                DeclsVariable description = declsSonVariables1.get(3);
                assertEquals("Incorrect variable name", "input.description", description.getVariableName());
                assertEquals("Incorrect var-kind", "field description", description.getVarKind());
                assertEquals("Incorrect decType", "java.lang.String", description.getDecType());
                assertEquals("Incorrect repType", "java.lang.String", description.getRepType());
                assertEquals("Incorrect enclosing var", "input", description.getEnclosingVar());
                assertFalse("This variable should not be an array", description.isArray());
                assertEquals("Unexpected number of son variables", 0, description.getEnclosedVariables().size());

                DeclsVariable isPublic = declsSonVariables1.get(4);
                assertEquals("Incorrect variable name", "input.public", isPublic.getVariableName());
                assertEquals("Incorrect var-kind", "field public", isPublic.getVarKind());
                assertEquals("Incorrect decType", "boolean", isPublic.getDecType());
                assertEquals("Incorrect repType", "boolean", isPublic.getRepType());
                assertEquals("Incorrect enclosing var", "input", isPublic.getEnclosingVar());
                assertFalse("This variable should not be an array", isPublic.isArray());
                assertEquals("Unexpected number of son variables", 0, isPublic.getEnclosedVariables().size());

            }

            assertEquals("The expected total number of classes is one", getAllDeclsClasses().size(), 1);

        }

    }
}
