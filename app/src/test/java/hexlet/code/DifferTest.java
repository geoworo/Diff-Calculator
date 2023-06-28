package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DifferTest {

    private static String FILE1_JSON;
    private static String FILE2_JSON;
    private static String FILE1_YAML;
    private static String FILE2_YAML;

    private static String PLAIN_RESULT;
    private static String STYLISH_RESULT;
    private static List<Map<String, Object>> JSON_RESULT;

    @BeforeAll

    public static void beforeEach() throws Exception {
        FILE1_JSON = getFilePath("test1.json");
        FILE2_JSON = getFilePath("test2.json");
        FILE1_YAML = getFilePath("test1.yml");
        FILE2_YAML = getFilePath("test2.yml");

        PLAIN_RESULT = getContent("resultplain.txt");
        STYLISH_RESULT = getContent("resultstylish.txt");

        JSON_RESULT = getMap(getContent("result.json"));
    }

    @Test

    public void testGen() throws Exception {
        assertEquals(STYLISH_RESULT, Differ.generate(FILE1_JSON, FILE2_JSON));
        assertEquals(STYLISH_RESULT, Differ.generate(FILE1_YAML, FILE2_YAML));

        assertEquals(STYLISH_RESULT, Differ.generate(FILE1_JSON, FILE2_JSON), "stylish");
        assertEquals(STYLISH_RESULT, Differ.generate(FILE1_YAML, FILE2_YAML), "stylish");

        assertEquals(PLAIN_RESULT, Differ.generate(FILE1_JSON, FILE2_JSON, "plain"));
        assertEquals(PLAIN_RESULT, Differ.generate(FILE1_YAML, FILE2_YAML, "plain"));

        var actualjson1 = getMap(Differ.generate(FILE1_JSON, FILE2_JSON, "json"));
        var actualjson2 = getMap(Differ.generate(FILE1_YAML, FILE2_YAML, "json"));
        assertEquals(JSON_RESULT, actualjson1);
        assertEquals(JSON_RESULT, actualjson2);
    }

    public static String getFilePath(String fileName) {
        return "src/test/resources/" + fileName;
    }

    public static String getContent(String fileName) throws Exception {
        Path path = Path.of(getFilePath(fileName));
        return Files.readString(path);
    }

    public static List<Map<String, Object>> getMap(String data) throws Exception {
        ObjectMapper om = new ObjectMapper();
        List<Map<String, Object>> list;
        list = om.readValue(data, new TypeReference<>() {
        });
        return list;
    }
}
