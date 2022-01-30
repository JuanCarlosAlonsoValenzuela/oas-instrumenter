package es.us.isa.jsoninstrumenter.print;

import static org.junit.Assert.assertEquals;

public class NestedArrayOfObjectDtraceFileTest {

//    @Test
//    public void testPrintNestedArrayOfObjectDtraceFileTest() {
//
//        // TODO: Refactor
//        numberOfExits = 1;
//
//        deleteAllDeclsClasses();
//
//        String oasPath = "src/test/resources/Spotify/swagger_categoryById.yaml";
//        String testCasesFilePath = "src/test/resources/Spotify/dtrace_simplified.csv";
//
//        // Equivalent to the getOpenAPISpecification private function
//        ParseOptions parseOptions = new ParseOptions();
//        parseOptions.setResolveFully(true);
//        parseOptions.setFlatten(true);
//        OpenAPI specification = new OpenAPIV3Parser().read(oasPath, null, parseOptions);
//
//        Paths paths = specification.getPaths();
//
//        // Get the Declaration file
//        for(Map.Entry<String, PathItem> path: paths.entrySet()) {
//
//            PathItem pathItem = path.getValue();
//
//            for (Map.Entry<PathItem.HttpMethod, Operation> operationEntry: pathItem.readOperationsMap().entrySet()) {
//                Operation operation = operationEntry.getValue();
//                String operationEndpoint = path.getKey().replace("/", "");
//
//                // Set the operation name for the .decls file
//                String operationName = getOperationName(operation, operationEntry, operationEndpoint);
//
//                // Extracting the input parameters
//                DeclsClass declsClassInput = new DeclsClass(packageName, operationName + "_Input", operationName + "_Input", operation.getParameters());
//                addNewDeclsClass(declsClassInput);
//
//                // Extracting the output parameters
//                generateOutputDeclsClasses(operationName, packageName, operation.getResponses());
//
//                // Extracting enter and exits
//                setDeclsClassEnterAndExit(packageName, operationEndpoint, operationName,
//                        "Input", operation.getParameters(), operation.getResponses());
//
//            }
//
//        }
//
//        DeclsFile declsFile = new DeclsFile(2.0, Comparability.implicit, getAllDeclsClasses());
//
//        // Generate dTrace file
//        List<TestCase> testCases = getTestCasesFromFile(testCasesFilePath);
//        for(TestCase testCase: testCases) {
//            for(DeclsClass declsClass: declsFile.getClasses()) {
//                // The enter and exits belong to the same class
//                if(declsClass.getPackageName().equalsIgnoreCase(packageName) &&
//                        declsClass.getClassName().equalsIgnoreCase(testCase.getPath().replace("/",""))){
//                    DeclsEnter declsEnter = declsClass.getDeclsEnters().get(0);
//
////                    System.out.println(declsEnter.generateDtrace(testCase));
//                    String[] enterDtraceLines = declsEnter.generateDtrace(testCase).split("\n");
//
//                    // Checks in ENTER
//                    assertEquals("Incorrect enter name", "main.browsecategories{category_id}.browseCategoriesByCategoryId(main.browseCategoriesByCategoryId_Input):::ENTER", enterDtraceLines[0]);
//                    assertEquals("Incorrect wrapper name", "input", enterDtraceLines[1]);
//                    assertEquals("Incorrect hashcode", "\"test_1hb878rv7d9ib_browseCategoriesByCategoryId_input_input\"", enterDtraceLines[2]);
//                    assertEquals("Incorrect end of trace", "1", enterDtraceLines[3]);
//
//                    // category_id
//                    assertEquals("Incorrect variable name", "input.category_id", enterDtraceLines[4]);
//                    assertEquals("Incorrect variable value", "\"hiphop\"", enterDtraceLines[5]);
//                    assertEquals("Incorrect end of trace", "1", enterDtraceLines[6]);
//
//                    // country
//                    assertEquals("Incorrect variable name", "input.country", enterDtraceLines[7]);
//                    assertEquals("Incorrect variable value", "\"SE\"", enterDtraceLines[8]);
//                    assertEquals("Incorrect end of trace", "1", enterDtraceLines[9]);
//
//                    // locale
//                    assertEquals("Incorrect variable name", "input.locale", enterDtraceLines[10]);
//                    assertEquals("Incorrect variable value", "null", enterDtraceLines[11]);
//                    assertEquals("Incorrect end of trace", "1", enterDtraceLines[12]);
//
//                    // Accept
//                    assertEquals("Incorrect variable name", "input.Accept", enterDtraceLines[13]);
//                    assertEquals("Incorrect variable value", "null", enterDtraceLines[14]);
//                    assertEquals("Incorrect end of trace", "1", enterDtraceLines[15]);
//
//
//
//                    // Get the correct declsExit by the responseCode
//                    DeclsExit declsExit = declsClass.getDeclsExits().stream().filter(x->x.getStatusCode().equalsIgnoreCase(testCase.getStatusCode())).findFirst()
//                            .orElseThrow(() -> new NullPointerException("Type of response not found in the specification"));
//
//                    // Checks in EXIT
//                    System.out.println(declsExit.generateDtrace(testCase));
//                    String[] exitDtraceLines = declsExit.generateDtrace(testCase).split("\n");
//                    // INPUT
//                    assertEquals("Incorrect exit name", "main.browsecategories{category_id}.browseCategoriesByCategoryId(main.browseCategoriesByCategoryId_Input):::EXIT1", exitDtraceLines[0]);
//                    assertEquals("Incorrect wrapper name", "input", exitDtraceLines[1]);
//                    assertEquals("Incorrect hashcode", "\"test_1hb878rv7d9ib_browseCategoriesByCategoryId_input_input\"", exitDtraceLines[2]);
//                    assertEquals("Incorrect end of trace", "1", exitDtraceLines[3]);
//
//                    // category_id
//                    assertEquals("Incorrect variable name", "input.category_id", exitDtraceLines[4]);
//                    assertEquals("Incorrect variable value", "\"hiphop\"", exitDtraceLines[5]);
//                    assertEquals("Incorrect end of trace", "1", exitDtraceLines[6]);
//
//                    // country
//                    assertEquals("Incorrect variable name", "input.country", exitDtraceLines[7]);
//                    assertEquals("Incorrect variable value", "\"SE\"", exitDtraceLines[8]);
//                    assertEquals("Incorrect end of trace", "1", exitDtraceLines[9]);
//
//                    // locale
//                    assertEquals("Incorrect variable name", "input.locale", exitDtraceLines[10]);
//                    assertEquals("Incorrect variable value", "null", exitDtraceLines[11]);
//                    assertEquals("Incorrect end of trace", "1", exitDtraceLines[12]);
//
//                    // Accept
//                    assertEquals("Incorrect variable name", "input.Accept", exitDtraceLines[13]);
//                    assertEquals("Incorrect variable value", "null", exitDtraceLines[14]);
//                    assertEquals("Incorrect end of trace", "1", exitDtraceLines[15]);
//
//
//                    // RETURN
//                    assertEquals("Incorrect wrapper name", "return", exitDtraceLines[16]);
//                    assertEquals("Incorrect hashcode", "\"test_1hb878rv7d9ib_browseCategoriesByCategoryId_return_output\"", exitDtraceLines[17]);
//                    assertEquals("Incorrect end of trace", "1", exitDtraceLines[18]);
//
//                    // href
//                    assertEquals("Incorrect variable name", "return.href", exitDtraceLines[19]);
//                    assertEquals("Incorrect variable value", "\"https://api.spotify.com/v1/browse/categories/hiphop\"", exitDtraceLines[20]);
//                    assertEquals("Incorrect end of trace", "1", exitDtraceLines[21]);
//
//                    // icons
//                    assertEquals("Incorrect variable name", "return.icons", exitDtraceLines[22]);
//                    assertEquals("Incorrect variable value", "\"test_1hb878rv7d9ib_browseCategoriesByCategoryId_return.icons_output\"", exitDtraceLines[23]);
//                    assertEquals("Incorrect end of trace", "1", exitDtraceLines[24]);
//
//                    // icons[..]
//                    assertEquals("Incorrect variable name", "return.icons[..]", exitDtraceLines[25]);
//                    assertEquals("Incorrect variable value", "[\"test_1hb878rv7d9ib_browseCategoriesByCategoryId_return.icons_output_1\"]", exitDtraceLines[26]);
//                    assertEquals("Incorrect end of trace", "1", exitDtraceLines[27]);
//
//                    // icons[..].height
//                    assertEquals("Incorrect variable name", "return.icons[..].height", exitDtraceLines[28]);
//                    assertEquals("Incorrect variable value", "[274]", exitDtraceLines[29]);
//                    assertEquals("Incorrect end of trace", "1", exitDtraceLines[30]);
//
//                    // icons[..].url
//                    assertEquals("Incorrect variable name", "return.icons[..].url", exitDtraceLines[31]);
//                    assertEquals("Incorrect variable value", "[\"https://t.scdn.co/media/original/hip-274_0a661854d61e29eace5fe63f73495e68_274x274.jpg\"]", exitDtraceLines[32]);
//                    assertEquals("Incorrect end of trace", "1", exitDtraceLines[33]);
//
//                    // icons[..].width
//                    assertEquals("Incorrect variable name", "return.icons[..].width", exitDtraceLines[34]);
//                    assertEquals("Incorrect variable value", "[274]", exitDtraceLines[35]);
//                    assertEquals("Incorrect end of trace", "1", exitDtraceLines[36]);
//
//                    // id
//                    assertEquals("Incorrect variable name", "return.id", exitDtraceLines[37]);
//                    assertEquals("Incorrect variable value", "\"hiphop\"", exitDtraceLines[38]);
//                    assertEquals("Incorrect end of trace", "1", exitDtraceLines[39]);
//
//                    // name
//                    assertEquals("Incorrect variable name", "return.name", exitDtraceLines[40]);
//                    assertEquals("Incorrect variable value", "\"Hip Hop\"", exitDtraceLines[41]);
//                    assertEquals("Incorrect end of trace", "1", exitDtraceLines[42]);
//
//                }
//
//            }
//
//        }
//
//
//
//    }

}
