package hexlet.code.formatters;

import java.util.Map;

public class FormatterPlain {
    public static String formatAsPlain(Map<String, String> data) throws Exception {
        StringBuilder sb = new StringBuilder();

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
                    break;
                default:
                    throw new Exception("Invalid status: " + status);
            }
        }

        String result = sb.toString();

        return result;
    }

    public static String formatAdded(Map<String, String> data, String key, String keyAndStatus) {
        String value = (data.get(keyAndStatus));
        return "Property '" + key + "' was added with value: " + value + "\n";
    }

    public static String formatDeleted(Map<String, String> data, String key, String keyAndStatus) {
        return "Property '" + key + "' was removed\n";
    }

    public static String formatChanged(Map<String, String> data, String key, String keyAndStatus) {
        String value1 = (data.get(keyAndStatus).split("/")[0]);
        String value2 = (data.get(keyAndStatus).split("/")[1]);
        return "Property '" + key + "' was updated. From " + value1 + " to " + value2 + "\n";
    }
}
