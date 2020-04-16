package in.siddharth.git.command;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Strings;

import javax.annotation.Nullable;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

import static in.siddharth.git.command.CommandHelpers.GIT_ROOT;
import static in.siddharth.git.command.CommandHelpers.writeToOutput;

public class InitCommand implements Command {

    private final Path rootPath;

    private InitCommand(Path rootPath) {
        this.rootPath = rootPath;
    }

    public static InitCommand buildInstance(@Nullable String path) {

        String rootPath = buildRootPath(path);
        return new InitCommand(Paths.get(rootPath, GIT_ROOT));
    }

    private static String buildRootPath(@Nullable String path) {
        if (Strings.isNullOrEmpty(path)) {
            return CommandHelpers.getCwd();
        }
        if (Paths.get(path).isAbsolute()) {
            return path;
        }
        return Paths.get(CommandHelpers.getCwd(), path).toString();
    }

    @Override
    public boolean run() {
        getDirectoriesToCreate().forEach(this::createDirectory);
        writeToOutput(String.format("Initialized empty Jit repository in %s", rootPath));
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
