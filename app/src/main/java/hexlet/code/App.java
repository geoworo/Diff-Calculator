package hexlet.code;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

@Command(name = "gendiff", description = "Compares two configuration files and shows a difference.")

public class App {

    @Option(names = {"-h", "--help"}, usageHelp = true, description = "Show this help message and exit.")
    private boolean usageHelpRequested = false;

    @Option(names = {"-V", "--version"}, versionHelp = true, description = "Print version information and exit.")
    boolean versionInfoRequested;

    public static void main(String[] args) {
        App app = CommandLine.populateCommand(new App(), args);
        CommandLine commandLine = new CommandLine(new App());
        commandLine.parseArgs(args);
        if (app.usageHelpRequested) {
            CommandLine.usage(new App(), System.out);
            return;
        } else if (commandLine.isVersionHelpRequested()) {
            commandLine.printVersionHelp(System.out);
            return;
        }
        System.out.println("Hello World!");
    }
}
