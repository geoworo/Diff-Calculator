package hexlet.code;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.List;

public class Differ {
    public static String generate(String filepath1, String filepath2, String format) throws Exception {

        var file1 = getFile(filepath1);
        var file2 = getFile(filepath2);

        var format1 = getFormat(file1);
        var format2 = getFormat(file2);

        var map1 = Parser.parse(Files.readString(file1.toPath()), format1);
        var map2 = Parser.parse(Files.readString(file2.toPath()), format2);

        List<Map<String, Object>> list = DiffCalculator.calculateDifference(map1, map2);

        return Formatter.format(list, format);

    }

    public static String generate(String filepath1, String filepath2) throws Exception {
        return generate(filepath1, filepath2, "stylish");
    }

    public static File getFile(String filepath) {
        Path normalizedFilePath = Paths.get(filepath).toAbsolutePath().normalize();
        return new File(String.valueOf(normalizedFilePath));
    }

    public static String getFormat(File file) {
        String fileName = file.getName();
        int index = fileName.lastIndexOf('.');
        return fileName.substring(index + 1);
    }
}
