package hexlet.code;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DifferTest {

    static final String FILE1_JSON  = "src/test/resources/test1.json";
    static final String FILE2_JSON = "src/test/resources/test2.json";
    static final String FILE1_YAML = "src/test/resources/test1.yml";
    static final String FILE2_YAML = "src/test/resources/test2.yml";

    static final Path STYLISH_PATH = Path.of("src/test/resources/resultstylish.txt");
    static final Path PLAIN_PATH = Path.of("src/test/resources/resultplain.txt");
    static final Path JSON_PATH = Path.of("src/test/resources/resultjson.txt");

    static String plainresult;
    static String stylishresult;
    static String jsonresult;

    @BeforeAll

    public static void beforeEach() throws Exception {
        plainresult = Files.readString(PLAIN_PATH);
        stylishresult = Files.readString(STYLISH_PATH);
        jsonresult = Files.readString(JSON_PATH);
    }

    @Test

    public void testGen() throws Exception {
        assertEquals(stylishresult, Differ.generate(FILE1_JSON, FILE2_JSON));
        assertEquals(stylishresult, Differ.generate(FILE1_YAML, FILE2_YAML));

        assertEquals(plainresult, Differ.generate(FILE1_JSON, FILE2_JSON, "plain"));
        assertEquals(plainresult, Differ.generate(FILE1_YAML, FILE2_YAML, "plain"));

        assertEquals(jsonresult, Differ.generate(FILE1_JSON, FILE2_JSON, "json"));
        assertEquals(jsonresult, Differ.generate(FILE1_YAML, FILE2_YAML, "json"));
    }
}
