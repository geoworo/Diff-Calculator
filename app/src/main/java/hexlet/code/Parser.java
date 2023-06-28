package hexlet.code;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Map;

public class Parser {
    public static Map<String, Object> parse(File file, String format) throws Exception {

        switch (format) {
            case "json":
                return getJSON(file);
            case "yaml", "yml":
                return getYAML(file);
            default:
                throw new Exception("File format is not supported");
        }
    }

    public static Map<String, Object> getYAML(File file) throws FileNotFoundException {
        Yaml yaml = new Yaml();
        Map<String, Object> result = (Map<String, Object>) yaml.load(new FileInputStream(file));
        return result;
    }

    public static Map<String, Object> getJSON(File file) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(file, Map.class);
    }
}
