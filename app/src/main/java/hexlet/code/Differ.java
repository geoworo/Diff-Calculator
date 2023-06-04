package hexlet.code;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class Differ {
    public static String generate(String filepath1, String filepath2) throws Exception {
        Map<String, Object> map1 = getData(filepath1);
        Map<String, Object> map2 = getData(filepath2);

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

    public static Map<String, Object> getData(String filepath) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> mapresult = new HashMap<>();

        Path path = Paths.get(filepath).toAbsolutePath().normalize();

        File file = new File(String.valueOf(path));

        if (file.length() == 0) {
            throw new Exception("File " + filepath + " does not exist or is empty.");
        }

        mapresult = mapper.readValue(file, Map.class);
        return mapresult;
    }
}
