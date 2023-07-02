package hexlet.code;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;

public class DiffCalculator {
    public static List<Map<String, Object>> calculateDifference(Map<String, Object> map1, Map<String, Object> map2) {
        SortedSet<String> sum = new TreeSet<>(map1.keySet());
        sum.addAll(map2.keySet());
        List<Map<String, Object>> result = new ArrayList<>();

        for (String key : sum) {
            Map<String, Object> map = new LinkedHashMap<>();
            map.put("key", key);
            if (!map1.containsKey(key)) {
                map.put("type", "added");
                map.put("value", map2.get(key));
            } else if (!map2.containsKey(key)) {
                map.put("type", "removed");
                map.put("value", map1.get(key));
            } else if (areEqual(map1.get(key), map2.get(key))) {
                map.put("type", "unchanged");
                map.put("value", map1.get(key));
            } else {
                map.put("type", "changed");
                map.put("value1", map1.get(key));
                map.put("value2", map2.get(key));
            }
            result.add(map);
        }

        return result;
    }

    public static boolean areEqual(Object obj1, Object obj2) {
        if (obj1 == null && obj2 == null) {
            return true;
        } else if (obj1 == null || obj2 == null) {
            return false;
        } else {
            return obj1.equals(obj2);
        }
    }
}
