package es.us.isa.jsoninstrumenter.util;

import es.us.isa.jsoninstrumenter.pojos.TestCase;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

            List<String> headerParameters = Arrays.asList(row.get(headerParametersIndex).split("\\s*;\\s*"));
            List<String> pathParameters = Arrays.asList(row.get(pathParametersIndex).split("\\s*;\\s*"));
            List<String> queryParameters = Arrays.asList(row.get(queryParametersIndex).split("\\s*;\\s*"));
            List<String> formParameters = Arrays.asList(row.get(formParametersIndex).split("\\s*;\\s*"));

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

}
