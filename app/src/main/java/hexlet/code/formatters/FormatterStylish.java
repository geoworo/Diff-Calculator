package hexlet.code.formatters;

import java.util.Map;
import java.util.List;

public class FormatterStylish {
    public static String formatAsStylish(List<Map<String, Object>> data) {
        StringBuilder sb = new StringBuilder("{\n");

        for (var map: data) {
            String type = map.get("type").toString();
            switch (type) {
                case "changed":
                    String value1 = transform(map.get("value1"));
                    String value2 = transform(map.get("value2"));
                    sb.append("  - " + map.get("key") + ": " + value1);
                    sb.append("\n");
                    sb.append("  + " + map.get("key") + ": " + value2);
                    sb.append("\n");
                    break;
                case "removed":
                    sb.append("  - " + map.get("key") + ": " + map.get("value"));
                    sb.append("\n");
                    break;
                case "added":
                    sb.append("  + " + map.get("key") + ": " + map.get("value"));
                    sb.append("\n");
                    break;
                default:
                    sb.append("    " + map.get("key") + ": " + map.get("value1"));
                    sb.append("\n");
                    break;
            }
        }

        sb.append("}");
        return sb.toString();
    }

    public static String transform(Object obj) {
        if (obj == null) {
            return "null";
        } else {
            return obj.toString();
        }
    }
}
