package hexlet.code.formatters;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Map;

public class FormatterJson {
    public static String formatAsJson(Map<String, Map<String, Object>> data) throws Exception {
        ObjectMapper om = new ObjectMapper();
        try {
            var json = om.writerWithDefaultPrettyPrinter()
                    .writeValueAsString(data);
            return json;

        } catch (Exception e) {
            throw new Exception("Json file could not be generated");
        }
    }
}
