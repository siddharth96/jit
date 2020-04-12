package in.siddharth.git.command;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

public class InitCommand implements Command {

    private final Path rootPath;

    private InitCommand(Path rootPath) {
        this.rootPath = rootPath;
    }

    public static InitCommand buildInstance(Path path) {
        return new InitCommand(path);
    }

    @Override
    public boolean run() {
        Arrays.asList("objects", "refs").forEach(dir -> createDirectory(rootPath, dir));
        return true;
    }

    private void createDirectory(Path directoryParent, String innerDirectory) {
        Path directory = Paths.get(directoryParent.toString(), innerDirectory);
        if (!Files.exists(directory)) {
            try {
                Files.createDirectories(directory);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
