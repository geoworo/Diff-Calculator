package hexlet.code.formatters;

import java.util.Map;
import java.util.List;

public class FormatterPlain {
    public static String formatAsPlain(List<Map<String, Object>> data) throws Exception {

        StringBuilder sb = new StringBuilder();

        for (var map : data) {
            String type = map.get("type").toString();
            switch (type) {
                case "changed":
                    String value1 = stringify(map.get("value1"));
                    String value2 = stringify(map.get("value2"));
                    sb.append("Property '" + map.get("key") + "' was updated. From " + value1 + " to " + value2);
                    sb.append("\n");
                    break;
                case "removed":
                    sb.append("Property '" + map.get("key") + "' was removed");
                    sb.append("\n");
                    break;
                case "added":
                    String value = stringify(map.get("value"));
                    sb.append("Property '" + map.get("key") + "' was added with value: " + value);
                    sb.append("\n");
                    break;
                case "unchanged":
                    break;
                default:
                    throw new Exception("Unknown status.");
            }
        }

        return sb.toString().trim();
    }

    private static String stringify(Object value) {
        if (value instanceof String) {
            return "'" + value + "'";
        } else if (value instanceof Map<?, ?> || value instanceof Iterable<?>) {
            return "[complex value]";
        } else if (value == null) {
            return "null";
        } else {
            return value.toString();
        }
    }
}
