package es.us.isa.jsoninstrumenter.dtrace.enter;

import es.us.isa.jsoninstrumenter.main.GenerateDeclsFile;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import static es.us.isa.jsoninstrumenter.main.GenerateDeclsFile.packageName;
import static es.us.isa.jsoninstrumenter.util.FileManager.deleteFile;
import static es.us.isa.jsoninstrumenter.util.FileManager.readFileAsString;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class PrimitiveParametersTest {


    @Test
    public void testPrimitiveInputParameters() throws IOException {
        String path = "src/test/resources/dtraceOracles/enter/primitiveInputs/dtraceFile.dtrace";
//        deleteFile(path);

        // OAS spec and testCases.csv
        String[] args = {"src/test/resources/dtraceOracles/enter/primitiveInputs/primitiveInputs.yaml",
                "src/test/resources/dtraceOracles/enter/primitiveInputs/setValues/testCase_primitiveInputs.csv"};
        GenerateDeclsFile.main(args);

        String[] generatedDtrace = readFileAsString(path, StandardCharsets.UTF_8).split("\n");

        String oraclePath = "src/test/resources/dtraceOracles/enter/primitiveInputs/setValues/dtraceFile_primitiveInputs.dtrace";
        // Read files as list of String
        String[] oracleDtrace = readFileAsString(oraclePath, StandardCharsets.UTF_8).split("\n");


        // Assert both dtrace files have the same number of lines
        assertEquals("The generated dtrace files does not have the expected number of lines", oracleDtrace.length, generatedDtrace.length);
        assertNotEquals("Path and oracle path must be different", path, oraclePath);

        for(int i = 0; i < oracleDtrace.length ; i++) {
            System.out.println(oracleDtrace[i]);
            int lineNumber = i + 1;
            assertEquals("The content of line " + lineNumber + " content is different from the expected", oracleDtrace[i], generatedDtrace[i]);
        }

        // Remove the generated file
        deleteFile(path);
    }

    @Test
    public void testPrimitiveInputParametersWithNullValues() throws IOException {
        String dtracePath = "src/test/resources/dtraceOracles/enter/primitiveInputs/dtraceFile.dtrace";
//        deleteFile(path);

        // OAS spec and testCases.csv
        String[] args = {"src/test/resources/dtraceOracles/enter/primitiveInputs/primitiveInputs.yaml",
                "src/test/resources/dtraceOracles/enter/primitiveInputs/nullValues/testCase_primitiveInputsNullValues.csv"};
        GenerateDeclsFile.main(args);


        String[] generatedDtrace = readFileAsString(dtracePath, StandardCharsets.UTF_8).split("\n");

        String oraclePath = "src/test/resources/dtraceOracles/enter/primitiveInputs/nullValues/dtraceFile_primitiveInputsNullValues.dtrace";
        // Read files as list of String
        String[] oracleDtrace = readFileAsString(oraclePath, StandardCharsets.UTF_8).split("\n");


        // Assert both dtrace files have the same number of lines
        assertEquals("The generated dtrace files does not have the expected number of lines", oracleDtrace.length, generatedDtrace.length);
        assertNotEquals("Path and oracle path must be different", dtracePath, oraclePath);

        for(int i = 0; i < oracleDtrace.length ; i++) {
            System.out.println(oracleDtrace[i]);
            int lineNumber = i + 1;
            assertEquals("The content of line " + lineNumber + " content is different from the expected", oracleDtrace[i], generatedDtrace[i]);
        }

        // Remove the generated file
        deleteFile(dtracePath);
    }
}
