package es.us.isa.jsoninstrumenter.util;

import java.util.*;

import static es.us.isa.jsoninstrumenter.util.CSVManager.createCSVwithHeader;
import static es.us.isa.jsoninstrumenter.util.CSVManager.writeCSVRow;
import static es.us.isa.jsoninstrumenter.util.FileManager.checkIfExists;

public class Timer {

    private static Map<Integer, List<Long>> counters = new HashMap<>();

    public static void startCounting(int step) {
        counters.putIfAbsent(step, new ArrayList<>());
        List<Long> stepMeasures = counters.get(step);
        if (stepMeasures.size() > 0 && stepMeasures.get(stepMeasures.size()-1) < 0) {
            stepMeasures.remove(stepMeasures.size() - 1);
        }
        stepMeasures.add(-new Date().getTime());
    }

    public static void stopCounting(int step) {
        Long stopTime = new Date().getTime();
        List<Long> stepMeasures = counters.get(step);
        stepMeasures.set(stepMeasures.size()-1, stopTime+stepMeasures.get(stepMeasures.size()-1));
    }

    public static void exportToCSV(String path) throws Exception {
        for(int executionNumber : counters.keySet()) {
            List<Long> values = counters.get(executionNumber);
            if(values.size()!=1) {
                throw new Exception("Unexpected list size");
            }
            writeCSVRow(path, executionNumber + "," + counters.get(executionNumber).get(0));
        }

        counters = new HashMap<>();
    }

    private static void writeRow(String path, int i) {
        StringBuilder row = new StringBuilder();
        boolean first = true;

        for(Map.Entry<Integer, List<Long>> entry : counters.entrySet()) {
            Long value;
            value = entry.getValue().get(i);
            if (first) {
                row.append(value);
                first = false;
            } else row.append(",").append(value);
        }

        writeCSVRow(path, row.toString());
    }

}
