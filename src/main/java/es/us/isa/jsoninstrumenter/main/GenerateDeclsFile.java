package es.us.isa.jsoninstrumenter.main;

import es.us.isa.jsoninstrumenter.pojos.*;
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

import static es.us.isa.jsoninstrumenter.pojos.DeclsClass.*;
import static es.us.isa.jsoninstrumenter.util.FileManager.writeFile;
import static es.us.isa.jsoninstrumenter.util.TestCaseFileManager.getTestCasesFromFile;

public class GenerateDeclsFile {

    private static String openApiSpecPath = "src/test/resources/RestCountries/swagger.yaml";
//    private static String openApiSpecPath = "src/test/resources/Spotify_createPlaylist/spec.yaml";
//    private static String openApiSpecPath = "src/test/resources/DHL/swagger_nestedObjectOutput.yaml";
//    private static String openApiSpecPath = "src/main/resources/DHL/swagger.yaml";

    private static String testCasesFilePath = "src/test/resources/Spotify_createPlaylist/testCases_20.csv";
    private static boolean generateDtrace = false;

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



//    public static final List<String> primitiveTypes = Arrays.asList("java.lang.String", "java.lang.Double", "int", "boolean");

    public static void main(String[] args) {
        OpenAPI specification = getOpenAPISpecification();
        Paths paths = specification.getPaths();

        // A path (endpoint) contains several operations (http methods/verbs)
        for(Entry<String, PathItem> path: paths.entrySet()) {

            PathItem pathItem = path.getValue();
            // TODO: Create a jUnit test case in which the parameters of the operation are null (i.e., there are no parameters or all the parameters are in the body)

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

                        // TODO: Take other parameters apart from the specified in the query (and discard those that are not present in the original declsFile)

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


            String dtraceFilePath = getOutputPath("dtraceFile.dtrace");
            writeFile(dtraceFilePath, dtraceContent);

        }

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

}
