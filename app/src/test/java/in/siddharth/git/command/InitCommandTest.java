package in.siddharth.git.command;

import org.junit.jupiter.api.Test;

import java.nio.file.Paths;

import static in.siddharth.git.command.CommandHelpers.GIT_ROOT;
import static org.assertj.core.api.Assertions.assertThat;

class InitCommandTest {

    @Test
    void getDirectoriesToCreate__should_return_valid_path__when_using_cwd() {
        String cwd = CommandHelpers.getCwd();

        InitCommand command = InitCommand.buildInstance(cwd);

        assertThat(command.getDirectoriesToCreate())
                .containsOnly(Paths.get(cwd, GIT_ROOT, "objects"),
                        Paths.get(cwd, GIT_ROOT, "refs"));
    }

    @Test
    void getDirectoriesToCreate__should_return_valid_path__when_using_explicit_path() {
        String wd = "/etc/repo";

        InitCommand command = InitCommand.buildInstance(wd);

        assertThat(command.getDirectoriesToCreate())
                .containsOnly(Paths.get(wd, GIT_ROOT, "objects"),
                        Paths.get(wd, GIT_ROOT, "refs"));
    }
}