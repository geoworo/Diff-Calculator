package hexlet.code.formatters;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Map;
import java.util.List;

public class FormatterJson {
    public static String formatAsJson(List<Map<String, Object>> data) throws Exception {
        ObjectMapper om = new ObjectMapper();
        return om.writerWithDefaultPrettyPrinter().writeValueAsString(data);
    }
}
