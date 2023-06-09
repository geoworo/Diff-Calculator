package hexlet.code;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

public class Parser {
    public static Map<String, Object> parse(String path) throws Exception {

        Path normalizedPath = Paths.get(path).toAbsolutePath().normalize();
        File file = new File(String.valueOf(normalizedPath));

        if (file.length() == 0) {
            throw new Exception("File " + path + " does not exist or is empty.");
        }

        String fileName = file.getName();
        int index = fileName.lastIndexOf('.');
        if (index <= 0) {
            throw new Exception("File " + path + " format is not specified.");
        }

        String format = fileName.substring(index + 1);

        switch (format) {
            case "json":
                return getJSON(file);
            case "yaml", "yml":
                return getYAML(file);
            default:
                throw new Exception("File " + path + " format is not supported");
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
