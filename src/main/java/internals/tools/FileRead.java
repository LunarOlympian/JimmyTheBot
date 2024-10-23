package internals.tools;

import internals.JimmyFiles;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import static internals.JimmyFiles.jimmyPath;

public final class FileRead {

    public static String readFile(String pathFromRoot) {
        if(!new File(jimmyPath + pathFromRoot).exists()) return "";
        try {
            return Files.readString(new File(jimmyPath + pathFromRoot).toPath());
        } catch(IOException ioException) {
            ioException.printStackTrace();
        }

        return "";
    }
}
