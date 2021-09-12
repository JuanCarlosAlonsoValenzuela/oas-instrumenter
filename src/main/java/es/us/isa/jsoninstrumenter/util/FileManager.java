package es.us.isa.jsoninstrumenter.util;

import java.io.FileWriter;
import java.io.IOException;

public class FileManager {

    public static void writeFile(String filepath, String content) {
        try {
            FileWriter myWriter = new FileWriter(filepath);
            myWriter.write(content);
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
