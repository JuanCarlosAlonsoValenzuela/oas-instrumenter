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
                )
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
