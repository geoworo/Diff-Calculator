package hexlet.code.formatters;

import java.util.Map;

public class FormatterPlain {
    public static String formatAsPlain(Map<String, Map<String, Object>> data) throws Exception {

        StringBuilder sb = new StringBuilder();

        for (var key : data.keySet()) {
            Map<String, Object> values = data.get(key);

            if (!values.containsKey("FirstFile")) {
                String value2 = transformValue(values.get("SecondFile"));
                sb.append("Property '" + key + "' was added with value: " + value2);
            } else if (!values.containsKey("SecondFile")) {
                sb.append("Property '" + key + "' was removed");
            } else if (!values.get("FirstFile").equals(values.get("SecondFile"))) {
                String value1 = transformValue(values.get("FirstFile"));
                String value2 = transformValue(values.get("SecondFile"));
                sb.append("Property '" + key + "' was updated. From " + value1 + " to " + value2);
            }

            sb.append("\n");
        }

        return sb.toString().trim();
    }

    public static String transformValue(Object value) {
        if (value instanceof String) {
            return "'" + value + "'";
        } else if (value instanceof Map<?,?> || value instanceof Iterable<?>) {
            return "[complex value]";
        } else {
            return value.toString();
        }
    }
}
