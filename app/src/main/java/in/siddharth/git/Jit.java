package in.siddharth.git;

import com.google.common.base.Preconditions;
import in.siddharth.git.command.Command;
import in.siddharth.git.command.CommandFactory;

import java.util.Arrays;

public class Jit {
    public static void main(String[] args) {
        Preconditions.checkArgument(args.length >= 1, "Too few arguments");
        String[] additionalArgs = args.length > 1 ? Arrays.copyOfRange(args, 1, args.length) : null;
        Command command = CommandFactory.getCommand(args[0], additionalArgs);
        command.run();
    }
}
