package hexlet.code;

import java.util.Map;

public class Formatter {
    public static String format(Map<String, String> data, String format) throws Exception {
        switch (format) {
            case "stylish":
                return formatAsStylish(data);
            case "plain":
                return formatAsPlain(data);
            default:
                throw new Exception("Invalid output format.");
        }
    }

    public static String formatAsStylish(Map<String, String> data) throws Exception {
        String result = "";

        StringBuilder sb = new StringBuilder("{\n");

        for (String keyAndStatus : data.keySet()) {
            String status = keyAndStatus.split("/")[1];
            String key = keyAndStatus.split("/")[0];

            switch (status) {
                case "a":
                    sb.append("  + " + key + ": " + data.get(keyAndStatus) + "\n");
                    break;
                case "d":
                    sb.append("  - " + key + ": " + data.get(keyAndStatus) + "\n");
                    break;
                case "c":
                    sb.append("  - " + key + ": " + data.get(keyAndStatus).split("/")[0] + "\n");
                    sb.append("  + " + key + ": " + data.get(keyAndStatus).split("/")[1] + "\n");
                    break;
                case "u":
                    sb.append("    " + key + ": " + data.get(keyAndStatus) + "\n");
                    break;
                default:
                    throw new Exception("Invalid status: " + status);
            }
        }

        sb.append("}");
        result = sb.toString();

        return result;
    }

    public static String formatAsPlain(Map<String, String> data) {
        return null;
    }
}
