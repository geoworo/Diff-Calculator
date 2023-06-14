package hexlet.code.formatters;

import java.util.Map;

public class FormatterStylish {
    public static String formatAsStylish(Map<String, String> data) throws Exception {
        StringBuilder sb = new StringBuilder("{\n");

        for (String keyAndStatus : data.keySet()) {
            String status = keyAndStatus.split("/")[1];
            String key = keyAndStatus.split("/")[0];

            switch (status) {
                case "a":
                    sb.append(formatAdded(data, key, keyAndStatus));
                    break;
                case "d":
                    sb.append(formatDeleted(data, key, keyAndStatus));
                    break;
                case "c":
                    sb.append(formatChanged(data, key, keyAndStatus));
                    break;
                case "u":
                    sb.append(formatUnchanged(data, key, keyAndStatus));
                    break;
                default:
                    throw new Exception("Invalid status: " + status);
            }
        }

        sb.append("}");
        String result = sb.toString();

        return result;
    }

    public static String formatAdded(Map<String, String> data, String key, String keyAndStatus) {
        return "  + " + key + ": " + data.get(keyAndStatus) + "\n";
    }

    public static String formatDeleted(Map<String, String> data, String key, String keyAndStatus) {
        return "  - " + key + ": " + data.get(keyAndStatus) + "\n";
    }

    public static String formatChanged(Map<String, String> data, String key, String keyAndStatus) {
        String result = "  - " + key + ": " + data.get(keyAndStatus).split("/")[0] + "\n";
        return result + "  + " + key + ": " + data.get(keyAndStatus).split("/")[1] + "\n";
    }

    public static String formatUnchanged(Map<String, String> data, String key, String keyAndStatus) {
        return "    " + key + ": " + data.get(keyAndStatus) + "\n";
    }
}
