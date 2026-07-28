package mermaid;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class MermaidFileReader {

    /**
     * Reads a Mermaid code file and returns its content as a String.
     *
     * @param filePath The relative or absolute path to the file (e.g., "diagram.mmd")
     * @return The Mermaid code as a String, or an empty string if reading fails.
     */
    public static String readFromFile(String filePath) {
        Path path = Paths.get(filePath);
        
        try {
            // Files.readString is available in Java 11 and later
            return Files.readString(path);
        } catch (IOException e) {
            System.err.println("Error: Could not read the Mermaid file at '" + filePath + "'");
            System.err.println("Reason: " + e.getMessage());
            return ""; 
        }
    }
}