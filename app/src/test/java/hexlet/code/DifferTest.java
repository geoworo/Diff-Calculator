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
        sb.append("  + pirates: Henry Every\n");
        sb.append("  - region: the Caribbean\n    topic: piracy\n}");
        String expected1 = sb.toString();

        assertEquals(expected1, diff1);
        assertEquals(expected1, diff2);

        String json3 = "src/test/resources/ffile1.json";
        String json4 = "src/test/resources/sfile1.json";
        String diff3 = Differ.generate(json3, json4, "stylish");

        String expected2 = "{\n    age: 23\n    firstName: Kyle\n";
        expected2 = expected2 + "    id: 1234567\n    middleName: Connor\n    secondName: Black\n}";

        assertEquals(expected2, diff3);

        StringBuilder sb1 = new StringBuilder();
        sb1.append("Property 'oceans' was updated. From [complex value] to [complex value]\n");
        sb1.append("Property 'period' was updated. From [complex value] to [complex value]\n");
        sb1.append("Property 'pirates' was added with value: 'Henry Every'\n");
        sb1.append("Property 'region' was removed");
        String expected3 = sb1.toString();

        String diff4 = Differ.generate(json1, json2, "plain");

        assertEquals(expected3, diff4);

        StringBuilder sb2 = new StringBuilder();
        sb2.append("{\n  \"oceans\" : {\n    \"FirstFile\" : [ \"Pacific\", \"Atlantic\", \"Indian\", \"Southern\", \"Arctic\" ],\n");
        sb2.append("    \"SecondFile\" : [ \"North Atlantic\", \"Indian\", \"Pacific\" ]\n  },\n  \"period\" : {\n    \"FirstFile\" : {\n");
        sb2.append("      \"beginning\" : 1500,\n      \"end\" : 1830\n    },\n    \"SecondFile\" : {\n      \"beginning\" : 1650,\n");
        sb2.append("      \"end\" : 1730\n    }\n  },\n  \"pirates\" : {\n    \"SecondFile\" : \"Henry Every\"\n  },\n  \"region\" : {\n");
        sb2.append("    \"FirstFile\" : \"the Caribbean\"\n  },\n  \"topic\" : {\n    \"FirstFile\" : \"piracy\",\n");
        sb2.append("    \"SecondFile\" : \"piracy\"\n  }\n}\n");

        String expected = sb2.toString();
        String actual = Differ.generate(json1, json2, "json");

    }
}
