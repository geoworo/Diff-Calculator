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

        Exception exception = assertThrows(Exception.class, () -> Differ.generate(nonexistent, nonexistent));
        String expectedMessage = "does not exist";
        String actualMessage = exception.getMessage();

        Exception exception2 = assertThrows(Exception.class, () -> Differ.generate(fileone, filetwo));
        String expectedMessage2 = "does not exist";
        String actualMessage2 = exception2.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
        assertTrue(actualMessage2.contains(expectedMessage2));

        String expected1 = "{\n   count: 3\n - country: Colombia\n + date: 1720\n";
        expected1 = expected1 + " - items: rum, pirates, ships\n + items: ships\n}";
        assertEquals(expected1, Differ.generate("src/test/resources/ffile.json", "src/test/resources/sfile.json"));
        assertEquals(expected1, Differ.generate("src/test/resources/yaml1.yml", "src/test/resources/syaml1.yml"));

        String expected2 = "{\n   age: 23\n   firstName: Kyle\n";
        expected2 = expected2 + "   id: 1234567\n   middleName: Connor\n   secondName: Black\n}";
        assertEquals(expected2, Differ.generate("src/test/resources/ffile1.json", "src/test/resources/sfile1.json"));
        assertEquals(expected2, Differ.generate("src/test/resources/yaml2.yml", "src/test/resources/syaml2.yml"));

    }
}
