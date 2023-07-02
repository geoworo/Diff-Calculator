package hexlet.code;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Map;

public class Parser {
    public static Map<String, Object> parse(String content, String format) throws Exception {
        ObjectMapper om = new ObjectMapper();
        Map<String, Object> map;
        switch(format) {
            case "yaml", "yml":
                Yaml yaml = new Yaml();
                return (Map<String, Object>) yaml.load(content);
            case "json":
                return om.readValue(content, Map.class);
            default:
                throw new Exception("Invalid format.");
        }
    }

/*    public static Map<String, Object> getYAML(File file) throws FileNotFoundException {
        Yaml yaml = new Yaml();
        Map<String, Object> result = (Map<String, Object>) yaml.load(new FileInputStream(file));
        return result;
    }

    public static Map<String, Object> getJSON(File file) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(file, Map.class);
    }*/
}
