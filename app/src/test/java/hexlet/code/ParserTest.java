package hexlet.code;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.util.LinkedHashMap;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {

    static Map<String, Object> map = new LinkedHashMap<>();


    @BeforeAll
    public static void beforeAll() {
        map.put("firstName", "Kyle");
        map.put("secondName", "Black");
        map.put("middleName", "Connor");
        map.put("age", 23);
        map.put("id", 1234567);
    }
    @Test
    public void testParse() throws Exception {
        assertEquals(map, Parser.parse("src/test/resources/ffile1.json"));
        assertEquals(map, Parser.parse("src/test/resources/yaml2.yml"));
        assertFalse(map.equals(Parser.parse("src/test/resources/ffile.json")));
    }
}
