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
import static es.us.isa.jsoninstrumenter.main.GenerateDeclsFile.packageName;
import static es.us.isa.jsoninstrumenter.model.DeclsClass.generateOutputDeclsClasses;
import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class NestedArraysPrimitiveOutputTest {

    @Test
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
                assertEquals("The size of the list of objects is not 3", 3, declsClassOutput.getDeclsObjects().size());
                assertEquals("The size of the list of enters is not 0", 0, declsClassOutput.getDeclsEnters().size());
                assertEquals("The size of the list of exits is not 0", 0, declsClassOutput.getDeclsExits().size());

                List<DeclsObject> declsObjectList = declsClassOutput.getDeclsObjects();
                // OBJECT 1
                DeclsObject declsObject1 = declsObjectList.get(0);
                assertEquals("Incorrect package name", packageName, declsObject1.getPackageName());
                assertEquals("Incorrect object name", operationName + "_Output_200", declsObject1.getObjectName());

                // VARIABLES
                // Father
                DeclsVariable declsFatherVariable1 = declsObject1.getDeclsVariables();
                assertEquals("Incorrect variable name", "this", declsFatherVariable1.getVariableName());
                assertEquals("Incorrect var-kind", "variable", declsFatherVariable1.getVarKind());
                assertEquals("Incorrect decType", packageName + ".sampleEndpointId_Output_200", declsFatherVariable1.getDecType());
                assertEquals("Incorrect repType", "hashcode", declsFatherVariable1.getRepType());
                assertNull("The enclosing var should be null", declsFatherVariable1.getEnclosingVar());
                assertFalse("This variable should not be an array", declsFatherVariable1.isArray());
                // Arrays are counted twice (normal and [..])
                assertEquals("Unexpected number of son variables", 2, declsFatherVariable1.getEnclosedVariables().size());

                // Sons (locations)
                List<DeclsVariable> declsSonVariables1 = declsFatherVariable1.getEnclosedVariables();

                DeclsVariable locations1 = declsSonVariables1.get(0);
                assertEquals("Incorrect variable name", "this.locations", locations1.getVariableName());
                assertEquals("Incorrect var-kind", "field locations", locations1.getVarKind());
                assertEquals("Incorrect decType", packageName + ".locations[]", locations1.getDecType());
                assertEquals("Incorrect repType", "hashcode", locations1.getRepType());
                assertEquals("Incorrect enclosing var", "this", locations1.getEnclosingVar());
                assertFalse("This variable should not be an array", locations1.isArray());
                assertEquals("Unexpected number of son variables", 0, locations1.getEnclosedVariables().size());

                DeclsVariable locations2 = declsSonVariables1.get(1);
                assertEquals("Incorrect variable name", "this.locations[..]", locations2.getVariableName());
                assertEquals("Incorrect var-kind", "array", locations2.getVarKind());
                assertEquals("Incorrect decType", packageName + ".locations[]", locations2.getDecType());
                assertEquals("Incorrect repType", "hashcode[]", locations2.getRepType());
                assertEquals("Incorrect enclosing var", "this.locations", locations2.getEnclosingVar());
                assertTrue("This variable should be an array", locations2.isArray());
                assertEquals("Unexpected number of son variables", 0, locations2.getEnclosedVariables().size());

                // SECOND OBJECT
                DeclsObject declsObject2 = declsObjectList.get(1);
                assertEquals("Incorrect package name", packageName, declsObject2.getPackageName());
                assertEquals("Incorrect object name", operationName + "_Output_200_locations.array.array", declsObject2.getObjectName());

                // VARIABLES
                // Father
                DeclsVariable declsFatherVariable2 = declsObject2.getDeclsVariables();
                assertEquals("Incorrect variable name", "this", declsFatherVariable2.getVariableName());
                assertEquals("Incorrect var-kind", "variable", declsFatherVariable2.getVarKind());
                assertEquals("Incorrect decType", packageName + ".sampleEndpointId_Output_200_locations.array.array", declsFatherVariable2.getDecType());
                assertEquals("Incorrect repType", "hashcode", declsFatherVariable2.getRepType());
                assertNull("The enclosing var should be null", declsFatherVariable2.getEnclosingVar());
                assertFalse("This variable should not be an array", declsFatherVariable2.isArray());
                // Arrays are counted twice (normal and [..])
                assertEquals("Unexpected number of son variables", 2, declsFatherVariable2.getEnclosedVariables().size());

                // Sons (locations)
                List<DeclsVariable> declsSonVariables2 = declsFatherVariable2.getEnclosedVariables();

                DeclsVariable array1 = declsSonVariables2.get(0);
                assertEquals("Incorrect variable name", "this.array", array1.getVariableName());
                assertEquals("Incorrect var-kind", "field array", array1.getVarKind());
                assertEquals("Incorrect decType", "double[]", array1.getDecType());
                assertEquals("Incorrect repType", "hashcode", array1.getRepType());
                assertEquals("Incorrect enclosing var", "this", array1.getEnclosingVar());
                assertFalse("This variable should not be an array", array1.isArray());
                assertEquals("Unexpected number of son variables", 0, array1.getEnclosedVariables().size());

                DeclsVariable array2 = declsSonVariables2.get(1);
                assertEquals("Incorrect variable name", "this.array[..]", array2.getVariableName());
                assertEquals("Incorrect var-kind", "array", array2.getVarKind());
                assertEquals("Incorrect decType", "double[]", array2.getDecType());
                assertEquals("Incorrect repType", "double[]", array2.getRepType());
                assertEquals("Incorrect enclosing var", "this.array", array2.getEnclosingVar());
                assertTrue("This variable should be an array", array2.isArray());
                assertEquals("Unexpected number of son variables", 0, array2.getEnclosedVariables().size());


                // THIRD OBJECT
                DeclsObject declsObject3 = declsObjectList.get(2);
                assertEquals("Incorrect package name", packageName, declsObject3.getPackageName());
                assertEquals("Incorrect object name", operationName + "_Output_200_locations.array", declsObject3.getObjectName());

                DeclsVariable declsFatherVariable3 = declsObject3.getDeclsVariables();
                assertEquals("Incorrect variable name", "this", declsFatherVariable3.getVariableName());
                assertEquals("Incorrect var-kind", "variable", declsFatherVariable3.getVarKind());
                assertEquals("Incorrect decType", packageName + ".sampleEndpointId_Output_200_locations.array", declsFatherVariable3.getDecType());
                assertEquals("Incorrect repType", "hashcode", declsFatherVariable3.getRepType());
                assertNull("The enclosing var should be null", declsFatherVariable3.getEnclosingVar());
                assertFalse("This variable should not be an array", declsFatherVariable3.isArray());
                // Arrays are counted twice (normal and [..])
                assertEquals("Unexpected number of son variables", 2, declsFatherVariable3.getEnclosedVariables().size());

                // Sons (locations)
                List<DeclsVariable> declsSonVariables3 = declsFatherVariable3.getEnclosedVariables();

                array1 = declsSonVariables3.get(0);
                assertEquals("Incorrect variable name", "this.array", array1.getVariableName());
                assertEquals("Incorrect var-kind", "field array", array1.getVarKind());
                assertEquals("Incorrect decType", packageName + ".array[]", array1.getDecType());
                assertEquals("Incorrect repType", "hashcode", array1.getRepType());
                assertEquals("Incorrect enclosing var", "this", array1.getEnclosingVar());
                assertFalse("This variable should not be an array", array1.isArray());
                assertEquals("Unexpected number of son variables", 0, array1.getEnclosedVariables().size());

                array2 = declsSonVariables3.get(1);
                assertEquals("Incorrect variable name", "this.array[..]", array2.getVariableName());
                assertEquals("Incorrect var-kind", "array", array2.getVarKind());
                assertEquals("Incorrect decType", packageName + ".array[]", array2.getDecType());
                assertEquals("Incorrect repType", "hashcode[]", array2.getRepType());
                assertEquals("Incorrect enclosing var", "this.array", array2.getEnclosingVar());
                assertTrue("This variable should be an array", array2.isArray());
                assertEquals("Unexpected number of son variables", 0, array2.getEnclosedVariables().size());

            }

        }


    }
}
