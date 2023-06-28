package hexlet.code;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.List;

public class Differ {
    public static String generate(String filepath1, String filepath2, String format) throws Exception {

        Path normalizedFilePath1 = Paths.get(filepath1).toAbsolutePath().normalize();
        Path normalizedFilePath2 = Paths.get(filepath2).toAbsolutePath().normalize();
        File file1 = new File(String.valueOf(normalizedFilePath1));
        File file2 = new File(String.valueOf(normalizedFilePath2));

        String fileName1 = file1.getName();
        int index1 = fileName1.lastIndexOf('.');
        String fileName2 = file2.getName();
        int index2 = fileName1.lastIndexOf('.');

        String fileformat1 = fileName1.substring(index1 + 1);
        String fileformat2 = fileName2.substring(index2 + 1);

        Map<String, Object> map1 = Parser.parse(file1, fileformat1);
        Map<String, Object> map2 = Parser.parse(file2, fileformat2);

        List<Map<String, Object>> list = DiffCalculator.calculateDifference(map1, map2);

        return Formatter.format(list, format);

    }

    public static String generate(String filepath1, String filepath2) throws Exception {
        return generate(filepath1, filepath2, "stylish");
    }
}
