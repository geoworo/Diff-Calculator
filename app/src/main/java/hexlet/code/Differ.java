package hexlet.code;

import java.util.Map;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.TreeMap;

public class Differ {
    public static String generate(String filepath1, String filepath2, String format) throws Exception {

        Map<String, Object> map1 = Parser.parse(filepath1);
        Map<String, Object> map2 = Parser.parse(filepath2);

        SortedSet<String> sum = new TreeSet<>(map1.keySet());
        sum.addAll(map2.keySet());

        SortedMap<String, Map<String, Object>> result = new TreeMap<>();

        for (String key: sum) {
            Map<String, Object> values = new TreeMap<>();
            if (!map1.containsKey(key)) {
                values.put("SecondFile", map2.get(key));
            } else if (!map2.containsKey(key)) {
                values.put("FirstFile", map1.get(key));
            } else {
                values.put("FirstFile", map1.get(key));
                values.put("SecondFile", map2.get(key));
            }
            result.put(key, values);
        }

        return Formatter.format(result, format);
    }
}
