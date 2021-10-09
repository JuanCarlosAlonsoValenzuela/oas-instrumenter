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

import java.util.List;
import java.util.Map;

import static es.us.isa.jsoninstrumenter.main.GenerateDeclsFile.*;
import static es.us.isa.jsoninstrumenter.main.GenerateDeclsFile.packageName;
import static es.us.isa.jsoninstrumenter.pojos.DeclsClass.generateOutputDeclsClasses;
import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class NestedArraysResponseOutput {

    // Nested arrays of objects
    @Test
    public void testNestedArraysOfObjectsResponseOutput() {
        deleteAllDeclsClasses();
        String oasPath = "src/test/resources/RestCountries/swagger_nestedArraysResponse.yaml";

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

                assertEquals("The expected number of output classes is 1", 1, allDeclsClasses.size());

                DeclsClass declsClassOutput = allDeclsClasses.get(0);
                System.out.println(declsClassOutput);

                // CLASS
                assertEquals("Incorrect package name", packageName, declsClassOutput.getPackageName());
                assertEquals("Incorrect class name", operationName + "_Output_200", declsClassOutput.getClassName());
                assertEquals("The size of the list of objects is not 1", 4, declsClassOutput.getDeclsObjects().size());
                assertEquals("The size of the list of enters is not 0", 0, declsClassOutput.getDeclsEnters().size());
                assertEquals("The size of the list of exits is not 0", 0, declsClassOutput.getDeclsExits().size());

                // Check the properties of the arrays that represent the nested objects
                for(int i= 0; i<3; i++) {
                    String repeatedArray = new String(new char[i+1]).replace("\0", ".array");

                    DeclsObject declsObject = declsClassOutput.getDeclsObjects().get(i);
                    assertEquals("Incorrect package name", packageName, declsObject.getPackageName());
                    assertEquals("Incorrect object name", operationName + "_Output_200" + repeatedArray, declsObject.getObjectName());

                    // VARIABLES
                    // Father
                    DeclsVariable declsFatherVariable = declsObject.getDeclsVariables();
                    assertEquals("Incorrect variable name", "this", declsFatherVariable.getVariableName());
                    assertEquals("Incorrect var-kind", "variable", declsFatherVariable.getVarKind());
                    assertEquals("Incorrect decType", packageName + ".v1Name_Output_200" + repeatedArray, declsFatherVariable.getDecType());
                    assertEquals("Incorrect repType", "java.lang.String", declsFatherVariable.getRepType());
                    assertNull("The enclosing var should be null", declsFatherVariable.getEnclosingVar());
                    assertFalse("This variable should not be an array", declsFatherVariable.isArray());
                    assertEquals("Unexpected number of son variables", 2, declsFatherVariable.getEnclosedVariables().size());

                    // Sons (array)
                    List<DeclsVariable> declsSonVariables = declsFatherVariable.getEnclosedVariables();

                    DeclsVariable array1 = declsSonVariables.get(0);
                    assertEquals("Incorrect variable name", "this.array", array1.getVariableName());
                    assertEquals("Incorrect var-kind", "field array", array1.getVarKind());
                    assertEquals("Incorrect decType", packageName + ".array[]", array1.getDecType());
                    assertEquals("Incorrect repType", "java.lang.String", array1.getRepType());
                    assertEquals("Incorrect enclosing var", "this", array1.getEnclosingVar());
                    assertFalse("This variable should not be an array", array1.isArray());
                    assertEquals("Unexpected number of son variables", 0, array1.getEnclosedVariables().size());

                    DeclsVariable array2 = declsSonVariables.get(1);
                    assertEquals("Incorrect variable name", "this.array[..]", array2.getVariableName());
                    assertEquals("Incorrect var-kind", "array", array2.getVarKind());
                    assertEquals("Incorrect decType", "main.array[]", array2.getDecType());
                    assertEquals("Incorrect repType", "java.lang.String[]", array2.getRepType());
                    assertEquals("Incorrect enclosing var", "this.array", array2.getEnclosingVar());
                    assertTrue("This variable should be an array", array2.isArray());
                    assertEquals("Unexpected number of son variables", 0, array2.getEnclosedVariables().size());

                }

                DeclsObject declsObject4 = declsClassOutput.getDeclsObjects().get(3);
                assertEquals("Incorrect package name", packageName, declsObject4.getPackageName());
                assertEquals("Incorrect object name", operationName + "_Output_200", declsObject4.getObjectName());

                // VARIABLES
                // Father
                DeclsVariable declsFatherVariable = declsObject4.getDeclsVariables();
                assertEquals("Incorrect variable name", "this", declsFatherVariable.getVariableName());
                assertEquals("Incorrect var-kind", "variable", declsFatherVariable.getVarKind());
                assertEquals("Incorrect decType", packageName + ".v1Name_Output_200", declsFatherVariable.getDecType());
                assertEquals("Incorrect repType", "java.lang.String", declsFatherVariable.getRepType());
                assertNull("The enclosing var should be null", declsFatherVariable.getEnclosingVar());
                assertFalse("This variable should not be an array", declsFatherVariable.isArray());
                assertEquals("Unexpected number of son variables", 29, declsFatherVariable.getEnclosedVariables().size());

                // First son
                List<DeclsVariable> declsSonVariables = declsFatherVariable.getEnclosedVariables();

                DeclsVariable name = declsSonVariables.get(0);
                assertEquals("Incorrect variable name", "this.name", name.getVariableName());
                assertEquals("Incorrect var-kind", "field name", name.getVarKind());
                assertEquals("Incorrect decType", "java.lang.String", name.getDecType());
                assertEquals("Incorrect repType", "java.lang.String", name.getRepType());
                assertEquals("Incorrect enclosing var", "this", name.getEnclosingVar());
                assertFalse("This variable should not be an array", name.isArray());
                assertEquals("Unexpected number of son variables", 0, name.getEnclosedVariables().size());

            }

        }

    }


    // Nested array of primitives
    @Test
    public void testNestedArraysOfPrimitivesResponseOutput() {
        deleteAllDeclsClasses();
        String oasPath = "src/test/resources/RestCountries/swagger_nestedArraysResponse_primitive.yaml";

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

                assertEquals("The expected number of output classes is 1", 1, allDeclsClasses.size());

                DeclsClass declsClassOutput = allDeclsClasses.get(0);
                System.out.println(declsClassOutput);

                // CLASS
                assertEquals("Incorrect package name", packageName, declsClassOutput.getPackageName());
                assertEquals("Incorrect class name", operationName + "_Output_200", declsClassOutput.getClassName());
                assertEquals("The size of the list of objects is not 3", 3, declsClassOutput.getDeclsObjects().size());
                assertEquals("The size of the list of enters is not 0", 0, declsClassOutput.getDeclsEnters().size());
                assertEquals("The size of the list of exits is not 0", 0, declsClassOutput.getDeclsExits().size());

                // Check the properties of the arrays that represent the nested objects
                for(int i= 0; i<2; i++) {
                    String repeatedArray = new String(new char[i+1]).replace("\0", ".array");

                    DeclsObject declsObject = declsClassOutput.getDeclsObjects().get(i);
                    assertEquals("Incorrect package name", packageName, declsObject.getPackageName());
                    assertEquals("Incorrect object name", operationName + "_Output_200" + repeatedArray, declsObject.getObjectName());

                    // VARIABLES
                    // Father
                    DeclsVariable declsFatherVariable = declsObject.getDeclsVariables();
                    assertEquals("Incorrect variable name", "this", declsFatherVariable.getVariableName());
                    assertEquals("Incorrect var-kind", "variable", declsFatherVariable.getVarKind());
                    assertEquals("Incorrect decType", packageName + ".v1Name_Output_200" + repeatedArray, declsFatherVariable.getDecType());
                    assertEquals("Incorrect repType", "java.lang.String", declsFatherVariable.getRepType());
                    assertNull("The enclosing var should be null", declsFatherVariable.getEnclosingVar());
                    assertFalse("This variable should not be an array", declsFatherVariable.isArray());
                    assertEquals("Unexpected number of son variables", 2, declsFatherVariable.getEnclosedVariables().size());

                    // Sons (array)
                    List<DeclsVariable> declsSonVariables = declsFatherVariable.getEnclosedVariables();

                    DeclsVariable array1 = declsSonVariables.get(0);
                    assertEquals("Incorrect variable name", "this.array", array1.getVariableName());
                    assertEquals("Incorrect var-kind", "field array", array1.getVarKind());
                    assertEquals("Incorrect decType", packageName + ".array[]", array1.getDecType());
                    assertEquals("Incorrect repType", "java.lang.String", array1.getRepType());
                    assertEquals("Incorrect enclosing var", "this", array1.getEnclosingVar());
                    assertFalse("This variable should not be an array", array1.isArray());
                    assertEquals("Unexpected number of son variables", 0, array1.getEnclosedVariables().size());

                    DeclsVariable array2 = declsSonVariables.get(1);
                    assertEquals("Incorrect variable name", "this.array[..]", array2.getVariableName());
                    assertEquals("Incorrect var-kind", "array", array2.getVarKind());
                    assertEquals("Incorrect decType", "main.array[]", array2.getDecType());
                    assertEquals("Incorrect repType", "java.lang.String[]", array2.getRepType());
                    assertEquals("Incorrect enclosing var", "this.array", array2.getEnclosingVar());
                    assertTrue("This variable should be an array", array2.isArray());
                    assertEquals("Unexpected number of son variables", 0, array2.getEnclosedVariables().size());

                }

                DeclsObject declsObject = declsClassOutput.getDeclsObjects().get(2);
                assertEquals("Incorrect package name", packageName, declsObject.getPackageName());
                assertEquals("Incorrect object name", operationName + "_Output_200.array.array.array", declsObject.getObjectName());

                // VARIABLES
                // Father
                DeclsVariable declsFatherVariable = declsObject.getDeclsVariables();
                assertEquals("Incorrect variable name", "this", declsFatherVariable.getVariableName());
                assertEquals("Incorrect var-kind", "variable", declsFatherVariable.getVarKind());
                assertEquals("Incorrect decType", packageName + ".v1Name_Output_200.array.array.array", declsFatherVariable.getDecType());
                assertEquals("Incorrect repType", "java.lang.String", declsFatherVariable.getRepType());
                assertNull("The enclosing var should be null", declsFatherVariable.getEnclosingVar());
                assertFalse("This variable should not be an array", declsFatherVariable.isArray());
                assertEquals("Unexpected number of son variables", 2, declsFatherVariable.getEnclosedVariables().size());

                // Sons (array)
                List<DeclsVariable> declsSonVariables = declsFatherVariable.getEnclosedVariables();

                DeclsVariable array1 = declsSonVariables.get(0);
                assertEquals("Incorrect variable name", "this.array", array1.getVariableName());
                assertEquals("Incorrect var-kind", "field array", array1.getVarKind());
                assertEquals("Incorrect decType", "double[]", array1.getDecType());
                assertEquals("Incorrect repType", "java.lang.String", array1.getRepType());
                assertEquals("Incorrect enclosing var", "this", array1.getEnclosingVar());
                assertFalse("This variable should not be an array", array1.isArray());
                assertEquals("Unexpected number of son variables", 0, array1.getEnclosedVariables().size());

                DeclsVariable array2 = declsSonVariables.get(1);
                assertEquals("Incorrect variable name", "this.array[..]", array2.getVariableName());
                assertEquals("Incorrect var-kind", "array", array2.getVarKind());
                assertEquals("Incorrect decType", "double[]", array2.getDecType());
                assertEquals("Incorrect repType", "double[]", array2.getRepType());
                assertEquals("Incorrect enclosing var", "this.array", array2.getEnclosingVar());
                assertTrue("This variable should be an array", array2.isArray());
                assertEquals("Unexpected number of son variables", 0, array2.getEnclosedVariables().size());

            }

        }

    }

}
