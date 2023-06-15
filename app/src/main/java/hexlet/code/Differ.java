package hexlet.code;

import java.io.File;
import java.util.*;

public class Differ {
    public static String generate(String filepath1, String filepath2, String format) throws Exception {
        Map<String, Object> map1 = Parser.parse(filepath1);
        Map<String, Object> map2 = Parser.parse(filepath2);

        SortedSet<String> sum = new TreeSet<>(map1.keySet());
        sum.addAll(map2.keySet());

        SortedMap<String, String> result = new TreeMap<>();

        for (String key: sum) {

            if (!map1.containsKey(key)) {
                result.put(key + "/a", transform(map2.get(key), format));
                // a = added
            } else if (!map2.containsKey(key)) {
                result.put(key + "/d", transform(map1.get(key), format));
                // d = deleted
            } else if (map1.get(key).equals(map2.get(key))) {
                result.put(key + "/u", transform(map1.get(key), format));
                // u = unchanged
            } else {
                result.put(key + "/c", (transform(map1.get(key), format)) + "/" + transform(map2.get(key), format));
                // c = changed
            }

        }

        return Formatter.format(result, format);

    }

    public static String transform(Object obj, String format) {
        if (format.equals("stylish")) {
            return obj.toString();
        }

        if (obj instanceof String) {
            return "'" + obj.toString() + "'";
        }

        if (obj instanceof Map<?,?> || obj instanceof Object[] || obj instanceof Iterable<?>) {
            return "[complex value]";
        }

        return obj.toString();
    }
}
