package hexlet.code;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DifferTest {

    @Test
    public void testGen() {
        Exception exception = assertThrows(Exception.class, () -> Differ.generate("fileone.json", "filetwo.json"));
        String expectedMessage = "does not exist";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));

        //TODO: finish tests
    }
}
