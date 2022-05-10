package es.us.isa.jsoninstrumenter.input;

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
import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class InputReadParametersFromPathAndFormTest {

    @Test
    public void testInputReadParametersFromPath() {
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

                DeclsClass declsClassInput = new DeclsClass(packageName, operationName + HIERARCHY_SEPARATOR + "Input", operationName + HIERARCHY_SEPARATOR + "Input", operation);

                System.out.println(declsClassInput);

                // CLASS
                assertEquals("Incorrect package name", packageName, declsClassInput.getPackageName());
                assertEquals("Incorrect class name", operationName + HIERARCHY_SEPARATOR + "Input", declsClassInput.getClassName());
                assertEquals("The size of the list of objects is not 1", 1, declsClassInput.getDeclsObjects().size());
                assertEquals("The size of the list of enters is not 0", 0, declsClassInput.getDeclsEnters().size());
                assertEquals("The size of the list of exits is not 0", 0, declsClassInput.getDeclsExits().size());

                // OBJECT
                DeclsObject declsObject = declsClassInput.getDeclsObjects().get(0);
                assertEquals("Incorrect package name", packageName, declsObject.getPackageName());
                assertEquals("Incorrect object name", operationName + HIERARCHY_SEPARATOR + "Input", declsObject.getObjectName());

                // VARIABLES
                // Father
                DeclsVariable declsFatherVariable = declsObject.getDeclsVariables();
                assertEquals("Incorrect variable name", "this", declsFatherVariable.getVariableName());
                assertEquals("Incorrect var-kind", "variable", declsFatherVariable.getVarKind());
                assertEquals("Incorrect decType", packageName + ".v1Name" + HIERARCHY_SEPARATOR + "Input", declsFatherVariable.getDecType());
                assertEquals("Incorrect repType", "hashcode", declsFatherVariable.getRepType());
                assertNull("The enclosing var should be null", declsFatherVariable.getEnclosingVar());
                assertFalse("This variable should not be an array", declsFatherVariable.isArray());
                assertEquals("Unexpected number of son variables", 2, declsFatherVariable.getEnclosedVariables().size());

                // Sons (name and fulltext)
                List<DeclsVariable> declsSonVariables = declsFatherVariable.getEnclosedVariables();
                DeclsVariable name = declsSonVariables.get(0);
                assertEquals("Incorrect variable name", "this.name", name.getVariableName());
                assertEquals("Incorrect var-kind", "field name", name.getVarKind());
                assertEquals("Incorrect decType", "java.lang.String", name.getDecType());
                assertEquals("Incorrect repType", "java.lang.String", name.getRepType());
                assertEquals("Incorrect enclosing var", "this", name.getEnclosingVar());
                assertFalse("This variable should not be an array", name.isArray());
                assertEquals("Unexpected number of son variables", 0, name.getEnclosedVariables().size());


                DeclsVariable fullText = declsSonVariables.get(1);
                assertEquals("Incorrect variable name", "this.fullText", fullText.getVariableName());
                assertEquals("Incorrect var-kind", "field fullText", fullText.getVarKind());
                assertEquals("Incorrect decType", "boolean", fullText.getDecType());
                assertEquals("Incorrect repType", "boolean", fullText.getRepType());
                assertEquals("Incorrect enclosing var", "this", fullText.getEnclosingVar());
                assertFalse("This variable should not be an array", fullText.isArray());
                assertEquals("Unexpected number of son variables", 0, fullText.getEnclosedVariables().size());

            }

        }

    }

    @Test
    public void testInputReadPrimitiveParametersFromForm() {
        deleteAllDeclsClasses();
        String oasPath = "src/test/resources/LanguageTool/swagger.yaml";

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

                DeclsClass declsClassInput = new DeclsClass(packageName, operationName + HIERARCHY_SEPARATOR + "Input", operationName + HIERARCHY_SEPARATOR + "Input", operation);

                System.out.println(declsClassInput);

                // CLASS
                assertEquals("Incorrect package name", packageName, declsClassInput.getPackageName());
                assertEquals("Incorrect class name", operationName + HIERARCHY_SEPARATOR + "Input", declsClassInput.getClassName());
                assertEquals("The size of the list of objects is not 1", 1, declsClassInput.getDeclsObjects().size());
                assertEquals("The size of the list of enters is not 0", 0, declsClassInput.getDeclsEnters().size());
                assertEquals("The size of the list of exits is not 0", 0, declsClassInput.getDeclsExits().size());

                // OBJECT
                DeclsObject declsObject = declsClassInput.getDeclsObjects().get(0);
                assertEquals("Incorrect package name", packageName, declsObject.getPackageName());
                assertEquals("Incorrect object name", operationName + HIERARCHY_SEPARATOR + "Input", declsObject.getObjectName());

                // VARIABLES
                // Father
                DeclsVariable declsFatherVariable = declsObject.getDeclsVariables();
                assertEquals("Incorrect variable name", "this", declsFatherVariable.getVariableName());
                assertEquals("Incorrect var-kind", "variable", declsFatherVariable.getVarKind());
                assertEquals("Incorrect decType", packageName + ".check" + HIERARCHY_SEPARATOR + "Input", declsFatherVariable.getDecType());
                assertEquals("Incorrect repType", "hashcode", declsFatherVariable.getRepType());
                assertNull("The enclosing var should be null", declsFatherVariable.getEnclosingVar());
                assertFalse("This variable should not be an array", declsFatherVariable.isArray());
                assertEquals("Unexpected number of son variables", 15, declsFatherVariable.getEnclosedVariables().size());

                // Sons (text and data)
                List<DeclsVariable> declsSonVariables = declsFatherVariable.getEnclosedVariables();
                DeclsVariable text = declsSonVariables.get(0);
                assertEquals("Incorrect variable name", "this.text", text.getVariableName());
                assertEquals("Incorrect var-kind", "field text", text.getVarKind());
                assertEquals("Incorrect decType", "java.lang.String", text.getDecType());
                assertEquals("Incorrect repType", "java.lang.String", text.getRepType());
                assertEquals("Incorrect enclosing var", "this", text.getEnclosingVar());
                assertFalse("This variable should not be an array", text.isArray());
                assertEquals("Unexpected number of son variables", 0, text.getEnclosedVariables().size());


                DeclsVariable data = declsSonVariables.get(1);
                assertEquals("Incorrect variable name", "this.data", data.getVariableName());
                assertEquals("Incorrect var-kind", "field data", data.getVarKind());
                assertEquals("Incorrect decType", "java.lang.String", data.getDecType());
                assertEquals("Incorrect repType", "java.lang.String", data.getRepType());
                assertEquals("Incorrect enclosing var", "this", data.getEnclosingVar());
                assertFalse("This variable should not be an array", data.isArray());
                assertEquals("Unexpected number of son variables", 0, data.getEnclosedVariables().size());

            }

        }



    }



}
