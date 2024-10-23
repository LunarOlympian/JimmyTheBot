package internals;

import jimmy.Runner;

import static internals.JimmyFiles.jimmyPath;
import static internals.tools.FileRead.readFile;

public final class Debug {

    public static boolean debugMode = getDebugMode();

    private static boolean getDebugMode() {
        return readFile("Internals/Debug.txt").equals("true");
    }
}
