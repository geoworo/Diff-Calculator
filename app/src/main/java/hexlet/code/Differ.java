package hexlet.code;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.List;

public class Differ {
    public static String generate(String filepath1, String filepath2, String format) throws Exception {

        var path1 = getPath(filepath1);
        var path2 = getPath(filepath2);

        var format1 = getFormat(filepath1);
        var format2 = getFormat(filepath2);

        var map1 = Parser.parse(Files.readString(path1), format1);
        var map2 = Parser.parse(Files.readString(path2), format2);

        List<Map<String, Object>> list = DiffCalculator.calculateDifference(map1, map2);

        return Formatter.format(list, format);

    }

    public static String generate(String filepath1, String filepath2) throws Exception {
        return generate(filepath1, filepath2, "stylish");
    }

    private static Path getPath(String filepath) {
        return Paths.get(filepath).toAbsolutePath().normalize();
    }

    private static String getFormat(String path) {
        return path.substring(path.lastIndexOf(".") + 1);
    }
}
