package in.siddharth.git.command;

import java.nio.file.Path;
import java.nio.file.Paths;

public class CommandHelpers {
    public static Path getCwd() {
        return Paths.get(System.getProperty("user.dir"), ".jit");
    }
}
