package in.siddharth.git.command;

import com.google.common.annotations.VisibleForTesting;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

import static in.siddharth.git.command.CommandHelpers.GIT_ROOT;

public class InitCommand implements Command {

    private final Path rootPath;

    private InitCommand(Path rootPath) {
        this.rootPath = rootPath;
    }

    public static InitCommand buildInstance(String path) {
        return new InitCommand(Paths.get(path, GIT_ROOT));
    }

    @Override
    public boolean run() {
        getDirectoriesToCreate().forEach(this::createDirectory);
        return true;
    }

    @VisibleForTesting
    public Stream<Path> getDirectoriesToCreate() {
        return Stream.of("objects", "refs")
                .map(dir -> Paths.get(rootPath.toString(), dir));
    }

    private void createDirectory(Path directory) {
        if (!Files.exists(directory)) {
            try {
                Files.createDirectories(directory);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
