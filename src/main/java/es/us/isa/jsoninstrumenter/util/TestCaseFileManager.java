package es.us.isa.jsoninstrumenter.util;

import es.us.isa.jsoninstrumenter.model.TestCase;

import java.util.*;
import java.util.stream.Collectors;

import static es.us.isa.jsoninstrumenter.util.CSVManager.readCSV;

public class TestCaseFileManager {

    public static List<TestCase> getTestCasesFromFile(String path) {
        List<TestCase> res = new ArrayList<>();

        List<List<String>> testCaseCSV = readCSV(path, ',');
        List<String> headers = testCaseCSV.get(0);

        int testCaseIdIndex = getIndexOfElementInHeaders(headers, "testCaseId");
        int operationIdIndex = getIndexOfElementInHeaders(headers,"operationId");
        int pathIndex = getIndexOfElementInHeaders(headers,"path");
        int httpMethodIndex = getIndexOfElementInHeaders(headers,"httpMethod");
        int headerParametersIndex = getIndexOfElementInHeaders(headers,"headerParameters");
        int pathParametersIndex = getIndexOfElementInHeaders(headers, "pathParameters");
        int queryParametersIndex = getIndexOfElementInHeaders(headers, "queryParameters");
        int formParametersIndex = getIndexOfElementInHeaders(headers, "formParameters");
        int bodyParameterIndex = getIndexOfElementInHeaders(headers, "bodyParameter");
        int statusCodeIndex = getIndexOfElementInHeaders(headers, "statusCode");
        int responseBodyIndex = getIndexOfElementInHeaders(headers,"responseBody");

        for(int i = 1; i < testCaseCSV.size(); i++) {
            List<String> row = testCaseCSV.get(i);

            Map<String, String> headerParameters = stringToMap(row.get(headerParametersIndex));
            Map<String, String> pathParameters = stringToMap(row.get(pathParametersIndex));
            Map<String, String> queryParameters = stringToMap(row.get(queryParametersIndex));
            Map<String, String> formParameters = stringToMap(row.get(formParametersIndex));

            TestCase testCase = new TestCase(row.get(testCaseIdIndex), row.get(operationIdIndex), row.get(pathIndex),
                    row.get(httpMethodIndex), headerParameters, pathParameters, queryParameters,
                    formParameters, row.get(bodyParameterIndex),
                    row.get(statusCodeIndex), row.get(responseBodyIndex));

            res.add(testCase);

        }

        return res;

    }

    private static int getIndexOfElementInHeaders(List<String> headers, String header){
        for(int i = 0; i < headers.size(); i++) {
            if(headers.get(i).equalsIgnoreCase(header)) {
                return i;
            }
        }
        throw new NullPointerException("Element " + header + " not found in the csv headers");
    }

    private static Map<String, String> stringToMap(String str) {
        if(str.trim().isEmpty()){
            return new HashMap<>();
        }else {
            Map<String, String> res = Arrays.asList(str.split("\\s*;\\s*")).stream().map(s -> s.split("="))
                    .collect(Collectors.toMap(a -> a[0], a -> a[1]));
            // Remove all new line chars (\n and \r). The parameter value line of the dTrace must be specified in a single line
            res.replaceAll((k,v) -> removeNewLineChars(v));
            return res;
        }
    }

    public static String removeNewLineChars(String str) {
        String res = str.replace("\n","\\n");
        res = res.replace("\r","\\r");
        return res;
    }

}
