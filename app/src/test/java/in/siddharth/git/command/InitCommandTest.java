package in.siddharth.git.command;

import org.junit.jupiter.api.Test;

import java.nio.file.Paths;

import static in.siddharth.git.command.CommandHelpers.GIT_ROOT;
import static org.assertj.core.api.Assertions.assertThat;

class InitCommandTest {

    @Test
    void getDirectoriesToCreate__should_create_jit__when_no_arg() {
        String cwd = CommandHelpers.getCwd();

        InitCommand command = InitCommand.buildInstance(null);

        assertThat(command.getDirectoriesToCreate())
                .containsOnly(Paths.get(cwd, GIT_ROOT, "objects"),
                        Paths.get(cwd, GIT_ROOT, "refs"));
    }

    @Test
    void getDirectoriesToCreate__should_create_jit_in_nested_sub_dir__when_relative_path() {
        String wd = "hello";

        InitCommand command = InitCommand.buildInstance(wd);

        String expectedRootWd = Paths.get(CommandHelpers.getCwd(), wd).toString();
        assertThat(command.getDirectoriesToCreate())
                .containsOnly(Paths.get(expectedRootWd, GIT_ROOT, "objects"),
                        Paths.get(expectedRootWd, GIT_ROOT, "refs"));
    }

    @Test
    void getDirectoriesToCreate__should_create_jit_in_dir__when_absolute_path() {
        String wd = "/etc/repo";

        InitCommand command = InitCommand.buildInstance(wd);

        assertThat(command.getDirectoriesToCreate())
                .containsOnly(Paths.get(wd, GIT_ROOT, "objects"),
                        Paths.get(wd, GIT_ROOT, "refs"));
    }
}