package in.siddharth.git.command;

public class CommandFactory {

    public static Command getCommand(String command, String... additionalArgs) {
        switch (command) {
            case "init":
                return InitCommand.buildInstance(
                        hasAdditionalArguments(additionalArgs)
                                ? additionalArgs[0]
                                : null
                );
            default:
                throw new IllegalArgumentException("Unknown command");
        }
    }

    private static boolean hasAdditionalArguments(String... additionalArgs) {
        return additionalArgs != null && additionalArgs.length > 0;
    }
}
