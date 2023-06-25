package hexlet.code;

import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DifferTest {

    @Test
    public void testGen() throws Exception {
        String file1json = "src/test/resources/test1.json";
        String file2json = "src/test/resources/test2.json";
        String file1yaml = "src/test/resources/test1.yml";
        String file2yaml = "src/test/resources/test2.yml";

        Path stylishpath = Path.of("src/test/resources/resultstylish.txt");
        Path plainpath = Path.of("src/test/resources/resultplain.txt");
        Path jsonpath = Path.of("src/test/resources/resultjson.txt");

        String resultplain = Files.readString(plainpath);
        String resultstylish = Files.readString(stylishpath);
        String resultjson = Files.readString(jsonpath);

        assertEquals(resultstylish, Differ.generate(file1json, file2json));
        assertEquals(resultstylish, Differ.generate(file1yaml, file2yaml));

        assertEquals(resultplain, Differ.generate(file1json, file2json, "plain"));
        assertEquals(resultplain, Differ.generate(file1yaml, file2yaml, "plain"));

        assertEquals(resultjson, Differ.generate(file1json, file2json, "json"));
        assertEquals(resultjson, Differ.generate(file1yaml, file2yaml, "json"));
    }
}
