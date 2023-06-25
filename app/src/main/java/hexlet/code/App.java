package hexlet.code;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

import java.util.concurrent.Callable;

@Command(name = "gendiff", description = "Compares two configuration files and shows a difference.")

public final class App implements Callable<Integer> {

    @Parameters(paramLabel = "filepath1", description = "path to first file")
    static private String path1;
    @Parameters(paramLabel = "filepath2", description = "path to second file")
    static private String path2;
    @Option(names = {"-f", "--format"}, defaultValue = "stylish",
            description = "output format (default: stylish)")
    static private String format;

    @Option(names = {"-h", "--help"}, usageHelp = true, description = "Show this help message and exit.")
    static private boolean usageHelpRequested = false;

    @Option(names = {"-V", "--version"}, versionHelp = true, description = "Print version information and exit.")
    static private boolean versionInfoRequested;



    @Override
    public Integer call() throws Exception {
        System.out.println(Differ.generate(path1, path2, format));
        return null;
    }

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
        int exitCode = new CommandLine(new App()).execute(args);
        System.exit(exitCode);
    }
}
