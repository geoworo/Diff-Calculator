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

        StringBuilder sb = new StringBuilder();
        sb.append("{\n  - oceans: [Pacific, Atlantic, Indian, Southern, Arctic]\n  + oceans: [North Atlantic, Indian, Pacific]\n");
        sb.append("  - period: {beginning=1500, end=1830}\n  + period: {beginning=1650, end=1730}\n  + pirates: [Henry Every, Anne Bonny]\n");
        sb.append("  - region: the Caribbean\n    topic: piracy\n}");
        String expected1 = sb.toString();
        assertEquals(expected1, Differ.generate("src/test/resources/ffile.json", "src/test/resources/sfile.json", "stylish"));
        assertEquals(expected1, Differ.generate("src/test/resources/yaml1.yml", "src/test/resources/syaml1.yml", "stylish"));

        String expected2 = "{\n    age: 23\n    firstName: Kyle\n";
        expected2 = expected2 + "    id: 1234567\n    middleName: Connor\n    secondName: Black\n}";
        assertEquals(expected2, Differ.generate("src/test/resources/ffile1.json", "src/test/resources/sfile1.json", "stylish"));
        assertEquals(expected2, Differ.generate("src/test/resources/yaml2.yml", "src/test/resources/syaml2.yml", "stylish"));

    }
}
