package hexlet.code;

import java.util.SortedSet;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeSet;
import java.util.TreeMap;

public class Differ {
    public static String generate(String filepath1, String filepath2, String format) throws Exception {
        Map<String, Object> map1 = Parser.parse(filepath1);
        Map<String, Object> map2 = Parser.parse(filepath2);

        SortedSet<String> sum = new TreeSet<>(map1.keySet());
        sum.addAll(map2.keySet());

        SortedMap<String, String> result = new TreeMap<>();

        for (String key: sum) {

            if (!map1.containsKey(key)) {
                result.put(key + "/a", (map2.get(key)).toString());
                // a = added
            } else if (!map2.containsKey(key)) {
                result.put(key + "/d", (map1.get(key)).toString());
                // d = deleted
            } else if (map1.get(key).equals(map2.get(key))) {
                result.put(key + "/u", (map1.get(key)).toString());
                // u = unchanged
            } else {
                result.put(key + "/c", (map1.get(key)).toString() + "/" + (map2.get(key)).toString());
                // c = changed
            }

        }

        return Formatter.format(result, format);

    }
}
