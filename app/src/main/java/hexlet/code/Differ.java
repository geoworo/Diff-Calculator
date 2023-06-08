package hexlet.code;

import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;


public class Differ {
    public static String generate(String filepath1, String filepath2) throws Exception {
        Map<String, Object> map1 = Parser.parse(filepath1);
        Map<String, Object> map2 = Parser.parse(filepath2);

        StringBuilder sb = new StringBuilder("{\n");

        SortedSet<String> keys = new TreeSet<>(map1.keySet());
        keys.addAll(map2.keySet());

        for (String key: keys) {

            if (!map1.containsKey(key)) {
                sb.append(" + " + key + ": " + map2.get(key) + "\n");
            } else if (!map2.containsKey(key)) {
                sb.append(" - " + key + ": " + map1.get(key) + "\n");
            } else if (map1.get(key).equals(map2.get(key))) {
                sb.append("   " + key + ": " + map1.get(key) + "\n");
            } else {
                sb.append(" - " + key + ": " + map1.get(key) + "\n");
                sb.append(" + " + key + ": " + map2.get(key) + "\n");
            }
        }
        sb.append("}");
        return sb.toString();
    }
}
