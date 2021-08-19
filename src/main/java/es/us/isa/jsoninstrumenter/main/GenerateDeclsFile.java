package es.us.isa.jsoninstrumenter.main;

import es.us.isa.jsoninstrumenter.pojos.*;
import io.swagger.v3.oas.models.Operation;
import io.swagger.v3.oas.models.PathItem.HttpMethod;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.PathItem;
import io.swagger.v3.oas.models.Paths;
import io.swagger.v3.parser.OpenAPIV3Parser;
import io.swagger.v3.parser.core.models.ParseOptions;
import org.junit.Test;
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import static es.us.isa.jsoninstrumenter.pojos.DeclsClass.*;
import static es.us.isa.jsoninstrumenter.util.TestCaseFileManager.getTestCasesFromFile;

public class GenerateDeclsFile {

//    private static final Logger log = LogManager.getLogger(GenerateDeclsFile.class);

    private static String openApiSpecPath = "src/main/resources/AirportInfo/OpenAPISpec.yaml";
    private static String testCasesFilePath = "src/main/resources/AirportInfo/testCases.csv";

    public static int numberOfExits = 1;

    private static List<DeclsClass> declsClasses = new ArrayList<>();

    public static final String packageName = "main";

    public static final List<String> primitiveTypes = Arrays.asList("java.lang.String", "double", "int", "boolean");

    public static void main(String[] args) {
        OpenAPI specification = getOpenAPISpecification();
        Paths paths = specification.getPaths();

        // A path (endpoint) contains several operations (http methods/verbs)
        for(Entry<String, PathItem> path: paths.entrySet()) {

            PathItem pathItem = path.getValue();
            // TODO: Common parameters for all the operations
            // TODO: Extract the request body
            // TODO: Consider the "in" property (query, header, path, etc.) when extracting parameters

            for (Entry<HttpMethod, Operation> operationEntry: pathItem.readOperationsMap().entrySet()) {
                Operation operation = operationEntry.getValue();
                String operationEndpoint = path.getKey().replace("/", "");

                // Set the operation name for the .decls file
                String operationName = getOperationName(operation, operationEntry, operationEndpoint);

                // Extracting the input parameters
                DeclsClass declsClassInput = new DeclsClass(packageName, operationName + "_Input", operationName + "_Input", operation.getParameters());
                addNewDeclsClass(declsClassInput);

                // Extracting the output parameters
                generateOutputDeclsClasses(operationName, packageName, operation.getResponses());

                // Extracting enter and exits
                setDeclsClassEnterAndExit(packageName, operationEndpoint, operationName,
                        "Input", operation.getParameters(), operation.getResponses());

            }

        }

        // PRINT TRACE
        DeclsFile declsFile = new DeclsFile(2.0, Comparability.implicit, declsClasses);
        System.out.println(declsFile);


        // Generate dTrace file
        List<TestCase> testCases = getTestCasesFromFile(testCasesFilePath);
        for(TestCase testCase: testCases) {
//            System.out.println(testCase);
            // TODO: operationEndpoint + "_" + httpMethod vs operationId
            // TODO: Extract ENTER
            for(DeclsClass declsClass: declsFile.getClasses()) {
                // The enter and exits belong to the same class
                if(declsClass.getPackageName().equalsIgnoreCase(packageName) &&
                        declsClass.getClassName().equalsIgnoreCase(testCase.getPath().replace("/",""))){
                    // TODO: Convert the list of declsEnter to a single declsEnter
                    // TODO: Take other parameters apart from the specified in the query (and discard those that are not present in the original declsFile)
                    DeclsEnter declsEnter = declsClass.getDeclsEnters().get(0);
                    System.out.println(declsEnter.generateDtrace(testCase));


                    // Get the correct declsExit by the responseCode
                    DeclsExit declsExit = declsClass.getDeclsExits().stream().filter(x->x.getStatusCode().equalsIgnoreCase(testCase.getStatusCode())).findFirst()
                            .orElseThrow(() -> new NullPointerException("Type of response not found in the specification"));




                    System.out.println(declsExit.generateDtrace(testCase));


                }

            }

        }




        // TODO: Deal with null values

        // TODO: Discard authentication parameters (by discarding them from the objects)
        // TODO: Extract EXIT (input and output) (use the response code)




    }

    public static String getOperationName(Operation operation, Entry<HttpMethod, Operation> operationEntry, String operationEndpoint){

        String operationName;
        if (operation.getOperationId() == null) {
            String httpMethod = operationEntry.getKey().toString();
            operationName = operationEndpoint + "_" + httpMethod;
        } else {
            operationName = operation.getOperationId();
        }

        return operationName;
    }

    private static OpenAPI getOpenAPISpecification(){

        ParseOptions parseOptions = new ParseOptions();
        parseOptions.setResolveFully(true);
        parseOptions.setFlatten(true);

        return new OpenAPIV3Parser().read(openApiSpecPath, null, parseOptions);
    }

    public static void addNewDeclsClass(DeclsClass declsClass){
        declsClasses.add(declsClass);
    }

    public static void addNewDeclsClasses(List<DeclsClass> declsClassList){
        declsClasses.addAll(declsClassList);
    }

    public static List<DeclsClass> getAllDeclsClasses(){
        return declsClasses;
    }

    // TODO: Convert test cases to e2e
    public static void deleteAllDeclsClasses(){
        declsClasses.clear();
    }

    // Input file
    // Distinguish program point declaration from variable declarations



}
