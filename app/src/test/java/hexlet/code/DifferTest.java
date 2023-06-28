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

    private static String file1json;
    private static String file2json;
    private static String file1yml;
    private static String file2yml;

    private static String plainResult;
    private static String stylishResult;
    private static List<Map<String, Object>> jsonResult;

    @BeforeAll

    public static void beforeEach() throws Exception {
        file1json = getFilePath("test1.json");
        file2json = getFilePath("test2.json");
        file1yml = getFilePath("test1.yml");
        file2yml = getFilePath("test2.yml");

        plainResult = getContent("resultplain.txt");
        stylishResult = getContent("resultstylish.txt");

        jsonResult = getMap(getContent("result.json"));
    }

    @Test

    public void testGen() throws Exception {
        assertEquals(stylishResult, Differ.generate(file1json, file2json));
        assertEquals(stylishResult, Differ.generate(file1yml, file2yml));

        assertEquals(stylishResult, Differ.generate(file1json, file2json), "stylish");
        assertEquals(stylishResult, Differ.generate(file1yml, file2yml), "stylish");

        assertEquals(plainResult, Differ.generate(file1json, file2json, "plain"));
        assertEquals(plainResult, Differ.generate(file1yml, file2yml, "plain"));

        var actualjson1 = getMap(Differ.generate(file1json, file2json, "json"));
        var actualjson2 = getMap(Differ.generate(file1yml, file2yml, "json"));
        assertEquals(jsonResult, actualjson1);
        assertEquals(jsonResult, actualjson2);
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
