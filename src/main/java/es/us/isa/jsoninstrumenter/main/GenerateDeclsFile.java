package es.us.isa.jsoninstrumenter.main;

import es.us.isa.jsoninstrumenter.model.*;
import io.swagger.v3.oas.models.Operation;
import io.swagger.v3.oas.models.PathItem.HttpMethod;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.PathItem;
import io.swagger.v3.oas.models.Paths;
import io.swagger.v3.parser.OpenAPIV3Parser;
import io.swagger.v3.parser.core.models.ParseOptions;


import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import static es.us.isa.jsoninstrumenter.util.FileManager.deleteFile;

import static es.us.isa.jsoninstrumenter.model.DeclsClass.*;
import static es.us.isa.jsoninstrumenter.util.FileManager.writeFile;
import static es.us.isa.jsoninstrumenter.util.TestCaseFileManager.getTestCasesFromFile;

public class GenerateDeclsFile {

    private static String openApiSpecPath = "src/test/resources/evaluation/GitHub/swagger.yaml";
    private static String testCasesFilePath = "src/test/resources/evaluation/GitHub/GitHub2_extended.csv";
    private static boolean generateDtrace = true;

//    cd /mnt/d/users/jcav/Documents/GitHub/json-instrumenter/src/main/resources
//    cd /mnt/d/users/Juan\ Carlos/Documents/GitHub/json-instrumenter/src/main/resources
//    java -cp $DAIKONDIR/daikon.jar daikon.Daikon declsFile.decls dtraceFile.dtrace

    // TODO: Variable names can be written in snake_case, use a list to specify the hierarchy instead of splitting the "_" character (Apply the same principle to nested arrays)
    // TODO: The name suffix of nested arrays of the exits must be print with "." or "&" instead of "_"

    public static int numberOfExits = 1;

    private static List<DeclsClass> declsClasses = new ArrayList<>();

    public static final String packageName = "main";

    public static final String STRING_TYPE_NAME = "java.lang.String";
    public static final String DOUBLE_TYPE_NAME = "double";
    public static final String INTEGER_TYPE_NAME = "int";
    public static final String BOOLEAN_TYPE_NAME = "boolean";
    public static final String OBJECT_TYPE_NAME = "object";
    public static final String ARRAY_TYPE_NAME = "array";

    public static final List<String> primitiveTypes = Arrays.asList(STRING_TYPE_NAME, DOUBLE_TYPE_NAME, INTEGER_TYPE_NAME, BOOLEAN_TYPE_NAME);

    public static void main(String[] args) {

        if(args.length == 2) {
            openApiSpecPath = args[0];
            testCasesFilePath = args[1];
            generateDtrace = true;
        } else if(args.length == 1) {
            openApiSpecPath = args[0];
            generateDtrace = false;
        }

        OpenAPI specification = getOpenAPISpecification();
        Paths paths = specification.getPaths();

        // A path (endpoint) contains several operations (http methods/verbs)
        for(Entry<String, PathItem> path: paths.entrySet()) {

            PathItem pathItem = path.getValue();

            for (Entry<HttpMethod, Operation> operationEntry: pathItem.readOperationsMap().entrySet()) {
                Operation operation = operationEntry.getValue();
                String operationEndpoint = path.getKey().replace("/", "");

                // Set the operation name for the .decls file
                String operationName = getOperationName(operation, operationEntry, operationEndpoint);

                // Extracting the input parameters
                String objectName = operationName + "_Input";

                DeclsClass declsClassInput = new DeclsClass(packageName, objectName, objectName, operation);
                addNewDeclsClass(declsClassInput);

                // Extracting the output parameters
                generateOutputDeclsClasses(operationName, packageName, operation.getResponses());

                // Extracting enter and exits
                setDeclsClassEnterAndExit(packageName, operationEndpoint, operationName,
                        objectName, operation);

            }

        }

        // PRINT DECLS file
        DeclsFile declsFile = new DeclsFile(2.0, Comparability.implicit, declsClasses);
//        System.out.println(declsFile);

        String declsFilePath = getOutputPath("declsFile.decls", openApiSpecPath);
        // Delete file if exists
        deleteFile(declsFilePath);
        writeFile(declsFilePath, declsFile.toString());



        if(generateDtrace){

            String dtraceContent = "";
            // Generate dTrace file
            List<TestCase> testCases = getTestCasesFromFile(testCasesFilePath);

            System.out.println("Total number of test cases: " + testCases.size());
            int i = 0;
            for(TestCase testCase: testCases) {
                // TODO: operationEndpoint + "_" + httpMethod vs operationId
                // TODO: Extract ENTER

                if(i%50==0){
                    System.out.println("Generated dtrace for " + i + " out of " + testCases.size() + " test cases");
                }
                i++;

                for(DeclsClass declsClass: declsFile.getClasses()) {
                    // The enter and exits belong to the same class
                    if(declsClass.getPackageName().equalsIgnoreCase(packageName) &&
                            declsClass.getClassName().equalsIgnoreCase(testCase.getPath().replace("/",""))){

                        // Get the correct declsExit by the responseCode
                        List<DeclsExit> declsExits = declsClass.getDeclsExits().stream()
                                .filter(x-> x.getStatusCode().equalsIgnoreCase(testCase.getStatusCode()))
                                .collect(Collectors.toList());

                        for(DeclsExit declsExit: declsExits) {
                            // Find the corresponding DeclsEnter according to the statusCode and nameSuffix
                            DeclsEnter declsEnter = declsClass.getDeclsEnters().stream()
                                    .filter(x-> x.getStatusCode().equals(declsExit.getStatusCode()) && x.getNameSuffix().equals(declsExit.getNameSuffix()))
                                    .findFirst().orElseThrow(() -> new NullPointerException("Could not find the corresponding DeclsEnter"));

                            // Generates the dtrace of both DeclsEnter and DeclsExit
//                            System.out.println(declsExit.generateDtrace(testCase, declsEnter));
                            dtraceContent = dtraceContent + declsExit.generateDtrace(testCase, declsEnter);
                        }

                    }

                }

            }


            String dtraceFilePath = getOutputPath("dtraceFile.dtrace", openApiSpecPath);      // openApiSpecPath testCasesFilePath
            deleteFile(dtraceFilePath);
            writeFile(dtraceFilePath, dtraceContent);

        }

    }

    public static String getOperationName(Operation operation, Entry<HttpMethod, Operation> operationEntry, String operationEndpoint){

        String operationName;
        // TODO: Consider throwing an error message
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

    public static void deleteAllDeclsClasses(){
        declsClasses.clear();
    }

    private static String getOutputPath(String filename, String folder) {
        Path path = java.nio.file.Paths.get(folder);      // openApiSpecPath
        Path dir = path.getParent();
        Path fn = path.getFileSystem().getPath(filename);
        Path target = (dir == null) ? fn : dir.resolve(fn);

        return target.toString();
    }

}
