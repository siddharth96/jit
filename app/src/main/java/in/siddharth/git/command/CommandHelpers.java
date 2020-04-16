package in.siddharth.git.command;

public class CommandHelpers {
    public static final String GIT_ROOT = ".jit";

    public static String getCwd() {
        return System.getProperty("user.dir");
    }

    public static void writeToOutput(String output) {
        System.out.println(output);
    }
}
