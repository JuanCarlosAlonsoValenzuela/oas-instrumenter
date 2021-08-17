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

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static es.us.isa.jsoninstrumenter.main.GenerateDeclsFile.getOperationName;
import static es.us.isa.jsoninstrumenter.main.GenerateDeclsFile.packageName;
import static es.us.isa.jsoninstrumenter.pojos.DeclsClass.generateOutputDeclsClasses;
import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class NestedObjectOutputTest {

    @Test
    public void testGenerationOfNestedObjectOutputTest() {

        String oasPath = "src/test/resources/DHL/swagger_nestedObjectOutput.yaml";

        // Equivalent to the getOpenAPISpecification private function
        ParseOptions parseOptions = new ParseOptions();
        parseOptions.setResolveFully(true);
        parseOptions.setFlatten(true);
        OpenAPI specification = new OpenAPIV3Parser().read(oasPath, null, parseOptions);

        Paths paths = specification.getPaths();

        List<DeclsClass> declsClassList = new ArrayList<>();

        for(Map.Entry<String, PathItem> path: paths.entrySet()) {
            PathItem pathItem = path.getValue();

            for (Map.Entry<PathItem.HttpMethod, Operation> operationEntry: pathItem.readOperationsMap().entrySet()) {

                Operation operation = operationEntry.getValue();
                String operationEndpoint = path.getKey().replace("/", "");

                // Set the operation name for the .decls file
                String operationName = getOperationName(operation, operationEntry, operationEndpoint);

                List<DeclsClass> declsClassesOutput = generateOutputDeclsClasses(operationName, packageName, operation.getResponses());

                assertEquals("The expected number of output classes is one", declsClassesOutput.size(), 1);
                declsClassList.addAll(declsClassesOutput);

                DeclsClass declsClassOutput = declsClassesOutput.get(0);
                System.out.println(declsClassOutput);

                // CLASS
                assertEquals("Incorrect package name", packageName, declsClassOutput.getPackageName());
                assertEquals("Incorrect class name", operationName + "_Output_200", declsClassOutput.getClassName());
                assertEquals("The size of the list of objects is not 1", 1, declsClassOutput.getDeclsObjects().size());
                assertEquals("The size of the list of enters is not 0", 0, declsClassOutput.getDeclsEnters().size());
                assertEquals("The size of the list of exits is not 0", 0, declsClassOutput.getDeclsExits().size());

                // OBJECT
                DeclsObject declsObject = declsClassOutput.getDeclsObjects().get(0);
                assertEquals("Incorrect package name", packageName, declsObject.getPackageName());
                assertEquals("Incorrect object name", operationName + "_Output_200", declsObject.getObjectName());
                assertEquals("The size of the list of variables is not 1", 1, declsObject.getDeclsVariables().size());

                // VARIABLES
                // Father
                DeclsVariable declsFatherVariable = declsObject.getDeclsVariables().get(0);
                assertEquals("Incorrect variable name", "this", declsFatherVariable.getVariableName());
                assertEquals("Incorrect var-kind", "variable", declsFatherVariable.getVarKind());
                assertEquals("Incorrect decType", packageName + ".findByAddress_Output_200", declsFatherVariable.getDecType());
                assertEquals("Incorrect repType", "java.lang.String", declsFatherVariable.getRepType());
                assertNull("The enclosing var should be null", declsFatherVariable.getEnclosingVar());
                assertFalse("This variable should not be an array", declsFatherVariable.isArray());
                // The sons are url and location
                assertEquals("Unexpected number of son variables", 2, declsFatherVariable.getEnclosedVariables().size());

                // Sons
                List<DeclsVariable> declsSonVariables = declsFatherVariable.getEnclosedVariables();
                // url and location (of type object)
                DeclsVariable url = declsSonVariables.get(0);
                assertEquals("Incorrect variable name", "this.url", url.getVariableName());
                assertEquals("Incorrect var-kind", "field url", url.getVarKind());
                assertEquals("Incorrect decType", "java.lang.String", url.getDecType());
                assertEquals("Incorrect repType", "java.lang.String", url.getRepType());
                assertEquals("Incorrect enclosing var", "this", url.getEnclosingVar());
                assertFalse("This variable should not be an array", url.isArray());
                assertEquals("Unexpected number of son variables", 0, url.getEnclosedVariables().size());

                // Variable of type object
                DeclsVariable locationObject = declsSonVariables.get(1);
                assertEquals("Incorrect variable name", "this.location", locationObject.getVariableName());
                assertEquals("Incorrect var-kind", "variable", locationObject.getVarKind());
                assertEquals("Incorrect decType", "java.lang.String", locationObject.getDecType());
                assertEquals("Incorrect repType", "java.lang.String", locationObject.getRepType());
                assertEquals("Incorrect enclosing var", "this", locationObject.getEnclosingVar());
                assertFalse("This variable should not be an array", locationObject.isArray());
                // 5 sons: ids, keyword, keywordId, type (ids is counted twice because it is of type array)
                assertEquals("Unexpected number of son variables", 5, locationObject.getEnclosedVariables().size());

                // Sons of the variable of type object
                List<DeclsVariable> sonsOfLocation = locationObject.getEnclosedVariables();

                // Properties of type String
                DeclsVariable keyword = sonsOfLocation.get(2);
                assertEquals("Incorrect variable name", "this.location.keyword",  keyword.getVariableName());
                assertEquals("Incorrect var-kind", "field keyword", keyword.getVarKind());
                assertEquals("Incorrect decType",  "java.lang.String", keyword.getDecType());
                assertEquals("Incorrect repType", "java.lang.String", keyword.getRepType());
                assertEquals("Incorrect enclosing var", "this.location", keyword.getEnclosingVar());
                assertFalse("This variable should not be an array", keyword.isArray());
                assertEquals("Unexpected number of son variables", 0, keyword.getEnclosedVariables().size());

                DeclsVariable keywordId = sonsOfLocation.get(3);
                assertEquals("Incorrect variable name", "this.location.keywordId",  keywordId.getVariableName());
                assertEquals("Incorrect var-kind", "field keywordId", keywordId.getVarKind());
                assertEquals("Incorrect decType",  "java.lang.String", keywordId.getDecType());
                assertEquals("Incorrect repType", "java.lang.String", keywordId.getRepType());
                assertEquals("Incorrect enclosing var", "this.location", keywordId.getEnclosingVar());
                assertFalse("This variable should not be an array", keywordId.isArray());
                assertEquals("Unexpected number of son variables", 0, keywordId.getEnclosedVariables().size());

                DeclsVariable type = sonsOfLocation.get(4);
                assertEquals("Incorrect variable name", "this.location.type",  type.getVariableName());
                assertEquals("Incorrect var-kind", "field type", type.getVarKind());
                assertEquals("Incorrect decType",  "java.lang.String", type.getDecType());
                assertEquals("Incorrect repType", "java.lang.String", type.getRepType());
                assertEquals("Incorrect enclosing var", "this.location", type.getEnclosingVar());
                assertFalse("This variable should not be an array", type.isArray());
                assertEquals("Unexpected number of son variables", 0, type.getEnclosedVariables().size());

                // Array type with another nested object
                DeclsVariable ids1 = sonsOfLocation.get(0);
                assertEquals("Incorrect variable name", "this.location.ids",  ids1.getVariableName());
                assertEquals("Incorrect var-kind", "field ids", ids1.getVarKind());
                assertEquals("Incorrect decType",  "main.ids[]", ids1.getDecType());
                assertEquals("Incorrect repType", "java.lang.String[]", ids1.getRepType());
                assertEquals("Incorrect enclosing var", "this.location", ids1.getEnclosingVar());
                assertFalse("This variable should not be an array", ids1.isArray());
                assertEquals("Unexpected number of son variables", 0, ids1.getEnclosedVariables().size());

                DeclsVariable ids2 = sonsOfLocation.get(1);
                assertEquals("Incorrect variable name", "this.location.ids[..]",  ids2.getVariableName());
                assertEquals("Incorrect var-kind", "array", ids2.getVarKind());
                assertEquals("Incorrect decType",  "main.ids", ids2.getDecType());
                assertEquals("Incorrect repType", "java.lang.String", ids2.getRepType());
                assertEquals("Incorrect enclosing var", "this.location.ids", ids2.getEnclosingVar());
                assertTrue("This variable should not be an array", ids2.isArray());
                assertEquals("Unexpected number of son variables", 2, ids2.getEnclosedVariables().size());

                // Sons of the Array of objects id, namely locationId and provider
                List<DeclsVariable> sonsOfTheIds = ids2.getEnclosedVariables();

                DeclsVariable locationId = sonsOfTheIds.get(0);
                assertEquals("Incorrect variable name", "this.location.ids[..].locationId",  locationId.getVariableName());
                assertEquals("Incorrect var-kind", "field locationId", locationId.getVarKind());
                assertEquals("Incorrect decType",  "java.lang.String", locationId.getDecType());
                assertEquals("Incorrect repType", "java.lang.String", locationId.getRepType());
                assertEquals("Incorrect enclosing var", "this.location.ids[..]", locationId.getEnclosingVar());
                assertTrue("This variable should not be an array", locationId.isArray());
                assertEquals("Unexpected number of son variables", 0, locationId.getEnclosedVariables().size());

                DeclsVariable provider = sonsOfTheIds.get(1);
                assertEquals("Incorrect variable name", "this.location.ids[..].provider",  provider.getVariableName());
                assertEquals("Incorrect var-kind", "field provider", provider.getVarKind());
                assertEquals("Incorrect decType",  "java.lang.String", provider.getDecType());
                assertEquals("Incorrect repType", "java.lang.String", provider.getRepType());
                assertEquals("Incorrect enclosing var", "this.location.ids[..]", provider.getEnclosingVar());
                assertTrue("This variable should not be an array", provider.isArray());
                assertEquals("Unexpected number of son variables", 0, provider.getEnclosedVariables().size());



            }

            assertEquals("The expected total number of classes is one", declsClassList.size(), 1);

        }




    }
}
