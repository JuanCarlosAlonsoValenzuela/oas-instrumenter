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

public class NestedArraysObjectOutputTest {

    @Test
    public void testGenerationOfNestedArraysObjectOutput(){

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

                generateOutputDeclsClasses(operationName, packageName, operation.getResponses());

                List<DeclsClass> allDeclsClasses = getAllDeclsClasses();

                assertEquals("The expected number of output classes is one", allDeclsClasses.size(), 1);

                DeclsClass declsClassOutput = allDeclsClasses.get(0);
                System.out.println(declsClassOutput);

                // CLASS
                assertEquals("Incorrect package name", packageName, declsClassOutput.getPackageName());
                assertEquals("Incorrect class name", operationName + HIERARCHY_SEPARATOR + "Output" + HIERARCHY_SEPARATOR + "200", declsClassOutput.getClassName());
                assertEquals("The size of the list of objects is not 4", 4, declsClassOutput.getDeclsObjects().size());
                assertEquals("The size of the list of enters is not 0", 0, declsClassOutput.getDeclsEnters().size());
                assertEquals("The size of the list of exits is not 0", 0, declsClassOutput.getDeclsExits().size());

                List<DeclsObject> declsObjectList = declsClassOutput.getDeclsObjects();

                // OBJECT 1
                DeclsObject declsObject1 = declsObjectList.get(0);
                assertEquals("Incorrect package name", packageName, declsObject1.getPackageName());
                assertEquals("Incorrect object name", operationName + HIERARCHY_SEPARATOR +"Output" + HIERARCHY_SEPARATOR + "200", declsObject1.getObjectName());

                // VARIABLES
                // Father
                DeclsVariable declsFatherVariable1 = declsObject1.getDeclsVariables();
                assertEquals("Incorrect variable name", "this", declsFatherVariable1.getVariableName());
                assertEquals("Incorrect var-kind", "variable", declsFatherVariable1.getVarKind());
                assertEquals("Incorrect decType", packageName + ".sampleEndpointId" + HIERARCHY_SEPARATOR + "Output" + HIERARCHY_SEPARATOR + "200", declsFatherVariable1.getDecType());
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
                assertEquals("Incorrect decType", "main.locations[]", locations1.getDecType());
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


                String repeatedArray = ".array";
                DeclsObject declsObject = declsObjectList.get(1);

                // VARIABLES
                // Father
                DeclsVariable declsFatherVariable = declsObject.getDeclsVariables();
                assertEquals("Incorrect variable name", "this", declsFatherVariable.getVariableName());
                assertEquals("Incorrect var-kind", "variable", declsFatherVariable.getVarKind());
                assertEquals("Incorrect decType", packageName + ".sampleEndpointId" + HIERARCHY_SEPARATOR + "Output" + HIERARCHY_SEPARATOR + "200" + HIERARCHY_SEPARATOR + "locations" + repeatedArray, declsFatherVariable.getDecType());
                assertEquals("Incorrect repType", "hashcode", declsFatherVariable.getRepType());
                assertNull("The enclosing var should be null", declsFatherVariable.getEnclosingVar());
                assertFalse("This variable should not be an array", declsFatherVariable.isArray());
                // Arrays are counted twice (normal and [..])
                assertEquals("Unexpected number of son variables", 2, declsFatherVariable.getEnclosedVariables().size());

                // Sons (locations)
                List<DeclsVariable> declsSonVariables = declsFatherVariable.getEnclosedVariables();

                DeclsVariable array1 = declsSonVariables.get(0);
                assertEquals("Incorrect variable name", "this.array", array1.getVariableName());
                assertEquals("Incorrect var-kind", "field array", array1.getVarKind());
                assertEquals("Incorrect decType", "main.array[]", array1.getDecType());
                assertEquals("Incorrect repType", "hashcode", array1.getRepType());
                assertEquals("Incorrect enclosing var", "this", array1.getEnclosingVar());
                assertFalse("This variable should not be an array", array1.isArray());
                assertEquals("Unexpected number of son variables", 0, array1.getEnclosedVariables().size());

                DeclsVariable array2 = declsSonVariables.get(1);
                assertEquals("Incorrect variable name", "this.array[..]", array2.getVariableName());
                assertEquals("Incorrect var-kind", "array", array2.getVarKind());
                assertEquals("Incorrect decType", packageName + ".array[]", array2.getDecType());
                assertEquals("Incorrect repType", "hashcode[]", array2.getRepType());
                assertEquals("Incorrect enclosing var", "this.array", array2.getEnclosingVar());
                assertTrue("This variable should be an array", array2.isArray());
                assertEquals("Unexpected number of son variables", 0, array2.getEnclosedVariables().size());

                // FINAL OBJECT
                DeclsObject declsObject4 = declsObjectList.get(2);
                assertEquals("Incorrect package name", packageName, declsObject4.getPackageName());
                assertEquals("Incorrect object name", operationName + HIERARCHY_SEPARATOR + "Output" + HIERARCHY_SEPARATOR + "200"+ HIERARCHY_SEPARATOR + "locations", declsObject4.getObjectName());

                // VARIABLES
                // Father
                DeclsVariable declsFatherVariable4 = declsObject4.getDeclsVariables();
                assertEquals("Incorrect variable name", "this", declsFatherVariable4.getVariableName());
                assertEquals("Incorrect var-kind", "variable", declsFatherVariable4.getVarKind());
                assertEquals("Incorrect decType", packageName + ".sampleEndpointId" + HIERARCHY_SEPARATOR + "Output" + HIERARCHY_SEPARATOR + "200" + HIERARCHY_SEPARATOR + "locations", declsFatherVariable4.getDecType());
                assertEquals("Incorrect repType", "hashcode", declsFatherVariable4.getRepType());
                assertNull("The enclosing var should be null", declsFatherVariable4.getEnclosingVar());
                assertFalse("This variable should not be an array", declsFatherVariable4.isArray());
                // Arrays are counted twice (normal and [..])
                assertEquals("Unexpected number of son variables", 2, declsFatherVariable4.getEnclosedVariables().size());

                // Sons (locations)
                List<DeclsVariable> declsSonVariables4 = declsFatherVariable4.getEnclosedVariables();

                // Only the first one
                DeclsVariable locationId = declsSonVariables4.get(0);
                assertEquals("Incorrect variable name", "this.locationId", locationId.getVariableName());
                assertEquals("Incorrect var-kind", "field locationId", locationId.getVarKind());
                assertEquals("Incorrect decType", "java.lang.String", locationId.getDecType());
                assertEquals("Incorrect repType", "java.lang.String", locationId.getRepType());
                assertEquals("Incorrect enclosing var", "this", locationId.getEnclosingVar());
                assertFalse("This variable should not be an array", locationId.isArray());
                assertEquals("Unexpected number of son variables", 0, locationId.getEnclosedVariables().size());

                repeatedArray = ".array.array";
                declsObject = declsObjectList.get(3);

                // VARIABLES
                // Father
                declsFatherVariable = declsObject.getDeclsVariables();
                assertEquals("Incorrect variable name", "this", declsFatherVariable.getVariableName());
                assertEquals("Incorrect var-kind", "variable", declsFatherVariable.getVarKind());
                assertEquals("Incorrect decType", packageName + ".sampleEndpointId" + HIERARCHY_SEPARATOR + "Output" + HIERARCHY_SEPARATOR + "200" + HIERARCHY_SEPARATOR + "locations" + repeatedArray, declsFatherVariable.getDecType());
                assertEquals("Incorrect repType", "hashcode", declsFatherVariable.getRepType());
                assertNull("The enclosing var should be null", declsFatherVariable.getEnclosingVar());
                assertFalse("This variable should not be an array", declsFatherVariable.isArray());
                // Arrays are counted twice (normal and [..])
                assertEquals("Unexpected number of son variables", 2, declsFatherVariable.getEnclosedVariables().size());

                // Sons (locations)
                declsSonVariables = declsFatherVariable.getEnclosedVariables();

                array1 = declsSonVariables.get(0);
                assertEquals("Incorrect variable name", "this.array", array1.getVariableName());
                assertEquals("Incorrect var-kind", "field array", array1.getVarKind());
                assertEquals("Incorrect decType", "main.array[]", array1.getDecType());
                assertEquals("Incorrect repType", "hashcode", array1.getRepType());
                assertEquals("Incorrect enclosing var", "this", array1.getEnclosingVar());
                assertFalse("This variable should not be an array", array1.isArray());
                assertEquals("Unexpected number of son variables", 0, array1.getEnclosedVariables().size());

                array2 = declsSonVariables.get(1);
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
