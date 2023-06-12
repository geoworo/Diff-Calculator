package hexlet.code;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DifferTest {

    @Test
    public void testGen() throws Exception {
        String fileone = "src/test/resources/ffile2.json";
        String filetwo = "src/test/resources/sfile2.json";
        String nonexistent = "nonexistent.json";

        Exception exception = assertThrows(Exception.class, () -> Differ.generate(nonexistent, nonexistent, "stylish"));
        String expectedMessage = "does not exist";
        String actualMessage = exception.getMessage();

        Exception exception2 = assertThrows(Exception.class, () -> Differ.generate(fileone, filetwo, "stylish"));
        String expectedMessage2 = "does not exist";
        String actualMessage2 = exception2.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
        assertTrue(actualMessage2.contains(expectedMessage2));

        String json1 = "src/test/resources/ffile.json";
        String json2 = "src/test/resources/sfile.json";
        String yaml1 = "src/test/resources/yaml1.yml";
        String yaml2 = "src/test/resources/syaml1.yml";

        String diff1 = Differ.generate(json1, json2, "stylish");
        String diff2 = Differ.generate(yaml1, yaml2, "stylish");

        StringBuilder sb = new StringBuilder("{\n");
        sb.append("  - oceans: [Pacific, Atlantic, Indian, Southern, Arctic]\n");
        sb.append("  + oceans: [North Atlantic, Indian, Pacific]\n");
        sb.append("  - period: {beginning=1500, end=1830}\n");
        sb.append("  + period: {beginning=1650, end=1730}\n");
        sb.append("  + pirates: [Henry Every, Anne Bonny]\n");
        sb.append("  - region: the Caribbean\n    topic: piracy\n}");
        String expected1 = sb.toString();

        assertEquals(expected1, diff1);
        assertEquals(expected1, diff2);

        String expected2 = "{\n    age: 23\n    firstName: Kyle\n";
        expected2 = expected2 + "    id: 1234567\n    middleName: Connor\n    secondName: Black\n}";
        assertEquals(expected2, Differ.generate("src/test/resources/ffile1.json", "src/test/resources/sfile1.json", "stylish"));
        assertEquals(expected2, Differ.generate("src/test/resources/yaml2.yml", "src/test/resources/syaml2.yml", "stylish"));

    }
}
