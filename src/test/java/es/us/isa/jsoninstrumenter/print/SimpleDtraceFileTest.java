package es.us.isa.jsoninstrumenter.print;

import es.us.isa.jsoninstrumenter.pojos.*;
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
import static es.us.isa.jsoninstrumenter.pojos.DeclsClass.generateOutputDeclsClasses;
import static es.us.isa.jsoninstrumenter.pojos.DeclsClass.setDeclsClassEnterAndExit;
import static es.us.isa.jsoninstrumenter.util.TestCaseFileManager.getTestCasesFromFile;
import static org.junit.Assert.assertEquals;

public class SimpleDtraceFileTest {

    @Test
    public void testPrintSimpleDtraceFile() {

        // TODO: Refactor
        numberOfExits = 1;

        deleteAllDeclsClasses();

        String oasPath = "src/main/resources/AirportInfo/OpenAPISpec.yaml";
        String testCasesFilePath = "src/test/resources/airportInfo/testCases_simplified.csv";

        // Equivalent to the getOpenAPISpecification private function
        ParseOptions parseOptions = new ParseOptions();
        parseOptions.setResolveFully(true);
        parseOptions.setFlatten(true);
        OpenAPI specification = new OpenAPIV3Parser().read(oasPath, null, parseOptions);

        Paths paths = specification.getPaths();

        // Get the Declaration file
        for(Map.Entry<String, PathItem> path: paths.entrySet()) {

            PathItem pathItem = path.getValue();

            for (Map.Entry<PathItem.HttpMethod, Operation> operationEntry: pathItem.readOperationsMap().entrySet()) {
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

        DeclsFile declsFile = new DeclsFile(2.0, Comparability.implicit, getAllDeclsClasses());


        // Generate dTrace file
        List<TestCase> testCases = getTestCasesFromFile(testCasesFilePath);
        for(TestCase testCase: testCases) {
            for(DeclsClass declsClass: declsFile.getClasses()) {
                // The enter and exits belong to the same class
                if(declsClass.getPackageName().equalsIgnoreCase(packageName) &&
                        declsClass.getClassName().equalsIgnoreCase(testCase.getPath().replace("/",""))){
                    DeclsEnter declsEnter = declsClass.getDeclsEnters().get(0);

                    // Checks in ENTER
                    String[] enterDtraceLines = declsEnter.generateDtrace(testCase).split("\n");
                    assertEquals("Incorrect enter name", "main.airport.findAirports(main.findAirports_Input):::ENTER", enterDtraceLines[0]);
                    assertEquals("Incorrect wrapper name", "input", enterDtraceLines[1]);
                    assertEquals("Incorrect hashcode", "\"test_1ibbive9wrnty_findAirports_input_input\"", enterDtraceLines[2]);
                    assertEquals("Incorrect end of trace", "1", enterDtraceLines[3]);

                    // iata
                    assertEquals("Incorrect variable name", "input.iata", enterDtraceLines[4]);
                    assertEquals("Incorrect variable value", "\"BCS\"", enterDtraceLines[5]);
                    assertEquals("Incorrect end of trace", "1", enterDtraceLines[6]);

                    // icao
                    assertEquals("Incorrect variable name", "input.icao", enterDtraceLines[7]);
                    assertEquals("Incorrect variable value", "null", enterDtraceLines[8]);
                    assertEquals("Incorrect end of trace", "1", enterDtraceLines[9]);


                    // Get the correct declsExit by the responseCode
                    DeclsExit declsExit = declsClass.getDeclsExits().stream().filter(x->x.getStatusCode().equalsIgnoreCase(testCase.getStatusCode())).findFirst()
                            .orElseThrow(() -> new NullPointerException("Type of response not found in the specification"));


                    // Checks in EXIT
                    // INPUT
                    String[] exitDtraceLines = declsExit.generateDtrace(testCase).split("\n");
                    assertEquals("Incorrect exit name", "main.airport.findAirports(main.findAirports_Input):::EXIT1", exitDtraceLines[0]);
                    assertEquals("Incorrect wrapper name", "input", exitDtraceLines[1]);
                    assertEquals("Incorrect hashcode", "\"test_1ibbive9wrnty_findAirports_input_input\"", exitDtraceLines[2]);
                    assertEquals("Incorrect end of trace", "1", exitDtraceLines[3]);

                    // iata
                    assertEquals("Incorrect variable name", "input.iata", exitDtraceLines[4]);
                    assertEquals("Incorrect variable value", "\"BCS\"", exitDtraceLines[5]);
                    assertEquals("Incorrect end of trace", "1", exitDtraceLines[6]);

                    // icao
                    assertEquals("Incorrect variable name", "input.icao", exitDtraceLines[7]);
                    assertEquals("Incorrect variable value", "null", exitDtraceLines[8]);
                    assertEquals("Incorrect end of trace", "1", exitDtraceLines[9]);

                    // RETURN
                    assertEquals("Incorrect wrapper name", "return", exitDtraceLines[10]);
                    assertEquals("Incorrect hashcode", "\"test_1ibbive9wrnty_findAirports_return_output\"", exitDtraceLines[11]);
                    assertEquals("Incorrect end of trace", "1", exitDtraceLines[12]);

                    // id
                    assertEquals("Incorrect variable name", "return.id", exitDtraceLines[13]);
                    assertEquals("Incorrect variable value", "588", exitDtraceLines[14]);
                    assertEquals("Incorrect end of trace", "1", exitDtraceLines[15]);

                    // iata
                    assertEquals("Incorrect variable name", "return.iata", exitDtraceLines[16]);
                    assertEquals("Incorrect variable value", "\"BCS\"", exitDtraceLines[17]);
                    assertEquals("Incorrect end of trace", "1", exitDtraceLines[18]);

                    // icao
                    assertEquals("Incorrect variable name", "return.icao", exitDtraceLines[19]);
                    assertEquals("Incorrect variable value", "\"\"", exitDtraceLines[20]);
                    assertEquals("Incorrect end of trace", "1", exitDtraceLines[21]);

                    // name
                    assertEquals("Incorrect variable name", "return.name", exitDtraceLines[22]);
                    assertEquals("Incorrect variable value", "\"Southern Seaplane Airport (FAA: 65LA)\"", exitDtraceLines[23]);
                    assertEquals("Incorrect end of trace", "1", exitDtraceLines[24]);

                    // location
                    assertEquals("Incorrect variable name", "return.location", exitDtraceLines[25]);
                    assertEquals("Incorrect variable value", "\"Belle Chasse, Louisiana, United States\"", exitDtraceLines[26]);
                    assertEquals("Incorrect end of trace", "1", exitDtraceLines[27]);

                    // street_number
                    assertEquals("Incorrect variable name", "return.street_number", exitDtraceLines[28]);
                    assertEquals("Incorrect variable value", "\"1\"", exitDtraceLines[29]);
                    assertEquals("Incorrect end of trace", "1", exitDtraceLines[30]);

                    // street
                    assertEquals("Incorrect variable name", "return.street", exitDtraceLines[31]);
                    assertEquals("Incorrect variable value", "\"Coquille Road\"", exitDtraceLines[32]);
                    assertEquals("Incorrect end of trace", "1", exitDtraceLines[33]);

                    // city
                    assertEquals("Incorrect variable name", "return.city", exitDtraceLines[34]);
                    assertEquals("Incorrect variable value", "\"Belle Chasse\"", exitDtraceLines[35]);
                    assertEquals("Incorrect end of trace", "1", exitDtraceLines[36]);

                    // county
                    assertEquals("Incorrect variable name", "return.county", exitDtraceLines[37]);
                    assertEquals("Incorrect variable value", "\"Plaquemines Parish\"", exitDtraceLines[38]);
                    assertEquals("Incorrect end of trace", "1", exitDtraceLines[39]);

                    // state
                    assertEquals("Incorrect variable name", "return.state", exitDtraceLines[40]);
                    assertEquals("Incorrect variable value", "\"Louisiana\"", exitDtraceLines[41]);
                    assertEquals("Incorrect end of trace", "1", exitDtraceLines[42]);

                    // country_iso
                    assertEquals("Incorrect variable name", "return.country_iso", exitDtraceLines[43]);
                    assertEquals("Incorrect variable value", "\"US\"", exitDtraceLines[44]);
                    assertEquals("Incorrect end of trace", "1", exitDtraceLines[45]);

                    // country
                    assertEquals("Incorrect variable name", "return.country", exitDtraceLines[46]);
                    assertEquals("Incorrect variable value", "\"United States\"", exitDtraceLines[47]);
                    assertEquals("Incorrect end of trace", "1", exitDtraceLines[48]);

                    // postal_code
                    assertEquals("Incorrect variable name", "return.postal_code", exitDtraceLines[49]);
                    assertEquals("Incorrect variable value", "\"70037\"", exitDtraceLines[50]);
                    assertEquals("Incorrect end of trace", "1", exitDtraceLines[51]);

                    // phone
                    assertEquals("Incorrect variable name", "return.phone", exitDtraceLines[52]);
                    assertEquals("Incorrect variable value", "\"+1 504-394-5633\"", exitDtraceLines[53]);
                    assertEquals("Incorrect end of trace", "1", exitDtraceLines[54]);

                    // latitude
                    assertEquals("Incorrect variable name", "return.latitude", exitDtraceLines[55]);
                    assertEquals("Incorrect variable value", "29.866688", exitDtraceLines[56]);
                    assertEquals("Incorrect end of trace", "1", exitDtraceLines[57]);

                    // longitude
                    assertEquals("Incorrect variable name", "return.longitude", exitDtraceLines[58]);
                    assertEquals("Incorrect variable value", "-90.020775", exitDtraceLines[59]);
                    assertEquals("Incorrect end of trace", "1", exitDtraceLines[60]);

                    // uct
                    assertEquals("Incorrect variable name", "return.uct", exitDtraceLines[61]);
                    assertEquals("Incorrect variable value", "-300", exitDtraceLines[62]);
                    assertEquals("Incorrect end of trace", "1", exitDtraceLines[63]);

                    // website
                    assertEquals("Incorrect variable name", "return.website", exitDtraceLines[64]);
                    assertEquals("Incorrect variable value", "\"http://www.southernseaplane.com/\"", exitDtraceLines[65]);
                    assertEquals("Incorrect end of trace", "1", exitDtraceLines[66]);

                }

            }

        }



    }
}
