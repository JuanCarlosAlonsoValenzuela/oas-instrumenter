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
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import static es.us.isa.jsoninstrumenter.pojos.DeclsClass.*;

public class GenerateDeclsFile {

    private static final Logger log = LogManager.getLogger(GenerateDeclsFile.class);
    private static String openApiSpecPath = "src/main/resources/AirportInfo/OpenAPISpec.yaml";
//    private static String openApiSpecPath = "src/main/resources/DHL/swagger.yaml";
//    private static String openApiSpecPath = "src/main/resources/Yelp/swagger.yaml";
    public static int numberOfExits;

    public static void main(String[] args) {
        numberOfExits = 1;

        ParseOptions parseOptions = new ParseOptions();
        parseOptions.setResolveFully(true);
        parseOptions.setFlatten(true);
        OpenAPI specification = new OpenAPIV3Parser().read(openApiSpecPath, null, parseOptions);


        Paths paths = specification.getPaths();
        // A path (endpoint) contains several operations (http methods/verbs)
        for(Entry<String, PathItem> path: paths.entrySet()) {

            PathItem pathItem = path.getValue();
            // TODO: Parámetros comunes a todas las operaciones
            // TODO: If operationId == null -> operationName = endpoint_httpmethod
            // TODO: Extract responses of operation
            // TODO: Extract parameters of operation
            // TODO: Extract the request body
            // TODO: Consider the "in" property (query, header, path, etc.) when extracting parameters

            for (Entry<HttpMethod, Operation> operationEntry: pathItem.readOperationsMap().entrySet()) {
                Operation operation = operationEntry.getValue();
                String operationEndpoint = path.getKey().replace("/", "");
//                System.out.println(operation);

                // Set the operation name for the .decls file
                String operationName;
                if (operation.getOperationId() == null) {
                    String httpMethod = operationEntry.getKey().toString();
                    operationName = operationEndpoint + "_" + httpMethod;
                } else {
                    operationName = operation.getOperationId();
                }

                List<DeclsClass> declsClasses = new ArrayList<>();
                ///////////////////////// INPUT /////////////////////////////
                // Extracting the input parameters
                DeclsClass declsClassInput = new DeclsClass("main", operationName + "_Input", operationName + "_Input", operation.getParameters());
                declsClasses.add(declsClassInput);

                ///////////////////////// OUTPUT /////////////////////////////
                List<DeclsClass> declsClassOutput = generateOutputDeclsClasses(operationName, "main", operation.getResponses());
                declsClasses.addAll(declsClassOutput);

                ///////////////////////// ENTER AND EXIT /////////////////////////////
                // TODO: Derive the output name automatically (list of outputs)
                // TODO: Check that the exit numbers correspond (Create a map?)
                DeclsClass declsClassEnterAndExit = getDeclsClassEnterAndExit("main", operationEndpoint, operationName,
                        "Input", operation.getParameters(), "Output_200", operation.getResponses());

                declsClasses.add(declsClassEnterAndExit);


                // PRINT TRACE
                DeclsFile declsFile = new DeclsFile(2.0, Comparability.implicit, declsClasses);
                System.out.println(declsFile);




            }


        }


    }

    // Input file
    // Distinguis program point declaration from variable declarations



}
