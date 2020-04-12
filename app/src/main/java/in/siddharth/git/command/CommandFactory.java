package in.siddharth.git.command;

public class CommandFactory {

    public static Command getCommand(String command, String... additionalArgs) {
        switch (command) {
            case "init":
                return InitCommand.buildInstance(CommandHelpers.getCwd());
            default:
                throw new IllegalArgumentException("Unknown command");
        }
    }
}
