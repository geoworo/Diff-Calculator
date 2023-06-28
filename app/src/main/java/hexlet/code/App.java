package hexlet.code;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

import java.util.concurrent.Callable;

@Command(name = "gendiff", mixinStandardHelpOptions = true, version = "gendiff 1.0",
        description = "Compares two configuration files and shows a difference.")

public final class App implements Callable<Integer> {
    @Parameters(index = "0", paramLabel = "filepath1", description = "path to first file")
    private static String path1;
    @Parameters(index = "1", paramLabel = "filepath2", description = "path to second file")
    private static String path2;
    @Option(names = {"-f", "--format"}, defaultValue = "stylish", description = "output format (default: stylish)")
    private static String format;
    @Option(names = {"-h", "--help"}, usageHelp = true, description = "Show this help message and exit.")
    private static boolean usageHelpRequested = false;
    @Option(names = {"-V", "--version"}, versionHelp = true, description = "Print version information and exit.")
    private static boolean versionInfoRequested;

    @Override
    public Integer call() throws Exception {
        try {
            System.out.println(Differ.generate(path1, path2, format));
        } catch (Exception e) {
            System.out.println(e);
            return 1;
        }
        return 0;
    }

    public static void main(String[] args) {
        App app = CommandLine.populateCommand(new App(), args);
        CommandLine commandLine = new CommandLine(new App());
        commandLine.parseArgs(args);
        if (usageHelpRequested) {
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
