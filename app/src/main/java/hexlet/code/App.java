package hexlet.code;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

import java.util.concurrent.Callable;

@Command(name = "gendiff", description = "Compares two configuration files and shows a difference.")

public class App implements Callable<Integer> {

    @Parameters(paramLabel = "filepath1", description = "path to first file")
    static
        String path1;
    @Parameters(paramLabel = "filepath2", description = "path to second file")
    static
        String path2;
    @Option(names = {"-f", "--format"}, defaultValue = "stylish",
            description = "output format (default: stylish)")
        String format;

    @Option(names = {"-h", "--help"}, usageHelp = true, description = "Show this help message and exit.")
    boolean usageHelpRequested = false;

    @Option(names = {"-V", "--version"}, versionHelp = true, description = "Print version information and exit.")
    boolean versionInfoRequested;



    @Override
    public Integer call() throws Exception {
        System.out.println(Differ.generate(path1, path2));
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
