package hexlet.code;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.yaml.snakeyaml.Yaml;

import java.util.Map;

public class Parser {
    public static Map<String, Object> parse(String content, String format) throws Exception {
        ObjectMapper om = new ObjectMapper();
        switch (format) {
            case "yaml", "yml":
                Yaml yaml = new Yaml();
                return (Map<String, Object>) yaml.load(content);
            case "json":
                return om.readValue(content, Map.class);
            default:
                throw new Exception("Invalid format.");
        }
    }
}
