package es.us.isa.jsoninstrumenter.main;

import es.us.isa.jsoninstrumenter.pojos.*;
import io.swagger.v3.oas.models.Operation;
import io.swagger.v3.oas.models.PathItem.HttpMethod;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.PathItem;
import io.swagger.v3.oas.models.Paths;
import io.swagger.v3.parser.OpenAPIV3Parser;
import io.swagger.v3.parser.core.models.ParseOptions;
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import static es.us.isa.jsoninstrumenter.pojos.DeclsClass.*;
import static es.us.isa.jsoninstrumenter.util.FileManager.writeFile;
import static es.us.isa.jsoninstrumenter.util.TestCaseFileManager.getTestCasesFromFile;

public class GenerateDeclsFile {

//    private static final Logger log = LogManager.getLogger(GenerateDeclsFile.class);

    private static String openApiSpecPath = "src/main/resources/DHL/swagger.yaml";
    private static String testCasesFilePath = "src/main/resources/DHL/testCase.csv";
    private static boolean generateDtrace = true;

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
                // TODO: Nesting
                // TODO: Uncomment
                DeclsClass declsClassInput = new DeclsClass(packageName, operationName + "_Input", operationName + "_Input", operation.getParameters());
                addNewDeclsClass(declsClassInput);

                // TODO: Uncomment
                // Extracting the output parameters
                generateOutputDeclsClasses(operationName, packageName, operation.getResponses());

                // Extracting enter and exits
                // TODO: Uncomment
                setDeclsClassEnterAndExit(packageName, operationEndpoint, operationName,
                        "Input", operation.getParameters(), operation.getResponses());

            }

        }

        // PRINT DECLS file
        DeclsFile declsFile = new DeclsFile(2.0, Comparability.implicit, declsClasses);
        System.out.println(declsFile);

        String declsFilePath = getOutputPath("declsFile.decls");
        writeFile(declsFilePath, declsFile.toString());



        if(generateDtrace){

            String dtraceContent = "";
            // Generate dTrace file
            List<TestCase> testCases = getTestCasesFromFile(testCasesFilePath);
            for(TestCase testCase: testCases) {
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
                        dtraceContent = dtraceContent + declsEnter.generateDtrace(testCase) + "\n";


                        // Get the correct declsExit by the responseCode
                        // TODO: Change to list and iterate by using a for loop
                        List<DeclsExit> declsExits = declsClass.getDeclsExits().stream()
                                .filter(x-> x.getStatusCode().equalsIgnoreCase(testCase.getStatusCode()))
                                .collect(Collectors.toList());

                        for(DeclsExit declsExit: declsExits) {
                            System.out.println(declsExit.generateDtrace(testCase));
                            dtraceContent = dtraceContent + declsExit.generateDtrace(testCase);
                        }

                    }

                }

            }


            String dtraceFilePath = getOutputPath("dtraceFile.dtrace");
            writeFile(dtraceFilePath, dtraceContent);

        }

        // TODO: Discard authentication parameters (by discarding them from the objects)

    }

    public static String getOperationName(Operation operation, Entry<HttpMethod, Operation> operationEntry, String operationEndpoint){

        String operationName;
        if (operation.getOperationId() == null) {
            String httpMethod = operationEntry.getKey().toString();
            operationName = operationEndpoint + "_" + httpMethod.toLowerCase();
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

    private static String getOutputPath(String filename) {
        Path path = java.nio.file.Paths.get(openApiSpecPath);
        Path dir = path.getParent();
        Path fn = path.getFileSystem().getPath(filename);
        Path target = (dir == null) ? fn : dir.resolve(fn);

        return target.toString();
    }


    // Input file
    // Distinguish program point declaration from variable declarations



}
