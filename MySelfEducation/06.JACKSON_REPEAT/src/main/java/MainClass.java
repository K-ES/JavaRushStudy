import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Map;

public class MainClass {
    public static void main(String[] args) throws IOException
    {
        String jsonString = "{\n" +
                "  \"total_in_month\": 95,\n" +
                "  \"status\": \"valid\",\n" +
                "  \"data\": [\n" +
                "    {\n" +
                "      \"total_on_date\": 47,\n" +
                "      \"date\": \"01-01-2020\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"total_on_date\": 48,\n" +
                "      \"date\": \"08-01-2020\"\n" +
                "    }\n" +
                "  ]\n" +
                "}";

        ObjectMapper objectMapper = new ObjectMapper();
//        Map map = objectMapper.readValue(jsonString, Map.class);
        TypeReference<Map<String, Object>> ref = new TypeReference<Map<String, Object>>() {};
        Map<String, Object> map = objectMapper.readValue(jsonString, ref);
        System.out.println(map);
    }
}
