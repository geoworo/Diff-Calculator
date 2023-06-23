package hexlet.code;

import java.util.Map;
import java.util.TreeSet;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.ArrayList;

public class DiffCalculator {
    public static List<Map<String, Object>> calculateDifference(Map<String, Object> map1, Map<String, Object> map2) {
        SortedSet<String> sum = new TreeSet<>(map1.keySet());
        sum.addAll(map2.keySet());
        List<Map<String, Object>> result = new ArrayList<>();

        for (String key: sum) {
            Map<String, Object> map = new TreeMap<>();
            map.put("key", key);
            if (!map1.containsKey(key)) {
                map.put("type", "added");
                map.put("value", map2.get(key));
            } else if (!map2.containsKey(key)) {
                map.put("type", "removed");
                map.put("value", map1.get(key));
            } else if (map1.get(key).equals(map2.get(key))) {
                map.put("type", "unchanged");
                map.put("value1", map1.get(key));
                map.put("value2", map2.get(key));
            } else {
                map.put("type", "changed");
                map.put("value1", map1.get(key));
                map.put("value2", map2.get(key));
            }
            result.add(map);
        }

        return result;
    }
}
