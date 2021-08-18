package es.us.isa.jsoninstrumenter.main;

import es.us.isa.jsoninstrumenter.pojos.Comparability;
import es.us.isa.jsoninstrumenter.pojos.DeclsClass;
import es.us.isa.jsoninstrumenter.pojos.DeclsFile;
import io.swagger.v3.oas.models.Operation;
import io.swagger.v3.oas.models.PathItem.HttpMethod;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.PathItem;
import io.swagger.v3.oas.models.Paths;
import io.swagger.v3.parser.OpenAPIV3Parser;
import io.swagger.v3.parser.core.models.ParseOptions;
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import static es.us.isa.jsoninstrumenter.pojos.DeclsClass.*;

public class GenerateDeclsFile {

//    private static final Logger log = LogManager.getLogger(GenerateDeclsFile.class);

//    private static String openApiSpecPath = "src/main/resources/AirportInfo/OpenAPISpec.yaml";
//    private static String openApiSpecPath = "src/main/resources/DHL/swagger.yaml";
//    private static String openApiSpecPath = "src/main/resources/DHL/swagger_arrayBaseCase.yaml";
//    private static String openApiSpecPath = "src/main/resources/Yelp/swagger.yaml";
    private static String openApiSpecPath = "src/test/resources/sampleAPI/swagger_nestedArraysObject.yaml";

    public static int numberOfExits = 1;

    private static List<DeclsClass> declsClasses = new ArrayList<>();


    // TODO: Refactor package name
    public static final String packageName = "main";

    public static void main(String[] args) {
        OpenAPI specification = getOpenAPISpecification();
        Paths paths = specification.getPaths();

//        List<DeclsClass> declsClasses = new ArrayList<>();

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
                setDeclsClassEnterAndExit("main", operationEndpoint, operationName,
                        "Input", operation.getParameters(), operation.getResponses());



            }


        }

        // PRINT TRACE
        DeclsFile declsFile = new DeclsFile(2.0, Comparability.implicit, declsClasses);
        System.out.println(declsFile);


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
