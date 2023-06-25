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

    static String RESULT_PLAIN;
    static String RESULT_STYLISH;
    static String RESULT_JSON;

    @BeforeAll

    public static void beforeEach() throws Exception {
        RESULT_PLAIN = Files.readString(PLAIN_PATH);
        RESULT_STYLISH = Files.readString(STYLISH_PATH);
        RESULT_JSON = Files.readString(JSON_PATH);
    }

    @Test

    public void testGen() throws Exception {
        assertEquals(RESULT_STYLISH, Differ.generate(FILE1_JSON, FILE2_JSON));
        assertEquals(RESULT_STYLISH, Differ.generate(FILE1_YAML, FILE2_YAML));

        assertEquals(RESULT_PLAIN, Differ.generate(FILE1_JSON, FILE2_JSON, "plain"));
        assertEquals(RESULT_PLAIN, Differ.generate(FILE1_YAML, FILE2_YAML, "plain"));

        assertEquals(RESULT_JSON, Differ.generate(FILE1_JSON, FILE2_JSON, "json"));
        assertEquals(RESULT_JSON, Differ.generate(FILE1_YAML, FILE2_YAML, "json"));
    }
}
