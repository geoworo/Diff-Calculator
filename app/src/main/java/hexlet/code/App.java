package hexlet.code;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

import java.util.concurrent.Callable;

@Command(name = "gendiff", mixinStandardHelpOptions = true, version = "1.0",
        description = "Compares two configuration files and shows a difference.")

public final class App implements Callable<Integer> {
    @Parameters(index = "0", paramLabel = "filepath1", description = "path to first file")
    private static String path1;
    @Parameters(index = "1", paramLabel = "filepath2", description = "path to second file")
    private static String path2;
    @Option(names = {"-f", "--format"}, defaultValue = "stylish", description = "output format (default: stylish)")
    private static String format;

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
        int exitCode = new CommandLine(new App()).execute(args);
        System.exit(exitCode);
    }
}
