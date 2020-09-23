package utils;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class CUtils {

    public static void write(String generated, String path) {
        if (path.contains(".mp"))
            path = path.replace(".mp", ".c");
        else
            path+=".c";
        try (FileWriter fw = new FileWriter(path)) {
            fw.write(generated);
        } catch (IOException ex) {
            System.err.println("Error in saving generated code");
        }
    }



    public static String getTemplate() {
        String template = null;
        String templatePath= "templates/template.c";
        try {
           template = new String(Files.readAllBytes(Paths.get(templatePath)));
        } catch (FileNotFoundException e) {
            System.err.println("File not found at" +templatePath);

        } catch (IOException e) {
            System.err.println("Error in template reading!");
        }
        
        return template;
    }
}
