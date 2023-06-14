package hexlet.code;

import hexlet.code.formatters.FormatterPlain;
import hexlet.code.formatters.FormatterStylish;

import java.util.Map;

public class Formatter {
    public static String format(Map<String, String> data, String format) throws Exception {
        switch (format) {
            case "stylish":
                return FormatterStylish.formatAsStylish(data);
            case "plain":
                return FormatterPlain.formatAsPlain(data);
            default:
                throw new Exception("Invalid output format.");
        }
    }
}
