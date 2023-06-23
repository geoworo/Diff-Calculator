package hexlet.code;

import java.util.Map;
import java.util.List;

public class Differ {
    public static String generate(String filepath1, String filepath2, String format) throws Exception {

        Map<String, Object> map1 = Parser.parse(filepath1);
        Map<String, Object> map2 = Parser.parse(filepath2);

        List<Map<String, Object>> list = DiffCalculator.calculateDifference(map1, map2);

        return Formatter.format(list, format);

    }

    public static String generate(String filepath1, String filepath2) throws Exception {
        return generate(filepath1, filepath2, "stylish");
    }
}
