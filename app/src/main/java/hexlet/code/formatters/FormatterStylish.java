package hexlet.code.formatters;

import java.util.Map;

public class FormatterStylish {
    public static String formatAsStylish(Map<String, Map<String, Object>> data) throws Exception {
        StringBuilder sb = new StringBuilder("{\n");

        for (String key : data.keySet()) {
            Map<String, Object> values = data.get(key);

            if (!values.containsKey("FirstFile")) {
                String value2 = values.get("SecondFile").toString();
                sb.append("  + " + key + ": " + value2);
                sb.append("\n");
            } else if (!values.containsKey("SecondFile")) {
                String value1 = values.get("FirstFile").toString();
                sb.append("  - " + key + ": " + value1);
                sb.append("\n");
            } else if (!values.get("FirstFile").equals(values.get("SecondFile"))) {
                String value1 = values.get("FirstFile").toString();
                String value2 = values.get("SecondFile").toString();
                sb.append("  - " + key + ": " + value1);
                sb.append("\n");
                sb.append("  + " + key + ": " + value2);
                sb.append("\n");
            } else {
                sb.append("    " + key + ": " + values.get("FirstFile").toString());
                sb.append("\n");
            }
        }

        sb.append("}");
        String result = sb.toString();

        return result;
    }
}
