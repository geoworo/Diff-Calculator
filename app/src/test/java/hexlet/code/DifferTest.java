package hexlet.code;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DifferTest {

    private static String file1json;
    private static String file2json;
    private static String file1yml;
    private static String file2yml;

    private static String plainResult;
    private static String stylishResult;
    private static JsonNode jsonResult;

    @BeforeAll
    public static void beforeEach() throws Exception {
        ObjectMapper om = new ObjectMapper();

        file1json = getFilePath("test1.json");
        file2json = getFilePath("test2.json");
        file1yml = getFilePath("test1.yml");
        file2yml = getFilePath("test2.yml");

        plainResult = getContent("resultplain.txt");
        stylishResult = getContent("resultstylish.txt");

        jsonResult = om.readTree(getContent("result.json"));
    }

    @Test
    public void generateTest() throws Exception {
        ObjectMapper om = new ObjectMapper();

        assertEquals(stylishResult, Differ.generate(file1json, file2json));
        assertEquals(stylishResult, Differ.generate(file1yml, file2yml));

        assertEquals(stylishResult, Differ.generate(file1json, file2json), "stylish");
        assertEquals(stylishResult, Differ.generate(file1yml, file2yml), "stylish");

        assertEquals(plainResult, Differ.generate(file1json, file2json, "plain"));
        assertEquals(plainResult, Differ.generate(file1yml, file2yml, "plain"));

        var actualjson1 = om.readTree(Differ.generate(file1json, file2json, "json"));
        var actualjson2 = om.readTree(Differ.generate(file1yml, file2yml, "json"));
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
}
