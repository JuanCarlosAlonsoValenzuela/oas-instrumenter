package es.us.isa.jsoninstrumenter.dtrace.enter;

import es.us.isa.jsoninstrumenter.main.GenerateDeclsFile;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.stream.Stream;

import static es.us.isa.jsoninstrumenter.main.GenerateDeclsFile.deleteAllDeclsClasses;
import static es.us.isa.jsoninstrumenter.main.GenerateDeclsFile.numberOfExits;
import static es.us.isa.jsoninstrumenter.util.FileManager.deleteFile;
import static es.us.isa.jsoninstrumenter.util.FileManager.readFileAsString;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class PrimitiveParametersTest {

    private static Stream<Arguments> dtraceGeneration() {
        return Stream.of(
                /*
                 *  TEST CASES RELATED TO ENTER
                 */
                /*      e2e_dtrace_enter_001
                This test receives as an input parameters of type primitive in query, path, header and form
                    (doubleInQuery, stringInPath, integerInHeader, booleanInForm)
                 */
                Arguments.of("src/test/resources/dtraceOracles/enter/primitiveInputs/primitiveInputs.yaml", "src/test/resources/dtraceOracles/enter/primitiveInputs/setValues/testCase_primitiveInputs.csv",
                        "src/test/resources/dtraceOracles/enter/primitiveInputs/dtraceFile.dtrace", "src/test/resources/dtraceOracles/enter/primitiveInputs/setValues/dtraceFile_primitiveInputs.dtrace"
                ),
                /*      e2e_dtrace_enter_002
                This test uses the same OAS spec as e2e_dtrace_enter_001, but the values of the parameters are null
                in the csv containing the test cases. Note that null doubles must be printed as "nonsensical" in the dtrace file
                 */
                Arguments.of("src/test/resources/dtraceOracles/enter/primitiveInputs/primitiveInputs.yaml", "src/test/resources/dtraceOracles/enter/primitiveInputs/nullValues/testCase_primitiveInputsNullValues.csv",
                        "src/test/resources/dtraceOracles/enter/primitiveInputs/dtraceFile.dtrace", "src/test/resources/dtraceOracles/enter/primitiveInputs/nullValues/dtraceFile_primitiveInputsNullValues.dtrace"
                ),

                /*
                 *  TEST CASES RELATED TO EXIT
                 */
                /* e2e_dtrace_exit_001
                The response of this test case is an object that contains properties of type primitive (string, double, integer and boolean)
                 */
                Arguments.of("src/test/resources/dtraceOracles/exit/primitiveExit/primitiveExit.yaml", "src/test/resources/dtraceOracles/exit/primitiveExit/setValues/testCase_primitiveExit.csv",
                        "src/test/resources/dtraceOracles/exit/primitiveExit/dtraceFile.dtrace", "src/test/resources/dtraceOracles/exit/primitiveExit/setValues/dtraceFile_primitiveExit.dtrace"
                ),
                /* e2e_dtrace_exit_002
                This test uses the same OAS spec as e2e_dtrace_exit_002, but the values of the properties of the response are null
                in the csv containing the test cases. Note that null doubles must be printed as "nonsensical" in the dtrace file
                 */
                Arguments.of("src/test/resources/dtraceOracles/exit/primitiveExit/primitiveExit.yaml", "src/test/resources/dtraceOracles/exit/primitiveExit/nullValues/testCase_primitiveExit_nullValues.csv",
                        "src/test/resources/dtraceOracles/exit/primitiveExit/dtraceFile.dtrace", "src/test/resources/dtraceOracles/exit/primitiveExit/nullValues/dtraceFile_primitiveExit_nullValues.dtrace"
                ),
                /* e2e_dtrace_exit_003  (Nested objects in the exit)
                The response of this test case contains a property of type object (Nested object), with one of its properties being an array of strings (Primitive)
                This object also contains another property of type object (Nested object inside a nested object) with one of its properties being an array of type double
                 */
                Arguments.of("src/test/resources/dtraceOracles/exit/objectExit/objectExit.yaml", "src/test/resources/dtraceOracles/exit/objectExit/setValues/testCase_objectExit.csv",
                        "src/test/resources/dtraceOracles/exit/objectExit/dtraceFile.dtrace", "src/test/resources/dtraceOracles/exit/objectExit/setValues/dtraceFile_objectExit.dtrace"
                ),
                /* e2e_dtrace_exit_004  (Nested object with null value)
                This test uses the same OAS spec as e2e_dtrace_exit_003, but the value of the nested object is null, and therefore all its properties must be null too.
                The array of doubles must be null (Not nonsensical)
                 */
                Arguments.of("src/test/resources/dtraceOracles/exit/objectExit/objectExit.yaml", "src/test/resources/dtraceOracles/exit/objectExit/nullValues/nullObject/testCase_objectExit_nullObject.csv",
                        "src/test/resources/dtraceOracles/exit/objectExit/dtraceFile.dtrace", "src/test/resources/dtraceOracles/exit/objectExit/nullValues/nullObject/dtraceFile_objectExit_nullObject.dtrace"
                ),
                /* e2e_dtrace_exit_005 (Arrays of primitive elements containing null values)
                This test uses the same OAS spec as e2e_dtrace_exit_003, but the arrays of primitive elements contain a null element
                 */
                Arguments.of("src/test/resources/dtraceOracles/exit/objectExit/objectExit.yaml", "src/test/resources/dtraceOracles/exit/objectExit/nullValues/arraysWithNullElements/testCase_objectExit_arraysWitNullElements.csv",
                        "src/test/resources/dtraceOracles/exit/objectExit/dtraceFile.dtrace", "src/test/resources/dtraceOracles/exit/objectExit/nullValues/arraysWithNullElements/dtraceFile_objectExit_arraysWithNullElements.dtrace"
                ),
                /* e2e_dtrace_exit_006 (Array with elements of type object)
                    The response of this test contains a property of type array of objects.
                    This test also checks the behaviour of the instrumenter when there are more than one exit per API operation (due to arrays of objects)
                 */
                Arguments.of("src/test/resources/dtraceOracles/exit/arrayOfObjects/arrayOfObjects.yaml", "src/test/resources/dtraceOracles/exit/arrayOfObjects/setValues/testCase_arrayOfObjects.csv",
                        "src/test/resources/dtraceOracles/exit/arrayOfObjects/dtraceFile.dtrace", "src/test/resources/dtraceOracles/exit/arrayOfObjects/setValues/dtraceFile_arrayOfObjects.dtrace"
                ),
                /* e2e_dtrace_exit_007 (Array of objects with one of the elements being null)
                This test uses the same OAS spec as e2e_dtrace_exit_006, but one of the elements of the array (i.e., one of the objects) is null
                 */
                /*
                TODO: Currently this test case does not print the null element (The second EXIT2), which makes sense.
                 Nevertheless, we should consider changing the hashcode of the null element for "null" (Check whether DAIKON accepts this)
                 */
                Arguments.of("src/test/resources/dtraceOracles/exit/arrayOfObjects/arrayOfObjects.yaml", "src/test/resources/dtraceOracles/exit/arrayOfObjects/nullValues/elementOfArrayNull/testCase_arrayOfObjects_elementOfArrayNull.csv",
                        "src/test/resources/dtraceOracles/exit/arrayOfObjects/dtraceFile.dtrace", "src/test/resources/dtraceOracles/exit/arrayOfObjects/nullValues/elementOfArrayNull/dtraceFile_arrayOfObjects_elementOfArrayNull.dtrace"
                ),
                /* e2e_dtrace_exit008 (Null array of objects)
                This test uses the same OAS spec as e2e_dtrace_exit_006, but the array of objects is null
                 */
                Arguments.of("src/test/resources/dtraceOracles/exit/arrayOfObjects/arrayOfObjects.yaml", "src/test/resources/dtraceOracles/exit/arrayOfObjects/nullValues/nullArray/testCase_arrayOfObjects_nullArray.csv",
                        "src/test/resources/dtraceOracles/exit/arrayOfObjects/dtraceFile.dtrace", "src/test/resources/dtraceOracles/exit/arrayOfObjects/nullValues/nullArray/dtraceFile_arrayOfObjects_nullArray.dtrace"
                )//,
                /* e2e_dtrace_exit009 (Exit of type array, bad practice)
                This test analyses a response of type array (Without being a property of an object). This type of response is a bad practice, but
                it is used by APIs such as RestCountries
                 */





                /*
                Array response
                Null array response
                Array response with nesting
                Array response with nesting and null values
                 */
        );
    }

    @ParameterizedTest
    @MethodSource("dtraceGeneration")
    public void testDtraceGeneration(String oasSpecPath, String testCasesCSVPath, String generatedPath, String oraclePath) throws IOException {
        numberOfExits = 1;
        deleteAllDeclsClasses();

        // OAS spec and testCases.csv
        String[] args = {oasSpecPath,
                testCasesCSVPath};
        GenerateDeclsFile.main(args);

        String[] generatedDtrace = readFileAsString(generatedPath, StandardCharsets.UTF_8).split("\n");


        // Read files as list of String
        String[] oracleDtrace = readFileAsString(oraclePath, StandardCharsets.UTF_8).split("\n");


        // Assert both dtrace files have the same number of lines
        Assertions.assertEquals(oracleDtrace.length, generatedDtrace.length, "The generated dtrace file does not have the expected number of lines");
        assertNotEquals("Path and oracle path must be different", generatedPath, oraclePath);

        for(int i = 0; i < oracleDtrace.length ; i++) {
            System.out.println(oracleDtrace[i]);
            int lineNumber = i + 1;
            Assertions.assertEquals(oracleDtrace[i].trim(), generatedDtrace[i], "The content of line " + lineNumber + " content is different from the expected");
        }

        // Remove the generated file
        deleteFile(generatedPath);
    }



}
