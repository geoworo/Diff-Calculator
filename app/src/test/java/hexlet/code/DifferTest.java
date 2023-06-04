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

        Exception exception = assertThrows(Exception.class, () -> Differ.generate("nonexistent.json", "nonexistent.json"));
        String expectedMessage = "does not exist";
        String actualMessage = exception.getMessage();

        Exception exception2 = assertThrows(Exception.class, () -> Differ.generate(fileone, filetwo));
        String expectedMessage2 = "does not exist";
        String actualMessage2 = exception2.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
        assertTrue(actualMessage2.contains(expectedMessage2));

        String expected1 = """
         {
            count: 3
          - country: Colombia
          + date: 1720
          - items: [rum, pirates, ships]
          + items: [ships]
         }""";
        assertEquals(expected1, Differ.generate("src/test/resources/ffile.json", "src/test/resources/sfile.json"));

        String expected2 = """
        {
           age: 23
           firstName: Kyle
           id: 1234567
           middleName: Connor
           secondName: Black
        }""";
        assertEquals(expected2, Differ.generate("src/test/resources/ffile1.json", "src/test/resources/sfile1.json"));

    }
}
